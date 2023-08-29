package frc.robot.subsystems.arm;

import com.ctre.phoenix.motorcontrol.TalonFXInvertType;
import edu.wpi.first.math.geometry.Translation2d;

public class ArmConstants {

    public static final int WAIT_TIME = 0;

    //shoulder physics
    public static final double SHOULDER_GEARING = 106.7; // Arbitrary units
    public static final double SHOULDER_MASS = 4; // [kg]
    public static final double SHOULDER_LENGTH = 0.75679; // [m]
    public static final double SHOULDER_MOMENT_OF_INERTIA = 2.613; // [kg*m^2]
    public static final double SHOULDER_CENTER_OF_MASS_RADIUS = 0.32; // [m]
    public static final int SHOULDER_NUMBER_OF_MOTORS = 2; // Arbitrary units
    public static final double SHOULDER_ARM_LENGTH = 0.75679; //[m]

    //elbow physics
    public static final double ELBOW_GEARING = 51.3; // Arbitrary units
    public static final double ELBOW_MASS = 3.5; // [kg]
    public static final double ELBOW_LENGTH = 0.75889; // [m]
    public static final double ELBOW_MOMENT_OF_INERTIA = 2.65; // [kg*m^2]
    public static final double ELBOW_CENTER_OF_MASS_RADIUS = 0.377; // [m]
    public static final int ELBOW_NUMBER_OF_MOTORS = 2; // Arbitrary units
    public static final double ELBOW_ARM_LENGTH = 0.75889; //[m]

    // motor configuration
    public static final double VOLT_COMP_SATURATION = 10; //[V]
    public static final boolean ENABLE_VOLT_COMPENSATION = true;
    public static final double SETPOINT_DEADBAND = 1;
    public static final double SHOULDER_FALCON_TICKS_PER_REVOLUTION = 2048 * SHOULDER_GEARING;
    public static final double TICKS_PER_RADIAN_SHOULDER = SHOULDER_FALCON_TICKS_PER_REVOLUTION / (Math.PI * 2);
    public static final double ELBOW_FALCON_TICKS_PER_REVOLUTION = 2048 * ELBOW_GEARING;
    public static final double TICKS_PER_RADIAN_ELBOW = ELBOW_FALCON_TICKS_PER_REVOLUTION / (Math.PI * 2);
    public static final TalonFXInvertType MAIN_CLOCKWISE = TalonFXInvertType.Clockwise;
    public static final TalonFXInvertType AUX_CLOCKWISE = TalonFXInvertType.Clockwise;

    public static final double ELBOW_ZERO_POSITION = 360 - 53.33; //[degrees]
    public static final double SHOULDER_ZERO_POSITION = 180 - 65.53; //[degrees]
    public static final double END_POSITION_UPPER_Y_LIMIT = 0; //[cm]
    public static final double END_POSITION_LOWER_Y_LIMIT = 0; //[cm]
    public static final double END_POSITION_UPPER_X_LIMIT = 0; //[cm]
    public static final double END_POSITION_LOWER_X_LIMIT = 0; //[cm]
    public static final double SHOULDER_ABSOLUTE_ENCODER_OFFSET = 0.40603 - SHOULDER_ZERO_POSITION / 360.0;
    public static final double ELBOW_ABSOLUTE_ENCODER_OFFSET = 0.57057 - ELBOW_ZERO_POSITION / 360.0;

    //PID
    public static final double shoulderP = 0.02;
    public static final double shoulderI = 0.0;
    public static final double shoulderD = 0.0;
    public static final double elbowP = 0.02;
    public static final double elbowI = 0.0;
    public static final double elbowD = 0.0;
}