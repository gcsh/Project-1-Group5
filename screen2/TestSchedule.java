package Screens;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import main.LoginHome;
import Database.DatabaseHelper;
import beans.Course;
import beans.Schedule;
import beans.Semester;
import beans.Student;

public class TestSchedule extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,download,browse;
	private String filePath,typeOfFile;
	public TestSchedule(final JFrame currentGUIFrame) {
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
		lblNewLabel = new JLabel("Test Schedule");
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
		
		lblNewLabel = new JLabel("Upload Files");
		lblNewLabel.setBounds(175, 100, 100, 20);
		currentGUIFrame.add(lblNewLabel);
		
		final JTextField textField = new JTextField();
		textField.setBounds(250, 100, 175, 25);
		//currentGUIFrame.add(textField);	
		
		download = new JButton("Generate Test Schedule");
		download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(filePath!=null && filePath.trim().length()!=0){
					LinkedHashMap studentDump=null,studentCourseDump=null;
					ArrayList courseList=null,facultyList=null,degreeList=null,degreePlanList=null,semesterList=null,studentCourseList=null; 
					DatabaseHelper helper=new DatabaseHelper();
					String[] fileNames=filePath.split(",");
					for(int i=0;i<fileNames.length;i++){
						if(fileNames[i].indexOf("STU.DUMP.CSV")!=-1){
							studentDump=helper.getAllStudentDataFromStudentDump(fileNames[i]);
						}
						if(fileNames[i].indexOf("STC.DUMP.CSV")!=-1){
							//studentCourseDump=helper.getAllStudentCourseDataFromStudentCourseDump(fileNames[i]);
							studentCourseList=helper.getAllStudentCourses(fileNames[i]);
						}
						if(fileNames[i].indexOf("TestDataCourses.csv")!=-1){
							courseList=helper.getCoursesDataFromTestCourse(fileNames[i]);
						}
						if(fileNames[i].indexOf("TestDataDegreePlanReq.csv")!=-1){
							degreePlanList=helper.getDegreePlanDataFromTestFaculty(fileNames[i]);
						}
						if(fileNames[i].indexOf("TestDataDegrees.csv")!=-1){
							degreeList=helper.getDegreesDataFromTestFaculty(fileNames[i]);
						}
						if(fileNames[i].indexOf("TestDataFaculty.csv")!=-1){
							facultyList=helper.getFacultiesDataFromTestFaculty(fileNames[i]);
						}
						if(fileNames[i].indexOf("TestDataSemesters.csv")!=-1){
							semesterList=helper.getSemesterDataFromTestFaculty(fileNames[i]);
						}
					}
					ArrayList scheduleList=new ArrayList();
					if(studentDump!=null && studentCourseList!=null && courseList!=null && facultyList!=null 
							&& degreeList!=null && degreePlanList!=null && semesterList!=null){
						for(int i=1;i<semesterList.size();i++){
							Semester semester=(Semester)semesterList.get(i);
							ArrayList subjectsList=new ArrayList();
							if(semester.getSemesterName().indexOf("SP")!=-1){
								for(int courseNum=1;courseNum<courseList.size();courseNum++){
									Course course=(Course)courseList.get(courseNum);
									if(course.getOfferedSpring().equalsIgnoreCase("Y")){
										subjectsList.add(course);
									}									
								}
							}else if(semester.getSemesterName().indexOf("SU")!=-1){
								for(int courseNum=1;courseNum<courseList.size();courseNum++){
									Course course=(Course)courseList.get(courseNum);
									if(course.getOfferedSummer().equalsIgnoreCase("Y")){
										subjectsList.add(course);
									}									
								}
							}else{
								for(int courseNum=1;courseNum<courseList.size();courseNum++){
									Course course=(Course)courseList.get(courseNum);
									if(course.getOfferedFall().equalsIgnoreCase("Y")){
										subjectsList.add(course);
									}									
								}
							}
							if(subjectsList!=null && subjectsList.size()!=0){
								for(int val=0;val<subjectsList.size();val++){
									Course course=(Course)subjectsList.get(val);
									Schedule schedule=new Schedule();
									schedule.setTerm(semester.getSemesterName());
									int year=Integer.parseInt(semester.getEndDate().substring(semester.getEndDate().lastIndexOf("/")+1));
									if(year>=2016){
										schedule.setStatus("Open");
									}else{
										schedule.setStatus("Closed");
									}
									schedule.setSectionAndTitle(course.getCourseCode()+" "+course.getCourseName());
									schedule.setFaculty(course.getTeaches());
									schedule.setMeetingInformation(semester.getStartDate()+" - "+semester.getEndDate());
									int counter=0;
									for(int count=0;count<studentCourseList.size();count++){
										Student student=(Student)studentCourseList.get(count);
										if(student.getCourseCode().equalsIgnoreCase(course.getCourseCode()) && student.getSemester().equalsIgnoreCase(semester.getSemesterName())){
											counter++;
										}										
									}
									schedule.setAvailability(course.getCourseCap()+" / "+counter);
									schedule.setAcademicLevel("Graduate");
									schedule.setCredits("3");
									scheduleList.add(schedule);
								}
							}
						}
					}
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new SchedulePanel(currentGUIFrame,scheduleList));
					currentGUIFrame.getContentPane().repaint();
				}
			}
		});
		download.setBounds(225, 200, 250, 30);
		currentGUIFrame.add(download);
		
		browse = new JButton("Browse");
		browse.addActionListener(new ActionListener(){
			 public void actionPerformed(ActionEvent ae){
				 JFileChooser fileChooser = new JFileChooser();
				 fileChooser.setMultiSelectionEnabled(true);
				 int returnValue = fileChooser.showOpenDialog(null);
				 if(returnValue == JFileChooser.APPROVE_OPTION){
//					 File selectedFile = fileChooser.getSelectedFile();
//					 textField.setText(selectedFile.getAbsolutePath());
//					 filePath=selectedFile.getAbsolutePath();
//					 textField.setEditable(false);					 
					 try {
						 ArrayList<String> FileData = new ArrayList<String>();
						 File[] file = fileChooser.getSelectedFiles();
						 for(int i=0;i<file.length;i++){  
				              textField.setText(textField.getText()+file[i].toString()+",");				              				              
						 }
						 filePath=textField.getText();
						 textField.setEditable(false);
					 }catch (Exception e){
						 JOptionPane.showMessageDialog(browse, "Please select correct files");
				     }
				 }
			 }
		 });
		browse.setBounds(425, 97, 100, 30);
		//currentGUIFrame.add(browse);
	}

}
