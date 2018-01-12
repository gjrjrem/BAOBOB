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
	
	// 관리자 영화
	@RequestMapping(value="hostMovie")
	public String hostMovie(HttpServletRequest req, Model model) {
		System.out.println("hostMovie");
		
		service.hostMovieList(req, model);
		
		return "host/host_movie/hostMovie";
	}
	
	// 영화 추가
	@RequestMapping(value="hostMovieAddForm")
	public String hostMovieAddForm() {
		System.out.println("hostMovieAddForm");
		
		return "host/host_movie/hostMovieAddForm";
	}

	// 영화 추가 처리
	@RequestMapping(value="hostMovieAddPro")
	public String hostMovieAddPro(MultipartHttpServletRequest req, Model model) {
		System.out.println("hostMovieAddPro");
		
		service.hostMovieAddPro(req, model);
		
		return "host/host_movie/hostMovieAddPro";
	}
	
	// 영화 삭제 처리
	@RequestMapping(value="hostMovieDel")
	public String hostMovieDel(HttpServletRequest req, Model model) {
		System.out.println("hostMovieDel");
		
		service.hostMovieDel(req, model);
		
		return "host/host_movie/hostMovieDel";
	}
	
	// 영화 상세 페이지
	@RequestMapping(value="hostMovieDetail")
	public String hostMovieDetail(HttpServletRequest req, Model model) {
		System.out.println("hostMovieDetail");
		
		service.hostMovieDetail(req, model);
		
		return "host/host_movie/hostMovieDetail";
	}
	
	// 영화 수정 처리
	@RequestMapping(value="hostMovieModPro")
	public String hostMovieModPro(MultipartHttpServletRequest req, Model model) {
		System.out.println("hostMovieModPro");
		
		service.hostMovieModPro(req, model);
		
		return "host/host_movie/hostMovieModPro";
	}
	
	// 관리자 상영관
	@RequestMapping(value="hostTheater")
	public String hostTheater(HttpServletRequest req, Model model) {
		System.out.println("hostTheater");
		
		service.hostTheaterList(req, model);
		
		return "host/host_movie/hostTheater";
	}
	
	// 상영관 등록하기
	@RequestMapping(value="hostTheaterAddForm")
	public String hostTheaterAddForm() {
		System.out.println("hostTheaterAddForm");
		
		return "host/host_movie/hostTheaterAddForm";
	}
	
	// 상영관 좌석 업데이트
	@RequestMapping(value="hostTheaterAddPro")
	public String hostTheaterSettingChange(HttpServletRequest req, Model model) {
		System.out.println("hostTheaterSettingChange");
		
		service.hostTheaterAddPro(req, model);
		
		return "host/host_movie/hostTheaterAddPro";
	}
	
	// 상영관 상세
	@RequestMapping(value="hostTheaterDetail")
	public String hostTheaterDetail(HttpServletRequest req, Model model) {
		System.out.println("hostTheaterDetail");
		
		service.hostTheaterDetail(req, model);
		
		return "host/host_movie/hostTheaterDetail";
	}
}
