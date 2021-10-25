package com.kh.elefont.community.model.service;

import static com.kh.elefont.common.JdbcTemplate.close;
import static com.kh.elefont.common.JdbcTemplate.getConnection;
import static com.kh.elefont.common.JdbcTemplate.commit;
import static com.kh.elefont.common.JdbcTemplate.rollback;

import java.sql.Connection;
import java.util.List;

import com.kh.elefont.community.model.dao.CommunityDao;


public class CommunityService {
	CommunityDao communityDao = new CommunityDao();
	public int enrollBoard() {
		Connection conn = getConnection();
		int result = communityDao.enrollBoard(conn, );
		close(conn);

		return 0;
	}

}
