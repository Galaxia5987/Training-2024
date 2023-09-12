package frc.robot.commands.singlesystem;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.Arm;

public class ArmByPosition extends CommandBase {
    private final Arm arm = Arm.getInstance();
    public double x;
    public double y;

    public ArmByPosition(double x, double y){
        this.x = x;
        this.y = y;
    }
    @Override
    public void execute() {
        arm.setArmPosition(x, y);
    }
}
