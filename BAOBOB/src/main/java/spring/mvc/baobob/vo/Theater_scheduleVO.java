package spring.mvc.baobob.vo;

import java.sql.Timestamp;

public class Theater_scheduleVO {
	public int theater_schedule_index;
	public int movie_index;
	public int theater_index;
	public Timestamp schedule_startDate;
	public Timestamp schedule_startTime;
	public Timestamp schedule_endTime;
	public int schedule_MDNstate;

	public int getTheater_schedule_index() {
		return theater_schedule_index;
	}

	public void setTheater_schedule_index(int theater_schedule_index) {
		this.theater_schedule_index = theater_schedule_index;
	}

	public int getMovie_index() {
		return movie_index;
	}

	public void setMovie_index(int movie_index) {
		this.movie_index = movie_index;
	}

	public int getTheater_index() {
		return theater_index;
	}

	public void setTheater_index(int theater_index) {
		this.theater_index = theater_index;
	}

	public Timestamp getSchedule_startDate() {
		return schedule_startDate;
	}

	public void setSchedule_startDate(Timestamp schedule_startDate) {
		this.schedule_startDate = schedule_startDate;
	}

	public Timestamp getSchedule_startTime() {
		return schedule_startTime;
	}

	public void setSchedule_startTime(Timestamp schedule_startTime) {
		this.schedule_startTime = schedule_startTime;
	}

	public Timestamp getSchedule_endTime() {
		return schedule_endTime;
	}

	public void setSchedule_endTime(Timestamp schedule_endTime) {
		this.schedule_endTime = schedule_endTime;
	}

	public int getSchedule_MDNstate() {
		return schedule_MDNstate;
	}

	public void setSchedule_MDNstate(int schedule_MDNstate) {
		this.schedule_MDNstate = schedule_MDNstate;
	}

}
