package spring.mvc.baobob.guest_parking.persistence;

import java.sql.Timestamp;
import java.util.Map;

import spring.mvc.baobob.vo.ParkingHistory;

public interface Guest_parkingDAO {

	//������ ���� -1) HISTORY ���� Ȯ��
	public String historyDateCheck(String member_id);
	
	//������ ����-2) HISTORY ���
	public int historyInsert(String member_id);
	
	//������ ���� -3) ���� ���
	public int parkInHistoryInsert(Map<String, Object> map);
	
	//���� ��ȣ Ȯ��
	public int parkingOutKeyCheck(String key);
	
	//���� - ī�� ��ϵ� ȸ�� ����
	public int parkingOutMemberCheck(String key);
	
	//���� �ð�
	public Timestamp getParkingInTime(String key);
	
	//��ȭ �Ǽ�
	public int getMovieHistoryCount(String key);
	
	//�Ĵ� �Ǽ�
	public int getRestaurantHistoryCount(String key);
	
	//����
	public int parkingHistoryUpdate(Map<String, Object> map);
	
	//�ش� ���� ����
	public ParkingHistory getParkingHistory(String key);
}
