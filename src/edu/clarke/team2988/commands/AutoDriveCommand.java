/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.commands;

import edu.clarke.team2988.subsystems.CompressorSubsystem;
import edu.clarke.team2988.subsystems.ShooterSubsystem;
import edu.clarke.team2988.commands.CommandBase;
import edu.clarke.team2988.commands.FireCommand;
import edu.clarke.team2988.framework.OI;
import edu.clarke.team2988.framework.RobotTemplate;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;

/**
 *
 * @author Dennis, Emily
 */
public class AutoDriveCommand extends CommandBase {

    private static ShooterSubsystem m_shooterSubsystem = null;
    private static CompressorSubsystem m_compressorSubsystem = null;
    private static FireCommand m_fireCommand = null;
    private static boolean started = false;
    private static Timer m_speedUpTimer = new Timer();
    private static Timer m_fireTimer = new Timer();
    private static double m_shootDelay = 3.7;
    private static double m_fireDelay = 0.3;
    private static int m_frisbeeCounter = 0;
    boolean m_finished = false;


    /* ---------------------------------------------------------------------- */
    /**
     * Constructor for AutoDriveCommand.
     * Specifies a requirement for the Drive subsystem. driveSubsystem
     * is created in {@link CommandBase CommandBase}. This command is used in
     * autonomous mode only.
     */
    public AutoDriveCommand() {

        if (started == false) {

            started = true;

        }  /* if (started == false) */


    }  /* AutoDriveCommand(); */

    /* ---------------------------------------------------------------------- */

    // Called just before this Command runs the first time
    protected void initialize() {
//        CommandBase.getShooterSubsystem().shoot();
        m_speedUpTimer.reset();
        m_speedUpTimer.start();
        m_frisbeeCounter = 0;
        m_fireCommand = OI.getFireCommand();
        if (m_fireCommand == null) {
            System.out.println("NULL");
        }
    }


    /* ---------------------------------------------------------------------- */
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (RobotTemplate.BaseContext.isAutonomous()
                && RobotTemplate.BaseContext.isEnabled()) {

            CommandBase.getShooterSubsystem().shoot();

            if (m_speedUpTimer.get() < m_shootDelay) {
                return;
            }
            if (m_frisbeeCounter == 3) {
                m_finished = true;
                return;
            }
//            m_fireCommand.runFireCommand();
            m_fireCommand.start();
            m_speedUpTimer.reset();
            m_frisbeeCounter++;

        }
        SmartDashboard.putString("Auto Running", "True");
    }

    /* ---------------------------------------------------------------------- */
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (RobotTemplate.BaseContext.isAutonomous()
                && RobotTemplate.BaseContext.isEnabled()) {

            return m_finished;


        } else {

            CommandBase.getShooterSubsystem().stop();
            SmartDashboard.putString("Auto Running", "False");
            return true;

        }  /* else AUTONOMOUS OR ENABLED WAS FALSE */

    }  /* isFinished(); */

    /* ---------------------------------------------------------------------- */

    // Called once after isFinished returns true
    protected void end() {

        CommandBase.getShooterSubsystem().stop();

    }  /* end(); */


    /* ---------------------------------------------------------------------- */
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
