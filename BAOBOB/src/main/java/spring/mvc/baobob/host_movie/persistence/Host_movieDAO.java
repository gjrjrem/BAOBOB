package spring.mvc.baobob.host_movie.persistence;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.MapKey;

import spring.mvc.baobob.vo.HostMovieChartVO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.MovieVO;
import spring.mvc.baobob.vo.TheaterVO;
import spring.mvc.baobob.vo.Theater_scheduleVO;
import spring.mvc.baobob.vo.Theater_seatVO;
import spring.mvc.baobob.vo.WordVO;
import spring.mvc.baobob.vo.hostTChartVO;

public interface Host_movieDAO {
	
	// ��ȭ ���� ���ϱ�
	public int getMovieCnt();
	
	// ��ȭ ��ü ��� ��ȸ(������ ��)
	public ArrayList<MovieVO> getMovieList();
	
	// ��ȭ �߰� ó��
	public int hostMovieAddPro(MovieVO vo);
	
	// ��ȭ ���� ó��
	public int hostMovieDel(int movie_index);
	
	// ��ȭ ��
	public MovieVO hostMovieDetail(int movie_index);
	
	// ��ȭ ���� ó��
	public int hostMovieModPro(MovieVO vo);
	
	// �󿵰� ���� ����
	public int theater_index_check(int theater_index);
	
	// �󿵰� �߰��ϱ�
	public int insert_theater(Map<String, Object> map);
	
	// �󿵰� �¼��� state �ֱ�
	public int insert_theater_seat(Map<String, Object> map);
	
	// �󿵰� ���� ����
	public int getTheaterCnt();
	
	// �󿵰� ��� ��ȸ
	public ArrayList<TheaterVO> getTheaterList();
	
	// �󿵰� ��
	public TheaterVO hostTheaterDetail(int theater_index);
	
	// �󿵰� �� �¼� ����
	public ArrayList<Theater_seatVO> hostTheaterSeatDetail(int theater_index);
	
	// �󿵰� �¼� ���� ó��
	public int modify_theater_seat(Map<String, Object> map);
	
	// �󿵰� ���� ó��
	public int hostTheaterDel(int theater_index);
	
	// �󿵰� �¼� ���� ó��
	public int hostTheaterSeatDel(int theater_index);
	
	// ������ ��� ��ȸ
	public ArrayList<Theater_scheduleVO> hostScheduleList(Map<String, Object> map);
	
	// ������ ��ȭ ����
	public ArrayList<MovieVO> getMovieING();
	
	// ��� �󿵰� ����
	public ArrayList<TheaterVO> getTheaterAllList();
	
	// �� ���� �󿵰� count
	public int checkPosTheaterCnt(String schedule_start);
	
	// �����ѽð��� ������ ���� �� ������ �󿵰� ��������
	public ArrayList<TheaterVO> checkPosTheater(String schedule_start);
	
	// ������ �߰� ó��
	public int hostScheduleAddPro(Map<String, Object> map);
	
	// ������ ��
	public Theater_scheduleVO hostScheduleDetail(int theater_schedule_index);
	
	// ������ ���� - ������ �ð��� �ٸ� �������� �ִ��� Ȯ��
	public int chkCnt(Map<String, Object> map);
	
	// ������ ���� ó��
	public int updateSchedule(Map<String, Object> map);
	
	// ������ ���� ó��
	public int hostScheduleDelPro(int theater_schedule_index);
	
	// ���� ��
	public int hostMovieEmpCnt();
	
	// ���� ��� ��������
	public ArrayList<Member> hostMovieEmpList();
	
	// ���� ��� ���̵� Ȯ��
	public int hostMovieEmpChkMemberId(String member_id);
	
	// ���̵�� ����� ���� ���� ��������
	public Member hostMovieEmpInfo(String member_id);
	
	// ����� ���� ���̵��� state ����
	public int memberChangeState(String member_id);
	
	// ��ȭ ���� ��Ͽ� �߰��ϱ�
	public int insertEmp(Map<String, Object> map);
	
	// ��������Ʈ ��������
	public int getMemberPoint(String member_id);
	
	// ���� ��Ͽ��� ����
	public int hostMovieEmpDel(String member_id);
	
	// ȸ�� step �����ϱ�
	public int updateMemberStep(Member vo);
	
	// �󿵰��� ������ �¼� state �ҷ�����
	public int getTheaterSeatState(Map<String, Integer> map);
	
	// �󿵰��� ������ �¼� price �ҷ�����
	public int getTheaterSeatPrice(Map<String, Integer> map);
	
	// �����ٿ� �ش��ϴ� �¼� ����
	public int TheaterScheduleSeatAddPro(Map<String, Integer> map);
	
	// �����ٿ� �ش��ϴ� �¼� ���� ��������
	public ArrayList<Theater_seatVO> hostTheaterScheduleSeatDetail(Map<String, Integer> map);
	
	// ��ȭ �����ٿ� ���¼� ������Ʈ�ϱ�
	public int updateEmpty_seat(int empty_seat);
	
	// �帣�� ��ȭ ������ ��
	@MapKey("kind")
	public List<HostMovieChartVO> getMovieCountChart();
	
	// ���� ���ɺ� ����
	@MapKey("kind")
	public List<hostTChartVO> movieAgeChart();

	// ������ ���� ��
	@MapKey("kind")
	public List<HostMovieChartVO> movieSexCountChart();
	
	// ���� Ŭ����
	// ����Ŭ���� �ܾ �̹� �����ϴ��� Ȯ��
	public int checkWordCloud(Map<String, Object> map);
	
	// ����Ŭ���� �ܾ� ���� ������
	public List<WordVO> getWordCloudModel();
	
	// �м��� ���� Ŭ���� �ܾ �߰�
	public int addWordCloud(WordVO vo);
	
	// �м��� ���� Ŭ���� �ܾ�� ������Ʈ
	public int updateWordCloud(WordVO vo);
	
	// ����ī��Ʈ �˻�
	public List<WordVO> searchWordcloud(Map<String, Object> map);
}
