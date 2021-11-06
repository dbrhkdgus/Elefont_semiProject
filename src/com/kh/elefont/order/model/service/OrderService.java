package com.kh.elefont.order.model.service;
import static com.kh.elefont.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.elefont.font.model.vo.Font;
import com.kh.elefont.member.model.vo.Member;
import com.kh.elefont.order.model.dao.OrderDao;
import com.kh.elefont.order.model.vo.Order;

public class OrderService {
	OrderDao orderDao = new OrderDao();
	
	public Order selectOneOrderByMemberNo(String memberNo) {
		Connection conn = getConnection();

		Order order = orderDao.selectOneOrderByMemberNo(conn, memberNo);
		close(conn);
		return order;
	}

	public int insertOrderFont(Order order, double finalPrice) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = orderDao.insertOrderFont(conn, order,finalPrice);
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

	public List<Order> selectAllOrder() {
		Connection conn = getConnection();
		List<Order> orderList = orderDao.selectAllOrderList(conn);
		close(conn);
		return orderList;
	}

	public List<Order> selectSerchOrder(Map<String, Object> param) {
		Connection conn = getConnection();
		List<Order> orderList = orderDao.selectSerchOrder(conn,param);
		
		close(conn);
		return orderList;
	}

	public int insertOrderFont(Order order) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = orderDao.insertOrderFont(conn, order);
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
		}finally {
			close(conn);
		}
		
		return result;
	}
		
}


