package frc.robot.commands.singlesystem;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.arm.Arm;
import frc.robot.utils.Utils;
import frc.robot.utils.math.differential.BooleanTrigger;

public class ArmByPower extends CommandBase {
    private final Arm arm = Arm.getInstance();
    private final XboxController controller;
    private final BooleanTrigger elbowHoldTrigger = new BooleanTrigger(false, false);
    private final BooleanTrigger shoulderHoldTrigger = new BooleanTrigger(false, false);

    public ArmByPower(XboxController controller) {
        this.controller = controller;
    }

    @Override
    public void execute() {
        elbowHoldTrigger.update(Utils.epsilonEquals(controller.getLeftY(), 0, 0.1));
        shoulderHoldTrigger.update(Utils.epsilonEquals(controller.getRightY(), 0, 0.1));

        if (shoulderHoldTrigger.triggered()) {
            arm.setShoulderAngle(arm.getShoulderAngle());
        }
        if (elbowHoldTrigger.triggered()) {
            arm.setElbowAngle(arm.getElbowAngle());
        }

        arm.setElbowPower(-controller.getLeftY());
        arm.setShoulderPower(-controller.getRightY());
    }
}
