package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Client {
	JFrame frame=new JFrame();
	JButton b1=new JButton("select");
	JButton b2=new JButton("select");
	
	JLabel l1=new JLabel("INSERT NEW CLIENT");
	JLabel l2=new JLabel("DISPLAY RECORD");
	JLabel heading = new JLabel("CLIENT CONTROL");
	
	Client()
	{
		l1.setFont(new Font("Calibri", Font.BOLD,15));
		l2.setFont(new Font("Calibri", Font.BOLD,15));
		heading.setFont(new Font("Calibri", Font.BOLD,30));
		 heading.setBounds(250, 20, 350, 30);
		
		
		
		    l1.setBounds(50,150,220,30);
			b1.setBounds(250,150,230,30);
			l2.setBounds(50,220,220,30);
			b2.setBounds(250,220,230,30);
		
		 frame.add(b1);
		 frame.add(b2);
		 frame.add(l1);
		 frame.add(l2);
		 frame.add(heading);
		 Color color = new Color(200, 242, 215);
	        frame.getContentPane().setBackground(color);
		
		 frame.setSize(700,700);
		   frame.setLayout(null);
		  frame.setVisible(true);
		    frame.setLocationRelativeTo(null); 
		    
		    
/////////////////--------------------------------------------------------------------
		    
		    b1.addActionListener(new ActionListener()
		    		{

					
						public void actionPerformed(ActionEvent e) {
							InsertcClient obj = new InsertcClient();
							
						}
		    	
		    		});
		    
		    b2.addActionListener(new ActionListener()
		    		{

						
						public void actionPerformed(ActionEvent e) {
							
							JFrame frame=new JFrame();
							DefaultTableModel defaultTable=new DefaultTableModel();
							defaultTable.addColumn("Client_ID");
							defaultTable.addColumn("Client_Name");
							defaultTable.addColumn("Email");
							defaultTable.addColumn("Phone_no");
							defaultTable.addColumn("Address");
							
							 String url = "jdbc:mysql://localhost:3306/project2";
							String userName="root";
							String password="";
							try(Connection connection=DriverManager.getConnection(url, userName, password))
							{
								
								String query="SELECT * FROM client;";
								
								Statement statement= connection.createStatement();
								ResultSet resultset=statement.executeQuery(query);
								
								while(resultset.next())
								{
									int id=resultset.getInt("Client_ID");
									String name=resultset.getString("Client_Name");
									String Email=resultset.getString("Email");
									String Phone_no=resultset.getString("Phone_no");
									String Address=resultset.getString("Address");
									
									
									defaultTable.addRow(new Object[]{id,name,Email,Phone_no,Address});
									
								}
								
								resultset.close();
								statement.close();
								
							} catch (SQLException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
							  JTable table = new JTable(defaultTable);
							         JScrollPane scrollPane = new JScrollPane(table);

							      frame.add(scrollPane);
							      frame.setVisible(true);
							         frame.pack();
							        frame.setLocationRelativeTo(null); 
						}
		    	
		    		});
		    	
		    
	}
	
	
	
	public static void main(String args[])
	{
		Client obj=new Client();
	}
}
