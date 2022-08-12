package co.yedam.prj.reply.service;

import java.util.List;

import co.yedam.prj.reply.vo.ReplyVO;

public interface ReplyService {
	////crud
	
	//댓글 전체조회
	List<ReplyVO> replySelectList();
	//댓글 한개조회
	ReplyVO replySelect(ReplyVO vo);
	//댓글 입력
	int replyInsert(ReplyVO vo);
	//댓글 삭제
	int replyDelete(ReplyVO vo); 
	//댓글 수정
	int replyUpdate(ReplyVO vo);

}
