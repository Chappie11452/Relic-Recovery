/*


                                  1111111       1111111         444444444    555555555555555555     222222222222222
         ######    ######        1::::::1      1::::::1        4::::::::4    5::::::::::::::::5   2:::::::::::::::22
         #::::#    #::::#       1:::::::1     1:::::::1       4:::::::::4    5::::::::::::::::5   2::::::222222:::::2
         #::::#    #::::#       111:::::1     111:::::1      4::::44::::4    5:::::555555555555   2222222     2:::::2
    ######::::######::::######     1::::1        1::::1     4::::4 4::::4    5:::::5                          2:::::2
    #::::::::::::::::::::::::#     1::::1        1::::1    4::::4  4::::4    5:::::5                          2:::::2
    ######::::######::::######     1::::1        1::::1   4::::4   4::::4    5:::::5555555555              2222::::2
         #::::#    #::::#          1::::l        1::::l  4::::444444::::444  5:::::::::::::::5        22222::::::22
         #::::#    #::::#          1::::l        1::::l  4::::::::::::::::4  555555555555:::::5     22::::::::222
    ######::::######::::######     1::::l        1::::l  4444444444:::::444              5:::::5   2:::::22222
    #::::::::::::::::::::::::#     1::::l        1::::l            4::::4                5:::::5  2:::::2
    ######::::######::::######     1::::l        1::::l            4::::4    5555555     5:::::5  2:::::2
         #::::#    #::::#       111::::::111  111::::::111         4::::4    5::::::55555::::::5  2:::::2       222222
         #::::#    #::::#       1::::::::::1  1::::::::::1       44::::::44   55:::::::::::::55   2::::::2222222:::::2
         ######    ######       1::::::::::1  1::::::::::1       4::::::::4     55:::::::::55     2::::::::::::::::::2
                                111111111111  111111111111       4444444444       555555555       22222222222222222222

                                              _
                                          ___| |__   __ _ _ __  _ __ (_) ___
                                         / __| '_ \ / _` | '_ \| '_ \| |/ _ \
                                        | (__| | | | (_| | |_) | |_) | |  __/
                                         \___|_| |_|\__,_| .__/| .__/|_|\___|
                                                         |_|   |_|


                                               ⬇⬇⬇ The Best Code ⬇⬇⬇



*/
package org.firstinspires.ftc.teamcode;

@TeleOp(name="Teleop: Turret Mode", group="Teleop")
//@Disabled
public class teleop extends LinearOpMode {

    MediaPlayer mediaPlayer;
    HardwareRobot robot = new HardwareRobot();
    ElapsedTime runtime = new ElapsedTime();
    boolean cryptoboxMode = false;

    // could also use HardwarePushbotMatrix class.
    @Override
    public void runOpMode() {
        String chipper = "\n"+
                "⬜⬛︎⬛         ⬛⬜︎⬛         ⬜⬛︎⬜"+"\n"+
                "⬜︎⬜︎⬛         ⬜⬛︎⬜         ⬛⬜︎⬛"+"\n"+
                "⬛︎⬜⬜         ⬛⬜︎⬛         ⬛⬜︎⬛"+"\n"+
                "⬛︎⬛⬜         ⬜⬛⬜         ⬜⬛⬜";
        double  driveX;
        double  driveY;
        double  rotation;

        double  sesetivity = 1.0;
        double  collectPW_right = 0.0;
        double  collectPW_left = 0.0;

        int     count = 0;
        final int MODULU = 1000;

        robot.init(hardwareMap);

        // print message to signify robot waiting
        telemetry.addData("Say", "Hello Driver! Press Play to start the game");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.initServos();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {
            // print the chipper to driver
            telemetry.addData("chipper",chipper);

            driveY = -gamepad1.left_stick_y * sesetivity;
            driveX = -gamepad1.left_stick_x * sesetivity;
            rotation = gamepad1.right_stick_x * sesetivity;
            double lf = driveX + driveY + rotation;
            double rf = -driveX + driveY - rotation;
            double lr = -driveX + driveY + rotation;
            double rr = driveX + driveY - rotation;
            
            robot.leftFrontDrive.setPower(lf);
            robot.rightFrontDrive.setPower(rf);
            robot.leftRearDrive.setPower(lr);
            robot.rightRearDrive.setPower(rr);


            if (gamepad1.left_trigger > 0.05) {
                collectPW_right = -gamepad1.left_trigger;
                collectPW_left = -gamepad1.left_trigger;
                telemetry.addData("left trigger", gamepad1.left_trigger);
            } else if (gamepad1.right_trigger > 0.05) {
                collectPW_right = gamepad1.right_trigger;
                collectPW_left = gamepad1.right_trigger;
                telemetry.addData("right trigger: ", gamepad1.left_trigger);
            }
            robot.squeeshCollectorRight.setPower(collectPW_right);
            robot.squeeshCollectorLeft.setPower(collectPW_left);

            // arm control
            if (gamepad1.y){
                // arm go up until the position, then, continue slowly
                if (robot.arm1.getCurrentPosition() < -200) {
                    robot.arm1.setPower(-0.5);
                    robot.arm2.setPower(-0.5);
                } else {
                    robot.arm1.setPower(-1);
                    robot.arm2.setPower(-1);
                }
            } else if (gamepad1.a && !gamepad1.start) {
                // arm go down until the position, then, it comes down with gravity
                if (robot.arm1.getCurrentPosition() < -255) {
                    robot.arm1.setPower(HardwareBastion.ARM_DOWN);
                    robot.arm2.setPower(HardwareBastion.ARM_DOWN);
                }
            }
            else {
                robot.arm1.setPower(HardwareBastion.ARM_STOP);
                robot.arm2.setPower(HardwareBastion.ARM_STOP);
            }
            // reset the encoder when the arm in the final position
            if (!robot.btn.getState()){
                robot.arm1.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            }
            // arm control
            if (gamepad1.b) {
                robot.clawServo = robot.CLAW_UP;
                count = 0;
            }
            else if (gamepad1.x) {
                robot.clawServo = robot.CLAW_DOWN;
                count = 0;
            }
            
            boolean servoMod = false;
            if (count%MODULU == 0)
            {
                robot.claw_right.setPosition(robot.clawServo);
                robot.claw_left.setPosition(robot.clawServo);
                //robot.patay.setPosition(robot.patayServo);
                count = 0;
                servoMod = true;
            }
            count++;

            telemetry.update();

            // Pace this loop so jaw action is reasonable speed.
            sleep(50);
        }
    }
}
