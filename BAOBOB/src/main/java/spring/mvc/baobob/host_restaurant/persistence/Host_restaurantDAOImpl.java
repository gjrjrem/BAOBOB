package spring.mvc.baobob.host_restaurant.persistence;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.EmployeeVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MenuVO;
import spring.mvc.baobob.vo.RestaurantVO;
import spring.mvc.baobob.vo.Restaurant_ChartVO;
import spring.mvc.baobob.vo.Restaurant_scheduleVO;
import spring.mvc.baobob.vo.TableVO;

@Repository
public class Host_restaurantDAOImpl implements Host_restaurantDAO {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private SqlSession sqlSession;

	// ���� �� ��ȸ
	@Override
	public int getRestaurantCnt() {
		log.debug("dao.getRestaurantCnt()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getRestaurantCnt();
	}

	// ��� ���� ����
	@Override
	public ArrayList<RestaurantVO> getRestaurantList() {
		log.debug("dao.getRestaurantList()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getRestaurantList();
	}

	// ���� �߰� ó��
	@Override
	public int addRestaurant(RestaurantVO dto) {
		log.debug("dao.addRestaurant()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.addRestaurant(dto);
	}

	// ���� �� ���̺� �߰� ó��
	@Override
	public int addTable(TableVO dto) {
		log.debug("dao.addTable()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.addTable(dto);
	}

	// ���� ���� ��ȸ
	@Override
	public RestaurantVO viewRestaurant(int restaurant_index) {
		log.debug("dao.viewRestaurant()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.viewRestaurant(restaurant_index);
	}

	// ������ �����ϴ� Ÿ���� �࿭ ��ȸ (��:5*5)
	@Override
	public TableVO getColRow(int restaurant_index) {
		log.debug("dao.getRowCol()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getColRow(restaurant_index);
	}

	// Ÿ�� �ϳ��ϳ��� ���� ��ȸ
	@Override
	public int getState(Map<String, Object> map) {

		log.debug("dao.getState()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getState(map);
	}

	// ���� ���� ó��
	@Override
	public int modRestaurant(RestaurantVO dto) {

		log.debug("dao.modRestaurant()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.modRestaurant(dto);
	}

	// ���� �� ���̺� ���� �ʱ�ȭ
	@Override
	public int resetTable(RestaurantVO dto) {

		log.debug("dao.resetTable()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.resetTable(dto);
	}

	// ���� �� ���̺� ���� ó��
	@Override
	public int modTable(Map<String, Object> map) {
		log.debug("dao.modTable()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.modTable(map);
	}

	// ���� ���� ó��
	@Override
	public int delRestaurant(RestaurantVO dto) {
		log.debug("dao.delRestaurant()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.delRestaurant(dto);
	}

	/////////////////////////////////////////////////////////////////////////////////////////

	// �޴� ���� ��ȸ
	@Override
	public int getMenuCnt(int restaurant_index) {
		log.debug("dao.getMenuCnt()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getMenuCnt(restaurant_index);
	}

	// �� �޴� ���� ��ȸ
	@Override
	public ArrayList<MenuVO> getMenuList(int restaurant_index) {
		log.debug("dao.getMenuList()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getMenuList(restaurant_index);
	}

	// �޴� �߰� �� �޴� �ε��� ���
	@Override
	public Integer getMenuIndex(int restaurant_index) {
		log.debug("dao.getMenuIndex()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		Integer restaurant_menu_index = dao.getMenuIndex(restaurant_index);
		
		if(restaurant_menu_index == null) {
			restaurant_menu_index = 1;
		} else {
			restaurant_menu_index++;
		}
				
		return restaurant_menu_index;
	}

	// �޴� �߰� ó��
	@Override
	public int addMenu(MenuVO dto) {
		log.debug("dao.addMenu()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.addMenu(dto);
	}

	// �޴� ���� ó��
	@Override
	public int modMenu(MenuVO dto) {
		log.debug("dao.modMenu()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.modMenu(dto);
	}

	// �޴� ���� ����ȸ
	@Override
	public MenuVO viewMenu(MenuVO dto) {
		log.debug("dao.viewMenu()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.viewMenu(dto);
	}

	// �޴� ���� ó��
	@Override
	public int delMenu(MenuVO dto) {

		log.debug("dao.delMenu()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.delMenu(dto);
	}

	// ���� �� ��ȸ
	@Override
	public int getEmployeeCnt(int member_step) {

		log.debug("dao.getEmployeeCnt()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getEmployeeCnt(member_step);
	}

	// �� ���� ���� ��ȸ
	@Override
	public ArrayList<EmployeeVO> getEmployeeList(int member_step) {
		log.debug("dao.getEmployeeList()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getEmployeeList(member_step);
	}

	// ��ü ȸ�� �� ��ȸ(���� ����)
	@Override
	public int getMemberCnt() {

		log.debug("dao.getMemberCnt()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getMemberCnt();
	}

	// �� ȸ�� ���� ��ȸ(���� ����)
	@Override
	public ArrayList<Member> getMemberList() {

		log.debug("dao.getEmployeeList()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getMemberList();
	}

	// �������� ����� ȸ�� ���� ��ȸ
	@Override
	public Member viewMember(String id) {
		log.debug("dao.viewMember()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.viewMember(id);
	}

	// ���� ���/���� ó��(member_step ����)
	@Override
	public int updateStep(Map<String, Object> map) {

		log.debug("dao.updateStep()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.updateStep(map);
	}

	// ���� ��� ó��
	@Override
	public int addEmployee(Map<String, Object> map) {

		log.debug("dao.addEmployee()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.addEmployee(map);
	}

	// ���� ���� ��ȸ
	@Override
	public EmployeeVO viewEmployee(String id) {
		log.debug("dao.viewMember()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.viewEmployee(id);
	}

	// ���� ���� ���� ó��(member_step ���)
	@Override
	public int getCumPoint(String id) {

		log.debug("dao.getCumPoint()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getCumPoint(id);
	}

	// ���� ���� ����
	@Override
	public int delEmployee(Map<String, Object> map) {

		log.debug("dao.delEmployee()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.delEmployee(map);
	}

	// ���� �߰�
	@Override
	public int addReserv(Map<String, Object> map) {
		log.debug("dao.addReserv()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.addReserv(map);
	}

	// ���� �� �ʱ�ȭ(���� ���� �� �����ϴ� ���)
	@Override
	public int resetTable2(Restaurant_scheduleVO dto) {

		log.debug("dao.resetTable2()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.resetTable2(dto);
	}

	// ���� ��¥ ���� ��ȸ
	@Override
	public int modTable2(Map<String, Object> map) {

		log.debug("dao.modTable2()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.modTable2(map);
	}

	// ��¥�� ���� ��ȸ
	@Override
	public ArrayList<Restaurant_scheduleVO> getReservList(Map<String, Object> map) {
		log.debug("dao.getReservList()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getReservList(map);
	}

	// ������ �ε��� ��ȸ
	@Override
	public Integer getScheduleIndex(Restaurant_scheduleVO dto) {

		log.debug("dao.getScheduleIndex()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getScheduleIndex(dto);
	}

	// �Ĵ� ����, �Ĵ� �ε��� üũ
	@Override
	public int[] getRestaurantIndex() {

		log.debug("dao.getRestaurantIndex()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getRestaurantIndex();
	}

	// ���� �ð�, ���� �ð� üũ
	@Override
	public Restaurant_scheduleVO getTime(Restaurant_scheduleVO dto) {
		log.debug("dao.getTime()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getTime(dto);
	}

	// �ֹ� �߰� ó��
	@Override
	public int addFoodHistory(Map<String, Object> map) {
		log.debug("dao.addFoodHistory()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.addFoodHistory(map);
	}

	// ȸ�� ���̵� �ִ���, �ִٸ� ������ ������ ��ȸ
	@Override
	public Integer confirmId(String id) {
		log.debug("dao.confirmId()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.confirmId(id);
	}

	// �̿� ���� �߰� ó��
	@Override
	public int addHistory(String id) {
		log.debug("dao.addHistory()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.addHistory(id);
	}

	// �̹� �ֹ��� �޴����� Ȯ��
	@Override
	public Integer getMenuCount(Map<String, Object> map) {
		log.debug("dao.getMenuCount()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getMenuCount(map);
	}

	// �ֹ��� �޴��� ��� ���� ����
	@Override
	public Integer modFoodHistory(Map<String, Object> map) {
		log.debug("dao.modFoodHistory()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.modFoodHistory(map);
	}

	// �ֹ� ��� ó��
	@Override
	public Integer delFoodHistory(Map<String, Object> map) {
		log.debug("dao.delFoodHistory()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.delFoodHistory(map);
	}

	// �ֹ� ���� ���(���̺� ���)
	@Override
	public Integer getBill(Map<String, Object> map) {
		log.debug("dao.getBill()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getBill(map);
	}

	// '�����'�� ���̺� '��밡��'���� ���� ����
	@Override
	public int modState(Map<String, Object> map) {
		log.debug("dao.modState()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.modState(map);
	}

	// ������� �����丮 ���̺� �̿� ���� �߰�
	@Override
	public int addRestaurantHistory(Map<String, Object> map) {
		log.debug("dao.addRestaurantHistory()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.addRestaurantHistory(map);
	}

	// ȸ�� ����Ʈ ��ȸ
	@Override
	public int getPoint(String id) {
		log.debug("dao.getPoint()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getPoint(id);
	}

	// ȸ�� ����Ʈ ����
	@Override
	public int modMemberPoint(Map<String, Object> map) {
		log.debug("dao.modMemberPoint()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.modMemberPoint(map);
	}

	// ��� �޴� �̸� ��ȸ
	@Override
	public String[] getMenuName(int restaurant_index) {
		log.debug("dao.getMenuName()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getMenuName(restaurant_index);
	}

	// ��� ��Ʈ
	@Override
	public List<Restaurant_ChartVO> getMenuCountChart(int restaurant_index) {
		log.debug("dao.getMenuCountChart()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getMenuCountChart(restaurant_index);
	}

	// ���� ����
	@Override
	public Object getSexChart(int restaurant_index) {
		log.debug("dao.getSexChart()");
		
		Map<String, Integer> m = null;

		m = new HashMap<String, Integer>();
		m.put("total", 0);
		m.put("��", 0);
		m.put("��", 0);

		List<Member> list = new ArrayList();
		
		for (int i = 0; i < m.size(); i++) {
			list = getSexChartContent(restaurant_index);
			String str = String.valueOf(list.get(i));
			
			System.out.println("str : " + str);
			
			String str2[] = str.split(",");
			String str3[] = str2[0].split("=");
			String str4[] = str2[1].split("=");
			String str5[] = str4[1].split("}");

			m.put(str3[1], Integer.parseInt(str5[0]));
		}

		return m;
	}
	
	// ���� ��Ʈ ����
	@Override
	public List<Member> getSexChartContent(int restaurant_index) {
		log.debug("dao.getSexChartContent()");

		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);

		return dao.getSexChartContent(restaurant_index);
	}

}
