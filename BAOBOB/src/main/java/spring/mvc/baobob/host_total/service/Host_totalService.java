package spring.mvc.baobob.host_total.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface Host_totalService {
	
	//ȸ�� ����Ʈ
	public void memList(HttpServletRequest req, Model model);
	
	//ȸ�� �߰�
	public void memAddPro(HttpServletRequest req, Model model);
	
	//���̵� �ߺ��˻�
	public void confirmId(HttpServletRequest req, Model model);
	
	//ȸ�� ����, ������������
	public void hostTMemView(HttpServletRequest req, Model model);
	
	//ȸ�� ���� ����ó��
	public void hostTMemModifyPro(HttpServletRequest req, Model model);
	
	//ȸ������ ó��������
	public void hostTMemDelPro(HttpServletRequest req, Model model);
	
	//��ȭ�� ���������
	public void movieChart(HttpServletRequest req, Model model);
	
	//��ȭ�� ���������
	public void restaurantChart(HttpServletRequest req, Model model);
	
	//������ ���������
	public void getParkingPayChart(HttpServletRequest req, Model model);
}
