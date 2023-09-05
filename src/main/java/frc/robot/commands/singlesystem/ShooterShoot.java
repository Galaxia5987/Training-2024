//package frc.robot.commands.singlesystem;
//
//import edu.wpi.first.wpilibj2.command.CommandBase;
//import frc.robot.subsystems.shooter.Shooter;
//
//import java.util.function.DoubleSupplier;
//
//public class ShooterShoot extends CommandBase {
//
//    private final DoubleSupplier supplier;
//    private final Shooter shooter = Shooter.getInstance();
//
//    public ShooterShoot(DoubleSupplier supplier) {
//        this.supplier = supplier;
//    }
//
//    @Override
//    public void execute() {
//        shooter.setShooterMotorVelocity(supplier.getAsDouble());
//    }
//
//    @Override
//    public void end(boolean interrupted) {
//        shooter.setShooterMotorVelocity(0);
//    }
//}
