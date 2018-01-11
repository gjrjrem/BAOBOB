package spring.mvc.baobob.host_parking.persistence;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.Parking;
import spring.mvc.baobob.vo.ParkingSpace;

@Repository
public class Host_parkingDAOImpl implements Host_parkingDAO {

	@Autowired
	SqlSession sqlSession;

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
		int idxChk = 0;

		int cnt = 0;
		if(idxChk != 0) {
			parkingUpdate(space);
		} else {
			Host_parkingDAO mapper = sqlSession.getMapper(Host_parkingDAO.class);
			cnt = mapper.parkingChange(space);
		}
				
		return cnt;
	}
	
	//������ �� ���� ���� ����
	@Override
	public int parkingUpdate(Parking space) {
		int cnt = 0;
		return cnt;
	}
	
}
