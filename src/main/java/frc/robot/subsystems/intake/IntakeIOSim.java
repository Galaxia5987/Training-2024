package frc.robot.subsystems.intake;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;

public class IntakeIOSim implements IntakeIO{
    private final FlywheelSim spinMotor;
    private final SingleJointedArmSim angleMotor;
    private final PIDController angleFeedback;

    public IntakeIOSim(){
        spinMotor = new FlywheelSim(DCMotor.getFalcon500(1), IntakeConstants.SPIN_GEAR_RATIO, 1);
        angleMotor = new SingleJointedArmSim(DCMotor.getFalcon500(1), IntakeConstants.ANGLE_GEAR_RATIO, 1, 0.5, -Math.PI/3, Math.PI/2, 3, true);
        angleFeedback = new PIDController(3.5, 0, 0, 0.02);
    }

    @Override
    public void updateInputs(IntakeInputs inputs) {
        inputs.spinMotorPower = inputs.spinMotorDesiredPower;
        inputs.spinMotorCurrent = spinMotor.getCurrentDrawAmps();
        inputs.angle = angleMotor.getAngleRads();

        spinMotor.update(0.02);
        angleMotor.update(0.02);
    }

    @Override
    public void setSpinMotorPower(double power) {
        spinMotor.setInputVoltage(power * 12);
    }

    @Override
    public void setAngleMotorAngle(double angle) {
        double angleMotorAppliedVoltage = angleFeedback.calculate(angleMotor.getAngleRads(), angle);
        angleMotor.setInputVoltage(angleMotorAppliedVoltage);
    }

}
