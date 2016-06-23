package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import beans.Professor;
import beans.Schedule;
import main.LoginHome;

public class SchedulePanel  extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,generateSchedule,testSchedule;
	public SchedulePanel(final JFrame currentGUIFrame,ArrayList scheduleList) {
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
		lblNewLabel = new JLabel("View Schedule");
		lblNewLabel.setBounds(250, 30, 200, 20);
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
		
		generateSchedule = new JButton("Generate Schedule");
		generateSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		generateSchedule.setBounds(10, 125, 175, 30);
		currentGUIFrame.add(generateSchedule);
		
		testSchedule = new JButton("Test Schedule");
		testSchedule.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new TestSchedule(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		testSchedule.setBounds(250, 125, 175, 30);
		currentGUIFrame.add(testSchedule);
		
		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6", "Row1-Column7","Row1-Column8"},
		                       { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row1-Column4", "Row1-Column5", "Row1-Column6", "Row1-Column7","Row1-Column8"} };
		Object columnNames[] = { "Term", "Status", "Title","Meeting Time","Faculty", "Availability","Credits","Academics"};
		
		if(scheduleList!=null && scheduleList.size()!=0){
			rowData=new String[scheduleList.size()][8];
			for(int count=0;count<scheduleList.size();count++){
				int increment=0;
				Schedule schedule=(Schedule)scheduleList.get(count);
				if(schedule!=null){
					rowData[count][increment]=schedule.getTerm();
					increment=increment+1;
					rowData[count][increment]=schedule.getStatus();
					increment=increment+1;
					rowData[count][increment]=schedule.getSectionAndTitle();
					increment=increment+1;
					rowData[count][increment]=schedule.getMeetingInformation();
					increment=increment+1;
					rowData[count][increment]=schedule.getFaculty();
					increment=increment+1;
					rowData[count][increment]=schedule.getAvailability();
					increment=increment+1;
					rowData[count][increment]=schedule.getCredits();
					increment=increment+1;
					rowData[count][increment]=schedule.getAcademicLevel();
				}				
			}
		}
		JTable table = new JTable(rowData, columnNames);
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 175, 600, 300);
		currentGUIFrame.add(scroll);
	}
}
