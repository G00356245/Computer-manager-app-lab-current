package ie.gmit.computermanager;

import java.io.File;
import java.io.Serializable;

import ie.gmit.studentmanager.Computer;

import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application implements Serializable {

    private static final long serialVersionUID = 1L; // Used for serialization
    ComputerManager cm = new ComputerManager(); // Used for managing computers

    @Override
    public void start(Stage primaryStage) {
        // Create TextArea node for bottom of scene 1
        TextArea taMyOutput = new TextArea();
        taMyOutput.setPrefHeight(100); // sets height of the TextArea to 400 pixels
        taMyOutput.setPrefWidth(100); // sets width of the TextArea to 300 pixels

        // Show total number of computers
        Button btnShowTotal = new Button("Show Total Computers");
        TextField tfTotalNumberOfComputers = new TextField();

        tfTotalNumberOfComputers.setEditable(false); // This box is not editable. Only displays result.
        tfTotalNumberOfComputers.setPromptText("0");

        btnShowTotal.setOnAction(e -> {

            // Code to run when button is clicked
            tfTotalNumberOfComputers.setText(Integer.toString(cm.findTotalComputers()));

        });

        // Add Computer arrangement
        Button btnAddComputer = new Button("Add Computer");
        TextField tfComputerID = new TextField();

        tfComputerID.setPromptText("Enter Computer ID");

        btnAddComputer.setOnAction(e -> {
            if (tfComputerID.getText().trim().equals("")) { // If text field is empty

                taMyOutput.setText("Invalid");
            } else {

                Computer computer = new Computer(tfComputerID.getText());
                cm.addComputer(computer); // Add computer to computer list
                tfComputerID.clear();
            }
        });

        // Delete Computer arrangement
        TextField tfComputerDel = new TextField();
        Button btnDelComputer = new Button("Delete Computer");

        tfComputerDel.setPromptText("Enter Computer ID");

        btnDelComputer.setOnAction(e -> {

            cm.deleteComputerById(tfComputerDel.getText());

        });

        // Save to DB
        Button btnSaveDB = new Button("Save Computers to DB");
        btnSaveDB.setOnAction(e -> {
            if (cm.findTotalComputers() > 0) {
                try {
                    File computerDB = new File("./resources/computersDB.ser");
                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(computerDB));
                    out.writeObject(cm);
                    out.close();
                    taMyOutput.setText("Computer Database Saved");
                } catch (Exception exception) {
                    System.out.print("[Error] Cannont save DB. Cause: ");
                    exception.printStackTrace();
                    taMyOutput.setText("ERROR: Failed to save Computers DB!");
                }
            } else {
                taMyOutput.setText("No Computers in List to save!");
            }
        });

        // Load from DB
        Button btnLoadDB = new Button("Load Computers from DB");
        TextField tfLoadComputers = new TextField();

        tfLoadComputers.setPromptText("Please enter DB path");
        btnLoadDB.setOnAction(e -> {

            try{
                File computerDB = new File(tfLoadComputers.getText());
                ObjectInputStream in = new ObjectInputStream(new FileInputStream(computerDB));
                cm = (ComputerManager) in.readObject();
                in.close();
                taMyOutput.setText("Successfully loaded Computers from Database");
            } catch (Exception exception) {
                    System.out.print("[Error] Cannont load DB. Cause: ");
                    exception.printStackTrace();
                    taMyOutput.setText("ERROR: Failed to load Computers DB!");
            }

        });

        // Add Quit button
		Button btnQuit = new Button("Quit");	
        btnQuit.setOnAction(e -> 
            Platform.exit()
        );

        // Adding and arranging all the nodes in the grid - add(node, column, row)
        GridPane gridPane1 = new GridPane();
        gridPane1.add(tfComputerID, 0, 0);
        gridPane1.add(btnAddComputer, 1, 0);
        gridPane1.add(btnShowTotal, 0, 1);
        gridPane1.add(tfTotalNumberOfComputers, 1, 1);
        gridPane1.add(tfComputerDel, 0, 2);
        gridPane1.add(btnDelComputer, 1, 2);
        gridPane1.add(btnSaveDB, 0, 3);
        gridPane1.add(btnLoadDB, 0, 4);
        gridPane1.add(tfLoadComputers, 1, 4);
        gridPane1.add(taMyOutput, 0, 5, 2, 1);
        gridPane1.add(btnQuit, 0, 6);

        // Preparing the Stage (i.e. the container of any JavaFX application)
        // Create a Scene by passing the root group object, height and width
        Scene scene1 = new Scene(gridPane1, 400, 450);
        // Setting the title to Stage.

        if (getParameters().getRaw().size() == 0) {
            primaryStage.setTitle("Computer Manager Application");
        } else {
            primaryStage.setTitle(getParameters().getRaw().get(0));
        }

        // Setting the scene to Stage
        primaryStage.setScene(scene1);
        // Displaying the stage
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
