package spring.mvc.baobob.guest_parking.persistence;

import java.util.Map;

public interface Guest_parkingDAO {

	//������ ���� -1) HISTORY ���� Ȯ��
	public String historyDateCheck(String member_id);
	
	//������ ����-2) HISTORY ���
	public int historyInsert(String member_id);
	
	//������ ���� -3) ���� ���
	public int parkInHistoryInsert(Map<String, Object> map);
	
	//���� ��ȣ Ȯ��
	public int parkingOutKeyCheck(String key);
}
