package frc.robot.commands.singlesystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.gripper.Gripper;

public class ChangeGripperState extends CommandBase {
    private final Gripper gripper = Gripper.getInstance();
    private boolean state;

    public ChangeGripperState(boolean state){
        this.state = state;
    }
    @Override
    public void execute() {
        gripper.setState(state);
    }
}
