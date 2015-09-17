package Main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Details extends JFrame {

	private JPanel contentPane;
	private JTextField txtSearch;
	private ResultSet rs;
	private JList<Students> listStudent;
	private DefaultListModel<Students> model;
	private JLabel lblResultMatric, lblResultFirstName, lblResultLevel,
			lblResultState, lblResultDate, lblResultLastName, lblResultPhone,
			lblResultLocal, lblResultReligion, lblResultSex, lblNextOfKin, lblResultKin, 
			lblNextOfKin_1, lblResultKinAddress, lblNextOfPhone , lblResultKinNumber,lblDepartment,  lblResultDepartment;
	
    String outstandingList;	
	
	BufferedImage myPicture;
	// this will initialize the database
	final DatabaseManager database = new DatabaseManager();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {

					new Details();

				} catch (Exception e) {}
			}
		});
	}

	/**
	 * Create the frame.
	 */

	public Details() {
	
		// this will define create the model class
		model = new DefaultListModel<Students>();
		database.creatTable();
		database.creatDepartmentTable();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		
		Details.this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			
			@Override
			public void keyPressed(KeyEvent e) {
				super.keyPressed(e);
				

			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
				fillDetails(txtSearch.getText(), database.KEY_FIRST_NAME);
			}
		});
		txtSearch.setBounds(12, 48, 418, 25);
		contentPane.add(txtSearch);
		txtSearch.setColumns(10);

		JButton btnNewButton = new JButton("New Student");
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					@Override
					public void run() {
						try {
							
							new NewStudent();
							Details.this.setVisible(false);
							Details.this.dispose();
							
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
				
			}
		});
		btnNewButton.setBounds(873, 634, 126, 25);
		contentPane.add(btnNewButton);

		JPanel panel = new JPanel();
		panel.setBounds(477, 0, 729, 594);
		contentPane.add(panel);
		panel.setLayout(null);

		JLabel lblStudentsDetails = new JLabel("Students Details");
		lblStudentsDetails.setFont(new Font("Courier", Font.BOLD, 20));
		lblStudentsDetails.setForeground(Color.BLACK);
		lblStudentsDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblStudentsDetails.setBounds(209, 0, 261, 25);
		panel.add(lblStudentsDetails);

		JLabel lblMatric = new JLabel("Matric Number");
		lblMatric.setBounds(23, 28, 119, 15);
		panel.add(lblMatric);

		JLabel lblFirstName = new JLabel("FIrst Name");
		lblFirstName.setBounds(23, 71, 95, 15);
		panel.add(lblFirstName);

		JLabel lblLevel = new JLabel("Level");
		lblLevel.setBounds(23, 157, 70, 15);
		panel.add(lblLevel);

		JLabel lblStateOfOrigin = new JLabel("State of Origin");
		lblStateOfOrigin.setBounds(23, 230, 104, 15);
		panel.add(lblStateOfOrigin);

		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(23, 113, 95, 15);
		panel.add(lblLastName);

		JLabel lblDateOfBirth = new JLabel("Date OF Birth");
		lblDateOfBirth.setBounds(23, 294, 104, 15);
		panel.add(lblDateOfBirth);

		lblResultMatric = new JLabel("nill");
		lblResultMatric.setForeground(Color.RED);
		lblResultMatric.setBounds(23, 44, 279, 15);
		panel.add(lblResultMatric);

		lblResultFirstName = new JLabel("nill");
		lblResultFirstName.setForeground(Color.RED);
		lblResultFirstName.setBounds(23, 85, 279, 15);
		panel.add(lblResultFirstName);

		lblResultLastName = new JLabel("nill");
		lblResultLastName.setForeground(Color.RED);
		lblResultLastName.setBounds(23, 130, 279, 15);
		panel.add(lblResultLastName);

		lblResultLevel = new JLabel("nill");
		lblResultLevel.setForeground(Color.RED);
		lblResultLevel.setBounds(23, 172, 279, 15);
		panel.add(lblResultLevel);

		lblResultState = new JLabel("nill");
		lblResultState.setForeground(Color.RED);
		lblResultState.setBounds(216, 230, 307, 15);
		panel.add(lblResultState);

		lblResultDate = new JLabel("nill");
		lblResultDate.setForeground(Color.RED);
		lblResultDate.setBounds(216, 294, 307, 15);
		panel.add(lblResultDate);
	
		// uncommment this portion after
		
//		try {
//			myPicture = ImageIO.read(new File("/home/superibk/workspace/Computational/pictures/pic1.jpg"));
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		JLabel picLabel = new JLabel(new ImageIcon(myPicture));
		
		
		
		JPanel panaImage = new JPanel();
		panaImage.setBounds(541, 142, 153, 167);
		panel.add(panaImage);
	
		
		// uncomment this portion after 
