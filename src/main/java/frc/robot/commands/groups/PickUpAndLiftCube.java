package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.singlesystem.ArmByPosition;
import frc.robot.commands.singlesystem.ArmByPower;
import frc.robot.commands.singlesystem.IntakePickupCube;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.arm.ArmConstants;
import frc.robot.subsystems.intake.Intake;

public class PickUpAndLiftCube extends SequentialCommandGroup {
    public PickUpAndLiftCube(){

        addCommands(
                new IntakePickupCube(),
                new ArmByPosition(ArmConstants.ELBOW_ZERO_POSITION, ArmConstants.SHOULDER_ZERO_POSITION));
    }
}
