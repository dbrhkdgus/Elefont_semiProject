package com.kh.elefont.order.model.service;
import static com.kh.elefont.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.elefont.font.model.vo.Font;
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

	public int insertOrderFont(Order order) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = orderDao.insertOrderFont(conn, order);
//			System.out.println("result@dao = "+result);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}


	public int insertOrders(Order order) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = orderDao.insertOrders(conn, order);
//			System.out.println("result@dao = "+result);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}

	public List<Order> selectAllOrderListByMemberNo(String memberNo) {
		Connection conn = getConnection();
		
		List<Order> orderList = orderDao.selectAllOrderListByMemberNo(conn, memberNo);
		
		close(conn);
		return orderList;
	}

	public List<Order> selectAllOrderListByOrderNo(String orderNo) {
		Connection conn = getConnection();
		List<Order> orderList = orderDao.selectAllOrderListByOrderNo(conn, orderNo);
		
		close(conn);
		return orderList;
	}
		
}


