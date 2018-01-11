package spring.mvc.baobob.host_parking.persistence;

import spring.mvc.baobob.vo.Parking;
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
	
	//������ �� ���� ���� ��ϼ���
	public int parkingChange(Parking space);
	
	//������ �� ���� ���� ����
	public int parkingUpdate(Parking space);
}
