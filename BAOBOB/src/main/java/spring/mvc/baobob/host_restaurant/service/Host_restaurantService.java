package spring.mvc.baobob.host_restaurant.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public interface Host_restaurantService {
	// �Ĵ�[1] �޴� ����Ʈ
	public void menuList(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] �޴� �߰�
	public void menuAdd(MultipartHttpServletRequest req, Model model);

	// �Ĵ�[1] ������ �޴� ���� ���� / ������ ���� �Է�
	public void menuView(HttpServletRequest req, Model model);
	
	// �Ĵ�[1] �޴� ���� ó��
	public void menuMod(MultipartHttpServletRequest req, Model model);
}
