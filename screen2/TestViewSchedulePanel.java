package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.LoginHome;
import Database.DatabaseHelper;
import beans.Schedule;

public class TestViewSchedulePanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewProfessor,back,exitButton;
	public TestViewSchedulePanel(final JFrame currentGUIFrame,ArrayList scheduleList,String year) {
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
		lblNewLabel = new JLabel("Test Schedules");
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
		schedule.setBackground(Color.gray);
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
		lblNewLabel = new JLabel("Total Students");
		lblNewLabel.setBounds(100, 120, 300, 20);
		currentGUIFrame.add(lblNewLabel);
		
		LinkedHashMap studentDegreeMap=helper.getAllStudentDataFromStudentDumpYear(year.trim());
		final JTextField degreeCode = new JTextField();
		degreeCode.setText(""+studentDegreeMap.size());
		degreeCode.setBounds(450, 118, 50, 25);
		currentGUIFrame.add(degreeCode);
		int requiredCourses=0;
		LinkedHashMap studentCourseMap=helper.getAllStudentCourseDataMap();
		for (Object key : studentDegreeMap.keySet()) {
			if(((ArrayList)studentCourseMap.get(key))!=null && ((ArrayList)studentCourseMap.get(key)).size()<10){
				requiredCourses++;
			}else if(((ArrayList)studentCourseMap.get(key))==null){
				requiredCourses++;
			}
		}
		
		lblNewLabel = new JLabel("Students Required Courses");
		lblNewLabel.setBounds(100, 150, 300, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  gradSchool= new JTextField();
		gradSchool.setText(""+requiredCourses);
		gradSchool.setBounds(450, 148, 50, 25);		
		currentGUIFrame.add(gradSchool);
		
		lblNewLabel = new JLabel("Students with out Required Courses");
		lblNewLabel.setBounds(100, 180, 300, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  forecast= new JTextField();
		forecast.setText(""+(studentDegreeMap.size()-requiredCourses));
		forecast.setBounds(450, 178, 50, 25);
		currentGUIFrame.add(forecast);
		
		lblNewLabel = new JLabel("Number of Sections");
		lblNewLabel.setBounds(100, 210, 300, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  degreeName= new JTextField();
		degreeName.setText(""+(scheduleList.size()));
		degreeName.setBounds(450, 208, 50, 25);
		currentGUIFrame.add(degreeName);
		
		int overcounter=0,lesscounter=0;
		for(int i=0;i<scheduleList.size();i++){
			Schedule schedule=(Schedule)scheduleList.get(i);
			if(Integer.parseInt(schedule.getWaitList())>25){
				overcounter++;
			}
			if(Integer.parseInt(schedule.getWaitList())<=25){
				lesscounter++;
			}
		}
		
		lblNewLabel = new JLabel("Number of Sections above capacity");
		lblNewLabel.setBounds(100, 240, 300, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  description= new JTextField();
		description.setText(""+overcounter);
		description.setBounds(450, 238, 50, 25);
		currentGUIFrame.add(description);
		
		lblNewLabel = new JLabel("Number of Sections below fill percent");
		lblNewLabel.setBounds(100, 270, 300, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField  hours= new JTextField();
		hours.setText(""+lesscounter);
		hours.setBounds(450, 268, 50, 25);
		currentGUIFrame.add(hours);
		
		
		
//		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6"},
//		                       { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6"} };
//		Object columnNames[] = { "Term", "Title", "Meeting Time","Faculty", "Availability","Occupancy"};
//		
//		if(scheduleList!=null && scheduleList.size()!=0){
//			rowData=new String[scheduleList.size()][6];
//			for(int count=0;count<scheduleList.size();count++){
//				int increment=0;
//				Schedule schedule=(Schedule)scheduleList.get(count);
//				if(schedule!=null){
//					rowData[count][increment]=schedule.getTerm();
//					increment=increment+1;
//					rowData[count][increment]=schedule.getSectionAndTitle();
//					increment=increment+1;
//					rowData[count][increment]=schedule.getMeetingInformation();
//					increment=increment+1;
//					rowData[count][increment]=schedule.getFaculty();
//					increment=increment+1;
//					rowData[count][increment]=schedule.getAvailability();
//					increment=increment+1;
//					rowData[count][increment]=schedule.getOccupancy();
//				}				
//			}
//		}
//		JTable table = new JTable(rowData, columnNames);
//		JScrollPane scroll = new JScrollPane(table);
//		scroll.setBounds(10, 175, 600, 250);
//		currentGUIFrame.add(scroll);
		
		back = new JButton("Back");
		back.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new SchedulePanel(currentGUIFrame,null));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		back.setBounds(100, 400, 125, 30);
		currentGUIFrame.add(back);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(450, 400, 100, 30);
		currentGUIFrame.add(exitButton);
	}	
}
