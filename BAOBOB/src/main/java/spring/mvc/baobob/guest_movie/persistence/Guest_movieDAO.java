package spring.mvc.baobob.guest_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.MovieVO;

public  interface Guest_movieDAO {

	//��ȭ����
	public int getMovieCnt();
	
	//�⺻��ȭ����
	public ArrayList<MovieVO> getAllMovies(Map<String,Object> map);
	
	//��ȭ������
	public MovieVO getMovie(int movie_index);
}
