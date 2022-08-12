package co.yedam.prj.reply.vo;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReplyVO {
	private int no;		
	private String writer; 		
	private Date wdate; 		
	private String content;		
	private int noticeId; 	
	
}
