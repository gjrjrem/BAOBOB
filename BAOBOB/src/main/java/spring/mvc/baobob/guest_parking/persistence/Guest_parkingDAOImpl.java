package spring.mvc.baobob.guest_parking.persistence;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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

}
