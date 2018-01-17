package spring.mvc.baobob.guest_restaurant.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Guest_restaurantService {
	//==========================================================================
	//============================== 1. ������� ���� ==============================
	//==========================================================================
	//1. ������� ����
	public void restaurant_imfomation(HttpServletRequest req, Model model);
	
	//==========================================================================
	//============================== 2. ������� �޴� ==============================
	//==========================================================================
	//2-1. ������� �޴� ����
	public void restaurant_menuInfo(HttpServletRequest req, Model model);
	//2-2. ������� �޴� ��
	//	public void restaurant_menu_Content(HttpServletRequest req, Model model);
	
	
	//==========================================================================
	//============================== 3. ������� ���� ==============================
	//==========================================================================
	//3-1. ������� ����
	public void restaurant_tableList(HttpServletRequest req, Model model);
	
	//==========================================================================
	//============================== 4. ������� ���� ==============================
	//==========================================================================
	//4-1. ���� ����Ʈ
	public void reviewList(HttpServletRequest req, Model model);
	
	//4-2. ���� �ۼ�
	public void reviewWrite(HttpServletRequest req, Model model); 
	
	//4-3-2. ���� ����
	public void review_modifyView(HttpServletRequest req, Model model); 
	//4-3-3. ���� ���� ó��
	public void review_modeifyPro(HttpServletRequest req, Model model);
	
	//4-4. ���� ����
	public void reviewDeletePro(HttpServletRequest req, Model model);
	//==========================================================================
	//==========================================================================
}
