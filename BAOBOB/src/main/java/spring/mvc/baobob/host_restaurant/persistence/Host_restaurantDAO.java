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
	public int getState(Map<String, Object> map);
	
	// �Ĵ� �� ������ - ���� ���� ó��
	public int modRestaurant(RestaurantVO dto);

	// �Ĵ� �� ������ - ���� �� ���̺� ���� ó�� �� ���̺� �ʱ�ȭ
	public int resetTable(RestaurantVO dto);
	
	// �Ĵ� �� ������ - ���� �� ���̺� ���� ó��
	public int modTable(Map<String, Object> map);

	// �Ĵ� �� ������ - ���� ���� ó��
	public int delRestaurant(RestaurantVO dto);
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	// �Ĵ纰 �޴� ����
	public int getMenuCnt(int restaurant_index); 

	// �� �޴� ����
	public ArrayList<MenuVO> getMenuList(int restaurant_index);
	
	// �޴� �߰�
	public int addMenu(MenuVO dto);
	
	// ������ �޴� ���� ����
	public MenuVO viewMenu(MenuVO dto);
	
	// �޴� ���� ó��
	public int modMenu(MenuVO dto);

	// �޴� ����
	public int delMenu(MenuVO dto);

	// �Ĵ纰 ���� ��
	public int getEmployeeCnt(int member_step);

	// �� ���� ����
	public ArrayList<EmployeeVO> getEmployeeList(int member_step);

	// ��ü ȸ�� ��(Ÿ �Ĵ�, Ÿ �μ� ���� ����)
	public int getMemberCnt();

	// �� ȸ�� ����
	public ArrayList<Member> getMemberList();

	// �������� ����� ȸ�� ���� �� ��ȸ
	public Member viewMember(String id);

	// ���� ���
	public int addEmployee(Map<String, Object> map);

	// ���� ��� ó��(member_step ����)
	public int updateStep(Map<String, Object> map);

	// ���� ���� ��ȸ
	public EmployeeVO viewEmployee(String id);

	// ���� ���� ���� ó��(member_step ���)
	public int getCumPoint(String id);

	// ���� ���� ����
	public int delEmployee(Map<String, Object> map);

	// ���� �߰�
	public int addReserv(Map<String, Object> map);

	// ���� �� �ʱ�ȭ(���� ���� �� �����ϴ� ���)
	public int resetTable2(Restaurant_scheduleVO dto);

	// ���� ��¥ ���� ��ȸ
	public int modTable2(Map<String, Object> map);
	
	// ��¥�� ���� ��ȸ
	public ArrayList<Restaurant_scheduleVO> getReservList(Map<String, Object> map);

	// ������ �¼� Ȯ���� ���� ������ �ε��� ��ȸ
	public Integer getScheduleIndex(Restaurant_scheduleVO dto);

	// �Ĵ� ����, �Ĵ� �ε��� üũ
	public int[] getRestaurantIndex();

	// ���� �ð�, ���� �ð� üũ
	public Restaurant_scheduleVO getTime(Restaurant_scheduleVO dto);

	// �ֹ� ���� �߰�(�Ǹ� ����)
	public int addFoodHistory(Map<String, Object> map);
}
