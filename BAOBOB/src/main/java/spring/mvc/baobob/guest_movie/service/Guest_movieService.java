package spring.mvc.baobob.guest_movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Guest_movieService {

	//�⺻ ��ȭ����
	public void movieList(HttpServletRequest req, Model model);
	
	//������ ��ȭ����Ʈ(+��������)
	public void playingMovieList(HttpServletRequest req, Model model);
	
	//HD-Ʈ���Ϸ�(movie_tbl)
	public void hd_Trailer(HttpServletRequest req, Model model);
	
	//HD-Ʈ���Ϸ�(movie_tbl) ���� 
	public void hd_TrailerPlaying(HttpServletRequest req, Model model);
	
}
