package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Screens.HomePanel;


public class LoginHome extends JPanel {
	private JTextField textField;
	private JPasswordField password;
	private JButton submit;
	public LoginHome(final JFrame currentGUIFrame) {
		setLayout(null);		
		//Prints out name of the store.
		JLabel lblNewLabel = new JLabel("User Name *");
		lblNewLabel.setBounds(100, 70, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		lblNewLabel = new JLabel("Oklohoma Christian University");
		lblNewLabel.setBounds(200, 30, 200, 20);
		currentGUIFrame.add(lblNewLabel);
		textField = new JTextField();
		textField.setBounds(200, 70, 225, 25);
		currentGUIFrame.add(textField);	
		lblNewLabel = new JLabel("Password *");
		lblNewLabel.setBounds(100, 100, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		password= new JPasswordField();
		password.setBounds(200, 100, 225, 25);		
		currentGUIFrame.add(password);
		submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(textField.getText()==null || textField.getText().trim().length()==0){
					JOptionPane.showMessageDialog(submit, "Please enter user name");
				}else if(password.getText()==null || password.getText().trim().length()==0){
					JOptionPane.showMessageDialog(submit, "Please enter password");
				}else if((textField.getText().trim().equalsIgnoreCase("admin") && password.getText().trim().equalsIgnoreCase("admin")) || (textField.getText().trim().equalsIgnoreCase("director") && password.getText().trim().equalsIgnoreCase("director"))){
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new HomePanel(currentGUIFrame));
					currentGUIFrame.getContentPane().repaint();					
				}else{
					JOptionPane.showMessageDialog(submit, "Please enter correct User name and password");
				}
			}
		});
		submit.setBounds(225, 175, 75, 30);
		currentGUIFrame.add(submit);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancel.setBounds(305, 175, 75, 30);
		currentGUIFrame.add(cancel);
	}
}
