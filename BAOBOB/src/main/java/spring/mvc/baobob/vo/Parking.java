package spring.mvc.baobob.vo;

import java.sql.Timestamp;

public class Parking {
//park_tbl
	private int park_index; //park_tbl_SEQ
	private int park_state; //1:��������, 0:�����Ұ��� -  0(����.������)/1(����X.��������)
	private int park_theme; //������, �ӻ��, �����, �Ϲ�
	private Timestamp park_last_date; //������ ���� �ð� - ParkingHistory�� p_history_in = 5�� ����
	private Timestamp park_last_out;

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
	
	public Timestamp getPark_last_out() {
		return park_last_out;
	}

	public void setPark_last_out(Timestamp park_last_out) {
		this.park_last_out = park_last_out;
	}

	public String getParkState() {
		switch(park_state) {
		case 1: return "���� ����";
		default: return "������";
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
