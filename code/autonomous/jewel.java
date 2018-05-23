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

@Autonomous(name="Autonomous: Jewel", group="Autonomous")
//@Disabled
public class jewel extends LinearOpMode {
    private HardwareRobot robot = new HardwareRobot();

    @Override
    public void runOpMode() {
        
        telemetry.addData(">",  "Wait For Start");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.initServos();

        
        //Alliance alliance = Alliance.BLUE;
        Alliance alliance = Alliance.RED;

        // get a reference to the color sensor.
        sensorColor = robot.colorSensor;
        sensorColor.enableLed(true);
        if ((alliance.equals(Alliance.BLUE) && sensorColor.red()/1.2 > sensorColor.blue()) ||
                (alliance.equals(Alliance.RED) && sensorColor.blue()/1.2 > sensorColor.red())){
            // If the color sensor detects the right jewel, the robot should flip the left jewel
            // If the color sensor detects the left jewel, the robot should flip the right jewel
        }else if ((alliance.equals(Alliance.BLUE) && sensorColor.blue()/1.2 > sensorColor.red())||
                (alliance.equals(Alliance.RED) && sensorColor.red()/1.2 > sensorColor.blue())){
            // If the color sensor detects the right jewel, the robot should flip the right jewel
            // If the color sensor detects the left jewel, the robot should flip the left jewel
        }
        sensorColor.enableLed(false);
    }
    enum Alliance {BLUE, RED}
}
