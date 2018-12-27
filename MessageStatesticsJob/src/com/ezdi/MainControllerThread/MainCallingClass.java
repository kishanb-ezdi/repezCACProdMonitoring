package com.ezdi.MainControllerThread;

import com.ezdi.DaoOperations.CheckingReceiverChargeFiles;
import com.ezdi.DaoOperations.ProductionDBHealthcCheck;
import com.ezdi.DaoOperations.StagingDBHealthcCheck;

public class MainCallingClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			
			/*Thread thread = new CheckingReceiverChargeFiles();
			thread.run();*/
			
			
			/*Thread thread2 = new ProductionDBHealthcCheck();
			thread2.run();*/
			
			Thread thread3 = new StagingDBHealthcCheck();
			thread3.run();
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

}
