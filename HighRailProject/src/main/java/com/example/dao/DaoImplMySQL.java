package com.example.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.entity.Ticket;
import com.example.entity.Tran;
import com.example.entity.User;

@Repository
public class DaoImplMySQL implements Dao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public void addUser(User user) {
		String sql = "insert into user(user_Name, user_Password, user_Phone, user_Email) values(?, ?, ?, ?)";
		jdbcTemplate.update(sql, user.getUserName(), user.getUserPassword(), user.getUserPhone(), user.getUserEmail());
	}

	@Override
	public Optional<User> findUserByUsername(String username) {
		String sql = "select user_Id, user_name, user_Password, user_Phone, user_Email from user where user_name = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), username);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public Optional<User> findUserByUserId(Integer userid) {
		String sql = "select user_Id, user_name, user_Password, user_Phone, user_Email from user where user_Id = ?";
		try {
			User user = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(User.class), userid);
			return Optional.ofNullable(user);
		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public void addTicket(Ticket ticket) {
		String sql = "insert into ticket(user_Id, tran_Id, car_No, site_No, price, state) values(?, ?, ?, ?, ?, ?)";
		jdbcTemplate.update(sql, ticket.getUserId(), ticket.getTranId(), ticket.getCarNo(), ticket.getSiteNo(),
				ticket.getPrice(), ticket.getState());

	}

	@Override
	public boolean removeTicket(Integer ticketId) {
		String sql = "delete from ticket where ticket_Id = ?";
		return jdbcTemplate.update(sql, ticketId) == 1;
	}

	@Override
	public Optional<Ticket> findTicketByTicketId(Integer ticketid) {
		String sql = "select user_id, tran_id, car_no, site_no, price, state from ticket where ticket_id = ?";
		try {
			Ticket ticket = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Ticket.class), ticketid);
			if (ticket != null) {
				enrichTicketWithDetails(ticket);
			}
			return Optional.ofNullable(ticket);

		} catch (EmptyResultDataAccessException e) {
			return Optional.empty();
		}
	}

	@Override
	public List<Ticket> findAllTicketsByUserId(Integer userId) {
		String sql = "select ticket_Id, tran_Id, car_No, site_No, price, state from ticket where user_Id = ? ";
		List<Ticket> tickets = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Ticket.class), userId);
		tickets.forEach(this::enrichTicketWithDetails);
		return tickets;
	}

	@Override
	public void addTran(Tran tran) {
		String sql = "insert into tran(tran_No, date, departureTime, arrivalTime) values(?, ?, ?, ?)";
		jdbcTemplate.update(sql, tran.getTranNo(), tran.getDate(), tran.getDepartureTime(), tran.getArrivalTime());
	}

	// 為 Ticket 注入 tran
	private void enrichTicketWithDetails(Ticket ticket) {

		// 注入 user
		findUserByUserId(ticket.getUserId()).ifPresent(ticket::setUser);

		// 查詢 tran 並注入
		String sql = "select tran_Id, tran_No, date, departureTime, arrivalTime from tran where tran_Id = ?";
		Tran tran = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Tran.class), ticket.getTranId());
		ticket.setTran(tran);
	}

}
