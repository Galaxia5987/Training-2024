package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.utils.units.UnitModel;

public class IntakeIOReal implements IntakeIO{
    private final TalonFX angleMotor;
    private final TalonFX spinMotor;
    private final UnitModel ticksToRads;

    public IntakeIOReal(int angleMotorID, int spinMotorID){
        angleMotor = new TalonFX(angleMotorID);
        spinMotor = new TalonFX(spinMotorID);
        angleMotor.configFactoryDefault(10);
        spinMotor.configFactoryDefault(10);

        ticksToRads = new UnitModel(2048 / Math.PI * 2 / 0.2);

        angleMotor.config_kP(0, IntakeConstants.kP, 10);
        angleMotor.config_kI(0, IntakeConstants.kI, 10);
        angleMotor.config_kD(0, IntakeConstants.kD, 10);
        angleMotor.config_kF(0, IntakeConstants.kF, 10);
    }
    @Override
    public void setSpinMotorPower(double power) {
        spinMotor.set(ControlMode.PercentOutput, power);
    }

    @Override
    public void setAngleMotorAngle(double angle) {
        angleMotor.set(ControlMode.Position, angle);
    }

    public void updateInputs(IntakeInputs inputs){
        inputs.angle = ticksToRads.toUnits(angleMotor.getSelectedSensorPosition());
        inputs.spinMotorPower = spinMotor.getMotorOutputPercent();
        inputs.spinMotorCurrent = spinMotor.getStatorCurrent();
    }
}
