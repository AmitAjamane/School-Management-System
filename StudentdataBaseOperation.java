package com.eduSchool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StudentdataBaseOperation {
	static Connection conn ;
	private static Statement stmt;
	private static PreparedStatement pst;
	private static String sql;
	private static ResultSet rs;
	
	private static String name ,phone,address,gender;
	private static int id,age,std ;
	private static String dob;
	private static int updvar;
	
	
	public StudentdataBaseOperation() throws SQLException {
		super();
		
		for ( ;;) {
			System.out.println();
		System.out.println("******* WELCOME TO STUDENT INTERFACE ********");
		
		int ch ;
		Scanner sc = new Scanner (System.in);
		System.out.println();
		System.out.println("******** MENU*********");
		System.out.println("Enter your Choise ");
		System.out.println("1.Show Student details");
		System.out.println("2.Register New Student");
        System.out.println("3.Delete Student details");
        System.out.println("4.Update Student Details");
        System.out.println("5.Exit");
        System.out.println();
        
        ch=sc.nextInt();
        switch(ch)
        {
        case 1 :// Student details
        	showstudentDetails();
        	break;
        	
        case 2 :// Register Student
        	registerNewStudent();
        	break;
        	
        case 3 :// Delete Student details
        	deleteStudent();
        	break;
        	
        case 4 :// Update Student Details
        	updateStudent();
           break;
           
        case 5 : // exit 
        	System.out.println("Thank you ....!");
			MainSchoolManagementApp.main(null);// calling main class
        	System.exit(0);
        	break;
        	
		default :
			System.out.println("Invalid Choise ....! Enter valid choise ");
        }
		}
	}
	
	public static void showstudentDetails() throws SQLException{
		conn=SchoolManagementConnection.getConnection();
		sql="select * from student_info";
	    pst=conn.prepareStatement(sql);	
		 rs=pst.executeQuery();
		
		 System.out.println("Id\tName\tStd\tAge\tAddress\t\tPhone no\tGender\tDOB");

		 while ( rs.next()) {
			 id=rs.getInt("sid");
			 name=rs.getString("sname");
			 std=rs.getInt("std");
			 age =rs.getInt("sage");
			 address=rs.getString("address");
			 phone =rs.getString("phone");
			 gender=rs.getString("gender");
			Date dob=rs.getDate("dob");
			 
			 System.out.println(id+"\t"+name+"\t"+std+"\t"+age+"\t"+address+"\t\t"+phone+"\t"+gender+"\t"+dob);
		 }
	
	}
	public static void registerNewStudent() throws SQLException{
		conn=SchoolManagementConnection.getConnection();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter the Name of student");
		name=sc.nextLine();
		
		System.out.println("Enter the phone number ");
		phone=sc.nextLine();
		
		System.out.println("Enter the address" );
		//sc.next();
		address=sc.nextLine();
		
		System.out.println("Enter the gender ");
		gender=sc.nextLine();
		
		System.out.println("Enter thr DOB (YYYY-MM-DD) ");
		dob=sc.nextLine();
		
		System.out.println("Enter the Standard ");
		std=sc.nextInt();
		
		System.out.println("Enter age");
		age=sc.nextInt();
		
		stmt=conn.createStatement();
		String s="select max(sid)+1 as sid from student_info";
		
		rs=stmt.executeQuery(s);
		int stid=0;
		if ( rs.next()) {
			stid=rs.getInt("sid");
		}
		
		sql="insert into student_info(sid,sname,std,sage,address,phone,gender,dob) values (?,?,?,?,?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setInt(1, stid);
		pst.setString(2,name);
		pst.setInt(3,std);
		pst.setInt(4,age );
		pst.setString(5,address);
		pst.setString(6,phone);
		pst.setString(7,gender);
		pst.setDate(8, Date.valueOf(dob));

		int i=pst.executeUpdate();
		if ( i>0) {
		
			System.out.println("Student registered Successfully...!");
		}
		else {
			System.out.println("Not Registered Successfully");
		}
		
		
	}
	public static void deleteStudent() throws SQLException{
		conn=SchoolManagementConnection.getConnection();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter student id ");
		id=sc.nextInt();
		sql="select * from student_info where sid=?";
		pst=conn.prepareStatement(sql);
		pst.setInt(1,id);
		rs=pst.executeQuery();
		if ( rs.next()) {
			String del="delete from student_info where sid=?";
			pst=conn.prepareStatement(del);
			pst.setInt(1, id);
			int i=pst.executeUpdate();
			
			if ( i>0) {
				
			System.out.println("Record is deleted....!");
			}
			
	}
		else {System.out.println("Student id not Exists....!");}
		
	}
	
	public static void updateStudent() throws SQLException{
		conn=SchoolManagementConnection.getConnection();

	Scanner sc = new Scanner (System.in);
	System.out.println("Enter the id ");
	id=sc.nextInt();
	sql="select * from student_info where sid=?";
	pst=conn.prepareStatement(sql);
	pst.setInt(1,id);
	
	rs=pst.executeQuery();
	if ( rs.next()) {
		int ch;
		System.out.println("chose one of them for update ");
		System.out.println("1.To update Student Name\n2.To update Student Age \n3.To update Student Address\n4.To update Student Phone no\n5.To update Student DOB\n6. To update gender \n7.To update standard");
	 ch=sc.nextInt();
	 switch(ch) {
	 
	 case 1: // update student Name 
		 System.out.println("Enter the name of student");
		 sc.nextLine();
		 name=sc.nextLine();
		 sql="update student_info set sname=? where sid=?";
		 pst=conn.prepareStatement(sql);
		 pst.setString(1, name);
		 pst.setInt(2,id);
		 updvar=pst.executeUpdate();
		 if ( updvar>0) {
			 
			 System.out.println("Your UPDATED Name is :"+name);
		 }
		 
		 break;
		 
		 
	 case 2: // update student age 
		 System.out.println("Enter the age of student");
		 sc.nextLine();
		 age=sc.nextInt();
		 sql="update student_info set sage=? where sid=?";
		 pst=conn.prepareStatement(sql);
		 pst.setInt(1, age);
		 pst.setInt(2,id);
		 updvar=pst.executeUpdate();
		 if ( updvar>0) {
			
			 System.out.println("Your UPDATED age is :"+age);
		 }
		 
		 break;
		 
	 case 3: // update student address 
		 System.out.println("Enter the address of student");
		 sc.nextLine();
		 address=sc.nextLine();
		 sql="update student_info set address=? where sid=?";
		 pst=conn.prepareStatement(sql);
		 pst.setString(1, address);
		 pst.setInt(2,id);
		 updvar=pst.executeUpdate();
		 if ( updvar>0) {
			
			 System.out.println("Your UPDATED Address is :"+address);
		 }
		
		 break;
		 
	 case 4: // update student phone no 
		 System.out.println("Enter the Phone no. of student");
		 sc.nextLine();
		 phone=sc.nextLine();
		 sql="update student_info set phone=? where sid=?";
		 pst=conn.prepareStatement(sql);
		 pst.setString(1, phone);
		 pst.setInt(2,id);
		 updvar=pst.executeUpdate();
		 if ( updvar>0) {
			 
			 System.out.println("Your UPDATED Phone no. is :"+phone);
		 }
		 break;
		 
	 case 5: // update student DOB 
		 System.out.println("Enter the date of Birth ");
		 sc.nextLine();
		 dob=sc.nextLine();
		 sql="update student_info set dob=? where sid=?";
		 pst=conn.prepareStatement(sql);
		 pst.setString(1,dob);
		 pst.setInt(2,id);
		 updvar=pst.executeUpdate();
		 if ( updvar>0) {
			 
			 System.out.println("Your UPDATED DOB is :"+dob);
		 }
		 break;
	 case 6: // update student Gender
		 System.out.println("Enter the gender ");
		 sc.nextLine();
		 gender=sc.nextLine();
		 sql="update student_info set gender=? where sid=?";
		 pst=conn.prepareStatement(sql);
		 pst.setString(1,gender);
		 pst.setInt(2,id);
		 updvar=pst.executeUpdate();
		 if ( updvar>0) {
			 
			 System.out.println("Your UPDATED gender is :"+gender);
		 }
		
		 break;
	 case 7: // update student std 
		 System.out.println("Enter the standard");
		 sc.nextLine();
		 std=sc.nextInt();
		 sql="update student_info set std=? where sid=?";
		 pst=conn.prepareStatement(sql);
		 pst.setInt(1,std);
		 pst.setInt(2,id);
		 updvar=pst.executeUpdate();
		 if ( updvar>0) {
			
			 System.out.println("Your UPDATED Standard is :"+std);
		 }
		
		 break;
			
		default :
			System.out.println("Invalid Choise ....! Enter valid choise ");
			System.exit(0);
	 }
	}
	 else {
		 System.out.println(id+" Not Exist");
	 }
	}
}
		
	
	
	
	
	
	
	

