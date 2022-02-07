package frc.robot.subsystems.Vision;

import javax.sound.sampled.Line;

import edu.wpi.first.wpilibj2.command.CommandBase;
import io.github.pseudoresonance.pixy2api.Pixy2;
import io.github.pseudoresonance.pixy2api.Pixy2Line;
import io.github.pseudoresonance.pixy2api.Pixy2Line.Vector;

public class ShadowLine extends CommandBase {

	private static ShadowLine instance;

	private static final int minLineLength = 25;

	private Pixy2 pixy;

	private int frameMid = 0;

	private double lineAngle = 0.0;
	private int linePosition = 0;
	private boolean hasLine = false;

	public static ShadowLine getInstance() {
		if (instance == null)
			instance = new ShadowLine();
		return instance;
	}

	public ShadowLine() {
	}

	public void initialize() {
		pixy = Cameras.getPixyCamera().getPixy2();
		pixy.getLine().setMode(Pixy2Line.LINE_MODE_WHITE_LINE);
		pixy.setLamp((byte) 1, (byte) 1);
		pixy.setLED(255, 255, 255);
		frameMid = pixy.getFrameHeight() / 2;
	}

	@Override
	public void execute() {
		pixy.getLine().getMainFeatures();
		Vector v[] = pixy.getLine().getVectorCache();
		double testAngle = 0.0;
		double testLength = 0.0;
		int testPosition = 0;
		if (v != null) {
			for (int i = 0; i < v.length; i++) {
				double x = v[i].getX1() - v[i].getX0();
				double y = v[i].getY1() - v[i].getY0();
				double angle = 90.0;
				if (x != 0)
					angle = -Math.toDegrees(Math.atan(y / x));
				double length = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
				int position = (v[i].getY0() + v[i].getY1()) / 2;
				if (length > testLength) {
					testLength = length;
					testAngle = angle;
					testPosition = position;
				}
			} 
		}
		if (testLength >= minLineLength) {
			lineAngle = testAngle;
			hasLine = true;
			linePosition = frameMid - testPosition;
		} else {
			lineAngle = 0;
			hasLine = false;
			linePosition = frameMid;
		}
	} 

	/**
	 * Gets angle of line in Pixy
	 * 
	 * @return Angle of line (Sign is the same as when graphing)
	 */
	public double getLineAngle() {
		return lineAngle;
	}

	/**
	 * Gets position of line in Pixy
	 * 
	 * @return Distance from midpoint (Top of Camera is Positive)
	 */
	public double getLinePosition() {
		return linePosition;
	}

	/**
	 * If Pixy has found a line
	 * 
	 * @return If Pixy has a line
	 */
	public boolean hasLine() {
		return hasLine;
	}

	
	public boolean isFinished() {
		return false;
	}

	public void setRunWhenDisabled(boolean b) {
	}

	public void start() {
	}

}