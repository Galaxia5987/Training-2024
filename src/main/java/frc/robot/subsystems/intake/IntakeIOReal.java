package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;
import frc.robot.utils.units.UnitModel;

public class IntakeIOReal implements IntakeIO{
    private final TalonFX angleMotor;
    private final CANSparkMax spinMotor;
    private final UnitModel ticksToRads;

    public IntakeIOReal(int angleMotorID, int spinMotorID){
        angleMotor = new TalonFX(angleMotorID);
        spinMotor = new CANSparkMax(spinMotorID, CANSparkMaxLowLevel.MotorType.kBrushless);
        angleMotor.configFactoryDefault(10);
        ticksToRads = new UnitModel(2048 / Math.PI * 2 / 0.2);

        angleMotor.setInverted(false);
        angleMotor.config_kP(0, IntakeConstants.kP, 10);
        angleMotor.config_kI(0, IntakeConstants.kI, 10);
        angleMotor.config_kD(0, IntakeConstants.kD, 10);
        angleMotor.config_kF(0, IntakeConstants.kF, 10);
    }
    @Override
    public void setSpinMotorPower(double power) {
        spinMotor.set(power);
    }

    @Override
    public void setAngleMotorAngle(double angle) {
        angleMotor.set(ControlMode.Position, angle);
    }

    @Override
    public void setAngleMotorPower(double power) {
        angleMotor.set(ControlMode.PercentOutput, power);
    }

    @Override
    public void resetIntake() {
        angleMotor.setSelectedSensorPosition(0);
    }

    public void updateInputs(IntakeInputs inputs){
        inputs.angleMotorCurrent = angleMotor.getStatorCurrent();
        inputs.angle = ticksToRads.toUnits(angleMotor.getSelectedSensorPosition());
        inputs.spinMotorPower = spinMotor.getAppliedOutput();
        inputs.spinMotorCurrent = spinMotor.getOutputCurrent();
    }
}
