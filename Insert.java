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
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Insert {
    
    JFrame frame = new JFrame();
    JLabel l1 = new JLabel("  ID :");
    JLabel l2 = new JLabel("  Name :");
    JLabel l3 = new JLabel("  Address :");
    JLabel l4 = new JLabel("  Gender :");
    JLabel l5 = new JLabel("  Password :");
    JLabel l6 = new JLabel("  DEPARTMENT :");
    JTextField id = new JTextField();
    JTextField name = new JTextField();
    JTextField address = new JTextField();
    JTextField depart = new JTextField();
    JRadioButton maleRadioButton = new JRadioButton("Male");
    JRadioButton femaleRadioButton = new JRadioButton("Female");
    JTextField pass = new JTextField();
    JButton b1 = new JButton("UPDATE");

    Insert() {
        JPanel panel = new JPanel(new GridLayout(6, 2, 10, 10)); // Change 5 to 6
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadioButton);
        genderGroup.add(femaleRadioButton);
       
        panel.add(l1);
        panel.add(id);
        panel.add(l2);
        panel.add(name);
        panel.add(l3);
        panel.add(address);
        panel.add(l4);
        
        JPanel genderPanel = new JPanel();
        genderPanel.add(maleRadioButton);
        genderPanel.add(femaleRadioButton);
        panel.add(genderPanel);
        
        panel.add(l5);
        panel.add(pass);
        
      //  panel.add(l6);
        //panel.add(depart);
        
        frame.add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(b1);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        b1.addActionListener(e -> insertData());

        frame.setSize(500, 300);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);

        Color color = new Color(200, 242, 215);
        panel.setBackground(color);
        buttonPanel.setBackground(color);
        b1.setBackground(color);
    }

    private void insertData() {
        String url = "jdbc:mysql://localhost:3306/project2";
        String userName = "root";
        String password = "";

        String insertQuery = "INSERT INTO employee VALUES (?, ?, ?, ?, ?, NULL)";
        String connectionQuery = "INSERT INTO connection VALUES (?, NULL, NULL, NULL, ?);";
//////------------------------------------------------------------------------------------------------------
        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            PreparedStatement employeeStatement = connection.prepareStatement(insertQuery);

            int idValue = Integer.parseInt(id.getText());
            String nameValue = name.getText();
            String addressValue = address.getText();
            String genderValue = maleRadioButton.isSelected() ? "Male" : "Female";
            String passValue = pass.getText();

            employeeStatement.setInt(1, idValue);
            employeeStatement.setString(2, nameValue);
            employeeStatement.setString(3, addressValue);
            employeeStatement.setString(4, genderValue);
            employeeStatement.setString(5, passValue);

            int rowsAffectedEmployee = employeeStatement.executeUpdate();
//////------------------------------------------------------------------------------------------------------
            if (rowsAffectedEmployee > 0) {
                PreparedStatement connectionStatement = connection.prepareStatement(connectionQuery);
                int shiftValue = 0; 
                connectionStatement.setInt(1, idValue);
                connectionStatement.setInt(2, shiftValue);

                int rowsAffectedConnection = connectionStatement.executeUpdate();

                if (rowsAffectedConnection > 0) {
                    JOptionPane.showMessageDialog(frame, "Data inserted successfully!");
                } else {
                    JOptionPane.showMessageDialog(frame, "Failed to insert data", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(frame, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        
  //////------------------------------------------------------------------------------------------------------
        
      
    }

    public static void main(String args[]) {
        SwingUtilities.invokeLater(() -> {
            Insert obj = new Insert();
        });
    }
}

	


	



