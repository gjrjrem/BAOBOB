package spring.mvc.baobob.host_movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface Host_movieService {
	
	// ��ȭ ���
	public void hostMovieList(HttpServletRequest req, Model model);

	//��ȭ �߰� ó��
	public void hostMovieAddPro(MultipartHttpServletRequest req, Model model);
}
