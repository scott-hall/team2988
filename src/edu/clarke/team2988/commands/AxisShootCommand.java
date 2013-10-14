/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.commands;

import edu.clarke.team2988.subsystems.ShooterSubsystem;
import edu.clarke.team2988.framework.RobotTemplate;
import edu.clarke.team2988.framework.OI;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 * @author novake
 */
public class AxisShootCommand extends CommandBase {

    static Joystick m_rightStick = new Joystick(2);
    private static ShooterSubsystem shooterSubsystem = CommandBase.getShooterSubsystem();
    private static boolean started = false;

    public AxisShootCommand() {
        shooterSubsystem = CommandBase.getShooterSubsystem();
        requires(shooterSubsystem);
        if (started == false) {
//            this.start();
//            started = true;
        }  /* if (started == false) */
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        started = true;

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (RobotTemplate.BaseContext.isOperatorControl()
                && RobotTemplate.BaseContext.isEnabled()) {
            shooterSubsystem.Axis(m_rightStick.getAxis(Joystick.AxisType.kZ));
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (RobotTemplate.BaseContext.isOperatorControl()
                && RobotTemplate.BaseContext.isEnabled()) {

            return false;

        } else {

            return true;

        }/* isFinished(); */
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        started = false;
    }
}
