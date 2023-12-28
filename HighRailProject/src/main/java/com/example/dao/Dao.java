package com.example.dao;
import java.util.List;
import java.util.Optional;

import com.example.entity.Ticket;
import com.example.entity.Tran;
import com.example.entity.User;

public interface Dao {

    //  新增使用者
	void addUser(User user);

    //  根據使用者名稱查找使用者(登入用-單筆)
	Optional<User> findUserByUsername(String username);

    //  根據使用者ID查找使用者
    Optional<User> findUserByUserId(Integer userid);

    //  新增車票
    void addTicket(Ticket ticket);

    //  刪除車票
    boolean removeTicket(Integer ticketId);

    //  查詢車票
    Optional<Ticket> findTicketByTicketId(Integer ticketId);

    //  查詢所有車票
    List<Ticket> findAllTicketsByUserId(Integer userid);

    //  新增列車資訊
    void addTran(Tran tran);
} 