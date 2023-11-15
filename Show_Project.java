package project;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.protocol.Resultset;

public class Show_Project {
	
	JFrame frame=new JFrame();
	DefaultTableModel defaultTable=new DefaultTableModel();
	
	Show_Project()
	{
		defaultTable.addColumn("Project_ID");
		defaultTable.addColumn("Project_Name");
		defaultTable.addColumn("Client_Name");
		defaultTable.addColumn("Project_Details");
		
		showDetails();	}
	
	
	void showDetails()
	{
		
		 String url = "jdbc:mysql://localhost:3306/project2";
		String userName="root";
		String password="";
		try(Connection connection=DriverManager.getConnection(url, userName, password))
		{
			
			String query="select project.Project_ID, project.Project_Name, client.Client_Name, project.Project_Details from project\r\n"
					+ "left join connection on connection.project_id=project.project_id\r\n"
					+ "left join client on client.Client_ID=connection.cient_id\r\n"
					+ "group by project.Project_ID,client.Client_ID;";
			
			Statement statement= connection.createStatement();
			ResultSet resultset=statement.executeQuery(query);
			
			while(resultset.next())
			{
				int id=resultset.getInt("Project_ID");
				String name=resultset.getString("Project_Name");
				String client=resultset.getString("Client_Name");
				String Details=resultset.getString("Project_Details");
				
				
				defaultTable.addRow(new Object[]{id,name,client,Details});
				
			}
			
			resultset.close();
			statement.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  JTable table = new JTable(defaultTable);
		         JScrollPane scrollPane = new JScrollPane(table);

		      frame.add(scrollPane);
		      frame.setVisible(true);
		         frame.pack();
		        frame.setLocationRelativeTo(null); 
		    
	}
}
