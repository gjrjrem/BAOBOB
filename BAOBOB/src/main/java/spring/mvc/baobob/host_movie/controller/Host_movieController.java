package spring.mvc.baobob.host_movie.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.host_movie.service.Host_movieServiceImpl;
import spring.mvc.baobob.host_total.service.Host_totalServiceImpl;
import spring.mvc.baobob.vo.MovieResViewVO;


@Controller
public class Host_movieController {
	@Autowired
	Host_movieServiceImpl service = new Host_movieServiceImpl();
	
	@Autowired
	Host_totalServiceImpl serviceT = new Host_totalServiceImpl();
	
	private Logger log = Logger.getLogger(this.getClass());
	
	// ������ ��ȭ
	@RequestMapping(value="hostMovie")
	public String hostMovie(HttpServletRequest req, Model model) {
		log.debug("hostMovie");
		
		service.hostMovieList(req, model);
		
		return "host/host_movie/hostMovie";
	}
	
	// ��ȭ �߰�
	@RequestMapping(value="hostMovieAddForm")
	public String hostMovieAddForm() {
		log.debug("hostMovieAddForm");
		
		return "host/host_movie/hostMovieAddForm";
	}

	// ��ȭ �߰� ó��
	@RequestMapping(value="hostMovieAddPro")
	public String hostMovieAddPro(MultipartHttpServletRequest req, Model model) {
		log.debug("hostMovieAddPro");
		
		service.hostMovieAddPro(req, model);
		
		return "host/host_movie/hostMovieAddPro";
	}
	
	// ��ȭ ���� ó��
	@RequestMapping(value="hostMovieDel")
	public String hostMovieDel(HttpServletRequest req, Model model) {
		log.debug("hostMovieDel");
		
		service.hostMovieDel(req, model);
		
		return "host/host_movie/hostMovieDel";
	}
	
	// ��ȭ �� ������
	@RequestMapping(value="hostMovieDetail")
	public String hostMovieDetail(HttpServletRequest req, Model model) {
		log.debug("hostMovieDetail");
		
		service.hostMovieDetail(req, model);
		
		return "host/host_movie/hostMovieDetail";
	}
	
	// ��ȭ ���� ó��
	@RequestMapping(value="hostMovieModPro")
	public String hostMovieModPro(MultipartHttpServletRequest req, Model model) {
		log.debug("hostMovieModPro");
		
		service.hostMovieModPro(req, model);
		
		return "host/host_movie/hostMovieModPro";
	}
	
	// ������ �󿵰�
	@RequestMapping(value="hostTheater")
	public String hostTheater(HttpServletRequest req, Model model) {
		log.debug("hostTheater");
		
		service.hostTheaterList(req, model);
		
		return "host/host_movie/hostTheater";
	}
	
	// �󿵰� ��� ��
	@RequestMapping(value="hostTheaterAddForm")
	public String hostTheaterAddForm() {
		log.debug("hostTheaterAddForm");
		
		return "host/host_movie/hostTheaterAddForm";
	}
	
	// �󿵰� ����ϱ�
	@RequestMapping(value="hostTheaterAddPro")
	public String hostTheaterSettingChange(HttpServletRequest req, Model model) {
		log.debug("hostTheaterSettingChange");
		
		service.hostTheaterAddPro(req, model);
		
		return "host/host_movie/hostTheaterAddPro";
	}
	
	// �󿵰� ��
	@RequestMapping(value="hostTheaterDetail")
	public String hostTheaterDetail(HttpServletRequest req, Model model) {
		log.debug("hostTheaterDetail");
		
		service.hostTheaterDetail(req, model);
		
		return "host/host_movie/hostTheaterDetail";
	}
	
	// �󿵰� ���� ó��
	@RequestMapping(value="hostTheaterModPro")
	public String hostTheaterModPro(HttpServletRequest req, Model model) {
		log.debug("hostTheaterModPro");
		
		service.hostTheaterModPro(req, model);
		
		return "host/host_movie/hostTheaterModPro";
	}
	
	// �󿵰� ����
	@RequestMapping(value="hostTheaterDel")
	public String hostTheaterDel(HttpServletRequest req, Model model) {
		log.debug("hostTheaterDel");
		
		service.hostTheaterDel(req, model);
		
		return "host/host_movie/hostTheaterDel";
	} 
	
	// ������
	@RequestMapping(value="hostSchedule")
	public String hostSchedule(HttpServletRequest req, Model model) {
		log.debug("hostSchedule");
		
		service.hostScheduleList(req, model);
		
		return "host/host_movie/hostSchedule";
	}
	
	// ������ ��ȸ�ϱ� ��ư
	@RequestMapping(value="hostScheduleSearch")
	public String hostScheduleSearch(HttpServletRequest req, Model model) {
		log.debug("hostScheduleSearch");
		
		service.hostScheduleSearch(req, model);
		
		return "host/host_movie/hostSchedule";
	}
	
	// ������ �߰� ��
	@RequestMapping(value="hostScheduleAddForm")
	public String hostScheduleAddForm(HttpServletRequest req, Model model) {
		log.debug("hostScheduleAddForm");
		
		service.hostScheduleAddForm(req, model);
		
		return "host/host_movie/hostScheduleAddForm";
	}
	
