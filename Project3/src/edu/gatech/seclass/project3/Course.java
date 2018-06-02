package edu.gatech.seclass.project3;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class Course {

	String DB = null;
	Students students = null;
	Grades grades = null;
	String formula = "ATT * 0.2 + AVGA * 0.4 + AVGP * 0.4";
	public Course(String db) {
		DB = db;		
		students = new Students(DB);
		grades = new Grades(DB,students.StudentRoster,students.numTeam);
	}

	public int getNumStudents() {

		return students.StudentRoster.size();		
	}

	public int getNumAssignments() {
		return grades.numAssignments;
	}
	
	public int getNumProjects() {
		return grades.numProjects;
	}

	public HashSet<Student> getStudents() {
		return students.StudentRoster;
	}

	public Student getStudentByName(String string) {
		Student student = new Student();
		student = students.getByName(string);
		return student;
	}

	public Student getStudentByID(int i) {
		Student student = new Student();
		student = students.getByID(i);
		return student;
	}

	public void addAssignment(String string) {
		grades.addAssignment(string);
		
	}

	public void updateGrades(Grades grades2) {	
		grades = grades2;
	}

	public void addGradesForAssignment(String assignmentName,
			HashMap<Student, Integer> grades2) {
		Set<Student> students = grades2.keySet();
		for(Student student : students){
			int grade = grades2.get(student);
			Student s = getStudentByID(student.getGtid());
			grades.setAssignments(s, assignmentName, grade);
		}
	}
	
	public int getAverageAssignmentsGrade(Student student1) {
		float average = 0;
		int num = 0;
		Student student = getStudentByID(student1.getGtid());
		for(int i:student.assignments){
			average += i;
			num++;
		}
		average = Math.round((float)average/num);
		return (int)average;
	}

	public int getAverageProjectsGrade(Student student) {
		float average = 0;
		int num = 0;
		Student s = getStudentByID(student.getGtid());
		int team = Integer.parseInt(s.getTeam().split("\\s")[1]);
		for(int rank:s.projects){
			average += (float)grades.teamProjects[team-1][num]*rank/100;
			num++;
		}
		
		average = Math.round(average/num);
		return (int)average;
	}

	public void addIndividualContributions(String projectName1,
			HashMap<Student, Integer> contributions1) {
		Set<Student> students = contributions1.keySet();
		for(Student student : students){
			int contribution = contributions1.get(student);
			Student s = getStudentByID(student.getGtid());
			grades.setContribution(s, projectName1, contribution);
		}	
	}

	public void addStudents(HashSet<Student> students2) {
		for(Student s:students2){
			students.addStudent(s);
			grades.addStudent(s);
		}
		
	}

	public void addProject(String projectName) {
		grades.addProject(projectName);
		
	}

	public void addGradesForProject(String projectName,
			HashMap<Student, Integer> grades2) {
		Set<Student> students = grades2.keySet();
		for(Student s : students){
			int grade = grades2.get(s);
			grades.setProjects(s, projectName, grade);
		}
		
	}

	public String getFormula() {
		
		return formula;
	
	}

	public String getEmail(Student student) {
		Student s = getStudentByID(student.getGtid());
		
		return s.getEmail();
	}

	public int getAttendance(Student student) {
		int attendance = 0;
		Student s = getStudentByID(student.getGtid());
		attendance = s.getAttendance();
		return attendance;
	}

	public void setFormula(String text) {
		formula = text;
		
	}

	public int getOverallGrade(Student student) throws GradeFormulaException {
			ScriptEngineManager manager = new ScriptEngineManager();
			ScriptEngine engine = manager.getEngineByName("javascript");
			try{
				engine.put("ATT", getAttendance(student));
				engine.put("AVGA", getAverageAssignmentsGrade(student));
			    engine.put("AVGP", getAverageProjectsGrade(student));
			    engine.eval("var output = " + formula + ";");
			    String output = engine.get("output").toString();
			    Integer overall = Math.round(Float.parseFloat(output));
			    return overall;
			}catch (ScriptException e){
				e.printStackTrace();
				throw new GradeFormulaException("Illegal Formula");
			}
	}

	public String getTeam(Student student) {
		Student s = getStudentByID(student.getGtid());
		return s.getTeam();
	}

}
