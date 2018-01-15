package spring.mvc.baobob.guest_restaurant.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.baobob.guest_restaurant.service.Guest_restaurantService;

@Controller
public class Guest_restaurantController {
	
	@Autowired 
	Guest_restaurantService service;
	
	//private Logger log = Logger.getLogger(this.getClass());

	//==========================================================================
	//=============================== ������� ����  ===============================
	//==========================================================================
	//������� ���� ������(������� ����)review_date
	@RequestMapping("guestRestaurantList")
	public String guestRestaurantList(HttpServletRequest req, Model model) {
		//log.debug("===== Guest_restaurantController/guestRestaurantList() =====");
		System.out.println("/����ȭ��");
		//���� �ҷ�����
	
		return "guest/guest_restaurant/guestRestaurantList";
	}
	
	//==========================================================================
	//===============================  ������� ����  ==============================
	//==========================================================================
	//������� ����������
	@RequestMapping("guestRestaurantMain")//response
	public String guestRestaurantMain(HttpServletRequest req, Model model) {
		//log.debug("===== Guest_restaurantController/guestRestaurantMain() =====");
		System.out.println("/guestRestaurantMain");

		//�Ĵ� ������ �ҷ��;���
		//service.restaurant_imfomation(req, model);
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		System.out.println("restaurant_index : "+ restaurant_index);
		model.addAttribute("restaurant_index", restaurant_index);
		return "guest/guest_restaurant/guestRestaurantMain";
	}
		
	
	//==========================================================================
	//============================== 1. ������� ���� ==============================
	//==========================================================================
	//1. ������� ����
	@RequestMapping("guestRestaurant_info")
	public String guestRestaurant_info(HttpServletRequest req, Model model) {
		//log.debug("===== Guest_restaurantController/guestRestaurantMain() =====");

		
		return "guest/guest_restaurant/info/guestRestaurant_info";
	}
	
	//==========================================================================
	//============================== 2. ������� �޴� ==============================
	//==========================================================================
	//2. ������� �޴�-���̺� restaurant_menu_tbl
	@RequestMapping("guestRestaurant_menu")
	public String guestRestaurantMenu(HttpServletRequest req, Model model) {
		//log.debug("===== Guest_restaurantController/guestRestaurantMain() =====");

		service.restaurant_menuInfo(req, model);
		
		return "guest/guest_restaurant/menu/guestRestaurant_menu";
	}
	
	
	
	
	//==========================================================================
	//============================== 3. ������� ���� ==============================
	//==========================================================================
	//3-1. ������� ����ȭ��-1(�ο�����/���̺�/��¥)
	@RequestMapping("guestRestaurant_reserv")
	public String guestRestaurantReserv(HttpServletRequest req, Model model) {
		//log.debug("===== Guest_restaurantController/guestRestaurantMain() =====");

		
		return "guest/guest_restaurant/reserv/guestRestaurant_reserv";
	}
	
	


	
	//==========================================================================
	//============================== 4. ������� ���� ==============================
	//==========================================================================
	//4-1. ������� ����
	@RequestMapping("guestRestaurant_review")
	public String guestRestaurant_review(HttpServletRequest req, Model model) {
		System.out.println("===== ����ȭ�� - guestRestaurant_review =====");	
		//log.debug("===== Guest_restaurantController/guestRestaurantMain() =====");
		
		service.reviewList(req, model);
		
		return "guest/guest_restaurant/review/guestRestaurant_review";
	}
	
	//4-2. ���� �ۼ�
	@RequestMapping("Restaurant_reviewWrite")
	public String Restaurant_reviewWrite(HttpServletRequest req, Model model) {
		
		System.out.println("reviewWriter����");
		service.reviewWrite(req, model);
		System.out.println("reviewWriter����");
		service.reviewList(req, model);

		return "guest/guest_restaurant/review/guestRestaurant_review";
	}
	
	
	//���� mvcBoard
	//4-3-1. ���� ����(��й�ȣ Ȯ��)
	@RequestMapping("Restaurant_reviewModifyForm")
	public String Restaurant_reviewModifyForm(HttpServletRequest req, Model	model) {
		System.out.println("===== �������1 - Restaurant_reviewModifyForm =====");
		int review_index = Integer.parseInt(req.getParameter("review_index"));
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		
		System.out.println("======���� ���� ======");
		System.out.println("review_index : "+ review_index);
		System.out.println("pageNum : " + pageNum);
		model.addAttribute("review_index", review_index);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
		
		return "guest/guest_restaurant/review/guestRestaurant_reviewModifyForm";
	}
	
	//4-3-2. ���� ����(����ȭ��)
	@RequestMapping("Restaurant_reviweModifyView")
	public String Restaurant_reviweModifyView(HttpServletRequest req, Model	model) {
		System.out.println("===== �������2 - Restaurant_reviweModifyView =====");
	
		System.out.println("4-3-2. ���� ��");
		service.review_modifyView(req, model);
		System.out.println("4-3-2. ���� ��");
 
		return "guest/guest_restaurant/review/guestRestaurant_reviewModifyView";
	}		
	
	//4-3-3. ���� ����(���� ó��)
	@RequestMapping("Restaurant_revieModifyPro")
	public String Restaurant_revieModifyPro(HttpServletRequest req, Model	model) {
		System.out.println("===== �������3 - Restaurant_revieModifyPro =====");
		
		System.out.println("4-3-3. ������");
		service.review_modeifyPro(req, model);
		System.out.println("4-3-3. ������");
		service.reviewList(req, model);
		
		return "guest/guest_restaurant/review/guestRestaurant_review";
	}
			
	
	
	
	
	
	//4-4-1. ���� ����
	@RequestMapping("Restaurant_reviewDeleteForm")
	public String Restaurant_reviewDeleteForm(HttpServletRequest req, Model	model) {
		System.out.println("===== ���� ����1 - Restaurant_reviewModifyForm =====");
		int review_index = Integer.parseInt(req.getParameter("review_index"));
		int restaurant_index = Integer.parseInt(req.getParameter("restaurant_index"));
		int pageNum=Integer.parseInt(req.getParameter("pageNum"));
		
		System.out.println("======4-4-1. ���� ���� ======");
		System.out.println("review_index : "+ review_index);
		System.out.println("pageNum : " + pageNum);
		model.addAttribute("review_index", review_index);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("restaurant_index", restaurant_index);
		
		return "guest/guest_restaurant/review/guestRestaurant_reviewDeleteForm";
	}
		
	//4-4-2. ���� ����
	@RequestMapping("Restaurant_reviewDeletePro")
	public String Restaurant_reviewDelete(HttpServletRequest req, Model	model) {
		System.out.println("===== ���� ���� - Restaurant_reviewDelete =====");

		System.out.println("===== 4-3-2. ���� reviewDelete���� =====");
		service.reviewDeletePro(req, model);
		System.out.println("===== 4-3-2. ���� reviewDelete���� =====");
		//service.reviewList(req, model);

		return "guest/guest_restaurant/review/guestRestaurant_reviewDeletePro";
	}
	
	
}
