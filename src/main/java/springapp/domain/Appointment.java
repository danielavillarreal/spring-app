package springapp.domain;

import java.time.LocalDate;

public class Appointment {
	private final Integer id;
	private final LocalDate date;
	private final String time;
	private final Integer clientId;
	
	public Appointment(Integer id, LocalDate date, String time, Integer clientId ){
		this.id = id;
		this.date = date;
		this.time = time;
		this.clientId = clientId;
	}
	
	public Integer getId() {
		return id;
	}

	public LocalDate getDate() {
		return date;
	}
	
	public String getTime() {
		return time;
	}
	
	public Integer getClientId() {
		return clientId;
	}


}
