package spring.mvc.baobob.host_restaurant.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.MenuVO;

public interface Host_restaurantDAO {
	// �Ĵ�[1] �޴� ���(����)
	public int getMenuCnt(); 

	// �Ĵ�[1] �޴� ���(����)
	public ArrayList<MenuVO> getMenuList(Map<String, Object> map);
	
	// �Ĵ�[1] �޴� �߰� ó��
	public int addMenu(MenuVO dto);
	
	// �Ĵ�[1] ������ �޴� ���� ����
	public MenuVO viewMenu(int index);
	
	// �Ĵ�[1] �޴� ���� ó��
	public int modMenu(MenuVO dto);
}
