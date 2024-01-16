package com.example.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import javax.crypto.spec.SecretKeySpec;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.bean.TrainTime;
import com.example.dao.DaoImplMySQL;
import com.example.entity.Ticket;
import com.example.entity.Tran;
import com.example.entity.User;
import com.example.util.KeyUtil;
import com.example.util.TimeTableAPI;



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

	
	@GetMapping("/getcode")
	private void getCodeImage(HttpSession session, HttpServletResponse response) throws IOException {
		// 產生一個驗證碼 code
		Random random = new Random();
		String code1 = String.format("%c", (char)(random.nextInt(122-65+1) + 65));
		String code2 = String.format("%c", (char)(random.nextInt(122-65+1) + 65));
		String code3 = String.format("%c", (char)(random.nextInt(122-65+1) + 65));
		String code4 = String.format("%c", (char)(random.nextInt(122-65+1) + 65));
		
		String code  = code1+code2+code3+code4;
		session.setAttribute("code", code);
		
		// Java 2D 產生圖檔
		// 1. 建立圖像暫存區
		BufferedImage img = new BufferedImage(100, 100, BufferedImage.TYPE_INT_BGR);
		// 2. 建立畫布
		Graphics g = img.getGraphics();
		// 3. 設定顏色
		g.setColor(Color.lightGray);
		// 4. 塗滿背景
		g.fillRect(0, 0, 100, 100);
		// 5. 設定顏色
		g.setColor(Color.red);
		// 6. 設定自型
		g.setFont(new Font("新細明體", Font.PLAIN, 30));
		// 7. 繪字串
		g.drawString(code1, 10, 23); // code, x, y
		g.drawString(code2, 30, 80);
		g.drawString(code3, 50, 40);
		g.drawString(code4, 80, 70);
		// 8. 干擾線
		g.setColor(Color.white);
		for(int i=0;i<30;i++) {
			int x1 = random.nextInt(100);
			int y1 = random.nextInt(100);
			int x2 = random.nextInt(100);
			int y2 = random.nextInt(100);
			g.drawLine(x1, y1, x2, y2);
		}
		
		// 設定回應類型
		response.setContentType("image/png");
		
		// 將影像串流回寫給 client
		ImageIO.write(img, "PNG", response.getOutputStream());
	}
	
	// 登入頁面
		@GetMapping("/login")
		public String loginpageString(HttpSession session) {
			return "login";
		}

	// 登入
	@PostMapping("/login")
	public String login(@RequestParam("username") String username,
						@RequestParam("password") String password,
						@RequestParam("code") String code,
						HttpSession session, Model model) throws Exception {
		
		// 比對驗證碼
//		if(!code.equals(session.getAttribute("code")+"")) {
//			session.invalidate(); // session 過期失效
//			model.addAttribute("loginMessage", "驗證碼錯誤");
//			return "login";
//		}
		// 根據 username 查找 user 物件
		Optional<User> userOpt = dao.findUserByUsername(username);
		if (userOpt.isPresent()) {
			User user = userOpt.get();
			// 將 password 進行 AES 加密 -------------------------------------------------------------------
			final String KEY = KeyUtil.getSecretKey();
			SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
			byte[] encryptedPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, password);
			String encryptedPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedPasswordECB);
			//-------------------------------------------------------------------------------------------
			if (user.getUserPassword().equals(encryptedPasswordECBBase64)) { // 比對加密過後的 password 是否相同
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
	public String register(@ModelAttribute User user, Model model) throws Exception {
		Optional<User> userOpt = dao.findUserByUsername(user.getUserName());
		if (userOpt.isPresent()) {
			model.addAttribute("registerMessage", "使用者名稱重複");
			System.out.println("使用者名稱重複");
			return "register";
		} 
		// 將 password 進行 AES 加密 -------------------------------------------------------------------
		final String KEY = KeyUtil.getSecretKey();
		SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
		byte[] encryptedPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, user.getUserPassword());
		String encryptedPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedPasswordECB);
		//-------------------------------------------------------------------------------------------
		
			user.setUserPassword(encryptedPasswordECBBase64);

			dao.addUser(user);
			return "login";
		
	}
	
	// 使用者 Profile
		@GetMapping("/profile")
		public String profile() {
			return "profile";
		}
	
	// 變更密碼
		@PostMapping("/change_password")
		public String changePassword(@RequestParam("oldPassword") String oldPassword, 
									 @RequestParam("newPassword") List<String> newPasswords,
									 HttpSession session,
									 Model model) throws Exception {
			
			User user = (User)session.getAttribute("user");
			

			// 將 password 進行 AES 加密 -------------------------------------------------------------------
			final String KEY = KeyUtil.getSecretKey();
			SecretKeySpec aesKeySpec = new SecretKeySpec(KEY.getBytes(), "AES");
			byte[] encryptedOldPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, oldPassword);
			String encryptedOldPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedOldPasswordECB);
			//-------------------------------------------------------------------------------------------
						
			
			if(!user.getUserPassword().equals(encryptedOldPasswordECBBase64)) {
				model.addAttribute("errorMessage", "原密碼錯誤");
				return "profile";
			}
			if(!newPasswords.get(0).equals(newPasswords.get(1))) {
				model.addAttribute("errorMessage", "二次新密碼不一致");
				return "profile";
			}
			// 將新密碼加密
			byte[] encryptedNewPasswordECB = KeyUtil.encryptWithAESKey(aesKeySpec, newPasswords.get(0));
			String encryptedNewPasswordECBBase64 = Base64.getEncoder().encodeToString(encryptedNewPasswordECB);
			// 進行密碼變更
			dao.updateUserPassword(user.getUserId(),encryptedNewPasswordECBBase64);
			
			return "redirect:/mvc/highrail/logout";
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
		
		List<TrainTime> trainTimes = TimeTableAPI.getTrainTimes(fromStation, toStation, departureDate);
		model.addAttribute("trainTimes", trainTimes);
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
			//@RequestParam("quantity") Integer quantity, 
			Model model) throws Exception {

		if (fromStation.equals(toStation)) {
			model.addAttribute("bookingMessage", "起點終點重複");
			return "booking";
		} 	
		

		List<TrainTime> trainTimes = TimeTableAPI.getTrainTimes(fromStation, toStation, departureDate);
		
		model.addAttribute("trainTimes", trainTimes);		
		
		return "choosing";
	

	}

	// 訂票結果
	@PostMapping("/booking/choosing/result")
	@ResponseBody
