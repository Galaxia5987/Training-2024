//package frc.robot.subsystems.arm;
//
//import com.ctre.phoenix.motorcontrol.ControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import edu.wpi.first.math.geometry.Translation2d;
//import frc.robot.utils.units.UnitModel;
//
//public class ArmIOReal implements ArmIO{
//    private final TalonFX elbowMotor;
//    private final TalonFX shoulderMotor;
//    private final UnitModel shoulderTicksToRads;
//    private final UnitModel elbowTicksToRads;
//    private final ArmKinematics armKinematics = new ArmKinematics(ArmConstants.SHOULDER_LENGTH, ArmConstants.ELBOW_LENGTH);
//
//    public ArmIOReal(int elbowMotorID, int shoulderMotorID){
//        elbowMotor = new TalonFX(6);
//        shoulderMotor = new TalonFX(5);
//
//        elbowTicksToRads = new UnitModel(2048 / Math.PI * 2 / 51.3);
//        shoulderTicksToRads = new UnitModel(2048 / Math.PI * 2 / 106.7);
//
//        elbowMotor.config_kP(0, ArmConstants.elbowP, 100);
//        elbowMotor.config_kI(0, ArmConstants.elbowI, 100);
//        elbowMotor.config_kD(0, ArmConstants.elbowD, 100);
//
//        shoulderMotor.config_kP(0, ArmConstants.shoulderP, 100);
//        shoulderMotor.config_kI(0, ArmConstants.shoulderI, 100);
//        shoulderMotor.config_kD(0, ArmConstants.shoulderD, 100);
//    }
//    @Override
//    public void updateInputs(ArmInputs inputs) {
//        inputs.elbowPower = elbowMotor.getMotorOutputPercent();
//        inputs.shoulderPower = elbowMotor.getMotorOutputPercent();
//        inputs.elbowAngle = elbowTicksToRads.toUnits(elbowMotor.getSelectedSensorPosition());
//        inputs.shoulderAngle = shoulderTicksToRads.toUnits(shoulderMotor.getSelectedSensorPosition());
//    }
//
//    @Override
//    public void setShoulderAngle(double angle) {
//        shoulderMotor.set(ControlMode.Position, angle);
//    }
//
//    @Override
//    public void setElbowAngle(double angle) {
//        elbowMotor.set(ControlMode.Position, angle);
//    }
//
//    @Override
//    public void setShoulderPower(double power) {
//        shoulderMotor.set(ControlMode.PercentOutput, power);
//    }
//
//    @Override
//    public void setElbowPower(double power) {
//        elbowMotor.set(ControlMode.PercentOutput, power);
//    }
//}
