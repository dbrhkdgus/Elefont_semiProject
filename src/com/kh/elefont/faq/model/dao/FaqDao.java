package com.kh.elefont.faq.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.elefont.faq.model.vo.Faq;



public class FaqDao {

	private Properties prop = new Properties();

	public List<Faq> selectAllFaq(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Faq> list = new ArrayList<>();
		String sql = prop.getProperty("selectAllFaq");
		
		try {
			pstmt = conn.prepareStatement(sql);

			rset = pstmt.executeQuery(); 

 			while(rset.next()) {
 			
				Faq faq = new Faq();
				
				
				faq.setFaqNo(rset.getInt("faq_no"));
				faq.setFaqTitle(rset.getString("faq_title"));
				faq.setFaqContent(rset.getString("faq_content"));
				
				System.out.println("faq를 잘 받아왔나용옹? dao입니다." + faq);
				list.add(faq);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}


	


}
