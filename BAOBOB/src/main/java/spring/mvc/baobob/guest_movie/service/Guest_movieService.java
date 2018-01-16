package spring.mvc.baobob.guest_movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Guest_movieService {

	//영화리스트
	public void movieList(HttpServletRequest req, Model model);
	
	//HD-트레일러(movie_tbl)
	public void hd_Trailer(HttpServletRequest req, Model model);
	
	//상세영화 정보
	public void movieInfo(HttpServletRequest req, Model model);
	
	//무비파인더 검색결과
	public void searchResult(HttpServletRequest req, Model model);
	
	//리뷰 리스트
	public void reviewList(HttpServletRequest req, Model model);
	
	//리뷰 썼었는지 체크
	public void movieReviewCheck(HttpServletRequest req, Model model);
	
	//리뷰작성처리
	public void movieReviewPro(HttpServletRequest req, Model model);
}
