package ie.gmit.studentmanager;

import java.io.Serializable;
import java.util.Date;

import ie.gmit.computermanager.ComputerModel;

public class Computer implements Serializable {

    private static final long serialVersionUID = 1L;
    
    // Instance Variables
	private String ComputertId;
	private String firstName;
	private String surname;
	private ComputerModel computerModel;
	private Memory memory;

	// Constructors
	public Computer(String ComputertId) {
		this.ComputertId = ComputertId;
	}
	
	public Computer(String ComputertId, String firstName, String surname) {
		// this(ComputertId); - could set ComputertId this way
		this.ComputertId = ComputertId;
		this.firstName = firstName;
		this.surname = surname;
	}

    // Getters and Setters
    public String getComputerId() {
		return ComputertId;
	}
	public String ComputerId() {
		return ComputertId;
	}

	public void setComputertId(String ComputerId) {
		this.ComputertId = ComputerId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public ComputerModel getComputerModel() {
		return computerModel;
	}

	public void setComputerModel(ComputerModel computerModel) {
		this.computerModel = computerModel;
	}

	public Memory getMemory() {
		return memory;
	}

	public void setMemory(Memory memory) {
		this.memory = memory;
	}

}