package frc.robot.subsystems.intake;

public interface IntakeIO {
    void updateInputs(IntakeInputs inputs);
    void setSpinMotorPower(double power);
    void setAngleMotorAngle(double angle);
}
