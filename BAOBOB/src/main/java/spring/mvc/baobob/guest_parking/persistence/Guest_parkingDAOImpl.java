package spring.mvc.baobob.guest_parking.persistence;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.ParkingHistory;

@Repository
public class Guest_parkingDAOImpl implements Guest_parkingDAO {

	@Autowired
	SqlSession sqlSession;

	//������ ���� -1) HISTORY ���� Ȯ��
	@Override
	public String historyDateCheck(String member_id) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		String chk = mapper.historyDateCheck(member_id);
		return chk;
	}

	//������ ����-2) HISTORY ���
	@Override
	public int historyInsert(String member_id) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		int cnt = mapper.historyInsert(member_id);
		return cnt;
	}
	
	//������ ���� -3) ���� ���
	public int parkInHistoryInsert(Map<String, Object> map) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		int cnt = mapper.parkInHistoryInsert(map);
		return cnt;
	}

	//���� ��ȣ Ȯ��
	@Override
	public int parkingOutKeyCheck(String key) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		int cnt = mapper.parkingOutKeyCheck(key);
		return cnt;
	}

	//���� - ī�� ��ϵ� ȸ�� ����
	@Override
	public int parkingOutMemberCheck(String key) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		int cnt = mapper.parkingOutMemberCheck(key);
		return cnt;
	}
	
	//���� �ð�
	@Override
	public Timestamp getParkingInTime(String key) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		Timestamp time = mapper.getParkingInTime(key);
		return time;
	}

	//��ȭ �Ǽ�
	public int getMovieHistoryCount(String key) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		int cnt = mapper.getMovieHistoryCount(key);
		return cnt;
	}
	
	//�Ĵ� �Ǽ�
	public int getRestaurantHistoryCount(String key) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		int cnt = mapper.getMovieHistoryCount(key);
		return cnt;
	}
	
	//����
	@Override
	public int parkingHistoryUpdate(Map<String, Object> map) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		int cnt = mapper.parkingHistoryUpdate(map);
		return cnt;
	}

	//�ش� ���� ����
	@Override
	public ParkingHistory getParkingHistory(String key) {
		Guest_parkingDAO mapper = sqlSession.getMapper(Guest_parkingDAO.class);
		ParkingHistory ph = mapper.getParkingHistory(key);
		return ph;
	}

}
