package frc.robot.subsystems.intake;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.utils.units.UnitModel;

public class Intake extends SubsystemBase {
    private final IntakeIO io;
    private static Intake INSTANCE;

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

    public void setSpinMotorPower(double power){
        inputs.spinMotorDesiredPower = power;
    }

    public void setAngleMotorAngle(double angle){
        inputs.angleMotorSetpoint = angle;
    }


    @Override
    public void periodic() {
        io.updateInputs(inputs);

        io.setAngleMotorAngle(inputs.angleMotorSetpoint);
        io.setSpinMotorPower(inputs.spinMotorDesiredPower);
    }
}
