package frc.robot.commands.singlesystem;

import edu.wpi.first.math.MathUtil;
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
    private double elbowHold = 0;
    private double shoulderHold = 0;

    public ArmByPower(XboxController controller) {
        this.controller = controller;
        addRequirements(arm);
    }

    @Override
    public void execute() {
        elbowHoldTrigger.update(Utils.epsilonEquals(controller.getLeftY(), 0, 0.1));
        shoulderHoldTrigger.update(Utils.epsilonEquals(controller.getRightY(), 0, 0.1));

        if (shoulderHoldTrigger.triggered()) {
            shoulderHold = arm.getShoulderAngle();
        }
        if ((Utils.epsilonEquals(controller.getRightY(), 0, 0.1))) {
            arm.setShoulderAngle(shoulderHold);
        }

        if (elbowHoldTrigger.triggered()) {
            elbowHold = arm.getElbowAngle();
        }
        if (Utils.epsilonEquals(controller.getRightY(), 0, 0.1)){
            arm.setElbowAngle(elbowHold);
        }

        arm.setElbowPower(MathUtil.applyDeadband(-controller.getLeftY(), 0.1) );
        arm.setShoulderPower(MathUtil.applyDeadband(-controller.getRightY(), 0.1));
    }
}
