package edu.clarke.team2988.framework;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.Joystick;
import edu.clarke.team2988.commands.CommandBase;
import edu.clarke.team2988.commands.ShootCommand;
import edu.clarke.team2988.commands.CompressorCommand;
import edu.clarke.team2988.commands.FireCommand;
import edu.clarke.team2988.commands.BrakeCommand;
import edu.clarke.team2988.commands.LifterCommand;
import edu.clarke.team2988.commands.ShootStopCommand;
import edu.clarke.team2988.commands.CameraCommand;
import edu.clarke.team2988.commands.AxisShootCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 * 
 * <p>
 * <strong>Creating Buttons</strong>
 * One type of button is a joystick button which is any button on a joystick.
 * You create one by telling it which joystick it's on and which button
 * number it is.
 *
 * @author Micheal Dowling
 * @author Emily Snyder
 * @author Dennis Heflin
 * @see edu.wpi.first.wpilibj.command.Subsystem
 * @see CommandBase
 */
public class OI {

    static private Joystick m_leftStick = new Joystick(1);
    static private Joystick m_rightStick = new Joystick(2);
    static private JoystickButton m_rightButton_4 = null;
    static private JoystickButton m_rightButton_5 = null;
    static private JoystickButton m_rightButton_8 = null;
    static private JoystickButton m_leftTriggerButton = null;
    static private JoystickButton m_rightTriggerButton = null;
    static private JoystickButton m_leftButton_3 = null;
    static private JoystickButton m_leftButton_7 = null;
    static private ShootCommand shootCommand = new ShootCommand();
    static private FireCommand fireCommand = new FireCommand();
    static private LifterCommand lifterCommand = new LifterCommand();
    static private ShootStopCommand shootStopCommand = new ShootStopCommand();
    static private CompressorCommand compressorCommand = new CompressorCommand();
    static private AxisShootCommand axisShootCommand = new AxisShootCommand();
    static private BrakeCommand brakeCommand = new BrakeCommand();
//    static private CameraCommand cameraCommand = new CameraCommand();
    static private CameraCommand cameraCommand = null;


    /* ---------------------------------------------------------------------- */
    // Another type of button you can create is a DigitalIOButton, which is
    // a button or switch hooked up to the cypress module. These are useful if
    // you want to build a customized operator interface.
    // Button button = new DigitalIOButton(1);
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:

    /* ---------------------------------------------------------------------- */
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    public OI() {

        m_rightButton_4 = new JoystickButton(m_rightStick, Buttons.BUTTON_4);
        m_rightButton_4.whenPressed(axisShootCommand);

        m_rightButton_8 = new JoystickButton(m_rightStick, Buttons.BUTTON_8);
        m_rightButton_8.whenPressed(shootCommand);

        m_leftTriggerButton = new JoystickButton(m_leftStick, Buttons.TRIGGER);
        m_leftTriggerButton.whileHeld(brakeCommand);

        m_rightTriggerButton = new JoystickButton(m_rightStick, Buttons.TRIGGER);
        m_rightTriggerButton.whenPressed(fireCommand);

        m_rightButton_5 = new JoystickButton(m_rightStick, Buttons.BUTTON_5);
        m_rightButton_5.whenPressed(shootStopCommand);

        m_leftButton_3 = new JoystickButton(m_leftStick, Buttons.BUTTON_3);
        m_leftButton_3.whileHeld(lifterCommand);

        m_leftButton_7 = new JoystickButton(m_leftStick, Buttons.BUTTON_7);

        if (TunableConstants.USE_CAMERA == true) {

            cameraCommand = CommandBase.getCameraCommand();
            m_leftButton_7.whenPressed(cameraCommand);

        }  /* if (TunableConstants.USE_CAMERA == true) */

        m_rightStick = new Joystick(2);
        m_leftStick = new Joystick(1);

    }  /* OI(); */

    /* ---------------------------------------------------------------------- */

    public static ShootCommand getShootCommand() {

        return shootCommand;

    }  /* shootCommand = getShootCommand(); */

    /* ---------------------------------------------------------------------- */

    public static ShootStopCommand getShootStopCommand() {

        return shootStopCommand;

    }  /* shootStopCommand = getShootStopCommand(); */

    /* ---------------------------------------------------------------------- */

    public static FireCommand getFireCommand() {

        return fireCommand;

    }  /* fireCommand = getFireCommand(); */

    /* ---------------------------------------------------------------------- */

    public static CompressorCommand getCompressorCommand() {

        return compressorCommand;

    }

    /* ---------------------------------------------------------------------- */
    public static CameraCommand getcameraCommand() {

        return cameraCommand;

    }

    /* ---------------------------------------------------------------------- */
//    public static AxisShootCommand getaxisShootCommand() {
//
//        return axisShootCommand;
//
//    }
//
//    /* ---------------------------------------------------------------------- */
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    //button.whileHeld(new ExampleCommand());

    /* ---------------------------------------------------------------------- */
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    //button.whenReleased(new ExampleCommand());
}
