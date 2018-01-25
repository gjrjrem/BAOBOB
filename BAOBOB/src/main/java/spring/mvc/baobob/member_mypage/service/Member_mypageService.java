package spring.mvc.baobob.member_mypage.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface Member_mypageService {
	
	//1:1���� ����Ʈ
	public void memQuestionList(HttpServletRequest req, Model model);
	
	//�нǹ� ���� ����Ʈ
	public void memLostList(HttpServletRequest req, Model model);
	
	//1:1���� ��
	public void memQuestionContentForm(HttpServletRequest req, Model model);
	
	//1:1���� ���� �� ������
	public void memQModifyView(HttpServletRequest req, Model model);
	
	//1:1���� ���� ó��
	public void memQModifyPro(HttpServletRequest req, Model model);
	
	//1:1���� �ۼ� ó��������
	public void memQWritePro(HttpServletRequest req, Model model);
	
	//�нǹ� ���� �ۼ� ó��������
	public void memLWritePro(MultipartHttpServletRequest req, Model model);
	
	//1:1���� ���� ó��������
	public void memQDelPro(HttpServletRequest req, Model model);
	
	//ȸ��ī������ ��������
	public void memberCard(HttpServletRequest req, Model model);
	
	//�������� �Է�������
	public void memPModifyView(HttpServletRequest req, Model model);
	
	//�������� ó��������
	public void memPPro(MultipartHttpServletRequest req, Model model);
	
	//ȸ��Ż�� ó��������
	public void memPDelPro(HttpServletRequest req, Model model);
	
	//����α�-���ø���Ʈ
	public void movieWishList(HttpServletRequest req, Model model);
	
	//����α�-������̾
	public void movieDiaryList(HttpServletRequest req, Model model);
	
	//����α�-������̾ �۾��� ó��
	public void movieDiaryPro(HttpServletRequest req, Model model);
	
	//����α�-������̾ �ۻ���ó��
	public void movieDiaryDelPro(HttpServletRequest req, Model model);
	
	//���ø���Ʈ �߰�
	public void addWishList(HttpServletRequest req, Model model);

	
	//����α�-���ø���Ʈ ����ó��
	public void delMovieWishList(HttpServletRequest req, Model model);
	
	//���ų���
	public void moviePaidList(HttpServletRequest req, Model model);
	
	//���ų��� ���ó��
	public void moviePaidDelPro(HttpServletRequest req, Model model);
	
	//���� �̿��� �������
	public void restaurantLog(HttpServletRequest req, Model model);
	
	//�湮����
	public void visitList(HttpServletRequest req, Model model);
}
