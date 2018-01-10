package spring.mvc.baobob.host_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.MovieVO;

public interface Host_movieDAO {
	
	// ��ȭ ���� ���ϱ�
	public int getMovieCnt();
	
	// ��ȭ ��ü ��� ��ȸ(������ ��)
	public ArrayList<MovieVO> getMovieList(Map<String, Integer> map);
	
	// ��ȭ �߰�
	public int hostMovieAddPro(MovieVO vo);

}
