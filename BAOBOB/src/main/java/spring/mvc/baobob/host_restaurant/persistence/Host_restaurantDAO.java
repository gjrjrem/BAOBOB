package spring.mvc.baobob.host_restaurant.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.EmployeeVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MenuVO;

public interface Host_restaurantDAO {
	// �Ĵ� �� ������ - �Ĵ� �߰� ó��
	public int addRestaurant(Map<String, Object> map);

	// �Ĵ� �� ������ - �Ĵ� �� ���̺� �߰� ó��
	public int addTable(Map<String, Object> map);
	
	// �Ĵ�[1] �޴� ����
	public int getMenuCnt(); 

	// �Ĵ�[1] �� �޴� ����
	public ArrayList<MenuVO> getMenuList();
	
	// �Ĵ�[1] �޴� �߰�
	public int addMenu(MenuVO dto);
	
	// �Ĵ�[1] ������ �޴� ���� ����
	public MenuVO viewMenu(int index);
	
	// �Ĵ�[1] �޴� ���� ó��
	public int modMenu(MenuVO dto);

	// �Ĵ�[1] �޴� ����
	public int delMenu(MenuVO dto);

	// �Ĵ�[1] ���� ��
	public int getEmployeeCnt();

	// �Ĵ�[1] �� ���� ����
	public ArrayList<EmployeeVO> getEmployeeList();

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
}
