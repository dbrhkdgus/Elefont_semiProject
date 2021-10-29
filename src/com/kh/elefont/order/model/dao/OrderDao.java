package com.kh.elefont.order.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


import com.kh.elefont.order.model.vo.Order;

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

}
