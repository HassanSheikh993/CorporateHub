package project;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.*;
public class Try extends JFrame {
	
	
	JLabel user=new JLabel("Username");
	JTextField name=new JTextField();
	
	JLabel pass=new JLabel("Password");
	JPasswordField word=new JPasswordField();
	
	JButton login=new JButton("LOGIN");
	JLabel head=new JLabel("    DEVLOOPS");
	JLabel lo=new JLabel("LOGIN");
	//ImageIcon img=new ImageIcon("E:\\HASSAN 2\\project pics\\login.png");
	ImageIcon img=new ImageIcon("E:\\pics\\4.png");
	JLabel image=new JLabel(img);
	JPanel P3=new JPanel();
	JPanel P1=new JPanel();
	JPanel P2=new JPanel();

	
	Try()
	{
P1.setLayout(null);
P2.setLayout(null);
P3.setLayout(null);
		user.setBounds(50,150,80,30);
		name.setBounds(130,150,230,30);
		pass.setBounds(50,220,80,30);
		word.setBounds(130,220,230,30);
		
		login.setBounds(140,300,180,30);
		
		login.setBackground(new Color (0,153,153));
		
		
		user.setFont(new Font("Arial",Font.BOLD,15));
		user.setForeground(new Color (0,153,153));
		
		pass.setFont(new Font("Arial",Font.BOLD,15));
		pass.setForeground(new Color (0,153,153));
		
		lo.setBounds(170,80,100,50);
		lo.setFont(new Font("Arial",Font.BOLD,30));
		lo.setForeground(new Color (0,153,153));
		
		P1.add(user);
		P1.add(name);
		P1.add(pass);
		P1.add(word);
		P1.add(login);
		P1.add(lo);
		//head.setFont(new Font("Calibri", Font.BOLD,30));
		head.setBounds(130, 50, 400, 50);
		head.setFont(new Font("Arial",Font.BOLD,30));
		head.setForeground(Color.white);
		image.setBounds(0, 0, 417, 417);
		
		P2.add(head);
		P3.add(image);
		P2.add(P3);
		
		
		
	P1.setBackground(new Color(204,255,255));	
	//P1.setBackground(Color.white);
	
	P2.setBounds(0, 0, 400, 600);
	P1.setBounds(534, 0, 400, 562);
	P3.setBounds(70,100,417,417);
	P2.setBackground(new Color (0,153,153));
	P3.setBackground(new Color (0,153,153));
	
	this.add(P1);
	this.add(P2);
	

	this.setSize(950, 600);
	this.setLocationRelativeTo(null);
	this.setVisible(true);
	this.setLayout(null);
	  String url = "jdbc:mysql://localhost:3306/project2";
      String userName = "root";
      String password = "";

	 login.addActionListener(new ActionListener() {
         @SuppressWarnings("deprecation")
         public void actionPerformed(ActionEvent e) {
             if (e.getSource() == login) {
                 try (Connection connection = DriverManager.getConnection(url, userName, password)) {
                     String Name = name.getText();
                     String employeePassword = new String(word.getPassword());
                     PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee WHERE Name = ? AND Password = ?");
                     preparedStatement.setString(1, Name);
                     preparedStatement.setString(2, employeePassword);

                     ResultSet rs = preparedStatement.executeQuery();
                   
                     if (rs.next()) {
                         int roll = rs.getInt("roll");
                         int id=rs.getInt("ID");
                         if (roll == 1) {
                             dispose();
                             Admin obj = new Admin();
                         } else if (roll == 0) {
                             dispose();
                             Employee obj = new Employee(Name, employeePassword, id);
                         }
                     } else {
                         JOptionPane.showMessageDialog(null, "Incorrect username or Password", "Alert", JOptionPane.ERROR_MESSAGE);
                     }

                     rs.close();
                     preparedStatement.close();
                 } catch (SQLException e1) {
                     e1.printStackTrace();
                 }
             }
         }
     });
	
	}
	
	
	
	public static void main(String[]args)
	{
		Try obj=new Try();
			
		
	}
}

