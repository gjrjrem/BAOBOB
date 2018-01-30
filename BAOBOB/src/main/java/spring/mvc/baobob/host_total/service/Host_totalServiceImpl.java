package spring.mvc.baobob.host_total.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.host_total.persistence.Host_totalDAO;
import spring.mvc.baobob.vo.Member;
import spring.mvc.baobob.vo.ParkingFee;
import spring.mvc.baobob.vo.ParkingHistory;
import spring.mvc.baobob.vo.hostTChartVO;

@Service
public class Host_totalServiceImpl implements Host_totalService{
	
	@Autowired
	Host_totalDAO dao;
	
/*----------------------------------------------------------------------------*/
	
	//ȸ�� ����Ʈ
	public void memList(HttpServletRequest req, Model model) {
		
		int pageSize = 15;		// �� �������� ����� �� ����
		int pageBlock = 5;		// �� ���� ������ ����
		
		int cnt = 0;			// �� ����
		int start = 0;			// ���� �������� �� ���۹�ȣ
		int end = 0;			// ���� �������� �� ��������ȣ
		int number = 0;			// ����� �۹�ȣ
		String pageNum = null;	// ������ ��ȣ
		int currentPage = 0;	// ���� ������
		
		int pageCount = 0;		// ������ ����
		int startPage = 0;		// ���� ������
		int endPage = 0;		// ������ ������
		
		
		//�� ���� ���ϱ�
		cnt = dao.getMemCnt();
		
		pageNum = req.getParameter("pageNum");
		
		if(pageNum == null) {
			pageNum = "1";	//ù�������� 1�������� ����
		}
		
		currentPage = Integer.parseInt(pageNum); //���� ������
		
		//������ ���� (pageSize�� 5�̰� ��ü �۰����� 12�� 2���� ���µ� �� 2���� �������� �Ҵ��� ����Ѵ�.)
		//pageCnt = 12 / 5 + 1; ... ������ 2���� 1�������� �Ҵ�ǹǷ� 3������(2������+1������)
		pageCount = (cnt / pageSize) + ((cnt % pageSize > 0) ? 1 : 0); 
		
		//���� ������ ���۹�ȣ
		start = (currentPage - 1) * pageSize + 1; 
		
		//���� ������ ����ȣ
		end = start + pageSize - 1;
		
		if(end > cnt) end = cnt;
		
		//1=21-(5(����������)-1)*5
		number = cnt - (currentPage -1) * pageSize; //����� �۹�ȣ..�ֽű�(ū������)�� 1������ ����� �۹�ȣ
		
		if(cnt > 0) {
			
			Map<String, Integer> map = new HashMap<String, Integer>();
			map.put("start", start);
			map.put("end", end);
			
			//�Խñ� ��� ��ȸ
			ArrayList<Member> dtos = dao.getMemList(map);
			model.addAttribute("dtos", dtos);
			
		}
		
		//4=(5/3)*3+1;
		startPage = (currentPage / pageBlock) * pageBlock + 1;
		if(currentPage % pageBlock == 0) startPage -= pageBlock; // (5 % 3 == 0)
		
		//6=4+3-1; 
		endPage = startPage + pageBlock - 1;
		if(endPage > pageCount) endPage = pageCount;
		
		model.addAttribute("cnt", cnt); //�۰���
		model.addAttribute("number", number); //�۹�ȣ
		model.addAttribute("pageNum", pageNum); //������ ��ȣ
		
		if(cnt > 0) {
			model.addAttribute("startPage", startPage); //���� ������
			model.addAttribute("endPage", endPage);//������ ������
			model.addAttribute("pageBlock", pageBlock);//����� ������ ����
			model.addAttribute("pageCount", pageCount);//������ ����
			model.addAttribute("currentPage", currentPage);//���� ������
		}
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ�� �߰�
	public void memAddPro(HttpServletRequest req, Model model) {
		Member vo = new Member();
		
		vo.setMember_name(req.getParameter("name"));
		vo.setMember_id(req.getParameter("id"));
		vo.setMember_step(Integer.parseInt(req.getParameter("step")));
		vo.setMember_pwd(req.getParameter("pwd"));
		vo.setMember_email(req.getParameter("email"));
		vo.setMember_birth(req.getParameter("birth"));
		vo.setMember_sex(req.getParameter("sex"));
		vo.setMember_tel(req.getParameter("tel"));
		vo.setMember_address(req.getParameter("address"));
		
		vo.setMember_reg_date(new Timestamp(System.currentTimeMillis()));
		
		int cnt = dao.insertMember(vo);
		
		model.addAttribute("cnt", cnt);
	}
	
/*----------------------------------------------------------------------------*/
	
	//���̵� �ߺ��˻�
	public void confirmId(HttpServletRequest req, Model model) {
		String id = req.getParameter("id");
		int cnt = dao.confirmId(id);
		System.out.println("confirmId: " + cnt);
		model.addAttribute("cnt", cnt);
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ�� ����, ������������
	public void hostTMemView(HttpServletRequest req, Model model) {
		String strId=req.getParameter("member_id");
		
		Member vo = dao.getMemberInfo(strId);
		model.addAttribute("vo", vo);
		
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ�� ���� ����ó��
	public void hostTMemModifyPro(HttpServletRequest req, Model model) {
		Member vo = new Member();
		
		vo.setMember_id(req.getParameter("memId"));
		vo.setMember_name(req.getParameter("name"));
		vo.setMember_step(Integer.parseInt(req.getParameter("step")));
		vo.setMember_pwd(req.getParameter("pwd"));
		vo.setMember_email(req.getParameter("email"));
		vo.setMember_tel(req.getParameter("tel"));
		vo.setMember_address(req.getParameter("address"));
		
		int cnt = dao.hostTMemModifyPro(vo);
		System.out.println("����2: " + req.getParameter("memId"));
		model.addAttribute("cnt", cnt);
	}
	
/*----------------------------------------------------------------------------*/
	
	//ȸ������ ó��������
	public void hostTMemDelPro(HttpServletRequest req, Model model) {
		int deleteCnt =0;
		
		String strId = req.getParameter("member_id");
		
		deleteCnt = dao.hostTMemDelPro(strId);
		
		model.addAttribute("deleteCnt", deleteCnt);
	}
	
/*----------------------------------------------------------------------------*/
	
	//��ȭ�� ���������
	public void movieChart(HttpServletRequest req, Model model) {
		//���Ǹž�
		int movieSale = dao.getMovieSale(); 
		model.addAttribute("movieSale",movieSale);
		
		Map<String , Object> map = new HashMap<String,Object>();
		String[] janre = {"1","2","3","4","5","6","7","8","9","10"};

		//��ǰ������ ���ż�
		//mapper���� �ҷ��� kind�� sum�� �ٰ��̱⶧���� vo������ List������ �޾��ش�.
		List<hostTChartVO> voList = dao.getMovieChart(); 
		
		//vo������Ÿ�� i �� List�����͵��� �ѰǾ� ���ͼ� map�� ����ش�.
		//(map�� key���� String�̱⶧���� int���� kind�� String���� ����ȯ ���ش�.
		for (hostTChartVO i : voList) {
			map.put(Integer.toString(i.getKind()) , i.getSum());
		}
		
		//Ű���� ������ 0���� �ʱ�ȭ 
		for(String s : janre) {
			int chk = 0;
			for(Entry<String, Object> m : map.entrySet()) {
				if(s.equals(m.getKey())) {
					chk=1;
				}
			}
			if(chk==0) {
				map.put(s, 0);
			}
		}
		
		model.addAttribute("movieChart",map);
		
		//test��(feat.�ؿ�)
		System.out.println("�ؿ�'s pointcut");
		map.forEach((k,v)->{
			System.out.println(k + " : " + v);
		});
	}
	
/*----------------------------------------------------------------------------*/
	
	//�Ĵ� ���������
	public void restaurantChart(HttpServletRequest req, Model model) {
		//���Ǹž�
		int restaurantSale = dao.getRestaurantSale(); 
		model.addAttribute("restaurantSale",restaurantSale);
		
		Map<String,Object> map = new HashMap<String,Object>();
		String[] janre = {"1","2","3"};
		
		List<hostTChartVO> voList = dao.getRestaurantChart();
		
		for(hostTChartVO i : voList) {
			map.put(Integer.toString(i.getKind()), i.getSum());
		}
		
		//Ű���� ������ 0���� �ʱ�ȭ 
		for(String s : janre) {
			int chk = 0;
			for(Entry<String, Object> m : map.entrySet()) {
				if(s.equals(m.getKey())) {
					chk=1;
				}
			}
			if(chk==0) {
				map.put(s, 0);
			}
		}
		
		model.addAttribute("restaurantChart", map);
		
		//test��(feat.�ؿ�)
		System.out.println("����'s pointcut");
		map.forEach((k,v)->{
			System.out.println(k + " : " + v);
		});
		
	}
	
/*----------------------------------------------------------------------------*/
	
	//������ ���������
	public void getParkingPayChart(HttpServletRequest req, Model model) {
		// ���� ���� �ð��� ���� �ݾװ� ���� ���� �ݾ�

		// ���� ���
		ParkingFee fee = dao.getParkingFee();

		// ���� ���� ����
		ArrayList<ParkingHistory> thisPh = dao.getThisYearPayList();

		Map<String, Integer> timePrice = new HashMap<String, Integer>(); // ���� ���� �ð��� ���� �ݾ�
		Map<String, Integer> userPrice = new HashMap<String, Integer>(); // ���� ���� �ݾ�
		
		for (ParkingHistory ph : thisPh) {
			long userTime = ph.getP_history_out().getTime() - ph.getP_history_in().getTime();
			long minute = (userTime / 1000) / 60; // �̿� �ð�(��)

			String month = ph.getP_history_out().toString().substring(5, 7); // �̿��� ��
			long time = minute - fee.getP_fee_base_time(); // �̿� �ʰ� �ð�
			int price = fee.getP_fee_base_price(); // �⺻ ���
			
			while (time > 0) {
				time -= fee.getP_fee_exc_time();
				price += fee.getP_fee_exc_price();
			}

			if (timePrice.get("P" + month) != null) {
				timePrice.put("P" + month, timePrice.get("P" + month) + price);
				userPrice.put("U" + month, userPrice.get("U" + month) + ph.getP_history_price());
			} else {
				timePrice.put("P" + month, price);
				userPrice.put("U" + month, ph.getP_history_price());
			}
		}

		String[] month = { "01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11", "12" };
		for (int i = 0; i < month.length; i += 1) {
			boolean flg = false;
			for (Entry<String, Integer> e : timePrice.entrySet()) {
				if (e.getKey().equals("P" + month[i])) {
					flg = true;
					model.addAttribute("P" + month[i], e.getValue());
					model.addAttribute("U" + month[i], userPrice.get("U" + month[i]));
				}
			}
			if (!flg) {
				model.addAttribute("P" + month[i], 0);
				model.addAttribute("U" + month[i], 0);
			}
		}
		
	}
	
	/*----------------------------------------------------------------------------*/
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
