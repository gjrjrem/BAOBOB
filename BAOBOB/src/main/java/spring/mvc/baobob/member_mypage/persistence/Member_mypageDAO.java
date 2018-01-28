package spring.mvc.baobob.member_mypage.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.HistoryVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieHistoryVO;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.ParkingHistory;
import spring.mvc.baobob.vo.RestaurantLogVO;
import spring.mvc.baobob.vo.Theater_seatVO;
import spring.mvc.baobob.vo.WishListVO;

public interface Member_mypageDAO {
	
	//1:1���� �۰��� ���ϱ�
	public int getArticleCnt();
	
	//�нǹ����� �۰��� ���ϱ�
	public int getArticleLCnt();
	
	//1:1���Ǳ� ��� ��ȸ
	public ArrayList<BoardVO> getArticleList(Map<String, Integer> map);
	
	//�нǹ� ���Ǳ� ��� ��ȸ
	public ArrayList<BoardVO> getArticleLList(Map<String, Integer> map);
	
	//1:1���� �������� ��������
	public BoardVO getArticle(int num);
	
	//��ȸ�� ����
	public void addReadCnt(int num);
	
	//��й�ȣ Ȯ��
	public int pwdCheck(Map<String,Object> map);
	
	//1:1���� ����ó��
	public int updateQuestion(BoardVO dto);
	
	//������� ���
	public int getMaxNum();
	
	//�亯���� ���
	public void updateReply(BoardVO dto);
	
	//1:1���� �ۼ�
	public int insertQuestion(BoardVO dto);
	
	//�нǹ� ���� �ۼ�
	public int insertLost(BoardVO dto);
	
	//����� �����ϴ��� ����
	public int checkReply(BoardVO dto);
	
	//����� ���� ���
	public void updateRef_step(BoardVO dto);
	
	//1:1���� ����
	public int deleteQuestion(int num);
	
	//�ߺ�Ȯ�� üũ
	public int idCheck(String strId);
		
	//üũ
	public int memPCheck(Map<String, Object> map);
	
	//ȸ������ ��������
	public Member getMemberInfo(String strId);
	
	//ȸ������ ���� ó��
	public int updateMember(Member vo);
	
	//ȸ��Ż�� ó��
	public int memPDelPro(String strId);
	
	//wishList ����
	public int wishListCnt(String strId);
	
	//wishList ����Ʈ
	public ArrayList<MovieVO> getWishListMovies(Map<String,Object> map);
		
	//������ ��ȭ ����	
	public int movieClearCnt(String strId);	
		
	//���� �� ��ȭ ����Ʈ	
	public ArrayList<MovieHistoryVO> getMovieClear(Map<String,Object> map);
	
	//������ �󿵰�,�¼� �ҷ�����
	public ArrayList<Theater_seatVO> getMovieSeat(Map<String,Object> map);
		
	//���� ���̾ �۰��� ���ϱ�
	public int getMovieDiaryCnt(String strId);	
	
	//���� ���̾ ��� ��ȸ
	public ArrayList<BoardVO> getMovieDiaryList(Map<String, Object> map);
	
	//������̾ �ۼ�
	public int insertMovieDiary(BoardVO dto);
	
	//������̾ ����
	public int deleteMovieDiary(int num);
	
	//���ø���Ʈ �߰�
	public int addWishList(WishListVO dto);

	//���ø���Ʈ ����
	public int delMovieWishList(int num);
	
	//���� �̿��� �Ĵ� �۰��� ���ϱ�
	public int restaurantLogCnt(String strId);	
	
	//���� �̿��� �Ĵ� ��� ��ȸ
	public ArrayList<RestaurantLogVO> restaurantLogList(Map<String, Object> map);
	
	//������ ���� ���� ���ϱ�
	public int parkHistoryCnt(String strId);
	
	//������ ���� ��� ��ȸ
	public ArrayList<ParkingHistory> parkHistoryList(Map<String, Object> map);
	
	//���κ� �����丮 ��� �� ��ȸ
	public int getHistoryListCnt(String strId);
	
	//���κ� �����丮 ��� ��ȸ
	public ArrayList<HistoryVO> getHistoryList(Map<String, Object> map);
		
	//�����¼� ��� - �����¼� ���� ��������
	public ArrayList<Theater_seatVO> getSeatInfo(Map<String, Object> map);
	
	//�����¼� ��� - �����¼� state ��������
	public int updateSeatState(int seat_index);
	
	//�����¼� ��� - �����쿡 ���¼� ��������
	public int updateEmptySeat(int seat_index);
	
	//�����¼� ��� - movie_count �������
	public int updateMovieCount(Map<String, Object> map);
	
	//���ų��� ����(movie_history_tbl)
	public int moviePaidDelPro(int history_index);
	
	//���ų��� ����(history_tbl)
	public int historyDelPro(int history_index);
	
	
	
}
