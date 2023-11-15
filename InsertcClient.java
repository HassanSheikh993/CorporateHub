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

public class InsertcClient {
    JFrame frame = new JFrame();

    JLabel l1 = new JLabel("Client_ID:                                                           ");
    JLabel l2 = new JLabel("Client_Name:");
    JLabel l3 = new JLabel("Email:");
    JLabel l4 = new JLabel("Phone_no:");
    JLabel l5 = new JLabel("Address:");

    JTextField id = new JTextField();
    JTextField name = new JTextField();
    JTextField email = new JTextField();
    JTextField phone = new JTextField();
    JTextField address = new JTextField();
    DefaultTableModel defaultTable = new DefaultTableModel();

    JButton b1 = new JButton("INSERT");
    JButton b2 = new JButton("DELETE");

    InsertcClient() {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(9, 2, 10, 10));
       JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
       
        panel.add(l1);
        panel.add(id);
        panel.add(l2);
        panel.add(name);
        panel.add(l3);
        panel.add(email);
        panel.add(l4);
        panel.add(phone);
        panel.add(l5);
        panel.add(address);
        buttonPanel.add(b1);
        buttonPanel.add(b2);
        //panel.add(b1);
        frame.add(panel, BorderLayout.WEST);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setSize(500, 300);
        frame.setVisible(true);
        Color color = new Color(200, 242, 215);
        panel.setBackground(color);
        b1.setBackground(color);
        frame.setLocationRelativeTo(null);
        b1.addActionListener(e -> insertData(defaultTable));
        b2.addActionListener(e -> delete(defaultTable));
       
        defaultTable.addColumn("Client_ID");
        defaultTable.addColumn("Client_Name");
        defaultTable.addColumn("Email");
        defaultTable.addColumn("Phone_no");
        defaultTable.addColumn("Address");
        
        JTable table = new JTable(defaultTable);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setBackground(new Color(200, 242, 215));

        frame.add(scrollPane, BorderLayout.CENTER);

        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
        
        display(defaultTable);
        
        
        b1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	insertData(defaultTable);
            }
        });
        
        
        
    }

  ////////////////////////////////////////////////////////////////////////////
    
    void display(DefaultTableModel defaultTable)
    {
    	  String url = "jdbc:mysql://localhost:3306/project2";
          String userName = "root";
          String password = "";
          try (Connection connection = DriverManager.getConnection(url, userName, password)) {
              String query = "SELECT * FROM client;";

              Statement statement = connection.createStatement();
              ResultSet resultset = statement.executeQuery(query);

              while (resultset.next()) {
                  int id1 = resultset.getInt("Client_ID");
                  String name1 = resultset.getString("Client_Name");
                  String Email = resultset.getString("Email");
                  String Phone_no = resultset.getString("Phone_no");
                  String Address = resultset.getString("Address");

                  defaultTable.addRow(new Object[]{id1, name1, Email, Phone_no, Address});
              }

              resultset.close();
              statement.close();

          } catch (SQLException e1) {
              e1.printStackTrace();
          }
    }

	///////////////////////////////////////////////////////////////
	 void insertData(DefaultTableModel defaultTable) {
 	    String url = "jdbc:mysql://localhost:3306/project2";
 	    String userName = "root";
 	    String password = "";

 	    String insertQuery = "INSERT INTO client (Client_ID,Client_Name,Email,Phone_no,Address) VALUES (?, ?, ?, ?,?)";
 	
 	    if( id.getText().isEmpty() || name.getText().isEmpty() || email.getText().isEmpty() || phone.getText().isEmpty() || address.getText().isEmpty())
 	    {
 	    	 JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
 	    }
 	    else
 	    {
 	 
 	    try (Connection connection = DriverManager.getConnection(url, userName, password)) {
 	        PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);
 	              
 	            
 	            int idValue = Integer.parseInt(id.getText());
 	            String nameValue = name.getText();
 	            String emailValue = email.getText();
 	            String phoneValue = phone.getText();
 	            String addressValue = address.getText();
 	            
 	            prepareStatement.setInt(1, idValue);
 	            prepareStatement.setString(2, nameValue);
 	            prepareStatement.setString(3, emailValue);  
 	            prepareStatement.setString(4, phoneValue);
 	            prepareStatement.setString(5, addressValue); 
 	            int rowsaffected=prepareStatement.executeUpdate();
                 if (rowsaffected>0) {
               JOptionPane.showMessageDialog(frame, "Data inserted successfully!");
           } else {
               JOptionPane.showMessageDialog(frame,"Failed to insert data.","Error",JOptionPane.ERROR_MESSAGE);
           }

 	        }
 	    
 	    catch (SQLException ex) {
 	        JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
 	    }
 	   defaultTable.setRowCount(0); // Clear existing data
       display(defaultTable);
 	    }
	 }
	 
	 
	 void delete(DefaultTableModel defaultTable)
	 {
		 

		 String url = "jdbc:mysql://localhost:3306/project2";
	     String userName = "root";
	     String password = "";
		 int clientid = Integer.parseInt(id.getText());
		 String queryclient="delete from client where client_id=?";
		 try(Connection connection=DriverManager.getConnection(url, userName, password))
		 {
			 PreparedStatement preparestatement=connection.prepareStatement(queryclient);
			 preparestatement.setInt(1, clientid);
			 int rowsAffectedConnection = preparestatement.executeUpdate();

	         if (rowsAffectedConnection > 0) {
	             JOptionPane.showMessageDialog(frame, " data inserted successfully!");
	         } else {
	             JOptionPane.showMessageDialog(frame, "Failed to insert data", "Error", JOptionPane.ERROR_MESSAGE);
	         }
	         defaultTable.setRowCount(0); // Clear existing data
	         display(defaultTable);
			 
		 } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 
	
	 
	 
 	    
	public static void main(String args[])
			{
		InsertcClient obj=new InsertcClient();
			}
}



