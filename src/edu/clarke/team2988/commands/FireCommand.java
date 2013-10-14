package edu.clarke.team2988.commands;

import edu.clarke.team2988.subsystems.CompressorSubsystem;
import edu.wpi.first.wpilibj.command.Scheduler;


/**
 *
 * This class is responsible for handling joystick trigger event to shoot the
 * Frisbee by activating the ram which is controlled by the ShooterSubsystem.
 * <p>
 * This command is instantiated within the OI (Operator Interface) class at
 * startup and never terminates.
 *
 * @author Micheal Dowling
 * @see OI {@link edu.clarke.team2988.framework.OI OI}
 * @see Subsystem {@link edu.wpi.first.wpilibj.command.Subsystem Subsystem}
 *
 */
public class FireCommand extends CommandBase {

    boolean m_finished = false;
    boolean m_fireActive = false;
    private static edu.wpi.first.wpilibj.Timer m_ExtendTimer =
            new edu.wpi.first.wpilibj.Timer();
    private static double m_TimerRetractDelayValue = 0.3;
    private CompressorSubsystem m_compressorSubsystem = null;

    /* ---------------------------------------------------------------------- */
    /**
     * Constructor for FireCommand.
     * Specifies a requirement for the Compressor subsystem. compressorSubsystem
     * is created in {@link CommandBase CommandBase}. requires() is necessary
     * to prevent potential conflicts caused by more than one command
     * attempting to use the same subsystem at the same time.
     */
    public FireCommand() {
        m_compressorSubsystem = CommandBase.getCompressorSubsystem();
        requires(m_compressorSubsystem);


    }  /* FireCommand(); */

    /* ---------------------------------------------------------------------- */

    /**
     * Called just before this Command runs the first time. Any initialization
     * that needs to be performed prior to using this command should be
     * performed here. This method implements the abstract
     * {@link edu.wpi.first.wpilibj.command.Command#initialize() initialize()} method from the
     * {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */
    protected void initialize() {
        CommandBase.getCompressorSubsystem().retractFire();
        m_fireActive = false;
        m_finished = false;
        m_ExtendTimer.reset();
        m_ExtendTimer.start();

    }  /* initialize(); */


    /* ---------------------------------------------------------------------- */
    /**
     * Called repeatedly when this Command is scheduled to run. This method
     * implements the abstract {@link edu.wpi.first.wpilibj.command.Command#execute() execute()} method from
     * the {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */
    protected void execute() {

        if (m_compressorSubsystem.inUse() == false) {

            m_compressorSubsystem.extendFire();
            m_fireActive = true;

        }
//        else {
//
//            if (m_fireActive == false)
//            m_finished = true;
//
//        }  /* else m_compressorSubsystem.inUse WAS TRUE */

        if (m_fireActive == true && (m_ExtendTimer.get() > m_TimerRetractDelayValue)) {

            m_finished = true;

        }  /* if (m_fireActive == true &&
        (m_ExtendTimer.get() > m_TimerRetractDelayValue)) */

    }  /* execute(); */

    /* ---------------------------------------------------------------------- */

    /**
     * Called to determine if command needs to continue to run. When the
     * command is command is finished, make sure this method returns true.
     * This method implements the abstract {@link edu.wpi.first.wpilibj.command.Command#isFinished() isFinished()}
     * method from the {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */
    protected boolean isFinished() {
        return m_finished;


    } /* ---------------------------------------------------------------------- */ /**
     * Called once after isFinished returns true. This method implements the
     * abstract {@link edu.wpi.first.wpilibj.command.Command#end() end()} method from the
     * {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */
    protected void end() {
        m_compressorSubsystem.retractFire();


    } /* ---------------------------------------------------------------------- */ /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run. This method implements the
     * abstract {@link edu.wpi.first.wpilibj.command.Command#interrupted() interrupted()} method from the
     * {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */
    protected void interrupted() {
        m_compressorSubsystem.retractFire();


    }

    /* ---------------------------------------------------------------------- */
    /**
     * Any processing that's required to be performed upon stop should be
     * done here.
     */
    public void stop() {
        m_finished = true;


    } /* ---------------------------------------------------------------------- */ /**
     * Any processing that's required to be performed upon cancel should be
     * done here.
     */
    protected void canel() {
        m_finished = true;

    }
    public void runFireCommand() {
        Scheduler.getInstance().run();

    }
}
