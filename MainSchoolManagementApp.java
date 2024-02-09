package com.eduSchool;

import java.sql.SQLException;
import java.util.Scanner;

public class MainSchoolManagementApp {

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
        Scanner sc = new Scanner (System.in)  ;
		int ch;
		System.out.println();
		System.out.println("************** WELCOME **************");
		System.out.println("Enter your Choise ");
		System.out.println("1.Student Interface ");
		System.out.println("2.Teacher Interface");
		System.out.println("3.Supporting staff Interface");
		System.out.println("4.Exit");
		 ch=sc.nextInt();
		
		switch (ch) {
		case 1: // Student Operation
			new StudentdataBaseOperation(); // constructor calling by creating object
			
			break;
			
		case 2:// Teacher Operation 
			new TeacherdataBaseOperation(); // constructor calling by creating object
			break;
			
		case 3: // Supporting staff Operation
			new StaffDatabaseOpearation();  // constructor calling by creating object
			break;
			
		case 4:// exit here
			System.out.println("Thank you ....!");
			System.exit(0);
			
			break;
		default :
			System.out.println("Invalid Choise ....! Enter valid choise ");
			main(args);
		}
		
	}

}
