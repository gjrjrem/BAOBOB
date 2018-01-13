package spring.mvc.baobob.host_parking.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Host_parkingService {

	//������ ���� ����
	public void getParkingSpace(HttpServletRequest req, Model model);
	
	//������ ���� ���� ���/����
	public void parkingSpaceChange(HttpServletRequest req, Model model);
	
	//�ش� ���� ���� ����
	public void getSpaceState(HttpServletRequest req, Model model);
	
	//������ �ǽð� ��Ȳ
	public void getParkingSpaceState(HttpServletRequest req, Model model);
	
	//���� ����
	public void getParkingHistory(HttpServletRequest req, Model model);
	
	//���� ����(������ ������)
	public void getParkingPayList(HttpServletRequest req, Model model);
}