	// �� ������ �󿵰� ã��
	@RequestMapping(value="checkPosTheater")
	public String checkPosTheater(HttpServletRequest req, Model model) {
		log.debug("checkPosTheater");
		
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
		log.debug("hostScheduleAddPro");
		
		service.hostScheduleAddPro(req, model);
		
		return "host/host_movie/hostScheduleAddPro";
	}
	
	// ������ �� ����
	@RequestMapping(value="hostScheduleDetail")
	public String hostScheduleDetail(HttpServletRequest req, Model model) {
		log.debug("hostScheduleDetail");
		
		service.hostScheduleDetail(req, model);
		
		return "host/host_movie/hostScheduleDetail";
	}
	
	// ������ ���� ó��
	@RequestMapping(value="hostScheduleModPro")
	public String hostScheduleModPro(HttpServletRequest req, Model model) {
		log.debug("hostScheduleModPro");
		
		service.hostScheduleModPro(req, model);
		
		return "host/host_movie/hostScheduleModPro";
	}

	// ������ ���� ó��
	@RequestMapping(value="hostScheduleDelPro")
	public String hostScheduleDelPro(HttpServletRequest req, Model model) {
		log.debug("hostScheduleDelPro");
		
		service.hostScheduleDelPro(req, model);
		
		return "host/host_movie/hostScheduleDelPro";
	}
	
	// ���� ����
	@RequestMapping(value="hostMovieEmp")
	public String hostMovieEmp(HttpServletRequest req, Model model) {
		log.debug("hostMovieEmp");
		
		service.hostMovieEmp(req, model);
		
		return "host/host_movie/hostMovieEmp";
	}
	
	// ���� ����ϱ�
	@RequestMapping(value="hostMovieEmpAddForm")
	public String hostMovieEmpAddForm(HttpServletRequest req, Model model) {
		log.debug("hostMovieEmpAddForm");
		
		service.getMemberList(req, model);
		
		return "host/host_movie/hostMovieEmpAddForm";
	}
	
	// ���� ����� ���� ȸ�� ���̵� Ȯ���ϱ�
	@RequestMapping(value="hostMovieEmpChkMemberId")
	public String hostMovieEmpChkMemberId(HttpServletRequest req, Model model) {
		log.debug("hostMovieEmpChkMemberId");
		
		service.hostMovieEmpChkMemberId(req, model);
		service.getMemberList(req, model);
		
		return "host/host_movie/hostMovieEmpAddForm";
	}
	
	// ���� ��� ó��
	@RequestMapping(value="hostMovieEmpAddPro")
	public String hostMovieEmpAddPro(HttpServletRequest req, Model model) {
		log.debug("hostMovieEmpAddPro");
		
		service.hostMovieEmpAddPro(req, model);
		
		return "host/host_movie/hostMovieEmpAddPro";
	}
	
	// ���� �ذ��ϱ�
	@RequestMapping(value="hostMovieEmpDel")
	public String hostMovieEmpDel(HttpServletRequest req, Model model) {
		log.debug("hostMovieEmpDel");
		
		service.hostMovieEmpDel(req, model);
		
		return "host/host_movie/hostMovieEmpDel";
	}
	
	// ���� ��ȸ
	@RequestMapping(value="hostMovieRes")
	public String hostMovieResList(HttpServletRequest req, Model model) {
		log.debug("hostMovieRes");
		
		service.hostScheduleList(req, model);
		
		return "host/host_movie/hostMovieRes";
	}
	
	// ���� ��
	@RequestMapping(value="hostMovieResDetail")
	public String hostMovieResDetail(HttpServletRequest req, Model model) {
		log.debug("hostMovieResDetail");
		
		service.hostTheaterScheduleDetail(req, model);
		
		return "host/host_movie/hostMovieResDetail";
	}
	
	// ���� ��ȸ�ϱ� ��ư
	@RequestMapping(value="hostResSearch")
	public String hostResSearch(HttpServletRequest req, Model model) {
		log.debug("hostResSearch");
		
		service.hostScheduleSearch(req, model);
		
		return "host/host_movie/hostMovieRes";
	}
	
	// ���� ��
	@RequestMapping(value="hostMovieResView")
	public @ResponseBody MovieResViewVO hostMovieResView(HttpServletRequest req, Model model) {
		log.debug("hostMovieResView");
		
		MovieResViewVO vo = null;
		
		vo = service.hostMovieResView(req, model);
		
		return vo;
	}
	
	// ���� ���
	@RequestMapping(value="hostMovieSettlement")
	public String hostMovieSettlement(HttpServletRequest req, Model model) {
		log.debug("hostMovieSettlement");
		
		serviceT.movieChart(req, model); // �帣�� ����
		service.movieJanreCountChart(req, model); // �帣�� ������ ��
		service.movieAgeChart(req, model); // ���ѿ��ɺ� ����
		service.movieSexCountChart(req, model); // ������ ���� ����
		
		
		return "host/host_movie/hostMovieSettlement";
	}
	
	
	// ��ȭ�� ���� ����Ŭ���� ��������
	@RequestMapping(value="movieWordcloud", produces = "application/json; charset=utf8")
	public String movieWordcloud(HttpServletRequest req, Model model) {
		log.debug("movieWordcloud");
		
		service.movieWordcloud(req, model);
		
		return "guest/guest_movie/movie/movieWordcloud";
	}
	
}
