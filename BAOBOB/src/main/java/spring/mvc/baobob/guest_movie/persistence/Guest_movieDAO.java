package spring.mvc.baobob.guest_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.MovieVO;

public  interface Guest_movieDAO {

	//��ü ��ȭ����
	public int getMovieCnt();
	
	//movie_state�� ���� ��ȭ����
	public int getMovieStateCnt(Map<String,Object> map);
	
	//�⺻��ȭ����
	public ArrayList<MovieVO> getAllMovies(Map<String,Object> map);
	
	//movie_state�� ���� ��ȭ����Ʈ
	public ArrayList<MovieVO> getMovieStateMovies(Map<String,Object> map);
	
	//��ȭ������
	public MovieVO getMovie(int movie_index);
}
