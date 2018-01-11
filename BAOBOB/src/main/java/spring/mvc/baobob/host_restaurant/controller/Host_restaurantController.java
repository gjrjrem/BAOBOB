package spring.mvc.baobob.host_restaurant.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.host_restaurant.service.Host_restaurantService;

@Controller
public class Host_restaurantController {
	private Logger log = Logger.getLogger(this.getClass());
	
	@Autowired
	Host_restaurantService service;

	// �Ĵ�[1] �޴� ����Ʈ
	@RequestMapping(value = "/hostMenuList")
	public String hostMenuList(HttpServletRequest req, Model model) {
		log.debug("hostMenuList()");

		service.menuList(req, model);
		
		return "host/host_restaurant/hostMenuList";
	}
	
	// �Ĵ�[1] �޴� �߰�
	@RequestMapping(value = "/hostMenuAddForm")
	public String hostMenuAddForm() {
		log.debug("hostMenuAddForm()");
		
		return "host/host_restaurant/hostMenuAddForm";
	}
	
	// �Ĵ�[1] �޴� �߰� ó��
	@RequestMapping(value = "/hostMenuAddPro")
	public String hostMenuAddPro(MultipartHttpServletRequest req, Model model) {
		log.debug("hostMenuAddPro()");
		
		service.menuAdd(req, model);
		
		return "host/host_restaurant/hostMenuAddPro";
	}
	
	// �Ĵ�[1] ������ �޴� ���� ���� / ������ ���� �Է�
	@RequestMapping(value = "/hostMenuModForm")
	public String hostMenuModForm(HttpServletRequest req, Model model) {
		log.debug("hostMenuModForm()");
		
		service.menuView(req, model);
		
		return "host/host_restaurant/hostMenuModForm";
	}
	
	// �Ĵ�[1] �޴� ���� ó��
	@RequestMapping(value = "/hostMenuModPro")
	public String hostMenuModPro(MultipartHttpServletRequest req, Model model) {
		log.debug("hostMenuModPro()");
		
		service.menuMod(req, model);
		
		return "host/host_restaurant/hostMenuModPro";
	}
	
	// �Ĵ�[1] �޴� ���� ó��
	@RequestMapping(value = "/hostMenuDel")
	public String hostMenuDel(HttpServletRequest req, Model model) {
		log.debug("hostMenuDel()");
		
		service.menuDel(req, model);
		
		return hostMenuList(req, model);
	}
}
