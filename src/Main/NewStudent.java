package Main;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.imageio.ImageIO;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class NewStudent extends JFrame {

	private JPanel contentPane;
	private JComboBox txtLevel;
	private JTextField txtPhoneNumber;
	private JTextField txtLocalGov;
	private JComboBox txtDepartment;
	private JTextField txtMatricNo;
	private JTextField txtLastName;
	private JTextField txtMiddleName;
	private JTextField txtFirstName;
	private JTextField txtDateOfBirth;
	private JTextArea txtAddress;
	private JTextField txtNextOfKin;
	private JTextField txtNitionality;
	JComboBox comReligion, comStatus, comState;
	JTextArea txtNextOfKinAddress;
	ResultSet rs;
	DatabaseManager database = new DatabaseManager();
	private JTextField txtNextOfKinNumber;
	BufferedImage myPicture;
	JLabel picLabel;
	JPanel panel;

	/**
	 * Create the frame.
	 */
	public NewStudent() {
		setTitle("Insert New Student");
		this.setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 949, 754);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblMatricNumber = new JLabel("MATRIC NUMBER:");
		lblMatricNumber.setBounds(8, 164, 123, 15);
		contentPane.add(lblMatricNumber);
		
		JLabel lblSex = new JLabel("SEX:");
		lblSex.setBounds(534, 366, 70, 18);
		contentPane.add(lblSex);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(202, 215, 1, 15);
		contentPane.add(textArea);
		
		JLabel lblDepartment = new JLabel("FACULTY");
		lblDepartment.setBounds(8, 215, 125, 15);
		contentPane.add(lblDepartment);
		
		JLabel lblLevel = new JLabel("LEVEL:");
		lblLevel.setBounds(0, 148, 70, -21);
		contentPane.add(lblLevel);
		
		JLabel lblPhoneNumber = new JLabel("PHONE NUMBER:");
		lblPhoneNumber.setBounds(8, 365, 134, 15);
		contentPane.add(lblPhoneNumber);
		
		JLabel lblStateOfOrigin = new JLabel("STATE OF ORIGIN:");
		lblStateOfOrigin.setBounds(0, 178, 149, -21);
		contentPane.add(lblStateOfOrigin);
		
		JLabel lblLocalGovtArea = new JLabel("LOCAL GOVT. AREA:");
		lblLocalGovtArea.setBounds(10, 408, 139, 15);
		contentPane.add(lblLocalGovtArea);
		
		JLabel lblDateOfBirth = new JLabel("DATE  OF BIRTH:");
		lblDateOfBirth.setBounds(530, 325, 111, 15);
		contentPane.add(lblDateOfBirth);
		
		JLabel lblFirstName = new JLabel("FIRST NAME:");
		lblFirstName.setBounds(8, 28, 123, 15);
		contentPane.add(lblFirstName);
		
		JLabel lblMiddleName = new JLabel("MIDDLE NAME:");
		lblMiddleName.setBounds(8, 73, 111, 19);
		contentPane.add(lblMiddleName);
		
		txtLevel = new JComboBox();
		txtLevel.setModel(new DefaultComboBoxModel(new String[] {"100", "200", "300", "400", "500", "600", "700"}));
		txtLevel.setBounds(158, 317, 254, 25);
		contentPane.add(txtLevel);
		
		
		JLabel lblLastName = new JLabel("LAST NAME:");
		lblLastName.setBounds(8, 111, 91, 25);
		contentPane.add(lblLastName);
		
		JLabel lblReligion = new JLabel("RELIGION:");
		lblReligion.setBounds(530, 267, 70, 18);
		contentPane.add(lblReligion);
		
		JButton btnComputeStudentsResult = new JButton("Add New");
		btnComputeStudentsResult.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				    int error = 0;
                                /*
                                for (Component C: this.getComponents()){
                                    if (C instanceof JTextField || C instanceof JTextArea){
                                    }
                                } */
                                if(txtFirstName.getText() == "" )
                                    error += 1;
                                if(txtFirstName.getText() == "" )
                                    error += 1;
                                if(txtLastName.getText() == "" )
                                    error += 1;
                                if(txtMiddleName.getText() == "" )
                                    error += 1;
                                if(txtMatricNo.getText() == "" )
                                    error += 1;
                                if(txtDateOfBirth.getText() == "" )
                                    error += 1;
                                if(txtPhoneNumber.getText() == "" )
                                    error += 1;
                                if(txtNextOfKin.getText() == "" )
                                    error += 1;
                                if(txtNextOfKinNumber.getText() == "" )
                                    error += 1;
                                if(txtNextOfKinAddress.getText() == "" )
                                    error += 1;
                                if(txtAddress.getText() == "" )
                                    error += 1;
                                if(txtLocalGov.getText() == "" )
                                    error += 1;
                                if(txtNitionality.getText() == "" )
                                    error += 1;
                                if(error !=0 ){
                                    JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else{
					
					insertStudent();
					   JOptionPane.showMessageDialog((Component)null, "Registration Completed,\n New Students Added","Successful", JOptionPane.INFORMATION_MESSAGE);
		             //   database.closeConnection();
						NewStudent.this.setVisible(false);
						new Details();
					
				}
				
			}
		});
		btnComputeStudentsResult.setBounds(330, 678, 134, 25);
		contentPane.add(btnComputeStudentsResult);
		
		JButton btnBack = new JButton("Cancel");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				new Details();
				NewStudent.this.setVisible(false);
				NewStudent.this.dispose();
			}
		});
		btnBack.setBounds(490, 678, 139, 25);
		contentPane.add(btnBack);
		
		JLabel lblLevel_1 = new JLabel("LEVEL:");
		lblLevel_1.setBounds(12, 322, 70, 15);
		contentPane.add(lblLevel_1);
		
		JLabel lblMaritalStatus = new JLabel("MARITAL STATUS:");
		lblMaritalStatus.setBounds(534, 218, 139, 15);
		contentPane.add(lblMaritalStatus);
		
		JButton btnUpload = new JButton("Upload");
		btnUpload.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				//Create a file chooser
				final JFileChooser fc = new JFileChooser();
			    fc.addChoosableFileFilter(new ImageFilter());
				int returnVal = fc.showDialog(NewStudent.this, "Upload Picture");
				
				
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File profilePicture = fc.getSelectedFile();
		            
		            //This is where a real application would open the file.
		            
		            String path = profilePicture.getAbsolutePath();
