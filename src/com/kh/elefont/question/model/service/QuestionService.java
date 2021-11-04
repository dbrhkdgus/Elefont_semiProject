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

	public int insertQuestion(String qContent, String qWriter) {
		Connection conn = getConnection();
		int result = 0;
		try {
			
			result = questionDao.insertQuestion(conn, qContent, qWriter);
			
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return result;
	}

	public List<Question> selectAllQuestionForAdmin() {
		Connection conn = getConnection();
		List<Question> questionList = questionDao.selectAllQuestionForAdmin(conn);
		
		close(conn);
		
		return questionList;
	}

	public int selectNotAnseredQuestionCnt() {
		Connection conn = getConnection();
		int cnt = 0;
		try {
			
			cnt = questionDao.selectNotAnseredQuestionCnt(conn);
			
			commit(conn);
		}catch(Exception e) {
			rollback(conn);
			throw e;
		}finally {
			close(conn);
		}
		
		return cnt;
	}

}
