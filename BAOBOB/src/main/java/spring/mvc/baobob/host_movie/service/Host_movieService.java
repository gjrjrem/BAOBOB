package spring.mvc.baobob.host_movie.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.vo.MovieResViewVO;

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
	
	// �󿵰� ���� ó��
	public void hostTheaterModPro(HttpServletRequest req, Model model);
	
	// �󿵰� ���� ó��
	public void hostTheaterDel(HttpServletRequest req, Model model);
	
	// ������ ���
	public void hostScheduleList(HttpServletRequest req, Model model);
	
	// ������ �˻�
	public void hostScheduleSearch(HttpServletRequest req, Model model);
	
	// ������ �߰� �� ��ȭ ������ �󿵰� ���� ��������
	public void hostScheduleAddForm(HttpServletRequest req, Model model);
	
	// �󿵰����� �󿵰� Ȯ��
	public void checkPosTheater(HttpServletRequest req, Model model);
	
	// �󿵰� �߰� ó��
	public void hostScheduleAddPro(HttpServletRequest req, Model model);
	
	// ������ �� ���� ��������
	public void hostScheduleDetail(HttpServletRequest req, Model model);
	
	// ������ ���� ó��
	public void hostScheduleModPro(HttpServletRequest req, Model model);
	
	// ������ ���� ó��
	public void hostScheduleDelPro(HttpServletRequest req, Model model);
	
	// ���� ���
	public void hostMovieEmp(HttpServletRequest req, Model model);
	
	// ���� ��� ���̵� Ȯ��
	public void hostMovieEmpChkMemberId(HttpServletRequest req, Model model);
	
	// ���� ��� ó��
	public void hostMovieEmpAddPro(HttpServletRequest req, Model model);
	
	// ���� �ذ�
	public void hostMovieEmpDel(HttpServletRequest req, Model model);
	
	// ���� ��
	public void hostTheaterScheduleDetail(HttpServletRequest req, Model model);
	
	// ���� ��
	public MovieResViewVO hostMovieResView(HttpServletRequest req, Model model);
	
	// �帣�� ��ȭ ���
	public void movieJanreCountChart(HttpServletRequest req, Model model);
	
	// ���� ���ɺ� ����
	public void movieAgeChart(HttpServletRequest req, Model model);

	// ������ ���� ����
	public void movieSexCountChart(HttpServletRequest req, Model model);
}
