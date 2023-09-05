//package frc.robot.commands.groups;
//
//import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
//import frc.robot.commands.singlesystem.ArmByPosition;
//import frc.robot.commands.singlesystem.ChangeGripperState;
//import frc.robot.subsystems.arm.ArmConstants;
//
//public class LiftUpCubeAndRelease extends SequentialCommandGroup {
//    public LiftUpCubeAndRelease(){
//        addCommands(
//                new ArmByPosition(ArmConstants.ELBOW_TOP_POSITION, ArmConstants.SHOULDER_TOP_POSITION),
//                new ChangeGripperState(false)
//        );
//    }
//}
