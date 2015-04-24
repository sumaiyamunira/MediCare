package com.xlifestation.medical.health.doctor.hospital.bmi.reminder.emergency.medicare;

//import android.os.Parcel;
//import android.os.Parcelable;
//import android.text.format.Time;

public class MedicineReminder {
	private String medicineName;
	private int doesPerDay;
	private int doesDuration;
	private int firstDoeshHour;
	private int firstDoeshMinute;
	private int intervalBetweenDosage;
	private String startDate;
	private String endDate;

	public String getMedicineName() {
		return medicineName;
	}

	public void setMedicineName(String medicineName) {
		if(medicineName.equalsIgnoreCase("") || medicineName.equalsIgnoreCase(null))
		{
			this.medicineName = "Untitled";
		}
		else
		{
		this.medicineName = medicineName;
		}
	}

	public int getDoesPerDay() {
		return doesPerDay;
	}

	public void setDoesPerDay(int doesPerDay) {
		this.doesPerDay = doesPerDay;
	}

	public int getDoesDuration() {
		return doesDuration;
	}

	public void setDoesDuration(int doesDuration) {
		this.doesDuration = doesDuration;
	}
	
	public int getFirstDoeshHour() {
		return firstDoeshHour;
	}

	public void setFirstDoeshHour(int firstDoeshHour) {
		this.firstDoeshHour = firstDoeshHour;
	}

	public int getFirstDoeshMinute() {
		return firstDoeshMinute;
	}

	public void setFirstDoeshMinute(int firstDoeshMinute) {
		this.firstDoeshMinute = firstDoeshMinute;
	}

	public int getintervalBetweenDosage() {
		return intervalBetweenDosage;
	}

	public void setintervalBetweenDosage(int intervalBetweenDosage) {
		this.intervalBetweenDosage = intervalBetweenDosage;
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
	
//	public static final Parcelable.Creator<MedicineReminder> CREATOR = new Creator<MedicineReminder>() { 
//			  public MedicineReminder createFromParcel(Parcel source) { 
//				  MedicineReminder mediReminder = new MedicineReminder(); 
//				  mediReminder.medicineName = source.readString(); 
//				  mediReminder.doesPerDay = source.readInt(); 
//				  mediReminder.doesDuration = source.readInt();
//				  mediReminder.firstDoeshHour = source.readInt(); 
//				  mediReminder.firstDoeshMinute = source.readInt(); 
//				  mediReminder.intervalBetweenDosage = source.readInt(); 
//				  
//			      return  mediReminder; 
//			  } 
//			  public MedicineReminder[] newArray(int size) { 
//			      return new MedicineReminder[size]; 
//			  } 
//		     };
//
//	@Override
//	public int describeContents() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel parcel, int flags) {
//		// TODO Auto-generated method stub
//		 parcel.writeString(medicineName); 
//		 parcel.writeInt(doesPerDay); 
//		 parcel.writeInt(doesDuration);
//		 parcel.writeInt(firstDoeshHour);
//		 parcel.writeInt(firstDoeshMinute);
//		 parcel.writeInt(intervalBetweenDosage);
//	}

}
