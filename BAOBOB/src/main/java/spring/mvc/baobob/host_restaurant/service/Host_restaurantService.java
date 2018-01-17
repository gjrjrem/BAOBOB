package spring.mvc.baobob.host_restaurant.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.vo.TableVO;

public interface Host_restaurantService {
	// �Ĵ� �� ������ - ���� ����Ʈ
	public void restaurantList(HttpServletRequest req, Model model);
	
	// �Ĵ� �� ������ - �Ĵ� �߰� ó��
	public void restaurantAdd(HttpServletRequest req, Model model);

	// �Ĵ� �� ������ - ������ ���� ���� ��ȸ / ������ ���� �Է�
	public void restaurantView(HttpServletRequest req, Model model);

	// �Ĵ� �� ������ - ���� ���� ó��
	public void restaurantMod(HttpServletRequest req, Model model);

	// �Ĵ� �� ������ - ���� ���� ó��
	public void restaurantDel(HttpServletRequest req, Model model);
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	// �Ĵ�[1] �޴� ����Ʈ
	public void menuList(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] �޴� �߰� ó��
	public void menuAdd(MultipartHttpServletRequest req, Model model);

	// �Ĵ�[1] ������ �޴� ���� ��ȸ / ������ ���� �Է�
	public void menuView(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] �޴� ���� ó��
	public void menuMod(MultipartHttpServletRequest req, Model model);

	// �Ĵ�[1] �޴� ���� ó��
	public void menuDel(HttpServletRequest req, Model model);

	// �Ĵ�[1] ���� ����Ʈ
	public void employeeList(HttpServletRequest req, Model model);

	// ��ü ȸ�� ����Ʈ (�Ĵ�[1] ���� �߰�)
	public void memberList(HttpServletRequest req, Model model);

	// �Ĵ�[1] �������� ����� ȸ�� ���� ��ȸ
	public void memberView(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] ���� �߰� ó��
	public void employeeAdd(HttpServletRequest req, Model model);

	// �Ĵ�[1] ���� ���� ��ȸ
	public void employeeView(HttpServletRequest req, Model model);

	// �Ĵ�[1] ���� ���� ó��
	public void employeeDel(HttpServletRequest req, Model model);

	// �Ĵ�[1] ���� ����Ʈ
	public void reservList(HttpServletRequest req, Model model);

	// �Ĵ�[1] ���� �߰�
	public void reservAdd(HttpServletRequest req, Model model);

	// ������ ��¥�� �ִ� ��� ���� ��ȸ
	public void reservView(HttpServletRequest req, Model model);

	// 
	public TableVO restaurantView2(HttpServletRequest req, Model model);
}
