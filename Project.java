package project;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Project {
	
	JFrame frame=new JFrame();
	JButton b1=new JButton("select");
	JButton b2=new JButton("select");
	JButton b3=new JButton("select");
	JLabel l1=new JLabel("INSERT NEW PROJECT");
	JLabel l2=new JLabel("DISPLAY RECORD");
	JLabel l3=new JLabel("ASSIGN PROJECT");
	JLabel heading = new JLabel("PROJECT CONTROL");
	
	Project()
	{
		l1.setFont(new Font("Calibri", Font.BOLD,15));
		l2.setFont(new Font("Calibri", Font.BOLD,15));
		l3.setFont(new Font("Calibri", Font.BOLD,15));
		heading.setFont(new Font("Calibri", Font.BOLD,30));
		
	    l1.setBounds(50,150,220,30);
		b1.setBounds(250,150,230,30);
		l2.setBounds(50,220,220,30);
		b2.setBounds(250,220,230,30);
		l3.setBounds(50,290,220,30);
		b3.setBounds(250,290,230,30);
		 heading.setBounds(250, 20, 350, 30);
		
		 frame.add(b1);
		 frame.add(b2);
		 frame.add(l1);
		 frame.add(l2);
		 frame.add(b3);
		 frame.add(l3);
		 frame.add(heading);
		 
		 Color color = new Color(255, 239, 213);
	        frame.getContentPane().setBackground(color);
		 frame.setSize(700,700);
		   frame.setLayout(null);
		  frame.setVisible(true);
		    frame.setLocationRelativeTo(null); 
		    
		    
/////////////////--------------------------------------------------------------------
		    
		    b1.addActionListener(new ActionListener()
		    		{

					
						public void actionPerformed(ActionEvent e) {
							
							Insert_project obj=new Insert_project();
						}
		    	
		    		});
		    
		    b2.addActionListener(new ActionListener()
		    		{

						
						public void actionPerformed(ActionEvent e) {
							
							Show_Project obj=new Show_Project();
						}
		    	
		    		});
		    
		    b3.addActionListener(new ActionListener()
    		{

				
				public void actionPerformed(ActionEvent e) {
					
					Project_assigne obj=new Project_assigne();
				}
    	
    		});
		    	
		    
	}
	
	
	
	public static void main(String args[])
	{
		Project obj=new Project();
	}

}
