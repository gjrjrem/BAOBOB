package spring.mvc.baobob.guest_movie.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.baobob.guest_movie.service.Guest_movieService;
import spring.mvc.baobob.host_movie.service.Host_movieServiceImpl;
import spring.mvc.baobob.vo.MovieResViewVO;
import spring.mvc.baobob.vo.Theater_seatVO;

@Controller
public class Guest_movieController {

	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	Guest_movieService gmservice;
	
	@Autowired
	Host_movieServiceImpl hmservice;
	
	// ��ȭ�� ����ȭ��
	@RequestMapping("guest_movie")
	public String guest_movie(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/guest_movie ======");
		
		gmservice.movieMain(req, model);
		
		return "/guest/guest_movie/movie_main";
	}
	
	//��ȭ�� ���� Ajax
	@RequestMapping("gMovieMainRankUpdate")
	public String gMovieMainRankUpdate(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/gMovieMainRankUpdate ======");
		gmservice.movieMain(req, model);
		return "guest/guest_movie/movie_main_rank";
	}
	
	// �α����� �ʿ��� ���� �϶�
	@RequestMapping("loginCheck")
	public String loginCheck(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/guest_movie ======");
		
		return "/guest/guest_movie/movie/loginCheck";
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
		
		gmservice.movieList(req, model);
		
		return "/guest/guest_movie/movie/movieFinder";
	}
	
	//��ȭ-�������δ�
	@RequestMapping("searchResult")
	public String searchResult(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/searchResult ======");
		
		gmservice.searchResult(req, model);
		
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
		gmservice.reviewList(req, model);
		
		return "/guest/guest_movie/movie/movieDetail";
	}
	
	//��ȭ-����
	@RequestMapping("movieReview")
	public String movieReview(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieReview ======");
		
		return "/guest/guest_movie/movie/movieReview";
	}
	
	//��ȭ-�����ۼ���
	@RequestMapping("movieReviewWrite")
	public String reviewWrite(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieReviewWrite ======");
		
		gmservice.movieReviewCheck(req, model);
		gmservice.movieInfo(req, model);
		
		return "/guest/guest_movie/movie/movieReviewWrite";
	}
	
	//��ȭ-���������
	@RequestMapping("movieReviewModify")
	public String movieReviewModify(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieReviewModify ======");
		
		int review_index = Integer.parseInt(req.getParameter("review_index"));
		System.out.println("review_index: "+review_index);
		model.addAttribute("review_index",review_index);
		gmservice.movieInfo(req, model);
		
		return "/guest/guest_movie/movie/movieReviewModify";
	}
	
	//��ȭ-���� �ۼ�, ����, ����ó��
	@RequestMapping("movieReviewPro")
	public String movieReviewPro(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieReviewPro ======");
		
		int pro = Integer.parseInt(req.getParameter("pro"));
		gmservice.movieReviewPro(req, model);
		if(pro == 1) {
			hmservice.wordAnalyzer(req, model);
		}
		
		return "/guest/guest_movie/movie/movieReviewPro";
	}
	
	//����-��������
	@RequestMapping("movieTicket")
	public String movieTicket(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieTicket ======");

		gmservice.reserveMovieList(req, model);
		
		return "/guest/guest_movie/reservation/movieTicket";
	}
	
	//����-��������(��ȭ,��¥���ý� - ajax ó�� =>dateResult�� )
	@RequestMapping("dateResult")
	public String dateResult(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/dateResult ======");
		
		gmservice.reserveDateList(req, model);
		
		return "/guest/guest_movie/reservation/dateResult";
	}
	
	//���ż����� ��ȭ����
	@RequestMapping("reserveMovieInfo")
	public String reserveMovieInfo(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/reserveMovieInfo ======");
		
		gmservice.reserveMovieResult(req, model);
		
		return "/guest/guest_movie/reservation/reserveMovieInfo";
	}
	
	//���ż����� ��ȭ����
	@RequestMapping("reserveScheduleInfo")
	public String reserveScheduleInfo(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/reserveScheduleInfo ======");
		
		gmservice.reserveScheduleResult(req, model);
		
		return "/guest/guest_movie/reservation/reserveScheduleInfo";
	}
	
	//���������� �����ϴ� ��ư(movie_index, theater_schedule_index);
	@RequestMapping("nextSeatButton")
	public String nextSeatButton(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/nextSeatButton ======");
		
		int movie_index = Integer.parseInt(req.getParameter("movie_index"));
		int theater_schedule_index = Integer.parseInt(req.getParameter("theater_schedule_index"));
		model.addAttribute("movie_index", movie_index);
		model.addAttribute("theater_schedule_index", theater_schedule_index);
		
		return "/guest/guest_movie/reservation/nextSeatButton";
	}
	
	//����-�������� 2��°������ - �¼� ����
	@RequestMapping("movieTicket2")
	public String movieTicket2(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieTicket2 ======");

		//���� ������ ��ȭ, ����������
		gmservice.reserveMovieResult(req, model);
		gmservice.reserveScheduleResult(req, model);
		
		return "/guest/guest_movie/reservation/movieTicket2";
	}
	
	//����-�������� 2��°������ - �¼��� ���� �ҷ�����
	@RequestMapping("seatInfo")
	public @ResponseBody MovieResViewVO seatInfo(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/seatInfo ======");

		//�¼��� ����
		MovieResViewVO seatInfo = null;
		seatInfo = gmservice.movieResView(req, model);
		//ResponseBody�� �ڹ� ��ü�� �۽����ش�. 
		
		return seatInfo;
	}
	
	//����-�������� 2��°������ - �¼� ����
	@RequestMapping("reserveSeatInfo")
	public String reserveSeatInfo(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/reserveSeatInfo ======");
 
		int allCnt = Integer.parseInt(req.getParameter("allCnt"));
		model.addAttribute("allCnt", allCnt);
		gmservice.seatSelect(req, model);
		
		return "/guest/guest_movie/reservation/reserveSeatInfo";
	}
	
	//����-�������� 2��°������ - ������ �¼� ����
	@RequestMapping("selectSeatInfo")
	public String selectSeatInfo(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/selectSeatInfo ======");
 
		gmservice.seatInfo(req, model);
		
		return "/guest/guest_movie/reservation/selectSeatInfo";
	}
	
	
	//����-�������� 2��°������ - ����â
	@RequestMapping("movieTicket3")
	public String movieTicket3(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/movieTicket3 ======");
		
		gmservice.seatInfos(req, model);
		
		return "/guest/guest_movie/reservation/movieTicket3";
	}
	
	//����-�������� 3��°������ - ����Ʈ ��� �� �ٲ��ֱ�
	@RequestMapping("pointUse")
	public @ResponseBody int pointUse(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/pointUse ======");

		//��밡���� ����Ʈ
		int pointSalePrice = Integer.parseInt(req.getParameter("point"));
		//ResponseBody�� �ڹ� ��ü�� �۽����ش�. 
		
		return pointSalePrice;
	}
	
	//���� ���� ó���ϴ� ��
	@RequestMapping("reservationMoviePro")
	public String reservationMoviePro(HttpServletRequest req, Model model) {
		log.debug("====== Guest_movieController/reservationMoviePro ======");

		gmservice.reservationPro(req, model);
		
		return "/guest/guest_movie/reservation/reservationMoviePro";
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
	
}
