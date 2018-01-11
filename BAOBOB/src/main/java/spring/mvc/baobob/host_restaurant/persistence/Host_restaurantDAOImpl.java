package spring.mvc.baobob.host_restaurant.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.MenuVO;

@Repository
public class Host_restaurantDAOImpl implements Host_restaurantDAO {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	private SqlSession sqlSession;

	// �Ĵ�[1] �޴� ���(����)
	@Override
	public int getMenuCnt() {
		// TODO Auto-generated method stub
		log.debug("dao.getMenuCnt()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getMenuCnt();
	}

	// �Ĵ�[1] �޴� ���(����)
	@Override
	public ArrayList<MenuVO> getMenuList(Map<String, Object> map) {
		// TODO Auto-generated method stub
		log.debug("dao.getMenuList()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.getMenuList(map);
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

	@Override
	public MenuVO viewMenu(int index) {
		// TODO Auto-generated method stub
		log.debug("dao.viewMenu()");
		
		Host_restaurantDAO dao = sqlSession.getMapper(Host_restaurantDAO.class);
		
		return dao.viewMenu(index);
	}
}
