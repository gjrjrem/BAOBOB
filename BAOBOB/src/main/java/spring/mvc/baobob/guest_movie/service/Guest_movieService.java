package spring.mvc.baobob.guest_movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Guest_movieService {

	//��ȭ����Ʈ
	public void movieList(HttpServletRequest req, Model model);
	
	//HD-Ʈ���Ϸ�(movie_tbl)
	public void hd_Trailer(HttpServletRequest req, Model model);
	
	//�󼼿�ȭ ����
	public void movieInfo(HttpServletRequest req, Model model);
	
}
