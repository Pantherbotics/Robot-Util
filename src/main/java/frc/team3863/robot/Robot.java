/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.team3863.robot;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import frc.team3863.robot.subsystems.Vision;
import jaci.pathfinder.Pathfinder;
import jaci.pathfinder.Trajectory;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {
    public static HashMap<String, Trajectory> paths;
    public static final Vision vision = new Vision(Constants.kVisionBaud, Constants.kVisionPort);


    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     * <p>
     * Initalizes the Auton and Drivetrain SmartDashboard choosers, initalizes all
     * subsystem (that need initalization), and sets SmartDashboard defaults
     */
    @Override
    public void robotInit() {
    
        paths = collectPathsFromDirectory(Constants.PATH_LOCATION);
    
    }

     @Override
    public void robotPeriodic() {
       
    }
    /**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
     * the robot is disabled.
     */
    @Override
    public void disabledInit() {

    }

    /**
     * Re-calculate autonomous selection and update the smartdashboard when disabled
     */
    @Override
    public void disabledPeriodic() {

    }

    /**
     * This autonomous (along with the chooser code above) shows how to select
     * between different autonomous modes using the dashboard. The sendable
     * chooser code works with the Java SmartDashboard. If you prefer the
     * LabVIEW Dashboard, remove all of the chooser code and uncomment the
     * getString code to get the auto name from the text box below the Gyro
     *
     * <p>You can add additional auto modes by adding additional commands to the
     * chooser code above (like the commented example) or additional comparisons
     * to the switch structure below with additional strings & commands.
     */
    @Override
    public void autonomousInit() {

    }

    /**
     * This function is called periodically during autonomous.
     */
    @Override
    public void autonomousPeriodic() {

    }

    @Override
    public void teleopInit() {

    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {

    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {

    }

    public void outputPathsToDashboard(HashMap<String, Trajectory> paths, SendableChooser<String> chooser){
        System.out.println(paths.isEmpty());
        for(String key : paths.keySet()){
            System.out.println(key);
            chooser.addObject(key, key);
        }
    }

    public HashMap<String, Trajectory> collectPathsFromDirectory(String dir){
        HashMap<String, Trajectory> paths = new HashMap<>();
    
            ArrayList<File> filesInFolder = listf(dir);

            for(int i = filesInFolder.size()-1; i >=0 ; i--){
                File traj = filesInFolder.get(i);
                if (!traj.getName().contains("_source_Jaci.csv")){
                    filesInFolder.remove(i);
                }
            }
            for(File traj: filesInFolder){
                System.out.println(traj.getName());                                                                          //take all the File objects we just created & convert them into Trajectories to put into HashMap
                paths.put(traj.getName().replace("_source_Jaci.csv", ""), Pathfinder.readFromCSV(traj));
            }
            return paths;
    }


    public static ArrayList<File> listf(String directoryName) {
        File directory = new File(directoryName);

        // get all the files from a directory
        File[] fList = directory.listFiles();
        ArrayList<File> resultList = new ArrayList<File>(Arrays.asList(fList));
        for (File file : fList) {
            if (file.isFile()) {
                System.out.println(file.getAbsolutePath());
            } else if (file.isDirectory()) {
                resultList.addAll(listf(file.getAbsolutePath()));
            }
        }
        //System.out.println(fList);
        return resultList;
    }

}
