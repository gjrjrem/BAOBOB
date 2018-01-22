package spring.mvc.baobob.host_restaurant.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import spring.mvc.baobob.host_restaurant.service.Host_restaurantService;
import spring.mvc.baobob.vo.TableVO;

@Controller
public class Host_restaurantController {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	Host_restaurantService service;

	// �Ĵ� �� ������ - ���� ���
	@RequestMapping(value = "/hostRestaurantList")
	public String hostRestaurantList(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantList()");

		service.restaurantList(req, model);

		return "host/host_restaurant/hostRestaurantList";
	}

	// �Ĵ� �� ������ - ���� �߰�
	@RequestMapping(value = "/hostRestaurantAddForm")
	public String hostRestaurantAddForm() {
		log.debug("hostRestaurantAddForm()");

		return "host/host_restaurant/hostRestaurantAddForm";
	}

	// �Ĵ� �� ������ - �Ĵ� �߰� ó��
	@RequestMapping(value = "/hostRestaurantAddPro")
	public String hostRestaurantAddPro(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantAddPro()");

		service.restaurantAdd(req, model);

		return "host/host_restaurant/hostRestaurantAddPro";
	}

	// �Ĵ� �� ������ - ������ ���� ���� ��ȸ / ������ ���� �Է�
	@RequestMapping(value = "/hostRestaurantModForm")
	public String hostRestaurantModForm(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantModForm()");

		service.restaurantView(req, model);

		return "host/host_restaurant/hostRestaurantModForm";
	}

	// �Ĵ� �� ������ - ���� ���� ���� ó��
	@RequestMapping(value = "/hostRestaurantModPro")
	public String hostRestaurantModPro(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantModPro()");

		service.restaurantMod(req, model);

		return "host/host_restaurant/hostRestaurantModPro";
	}

