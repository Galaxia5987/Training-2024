package frc.robot.commands.singlesystem;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.turret.Turret;
import frc.robot.subsystems.turret.TurretInputs;
import frc.robot.utils.Utils;

public class TurretByPower extends CommandBase {
    private final Turret turret = Turret.getInstance();
    private final XboxController controller;

    public TurretByPower(XboxController controller){
        this.controller = controller;
    }

    @Override
    public void execute() {
        turret.setTurretPower(MathUtil.applyDeadband(controller.getRightY(), 0.15));
    }

    @Override
    public void end(boolean interrupted) {
        turret.setTurretPower(0);
    }
}
