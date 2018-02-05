package spring.mvc.baobob.host_movie.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.HostMovieChartVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.TheaterVO;
import spring.mvc.baobob.vo.Theater_scheduleVO;
import spring.mvc.baobob.vo.Theater_seatVO;
import spring.mvc.baobob.vo.WordVO;
import spring.mvc.baobob.vo.hostTChartVO;

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
	public ArrayList<MovieVO> getMovieList() {
		ArrayList<MovieVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.getMovieList();
		
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

	// ��ȭ ��
	@Override
	public MovieVO hostMovieDetail(int movie_index) {
		MovieVO vo = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vo = dao.hostMovieDetail(movie_index);
		
		return vo;
	}

	// ��ȭ ���� ó��
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
	public ArrayList<TheaterVO> getTheaterList() {
		ArrayList<TheaterVO> vos = null;
		
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		vos = dao.getTheaterList();
		
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
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.checkPosTheaterCnt(schedule_start);
	}

	// �󿵰����� �󿵰� ����
	@Override
	public ArrayList<TheaterVO> checkPosTheater(String schedule_start) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.checkPosTheater(schedule_start);
	}
	
	// ������ �߰� ó��
	@Override
	public int hostScheduleAddPro(Map<String, Object> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostScheduleAddPro(map);
	}

	// ������ ��
	@Override
	public Theater_scheduleVO hostScheduleDetail(int theater_schedule_index) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostScheduleDetail(theater_schedule_index);
	}

	// ������ ���� - ������ �ð��� �ٸ� �������� �ִ��� Ȯ��
	@Override
	public int chkCnt(Map<String, Object> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.chkCnt(map);
	}

	// ������ ���� ó��
	@Override
	public int updateSchedule(Map<String, Object> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.updateSchedule(map);
	}

	// ������ ���� ó��
	@Override
	public int hostScheduleDelPro(int theater_schedule_index) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostScheduleDelPro(theater_schedule_index);
	}

	// ���� ��
	@Override
	public int hostMovieEmpCnt() {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostMovieEmpCnt();
	}
	
	// ���� ��� ��������
	@Override
	public ArrayList<Member> hostMovieEmpList() {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostMovieEmpList();
	}
	
	// ���� ��� ���̵� Ȯ��
	@Override
	public int hostMovieEmpChkMemberId(String member_id) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostMovieEmpChkMemberId(member_id);
	}

	// ���̵�� ����� ���� ���� ��������
	@Override
	public Member hostMovieEmpInfo(String member_id) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostMovieEmpInfo(member_id);
	}

	// ����� ������ ���̵� state ����
	@Override
	public int memberChangeState(String member_id) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.memberChangeState(member_id);
	}

	// ��ȭ ���� ��Ͽ� �߰��ϱ�
	@Override
	public int insertEmp(Map<String, Object> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.insertEmp(map);
	}

	// ���� ����Ʈ ��� ��������
	@Override
	public int getMemberPoint(String member_id) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.getMemberPoint(member_id);
	}

	// ���� ��Ͽ��� ����
	@Override
	public int hostMovieEmpDel(String member_id) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostMovieEmpDel(member_id);
	}

	// ȸ�� step �����ϱ�
	@Override
	public int updateMemberStep(Member vo) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.updateMemberStep(vo);
	}

	// �󿵰��� ������ �¼� state �ҷ�����
	@Override
	public int getTheaterSeatState(Map<String, Integer> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.getTheaterSeatState(map);
	}
	
	// �󿵰��� ������ �¼� price �ҷ�����
	@Override
	public int getTheaterSeatPrice(Map<String, Integer> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.getTheaterSeatPrice(map);
	}

	// �����ٿ� �ش��ϴ� �¼� ����
	@Override
	public int TheaterScheduleSeatAddPro(Map<String, Integer> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.TheaterScheduleSeatAddPro(map);
	}

	// �����ٿ� �ش��ϴ� �¼� ���� ��������
	@Override
	public ArrayList<Theater_seatVO> hostTheaterScheduleSeatDetail(Map<String, Integer> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.hostTheaterScheduleSeatDetail(map);
	}

	// ��ȭ �����ٿ� ���¼��÷� ������Ʈ�ϱ�
	@Override
	public int updateEmpty_seat(int empty_seat) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.updateEmpty_seat(empty_seat);
	}

	// �帣�� ��ȭ ��������
	@Override
	public List<HostMovieChartVO> getMovieCountChart() {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.getMovieCountChart();
	}

	// ���ѿ��ɺ� ����
	@Override
	public List<hostTChartVO> movieAgeChart() {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.movieAgeChart();
	}

	// ������ ���� ��
	@Override
	public List<HostMovieChartVO> movieSexCountChart() {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.movieSexCountChart();
	}

	// ����Ŭ���� ���� ������
	@Override
	public List<WordVO> getWordCloudModel() {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.getWordCloudModel();
	}

	// ����Ŭ���� �ܾ �����ϴ��� Ȯ��
	@Override
	public int checkWordCloud(Map<String, Object> map) { //String word
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.checkWordCloud(map);
	}

	// ����Ŭ���� �ܾ� �߰�
	@Override
	public int addWordCloud(WordVO vo) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.addWordCloud(vo);
	}

	// ����Ŭ���� �ܾ� ī��Ʈ ������Ʈ
	@Override
	public int updateWordCloud(WordVO vo) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.updateWordCloud(vo);
	}

	// ����Ŭ���� �˻�
	@Override
	public List<WordVO> searchWordcloud(Map<String, Object> map) {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.searchWordcloud(map);
	}

	// ���� ����ϱ� �� ��� ȸ�� ���� �ҷ�����
	@Override
	public ArrayList<Member> getMemberList() {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.getMemberList();
	}

	// ��������ϱ� �� ȸ���� 1���̶� �����ϴ��� üũ
	@Override
	public int getMemberCnt() {
		Host_movieDAO dao = sqlSession.getMapper(Host_movieDAO.class);
		return dao.getMemberCnt();
	}
		
}