	// �Ĵ� �� ������ - ���� ���� ���� ó��
	@RequestMapping(value = "/hostRestaurantDel")
	public String hostRestaurantDel(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantDel()");

		service.restaurantDel(req, model);

		return "host/host_restaurant/hostRestaurantDel";
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	// �Ĵ纰 �޴� ����Ʈ
	@RequestMapping(value = "/hostMenuList")
	public String hostMenuList(HttpServletRequest req, Model model) {
		log.debug("hostMenuList()");

		service.menuList(req, model);

		return "host/host_restaurant/hostMenuList";
	}

	// �Ĵ纰 �޴� �߰�
	@RequestMapping(value = "/hostMenuAddForm")
	public String hostMenuAddForm() {
		log.debug("hostMenuAddForm()");

		return "host/host_restaurant/hostMenuAddForm";
	}

	// �޴� �߰� ó��
	@RequestMapping(value = "/hostMenuAddPro")
	public String hostMenuAddPro(MultipartHttpServletRequest req, Model model) {
		log.debug("hostMenuAddPro()");

		service.menuAdd(req, model);

		return "host/host_restaurant/hostMenuAddPro";
	}

	// ������ �޴� ���� ��ȸ / ������ ���� �Է�
	@RequestMapping(value = "/hostMenuModForm")
	public String hostMenuModForm(HttpServletRequest req, Model model) {
		log.debug("hostMenuModForm()");

		service.menuView(req, model);

		return "host/host_restaurant/hostMenuModForm";
	}

	// �޴� ���� ó��
	@RequestMapping(value = "/hostMenuModPro")
	public String hostMenuModPro(MultipartHttpServletRequest req, Model model) {
		log.debug("hostMenuModPro()");

		service.menuMod(req, model);

		return "host/host_restaurant/hostMenuModPro";
	}

	// �޴� ���� ó��
	@RequestMapping(value = "/hostMenuDel")
	public String hostMenuDel(HttpServletRequest req, Model model) {
		log.debug("hostMenuDel()");

		service.menuDel(req, model);

		return "host/host_restaurant/hostMenuDel";
	}

	// �Ĵ纰 ���� ����Ʈ
	@RequestMapping(value = "/hostEmployeeList")
	public String hostEmployeeList(HttpServletRequest req, Model model) {
		log.debug("hostEmployeeList()");

		service.employeeList(req, model);

		return "host/host_restaurant/hostEmployeeList";
	}

	// ��ü ȸ�� ���(���� ��� ��)
	@RequestMapping(value = "/hostMemberList")
	public String hostMemberList(HttpServletRequest req, Model model) {
		log.debug("hostMemberList()");

		service.memberList(req, model);

		return "host/host_restaurant/hostMemberList";
	}

	// ���� ���
	@RequestMapping(value = "/hostEmployeeAddForm")
	public String hostEmployeeAddView(HttpServletRequest req, Model model) {
		log.debug("hostEmployeeAddForm()");

		// �������� ����� ȸ�� ���� ��ȸ
		service.memberView(req, model);

		return "host/host_restaurant/hostEmployeeAddForm";
	}

	// ���� ��� ó��
	@RequestMapping(value = "/hostEmployeeAddPro")
	public String hostEmployeeAddPro(HttpServletRequest req, Model model) {
		log.debug("hostMenuAddPro()");

		service.employeeAdd(req, model);

		return "host/host_restaurant/hostEmployeeAddPro";
	}

	// ���� ���
	@RequestMapping(value = "/hostEmployeeView")
	public String hostEmployeeView(HttpServletRequest req, Model model) {
		log.debug("hostEmployeeView()");

		// �Ĵ�[1] ���� ���� ��ȸ
		service.memberView(req, model);
		service.employeeView(req, model);

		return "host/host_restaurant/hostEmployeeView";
	}

	// ���� ���� ó��
	@RequestMapping(value = "/hostEmployeeDel")
	public String hostEmployeeDel(HttpServletRequest req, Model model) {
		log.debug("hostEmployeeDel()");

		service.employeeDel(req, model);

		return "host/host_restaurant/hostEmployeeDel";
	}

	// �Ĵ纰 ���� ����Ʈ
	@RequestMapping(value = "/hostReservList")
	public String hostReservList(HttpServletRequest req, Model model) {
		log.debug("hostReservList()");

		service.reservList(req, model);

		return "host/host_restaurant/hostReservList";
	}

	// �Ĵ纰 ���� ������
	@RequestMapping(value = "/hostReservAddForm")
	public String hostReservAddForm(HttpServletRequest req, Model model) {
		log.debug("hostReservAddForm()");

		return "host/host_restaurant/hostReservAddForm";
	}

	// ���� - ���̺� ���� ���� ���� �� ���̺� ��ȸ
	@RequestMapping(value = "checkPosRestaurant")
	public String checkPosRestaurant(HttpServletRequest req, Model model) {
		log.debug("checkPosRestaurant()");

		service.restaurantView(req, model);
		model.addAttribute("confirm", 1);

		return "host/host_restaurant/hostReservAddForm";
	}

	// ����� ��¥�� ���̺� �� �� ��ȸ
	@RequestMapping(value = "checkPosRestaurant2")
	public String checkPosRestaurant2(HttpServletRequest req, Model model) {
		log.debug("checkPosRestaurant2()");

		service.reservList(req, model);
		service.restaurantView(req, model);
		model.addAttribute("confirm", 1);

		return "host/host_restaurant/hostReservList";
	}

	// ���� ó��
	@RequestMapping(value = "/hostReservAddPro")
	public String hostReservAddPro(HttpServletRequest req, Model model) {
		log.debug("hostReservAddPro()");

		service.reservAdd(req, model);

		return "host/host_restaurant/hostReservAddPro";
	}

	// ������ ��ȸ�ϱ� ��ư
	@RequestMapping(value = "/reservView")
	public String hostScheduleSearch(HttpServletRequest req, Model model) {
		log.debug("reservView()");

		service.reservView(req, model);

		return "host/host_restaurant/hostReservList";
	}

	// ������ �� ����� ���̺� ��ȸ
	@RequestMapping(value = "/restaurantView")
	public @ResponseBody TableVO restaurantView(HttpServletRequest req, Model model) {
		log.debug("restaurantView()");

		TableVO dto = service.restaurantView2(req, model);

		return dto;
	}

	// ��� �޴� ����Ʈ
	@RequestMapping(value = "/hostAllMenuList")
	public String hostAllMenuList(HttpServletRequest req, Model model) {
		log.debug("hostAllMenuList()");

		service.allMenuList(req, model);

		return "host/host_restaurant/hostAllMenuList";
	}

	// ��� ���� ����Ʈ
	@RequestMapping(value = "/hostAllEmployeeList")
	public String hostAllEmployeeList(HttpServletRequest req, Model model) {
		log.debug("hostAllEmployeeList()");

		service.allEmployeeList(req, model);

		return "host/host_restaurant/hostAllEmployeeList";
	}
	
	// ������ �� ����
	@RequestMapping(value="/hostOrderAddForm")
	public String hostOrderAddForm(HttpServletRequest req, Model model) {
		log.debug("hostOrderAddForm()");

		service.menuList(req, model);
		service.useTableView(req, model);
		
		return "host/host_restaurant/hostOrderAddForm";
	}
	
	// ���̺� �޴� �߰�(�Ǹ�)
	@RequestMapping(value="/hostOrderAddPro")
	public String hostOrderAddPro(HttpServletRequest req, Model model) {
		log.debug("hostOrderAddPro()");

		service.orderAdd(req, model);
		
		return "host/host_restaurant/hostOrderAddPro";
	}
	
	// ���̺� �޴� �߰�(�Ǹ�)
	@RequestMapping(value="/hostRestaurantAccount")
	public String hostRestaurantAccount(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantAccount()");

		service.account(req, model);
		
		return "host/host_restaurant/hostRestaurantAccount";
	}
}
