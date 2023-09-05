package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.subsystems.shooter.Shooter;
import frc.robot.subsystems.shooter.ShooterConstants;
import frc.robot.utils.units.UnitModel;
import org.littletonrobotics.junction.Logger;

public class Turret extends SubsystemBase {
    private final TalonSRX turretMotor;
    private final UnitModel ticksToRads;
    private static Turret INSTANCE;
    private final TurretInputsAutoLogged inputs = new TurretInputsAutoLogged();
    private ControlMode turretMode;
    private Turret(){
        turretMotor = new TalonSRX(8);
        ticksToRads = new UnitModel(2048 / Math.PI * 2 / 16/100);

        turretMotor.configFactoryDefault(10);
        turretMotor.config_kP(0, ShooterConstants.kP, 10);
        turretMotor.config_kI(0, ShooterConstants.kI, 10);
        turretMotor.config_kD(0, ShooterConstants.kD, 10);
        turretMotor.config_kF(0, ShooterConstants.kF, 10);
    }

    public static Turret getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Turret();
        }
        return INSTANCE;
    }
    public void setTurretAngle(double angle){
        inputs.turretAngleSetPoint = angle;
        turretMode = ControlMode.Position;
    }
    public void setTurretPower(double power){
        inputs.turretPowerSetPoint = power;
        turretMode = ControlMode.PercentOutput;
    }
    public double getTurretAngle(){
        return inputs.turretAngle;
    }
    public double getTurretPower(){
        return inputs.turretPower;
    }
    public double getTurretCurrent(){
        return inputs.turretCurrent;
    }
    @Override
    public void periodic() {
        inputs.turretAngle = ticksToRads.toUnits(turretMotor.getSelectedSensorPosition());
        inputs.turretPower = turretMotor.getMotorOutputPercent();
        inputs.turretCurrent = turretMotor.getStatorCurrent();
        Logger.getInstance().processInputs("Turret", inputs);

        turretMotor.set(turretMode, (turretMode == ControlMode.Position) ? inputs.turretAngleSetPoint : inputs.turretPowerSetPoint);
    }
}
