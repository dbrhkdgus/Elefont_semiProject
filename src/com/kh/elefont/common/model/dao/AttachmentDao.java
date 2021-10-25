package com.kh.elefont.common.model.dao;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

import com.kh.elefont.common.model.vo.Attachment;
import com.kh.elefont.community.model.dao.CommunityDao;

public class AttachmentDao {
	
	private Properties prop = new Properties();
	
	public AttachmentDao() {
		
		String filepath = CommunityDao.class.getResource("/attachment/attachment-query.properties").getPath();
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	public int insertAttachment(Connection conn, Attachment attach) {
		// TODO Auto-generated method stub
		return 0;
	}

}
