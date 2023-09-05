package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.intake.Intake;

public class RobotContainer {
    private static RobotContainer INSTANCE = null;
    private final XboxController xboxController = new XboxController(0);
//    private final JoystickButton rb = new JoystickButton(xboxController, XboxController.Button.kRightBumper.value);
//    private final JoystickButton lb = new JoystickButton(xboxController, XboxController.Button.kLeftBumper.value);

    private final Intake intake = Intake.getInstance();

//    private final Vision vision = Vision.getInstance();

    /**
     * The container for the robot.  Contains subsystems, OI devices, and commands.
     */
    private RobotContainer() {
        // Configure the button bindings and default commands
        configureDefaultCommands();
        configureButtonBindings();
    }

    public static RobotContainer getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RobotContainer();
        }
        return INSTANCE;
    }

    private void configureDefaultCommands() {
    }

    private void configureButtonBindings() {
//        rb.whileTrue(new IntakePickupCube());
//        lb.whileTrue(new ResetIntake());
    }


    /**
     * Use this to pass the autonomous command to the main {@link Robot} class.
     *
     * @return the command to run in autonomous
     */
    public Command getAutonomousCommand() {
        return null;
    }
}
