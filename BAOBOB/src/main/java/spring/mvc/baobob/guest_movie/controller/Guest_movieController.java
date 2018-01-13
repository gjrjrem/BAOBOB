package spring.mvc.baobob.guest_movie.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.baobob.guest_movie.service.Guest_movieService;

@Controller
public class Guest_movieController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	Guest_movieService gmservice;
	
	// ��ȭ�� ����ȭ��
	@RequestMapping("guest_movie")
	public String guest_movie(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/guest_movie ======");
		
		return "/guest/guest_movie/movie_main";
	}
	
	//��ȭ-������Ʈ
	@RequestMapping("movieChart")
	public String movieChart(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieChart ======");
		
		gmservice.movieList(req, model);

		return "/guest/guest_movie/movie/movieChart";
	}
	
	//��ȭ-�������δ�
	@RequestMapping("movieFinder")
	public String movieFinder(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieFinder ======");
		
		return "/guest/guest_movie/movie/movieFinder";
	}
	
	//��ȭ-�������δ�
	@RequestMapping("searchResult")
	public String searchResult(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/searchResult ======");
		
		String movie_title = req.getParameter("movie_title");
		String sel = req.getParameter("sel");
		String[] str_movie_janre = req.getParameterValues("movie_janre");
		int[] movie_janre = new int[str_movie_janre.length];

		for(int i =0; i<movie_janre.length; i++){
			movie_janre[i]=Integer.parseInt(str_movie_janre[i]);
		};
		String[] movie_country = req.getParameterValues("movie_country");
		String[] movie_age = req.getParameterValues("movie_age");
		
		model.addAttribute("movie_title", movie_title);
		model.addAttribute("sel", sel);
		model.addAttribute("movie_janre", movie_janre);
		model.addAttribute("movie_country", movie_country);
		model.addAttribute("movie_age", movie_age);
		
		return "/guest/guest_movie/movie/searchResult";
	}
	
	//��ȭ-HD-Ʈ���Ϸ�
	@RequestMapping("hdTrailer")
	public String hdTrailer(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/hdTrailer ======");

		gmservice.hd_Trailer(req, model);
		
		return "/guest/guest_movie/movie/hdTrailer";
	}
	
	//��ȭ-HDƮ���Ϸ� �����ϴ� �� ������
	@RequestMapping("hdTrailerPlaying")
	public String hdTrailerPlaying(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/hdTrailerPlaying ======");

		gmservice.movieInfo(req, model);
		
		return "/guest/guest_movie/movie/hdTrailerPlaying";
	}
	
	//�󼼿�ȭ
	//��ȭ-HDƮ���Ϸ� �����ϴ� �� ������
		@RequestMapping("movieDetail")
		public String movieDetail(HttpServletRequest req, Model model) {
			log.debug("====== Guest_movieController/movieDetail ======");

			gmservice.movieInfo(req, model);
			
			return "/guest/guest_movie/movie/movieDetail";
		}
	
	
	//��ȭ-����
	@RequestMapping("movieReview")
	public String movieReview(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieReview ======");
		
		return "/guest/guest_movie/movie/movieReview";
	}
	
	//����-��������
	@RequestMapping("movieTicket")
	public String movieTicket(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieTicket ======");

		return "/guest/guest_movie/reservation/movieTicket";
	}
	
	//����-�󿵽ð�ǥ
	@RequestMapping("movieSchedule")
	public String movieSchedule(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieSchedule ======");

		return "/guest/guest_movie/reservation/movieSchedule";
	}
	
	//����-Ư����
	@RequestMapping("theaters")
	public String theaters(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/theaters ======");

		return "/guest/guest_movie/theater/theaters";
	}
	
	//�̺�Ʈ-�����
	@RequestMapping("eventMembership")
	public String eventMembership(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/eventMembership ======");

		return "/guest/guest_movie/event/eventMembership";
	}
	
	//�̺�Ʈ-����/����
	@RequestMapping("eventSale")
	public String eventSale(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/eventSale ======");

		return "/guest/guest_movie/event/eventSale";
	}
	
	//�̺�Ʈ-�����λ�
	@RequestMapping("eventPreview")
	public String eventPreview(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/eventPreview ======");

		return "/guest/guest_movie/event/eventPreview";
	}
}
