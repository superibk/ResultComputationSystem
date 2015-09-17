package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class ProfileResult extends JFrame {

	private JPanel contentPane;
	private JTextField txtScore;
	private JTextField txtGrade , txtUnit;
	private JLabel lblMatricno , lblCgpa , lblPoint, lblLoadunit ,lblCgpa_1;
	private String matricno;
	private DefaultListModel<Course> model;
	private DefaultListModel<ObjectOutstanding> modelOutstanding;
	int oddEvenFLag = 0;
	private JList list,list_1;
	private ResultSet rs;
	DatabaseManager database = new DatabaseManager();
	JComboBox comboBoxCourse;
	private String department;
	String result = "";
	String level ;
	ArrayList<ObjectOutstanding> proposedOutstanding  = new ArrayList<ObjectOutstanding>();
	
	// cgpa stuff
	
	int totalScore = 0;
	 int totalUnit = 0;
	 int multiply = 1;


	/**
	 * Create the frame.
	 */
	public ProfileResult(String matric, String department,String level) {
		
		this.setVisible(true);
		matricno = matric;
		this.level = level;
		this.department = department;
		model = new DefaultListModel<Course>();
		modelOutstanding = new DefaultListModel<ObjectOutstanding>();
		// initialize the value of the score to be empty
		
	   // JOptionPane.showMessageDialog((Component)null, matric,"Successful", JOptionPane.INFORMATION_MESSAGE);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		this. setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPanel = new JScrollPane();
		scrollPanel.setBounds(22, 43, 362, 605);
		contentPane.add(scrollPanel);
		
	   list = new JList();
		
		scrollPanel.setViewportView(list);
		
		JButton btnAddNew = new JButton("Add New ");
		btnAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			
				
				database.addNewCourse(matricno, comboBoxCourse.getSelectedItem().toString().trim(), txtScore.getText().trim(), txtUnit.getText().trim(), txtGrade.getText().trim());
				txtScore.setText("");
				txtGrade.setText("0");

				fillDetails();
					
				
			}
		});
		btnAddNew.setBounds(518, 155, 156, 25);
		contentPane.add(btnAddNew);
		
		txtScore = new JTextField();
		txtScore.setText("0");
		
		txtScore.addKeyListener( new KeyListener() {
			
			@Override
			public void keyTyped(KeyEvent arg0) {
				
				
			}
			
			@Override
			public void keyReleased(KeyEvent arg0) {
               String value = onTypeResult(txtScore.getText());
			   txtGrade.setText(value);
				
			}
			
			@Override
			public void keyPressed(KeyEvent arg0) {
				String value = onTypeResult(txtScore.getText());
				txtGrade.setText(value);
				
			}
		});
		txtScore.setBounds(518, 48, 156, 25);
		contentPane.add(txtScore);
		txtScore.setColumns(10);
		
		 comboBoxCourse = new JComboBox();
		 comboBoxCourse.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		   
		 		  UnitObject object  = (UnitObject)comboBoxCourse.getSelectedItem();
		 		  txtUnit.setText(object.getUnit());
		 		
		 	}
		 });
		comboBoxCourse.setBounds(518, 12, 156, 24);
		contentPane.add(comboBoxCourse);
		
		JLabel lblCourse = new JLabel("Course");
		lblCourse.setBounds(430, 17, 70, 15);
		contentPane.add(lblCourse);
		
		JLabel lblGrade = new JLabel("Score");
		lblGrade.setBounds(430, 53, 70, 15);
		contentPane.add(lblGrade);
		
		txtGrade = new JTextField();
		txtGrade.setEditable(false);
		txtGrade.setBounds(518, 118, 156, 25);
		contentPane.add(txtGrade);
		txtGrade.setColumns(10);
		
		JLabel lblGrade_1 = new JLabel("Grade");
		lblGrade_1.setBounds(430, 123, 70, 15);
		contentPane.add(lblGrade_1);
		
		JButton btnRemoveCourse = new JButton("Remove Course");
		btnRemoveCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			Course course =	(Course) list.getSelectedValue();
			 database.removeCourse(matricno, course.getId());
			 fillDetails();
				
			}
		});
		btnRemoveCourse.setBounds(518, 192, 156, 25);
		contentPane.add(btnRemoveCourse);
		
		JLabel lblMatric = new JLabel("Matric:");
		lblMatric.setBounds(430, 283, 70, 15);
		contentPane.add(lblMatric);
		
	    lblMatricno = new JLabel("Matric_no");
		lblMatricno.setBounds(561, 283, 70, 15);
		contentPane.add(lblMatricno);
		
		JLabel lblTotalLoadUnit = new JLabel("TLU:");
		lblTotalLoadUnit.setBounds(430, 310, 113, 15);
		contentPane.add(lblTotalLoadUnit);
		
	     lblLoadunit = new JLabel("load_unit");
		lblLoadunit.setBounds(561, 310, 70, 15);
		contentPane.add(lblLoadunit);
		
		JLabel lblTotalPoint = new JLabel("TCP:");
		lblTotalPoint.setBounds(430, 337, 108, 15);
		contentPane.add(lblTotalPoint);
		
		 lblPoint = new JLabel("point");
		lblPoint.setBounds(561, 337, 70, 15);
		contentPane.add(lblPoint);
		
		lblCgpa = new JLabel("CGPA:");
		lblCgpa.setBounds(430, 364, 70, 15);
		contentPane.add(lblCgpa);
		
		lblCgpa_1 = new JLabel("cgpa");
		lblCgpa_1.setBounds(561, 364, 70, 15);
		contentPane.add(lblCgpa_1);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new Details();
				ProfileResult.this.setVisible(false);
				ProfileResult.this.dispose();
			}
		});
		btnBack.setBounds(426, 678, 117, 25);
		contentPane.add(btnBack);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnExit.setBounds(573, 678, 117, 25);
		contentPane.add(btnExit);
		
		JLabel lblCourseDetails = new JLabel("Course Details");
		lblCourseDetails.setFont(new Font("Dialog", Font.BOLD, 16));
		lblCourseDetails.setBounds(141, 16, 144, 15);
		contentPane.add(lblCourseDetails);
		
		txtUnit = new JTextField();
		txtUnit.setEditable(false);
		txtUnit.setBounds(518, 85, 156, 25);
		txtUnit.setText("1");
		contentPane.add(txtUnit);
		
		JLabel lblUnit = new JLabel("Unit");
		lblUnit.setBounds(430, 80, 70, 15);
		contentPane.add(lblUnit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(425, 407, 265, 198);
		contentPane.add(scrollPane);
		
		list_1 = new JList();
		scrollPane.setViewportView(list_1);
		
		JLabel lblOutstanding = new JLabel("OutStanding");
		lblOutstanding.setForeground(Color.RED);
		lblOutstanding.setFont(new Font("Courier New", Font.BOLD, 20));
		lblOutstanding.setBounds(502, 380, 132, 15);
		contentPane.add(lblOutstanding);
		
		JButton btnNewButton = new JButton("Second");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// change the oddEvenFLag to 2
				oddEvenFLag = 2;
				fillDetails();
				
			}
		});
		btnNewButton.setBounds(22, 660, 102, 25);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("First");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				oddEvenFLag = 1;
				fillDetails();
			}
		});
		btnNewButton_1.setBounds(141, 660, 102, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("ALL");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				oddEvenFLag = 0;
				fillDetails();
			}
		});
		btnNewButton_2.setBounds(267, 660, 117, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Add Outstanding");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				

				database.addOutstanding(matricno, comboBoxCourse.getSelectedItem().toString().trim(), txtUnit.getText().trim());
				fillOutstanding();
					
			}
		});
		btnNewButton_3.setBounds(518, 229, 156, 25);
		contentPane.add(btnNewButton_3);
		
		JButton btnRemoveOutstanding = new JButton("Remove Outstanding");
		btnRemoveOutstanding.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				ObjectOutstanding outstanding =	(ObjectOutstanding)list_1.getSelectedValue();
				 database.removeOutstanding(matricno, outstanding.getId());
				 fillOutstanding();
			}
		});
		btnRemoveOutstanding.setBounds(430, 606, 260, 25);
		contentPane.add(btnRemoveOutstanding);
		
		JButton btnNewButton_4 = new JButton("Check For Outstanding");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillDetails();
				showOutstanding();
			}
		});
		btnNewButton_4.setBounds(430, 643, 260, 25);
		contentPane.add(btnNewButton_4);
		
	    // THIS WILL FILL THE COURSE COMBOX 
       fillCourseBox(this.department);
                
	// this will fill the details of the label	
		fillDetails();
		
  // this will fill the outstanding section
		
		fillOutstanding();
		
	}
	

	public String onTypeResult(String usevalue){
		int  value = Integer.parseInt(usevalue);
		String returnValue = "";
		if(value >= 70 && value <=100){
			returnValue = "A";
		}
		else if(value >= 60 && value <=69){
			returnValue = "B";
		}
		else if(value >= 50 && value <=59){
			returnValue = "C";
		}
		else if(value >= 45 && value <=49){
			returnValue = "D";
		}
		else if(value >=40  && value <=44){
			returnValue = "E";
		}
		else if(value >=0  && value <=39){
			returnValue = "F";
		}
		else {
				returnValue = "Must Be Between 0-100";
			}
		return returnValue;
	}
	
	public void fillDetails(){
		
		Thread updater = new Thread() {

			public void run() {
				
				
				proposedOutstanding.clear();
				result = "";
				
				 
				model.removeAllElements();
				lblMatricno.setText(matricno);
//				Course header = new Course(700, "COURSE", "UNIT", "GRADE");
//				fillModel(header, oddEvenFLag);
				try {
                    totalUnit = 0;
                    totalScore = 0;
					rs = database.searchQueryCourse(matricno.trim());
					while (rs.next()) {
						int idReturn = rs.getInt(DatabaseManager.KEY_ROWID_COURSE);
						String coursesReturned = rs.getString(DatabaseManager.KEY_COURSES);
						String scoreReturned = rs.getString(DatabaseManager.KEY_SCORE);
						String unitReturned = rs.getString(DatabaseManager.KEY_UNIT);
						String gradeReturned = rs.getString(DatabaseManager.KEY_GRADE);
						Course details = new Course(idReturn, coursesReturned, "     "+unitReturned, "     "+gradeReturned);
						multiply = onReturnPoint(gradeReturned) * Integer.parseInt(unitReturned);
						totalScore = totalScore + multiply;
						totalUnit = totalUnit + Integer.parseInt(unitReturned);
						
						// this is to detect the outstanding
						
						if (gradeReturned.trim().equalsIgnoreCase("F") && !getNoresult(coursesReturned)) {
							ObjectOutstanding object = new ObjectOutstanding(idReturn, coursesReturned, unitReturned);
							proposedOutstanding.add(object);
							result = result + coursesReturned + "\n";
						}
						fillModel(details, oddEvenFLag);
					}

				} catch (SQLException e) {
                                    e.printStackTrace();
                    
				}

				list.setModel(model);
				lblLoadunit.setText(Integer.toString(totalUnit));
				lblPoint.setText(Integer.toString(totalScore));
                double what = (double)  totalScore / totalUnit;
                
                int whole = (int) what;
                int part = (int) (what * 100);
                System.err.println(what+" "+whole+" "+part+ProfileResult.this.department);
                 String parts = (Integer.toString(part)).substring(1, 3);// this part is givin out of bound exception error
                
//                String parts = (Integer.toString(part));
                lblCgpa_1.setText(Integer.toString(whole)+"."+parts);
                
			}

		};
		updater.start();
 }
	
	
	 public void fillOutstanding(){

			
			Thread outstandingThread = new Thread() {

				public void run() {
					 
					modelOutstanding.removeAllElements();
					
					try {
	                    
							rs = database.searchOutstanding(matricno.trim());
							while (rs.next()) {
								int idOutstandingReturn = rs.getInt(DatabaseManager.KEY_ROWID_COURSE);
								String outstandingCoursesReturned = rs.getString(DatabaseManager.KEY_COURSES);
								String outstandingUnitReturned = rs.getString(DatabaseManager.KEY_UNIT);
								ObjectOutstanding outstandingDetails = new ObjectOutstanding(idOutstandingReturn, outstandingCoursesReturned, "     "+outstandingUnitReturned);
								modelOutstanding.addElement(outstandingDetails);
								
								
						}

					} catch (SQLException e) { e.printStackTrace();
	                    
					}

					list_1.setModel(modelOutstanding);
					
					
			}

			};
			outstandingThread.start();
	  }
	
	public void fillModel(Course course, int semester){
	  String oddEven = course.getCourse().substring(5);
//	  System.err.println("result" + oddEven);
	  int lastDigit = Integer.parseInt(oddEven);
	  if (semester == 2 && checkIfEven(lastDigit)){
		     model.addElement(course);
		}
	if (semester == 1 && !checkIfEven(lastDigit)){
		     model.addElement(course);
		}
	if (semester == 0){
	     model.addElement(course);
	}
	
		
}
	
