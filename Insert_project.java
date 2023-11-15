package project;


	import java.awt.BorderLayout;
	import java.awt.Color;
	import java.awt.FlowLayout;
	import java.awt.GridLayout;
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.PreparedStatement;
	import java.sql.SQLException;

	import javax.swing.ButtonGroup;
	import javax.swing.JButton;
	import javax.swing.JFrame;
	import javax.swing.JLabel;
	import javax.swing.JOptionPane;
	import javax.swing.JPanel;
	import javax.swing.JRadioButton;
	import javax.swing.JTextField;

	public class Insert_project {
	    
	    JFrame frame = new JFrame();
	    JLabel l1 = new JLabel("  Project ID :");
	    JLabel l2 = new JLabel("  Project Name :");
	    JLabel l3 = new JLabel("  Details :");
	    JLabel l4 = new JLabel("  Client ID :");
	    JLabel l5 = new JLabel("  Client Name :");
	    
	    JTextField id = new JTextField();
	    JTextField name = new JTextField();
	    JTextField details = new JTextField();
	    JTextField clientID = new JTextField();
	    JTextField clientName = new JTextField();

	    JButton b1 = new JButton("INSERT");
	    

	    Insert_project() {
	        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 10)); 
	        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));/// change 5 to 6
	              panel.add(l1);
	        panel.add(id);
	        panel.add(l2);
	        panel.add(name);
	        panel.add(l3);
	        panel.add(details); 
	        panel.add(l4);
	        panel.add(clientID);
	        panel.add(l5);
	        panel.add(clientName);
	        buttonPanel.add(b1);
	        frame.add(panel, BorderLayout.CENTER);
	        frame.add(buttonPanel, BorderLayout.SOUTH);
	        frame.setSize(500, 300);
	        frame.setVisible(true);
	        Color color=new Color(200, 242, 215);
	        panel.setBackground(color);
	        b1.setBackground(color);
	        frame.setLocationRelativeTo(null); 
	        b1.addActionListener(e -> insertData());
	     
	    }

	   
	//------------------------------------------------------------ Database connection 
	    	 void insertData() {
	    	    String url = "jdbc:mysql://localhost:3306/project2";
	    	    String userName = "root";
	    	    String password = "";

	    	    String insertQuery = "INSERT INTO project (Project_ID,Project_Name,Project_Details) VALUES (?, ?, ?)";
	    	
	    	    
	    	 
	    	    try (Connection connection = DriverManager.getConnection(url, userName, password)) {
	    	        PreparedStatement prepareStatement = connection.prepareStatement(insertQuery);
	    	              
	    	            
	    	            int idValue = Integer.parseInt(id.getText());
	    	            String nameValue = name.getText();
	    	            String detailsValue = details.getText();
	    	            
	    	            prepareStatement.setInt(1, idValue);
	    	            prepareStatement.setString(2, nameValue);
	    	            prepareStatement.setString(3, detailsValue);  
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
	    	    
	    	  
	    	}

	    

	    	public static void main(String args[])  {
	        
	    		Insert_project obj = new Insert_project();
	       
	    }
	}
