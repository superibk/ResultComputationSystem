package Main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class DatabaseManager {
	
	private Connection con;
	private Statement stmt;
	private ResultSet rs;

	// this class will help manage the database of the whole application
	
	private static final String DATABASE_TABLE_STUDENTS = "students";
	private static final String DATABASE_TABLE_RESULT = "result";
	private static final String DATABASE_TABLE_COURSE_DEPARTMENT = "depcourse";
	private static final String DATABASE_TABLE_OUTSTANDING = "outstanding";

	// DECLARATION OF ALL THE COLUMN REQUIRED TO BE CREATED
	public static final String KEY_ROWID = "ID";
	public static final String KEY_FIRST_NAME = "FIRSTNAME";
	public static final String KEY_LAST_NAME = "LASTNAME";
	public static final String KEY_MIDDLE_NAME = "MIDDLENAME";
	public static final String KEY_MATRIC = "MATRIC";
	public static final String KEY_LEVEL = "LEVEL";
	public static final String KEY_DEPARTMENT = "DEPARTMENT";
	public static final String KEY_PHONE = "PHONE";
	public static final String KEY_SEX = "SEX";
	public static final String KEY_DATE_OF_BIRTH = "BIRTH";
	public static final String KEY_STATE = "STATE";
	public static final String KEY_LOCAL_GOV = "LOCAL";
	public static final String KEY_RELIGION = "RELIGION";
	public static final String KEY_HOME_ADDRESS = "ADDRESS";
	public static final String KEY_MARITAL_STATUS = "STATUS";
	public static final String KEY_NEXT_OF_KIN = "NEXTOFKIN";
	public static final String KEY_NEXT_OF_KIN_NUMBER = "NEXTOFKINNUMBER";
	public static final String KEY_NEXT_OF_KIN_ADDRESS = "NEXTOFKINADDRESS";
	public static final String KEY_GUIADIAN_NAME = "GUIADIANNAME";
	public static final String KEY_NATIONALITY = "NATIONALITY";
//	public static final String KEY_CGPA = "CGPA";
	
	
	// DECLARATION OF ALL THE COURSES OFFERED AND RESULT 
		public static final String KEY_ROWID_COURSE = "ID";
		public static final String KEY_ROWID_OUTSTANDING = "ID";
		public static final String KEY_COURSES = "COURSES";
		public static final String KEY_SCORE = "SCORE";
		public static final String KEY_UNIT = "UNIT";
		public static final String KEY_GRADE = "GRADE";
		
		
	//SUMMARY DETAILS FOR THE STUDENTS 
	 public static final String KEY_CGPA = "CGPA";
	 public static final String KEY_CTPU = "CTPU";
	 
	 
 // DECLARATION FOR THE LIST OF COURSES IN A PARTICULAR DEPARTMENT
		
	 public static final String KEY_COURSE_TITLE = "COURSETITLE";
	
		
	
	
	public DatabaseManager(){
		initDatabase();
	}
	
	
	private void initDatabase() {
		try {
			Class.forName("org.h2.Driver");
			con = DriverManager.getConnection(
					"jdbc:h2:~/eksu ;IGNORECASE=TRUE ", "eksu", "");
			// this is the actual one the upper one is to make it case
			// insentitives con = DriverManager.getConnection("jdbc:h2:~/eksu",
			// "eksu", "" );
			stmt = con.createStatement();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			// is table already exist then this part will run
//			JOptionPane.showMessageDialog((Component)null, "Connecting to database","Error", JOptionPane.INFORMATION_MESSAGE);
			
		}
	}
	
	
	public  void deleteAllTable() throws SQLException{
		 stmt.executeUpdate( "DROP TABLE " + DATABASE_TABLE_STUDENTS);
		 stmt.executeUpdate( "DROP TABLE " + DATABASE_TABLE_RESULT);
		}
	 
	 
	// create students table
	 public void creatTable(){ 

		 try {
			stmt.executeUpdate("CREATE TABLE " + DATABASE_TABLE_STUDENTS + " ("
			 + KEY_ROWID_COURSE + " INT PRIMARY KEY AUTO_INCREMENT, "
			 + KEY_FIRST_NAME + " VARCHAR(50), "
			 + KEY_LAST_NAME + " VARCHAR(50), "
			 + KEY_MIDDLE_NAME + " VARCHAR(50), "
			 + KEY_MATRIC + " VARCHAR(20), "
			 + KEY_LEVEL + " VARCHAR(6), "
			 + KEY_PHONE + " VARCHAR(15), "
			 + KEY_DEPARTMENT + " VARCHAR(50), "
			 + KEY_SEX + " VARCHAR(7), "
			 + KEY_DATE_OF_BIRTH + " VARCHAR(15), "
			 + KEY_STATE + " VARCHAR(20), "
			 + KEY_LOCAL_GOV + " VARCHAR(30), "
			 + KEY_RELIGION + " VARCHAR(20), "
			 + KEY_HOME_ADDRESS + " VARCHAR(100), "
			 + KEY_MARITAL_STATUS + " VARCHAR(10), "
			 + KEY_NEXT_OF_KIN + " VARCHAR(20), "
			 + KEY_NEXT_OF_KIN_NUMBER + " VARCHAR(100), "
			 + KEY_NEXT_OF_KIN_ADDRESS + " VARCHAR(100), "
			 + KEY_GUIADIAN_NAME + " VARCHAR(20), "
			 + KEY_NATIONALITY + " VARCHAR(30));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			 JOptionPane.showMessageDialog((Component)null, "Students Table Already Exist","Error", JOptionPane.INFORMATION_MESSAGE);
		}	 
	 }
	 
	 
//	 create course table for individual students
	 public void creatTableCourse(String matric){ 
//		 this is to create table, id the table already exist , then the
		 try {
			stmt.executeUpdate("CREATE TABLE table" + matric.trim() + " ("
			 + KEY_ROWID_COURSE + " INT PRIMARY KEY AUTO_INCREMENT, "
			 + KEY_COURSES + " VARCHAR(20), "
			 + KEY_UNIT + " VARCHAR(20), "
			 + KEY_GRADE + " VARCHAR(20), "
			 + KEY_SCORE + " VARCHAR(20));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			 JOptionPane.showMessageDialog((Component)null, "Course Table Already Exist","Error", JOptionPane.INFORMATION_MESSAGE);
		}	 
	 }
	
/*********************************************************************************************************
 * *******************************************************************************************************	 
 ** This section covers all code required for confuguring outstanding									**
**********************************************************************************************************
**********************************************************************************************************/
	 
	 
	 
	 
//	 create outstanding table for individual students
	 public void creatTableOutstanding(String matric){ 
//		 this is to create table, id the table already exist , then the
		 try {
			stmt.executeUpdate("CREATE TABLE outstanding" + matric.trim() + " ("
			 + KEY_ROWID_COURSE + " INT PRIMARY KEY AUTO_INCREMENT, "
			 + KEY_COURSES + " VARCHAR(20), "
			 + KEY_UNIT + " VARCHAR(20));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			 JOptionPane.showMessageDialog((Component)null, "outstanding table Already Exist","Error", JOptionPane.INFORMATION_MESSAGE);
		}	 
	 }
	 
	 
	 
	 public ResultSet searchOutstanding(String matric) {
			try {
				rs = stmt.executeQuery("SELECT * FROM outstanding"+matric.trim());
				} 
			catch (SQLException e) {
						
			}
			return rs;

		}
		
		
		public void addOutstanding(String matric, String course,String unit){
			
			try {
				stmt.executeUpdate("INSERT INTO outstanding" + matric + "("
			    + KEY_COURSES+ "," 
				+ KEY_UNIT + ")"+ "VALUES ('"+course+"', '"+unit+"', )");
			
			} catch (SQLException e) {
//				JOptionPane.showMessageDialog(null,"Error create profile for new course" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
				e.printStackTrace();
			}
			
			
		}
		
		
		 public void removeOutstanding(String matric, int id){
	    	 
		 		try {
					stmt.executeUpdate("DELETE FROM outstanding" + matric.trim() + " WHERE "+ KEY_ROWID +"= "+ id);
				
				} catch (SQLException e) {
					JOptionPane.showMessageDialog(null, " Unable to Delete Details" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
					e.printStackTrace();
				}

		    	   	 
		     }
	 

 /*********************************************************************************************************
  ********************************************************************************************************	 
  ** This section covers all code required for confuguring outstanding									**
 **********************************************************************************************************
 **********************************************************************************************************/
	 
	 public ResultSet searchCourseForResult(String tableMatric, String courseCode) {
			try {
				rs = stmt.executeQuery("SELECT * FROM table"+tableMatric.trim()+" WHERE " + KEY_COURSES + " ='" + courseCode );
				} 
			catch (SQLException e) {}
			return rs;

		}
	 
	 
	 
	 
	 
	 
	 
	 
	 
	// this will create table with the list of department
	 public void creatDepartmentTable(){ 

		 try {
			stmt.executeUpdate("CREATE TABLE department ("
			 + KEY_ROWID + " INT PRIMARY KEY AUTO_INCREMENT, "
			 + KEY_DEPARTMENT + " VARCHAR(30));");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			 JOptionPane.showMessageDialog((Component)null, "Course Table Already Exist","Error", JOptionPane.INFORMATION_MESSAGE);
		}	 
	 }
	 
	 
	 // Add to department list 
	 
	 public void addDepartment(String department){
	 		
	 		try {
	 			stmt.executeUpdate("INSERT INTO department("
	 			+ KEY_DEPARTMENT + ")"+ " VALUES ('"+department+"')");
	 		
	 		} catch (SQLException e) {
	 			JOptionPane.showMessageDialog(null,"unable to insert new department "+ department , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
	 			e.printStackTrace();
	 		}
	 		
	 		
	 	}
	 
	 //remove from department list
	 
  public void removeDepartment( int id){
 	 
		try {
			stmt.executeUpdate("DELETE FROM department WHERE "+ KEY_ROWID +"= "+ id);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, " Unable to Delete department "  , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
	   	 
  }
  
  // this will return all the list of department in the database
  public ResultSet returnDepartmentList() {
		try {
			rs = stmt.executeQuery("SELECT * FROM department");
			} 
		catch (SQLException e) {
	
		}
		return rs;

	}
	 


	 
// create course table for department
	 public void creatTableDepCourse(String department){ 
		 // this line of code will remove the white space Inbewteen, before and after
		 // this is done because table names should not contain space on creation
//		 department.replaceAll("\\s", "");

		 try {
			stmt.executeUpdate("CREATE TABLE " + department.replaceAll("\\s", "").trim() + " ("
			 + KEY_ROWID_COURSE + " INT PRIMARY KEY AUTO_INCREMENT, "
			 + KEY_COURSES + " VARCHAR(20), "
			 + KEY_COURSE_TITLE + " VARCHAR(80), "
			 + KEY_UNIT + " VARCHAR(20));");
		} catch (SQLException e) {

			e.printStackTrace();
//			 JOptionPane.showMessageDialog((Component)null, "Table " + department + " already created" ,"Error", JOptionPane.INFORMATION_MESSAGE);
		}	 
	 }
	 
	 
	 public void addNewDepCourse(String department, String course, String title, String unit){
	 		
	 		try {
	 			stmt.executeUpdate("INSERT INTO " + department.replaceAll("\\s", "").trim() + "("
	 		    + KEY_COURSES+ "," 
	 		     + KEY_COURSE_TITLE+ ","
	 			+ KEY_UNIT + ")"+ "VALUES ('"+course+"', '"+title+"','"+unit+"')");
	 		
	 		} catch (SQLException e) {
	 			JOptionPane.showMessageDialog(null,"unable to insert new course into "+ department , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
	 			e.printStackTrace();
	 		}
	 		
	 		
	 	}
	 
     public void removeDeptCourse(String department, int id){
    	 
 		try {
			stmt.executeUpdate("DELETE FROM " + department.replaceAll("\\s", "").trim() + " WHERE "+ KEY_ROWID +"= "+ id);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, " Unable to Delete Course from " + department  , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

    	   	 
     }
     
     // this will search a dynamically created departmental table
     public ResultSet searchQueryDeptCourses(String department) {
 		try {
 			rs = stmt.executeQuery("SELECT * FROM "+department.replaceAll("\\s", "").trim());
 			} 
 		catch (SQLException e) {
 	
 		}
 		return rs;

 	}
     
 
	
	
	
	public ResultSet searchQuery(String queryValue, String type) {
		try {
			rs = stmt.executeQuery("SELECT * FROM students WHERE " + type + " LIKE '%" + queryValue + "%'");
			} 
		catch (SQLException e) {}
		return rs;

	}
	
	public ResultSet searchQueryStudentsExport(String department, String level) {
		try {
			rs = stmt.executeQuery("SELECT * FROM students WHERE " + KEY_DEPARTMENT + " ='" + department + "' AND " + KEY_LEVEL + " = '" + level + "'" );
			} 
		catch (SQLException e) {}
		return rs;

	}
	
	public ResultSet searchQueryCourse(String matric) {
		try {
			rs = stmt.executeQuery("SELECT * FROM table"+matric.trim());
			} 
		catch (SQLException e) {
					
		}
		return rs;

	}
	
	
	public void addNewCourse(String matric, String course, String score, String unit, String Grade){
		
		try {
			stmt.executeUpdate("INSERT INTO table" + matric + "("
		    + KEY_COURSES+ "," 
		     + KEY_UNIT+ ","
		      + KEY_GRADE+ ","
			+ KEY_SCORE + ")"+ "VALUES ('"+course+"', '"+unit+"','"+Grade+"', '"+score+"', )");
		
		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null,"Error create profile for new course" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		
	}
	
	
  

   
   
	public void closeConnection() {
		// this will close the connections
		try {
			stmt.close();
			con.close();
		} catch (SQLException e) {e.printStackTrace();}
	}
	
	
public void insertDetails(
		   	String firstName, 
		   	String lastName,
		   	String middleName,
		   	String matric,
		   	String dateOfBirth,
		   	String religion,
		   	String sex,
		   	String status,
		   	String phone,
		   	String department,
		   	String nextOfKin,
		   	String nextOfKinNumber,
			String nextOfKinAddress,
		   	String homeAddress,
		   	String guiadianName,
		   	String localGov,
		   	String state,
		   	String level,
		   	String nationality
		   	){	
		try {
			stmt.executeUpdate("INSERT INTO " + DATABASE_TABLE_STUDENTS + "("
		    + KEY_FIRST_NAME + "," 
			+ KEY_LAST_NAME + "," 
			+ KEY_MIDDLE_NAME + "," 
			+ KEY_MATRIC + "," 
			+ KEY_DATE_OF_BIRTH + "," 
			+ KEY_RELIGION + "," 
		    + KEY_SEX + "," 
		    + KEY_MARITAL_STATUS + "," 
		    + KEY_PHONE+ "," 
		    + KEY_DEPARTMENT+ "," 
		    + KEY_NEXT_OF_KIN + "," 
		    + KEY_NEXT_OF_KIN_NUMBER + "," 
		     + KEY_NEXT_OF_KIN_ADDRESS + "," 
		    + KEY_HOME_ADDRESS + "," 
		    + KEY_GUIADIAN_NAME + "," 
		    + KEY_LOCAL_GOV+ "," 
		    + KEY_STATE + "," 
		    + KEY_LEVEL + "," 
			+ KEY_NATIONALITY + ")"+ "VALUES ('"+firstName
		    +"', '"+lastName+"', '"+middleName+"', '"+matric+"','"+dateOfBirth+"','"+religion+"', '"+sex+"', '"+status+"', '"+phone+"', '"+department+"','"+nextOfKin
			+"','"+nextOfKinNumber+"','"+nextOfKinAddress+"', '"+homeAddress+"', '"+guiadianName+"', '"+localGov+"','"+state+"','"+level+"','"+nationality+"' )");
		
		} catch (SQLException e) {
//			JOptionPane.showMessageDialog(null,"Error Trying To Add New Student\n" +e.getMessage() , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}
		
		
		
	}

// this is to edit the database of the students
public void EditDetails(int id, 
	   	String firstName, 
	   	String lastName,
	   	String middleName,
	   	String matric,
	   	String dateOfBirth,
	   	String religion,
	   	String sex,
	   	String status,
	   	String phone,
	   	String nextOfKin,
	   	String nextOfKinNumber,
		String nextOfKinAddress,
	   	String homeAddress,
	   	String guiadianName,
	   	String localGov,
	   	String state,
	   	String level,
	   	String nationality
	   	){	
	try {
		// there will be update code here instead of insert which will create a new user
		stmt.executeUpdate("INSERT INTO " + DATABASE_TABLE_STUDENTS + "("
	    + KEY_FIRST_NAME + "," 
		+ KEY_LAST_NAME + "," 
		+ KEY_MIDDLE_NAME + "," 
		+ KEY_MATRIC + "," 
		+ KEY_DATE_OF_BIRTH + "," 
		+ KEY_RELIGION + "," 
	    + KEY_SEX + "," 
	    + KEY_MARITAL_STATUS + "," 
	    + KEY_PHONE+ "," 
	    + KEY_NEXT_OF_KIN + "," 
	    + KEY_NEXT_OF_KIN_NUMBER + "," 
	    + KEY_NEXT_OF_KIN_ADDRESS + "," 
	    + KEY_HOME_ADDRESS + "," 
	    + KEY_GUIADIAN_NAME + "," 
	    + KEY_LOCAL_GOV+ "," 
	    + KEY_STATE + "," 
	    + KEY_LEVEL + "," 
		+ KEY_NATIONALITY + ")"+ "VALUES ('"+firstName
	    +"', '"+lastName+"', '"+middleName+"', '"+matric+"','"+dateOfBirth+"','"+religion+"', '"+sex+"', '"+status+"', '"+phone+"','"+nextOfKin
		+"','"+nextOfKinNumber+"','"+nextOfKinAddress+"', '"+homeAddress+"', '"+guiadianName+"', '"+localGov+"','"+state+"','"+level+"','"+nationality+"' )");
	
	} catch (SQLException e) {
//		JOptionPane.showMessageDialog(null,"Error Trying To Add New Student" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		e.printStackTrace();
	}
	
	
}

	public void deleteStudents(int id){
		
		 
 		try {
			stmt.executeUpdate("DELETE FROM students  WHERE "+ KEY_ROWID +"= "+ id);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, " Unable to Delete Student Details" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

	
	
	}
	
	public ResultSet searchQuerySingle(int id) {
		try {
			rs = stmt.executeQuery("SELECT * FROM students WHERE "+ KEY_ROWID +"= "+ id);
			} 
		catch (SQLException e) {
			JOptionPane.showMessageDialog(null, " Unable to get Student Details" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
		}
		return rs;

	}


     public void removeCourse(String matric, int id){
    	 
 		try {
			stmt.executeUpdate("DELETE FROM table" + matric.trim() + " WHERE "+ KEY_ROWID +"= "+ id);
		
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, " Unable to Delete Details" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
			e.printStackTrace();
		}

    	   	 
     }
     
     
     
     // this will be used to update the followings
     
     public void updateDetials(int id){
    	 	 
    	 
     }
     
     
   // search and select department table
     
     public void departmentCourse(String department){
    	 
    	 try {
			rs = stmt.executeQuery("SELECT * FROM "+department.toUpperCase());
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
    	 
     }
     
     



	
	
	
	

}
