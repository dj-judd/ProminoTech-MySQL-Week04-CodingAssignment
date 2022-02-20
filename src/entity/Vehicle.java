package entity;

public class Vehicle {

	private int vehicleId;
	private String vehicleType;
	private int year;
	private String make;
	private String model;
	
	public Vehicle(int vehicleId, String vehicleType, int year, String make, String model) {
		this.setVehicleId(vehicleId);
		this.setVehicleType(vehicleType);
		this.setYear(year);
		this.setMake(make);
		this.setModel(model);
	}

	public int getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(int vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getVehicleType() {
		return vehicleType;
	}

	public void setVehicleType(String vehicleType) {
		this.vehicleType = vehicleType;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getMake() {
		return make;
	}

	public void setMake(String make) {
		this.make = make;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}
	
	
	
}
