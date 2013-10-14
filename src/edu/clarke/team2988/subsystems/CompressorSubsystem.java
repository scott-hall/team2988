package edu.clarke.team2988.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import edu.clarke.team2988.commands.CompressorCommand;
import edu.clarke.team2988.commands.CommandBase;
import edu.clarke.team2988.framework.OI;
import edu.clarke.team2988.framework.Sidecar;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Compressor;

/**
 *
 * @author Michael Dowling
 * @author Dennis Heflin
 * @author Emily Snyder
 */
public class CompressorSubsystem extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    private boolean m_compressorSubInUse;
    private boolean m_isExtended;
    private Relay m_relay;
    private Compressor m_compressor = new Compressor(Sidecar.DIGITALIO_14, Sidecar.RELAY_1);
    private static Solenoid m_fireSolenoid;
    private static Solenoid m_fireRetractSolenoid;
    private static Solenoid m_leftLifterExtendSolenoid;
    private static Solenoid m_leftLifterRetractSolenoid;
    private static Timer m_ExtendTimer = new Timer();
    private static double m_TimerRetractDelayValue = 0.3;

    /* ---------------------------------------------------------------------- */
    public CompressorSubsystem() {

        m_fireSolenoid = new Solenoid(1);
        m_fireRetractSolenoid = new Solenoid(2);

        m_leftLifterExtendSolenoid = new Solenoid(3);
        m_leftLifterRetractSolenoid = new Solenoid(4);



        /* ------------------------------------------------------------------ */
        /*  ENSURE LIFTER IS RETRACTED                                        */
        /* ------------------------------------------------------------------ */
        retractLifter();
        m_compressorSubInUse = false;
        retractFire();
        m_isExtended = false;

    }  /* CompressorSubsystem(); */

    /* ---------------------------------------------------------------------- */

    public boolean inUse() {
        return m_compressorSubInUse;
    }

    /* ---------------------------------------------------------------------- */
    public boolean isMaxPSI() {
        return m_compressor.getPressureSwitchValue();
    }

    /* ---------------------------------------------------------------------- */
    public void runCompressor() {
        m_compressor.start();
    }

    /* ---------------------------------------------------------------------- */
    public void stopCompressor() {

        m_compressor.stop();

    }  /* stopCompressor(); */

    /* ---------------------------------------------------------------------- */

    public void extendFire() {

        m_fireRetractSolenoid.set(false);

        /* ------------------------------------------------------------------ */
        /*  WAIT LOOP FOR ELECTROMECHANICAL SETTLING. THE INCREMENT OF THE    */
        /*  VARIABLE IS DONE INSIDE THE LOOP TO ENSURE THAT THE LOOP WON'T    */
        /*  OUTIMIZED OUT RESULTING IN NO DELAY AT ALL.                       */
        /* ------------------------------------------------------------------ */
        for (int i = 0; i < 1000;) {
            ++i;
        }  /* for (int i = 0; i < 1000;) */
        m_fireSolenoid.set(true);
        m_compressorSubInUse = true;
        m_isExtended = true;

    }  /* extendFire(); */

    /* ---------------------------------------------------------------------- */

    public void retractFire() {

        m_fireSolenoid.set(false);

        /* ------------------------------------------------------------------ */
        /*  WAIT LOOP FOR ELECTROMECHANICAL SETTLING. THE INCREMENT OF THE    */
        /*  VARIABLE IS DONE INSIDE THE LOOP TO ENSURE THAT THE LOOP WON'T    */
        /*  OUTIMIZED OUT RESULTING IN NO DELAY AT ALL.                       */
        /* ------------------------------------------------------------------ */
        for (int i = 0; i < 1000;) {
            ++i;
        }  /* for (int i = 0; i < 1000;) */
        m_fireRetractSolenoid.set(true);
        m_compressorSubInUse = false;
        m_isExtended = false;
    }  /* retractFire(); */


    /* ---------------------------------------------------------------------- */
    public void extendLifter() {

        m_compressorSubInUse = true;

        /* ------------------------------------------------------------------ */
        /*                                                                    */
        /*  - - - - - - - - - - - -  C A U T I O N   - - - - - - - - - - - -  */
        /*  THE ORDER OF ENERGIZE/DE-ENERGIZE IS  _V E R Y_ IMPORTANT. THE    */
        /*  SOLENOID THAT IS CURRENTLY ENERGIZED _M U S T_ BE DE-ENERGIZED    */
        /*  _B E F O R E_ ENERGIZING THE OPPOSITE DIRECTION SOLENOID.         */
        /*  FAILURE TO DO THIS MAY RESULT IN DAMAGING OR DESTROYING ONE       */
        /*  OF THE SOLENOIDS.
        /*  - - - - - - - - - - - -  C A U T I O N   - - - - - - - - - - - -  */
        /*                                                                    */
        /* ------------------------------------------------------------------ */
        m_leftLifterRetractSolenoid.set(false);


        /* ------------------------------------------------------------------ */
        /*  WAIT LOOP FOR ELECTROMECHANICAL SETTLING. THE INCREMENT OF THE    */
        /*  VARIABLE IS DONE INSIDE THE LOOP TO ENSURE THAT THE LOOP WON'T    */
        /*  OUTIMIZED OUT RESULTING IN NO DELAY AT ALL.                       */
        /* ------------------------------------------------------------------ */
        for (int i = 0; i < 1000;) {
            ++i;
        }  /* for (int i = 0; i < 1000;) */

        m_leftLifterExtendSolenoid.set(true);


    }  /* extendLifter(); */


    /* ---------------------------------------------------------------------- */
    public void retractLifter() {

        /* ------------------------------------------------------------------ */
        /*                                                                    */
        /*  - - - - - - - - - - - -  C A U T I O N   - - - - - - - - - - - -  */
        /*  THE ORDER OF ENERGIZE/DE-ENERGIZE IS  _V E R Y_ IMPORTANT. THE    */
        /*  SOLENOID THAT IS CURRENTLY ENERGIZED _M U S T_ BE DE-ENERGIZED    */
        /*  _B E F O R E_ ENERGIZING THE OPPOSITE DIRECTION SOLENOID.         */
        /*  FAILURE TO DO THIS MAY RESULT IN DAMAGING OR DESTROYING ONE       */
        /*  OF THE SOLENOIDS.
        /*  - - - - - - - - - - - -  C A U T I O N   - - - - - - - - - - - -  */
        /*                                                                    */
        /* ------------------------------------------------------------------ */
        m_leftLifterExtendSolenoid.set(false);


        /* ------------------------------------------------------------------ */
        /*  WAIT LOOP FOR ELECTROMECHANICAL SETTLING. THE INCREMENT OF THE    */
        /*  VARIABLE IS DONE INSIDE THE LOOP TO ENSURE THAT THE LOOP WON'T    */
        /*  OUTIMIZED OUT RESULTING IN NO DELAY AT ALL.                       */
        /* ------------------------------------------------------------------ */
        for (int i = 0; i < 1000;) {
            ++i;
        }  /* for (int i = 0; i < 1000;) */

        m_leftLifterRetractSolenoid.set(true);

        m_compressorSubInUse = false;

    }  /* retractLifter(); */

    /* ---------------------------------------------------------------------- */

    public void extendFire_1() {

        m_fireRetractSolenoid.set(false);

        /* ------------------------------------------------------------------ */
        /*  WAIT LOOP FOR ELECTROMECHANICAL SETTLING. THE INCREMENT OF THE    */
        /*  VARIABLE IS DONE INSIDE THE LOOP TO ENSURE THAT THE LOOP WON'T    */
        /*  OUTIMIZED OUT RESULTING IN NO DELAY AT ALL.                       */
        /* ------------------------------------------------------------------ */
        for (int i = 0; i < 1000;) {
            ++i;
        }  /* for (int i = 0; i < 1000;) */
        m_fireSolenoid.set(true);
        m_compressorSubInUse = true;
        m_ExtendTimer.start();
        m_ExtendTimer.reset();

    }  /* extendFire(); */

    /* ---------------------------------------------------------------------- */

    public void retractFire_1() {

        if (m_ExtendTimer.get() < m_TimerRetractDelayValue) {
            return;
        } else {
            m_fireSolenoid.set(false);

            /* ------------------------------------------------------------------ */
            /*  WAIT LOOP FOR ELECTROMECHANICAL SETTLING. THE INCREMENT OF THE    */
            /*  VARIABLE IS DONE INSIDE THE LOOP TO ENSURE THAT THE LOOP WON'T    */
            /*  OUTIMIZED OUT RESULTING IN NO DELAY AT ALL.                       */
            /* ------------------------------------------------------------------ */
            for (int i = 0; i < 1000;) {
                ++i;
            }  /* for (int i = 0; i < 1000;) */
            m_fireRetractSolenoid.set(true);
            m_compressorSubInUse = false;
        }
    }  /* retractFire(); */


    /* ---------------------------------------------------------------------- */
    public void isExtend() {

    }  /* */

    /* ---------------------------------------------------------------------- */

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }  /* initDefaultCommand(); */

}
