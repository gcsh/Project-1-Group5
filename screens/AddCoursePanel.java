package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import au.com.bytecode.opencsv.CSVWriter;
import main.LoginHome;

public class AddCoursePanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewCourse,back;
	public AddCoursePanel(final JFrame currentGUIFrame) {
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
		lblNewLabel = new JLabel("Add New Subject");
		lblNewLabel.setBounds(250, 90, 200, 20);
		currentGUIFrame.add(lblNewLabel);
		
		professors = new JButton("Professors");
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
		
		subjects = new JButton("Courses");
		subjects.setBackground(Color.gray);
		subjects.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new CousesPanel(currentGUIFrame));
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
				currentGUIFrame.getContentPane().add(new SchedulePanel(currentGUIFrame,null));
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
		
		lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setBounds(100, 120, 110, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField courseName = new JTextField();
		courseName.setBounds(250, 118, 175, 25);
		currentGUIFrame.add(courseName);	
		
		lblNewLabel = new JLabel("Course Hours");
		lblNewLabel.setBounds(100, 150, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField courseHours= new JTextField();
		courseHours.setBounds(250, 148, 175, 25);		
		currentGUIFrame.add(courseHours);
		
		lblNewLabel = new JLabel("Course Capacity");
		lblNewLabel.setBounds(100, 180, 115, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField courseCapacity= new JTextField();
		courseCapacity.setBounds(250, 178, 175, 25);
		currentGUIFrame.add(courseCapacity);
		
		lblNewLabel = new JLabel("Prequisite");
		lblNewLabel.setBounds(100, 210, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField prereq= new JTextField();
		prereq.setBounds(250, 208, 175, 25);
		currentGUIFrame.add(prereq);
		
		lblNewLabel = new JLabel("Course Semester");
		lblNewLabel.setBounds(100, 240, 115, 20);
		currentGUIFrame.add(lblNewLabel);
		
		String	listData[] ={"Spring","Fall","Semester"};
		JList listbox = new JList( listData );
		listbox.setBounds(250, 238, 175, 35);
		currentGUIFrame.add(listbox);
		
		
		addNewCourse = new JButton("Save");
		addNewCourse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(courseName!=null && courseName.getText().trim().length()!=0 && courseHours!=null && courseHours.getText().trim().length()!=0
						&& courseCapacity!=null && courseCapacity.getText().trim().length()!=0 && prereq!=null && prereq.getText().trim().length()!=0){
					try{
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataCourses.csv",true));
						String [] record = {courseName.getText(),courseHours.getText(),courseCapacity.getText(),prereq.getText()};
						writer.writeNext(record);
						writer.close();
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new CousesPanel(currentGUIFrame));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(addNewCourse, "Please enter all correct details");
				}
			}
		});
		addNewCourse.setBounds(300, 295, 125, 30);
		currentGUIFrame.add(addNewCourse);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new CousesPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		back.setBounds(100, 295, 125, 30);
		currentGUIFrame.add(back);
	}
}

