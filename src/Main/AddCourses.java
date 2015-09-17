package Main;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Box.Filler;
import javax.swing.border.EmptyBorder;
import javax.swing.DefaultListModel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;

public class AddCourses extends JFrame {

	private JPanel contentPane;
	private JTextField txtCourseCode;
	private JTextField txtCourseUnit;
	private JTextField txtCourseTitle, txtAddDepartment ;
	JComboBox comDepartment;
	DatabaseManager database = new DatabaseManager();
	ResultSet rs, rsDepartment ;
	JList listCourse;
	private DefaultListModel<AddCourseObject> model;

	
	/**
	 * Create the application
	 */
	
	public AddCourses() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		setBounds(100, 100, 796, 497);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//create the model element for the list class
		model = new DefaultListModel<AddCourseObject>();
		
		
		
		txtAddDepartment = new JTextField();
		txtAddDepartment.setBounds(24, 37, 184, 24);
		contentPane.add(txtAddDepartment);
		
		JButton btnNewButton = new JButton("Add New Course");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// this code will attempt to create the department table if not exist
                                if(txtCourseCode.getText().trim().isEmpty() || txtCourseTitle.getText().trim().isEmpty() || txtCourseUnit.getText().trim().isEmpty()){
                                    JOptionPane.showMessageDialog(null, "One or More Fields Required","Error",JOptionPane.ERROR_MESSAGE);
                                    return;
                                } else {
                    String dept = comDepartment.getSelectedItem().toString().trim();
                
                                    database.creatTableDepCourse(dept);
                                    database.addNewDepCourse(dept, txtCourseCode.getText().toUpperCase().trim(), txtCourseTitle.getText().toUpperCase().trim(), txtCourseUnit.getText().toUpperCase().trim());
                                    fillCourseList();
                                    JOptionPane.showMessageDialog(null, txtCourseCode.getText()  );
                                    txtCourseCode.setText("");
                                    txtCourseTitle.setText("");
                                    txtCourseUnit.setText("");
                                   
                                }
			}
		});
		btnNewButton.setBounds(12, 290, 306, 25);
		contentPane.add(btnNewButton);
		
		JButton btnRemoveCourse = new JButton("Remove Course");
		btnRemoveCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				AddCourseObject course =	(AddCourseObject)listCourse.getSelectedValue();
				database.removeDeptCourse(comDepartment.getSelectedItem().toString(), course.getId());
				fillCourseList();
				
				
			}
		});
		btnRemoveCourse.setBounds(12, 327, 306, 25);
		contentPane.add(btnRemoveCourse);
		
		comDepartment = new JComboBox();
		comDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				fillCourseList();
			}
		});
		comDepartment.setBounds(144, 73, 174, 24);
		contentPane.add(comDepartment);
		
		JLabel lblFaculty = new JLabel("New Department");
		lblFaculty.setBounds(24, 14, 123, 15);
		contentPane.add(lblFaculty);
		
		JLabel lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(24, 78, 123, 15);
		contentPane.add(lblDepartment);
		
		txtCourseCode = new JTextField();
		txtCourseCode.setBounds(144, 122, 174, 19);
		contentPane.add(txtCourseCode);
		txtCourseCode.setColumns(10);
		
		txtCourseUnit = new JTextField();
		txtCourseUnit.setBounds(144, 153, 174, 19);
		contentPane.add(txtCourseUnit);
		txtCourseUnit.setColumns(10);
		
		txtCourseTitle = new JTextField();
		txtCourseTitle.setBounds(24, 216, 294, 62);
		contentPane.add(txtCourseTitle);
		txtCourseTitle.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Course Code");
		lblNewLabel.setBounds(24, 124, 100, 15);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Course Unit");
		lblNewLabel_1.setBounds(24, 155, 100, 15);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Course Title");
		lblNewLabel_2.setBounds(24, 186, 113, 15);
		contentPane.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(355, 12, 427, 444);
		contentPane.add(scrollPane);
		
		listCourse = new JList();
		scrollPane.setViewportView(listCourse);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				new Details();
				AddCourses.this.setVisible(false);
				AddCourses.this.dispose();
			}
		});
		btnNewButton_1.setBounds(12, 397, 306, 25);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Exit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {		
				System.exit(0);
			}
		});
		btnNewButton_2.setBounds(7, 434, 311, 25);
		contentPane.add(btnNewButton_2);
		
		JButton btnAdd = new JButton("Add ");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(txtAddDepartment.getText().trim().isEmpty()){
                                    JOptionPane.showMessageDialog(null, "One or More Fields required", "Error", JOptionPane.ERROR_MESSAGE);
                                } else {
                                    database.addDepartment(txtAddDepartment.getText().toUpperCase());
                                    fillDepartmentList();
                            }
			}
		});
		btnAdd.setBounds(213, 37, 105, 25);
		contentPane.add(btnAdd);
		
		JButton btnDeleteCurrentDepartment = new JButton("Delete Current Department");
		btnDeleteCurrentDepartment.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				DepartmentObject departmentObject = (DepartmentObject)comDepartment.getSelectedItem();
				database.removeDepartment(departmentObject.getId());
				fillDepartmentList();
			}
		});
		btnDeleteCurrentDepartment.setBounds(12, 364, 306, 25);
		contentPane.add(btnDeleteCurrentDepartment);

		fillDepartmentList();
		fillCourseList();
		
		
	}
	
	
	// this functiion helps to fill course into the list 
	
	public void fillCourseList(){
		
		Thread updateCourse = new Thread() {

			public void run() {
			
				model.removeAllElements();
				try {
					rs = database.searchQueryDeptCourses(comDepartment.getSelectedItem().toString().trim());
					while (rs.next()) {
						int idReturn = rs.getInt(DatabaseManager.KEY_ROWID_COURSE);
						String coursesReturned = rs.getString(DatabaseManager.KEY_COURSES);
						String titleReturned = rs.getString(DatabaseManager.KEY_COURSE_TITLE);
						String unitReturned = rs.getString(DatabaseManager.KEY_UNIT);
						AddCourseObject courseDetails = new AddCourseObject(idReturn, coursesReturned,unitReturned,titleReturned);
						model.addElement(courseDetails);
			    	}

				} catch (SQLException e) {}
				listCourse.setModel(model);
				
			}

		};
		updateCourse.start();
	    
  }
	
	 public void fillDepartmentList(){
		 
		
					comDepartment.removeAllItems();
					try {
						rsDepartment = database.returnDepartmentList();
						while (rsDepartment.next()) {
							int idReturnDepartment = rsDepartment.getInt(DatabaseManager.KEY_ROWID);
							String departmentReturned = rsDepartment.getString(DatabaseManager.KEY_DEPARTMENT);
							DepartmentObject object1 =  new DepartmentObject(idReturnDepartment, departmentReturned);
							comDepartment.addItem(object1);
				    	}

					} catch (SQLException e) {}
					
	 }
	
}
