package frc.robot.subsystems.intake;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class IntakeInputs {
    public double angle = 0;
    public double spinMotorPower = 0;
    public double spinMotorCurrent = 0;

    public double angleMotorSetpoint = 0;
    public double angleMotorWantedPower = 0;
    public double angleMotorCurrent = 0;

    public double spinMotorDesiredPower = 0;
}
