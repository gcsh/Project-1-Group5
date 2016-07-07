package main;

import java.util.ArrayList;

public class SessionManager {
	private String userRole;
	private ArrayList studentList;
	private ArrayList professorList;
	private ArrayList courseList;
	private ArrayList degreeList;
	private ArrayList semesterList;
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public ArrayList getStudentList() {
		return studentList;
	}
	public void setStudentList(ArrayList studentList) {
		this.studentList = studentList;
	}
	public ArrayList getProfessorList() {
		return professorList;
	}
	public void setProfessorList(ArrayList professorList) {
		this.professorList = professorList;
	}
	public ArrayList getCourseList() {
		return courseList;
	}
	public void setCourseList(ArrayList courseList) {
		this.courseList = courseList;
	}
	public ArrayList getDegreeList() {
		return degreeList;
	}
	public void setDegreeList(ArrayList degreeList) {
		this.degreeList = degreeList;
	}
	public ArrayList getSemesterList() {
		return semesterList;
	}
	public void setSemesterList(ArrayList semesterList) {
		this.semesterList = semesterList;
	}
}
