package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import android.R.string;

public class Reminder {
	private int id;
	private String name;
	private String startDate;
	private String endDate;
	private int doesPerDay;
	private String time;
	private int serialNo;
	private int reminderType; // 0 for appointment, 1 for medicine 
	private int status; //status 0 for active, status 1 for deleted/deactivated
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public int getDoesPerDay() {
		return doesPerDay;
	}
	public void setDoesPerDay(int doesPerDay) {
		this.doesPerDay = doesPerDay;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public int getReminderType() {
		return reminderType;
	}
	public void setReminderType(int reminderType) {
		this.reminderType = reminderType;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
