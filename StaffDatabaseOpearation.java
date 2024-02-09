package com.eduSchool;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class StaffDatabaseOpearation {

	static Connection conn ;
	private static Statement stmt;
	private static PreparedStatement pst;
	private static String sql;
	private static ResultSet rs;
	
	private static String name ,phone,address,gender;
	private static int id;
	private static int updvar;
	
	
	public StaffDatabaseOpearation() throws SQLException {
		super();
	for (;;) {
		System.out.println();
		System.out.println("******* WELCOME TO SUPPORTING SATFF INTERFACE ********");
	
		int ch ;
		Scanner sc = new Scanner (System.in);
		System.out.println();
		System.out.println("******** MENU*********");
		System.out.println("Enter your Choise ");
		System.out.println("1.Show staff details");
		System.out.println("2.Register New staff");
        System.out.println("3.Delete staff details");
        System.out.println("4.Update staff Details");
        System.out.println("5.Exit");
        
        ch=sc.nextInt();
        switch(ch)
        {
        case 1 ://  staff details
        	staffDetails();
        	break;
        	
        case 2 :// Register new staff
        	registerNewstaff();
        	break;
        	
        case 3 :// Delete staff details
        	deletesStaff();
        	break;
        	
        case 4 :// Update staff Details
        	updateStaff();
        	 break;
        case 5 :
			System.out.println("Thank you ....!");
			MainSchoolManagementApp.main(null); // calling main class 
        	System.exit(0);
        	break;
        	
        default :
			System.out.println("Invalid Choise ....! Enter valid choise ");
        
        
        }
		
	}
	}
	
public static void staffDetails() throws SQLException{
	conn=SchoolManagementConnection.getConnection();
	sql="select * from staff_info";
    pst=conn.prepareStatement(sql);	
	 rs=pst.executeQuery();
	
	 System.out.println("Id\tName\t\t\tPhone no\tAddress\t\tGender");

	 while ( rs.next()) {
		 id=rs.getInt("stid");
		 name=rs.getString("stname");
		 address=rs.getString("address");
		 phone =rs.getString("stphone");
		 gender=rs.getString("gender");
		
		 
		 System.out.println(id+"\t"+name+"\t\t"+phone+"\t"+address+"\t\t"+gender);
	 }

}	
	
public static void registerNewstaff() throws SQLException{
	conn=SchoolManagementConnection.getConnection();
	Scanner sc = new Scanner (System.in);
	System.out.println("Enter the Name ");
	name=sc.nextLine();
	
	System.out.println("Enter the Address");
	address=sc.nextLine();
	
	System.out.println("Enter the Phone NO.");
	phone=sc.nextLine();
	
	System.out.println("Enter the Gender" );
	gender=sc.nextLine();
	
	stmt=conn.createStatement();
	String s="select max(stid)+1 as sid from staff_info";
	
	rs=stmt.executeQuery(s);
	int stid1=0;
	if ( rs.next()) {
		stid1=rs.getInt("sid");
	}
	
	sql="insert into staff_info(stid,stname,stphone,address,gender) values (?,?,?,?,?)";
	pst=conn.prepareStatement(sql);
	pst.setInt(1, stid1);
	pst.setString(2,name);
	pst.setString(3,phone);
	pst.setString(4,address );
	pst.setString(5,gender);
	
	int i=pst.executeUpdate();
	if (i>0) {
		
		System.out.println("staff Registered Successfully...!");

	}
	else {
		System.out.println("Not Registered Successfully");

	}
	
}
public static void deletesStaff() throws SQLException{
	conn=SchoolManagementConnection.getConnection();
	Scanner sc = new Scanner(System.in);
	System.out.println("Enter the id");
	id=sc.nextInt();
	sql="select * from staff_info where stid=?";
	pst=conn.prepareStatement(sql);
	pst.setInt(1, id);
	rs=pst.executeQuery();
	if ( rs.next()) {
		 String del="delete from staff_info where stid=?";
		 pst=conn.prepareStatement(del);
		 pst.setInt(1,id);
		 int i=pst.executeUpdate();
		 if ( i>0) {
			
			 System.out.println("Record is Deleted...!");}
	}
	else {System.out.println("Staff id not Exists....!");}

	
	
}
public static void updateStaff() throws SQLException{
	Scanner sc = new Scanner (System.in);
	System.out.println("Enter the id ");
	id=sc.nextInt();
	sql="select * from staff_info where stid=?";
	pst=conn.prepareStatement(sql);
	pst.setInt(1,id);
	
	rs=pst.executeQuery();
	if ( rs.next()) {
		int ch;
		System.out.println("chose one of them for update ");
		System.out.println("1.To update Name \n2.To update phone No \n3.To update address \n4.To update Gender");
		ch=sc.nextInt();
		switch(ch) {
		
		 
		 case 1: // update staff Name 
			 System.out.println("Enter the name ");
			 sc.nextLine();
			 name=sc.nextLine();
			 sql="update staff_info set stname=? where stid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, name);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
			
				 System.out.println("Your UPDATED Name is :"+name);
			 }
			 
			 break;
		
		 case 2: // update staff phone no 
			 System.out.println("Enter the Phone no.");
			 sc.nextLine();
			 phone=sc.nextLine();
			 sql="update staff_info set stphone=? where stid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, phone);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
			
				 System.out.println("Your UPDATED Phone no. is :"+phone);
			 }
			
			 break;
			 
		 case 3: // update staff address 
			 System.out.println("Enter the address ");
			 sc.nextLine();
			 address=sc.nextLine();
			 sql="update staff_info set address=? where stid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1, address);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
				
				 System.out.println("Your UPDATED Address is :"+address);
			 }
			
			 break;
			 
		 case 4: // update staff Gender
			 System.out.println("Enter the gender ");
			 sc.nextLine();
			 gender=sc.nextLine();
			 sql="update staff_info set gender=? where stid=?";
			 pst=conn.prepareStatement(sql);
			 pst.setString(1,gender);
			 pst.setInt(2,id);
			 updvar=pst.executeUpdate();
			 if ( updvar>0) {
			
			 
				 System.out.println("Your UPDATED gender is :"+gender);
			 }
			
			 break;
		}
	
}
	else {
		 System.out.println(id+" Not Exist");
	 }
}
	
	
	
}

