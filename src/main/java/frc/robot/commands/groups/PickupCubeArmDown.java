package frc.robot.commands.groups;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.singlesystem.ArmByPosition;
import frc.robot.commands.singlesystem.ChangeGripperState;
import frc.robot.commands.singlesystem.IntakePickupCube;
import frc.robot.subsystems.arm.ArmConstants;

public class PickupCubeArmDown extends SequentialCommandGroup {
    public PickupCubeArmDown(){

        addCommands(
                new IntakePickupCube(),
                new ArmByPosition(ArmConstants.ELBOW_ZERO_POSITION, ArmConstants.SHOULDER_ZERO_POSITION),
                new ChangeGripperState(true)
        );
    }
}
