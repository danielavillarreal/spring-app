package springapp.command;



import springapp.domain.Appointment;
import springapp.domain.Client;

/**
 * This command class is used to pass information back and force between the client and the server
 * 
 */
public class AppointmentCommand {
	
	private Integer id;
	private java.sql.Date start_date;
	private java.sql.Date end_date;
	private Integer clientId;
	private Client client;
	
	public AppointmentCommand(Integer clientId) {
		this.clientId = clientId;
	}

	public AppointmentCommand(Appointment appointment) {
		if (appointment != null) {
			this.clientId = appointment.getClientId();
			this.id = appointment.getId();
			this.start_date = appointment.getStartDate();
			this.end_date = appointment.getEndDate();
		}
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public java.sql.Date getStartDate() {
		return start_date;
	}
	
	public void setStartDate(java.sql.Date start_date) {
		this.start_date = start_date;
	}
	
	public java.sql.Date getEndDate() {
		return end_date;
	}
	
	public void setEndDate(java.sql.Date end_date) {
		this.end_date = end_date;
	}
	
	public Client getClient() {
		return client;
	}
	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public Integer getClientId() {
		return clientId;
	}
	
	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}
}