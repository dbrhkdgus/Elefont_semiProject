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
		int cnt =  questionDao.selectNotAnseredQuestionCnt(conn);
		close(conn);
		return cnt;
	}
	
	public Question selectLastQuestion() {
		Connection conn = getConnection();
		Question question = questionDao.selectLastQuestion(conn);
		close(conn);
		return question;

	}

	public int selectAnsweredQuestionCnt() {
		Connection conn = getConnection();
		int cnt =  questionDao.selectAnsweredQuestionCnt(conn);
		close(conn);
		return cnt;
	}

	public Question selectAllQuestionGroupByForAdmin(String questioner) {
		Connection conn = getConnection();
		Question question = questionDao.selectAllQuestionGroupByForAdmin(conn,questioner);
		
		close(conn);
		
		return question;
	}

	public List<String> selectAllQuestioner() {
		Connection conn = getConnection();
		List<String> questionerList = questionDao.selectAllQuestioner(conn);
		
		close(conn);
		
		return questionerList;
	}



}