//		            JOptionPane.showMessageDialog((Component)null, profilePicture.getAbsolutePath(),"Successful", JOptionPane.INFORMATION_MESSAGE);
		            
		            try {
		    			myPicture = ImageIO.read(new File(profilePicture.getAbsolutePath()));
		    		} catch (IOException e1) {
		    			e1.printStackTrace();
		    		}
		            
		    		picLabel = new JLabel(new ImageIcon(myPicture));
		    		panel.add(picLabel);
		    		panel = new JPanel();
		    		panel.setBounds(528, 62, 139, 118);
		    		contentPane.add(panel);
		            
		               
		            
		        } 
			}
		});
		btnUpload.setBounds(695, 26, 117, 25);
		contentPane.add(btnUpload);
		
		txtPhoneNumber = new JTextField();
		txtPhoneNumber.setColumns(10);
		txtPhoneNumber.setBounds(158, 360, 254, 25);
		contentPane.add(txtPhoneNumber);
		
		txtLocalGov = new JTextField();
		txtLocalGov.setBounds(158, 408, 254, 25);
		contentPane.add(txtLocalGov);
		txtLocalGov.setColumns(10);
		
		JRadioButton rdbtnMale = new JRadioButton("Male");
		rdbtnMale.setBounds(663, 391, 149, 23);
		contentPane.add(rdbtnMale);
		
		JRadioButton rdbtnFemaile = new JRadioButton("Female");
		rdbtnFemaile.setBounds(664, 364, 149, 23);
		contentPane.add(rdbtnFemaile);
		
	    comStatus = new JComboBox();
		comStatus.setModel(new DefaultComboBoxModel(new String[] {"Single", "Married"}));
		comStatus.setBounds(673, 213, 242, 24);
		contentPane.add(comStatus);
		
	   comReligion = new JComboBox();
		comReligion.setModel(new DefaultComboBoxModel(new String[] {"Christianity", "Islam", "Others"}));
		comReligion.setBounds(673, 264, 242, 24);
		contentPane.add(comReligion);
		
		JLabel label = new JLabel("DEPARTMENT:");
		label.setBounds(10, 266, 125, 15);
		contentPane.add(label);
		
		txtDepartment = new JComboBox();
		txtDepartment.setBounds(158, 264, 254, 25);
		contentPane.add(txtDepartment);
		
		txtMatricNo = new JTextField();
		txtMatricNo.setColumns(10);
		txtMatricNo.setBounds(158, 162, 254, 25);
		contentPane.add(txtMatricNo);
		
		txtLastName = new JTextField();
		txtLastName.setColumns(10);
		txtLastName.setBounds(158, 114, 254, 25);
		contentPane.add(txtLastName);
		
		txtMiddleName = new JTextField();
		txtMiddleName.setColumns(10);
		txtMiddleName.setBounds(158, 70, 254, 25);
		contentPane.add(txtMiddleName);
		
		txtFirstName = new JTextField();
		txtFirstName.setColumns(10);
		txtFirstName.setBounds(158, 26, 254, 25);
		contentPane.add(txtFirstName);
		
		JComboBox comFaculty = new JComboBox();
		comFaculty.setModel(new DefaultComboBoxModel(new String[] {"Sciences", "Management Sciences", "Art", "Agricultural Sciences", "Law", "Education", "Social Sciences", "College Of Medicine", "Engineering"}));
		comFaculty.setBounds(158, 210, 254, 24);
		contentPane.add(comFaculty);
		
		txtDateOfBirth = new JTextField();
		txtDateOfBirth.setColumns(10);
		txtDateOfBirth.setBounds(673, 320, 242, 25);
		contentPane.add(txtDateOfBirth);
		
		JLabel lblStateOfOrigin_1 = new JLabel("STATE OF ORIGIN:");
		lblStateOfOrigin_1.setBounds(8, 461, 139, 15);
		contentPane.add(lblStateOfOrigin_1);
		
	    comState = new JComboBox();
	    comState.setModel(new DefaultComboBoxModel(new String[] {"Anambra", "Enugu", "Akwa Ibom", "Adamawa", "Abia", "Bauchi", "Bayelsa", "Benue", "Borno", "Cross River", "Delta", "Ebonyi", "Edo", "Ekiti", "Gombe", "Imo", "Jigawa", "Kaduna", "Kano", "Katsina", "Kebbi", "Kogi", "Kwara", "Lagos", "Nasarawa", "Niger", "Ogun", "Ondo", "Osun", "Oyo", "Plateau", "Rivers", "Sokoto", "Taraba", "Yobe", "Zamfara"}));
		comState.setBounds(158, 456, 254, 24);
		contentPane.add(comState);
		
		txtAddress = new JTextArea();
		txtAddress.setBounds(673, 433, 242, 67);
		contentPane.add(txtAddress);
		txtAddress.setColumns(10);
		
		JLabel lblAddress = new JLabel("ADDRESS:");
		lblAddress.setBounds(534, 433, 70, 15);
		contentPane.add(lblAddress);
		
		JLabel lblNextOfKin = new JLabel("NEXT OF KIN");
		lblNextOfKin.setBounds(100, 499, 119, 15);
		contentPane.add(lblNextOfKin);
		
		txtNextOfKin = new JTextField();
		txtNextOfKin.setColumns(10);
		txtNextOfKin.setBounds(158, 526, 254, 25);
		contentPane.add(txtNextOfKin);
		
		JLabel lblNewLabel = new JLabel("NAME:");
		lblNewLabel.setBounds(8, 515, 134, 47);
		contentPane.add(lblNewLabel);
		
		txtNextOfKinAddress = new JTextArea();
		txtNextOfKinAddress.setColumns(10);
		txtNextOfKinAddress.setBounds(158, 599, 254, 67);
		contentPane.add(txtNextOfKinAddress);
		
		JLabel label_1 = new JLabel("ADDRESS:");
		label_1.setBounds(8, 599, 70, 15);
		contentPane.add(label_1);
		
		JLabel lblNationality = new JLabel("NATIONALITY");
		lblNationality.setBounds(534, 520, 107, 15);
		contentPane.add(lblNationality);
		
		txtNitionality = new JTextField();
		txtNitionality.setColumns(10);
		txtNitionality.setBounds(673, 518, 242, 25);
		contentPane.add(txtNitionality);
		
		txtNextOfKinNumber = new JTextField();
		txtNextOfKinNumber.setColumns(10);
		txtNextOfKinNumber.setBounds(158, 562, 254, 25);
		contentPane.add(txtNextOfKinNumber);
		
		JLabel lblPhoneNumber_1 = new JLabel("Phone Number:");
		lblPhoneNumber_1.setBounds(8, 562, 111, 15);
		contentPane.add(lblPhoneNumber_1);
		
       fillDepartmentList();
		
	
	}
	
	public void insertStudent(){
        	// create new student profile
	     database.insertDetails(
	    		 txtFirstName.getText(),
	    		 txtLastName.getText(),
	    		 txtMiddleName.getText(),
	    		 txtMatricNo.getText(), 
	    		 txtDateOfBirth.getText(),
	    		 comReligion.getSelectedItem().toString(),
	    		 "male",
	    		 comStatus.getSelectedItem().toString(),
	    		 txtPhoneNumber.getText(),
	    		 txtDepartment.getSelectedItem().toString(),
	    		 txtNextOfKin.getText(), 
	    		 txtNextOfKinNumber.getText(), 
	    		 txtNextOfKinAddress.getText(),
	    		 txtAddress.getText(),
	    		 "Gaudian Name Here",
	    		 txtLocalGov.getText(), 
	    		 comState.getSelectedItem().toString(), 
	    		 txtLevel.getSelectedItem().toString(),
	    		 txtNitionality.getText()
                     );	   
	     
	     database.creatTableCourse(txtMatricNo.getText().trim());
	     database.creatTableOutstanding(txtMatricNo.getText().trim());
        }
	
	 public void fillDepartmentList(){
		 
			
			txtDepartment.removeAllItems();
			try {
				rs = database.returnDepartmentList();
				while (rs.next()) {
					int idReturnDepartment = rs.getInt(DatabaseManager.KEY_ROWID);
					String departmentReturned = rs.getString(DatabaseManager.KEY_DEPARTMENT);
					DepartmentObject object1 =  new DepartmentObject(idReturnDepartment, departmentReturned);
					txtDepartment.addItem(object1);
		    	}

			} catch (SQLException e) {}
			
    }
}
