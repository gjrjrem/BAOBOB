package spring.mvc.baobob.host_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.MovieVO;

public interface Host_movieDAO {
	
	// ��ȭ ���� ���ϱ�
	public int getMovieCnt();
	
	// ��ȭ ��ü ��� ��ȸ(������ ��)
	public ArrayList<MovieVO> getMovieList(Map<String, Integer> map);
	
	// ��ȭ �߰� ó��
	public int hostMovieAddPro(MovieVO vo);
	
	// ��ȭ ���� ó��
	public int hostMovieDel(int movie_index);
	
	// ��ȭ ��
	public MovieVO hostMovieDetail(int movie_index);
	
	// ��ȭ ���� ó��
	public int hostMovieModPro(MovieVO vo);
	
}
