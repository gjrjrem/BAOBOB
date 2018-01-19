package spring.mvc.baobob.host_total.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import spring.mvc.baobob.host_total.service.Host_totalService;

@Controller
public class Host_totalController {
	
	@Autowired
	Host_totalService service;
	
	private Logger log = Logger.getLogger(this.getClass());
	
	//������������ ��Ʈ��Ʈ��
	@RequestMapping("adminDefault")
	public String adminDefault(Model model) {
		
		log.debug("====== host_total.controller/adminDefault ======");
		
		return "adminDefault";
	}
	
	//���հ����� ����
	@RequestMapping("hostTMain")
	public String hostTMain(HttpServletRequest req, Model model) {
		
		log.debug("====== host_total.controller/hostTMain() ======");
		
		//��ȭ�� ��� íƮ
		service.movieChart(req, model);
		//�Ĵ� ��� íƮ
		service.restaurantChart(req, model);
		//������ ��� íƮ
		service.getParkingPayChart(req, model);
		
		return "host/host_total/hostTMain";
	}
	
	//���հ����� ȸ��,���� ���
	@RequestMapping("hostTMemList")
	public String hostTMemList(HttpServletRequest req, Model model) {
		
		service.memList(req,model);
		
		return "host/host_total/hostTMemList";
	}
	
	//���հ����� ȸ��,���� �߰�
	@RequestMapping("hostTMemAdd")
	public String hostTMemAdd(HttpServletRequest req, Model model) {
		
		service.memList(req,model);
		
		return "host/host_total/hostTMemAdd";
	}
	
	//���̵� �ߺ� �˻�
	@RequestMapping("hostTConfirmId")
	public String confirmId(HttpServletRequest req, Model model) {
		System.out.println("hostTConfirmId()");
		service.confirmId(req, model);
		
		return "host/host_total/cntPage";
	}
	
	//���հ����� ȸ��,���� �߰� ó��
	@RequestMapping("hostTMemAddPro")
	public String hostTMemAddPro(HttpServletRequest req, Model model) {
		
		service.memAddPro(req,model);
		
		return "host/host_total/hostTMemAddPro";
	}
	
	//ȸ�� ����,������������
	@RequestMapping("hostTMemView")
	public String hostTMemView(HttpServletRequest req, Model model) {
		
		service.hostTMemView(req,model);
		
		return "host/host_total/hostTMemView";
	}
	
	//�������� ó��������
	@RequestMapping("hostTMemModifyPro")
	public String hostTMemModifyPro(HttpServletRequest req, Model model) {
		
		service.hostTMemModifyPro(req, model);
		
		return "host/host_total/hostTMemModifyPro";
	}
	
	//ȸ�� ����
	//ȸ��Ż�� ó��������
	@RequestMapping("hostTMemDelPro")
	public String hostTMemDelPro(HttpServletRequest req, Model model) {
		
		service.hostTMemDelPro(req, model);
		
		return "host/host_total/hostTMemDelPro";
	}
	
	//��ȭ�� ���������
	@RequestMapping("hostTMovie")
	public String hostTMovie(HttpServletRequest req, Model model) {
		
		service.movieChart(req, model);
		
		return "host/host_total/hostTMovieChart";
	}
	
	//�Ĵ� ���������
	@RequestMapping("hostTRestaurant")
	public String hostTRestaurant(HttpServletRequest req, Model model) {
		
		service.restaurantChart(req, model);
		
		return "host/host_total/hostTRestaurantChart";
	}
	
	//������ ���������
	@RequestMapping("hostTParkingChart")
	public String hostTParkingChart(HttpServletRequest req, Model model) {
		
		service.getParkingPayChart(req, model);
		
		return "host/host_total/hostParkChart";
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
