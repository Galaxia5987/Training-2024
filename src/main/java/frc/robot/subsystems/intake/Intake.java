package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import org.littletonrobotics.junction.Logger;

public class Intake extends SubsystemBase {
    private final IntakeIO io;
    private static Intake INSTANCE;
    private ControlMode angleMode;

    private final IntakeInputsAutoLogged inputs = new IntakeInputsAutoLogged();

    private Intake(){
        if (Robot.isReal()){
            io = new IntakeIOReal(1, 2);
        }
        else{
            io = new IntakeIOSim();
        }
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

    public double getSpinMotorPower(){
        return inputs.spinMotorPower;
    }

    public double getSpinMotorCurrent(){
        return inputs.spinMotorCurrent;
    }

    public double getAngleMotorCurrent(){
        return inputs.angleMotorCurrent;
    }

    public void setSpinMotorPower(double power){
        inputs.spinMotorDesiredPower = power;
    }
    public void setAngleMotorPower(double power){
        inputs.angleMotorWantedPower = power;
        angleMode = ControlMode.PercentOutput;
    }

    public void setAngleMotorAngle(double angle){
        inputs.angleMotorSetpoint = angle;
        angleMode = ControlMode.Position;
    }
    public void resetAngleMotor(){
        io.resetIntake();
    }


    @Override
    public void periodic() {
        io.updateInputs(inputs);
        Logger.getInstance().processInputs("Intake", inputs);

        if (angleMode == ControlMode.Position) {
            io.setAngleMotorAngle(inputs.angleMotorSetpoint);
        }
        else if (angleMode == ControlMode.PercentOutput){
            io.setAngleMotorPower(inputs.angleMotorWantedPower);
        }

        io.setSpinMotorPower(inputs.spinMotorDesiredPower);
    }
}
