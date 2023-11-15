package project;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.*;

public class Request_Leave {
    JFrame frame = new JFrame();
    JLabel heading = new JLabel("Request a Leave");
    JLabel l1 = new JLabel("Name");
    JLabel l2 = new JLabel("Employee ID");
    JLabel l3 = new JLabel("Department");
    JLabel l4 = new JLabel("Reason");
    JLabel l5 = new JLabel("Start Date");
    JLabel l6 = new JLabel("End Date");
    
    JTextField from = new JTextField();
    JTextField ID = new JTextField();
    JTextField Department = new JTextField();
    JTextArea Reason = new JTextArea();
    JTextField startDate = new JTextField();
    JTextField endDate = new JTextField();
    
    JButton submit=new JButton("SUBMIT");  
    JButton reset=new JButton("RESET");
   
    String loggedInEmployeeName;
    int loggedInEmployeeID;

    Request_Leave(String loggedInEmployeeName, int loggedInEmployeeID) {
    	this.loggedInEmployeeID=loggedInEmployeeID;
    	this.loggedInEmployeeName=loggedInEmployeeName;
    	heading.setFont(new Font("Calibri", Font.BOLD,30));
    
        heading.setBounds(250, 20, 300, 30);
        l1.setBounds(50, 80, 100, 30);
        l2.setBounds(50, 130, 100, 30);
      //  l3.setBounds(50, 180, 100, 30);
        l4.setBounds(50, 180, 100, 30);
        l5.setBounds(50, 330, 100, 30);
        l6.setBounds(50, 380, 100, 30);

        from.setBounds(160, 80, 200, 30);
        ID.setBounds(160, 130, 200, 30);
       // Department.setBounds(160, 180, 200, 30);
        Reason.setBounds(160, 180, 200, 120);
        startDate.setBounds(160, 330, 200, 30);
        endDate.setBounds(160, 380, 200, 30);
        submit.setBounds(100,470,180,30);
        reset.setBounds(350,470,180,30);

        frame.add(heading);
       // frame.add(Department);
        frame.add(ID);
        frame.add(Reason);
        frame.add(from);
        frame.add(l1);
        frame.add(l2);
      //  frame.add(l3);
        frame.add(l4);
        frame.add(l5);
        frame.add(l6);
        frame.add(startDate);
        frame.add(endDate);
        frame.add(reset);
        frame.add(submit);
        frame.setSize(700, 700);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        Color color=new Color(162,237,233);
        frame.getContentPane().setBackground(color);
        
        
        ///////////////////////---------------------------------------------------------------------
        ///   to fixed the values of name and id
        ID.setEditable(false);
        ID.setText(String.valueOf(loggedInEmployeeID));
        from.setEditable(false);
        from.setText(String.valueOf(loggedInEmployeeName));
        
        ///////////////////////---------------------------------------------------------------------
		  reset.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					
					if(e.getSource()==reset)
						
					{ 
						Reason.setText("");
						Department.setText("");
						startDate.setText("");
						endDate.setText("");
					}
			}
			});
        //////////////////////----------------------------------------------------------------------
	        submit.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	               //  String name = from.getText();
	            	String name =loggedInEmployeeName;
	              //  String employeeID = ID.getText();
	            	 int employeeID = loggedInEmployeeID;
	                String department = Department.getText();
	                String reason = Reason.getText();
	                String start = startDate.getText();
	                String end = endDate.getText();
	                ///////////////////////////////////////////////////////////////////////////////////////
	                if (name.isEmpty() || reason.isEmpty() || start.isEmpty() || end.isEmpty()) {
	                    JOptionPane.showMessageDialog(frame, "Please fill in all required fields.", "Error", JOptionPane.ERROR_MESSAGE);
	                }
	                
	                ///////////////////////////////////////////////////////////////////////////////////////
	                else {
	                String url = "jdbc:mysql://localhost:3306/project2"; // Corrected URL
	                String userName = "root";
	                String password = "";
	                String insertQuery = "INSERT INTO request_leave (Name,ID, Reason, Start, end) VALUES (?, ?, ?, ?,?)";
	                try (Connection connection = DriverManager.getConnection(url, userName, password);
	                	 PreparedStatement preparedStatement = connection.prepareStatement(insertQuery))	 {  
	                	preparedStatement.setString(1,name);
	                	preparedStatement.setInt(2, employeeID);
	                    preparedStatement.setString(3,reason);
	                    preparedStatement.setString(4,start);
	                    preparedStatement.setString(5,end);
	                   
	                    
	                    int rowsaffected=preparedStatement.executeUpdate();
	                    if (rowsaffected>0) {
	                  JOptionPane.showMessageDialog(frame, "Data inserted successfully!");
	              } else {
	                  JOptionPane.showMessageDialog(frame,"Failed to insert data.","Error",JOptionPane.ERROR_MESSAGE);
	              }

	                } catch (SQLException e1) {
	                    e1.printStackTrace();
	                }
	                }
	            }
	        });
	        
        
        
        ///////////////////////---------------------------------------------------------------------
      
    }

      ///////////////////////---------------------------------------------------------------------  


	public static void main(String args[])
	{
		Request_Leave obj1 = new Request_Leave("ddddd",1122);
	//	new Request_Leave(loggedInEmployeeName, loggedInEmployeeID);
	}
	
}
