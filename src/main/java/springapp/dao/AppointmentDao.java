package springapp.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import springapp.domain.Appointment;

@Repository
@Scope("singleton")
public class AppointmentDao {
	
	private Logger logger = LoggerFactory.getLogger(AppointmentDao.class);

	RowMapper<Appointment> simpleAppointmentMapper = new RowMapper<Appointment>() {

		@Override
		public Appointment mapRow(ResultSet rs, int rowNum) throws SQLException {
			java.sql.Date startDate = rs.getDate("start_date");
			Appointment date = null;
			if(startDate!= null) {
				date = Appointment.valueOf(startDate);

			}
			return new Appointment(rs.getInt("id"), rs.getDate("start_date"), rs.getDate("end_date"), rs.getInt("client_id"));
		}
	};
	
	
    @Autowired
    JdbcTemplate jdbcTemplate;
    	
	public List<Appointment> list(){
		List<Appointment> queryResult = jdbcTemplate.query(
				"SELECT id, start_date, end_date, client_id FROM appointments",
				simpleAppointmentMapper);
		
		
		return queryResult;
	}
	
	public List<Appointment> listForClient(int clientId){
		List<Appointment> queryResult = jdbcTemplate.query(
				"SELECT id, start_date, end_date, client_id FROM appointments WHERE client_id = ?",
				new Object[] {clientId},
				simpleAppointmentMapper);
		
		
		return queryResult;
	}
	
	public Appointment get(int id) {
		List<Appointment> queryResult = jdbcTemplate.query(
				"SELECT id, start_date, end_date, client_id FROM appointments WHERE id = ? LIMIT 1", 
				new Object[] {id},
				simpleAppointmentMapper);
		
		if(queryResult.isEmpty()) {
			return null;
		}
		
		return queryResult.get(0);
		
		
	}
	
	public Appointment save(Appointment appointment) {
		Integer id = appointment.getId();
		if(id == null) {
			
			KeyHolder holder = new GeneratedKeyHolder();

			jdbcTemplate.update(new PreparedStatementCreator() {
				
				@Override
				public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
					PreparedStatement statement = con.prepareStatement("INSERT INTO appointment(start_date, end_date, client_id) VALUES (?, ?, ?)");
					statement.setDate(1, appointment.getStartDate());
					statement.setDate(2, appointment.getEndDate());
					statement.setInt(3, appointment.getClientId());
					return statement;

				}
			}, holder);
			
			id = holder.getKey().intValue();
			
		} else {
			// notice that we do not update the client id since we do not want to enable pet transfer from this method
			jdbcTemplate.update("UPDATE appointments SET start_date = ?, end_date = ? WHERE id = ?",
					new Object[] {appointment.getStartDate(), appointment.getEndDate(), id});
		}
		
		return get(id);
	}
	
	public void delete(int id) {
		
		jdbcTemplate.update("DELETE FROM appointments WHERE id = ?",
				new Object[] {id});
		
	}
}