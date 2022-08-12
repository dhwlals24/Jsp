package co.yedam.web;

import java.util.List;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import co.yedam.prj.notice.serviceImpl.NoticeServiceImpl;
import co.yedam.prj.notice.vo.NoticeVO;

public class JacksonTest {
	
	//@Test
	public void toJson() throws JsonProcessingException {
		NoticeServiceImpl service = new NoticeServiceImpl();
		List<NoticeVO> list = service.noticeSelectList();
		
		ObjectMapper objectMapper = new ObjectMapper();
		String result = objectMapper.writeValueAsString(list);
		
		System.out.println(result);
	}

	@Test
	public void toObject() throws JsonMappingException, JsonProcessingException {
		String str = "{\"noticeId\":1,\"noticeWriter\":\"호빵\",\"noticeTitle\":\"2022 휴가계획표\",\"noticeSubject\":null,\"noticeDate\":1656601200000,\"noticeHit\":0,\"noticeAttech\":null,\"noticeAttechDir\":null}"; 
		ObjectMapper objectMapper = new ObjectMapper();
		NoticeVO vo = objectMapper.readValue(str, NoticeVO.class);
		System.out.println(vo.getNoticeTitle());
	}

}
