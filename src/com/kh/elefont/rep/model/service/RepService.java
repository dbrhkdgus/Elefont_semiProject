package com.kh.elefont.rep.model.service;
import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;

import com.kh.elefont.rep.model.dao.RepDao;
import com.kh.elefont.rep.model.vo.Rep;

public class RepService {
	
	RepDao repDao = new RepDao();
	
	public int insertShopRep(Rep rep) {
		Connection conn = getConnection();
		int result = 0;
		
		try {
			result = repDao.insertShopRep(conn, rep);			
		
			commit(conn);
		}catch(Exception e) {
			e.printStackTrace();
			rollback(conn);
		}
		close(conn);
		return result;
		
	}

}
