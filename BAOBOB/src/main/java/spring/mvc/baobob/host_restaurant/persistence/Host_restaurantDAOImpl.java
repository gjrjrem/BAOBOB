package spring.mvc.baobob.host_restaurant.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.EmployeeVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MenuVO;

@Repository
public class Host_restaurantDAOImpl implements Host_restaurantDAO {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private SqlSession sqlSession;

	// �Ĵ� �� ������ - �Ĵ� �߰� ó��
	@Override
	public int addRestaurant(Map<String, Object> map) {
		// TODO Auto-generated method stub
		log.debug("dao.addRestaurant()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.addRestaurant(map);
	}

	// �Ĵ� �� ������ - �Ĵ� �� ���̺� �߰� ó��
	@Override
	public int addTable(Map<String, Object> map) {
		// TODO Auto-generated method stub
		log.debug("dao.addTable()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.addTable(map);
	}
	
	// �Ĵ�[1] �޴� ���� ��ȸ
	@Override
	public int getMenuCnt() {
		// TODO Auto-generated method stub
		log.debug("dao.getMenuCnt()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getMenuCnt();
	}

	// �Ĵ�[1] �� �޴� ���� ��ȸ
	@Override
	public ArrayList<MenuVO> getMenuList() {
		// TODO Auto-generated method stub
		log.debug("dao.getMenuList()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getMenuList();
	}
	
	// �Ĵ�[1] �޴� �߰� ó��
	@Override
	public int addMenu(MenuVO dto) {
		// TODO Auto-generated method stub
		log.debug("dao.addMenu()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.addMenu(dto);
	}

	// �Ĵ�[1] �޴� ���� ó��
	@Override
	public int modMenu(MenuVO dto) {
		// TODO Auto-generated method stub
		log.debug("dao.modMenu()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.modMenu(dto);
	}

	// �Ĵ�[1] �޴� ���� �󼼺���
	@Override
	public MenuVO viewMenu(int index) {
		// TODO Auto-generated method stub
		log.debug("dao.viewMenu()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.viewMenu(index);
	}
	
	// �Ĵ�[1] �޴� ���� ó��
	@Override
	public int delMenu(MenuVO dto) {
		// TODO Auto-generated method stub
		log.debug("dao.delMenu()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.delMenu(dto);
	}

	// �Ĵ�[1] ���� �� ��ȸ
	@Override
	public int getEmployeeCnt() {
		// TODO Auto-generated method stub
		log.debug("dao.getEmployeeCnt()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getEmployeeCnt();
	}

	// �Ĵ�[1] �� ���� ���� ��ȸ
	@Override
	public ArrayList<EmployeeVO> getEmployeeList() {
		// TODO Auto-generated method stub
		log.debug("dao.getEmployeeList()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getEmployeeList();
	}

	// ��ü ȸ�� �� ��ȸ(�Ĵ�[1] ���� ����)
	@Override
	public int getMemberCnt() {
		// TODO Auto-generated method stub
		log.debug("dao.getMemberCnt()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getMemberCnt();
	}

	// �� ȸ�� ���� ��ȸ(�Ĵ�[1] ���� ����)
	@Override
	public ArrayList<Member> getMemberList() {
		// TODO Auto-generated method stub
		log.debug("dao.getEmployeeList()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getMemberList();
	}

	// �Ĵ�[1] �������� ����� ȸ�� ���� ����
	@Override
	public Member viewMember(String id) {
		// TODO Auto-generated method stub
		log.debug("dao.viewMember()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.viewMember(id);
	}
	
	// �Ĵ�[1] ���� ��� ó��(member_step ����)
	@Override
	public int updateStep(Map<String, Object> map) {
		// TODO Auto-generated method stub
		log.debug("dao.updateStep()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.updateStep(map);
	}

	// �Ĵ�[1] ���� ��� ó��
	@Override
	public int addEmployee(Map<String, Object> map) {
		// TODO Auto-generated method stub
		log.debug("dao.addEmployee()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
				
		return dao.addEmployee(map);
	}

	// �Ĵ�[1] ���� ���� ��ȸ
	@Override
	public EmployeeVO viewEmployee(String id) {
		// TODO Auto-generated method stub
		log.debug("dao.viewMember()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.viewEmployee(id);
	}

	// �Ĵ�[1] ���� ���� ���� ó��(member_step ���)
	@Override
	public int getCumPoint(String id) {
		// TODO Auto-generated method stub
		log.debug("dao.getCumPoint()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getCumPoint(id);
	}

	// �Ĵ�[1] ���� ���� ����
	@Override
	public int delEmployee(Map<String, Object> map) {
		// TODO Auto-generated method stub
		log.debug("dao.delEmployee()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.delEmployee(map);
	}
}
