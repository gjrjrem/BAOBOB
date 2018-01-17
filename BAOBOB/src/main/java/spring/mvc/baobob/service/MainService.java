package spring.mvc.baobob.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public interface MainService {

	//���̵� �ߺ� Ȯ��
	public void confirmId(HttpServletRequest req, Model model);
	
	//���� ó��
	public void joinPro(HttpServletRequest req, Model model);

	//��й�ȣ ã��
	public void mainPwdEmail(HttpServletRequest req, Model model);
	
	//���� �̸��� ����
	public String emailSend(HttpServletRequest req);
	
	//�̸��� ����
	public void confirmEmail(HttpServletRequest req, Model model);
	
	//��й�ȣ ã�� �̸��� ����
	public void confirmPwdEmail(HttpServletRequest req, Model model);
	
	//�α��� ó��
	public void signInPro(HttpServletRequest req, Model model);
	
	//firebase - facebook �α���
	public void firebaseLoginPro(HttpServletRequest req, Model model);
	
	//������
	public void mainHelp(HttpServletRequest req, Model model);
	
	//����Ű
	public String randomKey();
}
