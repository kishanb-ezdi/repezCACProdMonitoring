package com.ezdi.DaoOperations;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Vector;

import com.ezdi.CommonUtilities.UtilityClass;
import com.ezdi.channelModels.ChannelModel;
import com.ezdi.channelModels.GlobalVariables;

public class StagingDBHealthcCheck extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub

		// TODO Auto-generated method stub
		String emailstring = "";
		String dbheatlh = "";

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = UtilityClass
					.getconnectionfrommethodstagback();
			List<ChannelModel> Chargefilemodel = new Vector<ChannelModel>();

			Statement stmt = connection.createStatement();

			ResultSet resultSet = stmt
					.executeQuery(GlobalVariables.StagDbHealthquery);
			

			if (resultSet.first()) {

				while (resultSet.next()) {
					ChannelModel channelModel = new ChannelModel();
					channelModel.setChannelName(resultSet.getString(1));
					channelModel.setChannelstatastiscsString(resultSet
							.getString(2));

					Chargefilemodel.add(channelModel);
					dbheatlh = "Not Normal";

				}
				connection.close();

			} else {
				//System.out
					//	.println("There is no results return with the query seems fine");
				dbheatlh = "Normal";
			}

			emailstring = "<html><body> <p> This is report of the database health check for the staging receiver if you get details below please do check your db size and heatlh</p></br> Table as follows"
					+ "<table border=\"1\"><th>Db Name</th><td>Size in MB</th>";

			for (ChannelModel channelModel : Chargefilemodel) {

				emailstring = emailstring + "<tr><td>"
						+ channelModel.getChannelName() + "</td>" + "<td>"
						+ channelModel.getChannelstatastiscsString() + "</td>"
						+ "</tr>";

			}

			emailstring = emailstring
					+ "</table></br>"
					+ "<p>It seems that db helth is <b>"
					+ dbheatlh
					+ "</b></p>"
					+ "<p>This is System Generated Email please do not reply on this email</p>";

			System.out.println(emailstring);

			//boolean emailsent = UtilityClass.Sendemail(emailstring);

		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Issue with this thread "
					+ this.getClass().toString());
			e.printStackTrace();
		}

		super.run();
	}

	/*public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread thread = new StagingDBHealthcCheck();
		thread.run();
	}*/

}
