package com.ezdi.CommonUtilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.ezdi.channelModels.ChannelModel;
import com.ezdi.channelModels.GlobalVariables;

public class UtilityClass {

	public static Connection getconnectionfrommethod() {

		try {

			Connection connection = DriverManager.getConnection(
					GlobalVariables.DBHostStagingMessageSta,
					GlobalVariables.DBHostStagingusername,
					GlobalVariables.DBHostStagingMessageStaPassword);
			return connection;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in retriving connection of DB "
					+ GlobalVariables.DBHostStagingMessageSta
					+ " in this class " + UtilityClass.class.getName());
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public static Connection getconnectionfrommethod(String DBURL,
			String DbUsername, String password, String dbname) {

		try {

			Connection connection = DriverManager.getConnection(DBURL,
					DbUsername + "/" + dbname, password);
			return connection;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in retriving connection of DB "
					+ GlobalVariables.DBHostStagingMessageSta
					+ " in this class " + UtilityClass.class.getName());
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public static String getChargelistandcheck(
			List<ChannelModel> chargefilemodel) {
		// TODO Auto-generated method stub

		try {
			String returnmessage = "Normal";
			for (ChannelModel channelModel : chargefilemodel) {
				String[] statastics = channelModel
						.getChannelstatastiscsString().split(",");
				for (int i = 0; i < statastics.length; i++) {
					if (Integer.parseInt(statastics[i]) == 1) {
						// System.out.println("Charge file normal");
						returnmessage = "Normal";
						// return "normal";
					} else {
						// System.out.println("Something wrong with charge file");
						// return "Not Normal";
						returnmessage = "Not Normal";
					}

				}
			}

			return returnmessage;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in charge file check method"
					+ UtilityClass.class.getName());
			e.printStackTrace();
		}

		return null;
	}

	public static boolean Sendemail(String status) {
		// TODO Auto-generated method stub
		try {
			Properties properties = System.getProperties();
			properties.setProperty("mail.smtp.host", GlobalVariables.emailhost);
			properties
					.setProperty("mail.user", GlobalVariables.fromkishanemail);
			properties.put("mail.smtp.socketFactory.port", "465");
			properties.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.port", "465");

			// Get the default Session object.
			Session session = Session.getDefaultInstance(properties,
					new Authenticator() {

						protected PasswordAuthentication getPasswordAuthentication() {
							return new PasswordAuthentication(
									GlobalVariables.emailusername,
									GlobalVariables.emailpassword);
						}
					});

			MimeMessage message = new MimeMessage(session);

			InternetAddress[] addresses = new InternetAddress[2];
			addresses[0] = new InternetAddress(GlobalVariables.toRaviezzdi);
			addresses[1] = new InternetAddress("ravi.s@ezdi.us");

			message.setFrom(new InternetAddress(GlobalVariables.fromkishanemail));
			// message.addRecipient(Message.RecipientType.TO, new
			// InternetAddress(
			// GlobalVariables.toRaviezzdi));

			message.addRecipients(Message.RecipientType.TO, addresses);

			message.setSubject("Cac Channel Message statestics");
			message.setContent(status, "text/html");

			Transport.send(message);

			return true;

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return false;
	}

	public static Connection getconnectionfrommethodprodback() {
		// TODO Auto-generated method stub
		try {

			Connection connection = DriverManager.getConnection(
					GlobalVariables.ProdDbURL, GlobalVariables.ProdDbUsername,
					GlobalVariables.ProdDbPassword);
			return connection;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in retriving connection of DB "
					+ GlobalVariables.ProdDbURL + " in this class "
					+ UtilityClass.class.getName());
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}

	public static Connection getconnectionfrommethodstagback() {
		// TODO Auto-generated method stub
		try {

			Connection connection = DriverManager.getConnection(
					GlobalVariables.DBHostStagingDB, GlobalVariables.DBHostStagingusername,
					GlobalVariables.DBHostStagingMessageStaPassword);
			return connection;

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error in retriving connection of DB "
					+ GlobalVariables.ProdDbURL
					+ " in this class " + UtilityClass.class.getName());
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return null;
	}
}
