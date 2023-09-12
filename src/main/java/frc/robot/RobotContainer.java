package frc.robot;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.singlesystem.ArmByPosition;
import frc.robot.subsystems.arm.Arm;
import frc.robot.commands.singlesystem.ArmByPower;

public class RobotContainer {
    private static RobotContainer INSTANCE = null;
    private final XboxController xboxController = new XboxController(0);
    private final JoystickButton rb = new JoystickButton(xboxController, XboxController.Button.kRightBumper.value);
    private final JoystickButton lb = new JoystickButton(xboxController, XboxController.Button.kLeftBumper.value);

    private final Arm arm = Arm.getInstance();

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
//        arm.setDefaultCommand(new ArmByPower(xboxController));
    }

    private void configureButtonBindings() {
        rb.whileTrue(new ArmByPosition(-1, 0));
        lb.whileTrue(new ArmByPosition(-0.5, 1));
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
