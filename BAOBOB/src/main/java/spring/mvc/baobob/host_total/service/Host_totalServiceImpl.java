package spring.mvc.baobob.host_total.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.host_total.persistence.Host_totalDAO;
import spring.mvc.baobob.vo.Member;

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
		System.out.println("��꼭��");
		
		//���Ǹž��� ������
		int movieSale = dao.getMovieSale(); 
		model.addAttribute("movieSale",movieSale);
		System.out.println("�Ѿ�" + movieSale);
		
		//��ǰ������ ���ż�
		//Map<String,Integer> map = (Map<String,Integer>)dao.getMovieChart(); 
		//model.addAttribute("movieChart",map);
	}
	
	
	
	
	
	
	
	
	
	
	

}
