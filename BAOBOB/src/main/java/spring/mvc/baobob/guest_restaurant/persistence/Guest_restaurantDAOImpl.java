package spring.mvc.baobob.guest_restaurant.persistence;

import static org.hamcrest.CoreMatchers.nullValue;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.MenuVO;
import spring.mvc.baobob.vo.ReviewVO;

@Repository
public class Guest_restaurantDAOImpl implements Guest_restaurantDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	//==========================================================================
	//============================== 2. �������-�޴� ==============================
	//==========================================================================
	//2-1.������� �޴� ����
	@Override
	public int getArticleCnt_menu(int restaurant_index) {
		int cnt = 0;
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		cnt = dao.getArticleCnt_menu(restaurant_index);
		
		return cnt;
	}
	
	//2-2. �������-�޴�����
	@Override
	public ArrayList<MenuVO> getArticle_menu(int restaurant_index) {
		ArrayList<MenuVO> dtos = null;
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		dtos = dao.getArticle_menu(restaurant_index);
			
		return dtos;
	}
	//==========================================================================
	//============================== 3. ������� ���� ==============================
	//==========================================================================
	
	
	//==========================================================================
	//============================== 4. ������� ���� ==============================
	//==========================================================================
	//4-1. ���� ����
	@Override
	public int getReviewCnt(int restaurant_index) {
		int cnt = 0;
		
		Guest_restaurantDAO dao =  sqlSession.getMapper(Guest_restaurantDAO.class);
		cnt = dao.getReviewCnt(restaurant_index);
		
		return cnt;
	}
	
	//4-1.���� Ȯ��
	@Override
	public ArrayList<ReviewVO> getReviewList(Map<String, Integer> map){
	
		ArrayList<ReviewVO> dtos = null;
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		dtos = dao.getReviewList(map);
		
		return dtos;
	}
	
	//4-2-1. ���� �߰�(Review_tbl)
	public int insertReviewPro(ReviewVO dto) {
		int insertCnt = 0;
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		insertCnt = dao.insertReviewPro(dto);
		
		if(insertCnt > 0) {
			System.out.println("���� �߰�(Review_tbl) ����");
		}else {
			System.out.println("���� �߰�(Review_tbl) ����");
		}
		//memberId�� �߰��ؾ���
		return insertCnt;
	}
	
	//4-2-2. ���� �߰�(Restaurant_review_tbl)
	public int insertRestaurant_review_Pro(Map<String, Object> map) {
		int insertCnt = 0;
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		insertCnt = dao.insertRestaurant_review_Pro(map);
		
		if(insertCnt > 0) {
			System.out.println("���� �߰�(Review_tbl), ���� �߰�(Restaurant_review_tbl) ����");
		}else {
			System.out.println("���� �߰�(Restaurant_review_tbl) ����");
		}
		return insertCnt;
	}

	
	

	//4-3. ������������
	@Override
	public ReviewVO getReviewInfo(Map<String, Object> map) {
		ReviewVO dto = new ReviewVO();
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		dto = dao.getReviewInfo(map);
		
		return dto;
	}

	//4-3. ���� ����
	@Override
	public int updateReviewPro(ReviewVO dto) {
		int updateCnt = 0;
		System.out.println("===== DAO - updateReviewPro =====");
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		updateCnt = dao.updateReviewPro(dto);
		
		return updateCnt;
	}

	
	
	
	//4-4. ���� ����
	@Override
	public int delete_ReviewPro(Map<String, Object> map) {
		int deleteCnt = 0;
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		int deleteRCnt = delete_RestaurantReviewPro(map);
		deleteCnt = dao.delete_ReviewPro(map);
		
		System.out.println("������� ���� ����1 : " + deleteRCnt);
		System.out.println("���� ����2 : " + deleteCnt);

		return deleteCnt;
	}
	
	@Override
	public int delete_RestaurantReviewPro(Map<String, Object> map) {
		int deleteCnt = 0;
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		deleteCnt = dao.delete_RestaurantReviewPro(map);
		
		return deleteCnt;
	}
	
	
	
	
	
	//���̵� Ȯ��
	@Override
	public int idCheck(String member_id) {
		int cnt=0;
		
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		cnt = dao.idCheck(member_id);
		
		if(cnt>0) System.out.println("���̵� Ȯ��");
		
		return cnt;
	}
	
	//�α��� ó��, ��������, ��������
	@Override
	public int pwdCheck(Map<String, Object> map) {
		int cnt = 0;
		System.out.println("2. pwdCheck - strId : " + map.get("member_id"));
		//�α��� ȭ�鿡�� �Է¹��� id�� ��ġ�� �����Ͱ� �ִ��� Ȯ��
		Guest_restaurantDAO dao = sqlSession.getMapper(Guest_restaurantDAO.class);
		int idChkCnt = idCheck((String)map.get("member_id"));
		
		//�α����� id�� �ش��ϴ� �����Ͱ��ְ�
		if(idChkCnt>0) {
			//dao=sqlSession.getMapper(MemberDAO.class);
			int pwdChkCnt = dao.pwdCheck(map);
			System.out.println("pwdCheck : "+ pwdChkCnt);
			if(pwdChkCnt==1) {//�н����尡 ��ġ�ϸ� cnt=1
				cnt=1;
			
			}else {// �н����尡 ��ġ���� ������ cnt = -1
				cnt=-1;
			}
		}else {	//���̵� ���� x
			cnt=0;
		}
		return cnt;
	}
	
	
	
}
