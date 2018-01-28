package spring.mvc.baobob.guest_movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

import spring.mvc.baobob.vo.MovieResViewVO;

public interface Guest_movieService {

	//��ȭ ����
	public void movieMain(HttpServletRequest req, Model model);
	
	//��ȭ����Ʈ
	public void movieList(HttpServletRequest req, Model model);
	
	//HD-Ʈ���Ϸ�(movie_tbl)
	public void hd_Trailer(HttpServletRequest req, Model model);
	
	//�󼼿�ȭ ����(+ ���ƿ� ����)
	public void movieInfo(HttpServletRequest req, Model model);
	
	//�������δ� �˻����
	public void searchResult(HttpServletRequest req, Model model);
	
	//���� ����Ʈ
	public void reviewList(HttpServletRequest req, Model model);
	
	//���� ������� üũ
	public void movieReviewCheck(HttpServletRequest req, Model model);
	
	//�����ۼ�ó��
	public void movieReviewPro(HttpServletRequest req, Model model);
	
	//����â�� ��� ��ȭ����Ʈ(�������� �� ����)
	public void reserveMovieList(HttpServletRequest req, Model model);
	
	//��ȭ,�󿵰��� �Ǵ� ��¥ 
	public void reserveDateList(HttpServletRequest req, Model model);
	
	//��ȭ ������(���ſ��� Ajax�� ���� ����)
	public void reserveMovieResult(HttpServletRequest req, Model model);
	
	//������ ������(���ſ��� Ajax�� ���� ����)
	public void reserveScheduleResult(HttpServletRequest req, Model model);

	//�¼��� �����ֱ�
	public MovieResViewVO movieResView(HttpServletRequest req, Model model);
	
	//�¼��� ����
	public void seatSelect(HttpServletRequest req, Model model);
	
	//1 �¼� ����
	public void seatInfo(HttpServletRequest req, Model model);
	
	//���õ� �¼����� ������ 3��° ����â����
	public void seatInfos(HttpServletRequest req, Model model);
	
	//���� ����ó��
	public void reservationPro(HttpServletRequest req, Model model);
}
