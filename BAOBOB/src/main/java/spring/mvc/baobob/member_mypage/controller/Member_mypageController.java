package spring.mvc.baobob.member_mypage.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.member_mypage.service.Member_mypageService;

@Controller
public class Member_mypageController {
	
	@Autowired
	Member_mypageService service;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//ȸ�� ���������� ����
	@RequestMapping("memMyPageMain")
	public String memberMypage(HttpServletRequest req, Model model) {
		log.debug("====== Member_mypageController/memMypage() ======");
		
		service.memberCard(req, model);
		//MY���ų���
		service.moviePaidList(req,model);
		//MY1:1����
		service.memQuestionList(req, model);
		//MY�нǹ� ����
		service.memLostList(req, model);
		
		return "guest/member_myPage/member_myPage_main";
	}

/*----------------------------- �������� ----------------------------------------*/
	
	//�������� ��������
	@RequestMapping("memPForm")
	public String memPView(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		return "guest/member_myPage/memPersonal/member_myPage_memPForm";
	}
	
	//�������� �Է�������	
	@RequestMapping("memPModifyView")
	public String memPModifyView(HttpServletRequest req, Model model) {
		log.debug("====== Member_mypageController/memPModifyView() ======");
		service.memberCard(req, model);
		service.memPModifyView(req, model);
		
		return "guest/member_myPage/memPersonal/member_myPage_memPView";
	}
	
	//�������� ó��������
	@RequestMapping(value="memPPro", method=RequestMethod.POST)
	public String memPPro(MultipartHttpServletRequest req, Model model) {
		log.debug("====== Member_mypageController/memPPro() ======");
		service.memberCard(req, model);
		service.memPPro(req, model);
		
		return "guest/member_myPage/memPersonal/member_myPage_memPPro";
	}
	
	//ȸ��Ż�� ��������
	@RequestMapping("memPDelForm")
	public String memPDelForm(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		
		return "guest/member_myPage/memPersonal/member_myPage_memPDelForm";
	}
	
	//ȸ��Ż�� ó��������
	@RequestMapping("memPDelPro")
	public String memPDelPro(HttpServletRequest req, Model model) {
		
		service.memPDelPro(req, model);
		
		return "guest/member_myPage/memPersonal/member_myPage_memPDelPro";
	}
	
	
/*----------------------------- 1:1���� ----------------------------------------*/
	
	//1:1���� ����Ʈ
	@RequestMapping("memQuestion")
	public String memQuestion(HttpServletRequest req, Model model) {
		log.debug("====== Member_mypageController/memQuestion() ======");
		if(req.getSession().getAttribute("memId") != null) {
			service.memberCard(req, model);
			service.memQuestionList(req, model);
		}
		return "guest/member_myPage/memQuestion/member_myPage_memQuestion";
	}
	
	//1:1���� ��
	@RequestMapping("memQuestionContentForm")
	public String memQuestionContentForm(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQuestionContentForm(req, model);
		
		return "guest/member_myPage/memQuestion/member_myPage_memQContentForm";
	}

	//1:1���� ���� �� ������
	@RequestMapping("memQModifyForm")
	public String memQModifyForm(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		
		//contentForm���� �ۼ�����ư�� �������� �ѱ� ������ �޴´�.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		//�޾ƿ°����� �����ؼ� modifyForm.jsp���� �޾ƶ�
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/memQuestion/member_myPage_memQModifyForm";
	}
	
	//1:1���� ���� �� ������
	@RequestMapping("memQModifyView")
	public String boardModifyView(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQModifyView(req, model);
		
		return "guest/member_myPage/memQuestion/member_myPage_memQModifyView";
	}
	
	//1:1���� ���� ó��
	@RequestMapping("memQModifyPro")
	public String memQModifyPro(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQModifyPro(req, model);
		
		return "guest/member_myPage/memQuestion/member_myPage_memQModifyPro";
	}
	
	//1:1�����ϱ� �ۼ��� ������
	@RequestMapping("memQWriteForm")
	public String memQWriteForm(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		
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
		
		return "guest/member_myPage/memQuestion/member_myPage_memQWriteForm";
	}
	
