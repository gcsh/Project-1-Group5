package Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import main.LoginHome;
import main.SessionManager;

public class HomePanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,database;
	public HomePanel(final JFrame currentGUIFrame,final SessionManager sessionManager) {
		setLayout(null);		
		JLabel lblNewLabel = new JLabel("Welcome "+sessionManager.getUserRole());
		lblNewLabel.setBounds(425, 10, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int dialogButton = JOptionPane.YES_NO_OPTION;
				int result=JOptionPane.showConfirmDialog (null, "Do you want to logout with a button YES","Alert",dialogButton);
                if(result==0){
                	sessionManager.setUserRole("");
                	currentGUIFrame.getContentPane().removeAll();
    				currentGUIFrame.getContentPane().add(new LoginHome(currentGUIFrame));
    				currentGUIFrame.getContentPane().repaint();
                }	
			}
		});
		logout.setBounds(525, 7, 75, 30);
		currentGUIFrame.add(logout);
		lblNewLabel = new JLabel("Graduate School User Portal");
		lblNewLabel.setBounds(250, 75, 200, 20);
		currentGUIFrame.add(lblNewLabel);
		
		professors = new JButton("Professors");
		professors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ProfessorsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		professors.setBounds(75, 100, 100, 30);
		currentGUIFrame.add(professors);
		
		students = new JButton("Students");
		students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new StudentsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		students.setBounds(185, 100, 90, 30);
		currentGUIFrame.add(students);
		
		degrees = new JButton("Degrees");
		degrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new DegressPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		degrees.setBounds(285, 100, 90, 30);
		currentGUIFrame.add(degrees);
		
		subjects = new JButton("Courses");
		subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new CousesPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		subjects.setBounds(385, 100, 90, 30);
		currentGUIFrame.add(subjects);
		
		rooms = new JButton("Semesters");
		rooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new SemestersPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		rooms.setBounds(485, 100, 100, 30);
		currentGUIFrame.add(rooms);
		
		schedule = new JButton("Schedule");
		schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new SchedulePanel(currentGUIFrame,null));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		schedule.setBounds(75, 150, 90, 30);
		currentGUIFrame.add(schedule);
		
		reports = new JButton("Reports");
		reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ReportsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		reports.setBounds(185, 150, 85, 30);
		currentGUIFrame.add(reports);
		
		database = new JButton("Database");
		database.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(database, "Coming Soon !!!");
			}
		});
		database.setBounds(285, 150, 100, 30);
		currentGUIFrame.add(database);
		
		JLabel label = new JLabel();
		label.setBounds(20, 5, 200, 75);
		label.setIcon(new ImageIcon(System.getProperty("user.dir")+"\\src\\Screens\\logo.jpg"));
        currentGUIFrame.add(label);
	}
}
