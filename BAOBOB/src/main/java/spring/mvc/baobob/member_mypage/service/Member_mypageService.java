package spring.mvc.baobob.member_mypage.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.vo.Member;

public interface Member_mypageService {
	
	//1:1���� ����Ʈ
	public void memQuestionList(HttpServletRequest req, Model model);
	
	//1:1���� ��
	public void memQuestionContentForm(HttpServletRequest req, Model model);
	
	//1:1���� ���� �� ������
	public void memQModifyView(HttpServletRequest req, Model model);
	
	//1:1���� ���� ó��
	public void memQModifyPro(HttpServletRequest req, Model model);
	
	//1:1���� �ۼ� ó��������
	public void memQWritePro(HttpServletRequest req, Model model);
	
	//1:1���� ���� ó��������
	public void memQDelPro(HttpServletRequest req, Model model);
	
	//ȸ��ī������ ��������
	public void memberCard(HttpServletRequest req, Model model);
	
	//�������� �Է�������
	public void memPModifyView(HttpServletRequest req, Model model);
	
	//�������� ó��������
	public void memPPro(MultipartHttpServletRequest req, Model model);
	
	
	
	
	
	
	
	
	
	
	

}
