package edu.clarke.team2988.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Jaguar;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import edu.clarke.team2988.commands.CommandBase;



import edu.clarke.team2988.framework.Sidecar;

/**
 *
 * @author Dowling
 * @author Snyder
 * @author Heflin
 * 
 */
public class ShooterSubsystem extends Subsystem {

    private static ShooterSubsystem m_shooterInstance = null;
    private Jaguar m_shooterMotor = new Jaguar(Sidecar.PWMOUT_3);
    double m_time = 0;
    private static Timer m_speedUpTimer = new Timer();
    private static double m_delay = 4;

    /*page ----------------------------------------------------------------- */
    public ShooterSubsystem() {

        /* ----------------------------------------------------------------- */
        /*  START THE COMMAND WHEN THE BUTTON IS PRESSED AND LET IT RUN THE  */
        /*  COMMAND UNTIL IT IS FINISHED AS DETERMINED BY IT'S IS FINISHED   */
        /*  METHOD.                                                          */
        /* ----------------------------------------------------------------- */
        SmartDashboard.putString("Shooter Running", "False");
        LiveWindow.addActuator("Shooter Subsytem", "Jag", m_shooterMotor);

    }  /* ShooterSubsystem(); */

    /* ---------------------------------------------------------------------- */

    public void shoot() {

        /* ------------------------------------------------------------------ */
        /*  PASSING 1 TO THE SET METHOD WILL CAUSE THE SHOOTER MOTOR TO RUN   */
        /*  AT FULL SPEED.                                                    */
        /* ------------------------------------------------------------------ */
//        for(int i = 1; i < 9; i += 2) {
//           Timer.delay(0.2);
//           m_shooterMotor.set((double) i / 10);
//        }
//        m_shooterMotor.set(0.30);
//        if (m_time < 1) {
//            m_time+=.01;
//            m_shooterMotor.set(m_time);
//        } else {
//            m_shooterMotor.set(1);
//        }
        m_shooterMotor.set(0.5);

        SmartDashboard.putString("Shooter Running", "True");

    }  /* shoot() */

    /* ---------------------------------------------------------------------- */

    public void stop() {

        /* ------------------------------------------------------------------ */
        /*  PASSING ZERO TO THE SET METHOD WILL CAUSE THE MOTOR TO RUN AT     */
        /*  FULL SPEED.                                                       */
        /* ------------------------------------------------------------------ */
        m_shooterMotor.set(0);
        SmartDashboard.putString("Shooter Running", "False");

    }  /* stop() */

    /* ---------------------------------------------------------------------- */

    public void Axis(double zAxisValue) {

        if (zAxisValue < 0.05) {

            zAxisValue = 0.0;

        }  /* if (rightValue < 0.05) */

        m_shooterMotor.set(zAxisValue);

        if (zAxisValue > 0.05) {

            SmartDashboard.putNumber("Shooter Speed", zAxisValue);

        }

    }  /* Axis() */

    /* ---------------------------------------------------------------------- */

    public void AutoShoot() {
//        m_speedUpTimer.start();
//
//        for (int i = 1; i < 4; i++) {
//            CommandBase.getShooterSubsystem().shoot();
//            if (m_speedUpTimer.get() >= m_delay) {
//                CommandBase.getCompressorSubsystem().extendFire();
//                CommandBase.getCompressorSubsystem().retractFire();
//                m_speedUpTimer.reset();
//            }
//        }
//
//        CommandBase.getShooterSubsystem().stop();

    }  /* AutoShoot */

    /* ---------------------------------------------------------------------- */

    protected void initDefaultCommand() {
    }  /* initDefaultCommand(); */

}  /*  class ShooterSubsystem */
