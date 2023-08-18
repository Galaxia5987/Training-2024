package frc.robot.subsystems.vision;

import org.littletonrobotics.junction.AutoLog;

@AutoLog
public class VisionInputs {
    public boolean hasTarget = false;
    public double distance = 0;
    public double[] pose3D = {0, 0, 0, 0, 0, 0};

    public double[] target3D = {0, 0, 0};
    public double targetArea = 0;
    public double latencyMillis = 0;
}
