package co.yedam.prj.web;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import co.yedam.prj.comm.Command;
import co.yedam.prj.notice.command.NoticeForm;
import co.yedam.prj.notice.command.NoticeInsert;
import co.yedam.prj.notice.command.NoticeList;


@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private HashMap<String, Command> map = new HashMap<>();  

    public FrontController() {
        super();
    }

	public void init(ServletConfig config) throws ServletException {
		// 요청과 수행할 command연결
		map.put("/noticeList.do", new NoticeList());	//게시글 목록
		map.put("/noticeForm.do", new NoticeForm()); //게시글 입력폼 호출
		map.put("/noticeInsert.do", new NoticeInsert()); //게시글 등록
		//map.put("/ajaxNoticeSearch.do", new AjaxNoticeSearch());//게시글 검색
		//map.put("/noticeView.do", new NoticeView()); //글 눌렀을때 한개만 보기
	}

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청분석하고 실행하고 결과를 돌려주는 곳
		request.setCharacterEncoding("utf-8");
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		String page = uri.substring(contextPath.length());
		
		Command command = map.get(page);
		String viewPage = command.exec(request, response);
		
		if(!viewPage.endsWith(".do")) {
			if(viewPage.startsWith("ajax:")) {
				response.setContentType("text/html; charset=UTF-8");
				response.getWriter().append(viewPage.substring(5));
				return;
			}
			
			//viewPage = viewPage + ".tiles";
			viewPage = "WEB-INF/views/" + viewPage + ".jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);			
		} else {
			response.sendRedirect(viewPage);
		}
	}

}
