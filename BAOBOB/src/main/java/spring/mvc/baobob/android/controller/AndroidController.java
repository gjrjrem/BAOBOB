package spring.mvc.baobob.android.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import spring.mvc.baobob.android.persistence.AndroidDAO;
import spring.mvc.baobob.member_mypage.persistence.Member_mypageDAO;
import spring.mvc.baobob.persistence.MainDAO;
import spring.mvc.baobob.vo.Android;
import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;

@Controller
public class AndroidController {
	private Logger log = Logger.getLogger(this.getClass());

	@Autowired
	AndroidDAO dao;
	@Autowired
	MainDAO mainDao;
	@Autowired
	Member_mypageDAO myDdao;
	
	//�� �α���
	@ResponseBody
	@RequestMapping("androidSignIn")
	public Map<String, String> androidSignIn(HttpServletRequest req){
		log.info("androidSignIn()");
		
		String id = req.getParameter("id");
		String pwd = req.getParameter("pwd");
		
		Map<String, String> in = new HashMap<String, String>();
		in.put("member_id", id);
		in.put("member_pwd", pwd);
		String step = mainDao.confirmIdPwd(in);
		
		Map<String, String> out = new HashMap<String, String>();
		if(step != null) {
			out.put("member_id", id);
		} else {
			out.put("member_id", null);
		}
		
		return out;
	}
	
	//�� ����������
	@ResponseBody
	@RequestMapping("androidMyPageMain")
	public Map<String, Object> androidMyPageMain(HttpServletRequest req) {
		log.info("androidMyPageMain()");
		
		//ȸ�� ����
		String id = req.getParameter("id");
		Member m = myDdao.getMemberInfo(id);
		
		//���� �Ǽ�
		int movie = dao.getUseMovieCnt(id);
		int rest = dao.getUseRestaurantCnt(id);
		int park = dao.getUseParkingCnt(id);
		
		//���� ���
		ArrayList<BoardVO> list = dao.getBoardList(id); 
		
		Map<String, Object> map = new HashMap<>();
		map.put("data1", m.getMember_name());
		map.put("data2", movie);
		map.put("data3", rest);
		map.put("data4", park);
		map.put("member", m);
		map.put("boardList",  list);
	
		return map;
	}
	
	@ResponseBody
	@RequestMapping("androidMyPageList")
	public Map<String, Object> androidMyPageList(HttpServletRequest req) {
		log.info("androidMyPageList");
		Map<String, Object> map = new HashMap<String, Object>();
		
		String id = req.getParameter("id");
		String idx = req.getParameter("idx");
		
		//0 movie 1 rest 2 park
		if(idx.equals("0")) { //��ȭ ���� ����
			ArrayList<Android> tmp = dao.getMemberMovieTicketing(id); //���� ����
			ArrayList<Android> list = new ArrayList<Android>(); //2�� �̻��� ��� �ߺ� ���� => ����. �ο��� set.
			
			//tmp = data1:ticket_date, data2:time_start, data3:time_end, data4:movie_title, data5:movie_poster
			//list = data1 => ���� �ο����� ����
			String title = null;
			String date = null;
			int number = 1;
			int listIdx = 0;
			for(int i = 0; i < tmp.size(); i += 1) {
				Android a = tmp.get(i);
				
				String aTitle = a.getData4();
				String aDate = a.getData1();
				//ó���� �ƴ� ���
				if(title != null && date != null) {
					//���� ������ �ٸ� ��� �߰�
					if(!aTitle.equals(title) && !date.equals(aDate)) {
						number = 1; //�ο��� �ʱ�ȭ
						a.setData1(String.valueOf(number));
						list.add(a);
						
						listIdx++; //list index 1 ����
					//���� ������ ���� ���
					} else {
						number += 1; //�ο��� ����
						a.setData1(String.valueOf(number));
						list.set(listIdx-1, a); //���� index �̹Ƿ� -1
					}
				} else {
					//ó������ ����
					list.add(a);
					
					listIdx++;
				}
				title = aTitle;
				date = aDate;
			}
			map.put("data",  list);
		} else if(idx.equals("1")) { //�Ĵ� ���� ����
			
			/*
			 * VO Class �� Android�� �̿��ؼ� �ʿ��� �����͸� ����
			 * select �Ҷ� ��Ī���� data1/data2/.../data5 �� �ָ� �˴ϴ�.
			 * data�� �� �ʿ��Ͻø� �߰��ϼŵ� �˴ϴ�.(data1 ~ data5�� ����!)
			 * 
			 * ������ ���
			 * ArrayList<Android> list = dao.�Ĵ翹�ų���select();
			 * 
			 * �� ����
			 * map.put("data", list);
			 */
			
			
			
			
			
			
		} else if(idx.equals("2")) { //���� �̿� ����
			ArrayList<Android> list = new ArrayList<>();
			Android a = new Android();
			a.setData1("1");
			a.setData2("2");
			a.setData3("3");
			a.setData4("4");
			a.setData5("5");
			list.add(a);
			map.put("data",  list);
		}
		return map;
	}
	
	
	
	
	
	
	
	
	//TEST http://cocomo.tistory.com/412
	@ResponseBody //������ �ȵ���̵�� ���� �����ϱ� ���� �ӳ����̼�
	@RequestMapping("androidTest")
	public Map androidTest(HttpServletRequest req) {
		//�ȵ���̵忡�� ������ ��
		System.out.println("title : " + req.getParameter("title"));
		System.out.println("memo : " + req.getParameter("memo"));
		
		//������ ������ ��
		Map<String, String> map = new HashMap<String, String>();
		map.put("member_id", "���̵�");
		map.put("member_pwd", "��й�ȣ");
		return map;
	}
}
