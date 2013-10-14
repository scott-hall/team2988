
package edu.clarke.team2988.commands;


/**
 *
 * This (inappropriately named) class is responsible for handling joystick
 * trigger event to shoot the Frisbee by activating the ram which is controlled
 * by the ShooterSubsystem.
 * <p>
 * This command is instantiated within the OI (Operator Interface) class at
 * startup and never terminates.
 *
 * @author Micheal Dowling
 * @author Emily Snyder
 * @author Dennis Heflin
 * @see edu.wpi.first.wpilibj.command.Command.Subsystem
 * @see CommandBase
 * @see edu.clarke.team2988.framework.OI
 *
 */

public class ShootCommand extends CommandBase {

    boolean m_finished = false;
    int m_time = 0;

    /* ---------------------------------------------------------------------- */

    /**
     * Constructor for ShootCommand.
     * Specifies a requirement for the Shooter subsystem. shooterSubsystem
     * is created in {@link CommandBase CommandBase}.
     */

    public ShootCommand() {

        requires(CommandBase.getShooterSubsystem());

    }  /* ShootCommand(); */

    /* ---------------------------------------------------------------------- */

    /**
     * Called just before this Command runs the first time. Any initialization
     * that needs to be performed prior to using this command should be
     * performed here. This method implements the abstract
     * {@link edu.wpi.first.wpilibj.command.Command#initialize() initialize()} method from the
     * {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */
    protected void initialize() {

    }  /* initialize(); */

    /* ---------------------------------------------------------------------- */

    /**
     * Called repeatedly when this Command is scheduled to run. This method
     * implements the abstract {@link edu.wpi.first.wpilibj.command.Command#execute() execute()} method from
     * the {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */

    public void execute() {

      CommandBase.getShooterSubsystem().shoot();

    }  /* execute(); */


    /* ---------------------------------------------------------------------- */

    /**
     * Since the (so called) shoot command is active as long as the robot is
     * running this method will <strong><ul>always</ul></strong> return false unless
     * {@link ShootCommand#stop() stop()}, {@link ShootCommand#interrupted()} or
     * {@link ShootCommand#cancel()} is called. This method implements the
     * abstract {@link edu.wpi.first.wpilibj.command.Command#isFinished() isFinished()} method from the
     * {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */

    protected boolean isFinished() {

        return m_finished;

    }  /* isFinished(); */

    /* ---------------------------------------------------------------------- */

    /**
     * Called once after isFinished returns true. This method implements the
     * abstract {@link edu.wpi.first.wpilibj.command.Command#end() end()} method from the
     * {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */

    protected void end() {

    }  /* end(); */

    /* ---------------------------------------------------------------------- */

    /**
     * Called when another command which requires one or more of the same
     * subsystems is scheduled to run. This method implements the
     * abstract {@link edu.wpi.first.wpilibj.command.Command#interrupted() interrupted()} method from the
     * {@link edu.wpi.first.wpilibj.command.Command Command} class.
     */

    protected void interrupted() {

        m_finished = true;

    }  /* interrupted(); */


    /* ---------------------------------------------------------------------- */

    /**
     * Any processing that's required to be performed upon stop should be
     * done here.
     */

    public void stop() {

        m_finished = true;

    }  /* stop(); */


    /* ---------------------------------------------------------------------- */

    /**
     * Any processing that's required to be performed upon cancel should be
     * done here.
     */

    public void cancel() {

        m_finished = true;

    }  /* canel(); */

}  /* class ShootCommand */

