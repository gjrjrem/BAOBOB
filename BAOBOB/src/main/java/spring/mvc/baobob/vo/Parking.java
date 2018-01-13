package spring.mvc.baobob.vo;

import java.sql.Timestamp;

public class Parking {
//park_tbl
	private int park_index; //park_tbl_SEQ
	private int park_state; //0:��������, 1:�����Ұ���
	private int park_theme;
	private Timestamp park_last_date; //������ ���� �ð� - ParkingHistory�� p_history_in = 5�� ����

	public int getPark_index() {
		return park_index;
	}

	public void setPark_index(int park_index) {
		this.park_index = park_index;
	}

	public int getPark_state() {
		return park_state;
	}

	public void setPark_state(int park_state) {
		this.park_state = park_state;
	}

	public int getPark_theme() {
		return park_theme;
	}

	public void setPark_theme(int park_theme) {
		this.park_theme = park_theme;
	}

	public Timestamp getPark_last_date() {
		return park_last_date;
	}

	public void setPark_last_date(Timestamp park_last_date) {
		this.park_last_date = park_last_date;
	}

	public String getParkState() {
		switch(park_state) {
		case 0: return "���� ����";
		default: return "���� �Ұ���";
		}
	}
	
	public String getParkTheme() {
		switch(park_theme) {
		case 1: return "������";
		case 2: return "�ӻ��";
		case 3: return "�����";
		default: return "�Ϲ�";
		}
	}
}
