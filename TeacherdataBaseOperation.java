package com.eduSchool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TeacherdataBaseOperation {
	
	static Connection conn ;
	private static Statement stmt;
	private static PreparedStatement pst;
	private static String sql;
	private static ResultSet rs;
	
	private static String name ,phone,address,gender,email,subject;
	private static int id ;
	private static int updvar;
	
	public TeacherdataBaseOperation() throws SQLException {
		super();
		
		for (;;) 
		{
			System.out.println();
		System.out.println("******* WELCOME TO TEACHER INTERFACE ********");
		
		int ch ;
		Scanner sc = new Scanner (System.in);
		System.out.println();
		System.out.println(".......MENU.......");
		System.out.println("Enter your Choise ");
		System.out.println("1.Show Teacher details");
		System.out.println("2.Register New Teacher");
        System.out.println("3.Delete Teacher details");
        System.out.println("4.Update Teacher Details");
        System.out.println("5.Exit");
        
        ch=sc.nextInt();
      
        switch(ch)
        {
        case 1 :// Teacher details
        	showteacherDetails();
        	break;
        	
        case 2 :// Register new Teacher
        	registerNewTeacher();
        	break;
        	
        case 3 :// Delete Teacher details
        	deleteTeacher();
        	break;
        	
        case 4 :// Update Teacher Details
        	updateTeacher();
           break;
           
        case 5 :// exit 
        	System.out.println("Thank you ....!");
			MainSchoolManagementApp.main(null); // calling main class
        	System.exit(0);
        	break;
        	
        default :
			System.out.println("Invalid Choise ....! Enter valid choise ");
        
        }
	}
	}
	public static void showteacherDetails() throws SQLException{
		conn=SchoolManagementConnection.getConnection();
		sql="select * from teacher_info";
	    pst=conn.prepareStatement(sql);	
		 rs=pst.executeQuery();
		
		 System.out.println("Id\tName\t\temail\t\t\tAddress\tphone no\tSubject\tGender");

		 while ( rs.next()) {
			 id=rs.getInt("tid");
			 name=rs.getString("tname");
			 email=rs.getString("temail");
			 address=rs.getString("address");
			 phone =rs.getString("phone");
			 subject=rs.getString("subjects");
			 gender=rs.getString("gender");
			
			 
			 System.out.println(id+"\t"+name+"\t"+email+"\t"+address+"\t"+phone+"\t"+subject+"\t"+gender);
		 }
	}
		
	public static void registerNewTeacher() throws SQLException{
		
		conn=SchoolManagementConnection.getConnection();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter the Name ");
		
		name=sc.nextLine();
		System.out.println("Enter the Email ");
	
		email=sc.nextLine();
		System.out.println("Enter the address" );
	
		address=sc.nextLine();
		System.out.println("Enter the phone number ");
		
		phone=sc.nextLine();
		System.out.println("Enter the gender ");
		
		gender=sc.nextLine();
		System.out.println("Enter the subject  ");
	
		subject=sc.nextLine();
		
		stmt=conn.createStatement();
		String s="select max(tid)+1 as tid from teacher_info";
		rs=stmt.executeQuery(s);
		int stid=0;
		if ( rs.next()) {
			stid=rs.getInt("tid");
		}
		
		sql="insert into teacher_info(tid,tname,temail,address,phone,subjects,gender) values (?,?,?,?,?,?,?)";
		pst=conn.prepareStatement(sql);
		pst.setInt(1, stid);
		pst.setString(2,name);
		pst.setString(3,email);
		pst.setString(4,address );
		pst.setString(5,phone);
		pst.setString(6,subject);
		pst.setString(7,gender);
	

		int i=pst.executeUpdate();
		if ( i>0) {
		
			System.out.println("Teacher Registered Successfully...!");
		}
		else {
			System.out.println("Not Registered Successfully");
		}
		
	}
	public static void deleteTeacher() throws SQLException{
		
		conn=SchoolManagementConnection.getConnection();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter Teacher id ");
		id=sc.nextInt();
		sql="select * from teacher_info where tid=?";
		pst=conn.prepareStatement(sql);
		pst.setInt(1,id);
		rs=pst.executeQuery();
		if ( rs.next()) {
			String del="delete from teacher_info where tid=?";
			pst=conn.prepareStatement(del);
			pst.setInt(1, id);
			int i=pst.executeUpdate();
			
			if ( i>0) {
			
				     System.out.println("Record is deleted....!");}
	
		}
		else {System.out.println("Teacher id not Exists....!");}
		
		
		
	}
	public static void updateTeacher() throws SQLException{
		conn=SchoolManagementConnection.getConnection();
		Scanner sc = new Scanner (System.in);
		System.out.println("Enter the id ");
		id=sc.nextInt();
		sql="select * from teacher_info where tid=?";
		pst=conn.prepareStatement(sql);
		pst.setInt(1,id);
		
		rs=pst.executeQuery();
		
		if ( rs.next()) {
			int ch;
			System.out.println("choose one of them for update ");
			System.out.println("1.To update Name\n2.To update email \n3.To update Address\n4.To update Phone no\n5.To update subject \n6.To update gender ");
		 ch=sc.nextInt();
		 switch(ch) {
		
		 case 1: // update  Name 
			 System.out.println("Enter the name ");
			 sc.nextLine();
			 name=sc.nextLine();
			 sql="update teacher_info set tname=? where tid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, name);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
				 
			
				 System.out.println("Your UPDATED Name is :"+name);
			 }
			 
			 break;
			 
		 case 2: // update teachers email 
			 System.out.println("Enter the  Email");
			 sc.nextLine();
			 email=sc.nextLine();
			 sql="update teacher_info set email=? where tid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, email);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
			
				 System.out.println("Your UPDATED Email is :"+email);
			 }
			
			 break;
			 
		 case 3: // update address 
			 System.out.println("Enter the address ");
			 sc.nextLine();
			 address=sc.nextLine();
			 sql="update teacher_info set address=? where tid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, address);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
			
				 System.out.println("Your UPDATED Address is :"+address);
			 }
			
			 break;
			 
		 case 4: // update  phone no 
			 System.out.println("Enter the Phone no.");
			 sc.nextLine();
			 phone=sc.nextLine();
			 sql="update teacher_info set phone=? where tid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, phone);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
				
				 System.out.println("Your UPDATED Phone no. is :"+phone);
			 }
			 
			 
			 break;
			 
		 case 5 : // update subject 
			 System.out.println("Enter the subject");
			 sc.nextLine();
			 subject=sc.nextLine();
			 sql="update teacher_info set subjects=? where tid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1,subject);
			 pst.setInt(2, id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
			
				 System.out.println("Your UPDATED subject is :"+subject);
			 } 
			
			 break;
			 
		 case 6: // update Gender
			 System.out.println("Enter the gender ");
			 sc.nextLine();
			 gender=sc.nextLine();
			 sql="update teacher_info set gender=? where tid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1,gender);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
				
				     System.out.println("Your UPDATED gender is :"+gender);
			 }
			
			 break;
			 
				
			default :
				System.out.println("Invalid Choise ....! Enter valid choise ");
		 }
		}
		else {
			 System.out.println(id+" Not Exist");
		 }
		}
		}
