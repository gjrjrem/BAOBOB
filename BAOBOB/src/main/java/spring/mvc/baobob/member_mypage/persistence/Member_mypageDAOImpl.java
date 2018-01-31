package spring.mvc.baobob.member_mypage.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.HistoryVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieHistoryVO;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.ParkingHistory;
import spring.mvc.baobob.vo.RestaurantLogVO;
import spring.mvc.baobob.vo.TableVO;
import spring.mvc.baobob.vo.Theater_seatVO;
import spring.mvc.baobob.vo.WishListVO;

@Repository
public class Member_mypageDAOImpl implements Member_mypageDAO{
	
	@Autowired
	private SqlSession sqlSession;
	
	//1:1���� �۰��� ���ϱ�
	public int getArticleCnt() {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.getArticleCnt();
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���Ǳ� ��� ��ȸ
	public ArrayList<BoardVO> getArticleList(Map<String, Integer> map){
		ArrayList<BoardVO> dtos = null;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dtos = dao.getArticleList(map);
		
		return dtos;
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� �������� ��������
	public BoardVO getArticle(int num) {
		BoardVO dto = new BoardVO();
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dto = dao.getArticle(num);
		
		return dto;
	}
	
/*----------------------------------------------------------------------------*/

	//��ȸ�� ����
	public void addReadCnt(int num) {
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dao.addReadCnt(num);
	}
	
/*----------------------------------------------------------------------------*/
	
	//��й�ȣ Ȯ��
	public int pwdCheck(Map<String,Object> map) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.pwdCheck(map);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� ����ó��
	public int updateQuestion(BoardVO dto) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.updateQuestion(dto);
		
		return cnt;
		
	}
	
/*----------------------------------------------------------------------------*/
	
	//������� ���
	public int getMaxNum() {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.getMaxNum();
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//�亯���� ���
	public void updateReply(BoardVO dto) {
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dao.updateReply(dto);
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� �ۼ�
	public int insertQuestion(BoardVO dto) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		int num = dto.getBoard_index();
		int ref = dto.getBoard_ref();
		int ref_step = dto.getBoard_ref_step();
		int ref_level = dto.getBoard_ref_level();
		
		//������� ���
		if(num == 0) {
			cnt = getArticleCnt();
			System.out.println(cnt);
			//ù���� �ƴ� ���
			if(cnt > 0) {
				ref = getMaxNum() + 1;
				System.out.println(ref);
			//ù���� ���
			} else {
				ref = 1;
			}
			
			//�ʱ�ȭ
			ref_step = 0;
			ref_level = 0;
			
		//�亯���� ���
		} else {
			//������ �ۺ��� �Ʒ��� �۵��� update
			dao.updateReply(dto);
			ref_step++;
			ref_level++;
		}
		
		//���ǻ���!
		//�ؿ� 3������ ������ ���� �ٲ���⶧���� �ٲ� �װ��� dto�� �־ insertBoard���� �޾ƾ� �Ѵ�.
		dto.setBoard_ref(ref);
		dto.setBoard_ref_step(ref_step);
		dto.setBoard_ref_level(ref_level);
		
		cnt = dao.insertQuestion(dto);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//����� �����ϴ��� ����
	public int checkReply(BoardVO dto) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.checkReply(dto);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//����� ���� ���
	public void updateRef_step(BoardVO dto) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dao.updateRef_step(dto);
		
	}
	
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� ����
	public int deleteQuestion(int num) {
		int cnt = 0;
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		//����ȸ
		BoardVO dto = dao.getArticle(num);
		
		//����� �����ϴ��� ����
		int chkReply = checkReply(dto);
			
		//����� �ִ� ��� �������� �ʰڴ�.
		if(chkReply != 0) {
			cnt = -1;
		
		}else {

			//����� ���� ��� ����(�����, ��۾��� ���)
			updateRef_step(dto);
			
			// 2.����
			cnt = dao.deleteQuestion(num);
		}
				
			return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//�ߺ�Ȯ�� üũ
	@Override
	public int idCheck(String strId) {
		int cnt = 0;
				
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.idCheck(strId);
		
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
		
	//�α��� üũ
	public int memPCheck(Map<String, Object> map) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		int idChkCnt = dao.idCheck((String)map.get("strId"));
		
		if(idChkCnt == 1) {
			int pwdChkCnt = dao.memPCheck(map);
			
			if(pwdChkCnt == 1) {
				//�н����尡 ��ġ�ϸ� cnt=1
				cnt = 1;
			}else {
				//�н����尡 ��ġ���� ������ cnt=-1
				cnt = -1;
			}
		}
			
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ������ ��������
	public Member getMemberInfo(String strId) {
		Member vo = new Member();
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		vo = dao.getMemberInfo(strId);
		
		return vo;
		
	}
	
/*----------------------------------------------------------------------------*/
	
	//��������Ʈ������ ȸ�����(member_step)������Ʈ���ֱ�
	public int updateMemberStep(Map<String,Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.updateMemberStep(map);
	}
	
	
/*----------------------------------------------------------------------------*/
	
	//ȸ������ ���� ó��
	public int updateMember(Member vo) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.updateMember(vo);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//�нǹ����� �۰��� ���ϱ�
	public int getArticleLCnt() {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.getArticleLCnt();
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//�нǹ� ���Ǳ� ��� ��ȸ
	public ArrayList<BoardVO> getArticleLList(Map<String, Integer> map){
		ArrayList<BoardVO> dtos = null;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dtos = dao.getArticleLList(map);
		
		return dtos;
	}

/*----------------------------------------------------------------------------*/	
	
	//�нǹ� ���� �ۼ�
	public int insertLost(BoardVO dto) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		int num = dto.getBoard_index();
		int ref = dto.getBoard_ref();
		int ref_step = dto.getBoard_ref_step();
		int ref_level = dto.getBoard_ref_level();
		
		//������� ���
		if(num == 0) {
			cnt = getArticleCnt();
			//ù���� �ƴ� ���
			if(cnt > 0) {
				ref = getMaxNum() + 1;
			//ù���� ���
			} else {
				ref = 1;
			}
			
			//�ʱ�ȭ
			ref_step = 0;
			ref_level = 0;
			
		//�亯���� ���
		} else {
			//������ �ۺ��� �Ʒ��� �۵��� update
			dao.updateReply(dto);
			ref_step++;
			ref_level++;
		}
		
		//���ǻ���!
		//�ؿ� 3������ ������ ���� �ٲ���⶧���� �ٲ� �װ��� dto�� �־ insertBoard���� �޾ƾ� �Ѵ�.
		dto.setBoard_ref(ref);
		dto.setBoard_ref_step(ref_step);
		dto.setBoard_ref_level(ref_level);
		
		cnt = dao.insertLost(dto);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ��Ż�� ó��
	public int memPDelPro(String strId) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.memPDelPro(strId);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//wishList ����
	@Override
	public int wishListCnt(String strId) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.wishListCnt(strId);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//wishList ����Ʈ
	public ArrayList<MovieVO> getWishListMovies(Map<String,Object> map){
		ArrayList<MovieVO> movies = null;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		movies = dao.getWishListMovies(map);
		
		return movies;
	}
	
/*----------------------------------------------------------------------------*/

	//������ ��ȭ ����	
	public int movieClearCnt(String strId) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.movieClearCnt(strId);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//���� �� ��ȭ ����Ʈ	
	public ArrayList<MovieHistoryVO> getMovieClear(Map<String,Object> map){
		ArrayList<MovieHistoryVO> movies = null;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		movies = dao.getMovieClear(map);
		
		return movies;
	}
	
/*----------------------------------------------------------------------------*/
	
	//������ �󿵰�,�¼� �ҷ�����
	public ArrayList<Theater_seatVO> getMovieSeat(Map<String,Object> map){
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.getMovieSeat(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//���� ���̾ �۰��� ���ϱ�
	public int getMovieDiaryCnt(String strId) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.getMovieDiaryCnt(strId);
		
		return cnt;
		
	}
	
/*----------------------------------------------------------------------------*/
	
	//������̾ ��� ��ȸ
	public ArrayList<BoardVO> getMovieDiaryList(Map<String, Object> map){
		ArrayList<BoardVO> dtos = null;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dtos = dao.getMovieDiaryList(map);
		
		return dtos;
	}
	
/*----------------------------------------------------------------------------*/
	
	//������̾ �ۼ�
	public int insertMovieDiary(BoardVO dto) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		cnt = dao.insertMovieDiary(dto);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//������̾ ����
	public int deleteMovieDiary(int num) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		cnt = dao.deleteMovieDiary(num);
				
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//���ø���Ʈ �߰�
	public int addWishList(WishListVO dto) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.addWishList(dto);
		
		return cnt;
	}

/*----------------------------------------------------------------------------*/
	
	//���ø���Ʈ ����
	public int delMovieWishList(int num) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		cnt = dao.delMovieWishList(num);
				
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//���ų��� ����
	public int moviePaidDelPro(int num) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		cnt = dao.moviePaidDelPro(num);
				
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//���� �̿��� �Ĵ� �۰��� ���ϱ�
	public int restaurantLogCnt(String strId) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.restaurantLogCnt(strId);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//���� �̿��� �Ĵ� ��� ��ȸ
	public ArrayList<RestaurantLogVO> restaurantLogList(Map<String, Object> map){
		ArrayList<RestaurantLogVO> dtos = null;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dtos = dao.restaurantLogList(map);
		
		return dtos;
	}
	
/*----------------------------------------------------------------------------*/
	
	//������ ���� ���� ���ϱ�
	public int parkHistoryCnt(String strId) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.parkHistoryCnt(strId);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//������ ���� ��� ��ȸ
	public ArrayList<ParkingHistory> parkHistoryList(Map<String, Object> map){
		ArrayList<ParkingHistory> dtos = null;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dtos = dao.parkHistoryList(map);
		
		return dtos;
	}
	
/*----------------------------------------------------------------------------*/
	
	//���κ� �����丮 ��� �� ��ȸ
	public int getHistoryListCnt(String strId) {
		int cnt = 0;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		cnt = dao.getHistoryListCnt(strId);
		
		return cnt;
	}
	
/*----------------------------------------------------------------------------*/
	
	//���κ� �����丮 ��� ��ȸ
	public ArrayList<HistoryVO> getHistoryList(Map<String, Object> map){
		ArrayList<HistoryVO> dtos = null;
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		dtos = dao.getHistoryList(map);
		
		return dtos;
	}
	
/*----------------------------------------------------------------------------*/
	
	//�����¼� ���� ��������
	public ArrayList<Theater_seatVO> getSeatInfo(Map<String, Object> map){
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.getSeatInfo(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//�����¼� ��� - �����¼� state ��������
	public int updateSeatState(int seat_index) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.updateSeatState(seat_index);
	}
	
/*----------------------------------------------------------------------------*/
	
	//�����¼� ��� - �����쿡 ���¼� ��������
	public int updateEmptySeat(int seat_index) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.updateEmptySeat(seat_index);
	}
	
/*----------------------------------------------------------------------------*/
	
	//�����¼� ��� - movie_count �������
	public int updateMovieCount(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.updateMovieCount(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//����� ����Ʈ��ŭ �ٽ� ����Ʈ ���ϱ� �����ݾ��� 10% ����, ��������Ʈ���� �����ݾ��� 10% ����.
	public int updatePoint(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.updatePoint(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//���ų��� ����(history_tbl)
	public int historyDelPro(int history_index) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.historyDelPro(history_index);
	}
	
/*----------------------------------------------------------------------------*/
	
	//������ �����ϴ� Ÿ���� �࿭ (��:5*5)
	public TableVO getColRow(int restaurant_index){
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.getColRow(restaurant_index);
	}
	
/*----------------------------------------------------------------------------*/
	
	//state ���� ��ȸ
	public int getState(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.getState(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//�����丮 �ε��� ��ȸ
	public int getHistoryIndex(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.getHistoryIndex(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//������� �����丮 ���̺� �̿� ���� ����
	public int delRestaurantHistory(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.delRestaurantHistory(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//�����丮 ���̺� �̿� ���� ����
	public int delHistory(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.delHistory(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//'��� ��'�� ���̺��� '��� ����'���� ����
	public int modState(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.modState(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//���̺� ��ü ����
	public int delTable(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.delTable(map);
	}
	
/*----------------------------------------------------------------------------*/
	
	//������ ���� ó��
	public int delSchedule(Map<String, Object> map) {
		
		Member_mypageDAO dao = sqlSession.getMapper(Member_mypageDAO.class);
		
		return dao.delSchedule(map);
	}
	
	
	
	
	
	
}
