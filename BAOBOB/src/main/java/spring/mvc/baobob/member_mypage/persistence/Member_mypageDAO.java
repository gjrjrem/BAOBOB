package spring.mvc.baobob.member_mypage.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieHistoryVO;
import spring.mvc.baobob.vo.MovieVO;

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
		
	//���� ���̾ �۰��� ���ϱ�
	public int getMovieDiaryCnt(String strId);	
	
	//���� ���̾ ��� ��ȸ
	public ArrayList<BoardVO> getMovieDiaryList(Map<String, Object> map);
	
	//������̾ �ۼ�
	public int insertMovieDiary(BoardVO dto);
	
	//������̾ ����
	public int deleteMovieDiary(int num);
	
	//���ø���Ʈ ����
	public int delMovieWishList(int num);
	
	//���ų��� ����
	public int moviePaidDelPro(int num);

}
