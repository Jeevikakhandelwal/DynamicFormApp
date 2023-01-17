package com.abc.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.abc.dto.Student;

public class EditService {

	private static final String SQL_SELECT_QUERY = "select sid, sname, saddress from Students where sid=?";

	Connection connection = null;
	PreparedStatement pstmt = null;
	Statement statement=null;
	ResultSet resultSet = null;

	public EditService() {
		try {
			// JVM present in server will not support autoloading so manually load the
			// driver class
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql:///personinfo", "root", "root");
			statement = connection.createStatement();
			pstmt = connection.prepareStatement(SQL_SELECT_QUERY);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	//This method would get student object based on sid
	public Student getSutdent(Integer sid) {
		try {
			pstmt.setInt(1, sid);
			resultSet = pstmt.executeQuery();
			if (resultSet.next()) {

				// Write a logic of converting resultSet object to student and send it to the
				// controller
				Student student = new Student();
				student.setSid(sid);
				student.setSname(resultSet.getString(2));
				student.setSaddress(resultSet.getString(3));

				return student;
			} else {
					return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	//This method will update the information based on sid
	public boolean updateStudent(Student student) {
		String SQL_UPDATE_QUERY="update Students set sname='%s',saddress='%s' where sid='%s'";
		
		try {
			int rowCount = statement.executeUpdate(
					String.format(SQL_UPDATE_QUERY, student.getSname(), student.getSaddress(), student.getSid()));
			if (rowCount > 0) {
				return true;
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
	}
}
