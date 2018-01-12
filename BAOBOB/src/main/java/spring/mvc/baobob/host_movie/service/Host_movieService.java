package spring.mvc.baobob.host_movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface Host_movieService {
	
	// ��ȭ ���
	public void hostMovieList(HttpServletRequest req, Model model);

	//��ȭ �߰� ó��
	public void hostMovieAddPro(MultipartHttpServletRequest req, Model model);
	
	// ��ȭ ����
	public void hostMovieDel(HttpServletRequest req, Model model);
	
	// ��ȭ ��
	public void hostMovieDetail(HttpServletRequest req, Model model);
	
	// ��ȭ ����
	public void hostMovieModPro(MultipartHttpServletRequest req, Model model);
	
	// �󿵰� ����Ʈ
	public void hostTheaterList(HttpServletRequest req, Model model);
	
	// �󿵰� �¼� ������Ʈ
	public void hostTheaterAddPro(HttpServletRequest req, Model model);
	
	// �󿵰� ��
	public void hostTheaterDetail(HttpServletRequest req, Model model);
	
	
}
