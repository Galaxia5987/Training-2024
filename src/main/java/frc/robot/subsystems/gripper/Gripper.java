package frc.robot.subsystems.gripper;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Gripper extends SubsystemBase {
    private final Solenoid solenoid;
    private static Gripper INSTANCE;
    private final GripperInputsAutoLogged inputs = new GripperInputsAutoLogged();

    private Gripper(){
        solenoid = new Solenoid(PneumaticsModuleType.CTREPCM ,0);
    }

    public static Gripper getInstance(){
        if (INSTANCE == null){
            INSTANCE = new Gripper();
        }
        return INSTANCE;
    }
    public void setState(boolean state){
        inputs.wantedState = state;
    }
    public boolean getState(){
        return inputs.isOpen;
    }
    @Override
    public void periodic() {
        inputs.isOpen = solenoid.get();
        solenoid.set(inputs.wantedState);
    }
}
