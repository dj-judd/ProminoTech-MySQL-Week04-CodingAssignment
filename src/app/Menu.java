package app;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import dao.VehicleDao;
import entity.Vehicle;

public class Menu {
	
	private VehicleDao vehicleDao = new VehicleDao();
	private Scanner scanner = new Scanner(System.in);
	private List<String> menuOptions = Arrays.asList(
			"Display Vehicles",
			"Add a new vehicle",
			"Update an existing vehicle",
			"Delete a vehicle");
	
	public void start() {
		String selection = "";
		
		do {
			printMenu();
			selection = scanner.nextLine();
			
			try {
				if (selection.equals("1")) {
					displayVehicles();
				} else if (selection.equals("2")) {
					enterNewVehicle();
				} else if (selection.equals("3")) {
					updateVehicle();
				} else if (selection.equals("4")) {
					deleteVehicle();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			System.out.println("Press ENTER to continue...");
			scanner.nextLine();
			
		} while (!selection.equals("-1"));
			
	}
	
	private void printMenu() {
		System.out.println("Select an option:\n-----------------------------");
		for (int i = 0; i < menuOptions.size(); i++) {
			System.out.println(i + 1 + ") " + menuOptions.get(i));
		}
	}
	
	private void displayVehicles() throws SQLException {


		List<Vehicle> vehicles = vehicleDao.getVehicles();
		
		for (Vehicle vehicle : vehicles) {
					System.out.println("\t" + vehicle.getVehicleId() + 
							  " : " + vehicle.getVehicleType() + 
							  " : " + vehicle.getYear() + 
							  " : " + vehicle.getMake() + 
							  " : " + vehicle.getModel());
		}


	
	}
	
	private void enterNewVehicle() throws SQLException {
		
		String vehicleType;
		int vehicleYear;
		String vehicleMake;
		String vehicleModel;
		Boolean exit = false;
		Set<String> acceptedVehicleTypes = new HashSet<String>();
			acceptedVehicleTypes.add("Car");
			acceptedVehicleTypes.add("Boat");
			acceptedVehicleTypes.add("Plane");
			
		Boolean typeCheck = false;
		Boolean yearCheck = false;
		//Boolean makeCheck = false;
		//Boolean modelCheck = false;
		
		
		do {
			System.out.print("Enter Vehicle Type (Car, Boat, or Plane): ");
			vehicleType = scanner.nextLine();
			
			do {
				if (!acceptedVehicleTypes.contains(vehicleType)) {
				System.out.println("\n!!!---ERROR---!!!!\nOnly 'Car', 'Boat', or 'Plane' are valid entrys for Vehicle Type. (Case-Sensitive)\n");
				
				System.out.print("Enter Vehicle Type (Car, Boat, or Plane): ");
				vehicleType = scanner.nextLine();
				
				} else typeCheck = true;
			} while (!typeCheck);
			
			vehicleYear = yearEntry();
			
			if (vehicleYear > 0) {
				yearCheck = true;
			}
			
			System.out.print("Enter Vehicle Make: ");
			vehicleMake = scanner.nextLine();
			
			System.out.print("Enter Vehicle Model: ");
			vehicleModel = scanner.nextLine();
			
			
			
			if (typeCheck && yearCheck) {
				exit = true;
			}
			
		} while (!exit);
		

		
		vehicleDao.createNewVehicle(vehicleType, vehicleYear, vehicleMake, vehicleModel);
	}
	
	private void updateVehicle() throws SQLException {
		int id;
		String vehicleType;
		int vehicleYear;
		String vehicleMake;
		String vehicleModel;
		Boolean exit = false;
		Set<String> acceptedVehicleTypes = new HashSet<String>();
			acceptedVehicleTypes.add("Car");
			acceptedVehicleTypes.add("Boat");
			acceptedVehicleTypes.add("Plane");
		
		Boolean idCheck = false;	
		Boolean typeCheck = false;
		Boolean yearCheck = false;
		//Boolean makeCheck = false;
		//Boolean modelCheck = false;
	
		do {
			
			id = idEntry("Enter Vehicle Id of Vehicle to update: ");
			idCheck = intEntryCheck(id);
			
			vehicleType = typeEntry("Enter Vehicle Type (Car, Boat, or Plane): ");
			typeCheck = boolEntryCheck(acceptedVehicleTypes.contains(vehicleType));
			
			
			vehicleYear = yearEntry();
			yearCheck = intEntryCheck(vehicleYear);

			
			System.out.print("Enter Vehicle Make: ");
			vehicleMake = scanner.nextLine();
			
			System.out.print("Enter Vehicle Model: ");
			vehicleModel = scanner.nextLine();
			
			
			
			if (idCheck && typeCheck && yearCheck) {
				exit = true;
			}
			
		} while (!exit);
		
		vehicleDao.updateVehicleById(id, vehicleType, vehicleYear, vehicleMake, vehicleModel);
	}
	
	private void deleteVehicle() throws SQLException {
		
		String deleteCheckEntry;
		Boolean deleteCheck = false;
		
		int id = idEntry("Enter Vehicle Id to DELETE entry: ");

		if (id > 0) {
			System.out.println("Are you sure you want to DELETE the vehichle entry with the ID: " + id + " ?");
			System.out.println("Type 'yes' to confirm or 'no' to cancel. (No is defalut)");
			deleteCheckEntry = scanner.nextLine();
			
			if(deleteCheckEntry.equalsIgnoreCase("no") || deleteCheckEntry.equalsIgnoreCase("n") || deleteCheckEntry.equalsIgnoreCase("")) {
			} else if(deleteCheckEntry.equalsIgnoreCase("yes") || deleteCheckEntry.equalsIgnoreCase("y")) {
				vehicleDao.deleteVehicleById(id);
			} else System.out.println("Use: Yes, No ,Y , or N");
		}
		

	}

	private int idEntry(String passedString) {
		Boolean idCheck = false;
		int idEntry = 0;
		
		while (!idCheck) {
			//scanner.nextInt()'s mom is a ho. All of this code to catch it's exception gracefully.
			try {
				
				System.out.print(passedString);
				idEntry = Integer.parseInt(scanner.nextLine());
				
				
				if (idEntry > 0) {
					idCheck = true;
				} else {
					System.out.println("Only positive values above 0 please. Try again.");
				}
				
			} catch (InputMismatchException e) {
				System.out.println("Ooo, you did something naughty there. Try to stick to a normal integer. ex:1,2,...,123\n");
				scanner.nextLine();
				
				continue;
			}
		}
		
		return idEntry;
	}
	
	private String typeEntry(String passedString) {
		String vehicleTypeEntry;
		Boolean typeCheck = false;
		Set<String> acceptedVehicleTypes = new HashSet<String>();
			acceptedVehicleTypes.add("Car");
			acceptedVehicleTypes.add("Boat");
			acceptedVehicleTypes.add("Plane");
		
		System.out.print(passedString);
		vehicleTypeEntry = scanner.nextLine();
		
		do {
			if (!acceptedVehicleTypes.contains(vehicleTypeEntry)) {
			System.out.println("\n!!!---ERROR---!!!!\nOnly 'Car', 'Boat', or 'Plane' are valid entrys for Vehicle Type. (Case-Sensitive)\n");
			
			System.out.print(passedString);
			vehicleTypeEntry = scanner.nextLine();
			
			} else typeCheck = true;
		} while (!typeCheck);
		
		return vehicleTypeEntry;
	}
	
	private int yearEntry() {
		Boolean yearCheck = false;
		int vehicleYearEntry = 0;
		
		while (!yearCheck) {
			//scanner.nextInt()'s mom is a ho. All of this code to catch it's exception gracefully.
			try {
				
				System.out.print("Enter Vehicle Year (4 digits ex: 1994): ");
				vehicleYearEntry = Integer.parseInt(scanner.nextLine());
				
				
				do {
					if (String.valueOf(vehicleYearEntry).length() != 4) {
						System.out.println("\n!!!---ERROR---!!!!\nOnly 4 digit years are valid entrys. (ex: 1994)\n");
						
						System.out.print("Enter Vehicle Year (4 digits ex: 1994): ");
						vehicleYearEntry = Integer.parseInt(scanner.nextLine());
					
					//Stupid limitation of the Year field in MYSQL	
					} else if (vehicleYearEntry <= 1901 || vehicleYearEntry >= 2155) {
						System.out.println("\n!!!---ERROR---!!!!\nEntry outside of limits. Please keep between 1901-2155.\n");
						
						System.out.print("Enter Vehicle Year (4 digits ex: 1994): ");
						vehicleYearEntry = Integer.parseInt(scanner.nextLine());
						
					} else yearCheck = true;
					
				} while (!yearCheck);
			} catch (InputMismatchException e) {
				System.out.println("Ooo, you did something naughty there. Try to stick to a 4 digit year. ex:1994\n");
				scanner.nextLine();
				
				continue;
			}
		}
		

		
		return vehicleYearEntry;
	}
	
	private Boolean intEntryCheck(int incomingInt) {
		Boolean check = false;
		
		if (incomingInt > 0) {
			return check = true;
		} else return check = false;
		
	}
	
	private Boolean boolEntryCheck(Boolean incomingBool) {
		Boolean check = false;
		
		if (incomingBool = true) {
			return check = true;
		} else return check = false;
	}
	
}
