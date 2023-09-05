//package frc.robot.commands.singlesystem;
//
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.turret.Turret;
//
//import java.util.Collections;
//import java.util.function.DoubleSupplier;
//
//public class TurretByPosition extends CommandBase {
//    private final Turret turret = Turret.getInstance();
//    private final double position;
//    public TurretByPosition(double position) {
//        this.position = position;
//    }
//
//    @Override
//    public void execute() {
//        turret.setTurretAngle(position);
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        turret.setTurretPower(0);
//    }
//}
