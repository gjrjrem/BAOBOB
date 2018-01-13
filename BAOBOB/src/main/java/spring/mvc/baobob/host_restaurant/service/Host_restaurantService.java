package spring.mvc.baobob.host_restaurant.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface Host_restaurantService {
	// �Ĵ� �� ������ - �Ĵ� �߰� ó��
	public void restaurantAdd(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] �޴� ����Ʈ
	public void menuList(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] �޴� �߰�
	public void menuAdd(MultipartHttpServletRequest req, Model model);

	// �Ĵ�[1] ������ �޴� ���� ���� / ������ ���� �Է�
	public void menuView(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] �޴� ���� ó��
	public void menuMod(MultipartHttpServletRequest req, Model model);

	// �Ĵ�[1] �޴� ���� ó��
	public void menuDel(HttpServletRequest req, Model model);

	// �Ĵ�[1] ���� ����Ʈ
	public void employeeList(HttpServletRequest req, Model model);

	// ��ü ȸ�� ����Ʈ (�Ĵ�[1] ���� �߰�)
	public void memberList(HttpServletRequest req, Model model);

	// �Ĵ�[1] �������� ����� ȸ�� ���� ����
	public void memberView(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] ���� �߰� ó��
	public void employeeAdd(HttpServletRequest req, Model model);

	// �Ĵ�[1] ���� ���� ����
	public void employeeView(HttpServletRequest req, Model model);

	// �Ĵ�[1] ���� ���� ó��
	public void employeeDel(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] ���� ���� ó��
	
}