	//1:1�����ϱ� ó��������	
	@RequestMapping("memQWritePro")
	public String memQWritePro(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQWritePro(req, model);
		
		return "guest/member_myPage/memQuestion/member_myPage_memQWritePro";
	}
	
	//1:1�����ϱ� ������ ������	
	@RequestMapping("memQDelForm")
	public String memQDelForm(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		
		//contentForm.jsp���� ����button�� �������� get������� �ѱ� ���� �����´�.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/memQuestion/member_myPage_memQDelForm";
	}
	
	//1:1�����ϱ� ���� ó��������
	@RequestMapping("memQDelPro")
	public String memQDelPro(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQDelPro(req, model);
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/memQuestion/member_myPage_memQDelPro";
	}
	
/*----------------------------- �нǹ� ���� ----------------------------------------*/	
	
	//�нǹ� ���� ����Ʈ
	@RequestMapping("memLost")
	public String memLost(HttpServletRequest req, Model model) {
		log.debug("====== Member_mypageController/memLost() ======");
		if(req.getSession().getAttribute("memId") != null) {
			service.memberCard(req, model);
			service.memLostList(req, model);
		}
		return "guest/member_myPage/memLost/member_myPage_memLost";
	}
	
	//�нǹ� ���� ��
	@RequestMapping("memLostContentForm")
	public String memLostContentForm(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQuestionContentForm(req, model);
		
		return "guest/member_myPage/memLost/member_myPage_memLContentForm";
	}
	
	//�нǹ� ���� ���� �� ������
	@RequestMapping("memLModifyForm")
	public String memLModifyForm(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		
		//contentForm���� �ۼ�����ư�� �������� �ѱ� ������ �޴´�.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		//�޾ƿ°����� �����ؼ� modifyForm.jsp���� �޾ƶ�
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/memLost/member_myPage_memLModifyForm";
	}
	
	//�нǹ� ���� ���� �� ������
	@RequestMapping("memLModifyView")
	public String memLModifyView(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQModifyView(req, model);
		
		return "guest/member_myPage/memLost/member_myPage_memLModifyView";
	}
	
	//�нǹ� ���� ���� ó��
	@RequestMapping("memLModifyPro")
	public String memLModifyPro(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQModifyPro(req, model);
		
		return "guest/member_myPage/memLost/member_myPage_memLModifyPro";
	}
	
	//�нǹ� �����ϱ� ������ ������	
	@RequestMapping("memLDelForm")
	public String memLDelForm(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		
		//contentForm.jsp���� ����button�� �������� get������� �ѱ� ���� �����´�.
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/memLost/member_myPage_memLDelForm";
	}

	//�нǹ� �����ϱ� ���� ó��������
	@RequestMapping("memLDelPro")
	public String memLDelPro(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.memQDelPro(req, model);
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		req.setAttribute("num", num);
		req.setAttribute("pageNum", pageNum);
		
		return "guest/member_myPage/memLost/member_myPage_memLDelPro";
	}
	
	//�нǹ� �����ϱ� �ۼ��� ������
	@RequestMapping("memLWriteForm")
	public String memLWriteForm(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		
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
		
		return "guest/member_myPage/memLost/member_myPage_memLWriteForm";
	}
	
	//�нǹ� �����ϱ� ó��������	
	@RequestMapping(value="memLWritePro", method=RequestMethod.POST)
	public String memLWritePro(MultipartHttpServletRequest req, Model model) {
		
		log.debug("====== Member_mypageController/memLWritePro() ======");
		
		service.memberCard(req, model);
		service.memLWritePro(req, model);
		
		return "guest/member_myPage/memLost/member_myPage_memLWritePro";
	}
	
/*----------------------------- ���� �α� ----------------------------------------*/	
	
	//����α�
	@RequestMapping("MovieLog")
	public String MovieLog(HttpServletRequest req, Model model) {
		service.memberCard(req, model);
		service.movieWishList(req, model);
		
		return "guest/member_myPage/memMovie/member_myPage_MovieLog";
	}
	
