/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.Servo;
import edu.clarke.team2988.framework.Sidecar;

/**
 *
 * @author Michael Dowling
 * @author Emily Snyder
 */
public class CameraSubsystem extends Subsystem {

    private static final AxisCamera m_camera = AxisCamera.getInstance();
    private Servo m_panServo = null;
    private Servo m_tiltServo = null;

    public CameraSubsystem() {

        m_panServo = new Servo(Sidecar.PWMOUT_5);
        m_tiltServo = new Servo(Sidecar.PWMOUT_4);

        /* ------------------------------------------------------------------ */
        /*  THESE VALUES POSITION THE CAMERA PAN/TILT MOUNT SO THAT THE CAMERA*/
        /*  IMAGE IS SET UP TO SHOOT THE 3-POINT GOALS.                       */
        /* ------------------------------------------------------------------ */
//        System.out.println("Pan Position" + m_panServo.get());
//        System.out.println("Pan Angle" + m_panServo.getAngle());
        m_panServo.set(0.385);
        m_tiltServo.set(0.4);
        System.out.println("Pan Position" + m_panServo.get());
        System.out.println("Pan Angle" + m_panServo.getAngle());
    }

    public static AxisCamera getCamera() {

        return m_camera;

    }

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}
