package com.ezdi.channelModels;

import java.sql.Connection;
import java.sql.DriverManager;

public class GlobalVariables {

	
	public final static String emailusername = "";
	public final static String emailpassword = "";
	
	
	
	public final static String ProdDbName = "SA_MSG_STATISTICS";
	public final static String ProdDbURL = "jdbc:mysql://ezcac-production-backend.cncmk5ndlbjo.us-east-1.rds.amazonaws.com";
	public final static String ProdDbPassword = "cac@ezdi2014";
	public final static String ProdDbUsername = "prodread";
	public final static String ProdDbHealthquery = "SELECT table_schema \"DB Name\", ROUND(SUM(data_length + index_length) / 1024 / 1024, 1) \"DB_Size_in_MB\" FROM information_schema.tables GROUP BY table_schema having ROUND(SUM(data_length + index_length) / 1024 / 1024, 1) > '10240' order by DB_Size_in_MB desc;";
	public final static String StagDbHealthquery = "SELECT table_schema \"DB Name\", ROUND(SUM(data_length + index_length) / 1024 / 1024, 1) \"DB_Size_in_MB\" FROM information_schema.tables GROUP BY table_schema having ROUND(SUM(data_length + index_length) / 1024 / 1024, 1) > '20480' order by DB_Size_in_MB desc;";
	
	
	public final static String DBHostStagingDbname = "SA_MSG_STATISTICS";
	public final static String DBHostStagingMessageSta = "jdbc:mysql://ezcac-staging-backend.cncmk5ndlbjo.us-east-1.rds.amazonaws.com/"+DBHostStagingDbname+"";
	public final static String DBHostStagingMessageStaPassword = "stghl7@2018";
	public final static String DBHostStagingusername = "hl7_user";
	public final static String messagecountquery = "select channel_name, group_concat(message_count order by present_date) AS statestics from MESSAGE_STATISTICS where present_date > DATE_ADD(CURDATE(), INTERVAL -10 DAY) group by channel_name";
	
	
	public final static String DBHostStagingDB = "jdbc:mysql://ezcac-staging-backend.cncmk5ndlbjo.us-east-1.rds.amazonaws.com";
	
	// username : support-team
	// password : support-team@AWS2018!

	public final static String stgHosturl = "jdbc:mysql://ezcac-staging-backend.cncmk5ndlbjo.us-east-1.rds.amazonaws.com/HL7_RCVR";
	public final static String stgDbusername = "hl7_user";
	public final static String stgdbpassword = "stghl7@2018";


	public static String fromkishanemail = "kishan.b@ezdi.us";
	public static String toRaviezzdi = "kishan.b@ezdi.us";
	public static String emailhost = "smtp.gmail.com";
	public static String emailSocketport = "465";
	public static String ezdikishanpassword = "P@ssw0rd@12345";
	

	
	
	public final static String prodHosturl = "jdbc:mysql://ezcac-production-backend.cncmk5ndlbjo.us-east-1.rds.amazonaws.com/HL7_RCVR";
	public final static String prodDbusername = "awsdb";
	public final static String proddbpassword = "ezCAC@ezdi2015!";
	
	
	
	
	
	public static Connection getStaggingConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(stgHosturl,
					stgDbusername, stgdbpassword);
			return connection;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error In Getting connection of the Database");
			e.printStackTrace();
		}
		return null;

	}
	
	public static Connection getProdfbConnection() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(prodHosturl,
					prodDbusername, proddbpassword);
			return connection;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error In Getting connection of the Database");
			e.printStackTrace();
		}
		return null;

	}

}
