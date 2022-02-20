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

}
