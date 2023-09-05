package frc.robot.commands.singlesystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.intake.Intake;
import org.eclipse.jetty.server.PushBuilder;

public class IntakePickupCube extends CommandBase {
    private final Intake intake = Intake.getInstance();

    public IntakePickupCube(){
        addRequirements(intake);
    }

    @Override
    public void execute() {
        intake.setAngleMotorAngle(0);
        intake.setSpinMotorPower(0.5);
    }

    @Override
    public void end(boolean interrupted) {
        intake.setSpinMotorPower(0);
    }
}
