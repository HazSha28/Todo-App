package com.todo;
//database setup
import java.sql.Connection;//brings connection from java sql package
import java.sql.SQLException;//imports sql exception handling

//SwingUI setup
import javax.swing.SwingUtilities;//contains utility methods for Swing,lets run gui in event dispatch thread using invokelater
import javax.swing.UIManager;//controls look and feel of the GUI
import javax.swing.UnsupportedLookAndFeelException;//handles exceptions related to unsupported look and feel settings

//own project imports
import com.todo.gui.TodoAppGUI;//imports the custom class TodoAppGUI, main GUI class for the Todo application-for user interaction in window JFrame
import com.todo.util.DatabaseConnection;//imports the custom class DatabaseConnection, manages database connections with methods like getDBConnection()

public class Main {
    public static void main(String[] args) {
        DatabaseConnection db_Connection = new DatabaseConnection();
        //db connection test
        try {
            Connection cn = db_Connection.getDBConnection();
            System.out.println("Connected to the database");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the database " + e.getMessage());
            System.exit(1);
        }
        //UI setup test
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
            System.err.println("Could not set look and feel " + e.getMessage());
        }
        
        //launch the GUI in the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                new TodoAppGUI().setVisible(true);
            } catch (Exception e) {
                System.err.println("Error starting the application " + e.getLocalizedMessage());
            }
        });
    }
}