package project;
import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
public class Employee {
	JFrame frame=new JFrame();
	JButton b1=new JButton("select");
	JButton b2=new JButton("select");
	JLabel l1=new JLabel("DISPLAY");
	JLabel l2=new JLabel("EXIST");
	JLabel t1=new JLabel("*EMPLOYEE*");
	JButton b3=new JButton("select");
	JLabel l3=new JLabel("REQUEST LEAVE");
	JLabel l4=new JLabel("Project Details");
	JButton b4=new JButton("select");
	int id;
	String loggedInEmployeePassword;
	String loggedInEmployeeName;
	Employee(String loggedInEmployeeName, String loggedInEmployeePassword,int id)
	{   this.id=id;
	this.loggedInEmployeeName=loggedInEmployeeName;
	this.loggedInEmployeePassword=loggedInEmployeePassword;
	
		t1.setBounds(230,50,200,30); 
		
		t1.setFont(new Font("Calibri", Font.BOLD,30));
		frame.add(t1);
		l1.setFont(new Font("Calibri", Font.BOLD,18));
		l2.setFont(new Font("Calibri", Font.BOLD,18));
		l3.setFont(new Font("Calibri", Font.BOLD,18));
		l4.setFont(new Font("Calibri", Font.BOLD,18));
		 
		    l1.setBounds(50,150,300,30);
			b1.setBounds(250,150,230,30);
			l3.setBounds(50,220,300,30);
			b3.setBounds(250,220,230,30);
			l2.setBounds(50,290,290,30);
			b2.setBounds(250,290,230,30);
			l4.setBounds(50,360,290,30);
			b4.setBounds(250,360,230,30);

			l1.setForeground(new Color( 255, 204, 0));
			l2.setForeground(new Color( 255, 204, 0));
			l3.setForeground(new Color( 255, 204, 0));
			l4.setForeground(new Color( 255, 204, 0));
	
			
			
		 frame.add(b1);
		 frame.add(b2);
		 frame.add(l1);
		 frame.add(l2);
		 frame.add(b3);
		 frame.add(l3);
		// frame.add(l4);
		 //frame.add(b4);
		 frame.setSize(700,700);
		   frame.setLayout(null);
		  frame.setVisible(true);
		    frame.setLocationRelativeTo(null); 
		  //Color color=new Color(204,255,204);
		    Color color=new Color(120, 155, 194);
	        frame.getContentPane().setBackground(color);
		  
//////////////////////---------------------------------------------------------------
		  b1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					
					if(e.getSource()==b1)
					{ 
						//Display obj =new Display();
						//Login obj1 = new Login(loggedInEmployeeName, loggedInEmployeePassword);
						update_employee obj1 = new update_employee(loggedInEmployeeName, loggedInEmployeePassword);
						
					}
					}
			});
		  
/////////////////////----------------------------------------------------------------		  
		  
		  b2.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					
					if(e.getSource()==b2)
						frame.dispose();
					{ 
						Try obj=new Try();
						//Insert obj=new Insert();
					}
			}
			});
		  
		  ////////////////////------------------------------------------------------------
		  
		  b3.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e) {
					
					if(e.getSource()==b3)
						
					{ 
						Request_Leave obj1 = new Request_Leave(loggedInEmployeeName,id);
					}
			}
			});
		  
		  
		  //////////////////////////////////////////////////////////////////////////////////
		  b4.addActionListener(new ActionListener() {
	            public void actionPerformed(ActionEvent e) {
	                if (e.getSource() == b4) {
	                    String url = "jdbc:mysql://localhost:3306/project2";
	                    String userName = "root";
	                    String password = "";
	                    DefaultTableModel tableModel = new DefaultTableModel();
	                    tableModel.addColumn("Project_ID");
	                    tableModel.addColumn("Project_Name");
	                    tableModel.addColumn("Client_ID");
	                    tableModel.addColumn("Client_Name");

	                    try (Connection connection = DriverManager.getConnection(url, userName, password)) {
	                        String queryProject = "SELECT project.Project_ID, project.Project_Name, client.Client_ID, client.Client_Name FROM project " +
	                                             "LEFT JOIN connection ON connection.project_id = project.Project_ID " +
	                                             "LEFT JOIN client ON client.Client_ID = connection.cient_ID " +
	                                             "LEFT JOIN employee ON employee.ID = connection.emp_id " +
	                                             "WHERE employee.Name = ? AND employee.password = ?";
	                        PreparedStatement statement = connection.prepareStatement(queryProject);
	                        statement.setString(1, loggedInEmployeeName);
	                        statement.setString(2, loggedInEmployeePassword);
	                        ResultSet resultset = statement.executeQuery();

	                        while (resultset.next()) {
	                            int projectid = resultset.getInt("Project_ID");
	                            String projectname = resultset.getString("Project_Name");
	                            int clientid = resultset.getInt("Client_ID");
	                            String clientname = resultset.getString("Client_Name");

	                            tableModel.addRow(new Object[]{projectid, projectname, clientid, clientname});
	                        }

	                        resultset.close();
	                        statement.close();

	                        JTable table = new JTable(tableModel);
	                        JScrollPane scrollPane = new JScrollPane(table);
	                        frame.getContentPane().add(scrollPane, BorderLayout.CENTER);
	                        frame.revalidate();
	                        frame.repaint();

	                    } catch (SQLException e1) {
	                        e1.printStackTrace();
	                    }
	                }
	            }
	        });
		  ////////////////////////////////////////////////////////////////////////////////////
}
	
	//////public static void main(String[]args, String loggedInEmployeePassword, String loggedInEmployeeName, int id)
	public static void main(String[]args)
	{
		
			
		Employee obj=new Employee( "testing",  "123",  33);
	}
}

//////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////
