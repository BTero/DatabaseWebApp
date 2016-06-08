package com.brtero.web.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

public class StudentDbUtil {
	private DataSource dataSource;
	
	public StudentDbUtil(DataSource theDataSource){
		this.dataSource = theDataSource;
	}
	
	public List<Student> getStudents() throws Exception{
		List<Student> students = new ArrayList<>();
		
		Connection myConn = null;
		Statement myStmt = null;
		ResultSet myRs = null;
		
		try{
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from student order by last_name";			
			myStmt = myConn.createStatement();
			
			myRs = myStmt.executeQuery(sql);
			
			while(myRs.next()){
				int id = myRs.getInt("id");
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				students.add(new Student(id, firstName, lastName, email));
			}
			
			return students;
		}finally{
			close(myConn, myStmt, myRs);
		}
	}
	
	private void close(Connection myConn, Statement myStmt, ResultSet myRs) {
		try{
			if(myRs != null){
				myRs.close();
			}
			if(myStmt != null){
				myStmt.close();
			}
			if(myConn != null){
				myConn.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public void addStudent(Student student) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			// create sql insert
			myConn = dataSource.getConnection();
			String sql = "insert into student "
					+ "(first_name, last_name, email) "
					+ "values(?, ?, ?)";
			myStmt = myConn.prepareStatement(sql);
			
			// set the param values of the student
			myStmt.setString(1, student.getFirstName());
			myStmt.setString(2, student.getLastName());
			myStmt.setString(3, student.getEmail());
			
			// execute sql
			myStmt.execute();
		}finally{
			// clean up JDBC objects
			close(myConn, myStmt, null);
		}
	}
	
	public Student getStudent(String theStudentId) throws Exception{
		Student student = null;
		
		Connection myConn = null;
		PreparedStatement myStmt = null;
		ResultSet myRs = null;
		int studentId;
		
		try{
			studentId = Integer.parseInt(theStudentId);
			
			myConn = dataSource.getConnection();
			
			String sql = "select * from student where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setInt(1, studentId);
			
			myRs = myStmt.executeQuery();
			
			if(myRs.next()){
				String firstName = myRs.getString("first_name");
				String lastName = myRs.getString("last_name");
				String email = myRs.getString("email");
				
				student = new Student(studentId, firstName, lastName, email);
			}else{
				throw new Exception("Could not find student id: " + studentId);
			}
			return student;
		}finally{
			close(myConn, myStmt, myRs);
		}
	}
	
	public void updateStudent(Student theStudent) throws Exception{
		Connection myConn = null;
		PreparedStatement myStmt = null;
		
		try{
			myConn = dataSource.getConnection();
			
			String sql = "update student "
					+ "set first_name=?, last_name=?, email=? "
					+ "where id=?";
			
			myStmt = myConn.prepareStatement(sql);
			myStmt.setString(1, theStudent.getFirstName());
			myStmt.setString(2, theStudent.getLastName());
			myStmt.setString(3, theStudent.getEmail());
			myStmt.setInt(4, theStudent.getId());
			
			myStmt.execute();
			
		}finally{
			close(myConn, myStmt, null);
		}
	}
}











