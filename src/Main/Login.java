package Main;


import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;


public class Login implements ActionListener {

	private JFrame frame;
	private JTextField txt_userName,txt_password; ;
	private JButton btnExit, btnLogin;
	private long currentId;
	private BufferedImage myPicture;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					
					Login window = new Login();
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		frame. setBounds(100, 100, (int) dim.getWidth(), (int) dim.getHeight());
		
		JScrollPane scrollContentPane = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		frame.setContentPane(scrollContentPane);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		
		txt_userName = new JTextField();
		txt_userName.setBounds(1031, 519, 145, 25);
		frame.getContentPane().add(txt_userName);
		txt_userName.setColumns(10);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(1031, 550, 145, 25);
		frame.getContentPane().add(txt_password);
		txt_password.setColumns(10);
		
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(this);
		btnLogin.setBounds(1031, 587, 145, 25);
		frame.getContentPane().add(btnLogin);
		
		btnExit= new JButton("Exit");
		btnExit.setBounds(1031, 625, 145, 25);
		btnExit.addActionListener(this);
		frame.getContentPane().add(btnExit);
		
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setForeground(Color.RED);
		lblUsername.setBounds(951, 524, 93, 15);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setForeground(Color.RED);
		lblPassword.setBounds(951, 550, 93, 15);
		frame.getContentPane().add(lblPassword);
		
		JLabel lblEkitiStateUniversity = new JLabel("Ekiti State University");
		lblEkitiStateUniversity.setForeground(Color.RED);
		lblEkitiStateUniversity.setFont(new Font("Dialog", Font.ITALIC, 28));
		lblEkitiStateUniversity.setBounds(404, 41, 573, 86);
		frame.getContentPane().add(lblEkitiStateUniversity);
		
		JLabel lblResultComputationSystem = new JLabel("Result Computation System");
		lblResultComputationSystem.setForeground(Color.RED);
		lblResultComputationSystem.setFont(new Font("Dialog", Font.BOLD, 38));
		lblResultComputationSystem.setBounds(306, 12, 603, 45);
		frame.getContentPane().add(lblResultComputationSystem);
		
		JPanel panelPic = new JPanel();
		panelPic.setBounds(137, 186, 772, 461);
		frame.getContentPane().add(panelPic);
		
		InputStream input = getClass().getResourceAsStream("/online.jpg");

		try {
			myPicture = ImageIO.read(input);
			JLabel picLabel = new JLabel(new ImageIcon(myPicture));
	 		panelPic.add(picLabel);
	 		
	 		JScrollPane scrollPane = new JScrollPane();
	 		scrollPane.setBounds(0, 0, 3, 3);
	 		frame.getContentPane().add(scrollPane);
		} catch (IOException e) {e.printStackTrace();
		}
	

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		// this is to get the object that performed the event
		Object source = event.getSource(); 
        if(source.equals(btnLogin))
        { String loginname = txt_userName.getText().trim();
           String loginpass = txt_password.getText().trim();
           	 if(loginname.equals("s") && loginpass.equals("s"))
					{
           		 
					String dialogmessage = "Welcome " +loginname;
                     JOptionPane.showMessageDialog((Component)null, dialogmessage,"Successful", JOptionPane.INFORMATION_MESSAGE);
                    	
                         new Details();
						 frame.setVisible(false);
//						 frame.dispose();

					}
			else{
					JOptionPane.showMessageDialog(null,"Invaild User name or password" , "WARNING!!!",JOptionPane.INFORMATION_MESSAGE);
						txt_userName.setText("");
                        txt_password.setText("");
					}
		 } 
        
        if(source.equals(btnExit)) System.exit(0);
     
        
       
	}
}
