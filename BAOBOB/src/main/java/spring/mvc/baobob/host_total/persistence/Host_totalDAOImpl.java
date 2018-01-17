package spring.mvc.baobob.host_total.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.member_mypage.persistence.Member_mypageDAO;
import spring.mvc.baobob.vo.Member;

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
		System.out.println("���ٿ�");
		int cnt = 0;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		cnt = dao.getMovieSale();
		
		System.out.println("�Ѿ�:" + cnt);
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//��ȭ�� ���������(íƮ)
	public Map<String,Integer> getMovieChart(){
		Map<String,Integer> m = null;
		
		Host_totalDAO dao = sqlSession.getMapper(Host_totalDAO.class);
		
		m = new HashMap<String,Integer>();
		m.put("janre1",0);
		m.put("janre2",0);
		m.put("janre3",0);
		m.put("janre4",0);
		m.put("janre5",0);
		m.put("janre6",0);
		m.put("janre7",0);
		m.put("janre8",0);
		m.put("janre9",0);
		m.put("janre10",0);
		
		m = dao.getMovieChart();
		
		System.out.println("íƮ:" + m);
		return m;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
