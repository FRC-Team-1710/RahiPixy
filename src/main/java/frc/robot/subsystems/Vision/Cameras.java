package frc.robot.subsystems.Vision;


public class Cameras {

	private static PixyCamera pixy;

	public static void setup() {
		//initDrive();
		pixy = new PixyCamera();
		initLine();
	}

	public static PixyCamera getPixyCamera() {
		return pixy;
	}

	public static void initLine() {
		ShadowLine.getInstance().setRunWhenDisabled(true);
		ShadowLine.getInstance().start();
	}

	/* public static void initDrive() {
		drive = CameraServer.getInstance().startAutomaticCapture();
		drive.setConnectVerbose(0);
		if (drive != null) {
			drive.setResolution(320, 240);
			drive.setFPS(30);
			drive.setWhiteBalanceManual(4500);
			drive.setExposureAuto();
			drive.setBrightness(50);
		}
	} */

}

