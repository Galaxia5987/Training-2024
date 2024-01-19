package frc.robot.subsystems.arm;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.system.plant.DCMotor;
import edu.wpi.first.wpilibj.simulation.FlywheelSim;
import edu.wpi.first.wpilibj.simulation.SingleJointedArmSim;
import edu.wpi.first.wpilibj.smartdashboard.Mechanism2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismLigament2d;
import edu.wpi.first.wpilibj.smartdashboard.MechanismRoot2d;
import frc.robot.subsystems.intake.IntakeConstants;
import frc.robot.utils.math.AngleUtil;

public class ArmIOSim implements ArmIO{
    private final SingleJointedArmSim elbowMotor;
    private final SingleJointedArmSim shoulderMotor;

    private final PIDController elbowFeedback;
    private final PIDController shoulderFeedback;
    public ArmIOSim(){
        shoulderMotor = new SingleJointedArmSim(DCMotor.getFalcon500(2), 106.7, 2.613, 0.75679, 0, 2 * Math.PI, 4, true);
        shoulderFeedback = new PIDController(ArmConstants.shoulderP, ArmConstants.shoulderI, ArmConstants.shoulderD, 0.02);
        elbowMotor = new SingleJointedArmSim(DCMotor.getFalcon500(2), 51.3, 2.65, 0.75889, 0, 2 * Math.PI, 3.5, true);
        elbowFeedback = new PIDController(ArmConstants.elbowP, ArmConstants.elbowI, ArmConstants.elbowD, 0.02);
    }
    @Override
    public void updateInputs(ArmInputs inputs) {
        inputs.shoulderAngle = AngleUtil.normalize(shoulderMotor.getAngleRads());
        inputs.elbowAngle = AngleUtil.normalize(elbowMotor.getAngleRads());
        inputs.shoulderMotorCurrent = shoulderMotor.getCurrentDrawAmps();
        inputs.elbowMotorCurrent = elbowMotor.getCurrentDrawAmps();

        elbowMotor.update(0.02);
        shoulderMotor.update(0.02);
    }

    @Override
    public void setShoulderAngle(double angle) {
        double shoulderMotorAppliedVoltage = shoulderFeedback.calculate(AngleUtil.normalize(shoulderMotor.getAngleRads()), angle);
        setShoulderPower(shoulderMotorAppliedVoltage);
    }

    @Override
    public void setElbowAngle(double angle) {
        double elbowMotorAppliedVoltage = elbowFeedback.calculate(AngleUtil.normalize(elbowMotor.getAngleRads()), angle);
        setElbowPower(elbowMotorAppliedVoltage);
    }

    @Override
    public void setShoulderPower(double power) {
        shoulderMotor.setInputVoltage(power * 12);
    }

    @Override
    public void setElbowPower(double power) {
        elbowMotor.setInputVoltage(power * 12);
    }
}
