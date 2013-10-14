/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.commands;

import edu.clarke.team2988.framework.OI;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 *
 * @author novake
 */
public class ShootStopCommand extends CommandBase {

    boolean m_finished = false;
    int m_time = 0;
    static JoystickButton m_shooterMotorButton = null;

    public ShootStopCommand() {
        requires(CommandBase.getShooterSubsystem());
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        m_finished = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        CommandBase.getShooterSubsystem().stop();
        m_finished = true;
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return m_finished;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
