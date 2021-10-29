package com.kh.elefont.order.model.service;
import static com.kh.elefont.common.JdbcTemplate.*;

import java.sql.Connection;

import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.order.model.dao.OrderDao;
import com.kh.elefont.order.model.vo.Order;

public class OrderService {
	OrderDao orderDao = new OrderDao();
	
	public Order selectOneOrderByMemberNo(String memberNo) {
		System.out.println("서비스");
		Connection conn = getConnection();

		Order order = orderDao.selectOneOrderByMemberNo(conn, memberNo);
		close(conn);
		return order;
	}

}
