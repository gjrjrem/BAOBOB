package spring.mvc.baobob.member_mypage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.baobob.member_mypage.service.Member_mypageService;

@Controller
public class Member_mypageController {
	
	@Autowired
	Member_mypageService service;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//ȸ�� ����������
	@RequestMapping("memMyPageMain")
	public String memberMypage(Model model) {
		log.debug("====== Member_mypageController/memMypage() ======");
		
		return "guest/member_myPage/member_myPage_main";
	}
	
	//1:1���� ����Ʈ
	@RequestMapping("memQuestion")
	public String memQuestion(HttpServletRequest req, Model model) {
		log.debug("====== Member_mypageController/memQuestion() ======");
		
		//�ӽ� ����
		req.getSession().setAttribute("memId", "member_id 01");
		
		service.memQuestionList(req, model);
		
		return "guest/member_myPage/member_myPage_memQuestion";
	}
	
	//1:1���� ��
	@RequestMapping("memQuestionContentForm")
	public String memQuestionContentForm(HttpServletRequest req, Model model) {
		
		service.memQuestionContentForm(req, model);
		
		return "guest/member_myPage/member_myPage_memQContentForm";
	}

	//1:1���� ���� �� ������
	@RequestMapping("memQModifyForm")
	public String memQModifyForm(HttpServletRequest req, Model model) {
		
		//contentForm���� �ۼ�����ư�� �������� �ѱ� ������ �޴´�.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		//�޾ƿ°����� �����ؼ� modifyForm.jsp���� �޾ƶ�
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/member_myPage_memQModifyForm";
	}
	
	//1:1���� ���� �� ������
	@RequestMapping("memQModifyView")
	public String boardModifyView(HttpServletRequest req, Model model) {
		
		service.memQModifyView(req, model);
		
		return "guest/member_myPage/member_myPage_memQModifyView";
	}
	
	//1:1���� ���� ó��
	@RequestMapping("memQModifyPro")
	public String memQModifyPro(HttpServletRequest req, Model model) {
		
		service.memQModifyPro(req, model);
		
		return "guest/member_myPage/member_myPage_memQModifyPro";
	}
	
	//�����ϱ� �ۼ��� ������
	@RequestMapping("memQWriteForm")
	public String memQWriteForm(HttpServletRequest req, Model model) {
		
		//����۾���(�亯���� �ƴѰ��)
		int num = 0;
		int ref = 0; //�׷�ȭ ���̵�
		int ref_step = 0; //�亯�� ����(��)
		int ref_level = 0; //�亯�� ����(�鿩����) 
		
		//��۾���
		//contentForm���� ��۾��� ��ư�� �������� �ѱ� ������ �޴´�.
		if(req.getParameter("num") != null) {
			num = Integer.parseInt(req.getParameter("num"));
			ref = Integer.parseInt(req.getParameter("ref"));
			ref_step = Integer.parseInt(req.getParameter("ref_step"));
			ref_level = Integer.parseInt(req.getParameter("ref_level"));
		}
		
		//�޾ƿ°����� �����ؼ� writeForm.jsp���� �޾ƶ�
		req.setAttribute("num", num);
		req.setAttribute("ref", ref);
		req.setAttribute("ref_step", ref_step);
		req.setAttribute("ref_level", ref_level);
		
		return "guest/member_myPage/member_myPage_memQWriteForm";
	}
	
	//�����ϱ� ó��������	
	@RequestMapping("memQWritePro")
	public String memQWritePro(HttpServletRequest req, Model model) {
		
		service.memQWritePro(req, model);
		
		return "guest/member_myPage/member_myPage_memQWritePro";
	}
	
	//�����ϱ� ������ ������	
	@RequestMapping("memQDelForm")
	public String memQDelForm(HttpServletRequest req, Model model) {
		
		//contentForm.jsp���� ����button�� �������� get������� �ѱ� ���� �����´�.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/member_myPage_memQDelForm";
	}
	
	//�����ϱ� ���� ó��������
	@RequestMapping("memQDelPro")
	public String memQDelPro(HttpServletRequest req, Model model) {
		
		service.memQDelPro(req, model);
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/member_myPage_memQDelPro";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