	//����α� ���ø���Ʈ
	@RequestMapping("wishList")
	public String wishList(HttpServletRequest req, Model model) {
		
		service.movieWishList(req, model);
		
		return "guest/member_myPage/memMovie/member_myPage_WishList";
	}
	
	//���ø���Ʈ �߰�
	@RequestMapping("addWishList")
	public String addWishList(HttpServletRequest req, Model model) {
		
		service.addWishList(req, model);
		
		return "guest/member_myPage/memMovie/member_myPage_addWishListPro";
	}

	
	//����α� ���ø���Ʈ ����
	@RequestMapping("delWishList")
	public String delWishList(HttpServletRequest req, Model model) {
		
		service.delMovieWishList(req, model);
		
		return "guest/member_myPage/memMovie/member_myPage_delWishListPro";
	}
		
	//����α� ������̾
	@RequestMapping("movieDiaryWriteForm")
	public String movieDiaryWriteForm(HttpServletRequest req, Model model) {
		
		if(req.getSession().getAttribute("memId") != null) {
			service.movieDiaryList(req,model);
		}
		
		//service.movieClear(req, model);
		
		return "guest/member_myPage/memMovie/member_myPage_movieDiaryWriteForm";
	}
	
	//����α� ������̾ ����¡
	@RequestMapping("movieDiaryPage")
	public String movieDiaryPage(HttpServletRequest req, Model model) {
		
		service.movieDiaryList(req,model);
		
		return "guest/member_myPage/memMovie/member_myPage_movieDiaryPage";
	}
	
	//������̾ �۾��� ó��
	@RequestMapping("movieDiaryPro")
	public String movieDiaryPro(HttpServletRequest req, Model model) {
		
		service.movieDiaryPro(req,model);
		
		return "guest/member_myPage/memMovie/member_myPage_movieDiaryPro";
	}
	
	//������̾ �ۻ���ó��
	@RequestMapping("movieDiaryDel")
	public String movieDiaryDel(HttpServletRequest req, Model model) {
		
		service.movieDiaryDelPro(req,model);
		
		return "guest/member_myPage/memMovie/member_myPage_movieDiaryDelPro";
	}
	
/*----------------------------- ���� ���� ----------------------------------------*/
	
	//���ų��� ����Ʈ
	@RequestMapping("moviePaidList")
	public String moviePaidList(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		service.moviePaidList(req,model);
		
		return "guest/member_myPage/memMovie/member_myPage_moviePaidList";
	}
	
	//������� �α�
	@RequestMapping("restaurantLog")
	public String restaurantLog(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		service.restaurantLog(req, model);
		
		return "guest/member_myPage/memRestaurant/member_myPage_restaurantLog";
	}
	
	//������� ���� ����
	@RequestMapping("memRBookList")
	public String memRBookList(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		service.restaurantLog(req, model);
		
		return "guest/member_myPage/memRestaurant/member_myPage_memRBookList";
	}

	//��ȭ ���ų��� ���ó��
	@RequestMapping("moviePaidDelPro")
	public String moviePaidDelPro(HttpServletRequest req, Model model) {
		
		service.moviePaidDelPro(req, model);
		
		return "guest/member_myPage/memMovie/member_myPage_moviePaidDelPro";
	}
	
	//������� ���೻�� ����
	@RequestMapping("memRBookDel")
	public String memRBookDel(HttpServletRequest req, Model model) {
		
		service.memRBookDel(req, model);
		
		return "guest/member_myPage/memRestaurant/member_myPage_memRBookDelPro";
	}
	
	//�湮����
	@RequestMapping("visit")
	public String visit(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		service.visitList(req, model);
		
		return "guest/member_myPage/service/member_myPage_visit";
	}
	
	//����Ʈ �ȳ�
	@RequestMapping("memPoint")
	public String memPoint(HttpServletRequest req, Model model) {
		
		service.memberCard(req, model);
		
		return "guest/member_myPage/service/member_myPage_memPoint";
	}
	
	
	
	
	
}
