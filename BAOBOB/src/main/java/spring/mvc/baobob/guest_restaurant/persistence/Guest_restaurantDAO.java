package spring.mvc.baobob.guest_restaurant.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.MenuVO;
import spring.mvc.baobob.vo.ReviewVO;

public interface Guest_restaurantDAO {

	//1.������� ����
	
	//==========================================================================
	//============================== 2. �������-�޴� ==============================
	//==========================================================================
	//2-1.������� �޴� ����
	public int getArticleCnt_menu(int restaurant_index);
	//2-1.������� �޴� ����
	public ArrayList<MenuVO> getArticle_menu(int restaurant_index);
	
	
	//==========================================================================
	//============================== 3. ������� ���� ==============================
	//==========================================================================
	//3. �����ϱ�
	
	
	
	
	//==========================================================================
	//============================== 4. ������� ���� ==============================
	//==========================================================================
	//4-1. ���� ����
	public int getReviewCnt(int restaurant_index);
	
	//4-1. ���� ����
	public ArrayList<ReviewVO> getReviewList(Map<String, Integer> map);
	
	//4-2-1. ���� �ۼ�
	public int insertReviewPro(ReviewVO dto);
	
	//4-2-2. �����ۼ�
	public int insertRestaurant_review_Pro(Map<String, Object> map);
	
	//4-3-1. ���� ����(�ۼ��� �� Ȯ��)-> ����
	public ReviewVO getReviewInfo(Map<String, Object> map);
	//4-3-2. ���� ����
	public int updateReviewPro(ReviewVO dto);
	
	//4-4-1. ������� ���� ����(restaurant_review_tbl)
	public int delete_RestaurantReviewPro(Map<String, Object> map);
	//4-4-2. ���� ����(review_tbl)
	public int delete_ReviewPro(Map<String, Object> map);
	
	
	
	
	
	//���̵� Ȯ��
	public int idCheck(String member_id);
	//��й�ȣ Ȯ��
	public int pwdCheck(Map<String, Object> map);
	//public Member getMemberInfo(String strId);
}
