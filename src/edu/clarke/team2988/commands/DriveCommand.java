/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.commands;

import edu.clarke.team2988.subsystems.DriveSubsystem;
import edu.clarke.team2988.framework.RobotTemplate;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 * @author novake
 */
public class DriveCommand extends CommandBase {

    static Joystick m_leftStick = new Joystick(1);
    static Joystick m_rightStick = new Joystick(2);
    private static boolean started = false;
    private static DriveSubsystem driveSubsystem = CommandBase.getDriveSubsystem();


    /* ---------------------------------------------------------------------- */

    public DriveCommand() {

        driveSubsystem = CommandBase.getDriveSubsystem();
        requires(driveSubsystem);
        SmartDashboard.putString("Driving", "False");

        if (started == false) {

            this.start();
            started = true;

        }  /* if (started == false) */

    }

    /* ---------------------------------------------------------------------- */

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    /* ---------------------------------------------------------------------- */

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (RobotTemplate.BaseContext.isEnabled() &&
            RobotTemplate.BaseContext.isOperatorControl()) {

            SmartDashboard.putString("Driving", "True");
            driveSubsystem.drive(m_leftStick.getY(), m_rightStick.getY());
        }  /* if (RobotTemplate.BaseContext.isEnabled() &&
                  RobotTemplate.BaseContext.isOperatorControl()) */

    }  /* execute(); */

    /* ---------------------------------------------------------------------- */

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {

        if (RobotTemplate.BaseContext.isOperatorControl() &&
             RobotTemplate.BaseContext.isEnabled()) {

          return false;

        } else {

            SmartDashboard.putString("Driving", "False");
            return true;

        }

     }  /* isFinished(); */


    /* ---------------------------------------------------------------------- */

    // Called once after isFinished returns true
    protected void end() {

        started = false;

    }  /* end(); */

    /* ---------------------------------------------------------------------- */

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
