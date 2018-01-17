package spring.mvc.baobob.host_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.TheaterVO;
import spring.mvc.baobob.vo.Theater_scheduleVO;
import spring.mvc.baobob.vo.Theater_seatVO;

@Repository
public class Host_movieDAOImpl implements Host_movieDAO {
	
	@Autowired
	public SqlSession sqlSession;
	
	// ��ȭ ���� ���ϱ�
	@Override
	public int getMovieCnt() {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.getMovieCnt();
		
		return cnt;
	}
	
	// ��ȭ ��ü ��� ��ȸ(������ ��)
	@Override
	public ArrayList<MovieVO> getMovieList(Map<String, Integer> map) {
		ArrayList<MovieVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.getMovieList(map);
		
		System.out.println("vos : " + vos);
		
		return vos;
	}
	
	// ��ȭ �߰� ó��
	@Override
	public int hostMovieAddPro(MovieVO vo) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostMovieAddPro(vo);
		
		return cnt;
	}

	// ��ȭ ���� ó��
	@Override
	public int hostMovieDel(int movie_index) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostMovieDel(movie_index);
		
		return cnt;
	}

	@Override
	public MovieVO hostMovieDetail(int movie_index) {
		MovieVO vo = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vo = dao.hostMovieDetail(movie_index);
		
		return vo;
	}

	@Override
	public int hostMovieModPro(MovieVO vo) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostMovieModPro(vo);
		
		return cnt;
	}

	// �󿵰� ���� ����
	@Override
	public int theater_index_check(int theater_index) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.theater_index_check(theater_index);
		
		return cnt;
	}

	// �󿵰� �߰�
	@Override
	public int insert_theater(Map<String, Object> map) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.insert_theater(map);
		
		return cnt;
	}

	// �󿵰� �¼��� state
	@Override
	public int insert_theater_seat(Map<String, Object> map) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.insert_theater_seat(map);
		
		return cnt;
	}

	// �󿵰� ���� ����
	@Override
	public int getTheaterCnt() {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.getTheaterCnt();
		
		return cnt;
	}

	// �󿵰� ����Ʈ
	@Override
	public ArrayList<TheaterVO> getTheaterList(Map<String, Integer> map) {
		ArrayList<TheaterVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.getTheaterList(map);
		
		return vos;
	}

	// �󿵰� ��
	@Override
	public TheaterVO hostTheaterDetail(int theater_index) {
		TheaterVO vo = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vo = dao.hostTheaterDetail(theater_index);
		
		return vo;
	}

	// �󿵰� �� �¼� ����
	@Override
	public ArrayList<Theater_seatVO> hostTheaterSeatDetail(int theater_index) {
		ArrayList<Theater_seatVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.hostTheaterSeatDetail(theater_index);
		
		return vos;
	}
	
	// �󿵰� �¼� ���� ó��
	@Override
	public int modify_theater_seat(Map<String, Object> map) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.modify_theater_seat(map);
		
		return cnt;
	}

	// �󿵰� ���� ó��
	@Override
	public int hostTheaterDel(int theater_index) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostTheaterDel(theater_index);
		
		return cnt;
	}

	// �󿵰� �¼� ���� ó��
	@Override
	public int hostTheaterSeatDel(int theater_index) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostTheaterSeatDel(theater_index);
		
		return cnt;
	}

	// ������ ��� ��ȸ
	@Override
	public ArrayList<Theater_scheduleVO> hostScheduleList(Map<String, Object> map) {
		ArrayList<Theater_scheduleVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.hostScheduleList(map);
		
		return vos;
	}
	
	// ������ ��ȭ ����
	@Override
	public ArrayList<MovieVO> getMovieING() {
		ArrayList<MovieVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.getMovieING();
		
		return vos;
	}

	// ��� �󿵰� ����
	@Override
	public ArrayList<TheaterVO> getTheaterAllList() {
		ArrayList<TheaterVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.getTheaterAllList();
		
		return vos;
	}
	
	// �󿵰����� �󿵰� count
	@Override
	public int checkPosTheaterCnt(String schedule_start) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.checkPosTheaterCnt(schedule_start);
		
		return cnt;
	}

	// �󿵰����� �󿵰� ����
	@Override
	public ArrayList<TheaterVO> checkPosTheater(String schedule_start) {
		ArrayList<TheaterVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.checkPosTheater(schedule_start);
		
		return vos;
	}
	
	// ������ �߰� ó��
	@Override
	public int hostScheduleAddPro(Map<String, Object> map) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostScheduleAddPro(map);
		
		return cnt;
	}

	// ������ ��
	@Override
	public Theater_scheduleVO hostScheduleDetail(int theater_schedule_index) {
		Theater_scheduleVO vo = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vo = dao.hostScheduleDetail(theater_schedule_index);
		
		return vo;
	}

	// ������ ���� - ������ �ð��� �ٸ� �������� �ִ��� Ȯ��
	@Override
	public int chkCnt(Map<String, Object> map) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.chkCnt(map);
		
		return cnt;
	}

	// ������ ���� ó��
	@Override
	public int updateSchedule(Map<String, Object> map) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.updateSchedule(map);
		
		return cnt;
	}

	// ������ ���� ó��
	@Override
	public int hostScheduleDelPro(int theater_schedule_index) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostScheduleDelPro(theater_schedule_index);
		
		return cnt;
	}

	// ���� ��
	@Override
	public int hostMovieEmpCnt() {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostMovieEmpCnt();
		
		return cnt;
	}
	
	// ���� ��� ��������
	@Override
	public ArrayList<Member> hostMovieEmpList() {
		ArrayList<Member> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.hostMovieEmpList();
		
		return vos;
	}
	
	// ���� ��� ���̵� Ȯ��
	@Override
	public int hostMovieEmpChkMemberId(String member_id) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostMovieEmpChkMemberId(member_id);
		
		return cnt;
	}

	// ���̵�� ����� ���� ���� ��������
	@Override
	public Member hostMovieEmpInfo(String member_id) {
		Member vo = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vo = dao.hostMovieEmpInfo(member_id);
		
		return vo;
	}

	// ����� ������ ���̵� state ����
	@Override
	public int memberChangeState(String member_id) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.memberChangeState(member_id);
		
		return cnt;
	}

	// ��ȭ ���� ��Ͽ� �߰��ϱ�
	@Override
	public int insertEmp(Map<String, Object> map) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.insertEmp(map);
		
		return cnt;
	}

	// ���� ����Ʈ ��� ��������
	@Override
	public int getMemberPoint(String member_id) {
		int point = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		point = dao.getMemberPoint(member_id);
		
		return point;
	}

	// ���� ��Ͽ��� ����
	@Override
	public int hostMovieEmpDel(String member_id) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.hostMovieEmpDel(member_id);
		
		return cnt;
	}

	// ȸ�� step �����ϱ�
	@Override
	public int updateMemberStep(Member vo) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.updateMemberStep(vo);
		
		return cnt;
	}

	// �󿵰��� ������ �¼� state �ҷ�����
	@Override
	public int getTheaterSeatState(Map<String, Integer> map) {
		int state = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		state = dao.getTheaterSeatState(map);
		
		return state;
	}
	
	// �󿵰��� ������ �¼� price �ҷ�����
	@Override
	public int getTheaterSeatPrice(Map<String, Integer> map) {
		int price = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		price = dao.getTheaterSeatPrice(map);
		
		return price;
	}

	// �����ٿ� �ش��ϴ� �¼� ����
	@Override
	public int TheaterScheduleSeatAddPro(Map<String, Integer> map) {
		int cnt = 0;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		cnt = dao.TheaterScheduleSeatAddPro(map);
		
		return cnt;
	}

	// �����ٿ� �ش��ϴ� �¼� ���� ��������
	@Override
	public ArrayList<Theater_seatVO> hostTheaterScheduleSeatDetail(Map<String, Integer> map) {
		ArrayList<Theater_seatVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.hostTheaterScheduleSeatDetail(map);
		
		return vos;
	}






	

	



	

	
}
