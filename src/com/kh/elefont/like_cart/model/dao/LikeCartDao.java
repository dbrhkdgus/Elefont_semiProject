package com.kh.elefont.like_cart.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import static com.kh.elefont.common.JdbcTemplate.*;

public class LikeCartDao {
	
	private Properties prop = new Properties();
	public LikeCartDao() {
		  
        String filepath = LikeCartDao.class.getResource("/member/member-query.properties").getPath();

        try {
            prop.load(new FileReader(filepath));
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
	
	public int insertCart(Connection conn, String fontNo, String memberNo) {
		// TODO Auto-generated method stub
		return 0;
	}

}
