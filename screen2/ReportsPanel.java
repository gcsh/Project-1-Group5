package Screens;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Database.DatabaseHelper;
import au.com.bytecode.opencsv.CSVWriter;
import beans.Course;
import beans.DegreePlan;
import beans.Professor;
import beans.Schedule;
import main.LoginHome;

public class ReportsPanel extends JPanel {
	private JButton logout;
	private JButton professors,students,degrees,subjects,rooms,reports,importData,schedule,download,studentReport,facultyReport,exitButton,degreePlanReport,listofsection;
	public ReportsPanel(final JFrame currentGUIFrame) {
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
		lblNewLabel = new JLabel("Reports");
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
		reports.setBackground(Color.gray);
		reports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentGUIFrame.getContentPane().removeAll();
				currentGUIFrame.getContentPane().add(new ReportsPanel(currentGUIFrame));
				currentGUIFrame.getContentPane().repaint();
			}
		});
		reports.setBounds(520, 55, 85, 30);
		currentGUIFrame.add(reports);
		
		download = new JButton("Schedule Report");
		download.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					DatabaseHelper helper=new DatabaseHelper();
					//LinkedHashMap studentDegreeMap=helper.getAllStudentDataFromStudentDump();
					LinkedHashMap studentDegreeMap=helper.getAllStudentDataFromStudentDumpYear("2016FA");
					LinkedHashMap studentCourseMap=helper.getAllStudentCourseDataMap();
					LinkedHashMap degreeCourseMap=helper.getDegreePlanData();
					LinkedHashMap courseFacultyMap=helper.getCoursesFacultyDump();
					LinkedHashMap faculty=helper.getFacultyLastNameDump();
					LinkedHashMap scheduleStudents=new LinkedHashMap();
					LinkedHashMap graduateStudents=new LinkedHashMap();
					
					for(int numofGraduates=0;numofGraduates<10;numofGraduates++){
						for (Object key : studentDegreeMap.keySet()) {
							if(((ArrayList)studentCourseMap.get(key))!=null && ((ArrayList)studentCourseMap.get(key)).size()==numofGraduates){
								scheduleStudents.put(key.toString(), (ArrayList)studentCourseMap.get(key));
							}else{
								scheduleStudents.put(key.toString(), new ArrayList());
							}
						}
					}
					for (Object key : scheduleStudents.keySet()) {
						String degreeName=(String)studentDegreeMap.get(key.toString());
						ArrayList coureses=(ArrayList)degreeCourseMap.get(degreeName);
						ArrayList studentCourses=(ArrayList)scheduleStudents.get(key.toString());
						int studiedRequired=0,studiedSecond=0,studiedThird=0;
						int firstStudyCourses=0,secondStudyCourse=0,thirdStudyCourse=0;
						ArrayList studentCoursesWillStudy=new ArrayList();
						if(coureses!=null){
							for(int i=0;i<coureses.size();i++){
								DegreePlan degreePlan=(DegreePlan)coureses.get(i);
								if(i==0){
									studiedRequired=Integer.parseInt(degreePlan.getHours())/3;								
								}
								if(i==1){
									studiedSecond=Integer.parseInt(degreePlan.getHours())/3;
								}
								if(i==2){
									studiedThird=Integer.parseInt(degreePlan.getHours())/3;
								}
							}
							for(int i=0;i<coureses.size();i++){
								DegreePlan degreePlan=(DegreePlan)coureses.get(i);
								if(i==0){
									String[] courses=degreePlan.getCourses().split(",");
									for(int index=0;index<courses.length;index++){
										if(studentCourses.contains(courses[index].trim())){
											firstStudyCourses++;
										}
									}
									if(firstStudyCourses==0){
										studentCoursesWillStudy.add(courses[0].trim());
										studentCoursesWillStudy.add(courses[1].trim());
									}else if(studiedRequired>firstStudyCourses){
										int counter=studiedRequired-firstStudyCourses;
										for(int index=0;index<courses.length;index++){
											if(!studentCourses.contains(courses[index].trim())){
												if(counter!=0){
													studentCoursesWillStudy.add(courses[index].trim());
													counter--;
												}
											}
										}
									}
								}
								if(i==1){
									String[] courses=degreePlan.getCourses().split(",");
									for(int index=0;index<courses.length;index++){
										if(studentCourses.contains(courses[index].trim())){
											secondStudyCourse++;
										}
									}
									if(secondStudyCourse==0){
										studentCoursesWillStudy.add(courses[0].trim());
									}else if(studiedSecond>secondStudyCourse){
										int counter=studiedSecond-secondStudyCourse;
										for(int index=0;index<courses.length;index++){
											if(!studentCourses.contains(courses[index].trim())){
												if(counter!=0){
													studentCoursesWillStudy.add(courses[index].trim());
													counter--;
												}
											}
										}
									}
								}
								if(i==2){
									String[] courses=degreePlan.getCourses().split(",");
									for(int index=0;index<courses.length;index++){
										if(studentCourses.contains(courses[index].trim())){
											thirdStudyCourse++;
										}
									}
									if(thirdStudyCourse==0){
										studentCoursesWillStudy.add(courses[0].trim());
									}else if(studiedThird>thirdStudyCourse){
										int counter=studiedThird-thirdStudyCourse;
										for(int index=0;index<courses.length;index++){
											if(!studentCourses.contains(courses[index].trim())){
												if(counter!=0){
													studentCoursesWillStudy.add(courses[index].trim());
													counter--;
												}
											}
										}
									}
								}
							}
							if(studentCoursesWillStudy!=null && studentCoursesWillStudy.size()<=4){
								graduateStudents.put(key.toString(), studentCoursesWillStudy);
							}
						}
					}
					LinkedHashMap schedulingMap=new LinkedHashMap();
					LinkedHashMap days=new LinkedHashMap();
					days.put("M","Monday");
					days.put("T","Tuesday");
					days.put("W","Wednesday");
					days.put("R","Thursday");
					days.put("F","Friday");
					try{
						CSVWriter stuwriter = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\studentreport.csv"));
						String [] stuRecordHeader = {"Term","Title","Time","Student"};
						stuwriter.writeNext(stuRecordHeader);					
						if(graduateStudents!=null && graduateStudents.size()!=0){
							LinkedHashMap semesterMap=helper.getSemesterDataMap();
							for (Object key : graduateStudents.keySet()) {
								ArrayList studentCoursesWillStudy =(ArrayList)graduateStudents.get(key.toString());
								for(int i=0;i<studentCoursesWillStudy.size();i++){
									Schedule schedule=new Schedule();
									String courseCode=(String)studentCoursesWillStudy.get(i);
									if(schedulingMap.containsKey(courseCode)){
										Schedule tempSchedule=(Schedule)schedulingMap.get(courseCode);
										Course course=(Course)courseFacultyMap.get(courseCode);
										tempSchedule.setWaitList(""+(Integer.parseInt(tempSchedule.getWaitList())+1));
										tempSchedule.setAvailability(course.getCourseCap()+" / "+tempSchedule.getWaitList());
										String[] stuRecord = {tempSchedule.getTerm(),tempSchedule.getSectionAndTitle(),tempSchedule.getMeetingInformation(),key.toString()};
										stuwriter.writeNext(stuRecord);	
										schedulingMap.put(courseCode,tempSchedule);
									}else{
										schedule.setSectionAndTitle(courseCode);
										Course course=(Course)courseFacultyMap.get(courseCode);
										schedule.setSectionAndTitle(schedule.getSectionAndTitle()+" "+course.getCourseName());
										schedule.setAcademicLevel("Graduate");
										schedule.setCredits("3");
										schedule.setWaitList("1");
										schedule.setStatus("Open");
										schedule.setCapacity("25");
										schedule.setTerm("2016FA");
										schedule.setAvailability(course.getCourseCap()+" / "+schedule.getWaitList());
										schedule.setFaculty(course.getTeaches());
										String[] stuRecord = {schedule.getTerm(),schedule.getSectionAndTitle(),schedule.getMeetingInformation(),key.toString()};
										stuwriter.writeNext(stuRecord);
										schedulingMap.put(courseCode,schedule);
									}
								}
							}					
						}
						stuwriter.close();
					}catch(Exception ex){
						ex.printStackTrace();
					}
					ArrayList scheduleList=new ArrayList();
					if(schedulingMap!=null && schedulingMap.size()!=0){
						LinkedHashMap facultyMap=helper.getFacultiesDataMap();		
						for (Object key : schedulingMap.keySet()) {
							Schedule scheduleData=(Schedule)schedulingMap.get(key.toString());
							if(Integer.parseInt(scheduleData.getWaitList())>=10){
//								if(scheduleData.getFaculty().indexOf(",")!=-1){
//									scheduleData.setFaculty(scheduleData.getFaculty().substring(0,scheduleData.getFaculty().indexOf(",")));
//								}
//								Professor professor=(Professor)facultyMap.get(scheduleData.getFaculty());
//								if(professor!=null && professor.getDaysToTeach()!=null){
//									scheduleData.setMeetingInformation((String)days.get((String)(""+professor.getDaysToTeach().charAt(0))));
//								}
//								scheduleList.add(scheduleData);
//								if(scheduleData.getFaculty().indexOf(",")!=-1){
//									scheduleData.setFaculty(scheduleData.getFaculty().substring(0,scheduleData.getFaculty().indexOf(",")));
//								}
//								Professor professor=(Professor)facultyMap.get(scheduleData.getFaculty());
//								if(professor!=null){
//									scheduleData.setMeetingInformation(scheduleData.getMeetingInformation()+",  "+professor.getDaysToTeach());
//								}
								if((Integer.parseInt(scheduleData.getWaitList())-35)>10){
									Schedule newScheduleData=new Schedule();
									newScheduleData.setTerm(scheduleData.getTerm());
									newScheduleData.setCapacity(scheduleData.getCapacity());
									newScheduleData.setCredits(scheduleData.getCredits());
									newScheduleData.setStatus(scheduleData.getStatus());
									newScheduleData.setSectionAndTitle(scheduleData.getSectionAndTitle());
									newScheduleData.setWaitList(""+(Integer.parseInt(scheduleData.getWaitList())-35));
									newScheduleData.setAvailability("25 / "+newScheduleData.getWaitList());
									newScheduleData.setFaculty(scheduleData.getFaculty());
									if(newScheduleData.getFaculty().indexOf(",")!=-1){
										newScheduleData.setFaculty(newScheduleData.getFaculty().substring(0,newScheduleData.getFaculty().indexOf(",")));
									}
									Professor professorData=(Professor)facultyMap.get(newScheduleData.getFaculty());
									if(professorData!=null && professorData.getDaysToTeach()!=null){
										newScheduleData.setMeetingInformation((String)days.get((String)(""+professorData.getDaysToTeach().charAt(0))));
									}
									scheduleList.add(newScheduleData);								
									scheduleData.setAvailability("25 / 35");
									if(scheduleData.getFaculty().indexOf(",")!=-1){
										scheduleData.setFaculty(scheduleData.getFaculty().substring(0,scheduleData.getFaculty().indexOf(",")));
									}
									professorData=(Professor)facultyMap.get(scheduleData.getFaculty());
									if(professorData!=null && professorData.getDaysToTeach()!=null){
										scheduleData.setMeetingInformation((String)days.get((String)(""+professorData.getDaysToTeach().charAt(0))));
									}
									scheduleList.add(scheduleData);
								}else{
									if(scheduleData.getFaculty().indexOf(",")!=-1){
										scheduleData.setFaculty(scheduleData.getFaculty().substring(0,scheduleData.getFaculty().indexOf(",")));
									}
									Professor professorData=(Professor)facultyMap.get(scheduleData.getFaculty());
									if(professorData!=null && professorData.getDaysToTeach()!=null){
										scheduleData.setMeetingInformation((String)days.get((String)(""+professorData.getDaysToTeach().charAt(0))));
									}
									scheduleList.add(scheduleData);
								}
							}					
						}	
					}
					
					if(scheduleList!=null && scheduleList.size()!=0){
						try{
							CSVWriter writer = new CSVWriter(new FileWriter(System.getProperty("user.dir")+"\\src\\Database\\facultyreport.csv"));
							String [] recordHeader = {"Term","Title","Time","Faculty"};
							writer.writeNext(recordHeader);
							for(int i=0;i<scheduleList.size();i++){
								Schedule schedule=(Schedule)scheduleList.get(i);
								String [] record = {schedule.getTerm(),schedule.getSectionAndTitle(),schedule.getMeetingInformation(),schedule.getFaculty()};
								writer.writeNext(record);
							}
							writer.close();						
						}catch(Exception ex){
							ex.printStackTrace();
						}
					}
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new ViewSchedulePanel(currentGUIFrame,scheduleList));
					currentGUIFrame.getContentPane().repaint();
				}catch (Exception ex){
					System.err.println(ex);
				}
			}
		});
		download.setBounds(50, 200, 150, 30);
		currentGUIFrame.add(download);
		
		studentReport = new JButton("Student-Course Report");
		studentReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new StudentReportPanel(currentGUIFrame));
					currentGUIFrame.getContentPane().repaint();
				}catch (Exception ex){
					System.err.println(ex);
				}
			}
		});
		studentReport.setBounds(225, 200, 175, 30);
		currentGUIFrame.add(studentReport);
		
		degreePlanReport = new JButton("Degree Plan Report");
		degreePlanReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new DegreePlanReport(currentGUIFrame));
					currentGUIFrame.getContentPane().repaint();
				}catch (Exception ex){
					System.err.println(ex);
				}
			}
		});
		degreePlanReport.setBounds(420, 200, 175, 30);
		currentGUIFrame.add(degreePlanReport);
		
		facultyReport = new JButton("Faculty Load Report");
		facultyReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new FacultyReport(currentGUIFrame));
					currentGUIFrame.getContentPane().repaint();
				}catch (Exception ex){
					System.err.println(ex);
				}
			}
		});
		facultyReport.setBounds(50, 250, 160, 30);
		currentGUIFrame.add(facultyReport);	
		
		listofsection = new JButton("List of Sections");
		listofsection.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try{
					currentGUIFrame.getContentPane().removeAll();
					currentGUIFrame.getContentPane().add(new ListOfSections(currentGUIFrame));
					currentGUIFrame.getContentPane().repaint();
				}catch (Exception ex){
					System.err.println(ex);
				}
			}
		});
		listofsection.setBounds(225, 250, 180, 30);
		currentGUIFrame.add(listofsection);	
		
		exitButton = new JButton("Exit");
		exitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});
		exitButton.setBounds(250, 350, 75, 30);
		currentGUIFrame.add(exitButton);
	}
}