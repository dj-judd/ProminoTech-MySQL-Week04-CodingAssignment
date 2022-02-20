package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import entity.Vehicle;

public class VehicleDao {
	
	private Connection connection;
	private final String GET_VEHICLES_QUERY = "SELECT * FROM vehicles";
	private final String CREATE_NEW_VEHICLE = "INSERT INTO vehicle_table(type, year, make, model) VALUES (?, ?, ?, ?)";
	private final String UPDATE_VEHICLE_BY_ID = "UPDATE vehicle_table SET type = ?, year = ?, make = ?, model = ? WHERE id = ?";
	private final String DELETE_VEHICLE_BY_ID = "DELETE FROM vehicle_table WHERE id = ?";
	
	public VehicleDao() {
		
		connection = DB_Connection.getConneciton();
		
	}
	
	public List<Vehicle> getVehicles() throws SQLException {
		ResultSet rs = connection.prepareStatement(GET_VEHICLES_QUERY).executeQuery();
		List<Vehicle> vehicles = new ArrayList<Vehicle>();
		return vehicles;
	}
	
	public void createNewVehicle(String type, int year, String make, String model) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(CREATE_NEW_VEHICLE);
		ps.setString(1, type);
		ps.setInt(2, year);
		ps.setString(3, make);
		ps.setString(4, model);
		ps.executeUpdate();
	}
	
	public void updateVehicleById(int id, String type, int year, String make, String model) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(UPDATE_VEHICLE_BY_ID);
		ps.setInt(5, id);
		ps.setString(1, type);
		ps.setInt(2, year);
		ps.setString(3, make);
		ps.setString(4, model);
		ps.executeUpdate();
	}
	
	public void deleteVehicleById(int id) throws SQLException {
		PreparedStatement ps = connection.prepareStatement(DELETE_VEHICLE_BY_ID);
		ps.setInt(1, id);
		ps.executeUpdate();
		
	}

}
