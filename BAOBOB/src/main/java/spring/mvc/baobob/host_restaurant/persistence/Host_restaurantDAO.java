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

	// ���� �� ��ȸ
	public int getRestaurantCnt();
	
	// ��� ���� ����
	public ArrayList<RestaurantVO> getRestaurantList();

	// ���� �߰� ó��
	public int addRestaurant(RestaurantVO dto);

	// ���峻 ���̺� �߰� ó��
	public int addTable(TableVO dto);

	// ���� ���� ��ȸ
	public RestaurantVO viewRestaurant(int restaurant_index);

	// ������ �����ϴ� Ÿ���� �࿭ ��ȸ (��:5*5)
	public TableVO getColRow(int restaurant_index);

	// Ÿ�� �ϳ��ϳ��� ���� ��ȸ
	public int getState(Map<String, Object> map);
	
	// ���� ���� ó��
	public int modRestaurant(RestaurantVO dto);

	// ���� �� ���̺� ���� �ʱ�ȭ
	public int resetTable(RestaurantVO dto);
	
	// ���� �� ���̺� ���� ó��
	public int modTable(Map<String, Object> map);

	// ���� ���� ó��
	public int delRestaurant(RestaurantVO dto);
	
	/////////////////////////////////////////////////////////////////////////////////////////
	
	// �Ĵ纰 �޴� ����
	public int getMenuCnt(int restaurant_index); 

	// �� �޴� ����
	public ArrayList<MenuVO> getMenuList(int restaurant_index);
	
	// �޴� �߰� ó��
	public int addMenu(MenuVO dto);
	
	// �޴� ����
	public MenuVO viewMenu(MenuVO dto);
	
	// �޴� ���� ���� ó��
	public int modMenu(MenuVO dto);

	// �޴� ���� ó��
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

	// ���� ���/���� ó��(member_step ����)
	public int updateStep(Map<String, Object> map);

	// ���� ���� ��ȸ
	public EmployeeVO viewEmployee(String id);

	// step ����� ���� ���� ����Ʈ ��ȸ
	public int getCumPoint(String id);

	// ���� ���� ó��
	public int delEmployee(Map<String, Object> map);

	// ���� �߰�
	public int addReserv(Map<String, Object> map);

	// ���̺� �ʱ�ȭ(���� ���� �� �����ϴ� ���)
	public int resetTable2(Restaurant_scheduleVO dto);

	// ���� ��¥ ���� ��ȸ
	public int modTable2(Map<String, Object> map);
	
	// ��¥�� ���� ��ȸ
	public ArrayList<Restaurant_scheduleVO> getReservList(Map<String, Object> map);

	// ������ �ε��� ��ȸ
	public Integer getScheduleIndex(Restaurant_scheduleVO dto);

	// �Ĵ� ����, �Ĵ� �ε��� üũ
	public int[] getRestaurantIndex();

	// ���� �ð�, ���� �ð� üũ
	public Restaurant_scheduleVO getTime(Restaurant_scheduleVO dto);

	// �ֹ� �߰� ó��
	public int addFoodHistory(Map<String, Object> map);

	// �Ĵ纰 ���
	public Integer getAccount(int restaurant_index);

	// ȸ�� ���̵� �ִ���, �ִٸ� ������ ������ ��ȸ
	public Integer confirmId(String id);

	// �̿� ���� �߰� ó��
	public int addHistory(String id);

	// �̹� �ֹ��� �޴����� Ȯ��
	public Integer getMenuCount(Map<String, Object> map);

	// �ֹ��� �޴��� ��� ���� ����
	public Integer modFoodHistory(Map<String, Object> map);

	// �ֹ� ��� ó��
	public Integer delFoodHistory(Map<String, Object> map);

	// �ֹ� ���� ���(���̺� ���)
	public Integer getBill(Map<String, Object> map);

	// '�����'�� ���̺� '��밡��'���� ���� ����
	public int modState(Map<String, Object> map);

	// ������� �����丮 ���̺� �̿� ���� �߰�
	public int addRestaurantHistory(Map<String, Object> map);

	// ����Ʈ ��ȸ
	public int getPoint(String id);

	// ȸ�� ����Ʈ ����
	public int modMemberPoint(Map<String, Object> map);
}