//	@Transactional(propagation = Propagation.REQUIRED)
	public String result(@RequestBody TrainTime trainTime,
						Model model, HttpSession session) {
		
		User user = (User)session.getAttribute("user");

        String tranNo = trainTime.getTranNo();
        String startingStationName = trainTime.getStartingStationName();
        String endingStationName = trainTime.getEndingStationName();
        String departureDate = trainTime.getDepartureDate();
        String departureTime = trainTime.getDepartureTime();
        String arrivalTime = trainTime.getArrivalTime();
        String price = trainTime.getPrice();
        
        System.out.println(tranNo);
		
     	Tran tran = new Tran();
     	tran.setTranNo(Integer.parseInt(tranNo));
     	tran.setDepartureStation(startingStationName);
     	tran.setArrivalStation(endingStationName);
     	tran.setDate(departureDate);
     	tran.setDepartureTime(departureTime);
     	tran.setArrivalTime(arrivalTime);
     	
		dao.addTran(tran);
		
		
		Ticket ticket = new Ticket();
		
		ticket.setUserId(user.getUserId());
		
		ticket.setTranId(tran.getTranId());
		
		System.out.println(tran.getTranId());
		
		int i;
		i = (int) (Math.random()*10)+1;
		Integer iInteger = Integer.valueOf(i);
		ticket.setCarNo(iInteger);
		
		Random r = new Random();
		char c = (char)(r.nextInt(5) + 'a');
		String s = Character.toString(c);
		ticket.setSiteNo(s);
		
		ticket.setPrice(Integer.parseInt(price));
		
		dao.addTicket(ticket);
		
		return "success:" + tran.getTranId();
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
