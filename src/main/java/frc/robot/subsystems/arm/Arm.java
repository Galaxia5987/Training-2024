package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.utils.units.UnitModel;
import org.littletonrobotics.junction.Logger;

public class Arm extends SubsystemBase {
    private static Arm INSTANCE;
    private final ArmInputsAutoLogged inputs = new ArmInputsAutoLogged();
    private final ArmKinematics armKinematics = new ArmKinematics(ArmConstants.SHOULDER_LENGTH, ArmConstants.ELBOW_LENGTH);

    private final ArmIO io;
    private ControlMode shoulderMode;
    private ControlMode elbowMode;

    private Arm() {
        if (Robot.isReal()){
            io = new ArmIOReal(5, 6);
        }
        else{
            io = new ArmIOSim();
        }
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
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Arm", inputs);

        if (elbowMode == ControlMode.Position) {
            io.setElbowAngle(inputs.elbowAngle);
        }
        else if (elbowMode == ControlMode.PercentOutput){
            io.setElbowPower(inputs.elbowPowerSetPoint);
        }

        if (shoulderMode == ControlMode.Position) {
            io.setShoulderAngle(inputs.shoulderAngle);
        }
        else if (shoulderMode == ControlMode.PercentOutput){
            io.setShoulderPower(inputs.shoulderPowerSetPoint);
        }

    }
}
