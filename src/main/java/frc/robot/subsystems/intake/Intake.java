package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utils.units.UnitModel;

public class Intake extends SubsystemBase {
    private final TalonFX angleMotor;
    private final TalonFX spinMotor;
    private static Intake INSTANCE;
    private final UnitModel ticksToRads;

    private final IntakeInputsAutoLogged inputs = new IntakeInputsAutoLogged();

    private Intake(){
        angleMotor = new TalonFX(1);
        spinMotor = new TalonFX(2);
        angleMotor.configFactoryDefault(10);
        spinMotor.configFactoryDefault(10);

        ticksToRads = new UnitModel(2048 / Math.PI * 2 / 0.2);

        angleMotor.config_kP(0, IntakeConstants.kP, 10);
        angleMotor.config_kP(0, IntakeConstants.kI, 10);
        angleMotor.config_kP(0, IntakeConstants.kD, 10);
        angleMotor.config_kP(0, IntakeConstants.kF, 10);

    }

    public static Intake getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Intake();
        }
        return INSTANCE;
    }

    public double getAngle(){
        return inputs.angle;
    }

    public double getSpinMotoroPower(){
        return inputs.spinMotorPower;
    }

    public double getSpinMotorCurrent(){
        return inputs.spinMotorCurrent;
    }

    public void setSpinMotorPower(double power){
        inputs.spinMotorDesiredPower = power;
    }

    public void setAngleMotorAngle(double angle){
        inputs.angleMotorSetpoint = angle;
    }


    @Override
    public void periodic() {
        inputs.angle = ticksToRads.toUnits(angleMotor.getSelectedSensorPosition());
        inputs.spinMotorPower = spinMotor.getMotorOutputPercent();
        inputs.spinMotorCurrent = spinMotor.getStatorCurrent();

        spinMotor.set(ControlMode.PercentOutput, inputs.spinMotorDesiredPower);
        angleMotor.set(ControlMode.Position, inputs.angleMotorSetpoint);
    }
}
