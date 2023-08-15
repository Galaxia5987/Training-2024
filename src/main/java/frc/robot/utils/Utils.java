package frc.robot.utils;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;


public class Utils {
    public static final double EPSILON = 1e-9;

    public static boolean epsilonEquals(double a, double b) {
        return epsilonEquals(a, b, EPSILON);
    }

    public static boolean epsilonEquals(double a, double b, double maxError) {
        return Math.abs(a - b) < maxError;
    }

    public static double[] pose2dToArray(Pose2d pose) {
        return new double[]{pose.getX(), pose.getY(), pose.getRotation().getDegrees()};
    }

    public static double[] chassisSpeedsToArray(ChassisSpeeds speeds) {
        return new double[]{speeds.vxMetersPerSecond, speeds.vyMetersPerSecond, speeds.omegaRadiansPerSecond};
    }

    public static ChassisSpeeds arrayToChassisSpeeds(double[] array) {
        return new ChassisSpeeds(array[0], array[1], array[2]);
    }

    public static double[] swerveModuleStatesToArray(SwerveModuleState[] states) {
        double[] array = new double[states.length * 2];
        for (int i = 0; i < states.length; i++) {
            array[i * 2] = states[i].angle.getRadians();
            array[i * 2 + 1] = states[i].speedMetersPerSecond;
        }
        return array;
    }

    public static boolean speedsEpsilonEquals(ChassisSpeeds speeds) {
        return epsilonEquals(speeds.vxMetersPerSecond, 0) &&
                epsilonEquals(speeds.vyMetersPerSecond, 0) &&
                epsilonEquals(speeds.omegaRadiansPerSecond, 0);
    }

    public static double shootPower(double distance){
        double[] distances = { 0.0, 10.0, 20.0, 30.0 };
        double[] powers = { 0, 0.2, 0.4, 0.6};

        if (distance > distances[distances.length - 1]){
            return  powers[powers.length - 1];
        }
        else if (distance < 0){
            return 0;
        }

        int index = 0;
        while (index < distances.length - 1 && distance > distances[index + 1]) {
            index++;
        }

        double x1 = distances[index];
        double x2 = distances[index + 1];
        double y1 = powers[index];
        double y2 = powers[index + 1];

        return y1 + (distance - x1) * (y2 - y1) / (x2 - x1);
    }

    public static double visionDistance() {
        return 15;
    }
}