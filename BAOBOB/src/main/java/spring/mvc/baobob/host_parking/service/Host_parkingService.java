package spring.mvc.baobob.host_parking.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Host_parkingService {

	//������ ����
	public void hostParkingMain(HttpServletRequest req, Model model);
	
	//���� ajax. ���� ���� ��ȭ
	public void hostParkingMainSpace(HttpServletRequest req, Model model);
	
	//������ ���� ����
	public void getParkingSpace(HttpServletRequest req, Model model);
	
	//������ ���� ���� ���/����
	public void parkingSpaceChange(HttpServletRequest req, Model model);
	
	//�ش� ���� ���� ����
	public void getSpaceState(HttpServletRequest req, Model model);
	
	//������ �ǽð� ����
	public void getParkingSpaceState(HttpServletRequest req, Model model);
	
	//���� ��Ȳ  ��Ʈ
	public void getHostParkingChart(HttpServletRequest req, Model model);
	//���� ����
	/*public void getParkingHistory(HttpServletRequest req, Model model);*/
	
	//���� ��Ȳ  ��Ʈ - ajax ����
	public void getHostParkingChartMonth(HttpServletRequest req, Model model);
	
	//���� ����(������ ������)
	public void getParkingPayList(HttpServletRequest req, Model model);
	
	//���� ��Ȳ
	public void getParkingPayChart(HttpServletRequest req, Model model);
	
	//�Ƶ��̳�
	public void arduinoInput(HttpServletRequest req, Model model);
	
}
