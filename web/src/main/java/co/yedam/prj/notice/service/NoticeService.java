package co.yedam.prj.notice.service;

import java.util.List;

import co.yedam.prj.notice.vo.NoticeVO;

public interface NoticeService {
	//crud
	List<NoticeVO> noticeSelectList(); //전체조회
	NoticeVO noticeSelect(NoticeVO vo);	//한글조회
	int noticeInsert(NoticeVO vo); //글입력
	int noticeDelete(NoticeVO vo); //글삭제
	int noticeUpdate(NoticeVO vo); //글수정
	
	List<NoticeVO> noticeSearchList(String key,String val); //글 목록에서 검색
	
	
}
