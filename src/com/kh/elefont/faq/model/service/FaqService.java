package com.kh.elefont.faq.model.service;

import static com.kh.elefont.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.elefont.faq.model.dao.FaqDao;
import com.kh.elefont.faq.model.vo.Faq;


public class FaqService {
	
	
	FaqDao faqDao = new FaqDao();

	public List<Faq> selectAllFaq() {

		Connection conn = getConnection();
		List<Faq> faqList = faqDao.selectAllFaq(conn);
		close(conn);
		
		return faqList;
	}
}