public int onReturnPoint(String grade){
  		int returnValue = 0;
  		if(grade.trim().equalsIgnoreCase("A")){
  			returnValue = 5;
  		}
  		else if(grade.trim().equalsIgnoreCase("B")){
  			returnValue =4;
  		}
  		else if(grade.trim().equalsIgnoreCase("C")){
  			returnValue = 3;
  		}
  		else if(grade.trim().equalsIgnoreCase("D")){
  			returnValue = 2;
  		}
  		else if(grade.trim().equalsIgnoreCase("E")){
  			returnValue = 1;
  		}
  		else if(grade.trim().equalsIgnoreCase("F")){
  			returnValue = 0;
  		}
  		
  		return returnValue;
  	}
	
  public void fillCourseBox(String department){
	 
	ResultSet  rsCourse = database.searchQueryDeptCourses(department);
	   try {
		while(rsCourse.next()){
			String courseContent = rsCourse.getString(database.KEY_COURSES);
			String unit = rsCourse.getString(database.KEY_UNIT);
			UnitObject unitObject = new UnitObject(courseContent, unit);
			comboBoxCourse.addItem(unitObject);
		}
	} catch (SQLException e) { }
 }
  
  // this section was initially used for outstanding
  
//  public void fillOutStanding(String outstanding){
//	  String[] result = outstanding.split("\\,");
//		
//	  // loop over all the splited values
//		for (String s : result)
//		{modelOutstanding.addElement(s);}
//		list_1.setModel(modelOutstanding);
//		
//	}
  
   public boolean checkIfEven(int value){
	   if ( (value & 1) == 0 ) { return true;} else { return false;  }
   }
   
   
   
   public boolean getNoresult(String courses) {
	   
	   
	   String studentLevel = Integer.toString(getLevelIngeger());
		String levelFromCourse = courses.substring(3, 4);
		if (studentLevel.equalsIgnoreCase(levelFromCourse))
			return true;
		else
			return false;

	}
   
   public int getLevelIngeger() {
		
		return Integer.parseInt(level.substring(0, 1));
	}

	

   
   
   public void showOutstanding(){

		
		  //Custom DialogBox
			int n = JOptionPane.showConfirmDialog(
					    ProfileResult.this,
					    "List Of Outstanding \n"
					    +result,
					    "Add To Outstanding ?",
					    JOptionPane.YES_NO_OPTION);
			
			System.err.print(n);
			if (n == 0){
//				database.deleteStudents((int)listStudent.getSelectedValue().getId());
//				fillDetails("", database.KEY_FIRST_NAME);
				for (ObjectOutstanding s :proposedOutstanding) {
					database.addOutstanding(matricno, s.getCourse(), s.getUnit());
					}
				fillOutstanding();
				
				
				
			}
									
			
				
			
   }
}
