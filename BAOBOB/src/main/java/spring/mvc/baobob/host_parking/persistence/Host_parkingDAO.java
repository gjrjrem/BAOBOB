package spring.mvc.baobob.host_parking.persistence;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Map;

import spring.mvc.baobob.vo.Parking;
import spring.mvc.baobob.vo.ParkingFee;
import spring.mvc.baobob.vo.ParkingHistory;
import spring.mvc.baobob.vo.ParkingSpace;

public interface Host_parkingDAO {

	//������ ���� ����
	public ParkingSpace getParkingSpace();
	
	//������ ���� ���� ���
	public int parkingSpaceChange(ParkingSpace ps);
	
	//������ ���� ���� ����
	public int parkingSpaceUpdate(ParkingSpace ps);
	
	//������ �ش� ���� ����
	public Parking getParking(int park_index);
	
	//������ �� ���� ���� ���, ����
	public int parkingChange(Parking space);
	
	//������ �� ���� ���� ����
	public int parkingUpdate(Parking space);
	
	//������ ���� ���� ����
	public ArrayList<String> getParkingStates(int last_idx);
	
	//���� �⺻ ���
	public ParkingFee getParkingFee();
	
	//���� �⺻ ��� ���� ����
	public int getParkingFeeCount();
	
	//���� �⺻ ��� ���, ����
	public int parkingFeeChange(ParkingFee pf);
	
	//���� �⺻ ��� ����
	public int parkingFeeUpdate(ParkingFee pf);
	
	//���� ���� �Ѱ���
	public int getParkingHistoryCnt();
	
	//���� ����
	public ArrayList<ParkingHistory> getParkingHistory(Map<String, Integer> map);

	//���� ���� �Ѱ���
	public int getParkingPayCnt();
	
	//���� ����
	public ArrayList<ParkingHistory> getParkingPayList(Map<String, Integer> map);
	
	//�ش� ���� ���� ����
	public Parking getSpaceState(int park_index);
	
	//�ش� ���� ������ ������ �����
	public ArrayList<String> getParkLastDateMember(Timestamp park_last_date);
}
