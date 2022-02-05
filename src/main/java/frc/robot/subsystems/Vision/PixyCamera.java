package frc.robot.subsystems.Vision;


import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.links.SPILink;

public class PixyCamera {

    public static Pixy2 Pixy;

	public static void initialize() {
        Pixy = Pixy2.createInstance(new SPILink());
		//Pixy2.createInstance(new SPILink());
        Pixy.init(); // Initializes the camera and prepares to send/receive data
	}

    public Pixy2 getPixy2() {
        return getPixy2();
    }
}