package com.kh.elefont.order.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.elefont.order.model.vo.Order;
import com.kh.elefont.order.model.vo.OrderExt;

public class OrderDao {
	
	private Properties prop = new Properties();
	
	public OrderDao() {
		  
        String filepath = OrderDao.class.getResource("/order/order-query.properties").getPath();

        try {
            prop.load(new FileReader(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public Order selectOneOrderByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectOneOrderByMemberNo");
		Order order = null;
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				order.setMemberNo(rset.getString("member_no"));
				order.setMemberOrderDate(rset.getDate("member_order_date"));
				order.setOrderNo(rset.getString("order_no"));
				order.setFontNo(rset.getString("font_no"));
				order.setFontName(rset.getString("font_name"));
				order.setFontPrice(rset.getInt("font_price"));
				order.setFontDiscoutRate(rset.getDouble("font_discount_rate"));
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return order;
		
	}

	public int insertOrderFont(Connection conn, Order order) {
		PreparedStatement pstmt = null;
		int result  = 0;
		String sql = prop.getProperty("insertOrderFont");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,order.getOrderNo());
			pstmt.setString(2,order.getMemberNo());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public int insertOrders(Connection conn, Order order) {
		PreparedStatement pstmt = null;
		int result  = 0;
		String sql = prop.getProperty("insertOrders");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,order.getOrderNo());
			pstmt.setString(2,order.getFontNo());
			
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		return result;
	}

	public List<Order> selectAllOrderListByMemberNo(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Order> orderList = new ArrayList<>();
		String sql = prop.getProperty("selectAllOrderListByMemberNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Order order = new Order();
				order.setMemberNo(rset.getString("member_no"));
				order.setMemberOrderDate(rset.getDate("member_order_date"));
				order.setOrderNo(rset.getString("order_no"));
				order.setFontNo(rset.getString("font_no"));
				order.setFontName(rset.getString("font_name"));
				order.setFontPrice(rset.getInt("font_price"));
				order.setFontDiscoutRate(rset.getDouble("font_discount_rate"));
				
				orderList.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		return orderList;
	}

	public List<Order> selectAllOrderListByOrderNo(Connection conn, String orderNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Order> orderList = new ArrayList<>();
		String sql = prop.getProperty("selectAllOrderListByOrderNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, orderNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				OrderExt oe = new OrderExt();				
				oe.setMemberNo(rset.getString("member_no"));				
				oe.setMemberOrderDate(rset.getDate("member_order_date"));				
				oe.setOrderNo(rset.getString("order_no"));				
				oe.setFontNo(rset.getString("font_no"));				
				oe.setFontName(rset.getString("font_name"));				
				oe.setFontPrice(rset.getInt("font_price"));				
				oe.setFontDiscoutRate(rset.getDouble("font_discount_rate"));				
				oe.setMemberId(rset.getString("member_id"));				
				oe.setMemberEmail(rset.getString("member_email"));				
				oe.setFontUrl(rset.getString("font_url"));				
				orderList.add(oe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return orderList;
	}

	public List<Order> selectAllOrderList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Order> orderList = new ArrayList<>();
		String sql = prop.getProperty("selectAllOrderList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				OrderExt oe = new OrderExt();
				
				oe.setMemberNo(rset.getString("member_no"));
				oe.setMemberOrderDate(rset.getDate("member_order_date"));
				oe.setOrderNo(rset.getString("order_no"));
				oe.setFontNo(rset.getString("font_no"));
				oe.setFontName(rset.getString("font_name"));
				oe.setFontPrice(rset.getInt("font_price"));
				oe.setFontDiscoutRate(rset.getDouble("font_discount_rate"));
				oe.setMemberId(rset.getString("member_id"));
				oe.setMemberEmail(rset.getString("member_email"));
				oe.setFontUrl(rset.getString("font_url"));
				
				orderList.add(oe);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return orderList;
	}
}
