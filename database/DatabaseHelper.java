package Database;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;
import beans.Course;
import beans.DegreePlan;
import beans.Degrees;
import beans.Professor;
import beans.Semester;
import beans.Student;

public class DatabaseHelper {
	public LinkedHashMap getAllStudentDataFromStudentDump(){
		LinkedHashMap hashMap=new LinkedHashMap();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\Database\\STU.DUMP.CSV"));
			List<String[]> allStudentsData=inputReader.readAll();
			for(String[] student : allStudentsData){
				if(student!=null && student.length!=0 && student[0]!=null && student[1]!=null){
					hashMap.put(student[0].trim(), student[1].trim());
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return hashMap;
	}
	
	public LinkedHashMap getAllStudentDataFromStudentDump(String filePath){
		LinkedHashMap hashMap=new LinkedHashMap();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(filePath));
			List<String[]> allStudentsData=inputReader.readAll();
			for(String[] student : allStudentsData){
				if(student!=null && student.length!=0 && student[0]!=null && student[1]!=null){
					hashMap.put(student[0].trim(), student[1].trim());
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return hashMap;
	}
	
	public LinkedHashMap getAllStudentCourseDataFromStudentCourseDump(){
		LinkedHashMap studentCourseMap=new LinkedHashMap();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\Database\\STC.DUMP.CSV"));
			List<String[]> allStudentCoursesData=inputReader.readAll();
			for(String[] studentcourse : allStudentCoursesData){
				if(studentcourse!=null && studentcourse.length!=0 && studentcourse[0]!=null && studentcourse[2]!=null){
					if(studentCourseMap.containsKey(studentcourse[0].trim())){
						ArrayList studentCourseList=(ArrayList)studentCourseMap.get(studentcourse[0].trim());
						if(studentCourseList!=null && studentCourseList.size()!=0){
							Student student=new Student();
							student.setStudentId(studentcourse[0].trim());
							student.setCourseCode(studentcourse[1].trim());
							student.setCourseName(studentcourse[2].trim());
							studentCourseList.add(student);
							studentCourseMap.put(studentcourse[0].trim(), studentCourseList);
						}
					}else{
						ArrayList studentCourseList=new ArrayList();
						Student student=new Student();
						student.setStudentId(studentcourse[0].trim());
						student.setCourseCode(studentcourse[1].trim());
						student.setCourseName(studentcourse[2].trim());
						studentCourseList.add(student);
						studentCourseMap.put(studentcourse[0].trim(), studentCourseList);
					}
					
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return studentCourseMap;
	}
	
	public LinkedHashMap getAllStudentCourseDataFromStudentCourseDump(String fileName){
		LinkedHashMap studentCourseMap=new LinkedHashMap();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(fileName));
			List<String[]> allStudentCoursesData=inputReader.readAll();
			for(String[] studentcourse : allStudentCoursesData){
				if(studentcourse!=null && studentcourse.length!=0 && studentcourse[0]!=null && studentcourse[2]!=null){
					if(studentCourseMap.containsKey(studentcourse[0].trim())){
						ArrayList studentCourseList=(ArrayList)studentCourseMap.get(studentcourse[0].trim());
						if(studentCourseList!=null && studentCourseList.size()!=0){
							Student student=new Student();
							student.setStudentId(studentcourse[0].trim());
							student.setCourseCode(studentcourse[1].trim());
							student.setCourseName(studentcourse[2].trim());
							studentCourseList.add(student);
							studentCourseMap.put(studentcourse[0].trim(), studentCourseList);
						}
					}else{
						ArrayList studentCourseList=new ArrayList();
						Student student=new Student();
						student.setStudentId(studentcourse[0].trim());
						student.setCourseCode(studentcourse[1].trim());
						student.setCourseName(studentcourse[2].trim());
						studentCourseList.add(student);
						studentCourseMap.put(studentcourse[0].trim(), studentCourseList);
					}
					
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return studentCourseMap;
	}
	
	public ArrayList getAllStudentCourses(String fileName){
		ArrayList studentCourseList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(fileName));
			List<String[]> allStudentCoursesData=inputReader.readAll();
			for(String[] studentcourse : allStudentCoursesData){
				if(studentcourse!=null && studentcourse.length!=0 && studentcourse[0]!=null && studentcourse[2]!=null){
					Student student=new Student();
					student.setStudentId(studentcourse[0].trim());
					student.setCourseCode(studentcourse[1].trim());
					student.setCourseName(studentcourse[2].trim());
					student.setSemester(studentcourse[3].trim());
					studentCourseList.add(student);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return studentCourseList;
	}
	
	public ArrayList getCoursesDataFromTestCourse(){
		ArrayList courseList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\Database\\TestDataCourses.csv"));
			List<String[]> allCoursesData=inputReader.readAll();
			for(String[] courseData : allCoursesData){
				if(courseData!=null && courseData.length!=0){
					Course course=new Course();
					course.setCourseCode(courseData[0].trim());
					course.setCourseName(courseData[1].trim());
					course.setCourseDescription(courseData[2].trim());
					course.setCourseHours(courseData[3].trim());
					course.setCourseCap(courseData[4].trim());
					course.setOfferedFall(courseData[5].trim());
					course.setOfferedSpring(courseData[6].trim());
					course.setOfferedSummer(courseData[7].trim());
					course.setCoursePrereqs(courseData[8].trim());
					course.setTeaches(courseData[9].trim());
					courseList.add(course);
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return courseList;
	}
	
	public ArrayList getCoursesDataFromTestCourse(String fileName){
		ArrayList courseList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(fileName));
			List<String[]> allCoursesData=inputReader.readAll();
			for(String[] courseData : allCoursesData){
				if(courseData!=null && courseData.length!=0){
					Course course=new Course();
					course.setCourseCode(courseData[0].trim());
					course.setCourseName(courseData[1].trim());
					course.setCourseDescription(courseData[2].trim());
					course.setCourseHours(courseData[3].trim());
					course.setCourseCap(courseData[4].trim());
					course.setOfferedFall(courseData[5].trim());
					course.setOfferedSpring(courseData[6].trim());
					course.setOfferedSummer(courseData[7].trim());
					course.setCoursePrereqs(courseData[8].trim());
					course.setTeaches(courseData[9].trim());
					courseList.add(course);
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return courseList;
	}
	
	public ArrayList getFacultiesDataFromTestFaculty(){
		ArrayList professorList=new ArrayList();
		try{			
			CSVReader inputReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\Database\\TestDataFaculty.csv"));
			List<String[]> allProfessorsData=inputReader.readAll();
			for(String[] professorData : allProfessorsData){
				if(professorData!=null && professorData.length!=0){
					Professor professor=new Professor();
					professor.setFirstName(professorData[0].trim());
					professor.setLastName(professorData[1].trim());
					professor.setGradSchool(professorData[2].trim());
					professor.setDegree(professorData[3].trim());
					professor.setTitle(professorData[4].trim());
					professor.setDaysToTeach(professorData[5].trim());
					professor.setMaxLoadFall(professorData[6].trim());
					professor.setMaxLoadSpring(professorData[7].trim());
					professor.setMaxLoadSummer(professorData[8].trim());
					professorList.add(professor);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return professorList;
	}
	
	public ArrayList getFacultiesDataFromTestFaculty(String fileName){
		ArrayList professorList=new ArrayList();
		try{			
			CSVReader inputReader = new CSVReader(new FileReader(fileName));
			List<String[]> allProfessorsData=inputReader.readAll();
			for(String[] professorData : allProfessorsData){
				if(professorData!=null && professorData.length!=0){
					Professor professor=new Professor();
					professor.setFirstName(professorData[0].trim());
					professor.setLastName(professorData[1].trim());
					professor.setGradSchool(professorData[2].trim());
					professor.setDegree(professorData[3].trim());
					professor.setTitle(professorData[4].trim());
					professor.setDaysToTeach(professorData[5].trim());
					professor.setMaxLoadFall(professorData[6].trim());
					professor.setMaxLoadSpring(professorData[7].trim());
					professor.setMaxLoadSummer(professorData[8].trim());
					professorList.add(professor);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		return professorList;
	}
	
	public ArrayList getSemesterDataFromTestFaculty(){
		ArrayList semesterList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\Database\\TestDataSemesters.csv"));
			List<String[]> allSemesterData=inputReader.readAll();
			for(String[] semesterData : allSemesterData){
				if(semesterData!=null && semesterData.length!=0){
					Semester semester=new Semester();
					semester.setSemesterName(semesterData[0].trim());
					semester.setStartDate(semesterData[1].trim());
					semester.setEndDate(semesterData[2].trim());
					semesterList.add(semester);
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return semesterList;
	}
	
	public ArrayList getSemesterDataFromTestFaculty(String fileName){
		ArrayList semesterList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(fileName));
			List<String[]> allSemesterData=inputReader.readAll();
			for(String[] semesterData : allSemesterData){
				if(semesterData!=null && semesterData.length!=0){
					Semester semester=new Semester();
					semester.setSemesterName(semesterData[0].trim());
					semester.setStartDate(semesterData[1].trim());
					semester.setEndDate(semesterData[2].trim());
					semesterList.add(semester);
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return semesterList;
	}
	
	public ArrayList getDegreesDataFromTestFaculty(){
		ArrayList degreeList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegrees.csv"));
			List<String[]> allDegreeData=inputReader.readAll();
			for(String[] degreeData : allDegreeData){
				if(degreeData!=null && degreeData.length!=0){
					Degrees degree=new Degrees();
					degree.setDegreeCode(degreeData[0].trim());
					degree.setGradSchool(degreeData[1].trim());
					degree.setDegreeName(degreeData[2].trim());
					degree.setForecast(degreeData[3].trim());
					degreeList.add(degree);
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return degreeList;
	}
	
	public ArrayList getDegreesDataFromTestFaculty(String fileName){
		ArrayList degreeList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(fileName));
			List<String[]> allDegreeData=inputReader.readAll();
			for(String[] degreeData : allDegreeData){
				if(degreeData!=null && degreeData.length!=0){
					Degrees degree=new Degrees();
					degree.setDegreeCode(degreeData[0].trim());
					degree.setGradSchool(degreeData[1].trim());
					degree.setDegreeName(degreeData[2].trim());
					degree.setForecast(degreeData[3].trim());
					degreeList.add(degree);
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return degreeList;
	}
	
	public ArrayList getDegreePlanDataFromTestFaculty(){
		ArrayList degreeList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(System.getProperty("user.dir")+"\\src\\Database\\TestDataDegreePlanReq.csv"));
			List<String[]> allDegreeData=inputReader.readAll();
			for(String[] degreeData : allDegreeData){
				if(degreeData!=null && degreeData.length!=0){
					DegreePlan degreePlan=new DegreePlan();
					degreePlan.setDegreeCode(degreeData[0].trim());
					degreePlan.setDescription(degreeData[1].trim());
					degreePlan.setHours(degreeData[2].trim());
					degreePlan.setType(degreeData[3].trim());
					degreePlan.setCourses(degreeData[4].trim());
					degreeList.add(degreePlan);
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return degreeList;
	}
	
	public ArrayList getDegreePlanDataFromTestFaculty(String fileName){
		ArrayList degreeList=new ArrayList();
		try{
			CSVReader inputReader = new CSVReader(new FileReader(fileName));
			List<String[]> allDegreeData=inputReader.readAll();
			for(String[] degreeData : allDegreeData){
				if(degreeData!=null && degreeData.length!=0){
					DegreePlan degreePlan=new DegreePlan();
					degreePlan.setDegreeCode(degreeData[0].trim());
					degreePlan.setDescription(degreeData[1].trim());
					degreePlan.setHours(degreeData[2].trim());
					degreePlan.setType(degreeData[3].trim());
					degreePlan.setCourses(degreeData[4].trim());
					degreeList.add(degreePlan);
				}
			}			
		}catch (Exception e){
			e.printStackTrace();
		}
		return degreeList;
	}
}
