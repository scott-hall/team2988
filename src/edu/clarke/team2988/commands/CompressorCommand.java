/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.commands;


import edu.clarke.team2988.framework.Sidecar;
import edu.clarke.team2988.subsystems.CompressorSubsystem;


import edu.wpi.first.wpilibj.Compressor;

    // Called just before this Command runs the first time
/**
 *
 * @author Michael Dowling
 * @author Emily Snyder
 * @author Dennis Heflin
 */
public class CompressorCommand extends CommandBase {

    private static boolean started = false;
    private static CompressorSubsystem compressorSubsystem = CommandBase.getCompressorSubsystem();

    public CompressorCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        if(!started) {
            this.start();
        compressorSubsystem = CommandBase.getCompressorSubsystem();

        }
//        requires(CommandBase.compressorSubsystem);
    }
    
    protected void initialize(){
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if (compressorSubsystem.isMaxPSI() == false){
        compressorSubsystem.runCompressor();
        } else {
            compressorSubsystem.stopCompressor();
        }
     }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }


    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}