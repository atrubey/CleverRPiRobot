package org.jointheleague.ecolban.cleverrobot;

/*********************************************************************************************
 * Vic's ultrasonic sensor running with Erik's Clever Robot for Pi
 * version 0.9, 170227
 **********************************************************************************************/
import java.io.IOException;

import org.jointheleague.ecolban.rpirobot.IRobotAdapter;
import org.jointheleague.ecolban.rpirobot.IRobotInterface;
import org.jointheleague.ecolban.rpirobot.SimpleIRobot;

public class CleverRobot extends IRobotAdapter {
	Sonar sonar = new Sonar();
	private boolean tailLight;

	public CleverRobot(IRobotInterface iRobot) {
		super(iRobot);
	}

	public static void main(String[] args) throws Exception {
		System.out.println("Try event listner, rev Monday 2030");
		IRobotInterface base = new SimpleIRobot();
		CleverRobot rob = new CleverRobot(base);
		rob.setup();
		while (rob.loop()) {
		}
		rob.shutDown();

	}

	private void setup() throws Exception {
		driveDirect(100, 100);
	}

	private boolean loop() throws Exception {
		driveDirect(100, 100);
		readSensors(100);

		int[] lightSensors = getLightBumps();

		if (lightSensors[0] > 1000) {
			driveDirect(100, -100);
			Thread.sleep(1500);
		} else if (lightSensors[1] > 1000) {
			driveDirect(100, -100);
			Thread.sleep(750);
		} else if (lightSensors[2] > 1000) {
			driveDirect(100, -100);
			Thread.sleep(500);
		} else if (lightSensors[3] > 1000) {
			driveDirect(-100, 100);
			Thread.sleep(500);
		} else if (lightSensors[4] > 1000) {
			driveDirect(-100, 100);
			Thread.sleep(750);
		} else if (lightSensors[5] > 1000) {
			driveDirect(-100, 100);
			Thread.sleep(1500);
		}

		// if (isBumpLeft()) {
		// driveDirect(100, -100);
		// Thread.sleep(1000);
		// driveDirect(100, 100);
		// Thread.sleep(1000);
		// driveDirect(-100, 100);
		// Thread.sleep(1000);
		// } else if (isBumpRight()) {
		// driveDirect(-100, 100);
		// Thread.sleep(1000);
		// driveDirect(100, 100);
		// Thread.sleep(1000);
		// driveDirect(100, -100);
		// Thread.sleep(1000);
		// } else if (isBumpRight() && isBumpLeft()) {
		// driveDirect(-100, 100);
		// Thread.sleep(1000);
		// driveDirect(100, 100);
		// Thread.sleep(1000);
		// driveDirect(100, -100);
		// Thread.sleep(1000);
		// }

		if (isBumpLeft()) {
			driveDirect(100, -100);
			Thread.sleep(1000);
		} else if (isBumpRight()) {
			driveDirect(-100, 100);
			Thread.sleep(1000);
		} else if (isBumpRight() && isBumpLeft()) {
			driveDirect(-100, 100);
			Thread.sleep(1000);
		}

		return true;
	}

	private void shutDown() throws IOException {
		reset();
		stop();
		closeConnection();
	}
}
