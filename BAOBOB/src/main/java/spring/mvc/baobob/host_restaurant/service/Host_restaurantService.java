package spring.mvc.baobob.host_restaurant.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Host_restaurantService {
	// �Ĵ�[1] �޴� ����Ʈ
	public void menuList(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] �޴� �߰�
	public void menuAdd(HttpServletRequest req, Model model);
}
