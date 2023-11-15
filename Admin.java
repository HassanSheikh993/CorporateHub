package project;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

	public class Admin {
		JFrame frame=new JFrame();
		JButton b1=new JButton("SELECT");
		JButton b2=new JButton("SELECT");
		JButton b3=new JButton("SELECT");
		JButton b4=new JButton("SELECT");
		JButton b5=new JButton("SELECT");
		JButton b6=new JButton("SELECT");
		JButton b7=new JButton("SELECT");
		JLabel l1=new JLabel("CREATE NEW USER");
		JLabel l2=new JLabel("DISPLAY RECORD");
		JLabel l3=new JLabel("Exist");
		JLabel l4=new JLabel("CHECK LEAVE REQUESTS");
		JLabel l5=new JLabel("UPDATE RECORD");
		JLabel l6=new JLabel("PROJECT");
		JLabel l7=new JLabel("CLIENTS RECORD");
		JLabel t1=new JLabel("*WELCOME ADMIN*");
		
		Admin()
		{
			t1.setBounds(400,50,500,30);
			t1.setFont(new Font("Calibri", Font.BOLD,30));
			frame.add(t1);
			l1.setFont(new Font("Calibri", Font.BOLD,18));
			l2.setFont(new Font("Calibri", Font.BOLD,18));
			l3.setFont(new Font("Calibri", Font.BOLD,18));
			l4.setFont(new Font("Calibri", Font.BOLD,18));
			l5.setFont(new Font("Calibri", Font.BOLD,18));
			l6.setFont(new Font("Calibri", Font.BOLD,18));
			l7.setFont(new Font("Calibri", Font.BOLD,18));
			l1.setForeground(new Color( 0, 0, 128));
			l2.setForeground(new Color( 0, 0, 128));
			l3.setForeground(new Color( 0, 0, 128));
			l4.setForeground(new Color( 0, 0, 128));
			l5.setForeground(new Color( 0, 0, 128));
			l6.setForeground(new Color( 0, 0, 128));
			l7.setForeground(new Color( 0, 0, 128));
			t1.setForeground(new Color( 0, 0, 128));
			b1.setForeground(new Color (0, 0, 128));
			b2.setForeground(new Color (0, 0, 128));
			b3.setForeground(new Color (0, 0, 128));
			b4.setForeground(new Color (0, 0, 128));
			b5.setForeground(new Color (0, 0, 128));
			b6.setForeground(new Color (0, 0, 128));
			b7.setForeground(new Color (0, 0, 128));
			 
			    l1.setBounds(50,150,230,30);
				b1.setBounds(250,150,230,30);
				l2.setBounds(50,220,230,30);
				b2.setBounds(250,220,230,30);
				l5.setBounds(50,290,290,30);
				b5.setBounds(250,290,230,30);
				b4.setBounds(250,360,230,30);
				l4.setBounds(50,360,290,30);
				l3.setBounds(50,430,230,30);
				b3.setBounds(250,430,230,30);
				l6.setBounds(650,220,230,30);   //
				b6.setBounds(800,220,230,30);   //
				l7.setBounds(650,150,230,30);
				b7.setBounds(800,150,230,30);
				
			 frame.add(b1);
			 frame.add(b2);
			 frame.add(l1);
			 frame.add(l2);
			 frame.add(b3);
			 frame.add(l3);
			 frame.add(b4);
			 frame.add(l4);
			 frame.add(b5);
			 frame.add(l5);
			 frame.add(l6);
			 frame.add(b6);
			 frame.add(l7);
			 frame.add(b7);
			 frame.setSize(1200,1200);
			   frame.setLayout(null);
			  frame.setVisible(true);
			  Color color=new Color(255,255,153);
			 // Color color=new Color(120, 155, 194);
			 
		        frame.getContentPane().setBackground(color);
		        frame.setLocationRelativeTo(null); 
			  
			  ///////////////////////////////////////////////
			  //////////////////////////////////////////////
			  
			  b2.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						
						if(e.getSource()==b2)
						{ 
							Display obj =new Display();
						}
						}
				});
				
			  
			  /////////////////////////////////////////////
			  ////////////////////////////////////////////
			  
			  b3.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						
						if(e.getSource()==b3)
							frame.dispose();
						{ 
							Try obj=new Try();
						}
				}
				});
			  
			  /////////////////////////////////////////////////
			  /////////////////////////////////////////////////
			  b1.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
						
						if(e.getSource()==b1)
							//frame.dispose();
						{ 
							Insert obj=new Insert();
						}
				}
				});
			  
			  ///////////////////////////////////////////////////////////////
			  b4.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) {
					    DefaultTableModel table = new DefaultTableModel();
				        table.addColumn("ID");
				        table.addColumn("Name");
				        table.addColumn("Reason");
				        table.addColumn("start");
				        table.addColumn("end");

				        String url = "jdbc:mysql://localhost:3306/project2";
				        String userName = "root";
				        String password = "";
				        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
				            String query = "SELECT * FROM request_leave";
				            Statement statement = connection.createStatement();
				            ResultSet resultSet = statement.executeQuery(query);

				            while (resultSet.next()) {
				                int ID = resultSet.getInt("ID");
				                String Name = resultSet.getString("Name");
				                String Reason = resultSet.getString("Reason");
				                String start = resultSet.getString("start");
				                String end = resultSet.getString("end");
				                table.addRow(new Object[]{ID, Name, Reason, start, end});
				            }
				            
				            resultSet.close();
				            statement.close();
				        } catch (SQLException e1) {
				            e1.printStackTrace();
				        }

				        JTable table1 = new JTable(table);
				        JScrollPane scrollPane = new JScrollPane(table1);
				        JFrame frame1=new JFrame();

				        frame1.add(scrollPane);
				         frame1.pack();
				        frame1.setLocationRelativeTo(null); 
				         frame1.setVisible(true);
								 
						 
						
						}
				});
			  
			  ////////////////////////////////////////////////////////////////
			  
			  b5.addActionListener(new ActionListener()
					  {

				
						public void actionPerformed(ActionEvent e) {
							if(e.getSource()==b5)
							{
								updateAdmin obj=new updateAdmin();
							}
							
						}
				  
					  });
			  
			  
			  b6.addActionListener(new ActionListener()
					  {

						public void actionPerformed(ActionEvent e) {
							
							Project obj=new Project();
						}
				  
					  }
					  
					  );
				  
			
			  b7.addActionListener(new ActionListener()
			  {

				public void actionPerformed(ActionEvent e) {
					
					Client obj=new Client();
				}
		  
			  }
			  
			  );
		  

	}
		
	
			  public static void main(String args[])
		{
			Admin obj=new Admin();
				
			
		}
	}
	


