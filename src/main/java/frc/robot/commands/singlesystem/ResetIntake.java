package frc.robot.commands.singlesystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;

public class ResetIntake extends CommandBase {
    private final Intake intake = Intake.getInstance();

    @Override
    public void execute() {
        intake.setAngleMotorPower(0.4);
    }

    @Override
    public void end(boolean interrupted) {
        intake.resetAngleMotor();
    }

    @Override
    public boolean isFinished() {
        return intake.getAngleMotorCurrent()>12;
    }
}
