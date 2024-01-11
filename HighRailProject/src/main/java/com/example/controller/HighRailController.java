package com.example.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.dao.DaoImplMySQL;
import com.example.entity.Ticket;
import com.example.entity.Tran;
import com.example.entity.User;
import com.example.util.PriceTDXApi;
import com.example.util.TimeTDXApi;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.mchange.v2.cmdline.UnexpectedSwitchArgumentException;

/**
 * http://localhost:8080/HighRailProject/mvc/highrail
 */
@Controller
@RequestMapping("/highrail")
public class HighRailController {

	@Autowired
	private DaoImplMySQL dao;

	// 進入首頁
	@GetMapping("/main")
	// http://localhost:8080/HighRailProject/mvc/highrail/main
	public String main() {
		return "main";
	}

	// 登入頁面
	@GetMapping("/login")
	public String loginpageString(HttpSession session) {
		return "login";
	}

	// 登入
	@PostMapping("/login")
	public String login(@RequestParam("username") String username, @RequestParam("password") String password,
			HttpSession session, Model model) {
		// 根據 username 查找 user 物件
		Optional<User> userOpt = dao.findUserByUsername(username);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			if (user.getUserPassword().equals(password)) { // 比對加密過後的 password 是否相同
				session.setAttribute("user", user); // 將 user 物件放入到 session 變數中
				return "redirect:/mvc/highrail/main"; // OK, 導向前台首頁
			} else {
				session.invalidate(); // session 過期失效
				model.addAttribute("loginMessage", "密碼錯誤");
				return "login";
			}
		} else {
			session.invalidate(); // session 過期失效
			model.addAttribute("loginMessage", "無此使用者");
			return "login";
		}
	}

	// 登出
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/mvc/highrail/main";
	}

	// 註冊頁面
	@GetMapping("/register")
	public String registerpageString(@ModelAttribute User user, HttpSession session) {
		return "register";
	}

	// 註冊
	@PostMapping("/register")
	public String register(@ModelAttribute User user, Model model) {
		Optional<User> userOpt = dao.findUserByUsername(user.getUserName());
		if (userOpt.isPresent()) {
			model.addAttribute("registerMessage", "使用者名稱重複");
			System.out.println("使用者名稱重複");
			return "register";
		} 

			dao.addUser(user);
			return "login";
		
	}

	// 時刻表頁面
	@GetMapping("/timetable")
	public String timeTable() {

		return "timetable";
	}

	// 查詢時刻表
	@PostMapping("/timetable")
	public String timeTableCheck(@RequestParam("fromStation") String fromStation,
			@RequestParam("toStation") String toStation, @RequestParam("departureDate") String departureDate,
			Model model)  throws Exception {

		if (fromStation.equals(toStation)) {

			model.addAttribute("checkingMessage", "起點終點重複");
			System.out.println("起點終點重複");
			return "timetable";
		} 
			String timeTable = TimeTDXApi.getTimeTable(fromStation, toStation, departureDate);
			String departureTime = JsonParser.parseString(timeTable).getAsJsonArray().get(0)
					.getAsJsonObject().get("OriginStopTime")
					.getAsJsonObject().get("DepartureTime")
					.getAsString();
			String arrivalTime = JsonParser.parseString(timeTable).getAsJsonArray().get(0)
					.getAsJsonObject().get("DestinationStopTime")
					.getAsJsonObject().get("ArrivalTime")
					.getAsString();
			
			model.addAttribute("departureTime", departureTime);
			model.addAttribute("arrivalTime", arrivalTime);
			
			
			System.out.println(arrivalTime);
			
			return "timetable";
		
	}

	// 訂票頁面
	@GetMapping("/booking")
	public String booking(HttpSession session, Model model) {
		
		return "booking";
	}

	// 選票頁面
	@PostMapping("/booking/choosing")
	public String choosing(@RequestParam("fromStation") String fromStation,
			@RequestParam("toStation") String toStation, @RequestParam("departureDate") String departureDate,
			@RequestParam("quantity") Integer quantity, Model model) throws Exception {

		if (fromStation.equals(toStation)) {
			model.addAttribute("bookingMessage", "起點終點重複");
			return "booking";
		} 
		
		String fare = PriceTDXApi.getODFare(fromStation, toStation);
		String timeTable = TimeTDXApi.getTimeTable(fromStation, toStation, departureDate);
		
		String departureTime = JsonParser.parseString(timeTable).getAsJsonArray().get(0)
				.getAsJsonObject().get("OriginStopTime")
				.getAsJsonObject().get("DepartureTime")
				.getAsString();
		
		String arrivalTime = JsonParser.parseString(timeTable).getAsJsonArray().get(0)
				.getAsJsonObject().get("DestinationStopTime")
				.getAsJsonObject().get("ArrivalTime")
				.getAsString();
		
		String price = JsonParser.parseString(fare).getAsJsonArray().get(0)
				.getAsJsonObject().get("Fares")
				.getAsJsonArray().get(0)
				.getAsJsonObject().get("Price")
				.getAsString();
		
		System.out.println(price);
		
		return "choosing";
	

	}

	// 訂票結果
	@GetMapping("/booking/choosing/result")
	public String result(@ModelAttribute Tran tran, 
						Model model, HttpSession session) {
		User user = (User)session.getAttribute("user");
		dao.addTran(tran);
		Ticket ticket = new Ticket();
		ticket.setUser(user);
		ticket.setTran(tran);
		dao.addTicket(ticket);
		
		return "redirect:/mvc/highrail/ticketlist";
	}

	// 取消訂票
	@GetMapping("/ticketlist/cancel")
	public String cancelticket(@RequestParam("ticketId") Integer ticketId, HttpSession session) {
		dao.removeTicket(ticketId);
		return "redirect:/mvc/highrail/ticketlist";
	}

	// 查看票夾
	@GetMapping("/ticketlist")
	public String ticketlist(HttpSession session, Model model) {
		// 1. 先找到 user 登入者
		User user = (User) session.getAttribute("user");

		List<Ticket> tickets = dao.findAllTicketsByUserId(user.getUserId());
		model.addAttribute("tickets", tickets);
		return "ticketlist";

	}
}
