package org.usfirst.frc.team5829.robot.commands;

import org.usfirst.frc.team5829.robot.Robot;
import org.usfirst.frc.team5829.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TankDriveCommand extends Command {

	public double leftSpeed, rightSpeed;

    public TankDriveCommand(double left, double right) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	leftSpeed = left;
    	rightSpeed = right;
    	requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftSpeed = -(Robot.oi.xbox.getRawAxis(1));
    	rightSpeed = (Robot.oi.xbox.getRawAxis(5));
    	
    	if(Math.abs(leftSpeed) < .2) {
    		leftSpeed = 0;
    	}
    	if(Math.abs(rightSpeed) < .2) {
    		rightSpeed = 0;
    	}
    	
    	DriveTrain.TankDrive(leftSpeed, rightSpeed);
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
