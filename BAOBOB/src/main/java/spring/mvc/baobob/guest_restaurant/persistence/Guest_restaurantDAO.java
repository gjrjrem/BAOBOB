package spring.mvc.baobob.guest_restaurant.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.MenuVO;
import spring.mvc.baobob.vo.RestaurantVO;
import spring.mvc.baobob.vo.Restaurant_scheduleVO;
import spring.mvc.baobob.vo.ReviewVO;
import spring.mvc.baobob.vo.TableVO;

public interface Guest_restaurantDAO {

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
	//3.Ư�� ���� ���� ��ȸ
	public RestaurantVO reserv_tableList(int index);
	//3.���� �� ���̺� ���� ��ȸ�� ���� �¼� �� ��ȸ
	public TableVO getColRow(int index);
	//���� �� ���̺� ���� ��ȸ�� ���� ���̺� ���� ��ȸ
	public int getState(Map<String, Object> map);
	//���� �߰�
	public int addReserv(Map<String, Object> map);
	
	
	//�߰�-������ �¼� Ȯ���� ���� ������ �ε��� ��ȸ
	public Integer getScheduleIndex(Restaurant_scheduleVO dto);
	//�߰�-������ �ʱ�ȭ(���� ���� �� �����ϴ� ���)
	public int resetTable2(Restaurant_scheduleVO dto);
	//�߰�-���� ��¥ ���� ��ȸ
	public int modTable2(Map<String, Object> map);
	//���� ���� �߰�
	public int AddHistory(Map<String, Object> map);
	//������� ���� ���� �߰� 
	public int AddRHistory(Map<String, Object> map);
	// ������ �¼� Ȯ���� ���� ������ �ε��� ��ȸ

	
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
