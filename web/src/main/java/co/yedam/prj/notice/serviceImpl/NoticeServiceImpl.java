package co.yedam.prj.notice.serviceImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.prj.comm.DataSource;
import co.yedam.prj.notice.service.NoticeService;
import co.yedam.prj.notice.vo.NoticeVO;

public class NoticeServiceImpl implements NoticeService {
	private DataSource dao = DataSource.getInstance();
	private Connection conn;
	private PreparedStatement psmt;
	private ResultSet rs;
	
	
	@Override
	public List<NoticeVO> noticeSelectList() {
		//전체 목록
		List<NoticeVO> list = new ArrayList<>();
		NoticeVO vo;
		String sql = "SELECT * FROM NOTICE ORDER BY NOTICE_ID DESC";
		
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			rs = psmt.executeQuery();
			while(rs.next()) {
				vo = new NoticeVO();
				vo.setNoticeId(rs.getInt("notice_id"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeDate(rs.getDate("notice_date"));
				vo.setNoticeAttech(rs.getString("notice_attech"));
				vo.setNoticeHit(rs.getInt("notice_hit"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		
		return list;
	}

	@Override
	public NoticeVO noticeSelect(NoticeVO vo) {
		// 글 상세보기
		String sql = "SELECT * FROM NOTICE WHERE NOTICE_ID = ?";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, vo.getNoticeId());
			rs = psmt.executeQuery();
				if(rs.next()) {
					vo.setNoticeId(rs.getInt("notice_id"));
					vo.setNoticeWriter(rs.getString("notice_writer"));
					vo.setNoticeTitle(rs.getString("notice_title"));
					vo.setNoticeSubject(rs.getString("notice_subject"));
					vo.setNoticeAttech(rs.getString("notice_attech"));
					vo.setNoticeHit(rs.getInt("notice_hit"));
				}
		}catch(SQLException e){
			e.printStackTrace();
		}finally {
			close();
		}
		
		return vo;
	}

	@Override
	public int noticeInsert(NoticeVO vo) {
		// 글 등록
		int n =0;
		String sql = "INSERT INTO NOTICE VALUES(notice_seq.nextval,?,?,?,?,0,?,?)";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			//id 컬럼 부분이 생략됨 위에서 notice_seq.nextval로 해줘서
			psmt.setString(1, vo.getNoticeWriter());
			psmt.setString(2, vo.getNoticeTitle());
			psmt.setString(3, vo.getNoticeSubject());
			psmt.setDate(4, vo.getNoticeDate());
			psmt.setString(5, vo.getNoticeAttech());
			psmt.setString(6, vo.getNoticeAttechDir());
			n = psmt.executeUpdate();
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return n;
	}

	@Override
	public int noticeDelete(NoticeVO vo) {
		// 글 삭제
		int n = 0;
		return n;
	}

	@Override
	public int noticeUpdate(NoticeVO vo) {
		// 글 수정
		int n = 0;
		return n;
	}

	@Override
	public List<NoticeVO> noticeSearchList(String key, String val) {
		// 목록 검색
		List<NoticeVO> list = new ArrayList<>();
		NoticeVO vo;
		String sql = "SELECT * FROM NOTICE WHERE ? LIKE %?%";
		try {
			conn = dao.getConnection();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, key);
			psmt.setString(2, val);
			rs = psmt.executeQuery(); 
			while(rs.next()) {
				vo = new NoticeVO();	//검색해서 존재하면 vo인스턴스를 초기화
				vo.setNoticeId(rs.getInt("notice_id"));
				vo.setNoticeWriter(rs.getString("notice_writer"));
				vo.setNoticeTitle(rs.getString("notice_title"));
				vo.setNoticeSubject(rs.getString("notice_subject"));
				vo.setNoticeAttech(rs.getString("notice_attech"));
				vo.setNoticeHit(rs.getInt("notice_hit"));
				list.add(vo);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			close();
		}
		return list;
	}
	
	private void close() {
		try {
			if(rs != null) rs.close();
			if(psmt !=null)psmt.close();
			if(conn != null) conn.close();
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}

}
