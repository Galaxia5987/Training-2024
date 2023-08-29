package frc.robot.subsystems.arm;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class ArmInputs {
    public double elbowAngle = 0;
    public double shoulderAngle = 0;
    public double elbowPower = 0;
    public double shoulderPower = 0;
    public double elbowAngleSetPoint = 0;
    public double shoulderAngleSetPoint = 0;
    public double elbowPowerSetPoint = 0;
    public double shoulderPowerSetPoint = 0;

    public double shoulderMotorCurrent = 0;
    public double elbowMotorCurrent = 0;
}
