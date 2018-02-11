package spring.mvc.baobob.android.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.guest_movie.persistence.Guest_movieDAO;
import spring.mvc.baobob.vo.Android;
import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.Restaurant_scheduleVO;
import spring.mvc.baobob.vo.TableVO;

@Repository
public class AndroidDAOImpl implements AndroidDAO {

	@Autowired
	SqlSession sqlSession;

	// ��ȭ �̿� �Ǽ�
	@Override
	public int getUseMovieCnt(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.getUseMovieCnt(id);
		return cnt;
	}

	// �Ĵ� �̿� �Ǽ�
	@Override
	public int getUseRestaurantCnt(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.getUseRestaurantCnt(id);
		return cnt;
	}

	// ������ �̿� �Ǽ�
	@Override
	public int getUseParkingCnt(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.getUseParkingCnt(id);
		return cnt;
	}

	// �ֱ� 10�ϰ��� ���� ����
	@Override
	public ArrayList<BoardVO> getBoardList(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<BoardVO> list = mapper.getBoardList(id);
		return list;
	}

	// ��ȭ ���� ����
	@Override
	public ArrayList<Android> getMemberMovieTicketing(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getMemberMovieTicketing(id);
		;
		return list;
	}

	// �Ĵ� �̿� ����
	@Override
	public ArrayList<Android> getUseRestaurantList(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getUseRestaurantList(id);
		return list;
	}

	// ���� �̿� ����
	@Override
	public ArrayList<Android> getMemberParking(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getMemberParking(id);
		return list;
	}

	// ȸ�� ���� ����
	@Override
	public int anMemberUpdate(Member m) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.anMemberUpdate(m);
		return cnt;
	}

	// ��ȭ ����
	@Override
	public MovieVO androidMovieInfo(String movie_title) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		MovieVO movie = mapper.androidMovieInfo(movie_title);
		return movie;
	}

	// ���� - ��ȭ ���
	@Override
	public ArrayList<Android> getMovieList() {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getMovieList();
		return list;
	}

	// ���� - �Ĵ� ���(index)
	@Override
	public ArrayList<Android> getRestaurantList() {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getRestaurantList();
		return list;
	}

	// ����) �ش� ��¥ ���ϴ� ��ȭ
	@Override
	public ArrayList<Android> getMovieSchedule(String day) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getMovieSchedule(day);
		return list;
	}

	// ȸ���� ���� ����Ʈ
	@Override
	public int getMemberPoint(String id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int point = mapper.getMemberPoint(id);
		return point;
	}

	// ��ȭ ����) seat ����
	@Override
	public int updateSeatState(Map<String, Object> map) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.updateSeatState(map);
		return cnt;
	}

	// ��ȭ ����) ��ȭ �������� ����
	@Override
	public int movieCountUpdate(Map<String, Object> map) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.movieCountUpdate(map);
		return cnt;
	}

	// �Ĵ� �޴�
	public ArrayList<Android> getRestaurantMenu(int restaurant_index) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getRestaurantMenu(restaurant_index);
		return list;
	}

	// �Ĵ� �¼� ����
	public ArrayList<Android> getRestaurantSeatState(int restIndex) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getRestaurantSeatState(restIndex);
		return list;
	}

	// �Ĵ� �¼�
	public ArrayList<Android> getRestaurantSeat(Map<String, Object> map) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		ArrayList<Android> list = mapper.getRestaurantSeat(map);
		return list;
	}

	// �Ĵ� ���� 1) ������ ���
	public int setRestaurantSchedule(Restaurant_scheduleVO rest) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.setRestaurantSchedule(rest);
		return cnt;
	}

	// �Ĵ� ���� 2) ������ ���
	public int restaurantTableUpdate(TableVO restTable) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.restaurantTableUpdate(restTable);
		return cnt;
	}

	// �Ĵ� ���� 3) �����丮
	public int setRestaurantMainHistory(String member_id) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.setRestaurantMainHistory(member_id);
		return cnt;
	}

	// �Ĵ� ���� 4) �Ĵ� �����丮
	public int setRestaurantHistory(int restTableIndex) {
		AndroidDAO mapper = sqlSession.getMapper(AndroidDAO.class);
		int cnt = mapper.setRestaurantHistory(restTableIndex);
		return cnt;
	}
}