//		panaImage.add(picLabel);
		
		JLabel lblLocalGovernmentArea = new JLabel("Local Government Area");
		lblLocalGovernmentArea.setBounds(23, 257, 182, 15);
		panel.add(lblLocalGovernmentArea);
		
		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(23, 199, 128, 15);
		panel.add(lblPhoneNumber);
		
		lblResultPhone = new JLabel("nill");
		lblResultPhone.setForeground(Color.RED);
		lblResultPhone.setBounds(216, 203, 307, 15);
		panel.add(lblResultPhone);
		
		lblResultLocal = new JLabel("nill");
		lblResultLocal.setForeground(Color.RED);
		lblResultLocal.setBounds(216, 257, 307, 15);
		panel.add(lblResultLocal);
		
		JLabel lblReligion = new JLabel("Religion");
		lblReligion.setBounds(23, 329, 70, 15);
		panel.add(lblReligion);
		
		lblResultReligion = new JLabel(" nill");
		lblResultReligion.setForeground(Color.RED);
		lblResultReligion.setBounds(216, 329, 307, 15);
		panel.add(lblResultReligion);
		
		JLabel lblSex = new JLabel("Sex");
		lblSex.setBounds(23, 367, 70, 15);
		panel.add(lblSex);
		
		lblResultSex = new JLabel("nill");
		lblResultSex.setForeground(Color.RED);
		lblResultSex.setBounds(216, 367, 307, 15);
		panel.add(lblResultSex);
		
	    lblNextOfKin = new JLabel("Next Of Kin:");
		lblNextOfKin.setBounds(23, 406, 95, 15);
		panel.add(lblNextOfKin);
		
		lblResultKin = new JLabel("nill");
		lblResultKin.setForeground(Color.RED);
		lblResultKin.setBounds(216, 406, 307, 15);
		panel.add(lblResultKin);
		
		lblNextOfKin_1 = new JLabel("Next Of Kin Address");
		lblNextOfKin_1.setBounds(23, 447, 160, 15);
		panel.add(lblNextOfKin_1);
		
		lblResultKinAddress = new JLabel("nill");
		lblResultKinAddress.setForeground(Color.RED);
		lblResultKinAddress.setBounds(216, 447, 307, 15);
		panel.add(lblResultKinAddress);
		
		lblNextOfPhone = new JLabel("Next Of Phone Number");
		lblNextOfPhone.setBounds(23, 518, 182, 15);
		panel.add(lblNextOfPhone);
		
		lblResultKinNumber = new JLabel("nill");
		lblResultKinNumber.setForeground(Color.RED);
		lblResultKinNumber.setBounds(216, 518, 307, 15);
		panel.add(lblResultKinNumber);
		
		lblDepartment = new JLabel("Department");
		lblDepartment.setBounds(23, 485, 160, 15);
		panel.add(lblDepartment);
		
		 lblResultDepartment = new JLabel("nill");
		lblResultDepartment.setForeground(Color.RED);
		lblResultDepartment.setBounds(216, 485, 307, 15);
		panel.add(lblResultDepartment);
		
				JButton btnCheckResult = new JButton("Result");
				btnCheckResult.setBounds(546, 49, 137, 25);
				panel.add(btnCheckResult);
				
						JButton btnBack = new JButton("Edit Profile");
						btnBack.setBounds(546, 12, 137, 25);
						panel.add(btnBack);
						
						JButton btnDelete = new JButton("Delete ");
						btnDelete.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
						  //Custom DialogBox
							int n = JOptionPane.showConfirmDialog(
									    Details.this,
									    "Are you sure you want to delete \n"
									    +listStudent.getSelectedValue().getFirstName() +" "
									    +listStudent.getSelectedValue().getLastName() +"\n"
									    +listStudent.getSelectedValue().getMatric(),
									    "Delete Student ?",
									    JOptionPane.YES_NO_OPTION);
							
							System.err.print(n);
							if (n == 0){
								database.deleteStudents((int)listStudent.getSelectedValue().getId());
								fillDetails("", database.KEY_FIRST_NAME);
								
							}
													
							
								
							}
						});
						
						btnDelete.setBounds(546, 85, 137, 25);
						panel.add(btnDelete);
						btnBack.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent arg0) {

								database.closeConnection();
								new EditStudent((int)listStudent.getSelectedValue().getId());
								Details.this.setVisible(false);
								Details.this.dispose();
							}
						});
				btnCheckResult.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						EventQueue.invokeLater(new Runnable() {
							@Override
							public void run() {
								try {
									
									database.closeConnection();
									new ProfileResult(getMatric(), getDepatment(), getLevel());
									Details.this.setVisible(false);
									Details.this.dispose();
									
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						});
						
						
					}
				});
		
		

		final JComboBox comSearchCategory = new JComboBox();
		comSearchCategory.setModel(new DefaultComboBoxModel(new String[] {"Search by Name", "Search by Matric No", "Search by Level", "Search by CGPA", "Search by State", "Search by Age" }));
		comSearchCategory.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
