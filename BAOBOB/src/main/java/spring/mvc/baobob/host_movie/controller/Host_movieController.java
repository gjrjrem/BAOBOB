package spring.mvc.baobob.host_movie.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.host_movie.service.Host_movieServiceImpl;


@Controller
public class Host_movieController {
	@Autowired
	Host_movieServiceImpl service = new Host_movieServiceImpl();
	
	// ������ ��ȭ
	@RequestMapping(value="hostMovie")
	public String hostMovie(HttpServletRequest req, Model model) {
		System.out.println("hostMovie");
		
		service.hostMovieList(req, model);
		
		return "host/host_movie/hostMovie";
	}
	
	// ��ȭ �߰�
	@RequestMapping(value="hostMovieAddForm")
	public String hostMovieAddForm() {
		System.out.println("hostMovieAddForm");
		
		return "host/host_movie/hostMovieAddForm";
	}

	// ��ȭ �߰� ó��
	@RequestMapping(value="hostMovieAddPro")
	public String hostMovieAddPro(MultipartHttpServletRequest req, Model model) {
		System.out.println("hostMovieAddPro");
		
		service.hostMovieAddPro(req, model);
		
		return "host/host_movie/hostMovieAddPro";
	}
	
	// ��ȭ ���� ó��
	@RequestMapping(value="hostMovieDel")
	public String hostMovieDel(HttpServletRequest req, Model model) {
		System.out.println("hostMovieDel");
		
		service.hostMovieDel(req, model);
		
		return "host/host_movie/hostMovieDel";
	}
	
	// ��ȭ �� ������
	@RequestMapping(value="hostMovieDetail")
	public String hostMovieDetail(HttpServletRequest req, Model model) {
		System.out.println("hostMovieDetail");
		
		service.hostMovieDetail(req, model);
		
		return "host/host_movie/hostMovieDetail";
	}
	
	// ��ȭ ���� ó��
	@RequestMapping(value="hostMovieModPro")
	public String hostMovieModPro(MultipartHttpServletRequest req, Model model) {
		System.out.println("hostMovieModPro");
		
		service.hostMovieModPro(req, model);
		
		return "host/host_movie/hostMovieModPro";
	}
	
	// ������ �󿵰�
	@RequestMapping(value="hostTheater")
	public String hostTheater(HttpServletRequest req, Model model) {
		System.out.println("hostTheater");
		
		service.hostTheaterList(req, model);
		
		return "host/host_movie/hostTheater";
	}
	
	// �󿵰� ����ϱ�
	@RequestMapping(value="hostTheaterAddForm")
	public String hostTheaterAddForm() {
		System.out.println("hostTheaterAddForm");
		
		return "host/host_movie/hostTheaterAddForm";
	}
	
	// �󿵰� �¼� ������Ʈ
	@RequestMapping(value="hostTheaterAddPro")
	public String hostTheaterSettingChange(HttpServletRequest req, Model model) {
		System.out.println("hostTheaterSettingChange");
		
		service.hostTheaterAddPro(req, model);
		
		return "host/host_movie/hostTheaterAddPro";
	}
	
	// �󿵰� ��
	@RequestMapping(value="hostTheaterDetail")
	public String hostTheaterDetail(HttpServletRequest req, Model model) {
		System.out.println("hostTheaterDetail");
		
		service.hostTheaterDetail(req, model);
		
		return "host/host_movie/hostTheaterDetail";
	}
	
	// �󿵰� ���� ó��
	@RequestMapping(value="hostTheaterModPro")
	public String hostTheaterModPro(HttpServletRequest req, Model model) {
		System.out.println("hostTheaterModPro");
		
		service.hostTheaterModPro(req, model);
		
		return "host/host_movie/hostTheaterModPro";
	}
	
	// �󿵰� ����
	@RequestMapping(value="hostTheaterDel")
	public String hostTheaterDel(HttpServletRequest req, Model model) {
		System.out.println("hostTheaterDel");
		
		service.hostTheaterDel(req, model);
		
		return "host/host_movie/hostTheaterDel";
	} 
	
