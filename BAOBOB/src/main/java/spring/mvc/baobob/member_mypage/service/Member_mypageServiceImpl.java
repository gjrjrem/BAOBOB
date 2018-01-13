package spring.mvc.baobob.member_mypage.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import spring.mvc.baobob.member_mypage.persistence.Member_mypageDAO;
import spring.mvc.baobob.vo.BoardVO;
import spring.mvc.baobob.vo.Member;

@Service
public class Member_mypageServiceImpl implements Member_mypageService{
	
	@Autowired
	Member_mypageDAO dao;
	
	
	//1:1���� ����Ʈ
	public void memQuestionList(HttpServletRequest req, Model model) {
		
		int pageSize = 10;		// �� �������� ����� �� ����
		int pageBlock = 3;		// �� ���� ������ ����
		
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
		cnt = dao.getArticleCnt();
		
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
			ArrayList<BoardVO> dtos = dao.getArticleList(map);
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
		System.out.println(cnt);
		
		if(cnt > 0) {
			model.addAttribute("startPage", startPage); //���� ������
			model.addAttribute("endPage", endPage);//������ ������
			model.addAttribute("pageBlock", pageBlock);//����� ������ ����
			model.addAttribute("pageCount", pageCount);//������ ����
			model.addAttribute("currentPage", currentPage);//���� ������
		}
		
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� ��
	public void memQuestionContentForm(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		int number = Integer.parseInt(req.getParameter("number"));
		
		//�������� ��������...1��
		BoardVO dto = dao.getArticle(num);
		
		//��ȸ�� ����
		dao.addReadCnt(num);
		
		//jsp�� ���� �ѱ��. (dto, pageNum, number)
		model.addAttribute("dto", dto);
		model.addAttribute("pageNum", pageNum);
		model.addAttribute("number", number);
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� ���� �� ������
	public void memQModifyView(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String strPwd = req.getParameter("pwd");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("strPwd", strPwd);
		
		int selectCnt = dao.pwdCheck(map);
		
		if(selectCnt == 1) {
			BoardVO dto = dao.getArticle(num);
			model.addAttribute("dto", dto);
		}
		
		//jsp�� ������ �ѱ��.
		model.addAttribute("selectCnt", selectCnt); //selectCnt�� 0�̸� �н����尡 Ʋ�ȴٰ� �ѷ��ֱ�����
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� ���� ó��
	public void memQModifyPro(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		BoardVO dto = new BoardVO();
		
		dto.setBoard_index(num);
		dto.setBoard_subject(req.getParameter("subject"));
		dto.setBoard_content(req.getParameter("content"));
		dto.setBoard_pwd(req.getParameter("pwd"));
		
		int cnt = dao.updateQuestion(dto);
		
		model.addAttribute("cnt", cnt);
		model.addAttribute("num", num);
		model.addAttribute("pageNum", pageNum);
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� �ۼ� ó��������
	public void memQWritePro(HttpServletRequest req, Model model) {
		BoardVO dto = new BoardVO();
		
		//2.ȭ�����κ��� �Է¹��� ������ �����ٱ���(DTO)�� ��´�.
		dto.setMember_id((String)req.getSession().getAttribute("memId"));
		dto.setBoard_pwd(req.getParameter("pwd"));
		dto.setBoard_subject(req.getParameter("subject"));
		dto.setBoard_content(req.getParameter("content"));
		
		//3.hidden���κ��� �Ѱܹ��� ���� ���� �ٱ���(DTO)�� ��´�.
		dto.setBoard_index(Integer.parseInt(req.getParameter("num")));
		dto.setBoard_ref(Integer.parseInt(req.getParameter("ref")));
		dto.setBoard_ref_step(Integer.parseInt(req.getParameter("ref_step")));
		dto.setBoard_ref_level(Integer.parseInt(req.getParameter("ref_level")));
		dto.setBoard_reg_date(new Timestamp(System.currentTimeMillis()));
		dto.setBoard_ip(req.getRemoteAddr()); 
		
		//5.insertBoard()
		int cnt = dao.insertQuestion(dto);
		
		//6.jsp�� �ѱ� ���� �����Ѵ�.(setAttribute)
		model.addAttribute("cnt", cnt);
	}
	
/*----------------------------------------------------------------------------*/
	
	//1:1���� ���� ó��������
	public void memQDelPro(HttpServletRequest req, Model model) {
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String strPwd = req.getParameter("pwd");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("num", num);
		map.put("strPwd", strPwd);
		
		//num�� ��ġ�� ��� ��й�ȣ ��ġ�ϴ��� Ȯ��
		int selectCnt = dao.pwdCheck(map);
		
		if(selectCnt == 1) {
			int deleteCnt = dao.deleteQuestion(num);
			model.addAttribute("deleteCnt", deleteCnt);
		}
		
		model.addAttribute("selectCnt", selectCnt);
		model.addAttribute("pageNum", pageNum);
	}
	
/*----------------------------------------------------------------------------*/
	
	//�������� �Է�������
	public void memPModifyView(HttpServletRequest req, Model model) {
		//1�ܰ�. ȭ�����κ��� ���̵�, �н����� ���� �޾ƿ´�.
		String strId=(String) req.getSession().getAttribute("memId"); 
		String strPwd=req.getParameter("pwd");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("strId", strId);
		map.put("strPwd", strPwd);
		
		int selectCnt=dao.memPCheck(map);
		
		//���̵�� �н����尡 ��ġ�ϸ�, �����ϱ����ؼ� �Է��� ������ �о�´�.
		if(selectCnt == 1) {
			System.out.println("selectCnt == 1");
			Member vo = dao.getMemberInfo(strId);
			model.addAttribute("vo", vo);
		}
		
		//3�ܰ�. request�� session�� ó�� ����� �����ϰ� jsp(view)���� �޴´�.
		model.addAttribute("selectCnt", selectCnt);
		
	}
	
/*----------------------------------------------------------------------------*/
	
	//�������� ó��������
	public void memPPro(HttpServletRequest req, Model model) {
		Member vo = new Member();
		
		String id = (String)req.getSession().getAttribute("memId");
		vo.setMember_id(id);
		vo.setMember_pwd(req.getParameter("pwd"));
		vo.setMember_name(req.getParameter("name"));
		vo.setMember_address(req.getParameter("address"));
		
		//hp
		String hp = "";
		String hp1=req.getParameter("hp1");
		String hp2=req.getParameter("hp2");
		String hp3=req.getParameter("hp3");
		
		//�ʼ��Է� �׸��� �ƴϹǷ� null üũ���� ������ insert�ϸ� null pointer Exception �߻�
		if(!hp1.equals("") && !hp2.equals("") && !hp3.equals("")) {
			 hp = hp1 + "-" + hp2 + "-" + hp3;
		}
		//�ڵ��� ��ȣ�� �������ٸ� if���� Ÿ������	hp = "";�� ����.
		vo.setMember_tel(hp);
		
		//email
		String email="";
		String email1=req.getParameter("email1");
		String email2=req.getParameter("email2");
		email = email1 + "@" + email2;
		vo.setMember_email(email);
		
		int cnt = dao.updateMember(vo);
		
		model.addAttribute("cnt", cnt);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
