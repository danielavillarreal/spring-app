package springapp.domain;

import java.util.Date;

public class Appointment {
	private final Integer id;
	private final java.sql.Date start_date;
	private final java.sql.Date end_date;
	private final Integer clientId;
	
	public Appointment(Integer id, java.sql.Date start_date, java.sql.Date end_date, Integer clientId ){
		this.id = id;
		this.start_date = start_date;
		this.end_date = end_date;
		this.clientId = clientId;
	}
	
	public Integer getId() {
		return id;
	}

	public java.sql.Date getStartDate() {
		return start_date;
	}
	
	public java.sql.Date getEndDate() {
		return end_date;
	}
	
	public Integer getClientId() {
		return clientId;
	}

	public static Appointment valueOf(java.sql.Date startDate) {
		// TODO Auto-generated method stub
		return null;
	}


}
