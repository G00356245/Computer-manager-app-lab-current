package ie.gmit.computermanager;

import java.util.List;
import java.util.Scanner;

public class ComputerMenu {
	// Instance Variables
	private Scanner userInput = null;
	private ComputerManager computermanagerObject = null;
	private boolean keepRunning = false;
	
	// Constructor Method
	public ComputerMenu() {
		// Create new scanner object to capture input from the user
		userInput = new Scanner(System.in);
		computermanagerObject = new ComputerManager(); // Create a new computermanager object
		keepRunning = true; // Initialise loop checker
	}

	public void display() {
		while (keepRunning) {
			// Display Menu to user
			showOptions();

			// Save user Menu option selection
			int userSelectedOption = userInput.nextInt();

			// Execute option
			selectOption(userSelectedOption);
		}
	}

	private void showOptions() {
		System.out.println("###################################");
		System.out.println("#         Computer manager         #");
		System.out.println("###################################");
		System.out.println("(1) Load Computer DB");
		System.out.println("(2) Add a Computer.");
		System.out.println("(3) Delete a Computer.");
		System.out.println("(4) Search for a Computer by ID.");
		System.out.println("(5) Search for Computers by First Name.");
		System.out.println("(6) Show total Number of Computers.");
		System.out.println("(7) Save DB.");
		System.out.println("(8) Quit.");
		System.out.println("Select an option [1-8]>");
	}

	private void selectOption(int userSelection) {
		if (userSelection == 1) { // Load Computers DB
			System.out.println("User Selected 1");
		} else if (userSelection == 2) {  // Add Computer
			addComputer();
		} else if (userSelection == 3) {  // Delete Computer
			deleteComputer();
		} else if (userSelection == 4) {  // Search Computer by ID
			searchByID();
		} else if (userSelection == 5) {  // Search Computers by Name
			searchByName();
		} else if (userSelection == 6) {  // Search Computers by Name
			System.out.println("Total number of computer = " +
					computermanagerObject.findTotalComputers());
		} else if (userSelection == 8) {
			keepRunning = false;
			System.out.println("Goodbye!");
		}
			
	}

	private void addComputer() {
		System.out.println("Enter Computer ID>");
		String ComputerId = userInput.next();
		System.out.println("Enter Computer Name>");
		String computerName = userInput.next();
		System.out.println("Enter Computer Surname>");
		String computerSurname = userInput.next();

		ie.gmit.studentmanager.Computer newComputerObject = new ie.gmit.studentmanager.Computer(ComputerId, computerName, computerSurname);
		
		boolean result = computermanagerObject.addComputer(newComputerObject);

		if (result) {
			System.out.println("Computer " + ComputerId + " has been added successfully.");
		} else {
			System.out.println("ERROR: Computer " + ComputerId +"  was not added!");
		}
	}

	private void deleteComputer() {
		System.out.println("Enter Computer ID>");
		String ComputerId = userInput.next();
		boolean result = computermanagerObject.deleteComputerById(ComputerId);
		if (result) {
			System.out.println("Computer " + ComputerId + " has been deleted successfully.");
		} else {
			System.out.println("ERROR: Computer " + ComputerId +"  was not deleted!");
		}
	}

	private void searchByID() {
		System.out.println("Enter Computer ID>");
		String ComputerId = userInput.next();
		ie.gmit.studentmanager.Computer computer1 = computermanagerObject.findComputerById(ComputerId);
		if (computer1 == null) {
			System.out.println("Computer NOT found!");
		} else {
			System.out.println("Computer " + computer1.getComputerId() + " found!");
		}	
	}

	private void searchByName() {
		System.out.println("Enter Computer Name>");
		String ComputerName = userInput.next();
		List<ie.gmit.studentmanager.Computer> computers = computermanagerObject.getComputersByFirstName(ComputerName);
		if (computers == null) {
			System.out.println("No computer with that first name found!");
		} else {
			for (ie.gmit.studentmanager.Computer computer : computers) {
				System.out.println(     computer.getComputerId()    );
			}
		}
	}

}
