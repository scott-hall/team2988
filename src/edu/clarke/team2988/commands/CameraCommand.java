/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.clarke.team2988.commands;

import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.image.ColorImage;

import edu.clarke.team2988.subsystems.CameraSubsystem;
import edu.clarke.team2988.framework.RobotTemplate;


import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.camera.AxisCamera;
import edu.wpi.first.wpilibj.image.BinaryImage;
import edu.wpi.first.wpilibj.image.ColorImage;
import edu.wpi.first.wpilibj.image.CriteriaCollection;
import edu.wpi.first.wpilibj.image.NIVision.MeasurementType;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.image.ParticleAnalysisReport;
import edu.wpi.first.wpilibj.image.RGBImage;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DriverStationLCD;

/**
 *
 * @author novake
 */
public class CameraCommand extends CommandBase {

    private AxisCamera m_camera = null;
    private ColorImage m_currentImage = null;
    private CameraSubsystem m_cameraSubsystem = null;
    private DriverStationLCD m_driverStationLCD = null;
    private static Timer m_speedUpTimer = new Timer();
    private static double m_delay = 4;
    AxisCamera camera;          // the axis camera object (connected to the switch)
    CriteriaCollection cc;      // the criteria for doing the particle filter operation
    RobotTemplate m_baseContext = null;

    public CameraCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        m_cameraSubsystem = CommandBase.getCameraSubsystem();
        requires(m_cameraSubsystem);
        m_camera = m_cameraSubsystem.getCamera();
        cc = new CriteriaCollection();      // create the criteria for the particle filter
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_WIDTH, 30, 400, false);
        cc.addCriteria(MeasurementType.IMAQ_MT_BOUNDING_RECT_HEIGHT, 40, 400, false);
        m_baseContext = RobotTemplate.BaseContext;
//        SmartDashboard.putString("1 Points", "Nope");
//        SmartDashboard.putString("2 Points", "Nope");
//        SmartDashboard.putString("3 Points", "Nope");

    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        if (m_camera.freshImage() == false) {
//            try {
//
//                m_currentImage = m_camera.getImage();
//
//            } catch (AxisCameraException ex) {
//
//                ex.printStackTrace();
//
//            } catch (NIVisionException ex) {
//
//                ex.printStackTrace();
//
//            }  /* try  */
//
//        }

//        while (m_baseContext.isAutonomous() && m_baseContext.isEnabled()) {
        try {
            /**
             * Do the image capture with the camera and apply the algorithm described above. This
             * sample will either get images from the camera or from an image file stored in the top
             * level directory in the flash memory on the cRIO. The file name in this case is "10ft2.jpg"
             *
             */
            ColorImage image = m_camera.getImage();     // comment if using stored images
//                ColorImage image;                           // next 2 lines read image from flash on cRIO
//                image =  new RGBImage("/10ft2.jpg");
            BinaryImage thresholdImage = image.thresholdRGB(85, 115, 0, 255, 0, 10);   // keep only green objects
            BinaryImage bigObjectsImage = thresholdImage.removeSmallObjects(false, 2);  // remove small artifacts
            BinaryImage convexHullImage = bigObjectsImage.convexHull(false);          // fill in occluded rectangles
            BinaryImage filteredImage = convexHullImage.particleFilter(cc);           // find filled in rectangles
            ParticleAnalysisReport[] reports = filteredImage.getOrderedParticleAnalysisReports();  // get list of results
            for (int i = 0; i < reports.length; i++) {                                // print results
                ParticleAnalysisReport r = reports[i];
                //Here is the code we need to manipulate to set SmartDashboard values
                System.out.println("Particle: " + i + ":  Center of mass x: " + r.center_mass_x);
//                m_baseContext.lcdMessage("Particle: " + i + ":  Center of mass x: " + r.center_mass_x);
                //NOT REAL VALUES
//                if (i + r.center_mass_x > 100) {
//                    if (i + r.center_mass_y > 600) {
//                        SmartDashboard.putString("1 Points", "Good");
//                    } else {
//                        SmartDashboard.putString("1 Points", "Nope");
//                    }
//                } else if (i + r.center_mass_x > 600) {
//                    if (i + r.center_mass_y > 600) {
//                        SmartDashboard.putString("2 Points", "Good");
//                    } else {
//                        SmartDashboard.putString("2 Points", "Nope");
//                    }
//                } else if (i + r.center_mass_x > 876) {
//                    if (i + r.center_mass_y > 600) {
//                        SmartDashboard.putString("3 Points", "Good");
//                    } else {
//                        SmartDashboard.putString("3 Points", "Nope");
//                    }
//                }
            }
            System.out.println(filteredImage.getNumberParticles() + "  " + Timer.getFPGATimestamp());
            SmartDashboard.putNumber("Particles In Filtered Image", filteredImage.getNumberParticles());
            if (m_baseContext.isAutonomous()) {
//                if (filteredImage.getNumberParticles() > 0) {
//                    CommandBase.getShooterSubsystem().shoot();
//                } else {
//                    CommandBase.getShooterSubsystem().stop();
//                }
                
            }

            /**
             * all images in Java must be freed after they are used since they are allocated out
             * of C data structures. Not calling free() will cause the memory to accumulate over
             * each pass of this loop.
             */
            filteredImage.free();
            convexHullImage.free();
            bigObjectsImage.free();
            thresholdImage.free();
            image.free();

        } catch (AxisCameraException ex) {        // this is needed if the camera.getImage() is called
            ex.printStackTrace();
        } catch (NIVisionException ex) {
            ex.printStackTrace();
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
