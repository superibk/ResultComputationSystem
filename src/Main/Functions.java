package Main;

import java.awt.Component;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Functions {
	
	static ResultSet rs;
	


	public static String outStanding(String matric , DatabaseManager database){
		 String outStanding = "";
		rs = database.searchQueryCourse(matric.trim());
		
		try {
//			rs.beforeFirst();
			while (rs.next()) {
				String gradeReturn = rs.getString(DatabaseManager.KEY_GRADE);
				if (gradeReturn.equalsIgnoreCase("F")){
					String code = rs.getString(DatabaseManager.KEY_COURSES);
					JOptionPane.showMessageDialog((Component)null, code ,"Successful", JOptionPane.INFORMATION_MESSAGE);
//					outStanding = code + ", " ;				
				}
				
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog((Component)null, "connot connect another instance of the database" ,"Successful", JOptionPane.INFORMATION_MESSAGE);
		}
		
	   return outStanding;
	}
	
	
	// a function to calculate 
    }
