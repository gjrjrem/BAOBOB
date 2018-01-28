package spring.mvc.baobob.host_total.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.ParkingFee;
import spring.mvc.baobob.vo.ParkingHistory;
import spring.mvc.baobob.vo.hostTChartVO;

public interface Host_totalDAO {
	
	//ȸ���� ���ϱ�
	public int getMemCnt();
	
	//ȸ�� ��� ���ϱ�
	public ArrayList<Member> getMemList(Map<String, Integer> map);
	
	//ȸ�� �߰�
	public int insertMember(Member vo);
	
	// ���̵� �ߺ� Ȯ��
	public int confirmId(String id);
	
	//ȸ������ ��������
	public Member getMemberInfo(String strId);
	
	//ȸ������ ���� ó��
	public int hostTMemModifyPro(Member vo);
	
	//ȸ������ ó��
	public int hostTMemDelPro(String strId);
	
	//��ȭ�� ��� �Ѿ� ���ϱ�
	public int getMovieSale();
	
	//��ȭ�� ���������(íƮ)
	public List<hostTChartVO> getMovieChart();
	
	//�Ĵ� ��� �Ѿ� ���ϱ�
	public int getRestaurantSale();
	
	//�Ĵ� ���������(íƮ)
	public List<hostTChartVO> getRestaurantChart();
	
	//���� �⺻ ���
	public ParkingFee getParkingFee();
	
	//���� ���� ����
	public ArrayList<ParkingHistory> getThisYearPayList();
		
}
