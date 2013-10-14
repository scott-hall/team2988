/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.commands;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import edu.clarke.team2988.framework.OI;
import edu.clarke.team2988.subsystems.CompressorSubsystem;

/**
 *
 * @author Dennis
 */
public class LifterCommand extends CommandBase {

    boolean m_lifterActive = false;
    boolean m_finished = false;
    private CompressorSubsystem m_compressorSubsystem = null;

    public LifterCommand() {
        SmartDashboard.putString("Lifter Running", "False");
        m_compressorSubsystem = CommandBase.getCompressorSubsystem();
        requires(m_compressorSubsystem);

        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        m_finished = false;
        m_lifterActive = false;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (m_lifterActive == false
                && m_compressorSubsystem.inUse() == false) {
            m_compressorSubsystem.extendLifter();

            SmartDashboard.putString("Lifter Running", "True");
        }
//        else {
//            m_finished = true;
//        }

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (m_finished == true) {
            SmartDashboard.putString("Lifter Running", "False");
        }

        return m_finished;
    }

    // Called once after isFinished returns true
    protected void end() {
        m_compressorSubsystem.retractLifter();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
        m_finished = true;
    }

    public void stop() {
        m_finished = true;
    }

    public void cancel() {
        m_finished = true;
    }
}
