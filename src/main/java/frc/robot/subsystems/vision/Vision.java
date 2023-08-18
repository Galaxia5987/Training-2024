package frc.robot.subsystems.vision;

import edu.wpi.first.net.PortForwarder;
import edu.wpi.first.networktables.DoublePublisher;
import edu.wpi.first.networktables.IntegerPublisher;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import org.photonvision.EstimatedRobotPose;
import org.photonvision.PhotonCamera;

import java.util.Arrays;

public class Vision extends SubsystemBase {
    private final PhotonCamera camera;
    private EstimatedRobotPose estimatedRobotPose;
    private final VisionInputsAutoLogged inputs = new VisionInputsAutoLogged();
    private final IntegerPublisher pipelinePub;


    private Vision(String cameraName) {
        camera = new PhotonCamera(cameraName);
        pipelinePub = NetworkTableInstance.getDefault().getIntegerTopic("pipelineIndex").publish();

        PortForwarder.add(5800, "photonvision.local", 5800);
        PortForwarder.add(1182, "photonvision.local", 1182);
        PortForwarder.add(1181, "photonvision.local", 1181);
    }

    public boolean hasTarget() {
        return inputs.hasTarget;
    }

    public double[] getPose3D() {
        return Arrays.copyOf(inputs.pose3D, inputs.pose3D.length);
    }

    public double[] getTarget3D() {
        return Arrays.copyOf(inputs.target3D, inputs.target3D.length);
    }

    public double getlatencyMillis() {
        return inputs.latencyMillis;
    }

    public void setPipeline(int index) {
        pipelinePub.set(index);
    }

    @Override
    public void periodic() {
        inputs.hasTarget = camera.getLatestResult().hasTargets();
        inputs.pose3D = new double[]{
                estimatedRobotPose.estimatedPose.getX(),
                estimatedRobotPose.estimatedPose.getY(),
                estimatedRobotPose.estimatedPose.getZ(),
                estimatedRobotPose.estimatedPose.getRotation().getX(),
                estimatedRobotPose.estimatedPose.getRotation().getY(),
                estimatedRobotPose.estimatedPose.getRotation().getZ()
        };
        inputs.target3D = new double[]{
                camera.getLatestResult().getBestTarget().getPitch(),
                camera.getLatestResult().getBestTarget().getYaw(),
                camera.getLatestResult().getBestTarget().getSkew()
        };
        inputs.targetArea = camera.getLatestResult().getBestTarget().getArea();
        inputs.latencyMillis = camera.getLatestResult().getLatencyMillis();
    }
}