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

public class Project_assigne extends JFrame {
    private JTextField employeeIdField;
    private JTextField projectField;
    private JTextField clientField;
    private JButton assignButton;
    DefaultTableModel tableModel = new DefaultTableModel();

    public Project_assigne() {
        setTitle("Assign Project");
       // setSize(600, 400);
     //   setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        employeeIdField = new JTextField(10);
        projectField = new JTextField(15);
        clientField = new JTextField(15);
        assignButton = new JButton("Assign Project");

        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignProject();
            }
        });

        JPanel panel = new JPanel(new GridLayout(9, 2, 10, 10));
        panel.add(new JLabel("Employee ID:"));
        panel.add(employeeIdField);
        panel.add(new JLabel("Project:"));
        panel.add(projectField);
        panel.add(new JLabel("Client:"));
        panel.add(clientField);
        panel.add(assignButton);

       add(panel, BorderLayout.WEST);
        setVisible(true);
        
        
        //////////////////////////////////////////////////////////////////
        // Create and populate the table model
       
        tableModel.addColumn("ID");
        tableModel.addColumn("Name");
        tableModel.addColumn("Client_ID");
        tableModel.addColumn("Client_Name");
        tableModel.addColumn("Project_ID");
        tableModel.addColumn("Project_Name");

     populateTableModel(tableModel); // Populate table model with initial data

        // Create JTable and JScrollPane
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        // Add JScrollPane to the frame
      add(scrollPane, BorderLayout.CENTER);

        // Pack, set location, and make the frame visible
        pack();
        setLocationRelativeTo(null);
       setVisible(true);
       Color color = new Color(200, 242, 215);
       panel.setBackground(color);
       table.setBackground(new Color(200, 242, 215));

        //////////////////////////////////////////////////////////////////
    }
    
    /////////////////////////////////////////////////////////////////////

    public void populateTableModel(DefaultTableModel tableModel) {
        String url = "jdbc:mysql://localhost:3306/project2";
        String userName = "root";
        String password = "";

        try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            String query = "select employee.ID, employee.Name, client.Client_ID,client.Client_Name,project.Project_ID,project.Project_Name from employee\r\n"
            		+ "left join connection on connection.emp_id=employee.ID\r\n"
            		+ "left join client on client.Client_ID=connection.cient_ID\r\n"
            		+ "left join project on project.Project_ID=connection.project_id\r\n"
            		+ "order by employee.ID asc;";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("Name");
                String address = resultSet.getString("Client_ID");
                String gender = resultSet.getString("Client_Name");
                String shift = resultSet.getString("Project_ID");
                String dep = resultSet.getString("Project_Name");

                tableModel.addRow(new Object[]{id, name, address, gender, shift, dep});
            }
            

            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    ///////////////////////////////////////////////////////////////////

    private void assignProject() {
    	int employeeId = Integer.parseInt(employeeIdField.getText());
    	int project = Integer.parseInt(projectField.getText());
    	int client = Integer.parseInt(clientField.getText());
       
       
        String url = "jdbc:mysql://localhost:3306/project2";
        String userName="root";
       String password="";

        try {
            Connection connection = DriverManager.getConnection(url, userName, password);
            String query = "UPDATE connection SET cient_id=?, project_id=? WHERE emp_id=?";
            PreparedStatement statement = connection.prepareStatement(query);

            statement.setInt(1, client);
            statement.setInt(2, project);
            statement.setInt(3, employeeId);

            int rowsUpdated = statement.executeUpdate();
            
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Project assigned successfully.");
            } else {
                JOptionPane.showMessageDialog(this, "No records were updated.");
            }
            
            statement.close();
            connection.close();
            tableModel.setRowCount(0); // Clear existing data
            populateTableModel(tableModel); // Populate table model with updated data
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error assigning project.");
        }
    }

    public static void main(String[] args) {
      //  new Project_assigne().setVisible(true);
Project_assigne obj=new Project_assigne();
    }
}

