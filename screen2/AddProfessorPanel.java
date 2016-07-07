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

import main.LoginHome;
import au.com.bytecode.opencsv.CSVWriter;

public class AddProfessorPanel  extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewProfessor,back,exitButton;
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
		
		lblNewLabel = new JLabel("First Name");
		lblNewLabel.setBounds(50, 120, 80, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField firstName = new JTextField();
		firstName.setBounds(135, 118, 150, 25);
		currentGUIFrame.add(firstName);
		
		lblNewLabel = new JLabel("Last Name");
		lblNewLabel.setBounds(300, 120, 80, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField lastName = new JTextField();
		lastName.setBounds(405, 118, 150, 25);
		currentGUIFrame.add(lastName);
		
		lblNewLabel = new JLabel("Grad School");
		lblNewLabel.setBounds(50, 150, 80, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField gradSchool= new JTextField();
		gradSchool.setBounds(135, 148, 150, 25);		
		currentGUIFrame.add(gradSchool);
		
		lblNewLabel = new JLabel("Degree");
		lblNewLabel.setBounds(300, 150, 80, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField degree= new JTextField();
		degree.setBounds(405, 148, 150, 25);		
		currentGUIFrame.add(degree);
		
		lblNewLabel = new JLabel("Course");
		lblNewLabel.setBounds(50, 180, 80, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField course= new JTextField();
		course.setBounds(135, 178, 150, 25);		
		currentGUIFrame.add(course);
		
		lblNewLabel = new JLabel("Title");
		lblNewLabel.setBounds(300, 180, 80, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField title= new JTextField();
		title.setBounds(405, 178, 150, 25);		
		currentGUIFrame.add(title);
		
		lblNewLabel = new JLabel("MaxLoad Fall");
		lblNewLabel.setBounds(50, 210, 80, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField fallLoad= new JTextField();
		fallLoad.setBounds(135, 208, 150, 25);		
		currentGUIFrame.add(fallLoad);
		
		lblNewLabel = new JLabel("MaxLoad Spring");
		lblNewLabel.setBounds(300, 210, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField springLoad= new JTextField();
		springLoad.setBounds(405, 208, 150, 25);		
		currentGUIFrame.add(springLoad);
		
		lblNewLabel = new JLabel("MaxLoad Summer");
		lblNewLabel.setBounds(50, 250, 120, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField summerLoad= new JTextField();
		summerLoad.setBounds(175, 248, 150, 25);		
		currentGUIFrame.add(summerLoad);
		
		lblNewLabel = new JLabel("Days");
		lblNewLabel.setBounds(50, 290, 80, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField dayTextField= new JTextField();
		dayTextField.setBounds(175, 288, 150, 25);		
		currentGUIFrame.add(dayTextField);
		
		lblNewLabel = new JLabel("Semester");
		lblNewLabel.setBounds(300, 290, 80, 20);
		//currentGUIFrame.add(lblNewLabel);
		
		final JTextField semTextField= new JTextField();
		semTextField.setBounds(385, 288, 150, 25);		
		//currentGUIFrame.add(semTextField);
		
		addNewProfessor = new JButton("Save");
		addNewProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(firstName!=null && firstName.getText().trim().length()!=0 && lastName!=null && lastName.getText().trim().length()!=0
						&& degree!=null && degree.getText().trim().length()!=0 && title!=null && title.getText().trim().length()!=0){
					try{
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataFaculty.csv",true));
						String [] record = {lastName.getText(),firstName.getText(),gradSchool.getText(),degree.getText(),title.getText(),dayTextField.getText(),fallLoad.getText(),springLoad.getText(),summerLoad.getText()};
						writer.writeNext(record);
						writer.close();
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new ProfessorsPanel(currentGUIFrame));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(addNewProfessor, "Please enter all correct details");
				}
			}
		});
		addNewProfessor.setBounds(270, 440, 125, 30);
		currentGUIFrame.add(addNewProfessor);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ProfessorsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		back.setBounds(100, 440, 125, 30);
		currentGUIFrame.add(back);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(450, 440, 100, 30);
		currentGUIFrame.add(exitButton);
	}
}