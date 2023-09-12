package frc.robot.subsystems.arm;

public interface ArmIO {
    void updateInputs(ArmInputs inputs);
    void setShoulderAngle(double angle);
    void setElbowAngle(double angle);
    void setShoulderPower(double power);
    void setElbowPower(double power);
}