//				txtSearch.setText("Search by "+ comSearchCategory.getSelectedItem().toString());
			}
		});
		comSearchCategory.setBounds(12, 12, 418, 24);
		contentPane.add(comSearchCategory);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 85, 418, 552);
		contentPane.add(scrollPane);

		listStudent = new JList();
		listStudent.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				Students students = listStudent.getSelectedValue();
				fillDetailsToLables(students);
			}
		});
		scrollPane.setViewportView(listStudent);
		listStudent.setVisibleRowCount(12);

		// this will load the model into the list box
		listStudent.setModel(model);
				
						JButton btnExit = new JButton("Exit");
						btnExit.setBounds(558, 634, 128, 25);
						contentPane.add(btnExit);
						
						JButton btnResultSheet = new JButton("Courses");
						btnResultSheet.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								
								database.closeConnection();
								new AddCourses();
								Details.this.setVisible(false);
								Details.this.dispose();
								
							}
						});
						btnResultSheet.setBounds(727, 634, 119, 25);
						contentPane.add(btnResultSheet);
						
						JButton btnResultSheet_1 = new JButton("Result sheet");
						btnResultSheet_1.setBounds(1024, 634, 126, 25);
						contentPane.add(btnResultSheet_1);
						btnResultSheet_1.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								
											EventQueue.invokeLater(new Runnable() {
												@Override
												public void run() {
													try {
														
														database.closeConnection();
														new resultSheet();
														Details.this.setVisible(false);
														Details.this.dispose();
														
													} catch (Exception e) {
														e.printStackTrace();
													}
												}
											});
									}
							
						});
						btnExit.addActionListener(new ActionListener() {
							@Override
							public void actionPerformed(ActionEvent event) {
								database.closeConnection();
								System.exit(0);
							}
						});

		fillDetails("", database.KEY_FIRST_NAME);
	}

	private void fillDetails(final String query, final String type) {

		final Thread updater = new Thread() {
			@Override
			public void run() {

				model.removeAllElements();
				try {

					rs = database.searchQuery(query, type);
					while (rs.next()) {
						int id = rs.getInt(DatabaseManager.KEY_ROWID);
						String firstName = rs.getString(DatabaseManager.KEY_FIRST_NAME);
						String lastName = rs.getString(DatabaseManager.KEY_LAST_NAME);
						String middleName = rs.getString(DatabaseManager.KEY_MIDDLE_NAME);
						String matric = rs.getString(DatabaseManager.KEY_MATRIC);
						String birth = rs.getString(DatabaseManager.KEY_DATE_OF_BIRTH);
						String religion =  rs.getString(DatabaseManager.KEY_RELIGION);
						String sex =  rs.getString(DatabaseManager.KEY_SEX);
						String maritalStatus = rs.getString(DatabaseManager.KEY_MARITAL_STATUS);
						String phoneNumber =  rs.getString(DatabaseManager.KEY_PHONE);
						String department =  rs.getString(DatabaseManager.KEY_DEPARTMENT);
						String nextOfKin =  rs.getString(DatabaseManager.KEY_NEXT_OF_KIN);
						String nextOfKinNumber =  rs.getString(DatabaseManager.KEY_NEXT_OF_KIN_NUMBER);
						String homeAddress =  rs.getString(DatabaseManager.KEY_HOME_ADDRESS);
						String guiadianName =  rs.getString(DatabaseManager.KEY_NEXT_OF_KIN_ADDRESS);
						String localGov =  rs.getString(DatabaseManager.KEY_LOCAL_GOV);
						String state =  rs.getString(DatabaseManager.KEY_STATE);
						String level =  rs.getString(DatabaseManager.KEY_LEVEL);
						String nationality =  rs.getString(DatabaseManager.KEY_NATIONALITY);
					
						
					
						Students students = new Students(
								  id,
								  firstName,
								  lastName,
								  middleName,
								  matric,
								  birth,
								  religion,
								  sex,
								  maritalStatus,
								  phoneNumber,
								  department,
								  nextOfKin,
								  nextOfKinNumber,
								  homeAddress,
								  guiadianName,
								  localGov,
								  state,
								  level,
								  nationality	);
						model.addElement(students);
						
					}

				} catch (SQLException e) {
					throw new RuntimeException(e);
				}

			}
		};
		updater.start();

	}


	public void fillDetailsToLables(Students students) {
		lblResultFirstName.setText(students.getFirstName());
		lblResultMatric.setText(students.getMatric());
		lblResultDate.setText(students.getBirth());
		lblResultState.setText(students.getState());
		lblResultReligion.setText(students.getReligion());
		lblResultLevel.setText(students.getLevel());
		lblResultPhone.setText(students.getPhoneNumber());
		lblResultDepartment.setText(students.getDepartment());
		lblResultLocal.setText(students.getLocalGov());
		lblResultSex.setText(students.getSex());
		lblResultLastName.setText(students.getLastName());
		lblResultKin.setText(students.getNextOfKin());
		lblResultKinNumber.setText(students.getNextOfKinNumber());
		lblResultKinAddress.setText(students.getNextOfKinAddress());
	}
	
	private String getMatric(){
		
		return lblResultMatric.getText();
	}
	
	private String getDepatment(){
		return lblResultDepartment.getText();
	}
	
	private String getLevel(){
		return lblResultLevel.getText();
	}
	
	
}
