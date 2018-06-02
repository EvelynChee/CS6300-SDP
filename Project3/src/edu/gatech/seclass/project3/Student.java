package edu.gatech.seclass.project3;

class Student {
	int GT_ID;
	String name;
	int attendance;
	String team;
	int[] assignments;
	int[] projects;
	Course course;
	String email;
	/*public Student(String string, int i, Course course) {
		super();
		this.course = course; 
		Student student = course.getStudentByID(i);
		GT_ID = i;
		name = string;
		attendance = student.getAttendance();
		team = student.getTeam();
		assignments = student.assignments;
		projects = student.projects;
	}*/
	
	public Student(){
		super();
	}
	public Student(String string, int i) {
		super();
		GT_ID = i;
		name = string;
	}

	public String getName() {
		return name;
	}
	public int getGtid() {
		return GT_ID;
	}
	public int getAttendance() {
		return attendance;
	}
	public String getTeam() {
		return team;
	}
	public void setName(String stringCellValue) {
		name = stringCellValue;
		
	}
	public void setGtid(double d) {
		GT_ID = (int)d;
		
	}
	public void setAttendance(int stringCellValue) {
		attendance = stringCellValue;
		
	}
	public void setTeam(String stringCellValue) {
		team = stringCellValue;
		
	}
	public String getEmail() {
		
		return email;
	}
	public void setEmail(String string) {
		email = string;
	}
	
	
}
