package spring.mvc.baobob.host_parking.persistence;

import java.util.ArrayList;

import spring.mvc.baobob.vo.Parking;
import spring.mvc.baobob.vo.ParkingFee;
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
}
