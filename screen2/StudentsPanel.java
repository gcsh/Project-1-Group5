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
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import main.LoginHome;
import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVWriter;
import beans.Professor;
import beans.Student;

public class StudentsPanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,addNewStudent,deleteButton,exitButton,editButton;
	private String selectedIndex;
	public StudentsPanel(final JFrame currentGUIFrame) {
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
		lblNewLabel = new JLabel("View Students");
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
		
		addNewStudent = new JButton("Add New Student");
		addNewStudent.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new AddStudentPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		addNewStudent.setBounds(10, 90, 175, 30);
		currentGUIFrame.add(addNewStudent);
		
		Object rowData[][] = { { "Row1-Column1", "Row1-Column2", "Row1-Column3", "Row1-Column4"},
		                       { "Row2-Column1", "Row2-Column2", "Row2-Column3", "Row1-Column4"} };
		Object columnNames[] = { "Student ID", "Course Code", "Course Name", "Semester"};
		
		DatabaseHelper helper=new DatabaseHelper();
		ArrayList studentCourseList=helper.getAllStudentCourses();
		if(studentCourseList!=null && studentCourseList.size()!=0){
			rowData=new String[studentCourseList.size()][4];
			for(int count=0;count<studentCourseList.size();count++){
				int increment=0;
				Student student=(Student)studentCourseList.get(count);
				if(student!=null){
					rowData[count][increment]=student.getStudentId();
					increment=increment+1;
					rowData[count][increment]=student.getCourseCode();
					increment=increment+1;
					rowData[count][increment]=student.getCourseName();
					increment=increment+1;
					rowData[count][increment]=student.getSemester();
				}				
			}
		}
		final JTable table = new JTable(rowData, columnNames);
		table.setRowSelectionAllowed(true);
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
	    	public void valueChanged(ListSelectionEvent e) {
	    		String selectedData = null;
		        if(table.getSelectedRow() > -1){
		        	selectedIndex=""+table.getSelectedRow();		        	
		        }
	    	}
	    });
		JScrollPane scroll = new JScrollPane(table);
		scroll.setBounds(10, 150, 600, 300);
		currentGUIFrame.add(scroll);
		
		editButton = new JButton("Edit");
		editButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedIndex!=null && selectedIndex.trim().length()!=0){
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new EditStudentPanel(currentGUIFrame,selectedIndex));
					currentGUIFrame.getContentPane().repaint();
					selectedIndex="";
				}else{
					JOptionPane.showMessageDialog(editButton, "Please select one table row");
				}
			}
		});
		editButton.setBounds(10, 475, 100, 30);
		currentGUIFrame.add(editButton);
		
		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(selectedIndex!=null && selectedIndex.trim().length()!=0){
					DatabaseHelper helper=new DatabaseHelper();
					ArrayList studentCourseList=helper.getAllStudentCourses();
					studentCourseList.remove(Integer.parseInt(selectedIndex));				
					try{
						CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\STC.DUMP.CSV"));
						Iterator iterator=studentCourseList.iterator();
						while(iterator.hasNext()){
							Student student=(Student)iterator.next();
							String [] record = {student.getStudentId(),student.getCourseCode(),student.getCourseName(),student.getSemester(),student.getGrade()};
							writer.writeNext(record);
						}					
						writer.close();
						currentGUIFrame.getContentPane().removeAll();
						currentGUIFrame.getContentPane().add(new StudentsPanel(currentGUIFrame));
						currentGUIFrame.getContentPane().repaint();
					}catch(Exception ex){
						ex.printStackTrace();
					}
				}else{
					JOptionPane.showMessageDialog(editButton, "Please select one table row");
				}
			}
		});
		deleteButton.setBounds(115, 475, 100, 30);
		currentGUIFrame.add(deleteButton);
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(225, 475, 100, 30);
		currentGUIFrame.add(exitButton);
	}
}
