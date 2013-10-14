package edu.clarke.team2988.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;


import edu.clarke.team2988.framework.OI;

import edu.clarke.team2988.subsystems.ShooterSubsystem;
import edu.clarke.team2988.subsystems.DriveSubsystem;
import edu.clarke.team2988.subsystems.CompressorSubsystem;
import edu.clarke.team2988.subsystems.CameraSubsystem;
import edu.clarke.team2988.framework.TunableConstants;

/**
 * The base for all commands. All atomic commands should subclass CommandBase.
 * CommandBase stores creates and stores each control system. To access a
 * subsystem elsewhere in your code in your code use the getters provided in
 * this class. The getters are implemented as "public static" methods so
 * an instance of this class is not required to access them.
 * <p>
 * Example:
 *      CommandBase.getShooterSubsystem();
 *
 * <p>For questions, see one of the authors</p>
 *
 * @author Micheal Dowling
 * @author Emily Snyder
 * @author Dennis Heflin
 * @see Subsystem {@link edu.wpi.first.wpilibj.command.Subsystem Subsystem}
 * @see OI {@link OI OI}
 */
public abstract class CommandBase extends Command {

    /* --------------------------------------------------------------------- */
    /*  OUR OPERATOR INTERFACE CLASS. COMMANDS THAT ARE TIED TO BUTTONS ARE  */
    /*  INSTANTIATED IN OI.                                                  */
    /* --------------------------------------------------------------------- */
    public static OI oi;
    /* --------------------------------------------------------------------- */
    /*  CREATE A SINGLE STATIC INSTANCE OF ALL OF OUR SUBSYSTEMS             */
    /* --------------------------------------------------------------------- */
    private static ShooterSubsystem m_shooterSubsystem = new ShooterSubsystem();
    private static CompressorSubsystem m_compressorSubsystem = new CompressorSubsystem();
    private static CompressorCommand m_compressorCommand = new CompressorCommand();
    private static DriveSubsystem m_driveSubsystem = new DriveSubsystem();
    private static DriveCommand m_driveCommand = new DriveCommand();
//    private static AxisShootCommand m_axisShootCommand = new AxisShootCommand();
    private static CameraSubsystem m_cameraSubsystem = null;
    private static CameraCommand m_cameraCommand = null;
//    private static CameraSubsystem m_cameraSubsystem = new CameraSubsystem();
//    private static CameraCommand m_cameraCommand = new CameraCommand();


    /* ---------------------------------------------------------------------- */
    /**
     * Constructor for CommandBase with the given name.
     * @param name the name for this command
     */
    public CommandBase(String name) {

        super(name);

    }  /* CommandBase(name); */


    /* ---------------------------------------------------------------------- */
    /**
     * Constructor for CommandBase.
     */
    public CommandBase() {

        super();

    }  /* CommandBase(); */


    /* ---------------------------------------------------------------------- */
    public static void init() {

        /* ------------------------------------------------------------------ */
        /*  THIS  M U S T  BE HERE. IF THE OI CREATES COMMANDS (WHICH IT      */
        /*  VERY LIKELY WILL), CONSTRUCTING IT DURING THE CONSTRUCTION OF     */
        /*  COMMANDBASE (FROM WHICH COMMANDS EXTEND), SUBSYSTEMS ARE NOT      */
        /*  GUARANTEED TO BE YET. THUS, THEIR REQUIRES() STATEMENTS MAY GRAB  */
        /*  NULL POINTERS. BAD NEWS. DON'T MOVE IT.                           */
        /* ------------------------------------------------------------------ */
        if (TunableConstants.USE_CAMERA == true) {

            m_cameraSubsystem = new CameraSubsystem();
            m_cameraCommand = new CameraCommand();

        }  /* if (TunableConstants.USE_CAMERA == true) */

        oi = new OI();
        new LiveWindow();
        LiveWindow.setEnabled(true);

    }  /* init(); */


    /* ---------------------------------------------------------------------- */
    /**
     * Public static getter for the {@link CompressorSubsystem CompressorSubsystem}.
     * @return an instance of {@link CompressorSubsystem CompressorSubsystem}
     */
    public static CompressorSubsystem getCompressorSubsystem() {

        return m_compressorSubsystem;

    }  /* getCompressorSubsystem() */


    /* ---------------------------------------------------------------------- */
    /**
     * Public static getter for the {@link ShooterSubsystem ShooterSubsystem}.
     * @return an instance of {@link ShooterSubsystem ShooterSubsystem}
     */
    public static ShooterSubsystem getShooterSubsystem() {

        return m_shooterSubsystem;

    }  /* getShooterSubsystem() */


    /* ---------------------------------------------------------------------- */
    /**
     * Public static getter for the {@link DriveSubsystem DriveSubsystem}.
     * @return an instance of {@link DriveSubsystem DriveSubsystem}
     */
    public static DriveSubsystem getDriveSubsystem() {

        return m_driveSubsystem;

    }  /* getDriveSubsystem() */


    /* ---------------------------------------------------------------------- */
    /**
     * Public static getter for the {@link DriveCommand DriveCommand}.
     * @return an instance of {@link DriveCommand DriveCommand}
     */
    public static DriveCommand getDriveCommand() {

        return m_driveCommand;

    }  /* getDriveCommand() */

    /* ---------------------------------------------------------------------- */
//    public static AxisShootCommand getAxisShootCommand() {
//
//        return m_axisShootCommand;
//
//    }  /* getDriveCommand() */

    /* ---------------------------------------------------------------------- */
    /**
     * Public static getter for the {@link CameraSubsystem CameraSubsystem}.
     * @return an instance of {@link CameraSubsystem CameraSubsystem}
     */
    public static CameraSubsystem getCameraSubsystem() {

        return m_cameraSubsystem;

    }  /* getCameraSubsystem() */

    /* ---------------------------------------------------------------------- */

    /**
     * Public static getter for the {@link CameraCommand CameraCommand}.
     * @return an instance of {@link CameraCommand CameraCommand}
     */
    public static CameraCommand getCameraCommand() {

        return m_cameraCommand;

    }  /* getCameraCommand() */

}  /* class CommandBase */
