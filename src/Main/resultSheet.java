package Main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JCheckBox;

public class resultSheet extends JFrame {

	private JPanel contentPane;
	private JLabel lblResultLlocation;
	private ResultSet rs;
	private DatabaseManager database;
	JComboBox comDepartmet, comLevel;
	private JTextField txtFileName;
	private JTextField txtSheetName;
	private JTextField txtTitle;
	private String importLocation;
	int rowInc = 2;
	JComboBox comboBoxSemester;

	/**
	 * Create the frame.
	 */
	public resultSheet() {
		this.setVisible(true);
		setTitle("Result Sheet");
		// initailize the database
		database = new DatabaseManager();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setBounds(100, 100, 981, 700);

		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNewButton_1 = new JButton("Exit");
		btnNewButton_1.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnNewButton_1.setBounds(729, 605, 179, 25);
		contentPane.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Back");
		btnNewButton_2.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				new Details();
				resultSheet.this.setVisible(false);
				resultSheet.this.dispose();
			}
		});
		btnNewButton_2.setBounds(729, 568, 179, 25);
		contentPane.add(btnNewButton_2);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(33, 12, 630, 246);
		contentPane.add(panel_1);
		panel_1.setLayout(null);

		txtFileName = new JTextField();
		txtFileName.setBounds(202, 89, 407, 25);
		panel_1.add(txtFileName);
		txtFileName.setColumns(10);

		JLabel lblSpreadSheetFile = new JLabel("Spread Sheet File Name");
		lblSpreadSheetFile.setBounds(12, 94, 180, 15);
		panel_1.add(lblSpreadSheetFile);

		JLabel lblLocation = new JLabel("Location:");
		lblLocation.setBounds(12, 219, 85, 15);
		panel_1.add(lblLocation);

		JButton btnBrowse = new JButton("Browse");

		btnBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				// Create a file chooser
				final JFileChooser fc = new JFileChooser();
				// fc.addChoosableFileFilter(new ImageFilter());
				fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				int returnVal = fc.showDialog(resultSheet.this,
						"Selected Location");

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File dirPath = fc.getSelectedFile();
					String path = dirPath.getAbsolutePath();
					lblResultLlocation.setText(path);
				}
			}
		});

		btnBrowse.setBounds(292, 192, 227, 25);
		panel_1.add(btnBrowse);

		JLabel lblSheetName = new JLabel("Sheet Name");
		lblSheetName.setBounds(12, 55, 168, 15);
		panel_1.add(lblSheetName);

		txtSheetName = new JTextField();
		txtSheetName.setColumns(10);
		txtSheetName.setBounds(202, 52, 407, 25);
		panel_1.add(txtSheetName);

		txtTitle = new JTextField();
		txtTitle.setBounds(202, 126, 407, 27);
		panel_1.add(txtTitle);
		txtTitle.setColumns(10);

		JLabel lblThisWillBe = new JLabel(
				"THIS WILL BE SHOWN AS THE HEADER IN THE SPREADSHEET");
		lblThisWillBe.setBounds(12, 165, 417, 15);
		panel_1.add(lblThisWillBe);
		lblThisWillBe.setFont(new Font("Courier", Font.ITALIC, 12));
		lblThisWillBe.setForeground(Color.RED);

		lblResultLlocation = new JLabel("");
		lblResultLlocation.setBounds(192, 219, 417, 15);
		panel_1.add(lblResultLlocation);

		JLabel lblTitleForSummary = new JLabel("Title For Summary");
		lblTitleForSummary.setBounds(12, 132, 168, 15);
		panel_1.add(lblTitleForSummary);

		JLabel lblResultSheet = new JLabel("Export Result Sheet");
		lblResultSheet.setBounds(223, 12, 206, 15);
		panel_1.add(lblResultSheet);
		lblResultSheet.setFont(new Font("Dialog", Font.BOLD, 17));

		JPanel panel = new JPanel();
		panel.setBounds(675, 12, 280, 246);
		contentPane.add(panel);
		panel.setLayout(null);

		comDepartmet = new JComboBox();
		comDepartmet.setBounds(0, 12, 273, 24);
		panel.add(comDepartmet);

		comLevel = new JComboBox();
		comLevel.setBounds(0, 48, 103, 24);
		panel.add(comLevel);
		comLevel.setModel(new DefaultComboBoxModel(new String[] { "100", "200",
				"300", "400", "500", "600", "700" }));

		comboBoxSemester = new JComboBox();
		comboBoxSemester.setBounds(123, 48, 150, 24);
		panel.add(comboBoxSemester);
		comboBoxSemester.setModel(new DefaultComboBoxModel(new String[] {
				"1st", "2nd" }));

		JButton btnNewButton = new JButton("Export Result");
		btnNewButton.setBounds(0, 118, 273, 25);
		panel.add(btnNewButton);

		JButton btnExportSummary = new JButton("Export Summary");
		btnExportSummary.setBounds(0, 155, 273, 25);
		panel.add(btnExportSummary);

		// JButton button_1 = new JButton("Export As SpreadSheet");
		// button_1.addActionListener(new ActionListener() {
		// public void actionPerformed(ActionEvent arg0) {
		//
		// }
		// });
		// button_1.setBounds(0, 215, 273, 25);
		// panel.add(button_1);

		JButton btnNewButton_3 = new JButton("Export NR1");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportNR();
			}
		});
		btnNewButton_3.setBounds(0, 84, 273, 25);
		panel.add(btnNewButton_3);

		JButton btnNewButton_4 = new JButton("Export Detailed Result");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				exportResult();
			}
		});
		btnNewButton_4.setBounds(0, 191, 273, 25);
		panel.add(btnNewButton_4);
		btnExportSummary.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				exportAsSummary();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				exportAsSpreadSheet();

			}
		});

		// load the dapartments list
		fillDepartmentList();
	}

	// this will export result as sheetsheet format

	public void exportAsSpreadSheet() {
		int increment1 = 4;
		int inc = 2;
		int inc1 = 2;
		String courseList, courseUnit;
		String returnMatric;
		ArrayList<String> matricDetails1 = new ArrayList<String>();
		ArrayList<String> courseArrayList = new ArrayList<String>();

		String fileName = txtFileName.getText().trim();
		String sheetName = txtSheetName.getText().trim();

		// this is the selected value of the department
		String selectedDepartment = comDepartmet.getSelectedItem().toString()
				.trim();
		String level = comLevel.getSelectedItem().toString().trim();

		// create the spreadsheet file
		String filename = lblResultLlocation.getText() + "/" + fileName
				+ ".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName);
		// this line of code is creating a spreadsheet style in vertical format
		HSSFCellStyle myStyle = workbook.createCellStyle();
		myStyle.setRotation((short) 90);

		sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
				1, // last row (0-based)
				0, // first column (0-based)
				0 // last column (0-based)
		));

		// load all courses in this department
		rs = database.searchQueryDeptCourses(selectedDepartment);
		// this will create the first row
		HSSFRow rowhead = sheet.createRow((short) 0);
		HSSFRow rowheadUnit = sheet.createRow((short) 1);
		rowhead.createCell(0).setCellValue("");
		rowhead.createCell(1).setCellValue("");
		rowheadUnit.createCell(0).setCellValue("");
		rowheadUnit.createCell(1).setCellValue("NUMBER");
		try {
			while (rs.next()) {
				courseList = rs.getString(DatabaseManager.KEY_COURSES);
				courseUnit = rs.getString(DatabaseManager.KEY_UNIT);
				HSSFCell courseHeader = rowhead.createCell((short) inc);
				HSSFCell unitHeader = rowheadUnit.createCell((short) inc);
				courseHeader.setCellValue(courseList);
				unitHeader.setCellValue(courseUnit);
				courseHeader.setCellStyle(myStyle);
				// this will add the course into an arraylist to be use for
				// comparism of all the course available
				courseArrayList.add(courseList);
				inc++;
			}

			rs = database.searchQueryStudentsExport(selectedDepartment, level);

			// counter initailization
			int counter = 1;
			while (rs.next()) {

				HSSFRow row = sheet.createRow((short) inc1);
				row.createCell(0).setCellValue(Integer.toString(counter));
				counter++;
				inc1++;
				returnMatric = rs.getString(DatabaseManager.KEY_MATRIC);
				matricDetails1.add(returnMatric);
				row.createCell(1).setCellValue(returnMatric);

			}

			for (String s : matricDetails1) {

				int colInc = 2;

				ArrayList<String> detailsCourse = new ArrayList<String>();
				detailsCourse = sameCourse(s);
				for (String course : detailsCourse) {
					HSSFRow row = sheet.getRow(rowInc);
					row.createCell(colInc).setCellValue(course);
					colInc++;
				}
				rowInc++;

			}

			FileOutputStream fileOut = new FileOutputStream(filename);
			workbook.write(fileOut);
			fileOut.close();
			JOptionPane.showMessageDialog(null, "FIle Exported Successfully","WARNING!!!", JOptionPane.INFORMATION_MESSAGE);

		} catch (Exception e) {
		}

	}

	public void fillDepartmentList() {

		comDepartmet.removeAllItems();
		try {
			rs = database.returnDepartmentList();
			while (rs.next()) {
				int idReturnDepartment = rs.getInt(DatabaseManager.KEY_ROWID);
				String departmentReturned = rs.getString(DatabaseManager.KEY_DEPARTMENT);
				DepartmentObject object1 = new DepartmentObject(idReturnDepartment, departmentReturned);
				comDepartmet.addItem(object1);
			}

		} catch (SQLException e) {
		}

	}

	public int onReturnPoint(String grade) {
		int returnValue = 0;
		if (grade.trim().equalsIgnoreCase("A")) {
			returnValue = 5;
		} else if (grade.trim().equalsIgnoreCase("B")) {
			returnValue = 4;
		} else if (grade.trim().equalsIgnoreCase("C")) {
			returnValue = 3;
		} else if (grade.trim().equalsIgnoreCase("D")) {
			returnValue = 2;
		} else if (grade.trim().equalsIgnoreCase("E")) {
			returnValue = 1;
		} else if (grade.trim().equalsIgnoreCase("F")) {
			returnValue = 0;
		}

		return returnValue;
	}

	public void exportAsSummary() {

		HSSFRow row = null;

		ArrayList<String> matricDetails = new ArrayList<String>();
		int increment = 4;
		int incrementArray = 4;

		String returnMatric, returnFirstName, returnLastName, returnMiddleName;

		String fileName2 = txtFileName.getText().trim();
		String sheetName2 = txtSheetName.getText().trim();

		// this is the selected value of the department
		String selectedDepartment = comDepartmet.getSelectedItem().toString()
				.trim();
		String level = comLevel.getSelectedItem().toString().trim();

		// this will be the header for the result summary section
		String headerValue = txtTitle.getText().trim();

		// create the spreadsheet file
		String filename2 = lblResultLlocation.getText() + "/" + fileName2
				+ ".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName2);

		HSSFCellStyle myStyle = workbook.createCellStyle();
		myStyle.setAlignment(CellStyle.ALIGN_CENTER);

		// THIS SECTION IS USED TO SET THE FONT OF THE SPREAADSHEET

		// Create a new font and alter it.
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 30);
		font.setFontName("Courier New");
		font.setBoldweight((short) 20);

		// Fonts are set into a style so create a new one to use.
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);

		// this will create the first row
		HSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue(headerValue);
		rowhead.setHeightInPoints(35);
		rowhead.setRowStyle(style);

		sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
				0, // last row (0-based)
				0, // first column (0-based)
				5 // last column (0-based)
		));

		HSSFRow rowheadUnit = sheet.createRow((short) 1);
		rowheadUnit.createCell(0).setCellValue(
				"SUMMARY OF RESULTS FOR " + selectedDepartment.toUpperCase()
						+ " " + level + " LEVEL");
		rowheadUnit.setHeightInPoints(15);
		rowheadUnit.setRowStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				1, // last row (0-based)
				0, // first column (0-based)
				5 // last column (0-based)
		));

		HSSFRow rowSmallHeader = sheet.createRow((short) 2);
		HSSFCell small1 = rowSmallHeader.createCell(0);
		small1.setCellStyle(myStyle);
		small1.setCellValue("S/NO");

		HSSFCell small2 = rowSmallHeader.createCell(1);
		small2.setCellStyle(myStyle);
		small2.setCellValue("MAT. NO");

		HSSFCell small3 = rowSmallHeader.createCell(2);
		small3.setCellStyle(myStyle);
		small3.setCellValue("NAME");

		rowSmallHeader.createCell(3).setCellValue("");
		rowSmallHeader.createCell(4).setCellValue("");
		rowSmallHeader.createCell(5).setCellValue("");

		HSSFCell small6 = rowSmallHeader.createCell(6);
		small6.setCellStyle(myStyle);
		small6.setCellValue("REMARKS");

		HSSFRow rowSmallHeader2 = sheet.createRow((short) 3);
		rowSmallHeader2.createCell(0).setCellValue("");
		rowSmallHeader2.createCell(1).setCellValue("");
		rowSmallHeader2.createCell(2).setCellValue("");
		rowSmallHeader2.createCell(3).setCellValue("TCP");
		rowSmallHeader2.createCell(4).setCellValue("CGPA");
		rowSmallHeader2.createCell(5).setCellValue("PASS/REPEAT");
		rowSmallHeader2.createCell(6).setCellValue("OUTSTANDING");

		try {

			rs = database.searchQueryStudentsExport(selectedDepartment, level);
			// counter initailization
			int counter = 1;
			while (rs.next()) {

				row = sheet.createRow((short) increment);
				row.createCell(0).setCellValue(Integer.toString(counter));
				returnMatric = rs.getString(DatabaseManager.KEY_MATRIC);

				// this will add the matric number to an arraylist to be used
				// for query in another function
				matricDetails.add(returnMatric);
				row.createCell(1).setCellValue(returnMatric);
				returnFirstName = rs.getString(DatabaseManager.KEY_FIRST_NAME);
				returnLastName = rs.getString(DatabaseManager.KEY_LAST_NAME);
				returnMiddleName = rs.getString(DatabaseManager.KEY_MIDDLE_NAME);
				row.createCell(2).setCellValue(returnLastName.toUpperCase()
								+ ", "
								+ Character.toUpperCase(returnFirstName
										.charAt(0))
								+ returnFirstName.substring(1).toLowerCase()
								+ " "
								+ Character.toUpperCase(returnMiddleName
										.charAt(0))
								+ returnMiddleName.substring(1).toLowerCase());
				increment++;
				counter++;
			}

			// THIS SECTION WILL GET THE STUDENTS DETIALS ONE AFTER THE OTHER

			for (String s : matricDetails) {
				SummaryObject details = returnStudentDetailsForNoResult(s.trim());
				row = sheet.getRow(incrementArray);
				row.createCell(3).setCellValue(details.getTotalCredit());
				row.createCell(4).setCellValue(details.getCgpa());
				row.createCell(5).setCellValue(details.getPassFail());
				incrementArray++;
			}

			// initialize increment array back to value of 4
			incrementArray = 4;

			for (String s : matricDetails) {
				row = sheet.getRow(incrementArray);
				OutstandingObject object = returnOutstanding(s.trim());
				row.createCell(6).setCellValue(object.getOutstanding());
				incrementArray++;
			}

			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);

			FileOutputStream fileOut2 = new FileOutputStream(filename2);
			workbook.write(fileOut2);
			fileOut2.close();
			JOptionPane.showMessageDialog(null, "FIle Exported Successfully",
					"Action Successful", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		}
	}

	public ArrayList<String> sameCourse(String matric) {
		ArrayList<String> returnCourseList = new ArrayList<String>();
		rs = database.searchQueryCourse(matric.trim());
		try {
			while (rs.next()) {
				String scoreReturned = rs.getString(DatabaseManager.KEY_SCORE);
				String gradeReturned = rs.getString(DatabaseManager.KEY_GRADE);
				returnCourseList.add(scoreReturned + " " + gradeReturned);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return returnCourseList;
	}

	// this section deals with no result for both second and first semester

	public void exportNR() {

		HSSFRow row = null;

		ArrayList<String> matricDetails = new ArrayList<String>();
		int increment = 4;
		int incrementArray = 4;
		String returnMatric, returnFirstName, returnLastName, returnMiddleName;

		String fileName2 = txtFileName.getText().trim();

		// this is the selected value of the department
		String selectedDepartment = comDepartmet.getSelectedItem().toString().trim();
		String level = comLevel.getSelectedItem().toString().trim();

		// this will be the header for the result summary section
		String headerValue = txtTitle.getText().trim();

		// create the spreadsheet file
		String filename2 = lblResultLlocation.getText() + "/" + fileName2+ ".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet("NR");

		HSSFCellStyle myStyle = workbook.createCellStyle();
		myStyle.setAlignment(CellStyle.ALIGN_CENTER);

		// THIS SECTION IS USED TO SET THE FONT OF THE SPREAADSHEET

		// Create a new font and alter it.
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 30);
		font.setFontName("Courier New");
		font.setBoldweight((short) 20);

		// Fonts are set into a style so create a new one to use.
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);

		// this will create the first row
		HSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue(headerValue);
		rowhead.setHeightInPoints(35);
		rowhead.setRowStyle(style);

		sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
				0, // last row (0-based)
				0, // first column (0-based)
				5 // last column (0-based)
		));

		HSSFRow rowheadUnit = sheet.createRow((short) 1);
		rowheadUnit.createCell(0).setCellValue("SUMMARY OF RESULTS FOR NOT-REGISTER(NR1) "+ selectedDepartment.toUpperCase() + " " + level
						+ " LEVEL");
		rowheadUnit.setHeightInPoints(15);
		rowheadUnit.setRowStyle(style);
		sheet.addMergedRegion(new CellRangeAddress(1, // first row (0-based)
				1, // last row (0-based)
				0, // first column (0-based)
				5 // last column (0-based)
		));

		HSSFRow rowSmallHeader = sheet.createRow((short) 2);
		HSSFCell small1 = rowSmallHeader.createCell(0);
		small1.setCellStyle(myStyle);
		small1.setCellValue("S/NO");

		HSSFCell small2 = rowSmallHeader.createCell(1);
		small2.setCellStyle(myStyle);
		small2.setCellValue("MAT. NO");

		HSSFCell small3 = rowSmallHeader.createCell(2);
		small3.setCellStyle(myStyle);
		small3.setCellValue("NAME");

		rowSmallHeader.createCell(3).setCellValue("");
		rowSmallHeader.createCell(4).setCellValue("");
		
		HSSFCell small5 = rowSmallHeader.createCell(5);
		small5.setCellStyle(myStyle);
		small5.setCellValue("REMARKS");

		HSSFRow rowSmallHeader2 = sheet.createRow((short) 3);
		rowSmallHeader2.createCell(0).setCellValue("");
		rowSmallHeader2.createCell(1).setCellValue("");
		rowSmallHeader2.createCell(2).setCellValue("");
		rowSmallHeader2.createCell(3).setCellValue("TCP");
		rowSmallHeader2.createCell(4).setCellValue("CGPA");
		rowSmallHeader2.createCell(5).setCellValue("OUTSTANDING");

		try {

			rs = database.searchQueryStudentsExport(selectedDepartment, level);
			// counter initailization
			int counter = 1;
			while (rs.next()) {

				row = sheet.createRow((short) increment);
				row.createCell(0).setCellValue(Integer.toString(counter));
				returnMatric = rs.getString(DatabaseManager.KEY_MATRIC);

				// this will add the matric number to an arraylist to be used
				// for query in another function
				matricDetails.add(returnMatric);
				row.createCell(1).setCellValue(returnMatric);
				returnFirstName = rs.getString(DatabaseManager.KEY_FIRST_NAME);
				returnLastName = rs.getString(DatabaseManager.KEY_LAST_NAME);
				returnMiddleName = rs.getString(DatabaseManager.KEY_MIDDLE_NAME);
				row.createCell(2).setCellValue(returnLastName.toUpperCase()
								+ ", "
								+ Character.toUpperCase(returnFirstName
										.charAt(0))
								+ returnFirstName.substring(1).toLowerCase()
								+ " "
								+ Character.toUpperCase(returnMiddleName
										.charAt(0))
								+ returnMiddleName.substring(1).toLowerCase());
				increment++;
				counter++;
			}
			
			for (String s : matricDetails) {
				row = sheet.getRow(incrementArray);
				OutstandingObject object = returnOutstanding(s.trim());
				row.createCell(5).setCellValue(object.getOutstanding());
				incrementArray++;
			}

			// THIS SECTION WILL GET THE STUDENTS DETIALS ONE AFTER THE OTHER
			// initialize increment array back to value of 4
			incrementArray = 4;
			for (String s : matricDetails) {
				SummaryObject details = returnStudentDetailsForNoResult(s.trim());
				row = sheet.getRow(incrementArray);
				row.createCell(3).setCellValue(details.getTotalCredit());
				row.createCell(4).setCellValue(details.getCgpa());
				boolean resultNo = details.getNoResult();
				
				if (!resultNo) {
					sheet.removeRow(row);
//					incrementArray = incrementArray - 1;	
				}
				incrementArray++;
			}
			
			

			

			sheet.autoSizeColumn(2);
			sheet.autoSizeColumn(5);
			sheet.autoSizeColumn(6);

			FileOutputStream fileOut2 = new FileOutputStream(filename2);
			workbook.write(fileOut2);
			fileOut2.close();
			JOptionPane.showMessageDialog(null, "FIle Exported Successfully","Action Successful", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		}

	}

	public int getLevelIngeger() {
		String strLevel = comLevel.getSelectedItem().toString();
		return Integer.parseInt(strLevel.substring(0, 1));
	}

	public boolean getNoresult(String courses) {
		String studentLevel = Integer.toString(getLevelIngeger());
		String levelFromCourse = courses.substring(3, 4);
		if (studentLevel.equalsIgnoreCase(levelFromCourse))
			return true;
		else
			return false;

	}

	public SummaryObject returnStudentDetailsForNoResult(String matricno) {
		int totalScore = 0;
		int totalUnit = 0;
		int multiply = 1;
		int totalScoreCurrent = 0;
		int totalScorePrevious = 0;
		int totalUnitCurrent = 0;
		int totalUnitPrevious = 0;
		int multiplyPrevious = 1;
		int multiplyCurrent = 1;
		int tup = 0;
		int tupCurrent = 0;
		int tupPrevious = 0;
		boolean noResult = true;

		String passFail = "";
		SummaryObject studentResult = null;
	

		try {
			rs = database.searchQueryCourse(matricno.trim());
			while (rs.next()) {
				String coursesReturned = rs.getString(DatabaseManager.KEY_COURSES);
				String unitReturned = rs.getString(DatabaseManager.KEY_UNIT);
				String gradeReturned = rs.getString(DatabaseManager.KEY_GRADE);
				boolean present = getNoresult(coursesReturned);
				if (present) {
					noResult = false;
					multiplyCurrent = onReturnPoint(gradeReturned.trim())* Integer.parseInt(unitReturned);
					totalScoreCurrent = totalScoreCurrent + multiplyCurrent;
					totalUnitCurrent = totalUnitCurrent+ Integer.parseInt(unitReturned);
				 }
				else {
					
				    multiplyPrevious = onReturnPoint(gradeReturned.trim())* Integer.parseInt(unitReturned);
					totalScorePrevious = totalScorePrevious + multiplyPrevious;
					totalUnitPrevious = totalUnitPrevious+ Integer.parseInt(unitReturned);
				   }
				if (gradeReturned.trim().equalsIgnoreCase("F")) {
					passFail = passFail + coursesReturned + ",";
				}

				multiply = onReturnPoint(gradeReturned.trim())* Integer.parseInt(unitReturned);
				totalScore = totalScore + multiply;
				totalUnit = totalUnit + Integer.parseInt(unitReturned);
			}
			// this line of code will check if the string is empty or not, if
			// not value pass will be passed to the string
			if (passFail.equalsIgnoreCase("")) {
				passFail = "PASS";
			}

			double what = (double) totalScore / totalUnit;
			int whole = (int) what;
			int part = (int) (what * 100);
			String parts = (Integer.toString(part)).substring(1, 3);
			String cgpaa = (Integer.toString(whole) + "." + parts);

			double whatPrevious = (double) totalScorePrevious/ totalUnitPrevious;
			int wholePrevious = (int) whatPrevious;
			int partPrevious = (int) (whatPrevious * 100);
//			String partsPrevious = (Integer.toString(partPrevious)).substring(1, 3);
			String partsPrevious = (Integer.toString(partPrevious));
			String cgpaaPrevious = (Integer.toString(wholePrevious) + "." + partsPrevious);

			double whatCurrent = (double) totalScoreCurrent / totalUnitCurrent;
			int wholeCurrent = (int) whatCurrent;
			int partCurrent = (int) (whatCurrent * 100);
//			String partsCurrent = (Integer.toString(partCurrent)).substring(1,3);
			String partsCurrent = (Integer.toString(partCurrent));
			String cgpaaCurrent = (Integer.toString(wholeCurrent) + "." + partsCurrent);

			studentResult = new SummaryObject(passFail, totalScore, cgpaa);
			studentResult.setNoResult(noResult);
			studentResult.setTotalCreditPrevious(totalScorePrevious);
			studentResult.setTotalCreditCurrent(totalScoreCurrent);
			studentResult.setCgpaPrevious(cgpaaPrevious);
			studentResult.setCgpaCurrent(cgpaaCurrent);
			studentResult.setTotalUnit(totalUnit);
			studentResult.setTotalUnitPrevious(totalUnitPrevious);
			studentResult.setTotalUnitCurrent(totalUnitCurrent);
			studentResult.setTUP(tup);
			studentResult.setTUPCurrent(tupCurrent);
			studentResult.setTUPPrevious(tupPrevious);
			
		} catch (SQLException e) {}

		return studentResult;
	}
	
	
	
	

	public void exportResult() {

		HSSFRow row = null;

		ArrayList<String> matricDetails = new ArrayList<String>();
		int increment = 4;
		int incrementArray = 4;
		String returnMatric;

		String fileName2 = txtFileName.getText().trim();
		String sheetName2 = txtSheetName.getText().trim();

		// this is the selected value of the department
		String selectedDepartment = comDepartmet.getSelectedItem().toString()
				.trim();
		String level = comLevel.getSelectedItem().toString().trim();

		// this will be the header for the result summary section
		String headerValue = txtTitle.getText().trim();

		// create the spreadsheet file
		String filename2 = lblResultLlocation.getText() + "/" + fileName2
				+ ".xls";
		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = workbook.createSheet(sheetName2);

		HSSFCellStyle myStyle = workbook.createCellStyle();
		myStyle.setAlignment(CellStyle.ALIGN_CENTER);

		// THIS SECTION IS USED TO SET THE FONT OF THE SPREAADSHEET

		// Create a new font and alter it.
		HSSFFont font = workbook.createFont();
		font.setFontHeightInPoints((short) 30);
		font.setFontName("Courier New");
		font.setBoldweight((short) 20);

		// Fonts are set into a style so create a new one to use.
		CellStyle style = workbook.createCellStyle();
		style.setFont(font);
		style.setAlignment(CellStyle.ALIGN_CENTER);

		// this will create the first row
		HSSFRow rowhead = sheet.createRow((short) 0);
		rowhead.createCell(0).setCellValue(headerValue);
		rowhead.setHeightInPoints(35);
		rowhead.setRowStyle(style);

		sheet.addMergedRegion(new CellRangeAddress(0, // first row (0-based)
				0, // last row (0-based)
				0, // first column (0-based)
				15 // last column (0-based)
		));

		HSSFRow rowSmallHeader = sheet.createRow((short) 2);
		HSSFCell small1 = rowSmallHeader.createCell(0);
		small1.setCellStyle(myStyle);
		small1.setCellValue("S/NO");

		HSSFCell small2 = rowSmallHeader.createCell(1);
		small2.setCellStyle(myStyle);
		small2.setCellValue("MAT. NO");

		HSSFCell small3 = rowSmallHeader.createCell(2);
		small3.setCellStyle(myStyle);
		small3.setCellValue("CURRENT");
		
		HSSFCell small6 = rowSmallHeader.createCell(6);
		small6.setCellStyle(myStyle);
		small6.setCellValue("PREVIOUS");
		
		HSSFCell small10 = rowSmallHeader.createCell(10);
		small10.setCellStyle(myStyle);
		small10.setCellValue("CUMULATIVE");
		

		HSSFCell small14 = rowSmallHeader.createCell(14);
		small14.setCellStyle(myStyle);
		small14.setCellValue("REMARKS");

		HSSFRow rowSmallHeader2 = sheet.createRow((short) 3);
		rowSmallHeader2.createCell(0).setCellValue("");
		rowSmallHeader2.createCell(1).setCellValue("");
		rowSmallHeader2.createCell(2).setCellValue("TCP");
		rowSmallHeader2.createCell(3).setCellValue("TLU");
		rowSmallHeader2.createCell(4).setCellValue("TUP");
		rowSmallHeader2.createCell(5).setCellValue("CGPA");
		rowSmallHeader2.createCell(6).setCellValue("TCP");
		rowSmallHeader2.createCell(7).setCellValue("TLU");
		rowSmallHeader2.createCell(8).setCellValue("TUP");
		rowSmallHeader2.createCell(9).setCellValue("CGPA");
		rowSmallHeader2.createCell(10).setCellValue("TCP");
		rowSmallHeader2.createCell(11).setCellValue("TLU");
		rowSmallHeader2.createCell(12).setCellValue("TUP");
		rowSmallHeader2.createCell(13).setCellValue("CGPA");
		rowSmallHeader2.createCell(14).setCellValue("PASS/REPEAT");
		rowSmallHeader2.createCell(15).setCellValue("OUTSTANDING");

		sheet.addMergedRegion(new CellRangeAddress(2, // first row (0-based)
				2, // last row (0-based)
				2, // first column (0-based)
				5 // last column (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(2, // first row (0-based)
				2, // last row (0-based)
				6, // first column (0-based)
				9 // last column (0-based)
		));

		sheet.addMergedRegion(new CellRangeAddress(2, // first row (0-based)
				2, // last row (0-based)
				10, // first column (0-based)
				13 // last column (0-based)
		));

		try {

			rs = database.searchQueryStudentsExport(selectedDepartment, level);

			// counter initailization
			int counter = 1;
			while (rs.next()) {

				row = sheet.createRow((short) increment);
				row.createCell(0).setCellValue(Integer.toString(counter));
				returnMatric = rs.getString(DatabaseManager.KEY_MATRIC);
				// this will add the matric number to an arraylist to be used for query in another function
				matricDetails.add(returnMatric);
				row.createCell(1).setCellValue(returnMatric);
				increment++;
				counter++;
			}

			// THIS SECTION WILL GET THE STUDENTS DETIALS ONE AFTER THE OTHER

			for (String s : matricDetails) {
				SummaryObject details = returnStudentDetailsForNoResult(s.trim());
				row = sheet.getRow(incrementArray);
				row.createCell(2).setCellValue(details.getTotalCreditCurrent());
				row.createCell(3).setCellValue(details.getTLUCurrent());
//				row.createCell(4).setCellValue(details.getTUPCurrent());
				row.createCell(5).setCellValue(details.getCgpaCurrent());
				row.createCell(6).setCellValue(details.getTotalCreditPrevious());
				row.createCell(7).setCellValue(details.getTLUPrevious());
//				row.createCell(8).setCellValue(details.getTUPPrevious());
				row.createCell(9).setCellValue(details.getCgpaPrevious());
				row.createCell(10).setCellValue(details.getTotalCredit());
				row.createCell(11).setCellValue(details.getTLU());
//				row.createCell(12).setCellValue(details.getTUP());
				row.createCell(13).setCellValue(details.getCgpa());
				row.createCell(14).setCellValue(details.getPassFail());
				incrementArray++;
			}

			 incrementArray = 4;
			 
			for (String s : matricDetails) {
				int value, valueCurrent, valuePrevious, cellValue, cellValueCurrent, cellValuePrevious;
				row = sheet.getRow(incrementArray);
				OutstandingObject object = returnOutstanding(s.trim());
				row.createCell(15).setCellValue(object.getOutstanding());
				if (row.getCell(11) == null|| row.getCell(11).getCellType() == Cell.CELL_TYPE_BLANK) {
					
					 value = 0;
					 valueCurrent = 0;
					 valuePrevious = 0;
					 
				} else {
					String valueString = row.getCell(11).getStringCellValue();
					String valueStringCurrent  = row.getCell(3).getStringCellValue();
					String valueStringPrevious = row.getCell(7).getStringCellValue();
					value = Integer.parseInt(valueString);
					valueCurrent = Integer.parseInt(valueStringCurrent);
					valuePrevious = Integer.parseInt(valueStringPrevious);
				}
				cellValue = value - object.getUnitTotal();
				cellValueCurrent = valueCurrent - object.getUnitCurrent();
				cellValuePrevious = valuePrevious - object.getUnitPrevious();
				row.createCell(12).setCellValue(Integer.toString(cellValue));
				row.createCell(8).setCellValue(Integer.toString(cellValuePrevious));
				row.createCell(4).setCellValue(Integer.toString(cellValueCurrent));

				incrementArray++;
			}

			sheet.autoSizeColumn(14);
			sheet.autoSizeColumn(15);
			sheet.autoSizeColumn(1);

			FileOutputStream fileOut2 = new FileOutputStream(filename2);
			workbook.write(fileOut2);
			fileOut2.close();
			JOptionPane.showMessageDialog(null, "FIle Exported Successfully","Action Successful", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
		}

	}


	public boolean checkIfEven(int value) {
		if ((value & 1) == 0) {
			return true;
		} else {
			return false;
		}
	}
	
	



	public OutstandingObject returnOutstanding(String matricno) {
		String intialialize = "";
		int score = 0;
		int scoreCurrent = 0;
		int scorePrevious = 0;
		try {
			rs = database.searchOutstanding(matricno.trim());
			while (rs.next()) {
				String coursesReturned = rs.getString(DatabaseManager.KEY_COURSES);
				String unitReturned = rs.getString(DatabaseManager.KEY_UNIT);
				boolean present = getNoresult(coursesReturned);
				if (present) {
					
					scoreCurrent = scoreCurrent+ Integer.parseInt(unitReturned);
				 }
				else {
					
				   
					scorePrevious =  scorePrevious+ Integer.parseInt(unitReturned);
				   }
			
				
				intialialize = intialialize + coursesReturned + ",";
				score =+ Integer.parseInt(unitReturned);
				
			}
			} catch (SQLException e) {}
		OutstandingObject object = new OutstandingObject(intialialize, score, scoreCurrent, scorePrevious);
         return object;
	}
	
	
	
	
	
	
	
	
	

	// public SummaryObject returnStudentDetails(String matricno){
	// int totalScore = 0;
	// int totalUnit = 0;
	// int multiply = 1;
	//
	// String passFail = "";
	// SummaryObject studentResult = null;
	// try {
	// rs = database.searchQueryCourse(matricno.trim());
	// while (rs.next()) {
	// String coursesReturned = rs.getString(DatabaseManager.KEY_COURSES);
	// String unitReturned = rs.getString(DatabaseManager.KEY_UNIT);
	// String gradeReturned = rs.getString(DatabaseManager.KEY_GRADE);
	// if (gradeReturned.trim().equalsIgnoreCase("F")){
	// passFail = passFail +coursesReturned +"," ;
	// }
	// multiply = onReturnPoint(gradeReturned.trim()) *
	// Integer.parseInt(unitReturned);
	// totalScore = totalScore + multiply;
	// totalUnit = totalUnit + Integer.parseInt(unitReturned);
	// }
	// // this line of code will check if the string is empty or not, if not
	// value pass willl be passed to the string
	// if(passFail.equalsIgnoreCase("")){
	// passFail = "PASS";
	// }
	//
	// double what = (double) totalScore / totalUnit;
	//
	// int whole = (int) what;
	// int part = (int) (what * 100);
	// String parts = (Integer.toString(part)).substring(1, 3);
	//
	// String cgpaa = (Integer.toString(whole)+"."+parts);
	// //float cgpa = totalScore/totalUnit;
	//
	// studentResult = new SummaryObject(passFail, totalScore, cgpaa);
	// }
	// catch(SQLException e){ }
	//
	// return studentResult;
	// }
	
	
//	public SummaryObject resultDetailsResult(String matricno) {
//
//		int totalScoreCurrent = 0;
//		int totalScorePrevious = 0;
//		int totalUnitCurrent = 0;
//		int totalUnitPrevious = 0;
//		int totalScore = 0;
//		int totalUnit = 0;
//		int multiply = 1;
//		int multiplyPrevious = 1;
//		int multiplyCurrent = 1;
//		int tup = 0;
//		int tupCurrent = 0;
//		int tupPrevious = 0;
//		boolean noResult = true;
//
//		String passFail = "";
//		SummaryObject studentResult = null;
//		try {
//			rs = database.searchQueryCourse(matricno.trim());
//			while (rs.next()) {
//				String coursesReturned = rs.getString(DatabaseManager.KEY_COURSES);
//				String unitReturned = rs.getString(DatabaseManager.KEY_UNIT);
//				String gradeReturned = rs.getString(DatabaseManager.KEY_GRADE);
//				boolean present = getNoresult(coursesReturned);
//				if (present) {
//						noResult = false;
//						multiplyCurrent = onReturnPoint(gradeReturned.trim())* Integer.parseInt(unitReturned);
//						totalScoreCurrent = totalScoreCurrent + multiplyCurrent;
//						totalUnitCurrent = totalUnitCurrent+ Integer.parseInt(unitReturned);
//						if (!gradeReturned.trim().equalsIgnoreCase("F")) {
//							tupCurrent = tupCurrent + Integer.parseInt(unitReturned);
//						}
// 
//
//				} else {
//
//					multiplyPrevious = onReturnPoint(gradeReturned.trim())* Integer.parseInt(unitReturned);
//					totalScorePrevious = totalScorePrevious + multiplyPrevious;
//					totalUnitPrevious = totalUnitPrevious+ Integer.parseInt(unitReturned);
//					if (!gradeReturned.trim().equalsIgnoreCase("F")) {
//						tupPrevious = tupPrevious + Integer.parseInt(unitReturned);
//						
//					}
//
//				}
//
//				if (gradeReturned.trim().equalsIgnoreCase("F")) {
//					passFail = passFail + coursesReturned + ",";
//				} else {
//					tup = tup + Integer.parseInt(unitReturned);
//				}
//
//				multiply = onReturnPoint(gradeReturned.trim())* Integer.parseInt(unitReturned);
//				totalScore = totalScore + multiply;
//				totalUnit = totalUnit + Integer.parseInt(unitReturned);
//			}
//			// this line of code will check if the string is empty or not, if
//			// not value pass willl be passed to the string
//			if (passFail.equalsIgnoreCase("")) {
//				passFail = "PASS";
//			}
//
//			double what = (double) totalScore / totalUnit;
//			int whole = (int) what;
//			int part = (int) (what * 100);
//			String parts = (Integer.toString(part)).substring(1, 3);
//			String cgpaa = (Integer.toString(whole) + "." + parts);
//
//			double whatPrevious = (double) totalScorePrevious/ totalUnitPrevious;
//			int wholePrevious = (int) whatPrevious;
//			int partPrevious = (int) (whatPrevious * 100);
//			String partsPrevious = (Integer.toString(partPrevious)).substring(1, 3);
//			String cgpaaPrevious = (Integer.toString(wholePrevious) + "." + partsPrevious);
//
//			double whatCurrent = (double) totalScoreCurrent / totalUnitCurrent;
//			int wholeCurrent = (int) whatCurrent;
//			int partCurrent = (int) (whatCurrent * 100);
//			String partsCurrent = (Integer.toString(partCurrent)).substring(1,3);
//			String cgpaaCurrent = (Integer.toString(wholeCurrent) + "." + partsCurrent);
//
//			studentResult = new SummaryObject(passFail, totalScore, cgpaa);
//			studentResult.setNoResult(noResult);
//			studentResult.setTotalCreditPrevious(totalScorePrevious);
//			studentResult.setTotalCreditCurrent(totalScoreCurrent);
//			studentResult.setCgpaPrevious(cgpaaPrevious);
//			studentResult.setCgpaCurrent(cgpaaCurrent);
//			studentResult.setTotalUnit(totalUnit);
//			studentResult.setTotalUnitPrevious(totalUnitPrevious);
//			studentResult.setTotalUnitCurrent(totalUnitCurrent);
//			studentResult.setTUP(tup);
//			studentResult.setTUPCurrent(tupCurrent);
//			studentResult.setTUPPrevious(tupPrevious);
//			
//			
//
//		} catch (SQLException e) {
//		}
//
//		return studentResult;
//
//	}
	
//public int getSemester() {
//
//			int semester = 1;
//			if (comboBoxSemester.getSelectedItem().equals("1st")) {
//				return 1;
//			} else {
//				return 2;
//			}
//
//		}


}
