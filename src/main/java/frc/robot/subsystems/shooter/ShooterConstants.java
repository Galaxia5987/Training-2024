package frc.robot.subsystems.shooter;

import com.ctre.phoenix.motorcontrol.StatorCurrentLimitConfiguration;

public class ShooterConstants {
    public static double kP = 0.7;
    public static double kI = 0;
    public static double kD = 0.1;
    public static double kF = 0;
    public static StatorCurrentLimitConfiguration currentLimitConfiguration = new StatorCurrentLimitConfiguration(
            true, 50, 0, 0
    );
}
