package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.units.UnitModel;

public class Arm extends SubsystemBase {
    private static Arm INSTANCE;
    private final TalonFX elbowMotor;
    private final TalonFX shoulderMotor;
    private final UnitModel shoulderTicksToRads;
    private final UnitModel elbowTicksToRads;
    private final ArmInputsAutoLogged inputs = new ArmInputsAutoLogged();

    private final ArmKinematics armKinematics = new ArmKinematics(ArmConstants.SHOULDER_LENGTH, ArmConstants.ELBOW_LENGTH);

    private ControlMode shoulderMode;
    private ControlMode elbowMode;

    private Arm() {
        elbowMotor = new TalonFX(6);
        shoulderMotor = new TalonFX(5);

        elbowTicksToRads = new UnitModel(2048 / Math.PI * 2 / 51.3);
        shoulderTicksToRads = new UnitModel(2048 / Math.PI * 2 / 106.7);

        elbowMotor.config_kP(0, ArmConstants.elbowP, 100);
        elbowMotor.config_kI(0, ArmConstants.elbowI, 100);
        elbowMotor.config_kD(0, ArmConstants.elbowD, 100);

        shoulderMotor.config_kP(0, ArmConstants.shoulderP, 100);
        shoulderMotor.config_kI(0, ArmConstants.shoulderI, 100);
        shoulderMotor.config_kD(0, ArmConstants.shoulderD, 100);
    }

    public static Arm getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Arm();
        }
        return INSTANCE;
    }

    public void setShoulderAngle(double angle) {
        inputs.shoulderAngleSetPoint = angle;
        shoulderMode = ControlMode.Position;
    }

    public void setElbowAngle(double angle) {
        inputs.elbowAngleSetPoint = angle;
        elbowMode = ControlMode.Position;
    }

    public void setShoulderPower(double power) {
        inputs.shoulderPowerSetPoint = power;
        shoulderMode = ControlMode.PercentOutput;
    }

    public void setElbowPower(double power) {
        inputs.elbowPowerSetPoint = power;
        elbowMode = ControlMode.PercentOutput;
    }

    public double getShoulderAngle() {
        return inputs.shoulderAngle;
    }

    public double getElbowAngle() {
        return inputs.elbowAngle;
    }

    public double getShoulderPower() {
        return inputs.shoulderPower;
    }

    public double getElbowPower() {
        return inputs.elbowPower;
    }

    public void setArmPosition(double x, double y) {
        var solution = armKinematics.inverseKinematics(new Translation2d(x, y));
        setShoulderAngle(solution.shoulderAngle);
        setElbowAngle(solution.elbowAngle);
    }

    @Override
    public void periodic() {
        inputs.elbowPower = elbowMotor.getMotorOutputPercent();
        inputs.shoulderPower = elbowMotor.getMotorOutputPercent();
        inputs.elbowPower = elbowMotor.getStatorCurrent();
        inputs.shoulderPower = shoulderMotor.getStatorCurrent();
        inputs.elbowAngle = elbowTicksToRads.toUnits(elbowMotor.getSelectedSensorPosition());
        inputs.shoulderAngle = shoulderTicksToRads.toUnits(shoulderMotor.getSelectedSensorPosition());

        elbowMotor.set(elbowMode, (elbowMode == ControlMode.Position) ? inputs.elbowAngleSetPoint : inputs.elbowPowerSetPoint);
        shoulderMotor.set(shoulderMode, (shoulderMode == ControlMode.Position) ? inputs.shoulderAngleSetPoint : inputs.shoulderPowerSetPoint);
    }
}
