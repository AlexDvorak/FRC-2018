package org.usfirst.frc.team5829.robot.commands;

import org.usfirst.frc.team5829.robot.Robot;
import org.usfirst.frc.team5829.robot.subsystems.DriveTrain;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class SplitArcade extends Command {
	
	public double straight, rotate;
	

    public SplitArcade() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveBase);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	straight = (Robot.oi.xbox.getRawAxis(4));
    	rotate = -(Robot.oi.xbox.getRawAxis(1));
    	
    	if (Math.abs(straight) < .2) {
    		straight = 0;
    	}
    	if (Math.abs(rotate) < .2) {
    		rotate = 0;
    	}
    	
    	DriveTrain.SplitArcade(straight, rotate);
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
