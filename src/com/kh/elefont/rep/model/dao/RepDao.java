package com.kh.elefont.rep.model.dao;

import static com.kh.elefont.common.JdbcTemplate.close;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import com.kh.elefont.community.model.vo.Community;
import com.kh.elefont.community.model.vo.DeletedCommunity;
import com.kh.elefont.font.model.dao.FontDao;
import com.kh.elefont.rep.model.vo.DeletedRep;
import com.kh.elefont.rep.model.vo.Rep;

public class RepDao {
	
	private Properties prop = new Properties();

	public RepDao() {
		String filepath = RepDao.class.getResource("/rep/rep-query.properties").getPath();
		
		try {
			prop.load(new FileReader(filepath));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public int insertShopRep(Connection conn, Rep rep) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertShopRep");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rep.getRepWriter());
			pstmt.setString(2, rep.getRepContent());
			pstmt.setString(3, rep.getFontNo());
			pstmt.setInt(4, rep.getRepLevel());
			pstmt.setObject(5, rep.getRepRef() == 0 ? null : rep.getRepRef());
			pstmt.setString(6, rep.getMemberNo());
			
			
		
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Rep> selectFontRepListByFontNo(Connection conn, String fontNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Rep> repList = new ArrayList<>();
		
		String sql = prop.getProperty("selectFontRepListByFontNo");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,fontNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Rep rep = new Rep();
				rep.setRepNo(rset.getInt("rep_no"));
				rep.setRepWriter(rset.getString("rep_writer"));
				rep.setRepContent(rset.getString("rep_content"));
				rep.setRepRegDate(rset.getDate("rep_reg_date"));
				rep.setComnNo(rset.getString("comm_no"));
				rep.setRepLevel(rset.getInt("rep_level"));
				rep.setRepRef(rset.getInt("rep_ref"));
				rep.setMemberNo(rset.getString("member_no"));
		
				
				repList.add(rep);
				
			  }
      } catch (SQLException e) {
			e.printStackTrace();
		  } finally {
			close(rset);
			close(pstmt);
		}	
		return repList;
	}

	public int deleteRep(Connection conn, String repNo) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("deleteRep");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, repNo);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int updateRep(Connection conn, String repNo, String updateRepContent) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("updateRep");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, updateRepContent);
			pstmt.setString(2, repNo);

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public int insertCommunityRep(Connection conn, Rep rep) {
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = prop.getProperty("insertCommunityRep");
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, rep.getRepWriter());
			pstmt.setString(2, rep.getRepContent());
			pstmt.setString(3, rep.getComnNo());
			pstmt.setInt(4, rep.getRepLevel());
			pstmt.setObject(5, rep.getRepRef() == 0 ? null : rep.getRepRef() );
			pstmt.setString(6, rep.getMemberNo());

			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}

	public List<Rep> selectAllCommunityRepListByCommNo(Connection conn, String commNo) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<Rep> repList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllCommunityRepListByCommNo");
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,commNo);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				Rep rep = new Rep();
				rep.setRepNo(rset.getInt("rep_no"));
				rep.setRepWriter(rset.getString("rep_writer"));
				rep.setRepContent(rset.getString("rep_content"));
				rep.setRepRegDate(rset.getDate("rep_reg_date"));
				rep.setComnNo(rset.getString("comm_no"));
				rep.setRepLevel(rset.getInt("rep_level"));
				rep.setRepRef(rset.getInt("rep_ref"));
				rep.setMemberNo(rset.getString("member_no"));
		
				
				repList.add(rep);
				
			  }
      } catch (SQLException e) {
			e.printStackTrace();
		  } finally {
			close(rset);
			close(pstmt);
		}	
		return repList;
	}

	public List<DeletedRep> selectAllDeletedRepList(Connection conn) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		List<DeletedRep> deletedRepList = new ArrayList<>();
		
		String sql = prop.getProperty("selectAllDeletedRepList");
		
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				DeletedRep deletedRep = new DeletedRep();
				deletedRep.setComnNo(rset.getString("comm_no"));
				deletedRep.setMemberNo(rset.getString("member_no"));
				deletedRep.setRepWriter(rset.getString("rep_writer"));
				deletedRep.setRepContent(rset.getString("rep_content"));
				deletedRep.setRepRegDate(rset.getDate("rep_reg_date"));
				deletedRep.setRepDeleteDate(rset.getDate("rep_delete_date"));
				
				deletedRepList.add(deletedRep);
				
			}
				
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return deletedRepList;
	}

}
