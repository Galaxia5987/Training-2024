package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.singlesystem.ArmByPower;
import frc.robot.commands.singlesystem.IntakePickupCube;
import frc.robot.commands.singlesystem.ShooterShoot;
import frc.robot.subsystems.arm.Arm;
import frc.robot.subsystems.intake.Intake;
import frc.robot.subsystems.vision.Vision;
import frc.robot.utils.Utils;

public class RobotContainer {
    private static RobotContainer INSTANCE = null;
    private final XboxController xboxController = new XboxController(0);
    private final Joystick leftJoystick = new Joystick(1);
    private final Joystick rightJoystick = new Joystick(2);
    private final JoystickButton rb = new JoystickButton(xboxController, XboxController.Button.kRightBumper.value);
    private final JoystickButton leftTrigger = new JoystickButton(leftJoystick, 1);

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
        arm.setDefaultCommand(new ArmByPower(xboxController));
    }

    private void configureButtonBindings() {

//        leftTrigger.whileTrue(new ShooterShoot(() -> Utils.shootPower(vision.distance())));
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
