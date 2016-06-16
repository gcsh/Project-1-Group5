package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import main.LoginHome;

public class AddProfessorPanel  extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewProfessor,back;
	public AddProfessorPanel(final JFrame currentGUIFrame) {
		setLayout(null);		
		JLabel lblNewLabel = new JLabel("Welcome User");
		lblNewLabel.setBounds(425, 10, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		logout = new JButton("Logout");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new LoginHome(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();	
			}
		});
		logout.setBounds(525, 7, 75, 30);
		currentGUIFrame.add(logout);
		lblNewLabel = new JLabel("Add New Professor");
		lblNewLabel.setBounds(250, 90, 200, 20);
		currentGUIFrame.add(lblNewLabel);
		
		professors = new JButton("Professors");
		professors.setBackground(Color.gray);
		professors.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ProfessorsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		professors.setBounds(10, 55, 100, 30);
		currentGUIFrame.add(professors);
		
		students = new JButton("Students");
		students.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new StudentsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		students.setBounds(95, 55, 90, 30);
		currentGUIFrame.add(students);
		
		degrees = new JButton("Degrees");
		degrees.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new DegressPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		degrees.setBounds(180, 55, 90, 30);
		currentGUIFrame.add(degrees);
		
		subjects = new JButton("Subjects");
		subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new SubjectsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		subjects.setBounds(265, 55, 90, 30);
		currentGUIFrame.add(subjects);
		
		rooms = new JButton("Semesters");
		rooms.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new SemestersPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		rooms.setBounds(350, 55, 100, 30);
		currentGUIFrame.add(rooms);
		
		schedule = new JButton("Schedule");
		schedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new SchedulePanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		schedule.setBounds(435, 55, 90, 30);
		currentGUIFrame.add(schedule);
		
		reports = new JButton("Reports");
		reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ReportsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		reports.setBounds(520, 55, 85, 30);
		currentGUIFrame.add(reports);
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(100, 120, 110, 20);
		currentGUIFrame.add(lblNewLabel);
		
		JTextField textField = new JTextField();
		textField.setBounds(250, 118, 175, 25);
		currentGUIFrame.add(textField);
		
		lblNewLabel = new JLabel("Last Name");
		lblNewLabel.setBounds(100, 150, 110, 20);
		currentGUIFrame.add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(250, 148, 175, 25);
		currentGUIFrame.add(textField);
		
		lblNewLabel = new JLabel("Grad School");
		lblNewLabel.setBounds(100, 180, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		JTextField subject= new JTextField();
		subject.setBounds(250, 178, 175, 25);		
		currentGUIFrame.add(subject);
		
		lblNewLabel = new JLabel("Degree");
		lblNewLabel.setBounds(100, 210, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		subject= new JTextField();
		subject.setBounds(250, 208, 175, 25);		
		currentGUIFrame.add(subject);
		
		lblNewLabel = new JLabel("Subject");
		lblNewLabel.setBounds(100, 240, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		subject= new JTextField();
		subject.setBounds(250, 238, 175, 25);		
		currentGUIFrame.add(subject);
		
		lblNewLabel = new JLabel("Title");
		lblNewLabel.setBounds(100, 270, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		subject= new JTextField();
		subject.setBounds(250, 268, 175, 25);		
		currentGUIFrame.add(subject);
		
		lblNewLabel = new JLabel("Days");
		lblNewLabel.setBounds(100, 310, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		String	listData[] ={"Sunday","Monday","Tuesday","Wednesday","Thursday","Friday","Saturday"};
		JList listbox = new JList( listData );
		listbox.setBounds(250, 308, 100, 35);
		currentGUIFrame.add(listbox);
		
		lblNewLabel = new JLabel("Semester *");
		lblNewLabel.setBounds(100, 360, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		String semesterData[] ={"Spring","Fall"};
		listbox = new JList( semesterData );
		listbox.setBounds(250, 358, 100, 35);
		currentGUIFrame.add(listbox);
		
		addNewProfessor = new JButton("Save");
		addNewProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		addNewProfessor.setBounds(300, 400, 125, 30);
		currentGUIFrame.add(addNewProfessor);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ProfessorsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		back.setBounds(100, 400, 125, 30);
		currentGUIFrame.add(back);
	}
}