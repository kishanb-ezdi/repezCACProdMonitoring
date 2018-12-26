package com.ezdi.DaoOperations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import com.ezdi.channelModels.ChannelModel;
import com.ezdi.channelModels.GlobalVariables;
import com.ezdi.CommonUtilities.UtilityClass;

public class CheckingReceiverChargeFiles extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		Connection connection = UtilityClass.getconnectionfrommethod();
		try {

			// System.out.println("String Method for getting Charge file Counts");
			// System.out.println("Connecting databse");

			Class.forName("com.mysql.jdbc.Driver");
			List<ChannelModel> Chargefilemodel = new Vector<ChannelModel>();
			List<ChannelModel> restchannel = new Vector<ChannelModel>();
			List<ChannelModel> configbackup = new Vector<ChannelModel>();

			if (connection != null) {
				// System.out.println("Connection successful");

				Statement stmt = connection.createStatement();

				ResultSet resultSet = stmt
						.executeQuery(GlobalVariables.messagecountquery);

				while (resultSet.next()) {

					if (resultSet.getString(1).contains("INBOUND_CHARGE_CODE")) {
						// System.err.println("Charge Channel index");
						ChannelModel e = new ChannelModel();

						e.setChannelName(resultSet.getString(1));
						e.setChannelstatastiscsString(resultSet.getString(2));
						Chargefilemodel.add(e);

					} else if (resultSet.getString(1).contains(
							"S3")) {
						ChannelModel e = new ChannelModel();
						e.setChannelName(resultSet.getString(1));
						e.setChannelstatastiscsString(resultSet.getString(2));

						configbackup.add(e);

					}

					else {
						ChannelModel e = new ChannelModel();

						e.setChannelName(resultSet.getString(1));
						e.setChannelstatastiscsString(resultSet.getString(2));
						restchannel.add(e);

					}

				}
				connection.close();
				String status = UtilityClass
						.getChargelistandcheck(Chargefilemodel);

				String status_charge = UtilityClass
						.getChargelistandcheck(configbackup);

				String emailcontent = "Hello Team </br>"
						+ "This is System generated automatic email for sending you message Ststestics This report seems to be"
						+ status
						+ "<html><body><table border=\"1\"><tr><th>Channel Nmae</th><th>Channel Channel Statestics</th></tr>";

				for (ChannelModel channelModel : restchannel) {
					emailcontent = emailcontent + "<tr><td>"
							+ channelModel.getChannelName() + "</td>" + "<td>"
							+ channelModel.getChannelstatastiscsString()
							+ "</td></tr>";
				}

				emailcontent = emailcontent
						+ "</table> </br> Now table for charge files </br> <table border=\"1\"><tr> <th>Channel Nmae</th><th>Channel Channel Statestics</th></tr>";

				for (ChannelModel channelModel : Chargefilemodel) {
					emailcontent = emailcontent + "<tr><td>"
							+ channelModel.getChannelName() + "</td>" + "<td>"
							+ channelModel.getChannelstatastiscsString()
							+ "</td></tr>";
				}
				emailcontent = emailcontent + "</table> </br>";

				emailcontent = emailcontent						
						+ "Below is the report for S3 Back up for each client and Report seems to be <b> " + status_charge
						+ "</b></br> "
						+ "<table border=\"1\"><tr><th>Channel Nmae</th><th>Channel config S3 Statestics</th></tr>";

				for (ChannelModel channelModel : configbackup) {

					emailcontent = emailcontent + "<tr><td>"
							+ channelModel.getChannelName() + "</td>" + "<td>"
							+ channelModel.getChannelstatastiscsString()
							+ "</td></tr>";

				}
				
				emailcontent = emailcontent + "</table> </br>";

				emailcontent = emailcontent
						+ "<p>Please do not reply to this email</p>";
				
				System.out.println(emailcontent);
				boolean emailsent = UtilityClass.Sendemail(emailcontent);

			}

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Thread Error in "
					+ CheckingReceiverChargeFiles.class.getName());
			e.printStackTrace();

			try {
				connection.close();

			} catch (Exception closeconnectionerror) {
				// TODO: handle exception
				e.printStackTrace();
			}

		}
	}

/*	public static void main(String[] args) {

		Thread thread = new CheckingReceiverChargeFiles();
		thread.run();
	}*/

}
