package ics_370_iteration3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class MovieDriver {
	
	public static void main(String[] args) {

		String username = "root"; //*********enter your user here
		String password = " "; //*********enter your password here
		
		Scanner input1 = new Scanner(System.in);
		int input2;
		
		System.out.println("Enter 1 to insert **movies**\n"
	       					+ "Enter 2 to select **movies** & **movie_data**\n"
	       					+ "Enter 3 to delete **movies**\n"
	       					+ "Enter 4 to update **movies**\n");
		
		input2=input1.nextInt();
		
		//insert movies******************************************************************
		if (input2 == 1) {
			try {
				Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/omdb", username , password);
				String query = "insert into movies (movie_id, native_name, english_name, year_made) values(?,?,?,?)";
			
				Scanner sc = new Scanner(System.in);
				
				System.out.println("movie_id: ");
		        int inputM1 = Integer.parseInt(sc.nextLine());        
		        
		        System.out.println("native_name: "); 
		        String inputM2 = sc.nextLine();
		                
		        System.out.println("english_name: "); 
		        String inputM3 = sc.nextLine();
		        
		        System.out.println("year_made: ");
		        int inputM4 = Integer.parseInt(sc.nextLine());;

		        System.out.println("\n**Movie that is inserted** ");
		        System.out.println("movie_id: " + inputM1); 
		        System.out.println("native_name: " +inputM2); 
		        System.out.println("english_name: " + inputM3); 
		        System.out.println("year_made: " + inputM4);
				
				PreparedStatement ps = myCon.prepareStatement(query);
				ps.setInt (1, inputM1);
				ps.setString (2, inputM2);
				ps.setString (3, inputM3);
				ps.setInt (4, inputM4);
		      
				ps.execute();
				ps.close();
				myCon.close();
		    	}
			catch (Exception ex)
			{
				ex.printStackTrace();
				}
			}
		
		//select movies******************************************************************
		else if (input2 == 2) {
			try {
				Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/omdb", username , password);
	    		java.sql.Statement myStat = myCon.createStatement();
			
	    		ResultSet myRs = myStat.executeQuery("select * from movies");
			
	    		System.out.println("\n**********************************************************"
								 + "movies" +
	    				   		   "**********************************************************");
	    		while(myRs.next())
	    		{
	    			int movie_id = myRs.getInt("movie_id");
	    			String native_name = myRs.getString("native_name");
	    			String english_name = myRs.getString("english_name");
	    			int year_made = myRs.getInt("year_made");
			    
	    			System.out.format("%s, %s, %s, %s,\n", movie_id, native_name, english_name, year_made);
	    			}
	    		myRs.close();
	    		}
			catch (Exception ex)
			{
				ex.printStackTrace();
				}
			
		     
	 		//select movie_data******************************************************************
	    	try {
	    		Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/omdb", username , password);
	    		java.sql.Statement myStat = myCon.createStatement();
			
	    		ResultSet myRs = myStat.executeQuery("select * from movie_data");
			
	    		System.out.println("\n**********************************************************"
		    				     + "movie_data"+
	    				   		   "**********************************************************");
	    		while(myRs.next())
	    		{
	    			String tag_line = myRs.getString("tag_line");
	    			int movie_id = myRs.getInt("movie_id");
	    			String language = myRs.getString("language");
	    			String country = myRs.getString("country");
	    			String genre = myRs.getString("genre");
	    			String plot = myRs.getString("plot");

	    			System.out.format("%s, %s, %s, %s,\n", tag_line, movie_id, language, country, genre, plot);
	    			}
	    		myRs.close();
	    		}
	    	catch (Exception ex)
	    	{
	    		ex.printStackTrace();
	    		}
	    	}
		
		//delete movies******************************************************************
    	else if (input2 == 4) {
    		try {
				Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/omdb", username , password);
    			java.sql.Statement myStat = myCon.createStatement();
    			String query = "delete from movies where movie_id = ?";
			
				Scanner sc = new Scanner(System.in);
				
				System.out.println("movie_id: ");
		        int inputMd1 = sc.nextInt(); 

		        System.out.println("\n**Movie that is deleted**: ");
		        System.out.println("movie_id: " + inputMd1); 
		        
    			PreparedStatement ps = myCon.prepareStatement(query);
    			ps.setInt(1, inputMd1);
			
    			ps.execute();
    			ps.close();
    			myCon.close();
    			}
    		catch (Exception ex)
    			{
    				ex.printStackTrace();	
    			}
    		}

 		//update movies******************************************************************
    	else if (input2 == 5) {
    		try {
			Connection myCon = DriverManager.getConnection("jdbc:mysql://localhost:3306/omdb", username , password);
			java.sql.Statement myStat = myCon.createStatement();
			String query = "update movies set movie_id = ?, native_name = ?, english_name = ? where year_made = ?";
			
			Scanner sc = new Scanner(System.in);
			
			System.out.println("movie_id: ");
	        int inputUm1 = Integer.parseInt(sc.nextLine());
	        
	        System.out.println("native_name: "); 
	        String inputUm2 = sc.nextLine();
	        
	        System.out.println("english_name: "); 
	        String inputUm3 = sc.nextLine();
	        
	        System.out.println("year_made: ");
	        int inputUm4 = Integer.parseInt(sc.nextLine());

	        System.out.println("\n**Movie that is updated**: ");
	        System.out.println("movie_id: " + inputUm1); 
	        System.out.println("native_name: " +inputUm2); 
	        System.out.println("english_name: " + inputUm3); 
	        System.out.println("year_made: " + inputUm4);
	        
			PreparedStatement ps = myCon.prepareStatement(query);
		    ps.setInt (1, inputUm1);
		    ps.setString (2, inputUm2);
		    ps.setString (3, inputUm3);
		    ps.setInt (4, inputUm4);
		      
		    ps.execute();
		    ps.close();
		    myCon.close();
		    }
    		catch (Exception ex)
    		{
    			ex.printStackTrace();	
    			}
    		}
    	}
	}


