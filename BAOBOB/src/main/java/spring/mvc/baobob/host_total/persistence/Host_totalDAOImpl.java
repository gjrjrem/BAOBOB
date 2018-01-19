package spring.mvc.baobob.host_total.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.member_mypage.persistence.Member_mypageDAO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.ParkingFee;
import spring.mvc.baobob.vo.ParkingHistory;
import spring.mvc.baobob.vo.hostTChartVO;

@Repository
public class Host_totalDAOImpl implements Host_totalDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
/*----------------------------------------------------------------------------*/
	
	//ȸ���� ���ϱ�
	public int getMemCnt() {
		int cnt = 0;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		cnt = dao.getMemCnt();
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ�� ��� ���ϱ�
	public ArrayList<Member> getMemList(Map<String, Integer> map){
		ArrayList<Member> dtos = null;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		dtos = dao.getMemList(map);
		
		return dtos;
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ�� �߰�
	public int insertMember(Member vo) {
		int cnt = 0;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		cnt = dao.insertMember(vo);
				
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	// ���̵� �ߺ� Ȯ��
	@Override
	public int confirmId(String id) {
		int cnt = 0;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		cnt = dao.confirmId(id);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ������ ��������
	public Member getMemberInfo(String strId) {
		Member vo = new Member();
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		vo = dao.getMemberInfo(strId);
		
		return vo;
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ������ ���� ó��
	public int hostTMemModifyPro(Member vo) {
		int cnt = 0;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		cnt = dao.hostTMemModifyPro(vo);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ������ ó��
	public int hostTMemDelPro(String strId) {
		int cnt = 0;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		cnt = dao.hostTMemDelPro(strId);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//��ȭ�� ��� �Ѿ� ���ϱ�
	public int getMovieSale() {
		int cnt = 0;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		cnt = dao.getMovieSale();
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//��ȭ�� ���������(íƮ)
	public List<hostTChartVO> getMovieChart(){
		List<hostTChartVO> voList = null;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		voList =  dao.getMovieChart();
		
		return voList;
	}
	
/*----------------------------------------------------------------------------*/
	
	//�Ĵ� ��� �Ѿ� ���ϱ�
	public int getRestaurantSale() {
		int cnt = 0;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		cnt = dao.getRestaurantSale();
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//�Ĵ� ���������(íƮ)
	public List<hostTChartVO> getRestaurantChart(){
		List<hostTChartVO> voList = null;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		voList = dao.getRestaurantChart();
		
		return voList;
	}
	
/*----------------------------------------------------------------------------*/

	//���� �⺻ ���
	public ParkingFee getParkingFee() {
		Host_totalDAO mapper = sqlSession.getMapper(Host_totalDAO.class);
		
		ParkingFee pf =  mapper.getParkingFee();
		
		return pf;
	}
	
/*----------------------------------------------------------------------------*/
	
	//���� ���� ����
	public ArrayList<ParkingHistory> getThisYearPayList(){
		Host_totalDAO mapper = sqlSession.getMapper(Host_totalDAO.class);
		
		ArrayList<ParkingHistory> list = mapper.getThisYearPayList();
		
		return list;
	}
	
	
	
	
	
	
	
	

}
