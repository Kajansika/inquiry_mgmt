package com; 
import java.sql.*;

public class Inquiry 
{ 	//A common method to connect to the DB
	private Connection connect() 
	{ 
		Connection con = null; 
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/details", "root", "");
			// For testing
			System.out.print("Successfully connected");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return con;
	}

	public String insertInq(String Subject, String fullName, String accountNo, String inquiryID) 
			 { 
			 String output = ""; 
			 try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database";
					}
					// create a prepared statement
					String query = " insert into inquiry(`PID`,`Subject`,`fullName`,`accountNo`,`inquiryID`)"
							+ " values (?, ?, ?, ?, ?)";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, 0);
					preparedStmt.setString(2, Subject);
					preparedStmt.setString(3, fullName);
					preparedStmt.setDouble(4, Double.parseDouble(accountNo));
					preparedStmt.setString(5, inquiryID);

					// execute the statement
					preparedStmt.execute();
					con.close();
					String newInq = readInqe(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newInq + "\"}";
					output = "Inserted successfully";
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while inserting the items.\"}"; 
					 System.err.println(e.getMessage());
				}
				return output;
			} 
			
	public String readInqe()
	{ 
	 String output = ""; 
	 try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for reading.";
			}
			// Prepare the html table to be displayed
			output = "<table border='1'><tr><th>Subject</th>" + "<th>fullName</th><th>accountNo</th>"
					+ "<th>inquiryID</th>" + "<th>Update</th><th>Remove</th></tr>";
			String query = "select * from inquiry";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			// iterate through the rows in the result set
			while (rs.next()) {
				String PID = Integer.toString(rs.getInt("PID"));
				String Subject = rs.getString("Subject");
				String fullName = rs.getString("fullName");
				String accountNo = Double.toString(rs.getDouble("accountNo"));
				String inquiryID = rs.getString("inquiryID");
				// Add a row into the html table
				output += "<tr><td>" + Subject + "</td>";
				output += "<td>" + fullName + "</td>";
				output += "<td>" + accountNo + "</td>";

				output += "<td>" +inquiryID + "</td>";
	// buttons
	output += "<td><input name='btnUpdate' type='button' value='Update' "
	+ "class='btnUpdate btn btn-secondary' data-pid='" + PID + "'></td>"
	+ "<td><input name='btnRemove' type='button' value='Remove' "
	+ "class='btnRemove btn btn-danger' data-pid='" + PID + "'></td></tr>"; 
	 } 
	 con.close(); 
	 // Complete the html table
	 output += "</table>"; 
	 } 
	catch (Exception e) 
	 { 
	 output = "Error while reading the items."; 
	 System.err.println(e.getMessage()); 
	 } 
	return output; 
	}
		
	public String updateInq(String ID, String Subject, String fullName, String accountNo, String inquiryID) 
			 { 
			 String output = ""; 
			 try {
					Connection con = connect();
					if (con == null) {
						return "Error while connecting to the database for updating.";
					}
					// create a prepared statement
					String query = "UPDATE inquiry SET Subject=?,fullName=?,accountNo=?,inquiryID=? WHERE PID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setString(1, Subject);
					preparedStmt.setString(2, fullName);
					preparedStmt.setDouble(3, Double.parseDouble(accountNo));
					preparedStmt.setString(4, inquiryID);
					preparedStmt.setInt(5, Integer.parseInt(ID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					String newPay = readInqe(); 
					 output = "{\"status\":\"success\", \"data\": \"" + 
					 newPay + "\"}"; 
				} catch (Exception e) {
					output = "{\"status\":\"error\", \"data\": \"Error while updating the items.\"}"; 
					 System.err.println(e.getMessage()); 
				}
				return output;
			}
			 
	public String deleteInq(String PID) 
	 { 
	 String output = ""; 
	 try {
			Connection con = connect();
			if (con == null) {
				return "Error while connecting to the database for deleting.";
			}
			// create a prepared statement
			String query = "delete from inquiry where PID=?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			// binding values
			preparedStmt.setInt(1, Integer.parseInt(PID));
			// execute the statement
			preparedStmt.execute();
			con.close();
			String newInq = readInqe(); 
			 output = "{\"status\":\"success\", \"data\": \"" + 
			 newInq + "\"}"; 
		} catch (Exception e) {
			output = "{\"status\":\"error\", \"data\": \"Error while deleting the items.\"}"; 
			 System.err.println(e.getMessage());
		}
		return output;
	}
} 