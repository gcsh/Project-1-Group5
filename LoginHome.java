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

import Database.DatabaseHelper;
import Screens.HomePanel;
import beans.UserAccount;


public class LoginHome extends JPanel {
	private JTextField textField;
	private JPasswordField password;
	private JButton submit;
	public LoginHome(final JFrame currentGUIFrame) {
		setLayout(null);		
		//Prints out name of the store.
		JLabel lblNewLabel = new JLabel("User Name *");
		lblNewLabel.setBounds(175, 70, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		lblNewLabel = new JLabel("Oklohoma Christian University");
		lblNewLabel.setBounds(250, 30, 200, 20);
		currentGUIFrame.add(lblNewLabel);
		textField = new JTextField();
		textField.setBounds(275, 70, 225, 25);
		currentGUIFrame.add(textField);	
		lblNewLabel = new JLabel("Password *");
		lblNewLabel.setBounds(175, 100, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		password= new JPasswordField();
		password.setBounds(275, 100, 225, 25);		
		currentGUIFrame.add(password);
		submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				DatabaseHelper databaseHelper=new DatabaseHelper();
				if(textField.getText()==null || textField.getText().trim().length()==0){
					JOptionPane.showMessageDialog(submit, "Please enter user name");
				}else if(password.getText()==null || password.getText().trim().length()==0){
					JOptionPane.showMessageDialog(submit, "Please enter password");
				}
				UserAccount userAccount=databaseHelper.getUserAccounts(textField.getText().trim(), password.getText().trim());
				if(userAccount!=null){//if((textField.getText().trim().equalsIgnoreCase("admin") && password.getText().trim().equalsIgnoreCase("admin")) || (textField.getText().trim().equalsIgnoreCase("director") && password.getText().trim().equalsIgnoreCase("director"))){
					SessionManager sessionManager=new SessionManager();
					sessionManager.setUserRole(userAccount.getType());
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new HomePanel(currentGUIFrame,sessionManager));
					currentGUIFrame.getContentPane().repaint();					
				}else{
					JOptionPane.showMessageDialog(submit, "Please enter correct User name and password");
				}
			}
		});
		submit.setBounds(250, 175, 75, 30);
		currentGUIFrame.add(submit);
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		cancel.setBounds(350, 175, 75, 30);
		currentGUIFrame.add(cancel);
	}
}
