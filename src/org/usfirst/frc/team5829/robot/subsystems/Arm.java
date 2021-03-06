package org.usfirst.frc.team5829.robot.subsystems;

import org.usfirst.frc.team5829.robot.Lidar;
import org.usfirst.frc.team5829.robot.RobotMap;
import org.usfirst.frc.team5829.robot.commands.ArmMove;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Arm extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public static TalonSRX liftMotor1 = new TalonSRX(RobotMap.liftMotor1);
	public static Spark liftMotor2 = new Spark(RobotMap.liftMotor2);
	public static Spark liftMotor3 = new Spark(RobotMap.liftMotor3);
	public static Spark liftMotor4 = new Spark(RobotMap.liftMotor4);
	public static final double armUpSpeed = 0.65;
	public static final double armDownSpeed = 0.35;
	public static int currentPos = 0;
	public static DoubleSolenoid armMove = new DoubleSolenoid(RobotMap.arm_down,RobotMap.arm_up);
	public static DoubleSolenoid bikeBreak = new DoubleSolenoid(RobotMap.breakClose, RobotMap.breakOpen);
	public static DigitalInput bumper = new DigitalInput(RobotMap.bumper);
	//public static DigitalInput limit = new DigitalInput(RobotMap.limit);
	//public static Lidar lidarLift;
	
    public void initDefaultCommand(){
    	liftMotor1.setSensorPhase(false);
    	armMovePiston(0);
//    	armMove.set(DoubleSolenoid.Value.kForward);
    	setDefaultCommand(new ArmMove(0, 0));
    }
    
    public static void armMoveMotor(int upOrDown) {
    	if(upOrDown == -1) {
    			bikeBreak.set(DoubleSolenoid.Value.kForward);
    			liftMotor1.set(ControlMode.PercentOutput, armUpSpeed);
    			liftMotor2.set( armUpSpeed);
    			liftMotor3.set( -armUpSpeed);
    			liftMotor4.set( -armUpSpeed);
    	}else if(upOrDown == 1){
    			bikeBreak.set(DoubleSolenoid.Value.kForward);
    			liftMotor1.set(ControlMode.PercentOutput, -armDownSpeed);
    			liftMotor2.set( -armDownSpeed);
    			liftMotor3.set( armDownSpeed);
    			liftMotor4.set( armDownSpeed);
    	}else{
    		bikeBreak.set(DoubleSolenoid.Value.kReverse);
    		liftMotor1.set(ControlMode.PercentOutput, 0);
    		liftMotor2.set(0);
    		liftMotor3.set(0); 
    		liftMotor4.set(0);
    	}
    }
    
    public static boolean moveArmPosition(int position) {
    	currentPos = Math.abs(liftMotor1.getSelectedSensorPosition(0));
    	System.out.println(currentPos);
    	if(currentPos > position) {
    		System.out.println("Above");
    		bikeBreak.set(DoubleSolenoid.Value.kForward);
			liftMotor1.set(ControlMode.PercentOutput, -armDownSpeed);
			liftMotor2.set( -armDownSpeed);
			liftMotor3.set( armDownSpeed);
			liftMotor4.set( armDownSpeed);
			return true;
    	}else if(currentPos < position) {
    		System.out.println("Below");
    		bikeBreak.set(DoubleSolenoid.Value.kForward);
			liftMotor1.set(ControlMode.PercentOutput, armUpSpeed);
			liftMotor2.set( armUpSpeed);
			liftMotor3.set( -armUpSpeed);
			liftMotor4.set( -armUpSpeed);
			return false;
    	}else if(position - 500 < currentPos || position + 500 > currentPos){
    		System.out.println("STOP!!!!!!");
    		bikeBreak.set(DoubleSolenoid.Value.kReverse);
    		liftMotor1.set(ControlMode.PercentOutput, 0);
    		liftMotor2.set(0);
    		liftMotor3.set(0); 
    		liftMotor4.set(0);
    		return true;
    	}
    	currentPos = position;
    	return false;
    }
    
    public static void armMovePiston(int upOrDown){
    	if(upOrDown == 0)
    		armMove.set(DoubleSolenoid.Value.kForward);
    	if(upOrDown == 1)
    		armMove.set(DoubleSolenoid.Value.kReverse);
    }
    
    
    public static void resetEncoder() {
    	liftMotor1.setSelectedSensorPosition(0 ,0, 10);	
    }
}

