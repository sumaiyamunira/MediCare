package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

import java.util.Date;

public class AppointmentReminder {
	private String appointmentName;
	private int timeHour;
	private int timeMinute;
	private int serialNo;
	private String time;
	private Date date;
	private int dateYear;
	private int dateMonth;
	private int dateDay;
	
	
	public String getAppointmentName() {
		return appointmentName;
	}
	public void setAppointmentName(String appointmentName) {
		if(appointmentName.equalsIgnoreCase("") || appointmentName.equalsIgnoreCase(null))
		{
			this.appointmentName = "Untitled";
		}
		else
		{
		this.appointmentName = appointmentName;
		}
	}
	public int getTimeHour() {
		return timeHour;
	}
	public void setTimeHour(int timeHour) {
		this.timeHour = timeHour;
	}
	public int getTimeMinute() {
		return timeMinute;
	}
	public void setTimeMinute(int timeMinute) {
		this.timeMinute = timeMinute;
	}
	public int getSerialNo() {
		return serialNo;
	}
	public void setSerialNo(int serialNo) {
		this.serialNo = serialNo;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getDateYear() {
		return dateYear;
	}
	public void setDateYear(int dateYear) {
		this.dateYear = dateYear;
	}
	public int getDateMonth() {
		return dateMonth;
	}
	public void setDateMonth(int dateMonth) {
		this.dateMonth = dateMonth;
	}
	public int getDateDay() {
		return dateDay;
	}
	public void setDateDay(int dateDay) {
		this.dateDay = dateDay;
	}

}
