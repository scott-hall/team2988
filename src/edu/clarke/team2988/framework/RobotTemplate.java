/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package edu.clarke.team2988.framework;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.DriverStationLCD;
import edu.clarke.team2988.commands.CommandBase;
import edu.clarke.team2988.commands.ShootCommand;
import edu.clarke.team2988.commands.ShootStopCommand;
import edu.clarke.team2988.commands.AutoDriveCommand;
import edu.clarke.team2988.commands.CameraCommand;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class RobotTemplate extends IterativeRobot {

    public static RobotTemplate BaseContext = null;
//    private Command autonomousCommand = null;
    private ShootCommand m_shootCommand = null;
    private ShootStopCommand m_shootStopCommand = null;
    private Joystick m_leftStick = new Joystick(1);
    private JoystickButton m_leftButton = null;
    private static AutoDriveCommand m_autoDriveCommand = null;
    private DriverStationLCD m_driverStationLCD = null;
    private DriverStationLCD.Line m_bottomLine = DriverStationLCD.Line.kUser6;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {

        /* ------------------------------------------------------------------ */
        /*  INITIALIZE ALL SUBSYSTEMS                                         */
        /* ------------------------------------------------------------------ */
        BaseContext = this;
        CommandBase.init();

    }

    /**
     * This function is called prior to entering autonomous mode. Any required
     *
     */
    public void autonomousInit() {

        m_autoDriveCommand = new AutoDriveCommand();
        m_autoDriveCommand.start();
        CameraCommand cameraCommand = CommandBase.getCameraCommand();
        if (cameraCommand != null) {
            cameraCommand.start();
        }
//        m_shootCommand = OI.getShootCommand();
//        m_shootCommand.start();
//        m_shootStopCommand.start();

    }  /* autonomousInit(); */


    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    /* ---------------------------------------------------------------------- */
    public void teleopInit() {

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.

        CommandBase.getDriveCommand().start();
        CommandBase.getShooterSubsystem().stop();
        CommandBase.getCompressorSubsystem().retractLifter();


    }  /* teleopInit(); */

    /* ---------------------------------------------------------------------- */

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {

        Scheduler.getInstance().run();

    }  /* teleopPeriodic(); */

    /* ---------------------------------------------------------------------- */

    public void disabledInit() {
        /* ------------------------------------------------------------------ */
        /*  THIS METHOD IS ONLY HERE TO STOP THE IterativeRobot CLASS FROM    */
        /*  COMPLAINING THAT WE HAVEN'T SUPPLIED AN OVERRIDE FOR THIS METHOD  */
        /* ------------------------------------------------------------------ */
    }  /* disabledInit(); */


    public void disabledPeriodic() {
        /* ------------------------------------------------------------------ */
        /*  THIS METHOD IS ONLY HERE TO STOP THE IterativeRobot CLASS FROM    */
        /*  COMPLAINING THAT WE HAVEN'T SUPPLIED AN OVERRIDE FOR THIS METHOD  */
        /* ------------------------------------------------------------------ */
    }  /* disabledPeriodic(); */


    public void disabledContinuous() {
        /* ------------------------------------------------------------------ */
        /*  THIS METHOD IS ONLY HERE TO STOP THE IterativeRobot CLASS FROM    */
        /*  COMPLAINING THAT WE HAVEN'T SUPPLIED AN OVERRIDE FOR THIS METHOD  */
        /* ------------------------------------------------------------------ */
    }  /* disabledContinuous(); */

    /* ---------------------------------------------------------------------- */

    public void autonomousContinuous() {
        /* ------------------------------------------------------------------ */
        /*  THIS METHOD IS ONLY HERE TO STOP THE IterativeRobot CLASS FROM    */
        /*  COMPLAINING THAT WE HAVEN'T SUPPLIED AN OVERRIDE FOR THIS METHOD  */
        /* ------------------------------------------------------------------ */
    }  /* autonomousContinuous(); */

    /* ---------------------------------------------------------------------- */

    public void teleopContinuous() {
        /* ------------------------------------------------------------------ */
        /*  THIS METHOD IS ONLY HERE TO STOP THE IterativeRobot CLASS FROM    */
        /*  COMPLAINING THAT WE HAVEN'T SUPPLIED AN OVERRIDE FOR THIS METHOD  */
        /* ------------------------------------------------------------------ */
    }  /* teleopContinuous(); */

}  /* class RobotTemplate */
