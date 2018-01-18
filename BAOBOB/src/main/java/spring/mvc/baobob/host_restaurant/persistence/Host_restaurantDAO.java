package spring.mvc.baobob.host_restaurant.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.EmployeeVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MenuVO;
import spring.mvc.baobob.vo.RestaurantVO;
import spring.mvc.baobob.vo.Restaurant_scheduleVO;
import spring.mvc.baobob.vo.TableVO;

public interface Host_restaurantDAO {

	// �Ĵ� �� ������ - ���� ��
	public int getRestaurantCnt();
	
	// �Ĵ� �� ������ - �� ���� ����
	public ArrayList<RestaurantVO> getRestaurantList();
	
	// �Ĵ� �� ������ - ���� �߰� ó��
	public int addRestaurant(RestaurantVO dto);

	// �Ĵ� �� ������ - ���� �� ���̺� �߰� ó��
	public int addTable(TableVO dto);

	// �Ĵ� �� ������ - ������ ���� ���� ��ȸ / ������ ���� �Է�
	public RestaurantVO viewRestaurant(int restaurant_index);
	
	// �Ĵ� �� ������ - ���� �� ���̺� ���� ��ȸ�� ���� �¼� �� ��ȸ
	public TableVO getColRow(int restaurant_index);

	// �Ĵ� �� ������ - ���� �� ���̺� ���� ��ȸ�� ���� ���̺� ���� ��ȸ
	public String getState(Map<String, Object> map);
	
	// �Ĵ� �� ������ - ���� ���� ó��
	public int modRestaurant(RestaurantVO dto);

	// �Ĵ� �� ������ - ���� �� ���̺� ���� ó�� �� ���̺� �ʱ�ȭ
	public int resetTable(RestaurantVO dto);
	
	// �Ĵ� �� ������ - ���� �� ���̺� ���� ó��
	public int modTable(Map<String, Object> map);

	// �Ĵ� �� ������ - ���� ���� ó��
	public int delRestaurant(RestaurantVO dto);
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	// �Ĵ�[1] �޴� ����
	public int getMenuCnt(int restaurant_index); 

	// �Ĵ�[1] �� �޴� ����
	public ArrayList<MenuVO> getMenuList(int restaurant_index);
	
	// �Ĵ�[1] �޴� �߰�
	public int addMenu(MenuVO dto);
	
	// �Ĵ�[1] ������ �޴� ���� ����
	public MenuVO viewMenu(MenuVO dto);
	
	// �Ĵ�[1] �޴� ���� ó��
	public int modMenu(MenuVO dto);

	// �Ĵ�[1] �޴� ����
	public int delMenu(MenuVO dto);

	// �Ĵ�[1] ���� ��
	public int getEmployeeCnt(int member_step);

	// �Ĵ�[1] �� ���� ����
	public ArrayList<EmployeeVO> getEmployeeList(int member_step);

	// ��ü ȸ�� ��
	public int getMemberCnt();

	// �� ȸ�� ����
	public ArrayList<Member> getMemberList();

	// �Ĵ�[1] �������� ����� ȸ�� ���� ��ȸ
	public Member viewMember(String id);

	// �Ĵ�[1] ���� ���
	public int addEmployee(Map<String, Object> map);

	// �Ĵ�[1] ���� ��� ó��(member_step ����)
	public int updateStep(Map<String, Object> map);

	// �Ĵ�[1] ���� ���� ��ȸ
	public EmployeeVO viewEmployee(String id);

	// �Ĵ�[1] ���� ���� ���� ó��(member_step ���)
	public int getCumPoint(String id);

	// �Ĵ�[1] ���� ���� ����
	public int delEmployee(Map<String, Object> map);

	// �Ĵ�[1] ���� �߰�
	public int addReserv(Map<String, Object> map);

	// �Ĵ�[1] ���� ��¥ ���� ��ȸ
	int modTable2(Map<String, Object> map);
	
	// �Ĵ�[1] ��¥�� ���� ��ȸ
	public ArrayList<Restaurant_scheduleVO> getReservList(Map<String, Object> map);

	// ������ �¼� Ȯ���� ���� ������ �ε��� ��ȸ
	public int getScheduleIndex(Restaurant_scheduleVO schedule_dto);
}
