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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.LoginHome;
import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVWriter;
import beans.Professor;
import beans.Semester;

public class EditSemesterPanel  extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewSemester,back,exitButton;
	public EditSemesterPanel(final JFrame currentGUIFrame,final String seletedIndex) {
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
		lblNewLabel = new JLabel("Edit Professor");
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
		
		DatabaseHelper helper=new DatabaseHelper();
		ArrayList semesterList=helper.getSemesterDataFromTestFaculty();
		Semester semesterData=(Semester)semesterList.get(Integer.parseInt(seletedIndex)+1);
		
		lblNewLabel = new JLabel("Semester");
		lblNewLabel.setBounds(100, 120, 110, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField semester = new JTextField();
		semester.setText(semesterData.getSemesterName());
		semester.setBounds(250, 118, 175, 25);
		currentGUIFrame.add(semester);	
		
		lblNewLabel = new JLabel("Start Date");
		lblNewLabel.setBounds(100, 150, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField startDate= new JTextField();
		startDate.setText(semesterData.getStartDate());
		startDate.setBounds(250, 148, 175, 25);		
		currentGUIFrame.add(startDate);
		
		lblNewLabel = new JLabel("End Date");
		lblNewLabel.setBounds(100, 180, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField endDate= new JTextField();
		endDate.setText(semesterData.getEndDate());
		endDate.setBounds(250, 178, 175, 25);
		currentGUIFrame.add(endDate);
		
		addNewSemester = new JButton("Update");
		addNewSemester.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(semester!=null && semester.getText().trim().length()!=0 
						&& startDate!=null && startDate.getText().trim().length()!=0
						&& endDate!=null && endDate.getText().trim().length()!=0){
					try{
						DatabaseHelper helper=new DatabaseHelper();
						ArrayList semesterList=helper.getSemesterDataFromTestFaculty();
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\TestDataSemesters.csv"));
						Iterator iterator=semesterList.iterator();
						int i=0;
						while(iterator.hasNext()){
							Semester semesterData=(Semester)iterator.next();
							if(i==(Integer.parseInt(seletedIndex)+1)){
								String [] record = {semester.getText(),startDate.getText(),endDate.getText()};
								writer.writeNext(record);
							}else{								
								String [] record = {semesterData.getSemesterName(),semesterData.getStartDate(),semesterData.getEndDate()};
								writer.writeNext(record);								
							}
							i++;
						}						
						writer.close();
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new SemestersPanel(currentGUIFrame));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(addNewSemester, "Please enter all correct details");
				}
			}
		});
		addNewSemester.setBounds(270, 275, 125, 30);
		currentGUIFrame.add(addNewSemester);
				
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
		exitButton.setBounds(450, 275, 125, 30);
		currentGUIFrame.add(exitButton);
	}

}
