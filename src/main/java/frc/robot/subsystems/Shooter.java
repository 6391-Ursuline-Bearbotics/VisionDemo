package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.SHOOTER;

public class Shooter extends SubsystemBase {
  //private WPI_TalonFX topMotor = new WPI_TalonFX(SHOOTER.TOP_MOTOR);
  private WPI_TalonFX bottomMotor = new WPI_TalonFX(SHOOTER.BOTTOM_MOTOR);
  private WPI_TalonFX bottomMotor2 = new WPI_TalonFX(SHOOTER.BOTTOM_MOTOR2);
  private boolean firing = false;
  private double heightVelocity = SHOOTER.HIGH_VELOCITY;
  private double m_targetVelocity = 0;

  /**
   * Creates a new Shooter.
   */
  public Shooter() {
    //topMotor.setInverted(bottomMotor.getInverted());
    bottomMotor.setInverted(false);
    bottomMotor2.follow(bottomMotor);
    bottomMotor2.setInverted(InvertType.OpposeMaster);

    setPID(bottomMotor, SHOOTER.kP, 0, 0, SHOOTER.kF);
    //setPID(topMotor, SHOOTER.kP, 0, 0, SHOOTER.kF);
  }

  public void setMotor(double speed) {
    bottomMotor.set(speed);
  }

  //method for testing.
  public void changeSpeed(double velocity){
    setTargetVelocity(getTargetVelocity() + velocity);
    //adjustMotorsToTarget();
    firing = true;
    //String("topShooterVelocity",""+ topMotor.getSelectedSensorVelocity());
    //SmartDashboard.putString("bottomShooterVelocity", ""+bottomMotor.getSelectedSensorVelocity());
  }
  
  public void setPID(WPI_TalonFX motor,double kP, double kI, double kD, double kF) {
    motor.config_kP(0, kP, 30);
    motor.config_kI(0, kI, 30);
	motor.config_kD(0, kD, 30);
	motor.config_kF(0, kF, 30);
  }

  public void spinUp(){
    setTargetVelocity(heightVelocity);
  }
  public void fire(){
    setTargetVelocity(heightVelocity);
    firing = true;
    //String("Shooter is Firing: ", ""+firing);
  }

  public void fireLow(){
    firing = true;
    setTargetVelocity(SHOOTER.LOW_VELOCITY);
  }

  public void stopFiring(){
    firing = false;
  }

  @Override
  public void periodic() {
    //SmartDashboard.putString("topShooterTargetVelocity",""+ getTopTargetVelocity());
    //SmartDashboard.putString("bottomShooterTargetVelocity", ""+getTargetVelocity());
    //SmartDashboard.putString("topShooterVelocity",""+ topMotor.getSelectedSensorVelocity());
    SmartDashboard.putString("bottomShooterVelocity", ""+bottomMotor.getSelectedSensorVelocity());
    if (firing){
      //bottomMotor.set(ControlMode.Velocity,getTargetVelocity());
      //topMotor.set(ControlMode.Velocity,getTopTargetVelocity());
    } else {
      //bottomMotor.set(ControlMode.PercentOutput, 0.0);
      //topMotor.set(ControlMode.PercentOutput, 0.0);
    }

  }

  public void setTargetVelocity(double bottomMotorTarget){
    m_targetVelocity = bottomMotorTarget;
  }

  public double getTopTargetVelocity(){
    return m_targetVelocity * SHOOTER.TOP_PERCENT_OF_BOTTOM;
  }

  public double getTargetVelocity(){
    return m_targetVelocity;
  }
  public boolean atSpeed(){
    //Boolean("Is BottomMotor at speed?: ", bottomMotor.getSelectedSensorVelocity() >= getTargetVelocity() );

    return bottomMotor.getSelectedSensorVelocity() >= getTargetVelocity() *.95; //&& topMotor.getSelectedSensorVelocity() >= getTopTargetVelocity() *.95;
  }

  public void changeHighVelocity(double amount){
    heightVelocity += amount;
  }
  public void resetHighVelocity(){
    heightVelocity = SHOOTER.HIGH_VELOCITY;
  }
}