	// ������
	@RequestMapping(value="hostSchedule")
	public String hostSchedule(HttpServletRequest req, Model model) {
		System.out.println("hostSchedule");
		
		service.hostScheduleList(req, model);
		
		return "host/host_movie/hostSchedule";
	}
	
	// ������ ��ȸ�ϱ� ��ư
	@RequestMapping(value="hostScheduleSearch")
	public String hostScheduleSearch(HttpServletRequest req, Model model) {
		System.out.println("hostScheduleSearch");
		
		service.hostScheduleSearch(req, model);
		
		return "host/host_movie/hostSchedule";
	}
	
	// ������ �߰� ��
	@RequestMapping(value="hostScheduleAddForm")
	public String hostScheduleAddForm(HttpServletRequest req, Model model) {
		System.out.println("hostScheduleAddForm");
		
		service.hostScheduleAddForm(req, model);
		
		return "host/host_movie/hostScheduleAddForm";
	}
	
	// �� ������ �󿵰� ã��
	@RequestMapping(value="checkPosTheater")
	public String checkPosTheater(HttpServletRequest req, Model model) {
		System.out.println("checkPosTheater");
		
		service.checkPosTheater(req, model);
		
		String schedule_startDate = req.getParameter("schedule_startDate");
		String schedule_startTime = req.getParameter("schedule_startTime");
		model.addAttribute("schedule_startDate", schedule_startDate);
		model.addAttribute("schedule_startTime", schedule_startTime);
		
		return "host/host_movie/hostScheduleAddForm";
	}
	
	// ������ �߰� ó��
	@RequestMapping(value="hostScheduleAddPro")
	public String hostScheduleAddPro(HttpServletRequest req, Model model) {
		System.out.println("hostScheduleAddPro");
		
		service.hostScheduleAddPro(req, model);
		
		return "host/host_movie/hostScheduleAddPro";
	}
	
	// ������ �� ����
	@RequestMapping(value="hostScheduleDetail")
	public String hostScheduleDetail(HttpServletRequest req, Model model) {
		System.out.println("hostScheduleDetail");
		
		service.hostScheduleDetail(req, model);
		
		return "host/host_movie/hostScheduleDetail";
	}
	
	// ������ ���� ó��
	@RequestMapping(value="hostScheduleModPro")
	public String hostScheduleModPro(HttpServletRequest req, Model model) {
		System.out.println("hostScheduleModPro");
		
		service.hostScheduleModPro(req, model);
		
		return "host/host_movie/hostScheduleModPro";
	}

	// ������ ���� ó��
	@RequestMapping(value="hostScheduleDelPro")
	public String hostScheduleDelPro(HttpServletRequest req, Model model) {
		System.out.println("hostScheduleDelPro");
		
		service.hostScheduleDelPro(req, model);
		
		return "host/host_movie/hostScheduleDelPro";
	}
	
	// ���� ����
	@RequestMapping(value="hostMovieEmp")
	public String hostMovieEmp(HttpServletRequest req, Model model) {
		System.out.println("hostMovieEmp");
		
		
		
		
		return "host/host_movie/hostMovieEmp";
	}
	
	// ���� ����ϱ�
	@RequestMapping(value="hostMovieEmpAddForm")
	public String hostMovieEmpAddForm(HttpServletRequest req, Model model) {
		System.out.println("hostMovieEmpAddForm");
		
		
		
		
		return "host/host_movie/hostMovieEmpAddForm";
	}
	
	// ���� ����� ���� ȸ�� ���̵� Ȯ���ϱ�
	@RequestMapping(value="hostMovieEmpChkMemberId")
	public String hostMovieEmpChkMemberId(HttpServletRequest req, Model model) {
		System.out.println("hostMovieEmpChkMemberId");
		
		service.hostMovieEmpChkMemberId(req, model);
		
		return "host/host_movie/hostMovieEmpAddForm";
	}
	
	// ���� ��� ó��
	@RequestMapping(value="hostMovieEmpAddPro")
	public String hostMovieEmpAddPro(HttpServletRequest req, Model model) {
		System.out.println("hostMovieEmpAddPro");
		
		service.hostMovieEmpAddPro(req, model);
		
		return "host/host_movie/hostMovieEmpAddPro";
	}
}
