package spring.mvc.baobob.guest_movie.persistence;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import spring.mvc.baobob.vo.MovieVO;

@Repository
public class Guest_movieDAOImpl implements Guest_movieDAO{

	@Autowired
	public SqlSession sqlSession;

	//��ü ��ȭ����
	@Override
	public int getMovieCnt() {
		int cnt = 0;

		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.getMovieCnt();
		
		return cnt;
	}
	
	//movie_state�� ���� ��ȭ����
	@Override
	public int getMovieStateCnt(Map<String, Object> map) {
		int cnt = 0;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		cnt = gmdao.getMovieStateCnt(map);
		
		return cnt;
	}

	//�⺻��ȭ����
	@Override
	public ArrayList<MovieVO> getAllMovies(Map<String,Object> map) {
		ArrayList<MovieVO> movies = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		movies = gmdao.getAllMovies(map);
		
		return movies;
	}
	
	//movie_state�� ���� ��ȭ����Ʈ
	@Override
	public ArrayList<MovieVO> getMovieStateMovies(Map<String, Object> map) {
		ArrayList<MovieVO> movies = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		movies = gmdao.getMovieStateMovies(map);
		
		return movies;
	}

	//��ȭ������
	@Override
	public MovieVO getMovie(int movie_index) {
		MovieVO movie = null;
		
		Guest_movieDAO gmdao = sqlSession.getMapper(Guest_movieDAO.class);
		movie = gmdao.getMovie(movie_index);
		
		return movie;
	}

	

	

	
}
