package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.LoginHome;
import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVWriter;
import beans.Professor;
import beans.Student;

public class EditStudentPanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewProfessor,back,exitButton;
	public EditStudentPanel(final JFrame currentGUIFrame,final String seletedIndex) {
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
		lblNewLabel = new JLabel("Edit Student");
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
		students.setBackground(Color.gray);
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
		
		lblNewLabel = new JLabel("Student Name");
		lblNewLabel.setBounds(100, 120, 110, 20);
		//currentGUIFrame.add(lblNewLabel);
		
		JTextField studentName = new JTextField();
		studentName.setBounds(250, 118, 175, 25);
		//currentGUIFrame.add(studentName);
		
		DatabaseHelper helper=new DatabaseHelper();
		ArrayList studentCourseList=helper.getAllStudentCourses();
		Student student=(Student)studentCourseList.get(Integer.parseInt(seletedIndex));
		
		lblNewLabel = new JLabel("Student ID");
		lblNewLabel.setBounds(100, 120, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField studentId= new JTextField();
		studentId.setText(student.getStudentId());
		studentId.setBounds(250, 118, 175, 25);		
		currentGUIFrame.add(studentId);
		
		lblNewLabel = new JLabel("Course Code");
		lblNewLabel.setBounds(100, 150, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField subject= new JTextField();
		subject.setText(student.getCourseCode());
		subject.setBounds(250, 148, 175, 25);
		currentGUIFrame.add(subject);
		
		lblNewLabel = new JLabel("Course Name");
		lblNewLabel.setBounds(100, 180, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField subjectName= new JTextField();
		subjectName.setText(student.getCourseName());
		subjectName.setBounds(250, 178, 175, 25);
		currentGUIFrame.add(subjectName);
		
		lblNewLabel = new JLabel("Grade");
		lblNewLabel.setBounds(100, 210, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField grade= new JTextField();
		grade.setText(student.getGrade());
		grade.setBounds(250, 208, 175, 25);
		currentGUIFrame.add(grade);
		
		lblNewLabel = new JLabel("Semester");
		lblNewLabel.setBounds(100, 240, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField semester= new JTextField();
		semester.setText(student.getSemester());
		semester.setBounds(250, 238, 175, 25);
		currentGUIFrame.add(semester);
		
		addNewProfessor = new JButton("Update");
		addNewProfessor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(studentId!=null && studentId.getText().trim().length()!=0 && subject!=null && subject.getText().trim().length()!=0
						&& subjectName!=null && subjectName.getText().trim().length()!=0 && grade!=null && grade.getText().trim().length()!=0 
						&& semester!=null && semester.getText().trim().length()!=0){
					try{
						DatabaseHelper helper=new DatabaseHelper();
						ArrayList studentCourseList=helper.getAllStudentCourses();
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\STC.DUMP.CSV"));
						Iterator iterator=studentCourseList.iterator();
						int i=0;
						while(iterator.hasNext()){
							Student student=(Student)iterator.next();
							if(i==(Integer.parseInt(seletedIndex))){
								String [] record = {studentId.getText(),subject.getText(),subjectName.getText(),semester.getText(),grade.getText()};
								writer.writeNext(record);
							}else{								
								String [] record = {student.getStudentId(),student.getCourseCode(),student.getCourseName(),student.getSemester(),student.getGrade()};
								writer.writeNext(record);								
							}
							i++;
						}						
						writer.close();
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new StudentsPanel(currentGUIFrame));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(addNewProfessor, "Please enter all correct details");
				}
			}
		});
		addNewProfessor.setBounds(270, 275, 125, 30);
		currentGUIFrame.add(addNewProfessor);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new StudentsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		back.setBounds(100, 275, 125, 30);
		currentGUIFrame.add(back);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(450, 275, 125, 30);
		currentGUIFrame.add(exitButton);
	}

}
