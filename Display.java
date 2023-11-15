package project;

import java.awt.Color;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Display {
	
	
	Display()
	{
		 JFrame frame = new JFrame("Default Table Demo");
         //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

         DefaultTableModel tableModel = new DefaultTableModel();
         tableModel.addColumn("ID");
         tableModel.addColumn("Name");
         tableModel.addColumn("Address");
         tableModel.addColumn("Gender");
         tableModel.addColumn("Shift");
         tableModel.addColumn("Department_Name");
         

 /////----------------------------------- DATA BASE CONNECTION
         String url = "jdbc:mysql://localhost:3306/project2";
         String userName = "root";
         String password = "";

         try (Connection connection = DriverManager.getConnection(url, userName, password)) {
            // String query = "SELECT * FROM employee"; ///
             String query =" select employee.ID,employee.Name,employee.Address,employee.Gender,shift.Shift,department.Department_Name from employee\r\n"
             		+ "             		 left join connection on connection.emp_id=employee.ID\r\n"
             		+ "             		left join shift on shift.shift_id=connection.shift_id\r\n"
             		+ "             		 left join department on department.dep_id=connection.depart_id order by employee.ID asc;"
             		
             		;
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query);

             while (resultSet.next()) {
                 int id = resultSet.getInt("ID");
                 String name = resultSet.getString("Name");
                 String address = resultSet.getString("Address");
                 String gender = resultSet.getString("Gender");
                 String shift=resultSet.getString("Shift");
                 String dep=resultSet.getString("Department_Name");

                 tableModel.addRow(new Object[]{id, name, address, gender,shift, dep});
             }

                resultSet.close();
             statement.close();
         } catch (SQLException e) {
             e.printStackTrace();
         }

////---------------------------------------------------------------------------------- JTABLE
         JTable table = new JTable(tableModel);

////---------------------------------------------------------------------------------- JSCROLL PANE 
         JScrollPane scrollPane = new JScrollPane(table);

      frame.add(scrollPane);
         frame.pack();
        frame.setLocationRelativeTo(null); 
         frame.setVisible(true);
      
     }
	
	
///////////////////////------------------------------------------------------------- MAIN METHORD	
	
	  public static void main(String args[])
{
	Display obj=new Display();
		
	
}
	}



