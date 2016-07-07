package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import au.com.bytecode.opencsv.CSVWriter;
import main.LoginHome;

public class AddDegreePanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewDegree,back,exitButton;
	public AddDegreePanel(final JFrame currentGUIFrame) {
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
		lblNewLabel = new JLabel("Add New Degree Plan");
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
		degrees.setBackground(Color.gray);
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
		
		lblNewLabel = new JLabel("Degree Code");
		lblNewLabel.setBounds(100, 120, 110, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField degreeCode = new JTextField();
		degreeCode.setBounds(250, 118, 175, 25);
		currentGUIFrame.add(degreeCode);	
		
		lblNewLabel = new JLabel("Grad School");
		lblNewLabel.setBounds(100, 150, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  gradSchool= new JTextField();
		gradSchool.setBounds(250, 148, 175, 25);		
		currentGUIFrame.add(gradSchool);
		
		lblNewLabel = new JLabel("Forecast");
		lblNewLabel.setBounds(100, 180, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  forecast= new JTextField();
		forecast.setBounds(250, 178, 175, 25);
		currentGUIFrame.add(forecast);
		
		lblNewLabel = new JLabel("Degree Name");
		lblNewLabel.setBounds(100, 210, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  degreeName= new JTextField();
		degreeName.setBounds(250, 208, 175, 25);
		currentGUIFrame.add(degreeName);
		
		lblNewLabel = new JLabel("Description");
		lblNewLabel.setBounds(100, 240, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  description= new JTextField();
		description.setBounds(250, 238, 175, 25);
		currentGUIFrame.add(description);
		
		lblNewLabel = new JLabel("Hours");
		lblNewLabel.setBounds(100, 270, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  hours= new JTextField();
		hours.setBounds(250, 268, 175, 25);
		currentGUIFrame.add(hours);
		
		lblNewLabel = new JLabel("Type");
		lblNewLabel.setBounds(100, 300, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  type= new JTextField();
		type.setBounds(250, 298, 175, 25);
		currentGUIFrame.add(type);
		
		lblNewLabel = new JLabel("Courses");
		lblNewLabel.setBounds(100, 330, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  courses= new JTextField();
		courses.setBounds(250, 328, 175, 25);
		currentGUIFrame.add(courses);
		
//		final JCheckBox fall = new JCheckBox("Fall");
//		final JCheckBox spring = new JCheckBox("Spring");
//	    final JCheckBox summer = new JCheckBox("Summer");
//	    fall.setBounds(150, 240, 50, 30);
//		currentGUIFrame.add(fall);
//		spring.setBounds(210, 240, 70, 30);
//		currentGUIFrame.add(spring);
//		summer.setBounds(280, 240, 100, 30);
//		currentGUIFrame.add(summer);
		
		addNewDegree = new JButton("Save");
		addNewDegree.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(degreeCode!=null && degreeCode.getText().trim().length()!=0 && gradSchool!=null && gradSchool.getText().trim().length()!=0
						&& forecast!=null && forecast.getText().trim().length()!=0 && degreeName!=null && degreeName.getText().trim().length()!=0){
					try{
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegrees.csv",true));
						String [] record = {degreeCode.getText(),gradSchool.getText(),degreeName.getText(),forecast.getText()};
						writer.writeNext(record);
						writer.close();
						CSVWriter writers = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegreePlanReq.csv",true));
						String [] records = {degreeCode.getText(),description.getText(),hours.getText(),type.getText(),courses.getText()};
						writers.writeNext(records);
						writers.close();
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new DegressPanel(currentGUIFrame));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(addNewDegree, "Please enter all correct details");
				}
			}
		});
		addNewDegree.setBounds(275, 375, 125, 30);
		currentGUIFrame.add(addNewDegree);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new DegressPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		back.setBounds(100, 375, 125, 30);
		currentGUIFrame.add(back);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(450, 375, 100, 30);
		currentGUIFrame.add(exitButton);
	}
}
