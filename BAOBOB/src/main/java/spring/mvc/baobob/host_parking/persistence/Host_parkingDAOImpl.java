package spring.mvc.baobob.host_parking.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.Parking;
import spring.mvc.baobob.vo.ParkingFee;
import spring.mvc.baobob.vo.ParkingHistory;
import spring.mvc.baobob.vo.ParkingSpace;

@Repository
public class Host_parkingDAOImpl implements Host_parkingDAO {

	@Autowired
	SqlSession sqlSession;

	// ���� �̿��� �� 
	public int getParkingMember() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.getParkingMember();
		return cnt;
	}

	//��� ���� �ð�
	public List<Map<String, Object>> getAvgPakingTime(){
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		List<Map<String, Object>> list = mapper.getAvgPakingTime();
		return list;
	}

	// ���� ��ȭ �̿��� �� 
	public int getParkingMovieMember() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.getParkingMovieMember();
		return cnt;
	}
	
	// ���� �Ĵ� �̿��� �� 
	public int getParkingRestaurantMember() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.getParkingMovieMember();
		return cnt;
	}

	//ajax ���� ��ȭ
	public ArrayList<Parking> getParkChageState() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		ArrayList<Parking> ps = mapper.getParkChageState();
		return ps;
	}
	
	//������ ���� ����, ���� ����
	@Override
	public ParkingSpace getParkingSpace() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		ParkingSpace ps = mapper.getParkingSpace();
		return ps;
	}

	//������ ���� ���� ���
	@Override
	public int parkingSpaceChange(ParkingSpace ps) {
		ParkingSpace tmp = getParkingSpace();
		
		int cnt = 0;
		if(tmp != null) {
			cnt = parkingSpaceUpdate(ps);
		} else {
			Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
			cnt = mapper.parkingSpaceChange(ps);
		}
		return cnt;
	}

	//������ ���� ���� ����
	@Override
	public int parkingSpaceUpdate(ParkingSpace ps) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.parkingSpaceUpdate(ps);
		return cnt;
	}

	//������ �ش� ���� ����
	public Parking getParking(int park_index) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		Parking p = mapper.getParking(park_index);
		return p;
	}
	
	//������ �� ���� ���� ���
	@Override
	public int parkingChange(Parking space) {
		//�ش� ���� ���� ���� ����
		Parking p = getParking(space.getPark_index());

		int cnt = 0;
		if(p != null) {
			space.setPark_last_date(p.getPark_last_date());
			parkingUpdate(space);
			cnt = 1;
		} else {
			Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
			cnt = mapper.parkingChange(space);
		}
		return cnt;
	}
	
	//������ �� ���� ���� ����
	@Override
	public int parkingUpdate(Parking space) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.parkingUpdate(space);
		return cnt;
	}

	//������ ���� ���� ����
	@Override
	public ArrayList<String> getParkingStates(int last_idx) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		ArrayList<String> list = mapper.getParkingStates(last_idx);
		return list;
	}

	//���� �⺻ ��� ���� Ȯ��
	@Override
	public ParkingFee getParkingFee() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		ParkingFee pf =  mapper.getParkingFee();
		return pf;
	}
	
	//���� �⺻ ��� ���� ����
	@Override
	public int getParkingFeeCount() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.getParkingFeeCount();
		return cnt;
	}

	//���� �⺻ ��� ���
	@Override
	public int parkingFeeChange(ParkingFee pf) {
		int count = getParkingFeeCount();
		
		int cnt = 0;
		if(count == 0) {
			Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
			cnt = mapper.parkingFeeChange(pf);
		} else {
			parkingFeeUpdate(pf);
			cnt = 1;
		}
		return cnt;
	}

	//���� �⺻ ��� ����
	@Override
	public int parkingFeeUpdate(ParkingFee pf) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.parkingFeeUpdate(pf);
		return cnt;
	}

	//���� ��Ʈ - ���� �ð��� �̿��ڼ�
	public List<Map<String, Object>> getDayInTimeAvg() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		List<Map<String, Object>> list = mapper.getDayInTimeAvg();
		return list;
	}
	
	//���� ��Ʈ - ���� �ð��� �̿��ڼ�
	public List<Map<String, Object>> getDayOutTimeAvg() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		List<Map<String, Object>> list = mapper.getDayOutTimeAvg();
		return list;
	}

	//���� ��Ʈ - ���Ϻ� ���� ��
	public List<Map<String, Object>> getWeekIn(){
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		List<Map<String, Object>> list = mapper.getWeekIn();
		return list;
	}

	//���� ��Ʈ - ���Ϻ� ���� ��
	public List<Map<String, Object>> getWeekOut() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		List<Map<String, Object>> list = mapper.getWeekOut();
		return list;
	}
	

	//���� ��Ʈ - ���� ���� ��
	public List<Map<String, Object>> getMonthIn(){
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		List<Map<String, Object>> list = mapper.getMonthIn();
		return list;
	}

	//���� ��Ʈ - ���� ���� ��
	public List<Map<String, Object>> getMonthOut(){
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		List<Map<String, Object>> list = mapper.getMonthIn();
		return list;
	}
	
	//���� ���� �Ѱ���
	/*@Override
	public int getParkingHistoryCnt() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.getParkingHistoryCnt();;
		return cnt;
	}*/

	//���� ����
	/*@Override
	public ArrayList<ParkingHistory> getParkingHistory(Map<String, Integer> map) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		ArrayList<ParkingHistory> list = mapper.getParkingHistory(map);
		return list;
	}*/

	//���� ���� �Ѱ���
	public int getParkingPayCnt() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		return mapper.getParkingPayCnt();
	}
	
	//���� ����
	@Override
	public ArrayList<ParkingHistory> getParkingPayList(Map<String, Object> map) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		ArrayList<ParkingHistory> list = mapper.getParkingPayList(map);
		return list;
	}

	//�ش� ���� ���� ����
	@Override
	public Parking getSpaceState(int park_index) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		Parking space = mapper.getSpaceState(park_index);
		return space;
	}

	//�ش� ���� ������ ������ �����
	public ArrayList<String> getParkLastDateMember(Timestamp park_last_date) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		ArrayList<String> lasts = mapper.getParkLastDateMember(park_last_date);
		return lasts;
	}

	//���� ���� ����
	public ArrayList<ParkingHistory> getThisYearPayList() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		ArrayList<ParkingHistory> list = mapper.getThisYearPayList();
		return list;
	}

	//������
	@Override
	public List<Map<String, Object>> getParkingSexRatio() {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		List<Map<String, Object>> list = mapper.getParkingSexRatio(); 
		return list;
	}
	

	//�Ƶ��̳�. ���� ���� ���� ����
	@Override
	public int arduinoInput(Map<String, Object> map) {
		Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
		int cnt = mapper.arduinoInput(map);
		return cnt;
	}
}
