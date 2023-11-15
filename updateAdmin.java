package project;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class updateAdmin {

	   JTextField nameTextField;
	    JTextField addressTextField;
	    JRadioButton maleRadioButton;
	    JRadioButton femaleRadioButton;
	   // JTextField departmentTextField;
	    JTextField idTextField;
	    JRadioButton morningRadioButton;
	    JRadioButton eveningRadioButton;
	    JRadioButton Depart1;
	    JRadioButton Depart2;
	    
	    JFrame frame = new JFrame("Employee Management System");;
	    updateAdmin() {
   
 	 JPanel panel = new JPanel();
      panel.setLayout(new GridLayout(9, 2, 10, 10)); // Rows, Columns, Horizontal gap, Vertical gap

      // Add JLabels and JTextFields for input
      JLabel nameLabel = new JLabel("Name:");
      nameTextField = new JTextField();
      JLabel addressLabel = new JLabel("Address:");
      addressTextField = new JTextField();
      JLabel genderLabel = new JLabel("Gender:");
      JLabel shiftLabel = new JLabel("Shift:");
      JLabel departmentLabel = new JLabel("Department Name:");
     // departmentTextField = new JTextField();
      JLabel idLabel = new JLabel("ID (EMPLOYEE YOU WANT TO UPDATE):");
      idTextField = new JTextField();
      
      

      // Add components to the panel
      panel.add(idLabel);
      panel.add(idTextField);
      panel.add(nameLabel);
      panel.add(nameTextField);
      panel.add(addressLabel);
      panel.add(addressTextField);

      // Add gender radio buttons
      JPanel genderPanel = new JPanel();
      ButtonGroup genderButtonGroup = new ButtonGroup();
      maleRadioButton = new JRadioButton("Male");
      femaleRadioButton = new JRadioButton("Female");
      genderButtonGroup.add(maleRadioButton);
      genderButtonGroup.add(femaleRadioButton);
      genderPanel.add(maleRadioButton);
      genderPanel.add(femaleRadioButton);
      panel.add(genderLabel);
      panel.add(genderPanel);
      
   // Add shift radio buttons
      JPanel shiftPanel = new JPanel();
      ButtonGroup shiftButtonGroup = new ButtonGroup();
      morningRadioButton = new JRadioButton("Morning");
      eveningRadioButton = new JRadioButton("Evening");
      shiftButtonGroup.add(morningRadioButton);
      shiftButtonGroup.add(eveningRadioButton);
      shiftPanel.add(morningRadioButton);
      shiftPanel.add(eveningRadioButton);
      panel.add(shiftLabel);
      panel.add(shiftPanel);
      
      // department radio button
      panel.add(departmentLabel);
      JPanel depart=new JPanel();
      ButtonGroup departgroup=new ButtonGroup();
      Depart1=new JRadioButton("Department1");
      Depart2=new JRadioButton("Department2");
      departgroup.add(Depart1);
      departgroup.add(Depart2);
      depart.add(Depart1);
      depart.add(Depart2);
      panel.add(depart);
      panel.add(depart);

   
     // panel.add(departmentTextField);
     // Add JButton
     JButton updateButton = new JButton("Update");
     panel.add(updateButton);
     JButton deleteButton = new JButton("Delete");
     panel.add(deleteButton);

     // Add the panel to the frame
     frame.add(panel, BorderLayout.WEST);

     // Create and populate the table model
     DefaultTableModel tableModel = new DefaultTableModel();
     tableModel.addColumn("ID");
     tableModel.addColumn("Name");
     tableModel.addColumn("Address");
     tableModel.addColumn("Gender");
     tableModel.addColumn("Shift");
     tableModel.addColumn("Department_Name");

     populateTableModel(tableModel); // Populate table model with initial data

     // Create JTable and JScrollPane
     JTable table = new JTable(tableModel);
     JScrollPane scrollPane = new JScrollPane(table);

     // Add JScrollPane to the frame
     frame.add(scrollPane, BorderLayout.CENTER);

     // Pack, set location, and make the frame visible
     frame.pack();
     frame.setLocationRelativeTo(null);
     frame.setVisible(true);
     Color color = new Color(204,255,255);
     panel.setBackground(color);
     table.setBackground(new Color(204,255,255));

     // Update button ActionListener
     updateButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
             updateEmployee(tableModel);
         }
     });
     
     ///////
     deleteButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {
            delete(tableModel);
         }
     });
 }

 public void populateTableModel(DefaultTableModel tableModel) {
     String url = "jdbc:mysql://localhost:3306/project2";
     String userName = "root";
     String password = "";

     try (Connection connection = DriverManager.getConnection(url, userName, password)) {
         String query = "SELECT employee.ID, employee.Name, employee.Address, employee.Gender, shift.Shift, department.Department_Name "
                 + "FROM employee "
                 + "LEFT JOIN connection ON connection.emp_id = employee.ID "
                 + "LEFT JOIN shift ON shift.shift_id = connection.shift_id "
                 + "LEFT JOIN department ON department.dep_id = connection.depart_id order by employee.ID asc";

         Statement statement = connection.createStatement();
         ResultSet resultSet = statement.executeQuery(query);

         while (resultSet.next()) {
             int id = resultSet.getInt("ID");
             String name = resultSet.getString("Name");
             String address = resultSet.getString("Address");
             String gender = resultSet.getString("Gender");
             String shift = resultSet.getString("Shift");
             String dep = resultSet.getString("Department_Name");

             tableModel.addRow(new Object[]{id, name, address, gender, shift, dep});
         }

         resultSet.close();
         statement.close();
     } catch (SQLException e) {
         e.printStackTrace();
     }
 }

 public void updateEmployee(DefaultTableModel tableModel) {
	    String url = "jdbc:mysql://localhost:3306/project2";
	    String userName = "root";
	    String password = "";

	    int id = Integer.parseInt(idTextField.getText());

	    if (idTextField.getText().isEmpty()) {
	        JOptionPane.showMessageDialog(null, "Please enter an ID.");
	        return;
	    }

	    // Query to retrieve the current employee data
	    String selectQuery = "SELECT * FROM employee WHERE ID=?";
	    String updateQuery = "UPDATE employee SET Name=?, Address=?, Gender=? WHERE ID=?";

	    try (Connection connection = DriverManager.getConnection(url, userName, password);
	         PreparedStatement selectStatement = connection.prepareStatement(selectQuery);
	         PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {

	        // Retrieve current data from the database
	        selectStatement.setInt(1, id);
	        ResultSet resultSet = selectStatement.executeQuery();

	        if (resultSet.next()) {
	            // Retrieve existing values from the database
	            String currentName = resultSet.getString("Name");
	            String currentAddress = resultSet.getString("Address");
	            String currentGender = resultSet.getString("Gender");

	            // Get user input
	            String name = nameTextField.getText();
	            String address = addressTextField.getText();
	            String gender = maleRadioButton.isSelected() ? "Male" : (femaleRadioButton.isSelected() ? "Female" : currentGender);

	            // Update only the provided fields
	            if (!name.isEmpty()) {
	                currentName = name;
	            }
	            if (!address.isEmpty()) {
	                currentAddress = address;
	            }

	            // Update the employee data
	            updateStatement.setString(1, currentName);
	            updateStatement.setString(2, currentAddress);
	            updateStatement.setString(3, gender);
	            updateStatement.setInt(4, id);
	            updateStatement.executeUpdate();

	            // Clear the text fields and update the table
	            clearTextFields();
	            tableModel.setRowCount(0); // Clear existing data
	            populateTableModel(tableModel); // Populate table model with updated data
	        }

	        resultSet.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	
     
     
/////////////-----------------------------------------------------------------------------------------
     String query="update connection set shift_id=? where emp_id=?;";
     try(Connection connection=DriverManager.getConnection(url, userName,password))
     {
     	PreparedStatement preparestatement= connection.prepareStatement(query);
     	int set=0;
     	if(morningRadioButton.isSelected())
     	{
     		set=1;
     		preparestatement.setInt(2, id);
     		preparestatement.setInt(1, set);
     	}
     	if(eveningRadioButton.isSelected())
     	{
     		set=2;
     		preparestatement.setInt(2, id);
     		preparestatement.setInt(1, set);
     	}
    	 int rowsAffectedConnection = preparestatement.executeUpdate();

       
       tableModel.setRowCount(0); // Clear existing data
       populateTableModel(tableModel); // Populate table model with updated data
     } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
     //////------------------------------------------------------------------------------------------------------
     
     String query222="update connection set depart_id=? where emp_id=?;";
     try(Connection connection=DriverManager.getConnection(url,userName,password))
     {
    	 PreparedStatement preparestatement=connection.prepareStatement(query222);
    	 
    	 int set=0;
    	 if(Depart1.isSelected())
    	 {
    		 set=1;
    		 preparestatement.setInt(1, set);
        	 preparestatement.setInt(2, id);
    	 }
    	 if(Depart2.isSelected())
    	 {
    		 set=2;
    		 preparestatement.setInt(1, set);
        	 preparestatement.setInt(2, id);
    	 }
    	 int rowsAffectedConnection = preparestatement.executeUpdate();

         if (rowsAffectedConnection > 0) {
             JOptionPane.showMessageDialog(frame, " data inserted successfully!");
         } else {
             JOptionPane.showMessageDialog(frame, "Failed to insert data", "Error", JOptionPane.ERROR_MESSAGE);
         }
    	 tableModel.setRowCount(0); // Clear existing data
         populateTableModel(tableModel);
     } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
     
 }
 
 /////////////////////////////////////////////////////////////////////////////////////////
 
 
 void delete(DefaultTableModel tableModel)
 {
	 String url = "jdbc:mysql://localhost:3306/project2";
     String userName = "root";
     String password = "";
	 int id = Integer.parseInt(idTextField.getText());
	 String queryDelete="delete from employee where ID=?";
	 try(Connection connection=DriverManager.getConnection(url, userName, password))
	 {
		 PreparedStatement preparestatement=connection.prepareStatement(queryDelete);
		 preparestatement.setInt(1, id);
		 int rowsAffectedConnection = preparestatement.executeUpdate();

         if (rowsAffectedConnection > 0) {
             JOptionPane.showMessageDialog(frame, " data inserted successfully!");
         } else {
             JOptionPane.showMessageDialog(frame, "Failed to insert data", "Error", JOptionPane.ERROR_MESSAGE);
         }
         tableModel.setRowCount(0); // Clear existing data
         populateTableModel(tableModel);
		 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
	 String Queryconnection="delete from connection where emp_id=?";
	 try(Connection connection=DriverManager.getConnection(url, userName, password))
	 {
		 PreparedStatement preparestatement=connection.prepareStatement(Queryconnection);
		 preparestatement.setInt(1, id);
		 int rowsAffectedConnection = preparestatement.executeUpdate();

         if (rowsAffectedConnection > 0) {
             JOptionPane.showMessageDialog(frame, " data inserted successfully!");
         } else {
             JOptionPane.showMessageDialog(frame, "Failed to insert data", "Error", JOptionPane.ERROR_MESSAGE);
         }
         tableModel.setRowCount(0); // Clear existing data
         populateTableModel(tableModel);
		 
	 } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	 
 }
 
 

 public void clearTextFields() {
     nameTextField.setText("");
     addressTextField.setText("");
    
    
     idTextField.setText("");
 }

 public static void main(String[] args) {
	 updateAdmin obj=new updateAdmin();
 }
}

