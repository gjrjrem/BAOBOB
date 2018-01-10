package spring.mvc.baobob.host_movie.controller;

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
	public String hostMovie() {
		System.out.println("hostMovie");
		
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
}
