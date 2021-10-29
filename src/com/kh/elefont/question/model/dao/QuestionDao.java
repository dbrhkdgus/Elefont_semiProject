package com.kh.elefont.question.model.dao;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.elefont.question.model.vo.Question;
import static com.kh.elefont.common.JdbcTemplate.*;

public class QuestionDao {
	private Properties prop = new Properties();
	
	public QuestionDao(){
		String filepath = QuestionDao.class.getResource("/question/question-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public List<Question> selectAllQuestion(Connection conn, String memberNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Question> questionList = new ArrayList<>();
		String sql = prop.getProperty("selectAllQuestion");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, memberNo);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Question question = new Question();
				
				question.setqNo(rset.getInt("q_no"));
				question.setqQuestioner(rset.getString("q_questioner"));
				question.setqContent(rset.getString("q_content"));
				question.setqWriter(rset.getString("q_writer"));
				question.setqDate(rset.getTimestamp("q_date"));
				question.setqIsAnswered(rset.getString("q_is_answered"));
				
				questionList.add(question);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return questionList;
	}

}