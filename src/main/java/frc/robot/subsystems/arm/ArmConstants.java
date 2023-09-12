package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import edu.wpi.first.math.geometry.Translation2d;

public class ArmConstants {
    public static final double SHOULDER_LENGTH = 0.75679; // [m]
    public static final double ELBOW_LENGTH = 0.75889; // [m]

    public static final double ELBOW_ZERO_POSITION = 360 - 53.33; //[degrees]
    public static final double SHOULDER_ZERO_POSITION = 180 - 65.53; //[degrees]
    public static final double ELBOW_TOP_POSITION = 210; //[degrees]
    public static final double SHOULDER_TOP_POSITION = 120; //[degrees]

    //PID
    public static final double shoulderP = 2.55;
    public static final double shoulderI = 0;
    public static final double shoulderD = 0.1;
    public static final double elbowP = 1.7;
    public static final double elbowI = 0.0;
    public static final double elbowD = 0;
}