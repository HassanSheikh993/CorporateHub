
package project;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;



class update_employee {
	
	  JLabel addressLabel = new JLabel("Address:");
      JTextField addressTextField = new JTextField();
      JLabel passwordLabel = new JLabel("Password:");
      JTextField passwordField = new JTextField();
      JButton updateButton = new JButton("Update");
      JFrame frame = new JFrame("Employee Records");
      JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));
      DefaultTableModel tableModel = new DefaultTableModel();
	
	String loggedInEmployeeName;
	String loggedInEmployeePassword;
	
	
	update_employee(String loggedInEmployeeName, String loggedInEmployeePassword) {
        
		this.loggedInEmployeePassword=loggedInEmployeePassword;
		this.loggedInEmployeeName=loggedInEmployeeName;
        frame.setLayout(new BorderLayout());

       
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Address");
        tableModel.addColumn("Gender");
        tableModel.addColumn("password");
        tableModel.addColumn("Shift");
        tableModel.addColumn("Department_Name");

        
        panel.add(addressLabel);
        panel.add(addressTextField);
        panel.add(passwordLabel);
        panel.add(passwordField);
        panel.add(new JLabel()); // Empty label for spacing
        panel.add(updateButton);

        frame.add(panel, BorderLayout.SOUTH);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Showdata(tableModel);
       
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == updateButton) {
                  update();
                }
            }
        });
    
      
    }

	

	void Showdata(DefaultTableModel tableModel2)
	{
		   String url = "jdbc:mysql://localhost:3306/project2";
	        String userName = "root";
	        String password = "";

	        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
	            // Database retrieval code remains the same...
	        	 String query =" select employee.ID,employee.Name,employee.Address,employee.Gender,employee.password,shift.Shift,department.Department_Name from employee\r\n"
	             		+ "             		 left join connection on connection.emp_id=employee.ID\r\n"
	             		+ "             		left join shift on shift.shift_id=connection.shift_id\r\n"
	             		+ "             		 left join department on department.dep_id=connection.depart_id\r\n"
	             		+ "                     where employee.Name=? and employee.password=?;";
	             PreparedStatement preparedStatement = connection.prepareStatement(query);
	             preparedStatement.setString(1, loggedInEmployeeName);
	             preparedStatement.setString(2, loggedInEmployeePassword);

	             ResultSet resultSet = preparedStatement.executeQuery();

	             while (resultSet.next()) {
	                 int id = resultSet.getInt("ID");
	                 String name = resultSet.getString("Name");
	                 String address = resultSet.getString("Address");
	                 String gender = resultSet.getString("Gender");
	                 String pass=resultSet.getString("password");
	                 String shift=resultSet.getString("Shift");
	                 String dep=resultSet.getString("Department_Name");

	                 tableModel.addRow(new Object[]{id, name, address, gender, pass, shift, dep});
	             }

	             resultSet.close();
	             preparedStatement.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        JTable table = new JTable(tableModel);
	        JScrollPane scrollPane = new JScrollPane(table);
	        frame.add(scrollPane);
	        frame.pack();
	        frame.setLocationRelativeTo(null);
	        frame.setVisible(true);
	        frame.add(scrollPane, BorderLayout.NORTH);
	}
	    
 
	void update()
	{
		   String address = addressTextField.getText();
           String pass = passwordField.getText();

           String updateQuery = null;
           String url = "jdbc:mysql://localhost:3306/project2";
           String userName = "root";
           String password = "";

           try (Connection connection = DriverManager.getConnection(url, userName, password)) {
               if (!address.isEmpty() && !pass.isEmpty()) {
                   updateQuery = "UPDATE employee SET Address=?, password=? WHERE Name=? AND password=?";
               } else if (!address.isEmpty()) {
                   updateQuery = "UPDATE employee SET Address=? WHERE Name=? AND password=?";
               } else if (!pass.isEmpty()) {
                   updateQuery = "UPDATE employee SET password=? WHERE Name=? AND password=?";
               } else {
                   System.out.println("Nothing to update.");
                   return;
               }

               PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

               int parameterIndex = 1;
               if (!address.isEmpty()) {
                   preparedStatement.setString(parameterIndex++, address);
               }
               if (!pass.isEmpty()) {
                   preparedStatement.setString(parameterIndex++, pass);
               }
               preparedStatement.setString(parameterIndex++, loggedInEmployeeName);
               preparedStatement.setString(parameterIndex, loggedInEmployeePassword);

               int rowsUpdated = preparedStatement.executeUpdate();

               if (rowsUpdated > 0) {
                   System.out.println("Record updated successfully.");
               } else {
                   System.out.println("No records updated.");
               }

               
               tableModel.setRowCount(0); // Clear existing data
               Showdata(tableModel);
               
               preparedStatement.close();
           } catch (SQLException e1) {
               e1.printStackTrace();
           }
	}


    public static void main(String args[]) {
      update_employee obj=new update_employee(null, null);
    }
}

