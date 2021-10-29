package com.kh.elefont.question.model.service;

import static com.kh.elefont.common.JdbcTemplate.*;

import java.sql.Connection;
import java.util.List;

import com.kh.elefont.question.model.dao.QuestionDao;
import com.kh.elefont.question.model.vo.Question;

public class QuestionService {
	private QuestionDao questionDao = new QuestionDao();

	public List<Question> selectAllQuestion(String memberNo) {
		Connection conn = getConnection();
		List<Question> questionList = questionDao.selectAllQuestion(conn, memberNo);
		
		close(conn);
		
		return questionList;
	}

}
