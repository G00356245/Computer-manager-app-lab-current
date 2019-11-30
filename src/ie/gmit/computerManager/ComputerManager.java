package ie.gmit.computermanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import ie.gmit.studentmanager.Computer;

public class ComputerManager implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Declare a List called students to hold the student objects
	private List<Computer> ComputerList;

	// Constructor
	public ComputerManager() {
		// Instantiate a student ArrayList
		ComputerList = new ArrayList<Computer>();
	}

	// Getters and Setters
	public List<Computer> getComputers() {
		return ComputerList;
	}

	public void setComputers(List<Computer> ComputerList) {
		this.ComputerList = ComputerList;
	}

	/**
	 * This method adds a Student to the Student List.
	 *
	 * @param student a student object that is to be added to the student list
	 * @return a boolean value indicating if the add was successful		
	 */                     
	public boolean addComputer(Computer computer) {
		try {
			// Using Collections add method. It returns true if this collection
			// changed as a result of the call
			return ComputerList.add(computer);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	public boolean deleteComputer(Computer computer) {
		try {
			// Using Collections remove method. It returns true if this list 
			// contained the specified element
			return ComputerList.remove(computer);
		} catch (Exception error) {
			error.printStackTrace();
			return false;
		}
	}

	public boolean deleteComputerById(String ComputerID) {
		// Search for the computer by ID
		Computer computer = findComputerById(ComputerID);
		// If a computer was found then delete the computer
		if (computer != null) {
			return deleteComputer(computer);
		} else {
			// If no computer was found Return false
			return false;
		}
	}

	public Computer findComputerById(String ComputerID) {

		// Loop over (i.e. Iterate over) arrayList for computer type elements in
		// the computers ArrayList

		// There are 3 ways you can iterate through a List.
		// 1. For Loop
		// 2. Advanced For Loop
		// 3. Iterator

		// 1. For Loop
//		for (int i = 0; i < computerList.size(); i++) {
//			if (computerList.get(i).getComputerId().equals(ComputerId)) {
//				return computerList.get(i);
//			}
//		}

		// 2. Advanced For Loop
//		for (Computer computer : computerList) {
//			// No need to check for null as ArrayList is dynamic and fills holes
//			if (computer.getComputerId().equals(ComputerId)) {
//				return computer;
//			}
//		}

		// 3. Iterator
		Iterator<Computer> computerIterator = ComputerList.iterator();
		Computer computerObjectHolder;
		while (computerIterator.hasNext()) {
			// Store next Computer
			computerObjectHolder = computerIterator.next();
			// Check if ComputerID equals that of current computer object
			if (computerObjectHolder.getComputerId().equals(ComputerID)) {
				return computerObjectHolder;
			}
		}

		// Return null if Computer ID was not found
		return null;
	}

	// Find a list of computer by first name
	public List<Computer> getComputersByFirstName(String firstName) {
		// Create a new ArrayList to Hold Computers with same names
		List<Computer> sameNames = new ArrayList<Computer>();
		// Loop over arrayList for Computer type elements in the computers ArrayList do
		for (Computer computer : ComputerList) {
			// If I find a Computer with the given first name then add to list
			if (computer.getFirstName().equalsIgnoreCase(firstName)) {
				sameNames.add(computer);
			}
		}
		// Check if list has any computers
		if (sameNames.size() > 0) {
			// If computers were found then return the list
			return sameNames;
		}
		// If no computers were found with that first name then return null
		return null;
	}

	public void loadComputerFile(String pathToFile) {
		File inFile = new File(pathToFile);
		FileReader fileReader = null;
		BufferedReader br = null;
		String record = null;

		try {
			fileReader = new FileReader(inFile);
			br = new BufferedReader(fileReader);
			br.readLine(); //discard first line of csv file
			while ((record = br.readLine()) != null) {
				String[] elements = record.split(",");
				Computer newComputer = new Computer(elements[0], elements[1], elements[2]);
				this.addComputer(newComputer);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public int findTotalComputers() {
		// returns the current number of Computers in the ArrayList
		return ComputerList.size();
	}
	
	public ComputerManager loadDB(String dbPath){
    	ComputerManager sm = null;
    	try {
			ObjectInputStream in = new ObjectInputStream(new FileInputStream(dbPath));
			sm = (ComputerManager) in.readObject();
    		in.close();
    	} catch (Exception e) {
    		System.out.print("[Error] Cannont load DB. Cause: ");
    		e.printStackTrace();
    	}
		return sm;
    }

}