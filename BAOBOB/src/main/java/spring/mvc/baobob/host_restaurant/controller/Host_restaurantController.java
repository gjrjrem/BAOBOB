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

	// �Ĵ� �� ������ - ���� ����Ʈ
	@RequestMapping(value = "/hostRestaurantList")
	public String hostRestaurantList(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantList()");

		// ���� ����Ʈ
		service.restaurantList(req, model);

		return "host/host_restaurant/hostRestaurantList";
	}

	// �Ĵ� �� ������ - ���� �߰� ������
	@RequestMapping(value = "/hostRestaurantAddForm")
	public String hostRestaurantAddForm() {
		log.debug("hostRestaurantAddForm()");

		return "host/host_restaurant/hostRestaurantAddForm";
	}

	// �Ĵ� �� ������ - ���� �߰� ó��
	@RequestMapping(value = "/hostRestaurantAddPro")
	public String hostRestaurantAddPro(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantAddPro()");

		// ���� �߰� ó��
		service.restaurantAdd(req, model);

		return "host/host_restaurant/hostRestaurantAddPro";
	}

	// �Ĵ� �� ������ - ������ ���� ���� ��ȸ / ���� ������
	@RequestMapping(value = "/hostRestaurantModForm")
	public String hostRestaurantModForm(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantModForm()");

		// ������ ���� ���� ��ȸ
		service.restaurantView(req, model);

		return "host/host_restaurant/hostRestaurantModForm";
	}

	// �Ĵ� �� ������ - ���� ���� ���� ó��
	@RequestMapping(value = "/hostRestaurantModPro")
	public String hostRestaurantModPro(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantModPro()");

		// ���� ���� ó��
		service.restaurantMod(req, model);

		return "host/host_restaurant/hostRestaurantModPro";
	}

	// �Ĵ� �� ������ - ���� ���� ���� ó��
	@RequestMapping(value = "/hostRestaurantDel")
	public String hostRestaurantDel(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantDel()");

		// ���� ���� ó��
		service.restaurantDel(req, model);

		return "host/host_restaurant/hostRestaurantDel";
	}

	// �Ĵ� �� ������ - ��� �޴� ����Ʈ
	@RequestMapping(value = "/hostAllMenuList")
	public String hostAllMenuList(HttpServletRequest req, Model model) {
		log.debug("hostAllMenuList()");

		// ��� ������ ��� �޴� ��ȸ
		service.allMenuList(req, model);

		return "host/host_restaurant/hostAllMenuList";
	}

	// �Ĵ� �� ������ - ��� ���� ����Ʈ
	@RequestMapping(value = "/hostAllEmployeeList")
	public String hostAllEmployeeList(HttpServletRequest req, Model model) {
		log.debug("hostAllEmployeeList()");

		// ��� ������ ��� ���� ��ȸ
		service.allEmployeeList(req, model);

		return "host/host_restaurant/hostAllEmployeeList";
	}

	// �Ĵ� �� ������ - �Ĵ纰 ��Ʈ
	@RequestMapping(value = "/hostAllAccountChart")
	public String hostAllAccountChart(HttpServletRequest req, Model model) {
		log.debug("hostAllAccountChart()");

		// �Ĵ纰 ��Ʈ
		service.allAccountChart(req, model);

		return "host/host_restaurant/hostAllAccountChart";
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	// �Ĵ纰 �޴� ����Ʈ
	@RequestMapping(value = "/hostMenuList")
	public String hostMenuList(HttpServletRequest req, Model model) {
		log.debug("hostMenuList()");

		// �޴� ����Ʈ
		service.menuList(req, model);

		return "host/host_restaurant/hostMenuList";
	}

	// �޴� �߰� ������
	@RequestMapping(value = "/hostMenuAddForm")
	public String hostMenuAddForm() {
		log.debug("hostMenuAddForm()");

		return "host/host_restaurant/hostMenuAddForm";
	}

	// �޴� �߰� ó��
	@RequestMapping(value = "/hostMenuAddPro")
	public String hostMenuAddPro(MultipartHttpServletRequest req, Model model) {
		log.debug("hostMenuAddPro()");

		// �޴� �߰� ó��
		service.menuAdd(req, model);

		return "host/host_restaurant/hostMenuAddPro";
	}

	// ������ �޴� ���� ��ȸ / ���� ������
	@RequestMapping(value = "/hostMenuModForm")
	public String hostMenuModForm(HttpServletRequest req, Model model) {
		log.debug("hostMenuModForm()");

		// �޴� �� ���� ��ȸ
		service.menuView(req, model);

		return "host/host_restaurant/hostMenuModForm";
	}

	// �޴� ���� ó��
	@RequestMapping(value = "/hostMenuModPro")
	public String hostMenuModPro(MultipartHttpServletRequest req, Model model) {
		log.debug("hostMenuModPro()");

		// �޴� ���� ó��
		service.menuMod(req, model);

		return "host/host_restaurant/hostMenuModPro";
	}

	// �޴� ���� ó��
	@RequestMapping(value = "/hostMenuDel")
	public String hostMenuDel(HttpServletRequest req, Model model) {
		log.debug("hostMenuDel()");

		// �޴� ���� ó��
		service.menuDel(req, model);

		return "host/host_restaurant/hostMenuDel";
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	// ���� ����Ʈ
	@RequestMapping(value = "/hostEmployeeList")
	public String hostEmployeeList(HttpServletRequest req, Model model) {
		log.debug("hostEmployeeList()");

		// ���� ����Ʈ
		service.employeeList(req, model);

		return "host/host_restaurant/hostEmployeeList";
	}

	// �������� ��� ������ ȸ�� ����Ʈ
	@RequestMapping(value = "/hostMemberList")
	public String hostMemberList(HttpServletRequest req, Model model) {
		log.debug("hostMemberList()");

		// �������� ��� ������ ȸ�� ����Ʈ
		service.memberList(req, model);

		return "host/host_restaurant/hostMemberList";
	}

	// �������� ����� ȸ�� ���� ��ȸ / ���� ��� ������
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

		// ���� ��� ó��
		service.employeeAdd(req, model);

		return "host/host_restaurant/hostEmployeeAddPro";
	}

	// ���� ���� ��ȸ / ���� ���� ������
	@RequestMapping(value = "/hostEmployeeView")
	public String hostEmployeeView(HttpServletRequest req, Model model) {
		log.debug("hostEmployeeView()");

		// ���� ���� ��ȸ(ȸ�� ���� + ���� ����)
		service.memberView(req, model);
		service.employeeView(req, model);

		return "host/host_restaurant/hostEmployeeView";
	}

	// ���� ���� ó��
	@RequestMapping(value = "/hostEmployeeDel")
	public String hostEmployeeDel(HttpServletRequest req, Model model) {
		log.debug("hostEmployeeDel()");

		// ���� ���� ó��
		service.employeeDel(req, model);

		return "host/host_restaurant/hostEmployeeDel";
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	// ���� ����Ʈ
	@RequestMapping(value = "/hostReservList")
	public String hostReservList(HttpServletRequest req, Model model) {
		log.debug("hostReservList()");

		// ���� ����Ʈ
		service.reservList(req, model);

		return "host/host_restaurant/hostReservList";
	}

	// ���� �߰� ������
	@RequestMapping(value = "/hostReservAddForm")
	public String hostReservAddForm(HttpServletRequest req, Model model) {
		log.debug("hostReservAddForm()");

		return "host/host_restaurant/hostReservAddForm";
	}

	// ���� - ���̺� ������ ���� ���� ���� ��ȸ
	@RequestMapping(value = "checkPosRestaurant")
	public String checkPosRestaurant(HttpServletRequest req, Model model) {
		log.debug("checkPosRestaurant()");

		// ���̺� ������ ���� ���� ���� ��ȸ
		service.restaurantView(req, model);

		// ���̺� ���� show & hide�� ���� ��
		model.addAttribute("confirm", 1);

		return "host/host_restaurant/hostReservAddForm";
	}

	// ���� �߰� ó��
	@RequestMapping(value = "/hostReservAddPro")
	public String hostReservAddPro(HttpServletRequest req, Model model) {
		log.debug("hostReservAddPro()");

		// ���� �߰� ó��
		service.reservAdd(req, model);

		return "host/host_restaurant/hostReservAddPro";
	}

	// ���� ��ȸ ������
	@RequestMapping(value = "/reservView")
	public String hostScheduleSearch(HttpServletRequest req, Model model) {
		log.debug("reservView()");

		// ������ ��¥�� �ִ� ��� ���� ��ȸ
		service.reservView(req, model);

		return "host/host_restaurant/hostReservList";
	}

	// ���� �� ����
	@RequestMapping(value = "/restaurantView")
	public @ResponseBody TableVO restaurantView(HttpServletRequest req, Model model) {
		log.debug("restaurantView()");

		// ������ ��¥�� ����ִ� ������ �� ���� ��ȸ
		TableVO dto = service.restaurantView2(req, model);

		return dto;
	}

	// ���� ���� ó��
	@RequestMapping(value = "/hostReservDel")
	public String hostReservDel(HttpServletRequest req, Model model) {
		log.debug("hostReservDel()");

		// ���� ��� ó��
		service.reservDel(req, model);

		return "host/host_restaurant/hostReservDel";
	}

	// �ֹ� �߰� ������
	@RequestMapping(value = "/hostOrderAddForm")
	public String hostOrderAddForm(HttpServletRequest req, Model model) {
		log.debug("hostOrderAddForm()");

		// �ֹ��� �� �ִ� ������ ���̺�, ���̺� �ֹ��� ���� ��ȸ
		service.useTableView(req, model);

		// �ֹ� ���� �� �ִ� �޴� ����Ʈ ��ȸ
		service.menuList(req, model);

		return "host/host_restaurant/hostOrderAddForm";
	}

	// �ֹ� �߰� ó��
	@RequestMapping(value = "/hostOrderAddPro")
	public String hostOrderAddPro(HttpServletRequest req, Model model) {
		log.debug("hostOrderAddPro()");

		// �ֹ� �߰� ó��
		service.orderAdd(req, model);

		return "host/host_restaurant/hostOrderAddPro";
	}

	// �ֹ� ���� ó��
	@RequestMapping(value = "/hostOrderDel")
	public String hostOrderDel(HttpServletRequest req, Model model) {
		log.debug("hostOrderDel()");

		// �ֹ� ���� ó��
		service.orderDel(req, model);

		return "host/host_restaurant/hostOrderDel";
	}

	// ���̺� ���
	@RequestMapping(value = "/hostPayment")
	public String hostPayment(HttpServletRequest req, Model model) {
		log.debug("hostPayment()");

		// ���̺� ���(���� ó��)
		service.payment(req, model);

		return "host/host_restaurant/hostPayment";
	}

	// ���
	@RequestMapping(value = "/hostRestaurantAccount")
	public String hostRestaurantAccount(HttpServletRequest req, Model model) {
		log.debug("hostRestaurantAccount()");

		service.accountChart(req, model); // �޴��� ��Ʈ, �ݿ� ����
		service.accountChart2(req, model); // ���� ��Ʈ

		return "host/host_restaurant/hostRestaurantAccount";
	}
}
