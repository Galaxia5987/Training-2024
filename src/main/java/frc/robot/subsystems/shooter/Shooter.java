//package frc.robot.subsystems.shooter;
//
//import com.ctre.phoenix.motorcontrol.InvertType;
//import com.ctre.phoenix.motorcontrol.NeutralMode;
//import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.utils.units.UnitModel;
//import org.littletonrobotics.junction.Logger;
//
//public class Shooter extends SubsystemBase{
//
//    private final TalonFX shooterMotor1;
//    private final TalonFX shooterMotor2;
//    private static Shooter INSTANCE;
//    private final UnitModel ticksToRads;
//
//    private final ShooterInputsAutoLogged inputs = new ShooterInputsAutoLogged();
//
//    private Shooter(){
//        shooterMotor1 = new TalonFX(3);
//        shooterMotor2 = new TalonFX(4);
//
//        ticksToRads = new UnitModel(2048 / Math.PI * 2 / 0.2);
//
//        shooterMotor1.configFactoryDefault(10);
//        shooterMotor1.configStatorCurrentLimit(ShooterConstants.currentLimitConfiguration);
//        shooterMotor1.setInverted(false);
//        shooterMotor1.setNeutralMode(NeutralMode.Coast);
//
//        shooterMotor2.configFactoryDefault(10);
//        shooterMotor2.follow(shooterMotor1);
//        shooterMotor2.setInverted(InvertType.OpposeMaster);
//        shooterMotor2.setNeutralMode(NeutralMode.Coast);
//
//        shooterMotor1.config_kP(0, ShooterConstants.kP, 10);
//        shooterMotor1.config_kI(0, ShooterConstants.kI, 10);
//        shooterMotor1.config_kD(0, ShooterConstants.kD, 10);
//        shooterMotor1.config_kF(0, ShooterConstants.kF, 10);
//    }
//
//    public static Shooter getInstance(){
//        if (INSTANCE == null){
//            INSTANCE = new Shooter();
//        }
//        return INSTANCE;
//    }
//
//    public void setShooterMotorVelocity(double power){
//        inputs.desiredMotorVelocity = power;
//    }
//
//    public double getCurrent(){
//        return inputs.motor1Current + inputs.motor2Current;
//    }
//    public double getVelocity(){
//        return inputs.motorVelocity;
//    }
//
//    @Override
//    public void periodic() {
//        inputs.motorVelocity = ticksToRads.toVelocity(shooterMotor1.getSelectedSensorVelocity());
//        inputs.motor1Current = shooterMotor1.getStatorCurrent();
//        inputs.motor2Current = shooterMotor2.getStatorCurrent();
//        inputs.motor2BusVoltage = shooterMotor1.getBusVoltage();
//        inputs.motor2BusVoltage = shooterMotor2.getBusVoltage();
//        Logger.getInstance().processInputs("Shooter", inputs);
//
//        shooterMotor1.set(TalonFXControlMode.Velocity, inputs.desiredMotorVelocity);
//    }
//}
