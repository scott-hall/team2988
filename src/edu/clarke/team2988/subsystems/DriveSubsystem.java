/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.subsystems;

import edu.clarke.team2988.framework.Sidecar;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 * @author novake
 */
public class DriveSubsystem extends Subsystem {

    private Jaguar m_leftJaguar = new Jaguar(Sidecar.PWMOUT_2);
    private Jaguar m_rightJaguar = new Jaguar(Sidecar.PWMOUT_1);

    /* ---------------------------------------------------------------------- */
    public DriveSubsystem() {

        SmartDashboard.putNumber("Left Motor", 0.0);
        SmartDashboard.putNumber("Right Motor", 0.0);

    }  /* DriveSubsystem(); */

    /* ---------------------------------------------------------------------- */

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new DriveCommand());
    }

    /* ---------------------------------------------------------------------- */
    public void drive(double leftValue, double rightValue) {

        /* ------------------------------------------------------------------ */
        /*  WE'RE IMPOSING A WINDOW ON THE VALUES PASSED IN FROM THE          */
        /*  JOYSTICKS. THE 0.05  VALUE WAS ARBITRARILY CHOOSEN TO PREVENT     */
        /*  TO KEEP THE JAGUARS FROM GOING INTO A FAULT CONDITION CAUSED BY   */
        /*  TRYING TO OVERCOME MOTOR FRICTION AND INERTIAL IN A STOPPED       */
        /*  MOTOR. THIS ALSO SAVES BATTERY POWER AS WELL. IF THE VALUE        */
        /*  PASSED IS LESS THAN 0.05, ZERO WILL BE PASSED TO THE JAGUAR       */
        /* ------------------------------------------------------------------ */
        if (leftValue < 0.05 && leftValue > -0.05) {

            leftValue = 0.0;

        }  /* if (leftValue < 0.05 && leftValue > -0.05) */

        m_leftJaguar.set(-leftValue);

        if (rightValue < 0.05 && rightValue > -0.05) {

            rightValue = 0.0;

        }  /* if (rightValue < 0.05 && rightValue > -0.05) */

        m_rightJaguar.set(rightValue);

        SmartDashboard.putNumber("Left Motor", -leftValue);
        SmartDashboard.putNumber("Right Motor", -rightValue);

    }  /* drive(leftValue, rightValue); */


    public void brake() {

        m_rightJaguar.set(0);
        m_leftJaguar.set(0);

    }  /* brake(); */

    /* ---------------------------------------------------------------------- */
}  /* class DriveSubsystem */
