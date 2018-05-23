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

@Autonomous(name="Autonomous: vuMark", group="Autonomous")
//@Disabled
public class vuMark extends LinearOpMode {
    @Override
    public void runOpMode() {
    
        // start up Vuforia and view the camera monitor on the RC phone
        VuforiaLocalizer vuforia;
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // License Key from https://developer.vuforia.com/license-manager
        parameters.vuforiaLicenseKey = "abc123";
        
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        vuforia = ClassFactory.createVuforiaLocalizer(parameters);
        VuforiaTrackables relicTrackables = vuforia.loadTrackablesFromAsset("RelicVuMark");
        VuforiaTrackable relicTemplate = relicTrackables.get(0);
        relicTemplate.setName("relicVuMarkTemplate");

        telemetry.addData(">",  "Wait For Start");
        telemetry.update();

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        robot.initServos();
        relicTrackables.activate();

        int counter = 0;
        boolean visible=false;
        while (opModeIsActive() && !visible && counter<100) {
            RelicRecoveryVuMark vuMark = RelicRecoveryVuMark.from(relicTemplate);
            if (vuMark != RelicRecoveryVuMark.UNKNOWN) {
                // RIGHT
                if (vuMark==RelicRecoveryVuMark.RIGHT){
                  // code for vuMark right column
                  visible=true;
                }
                // CENTER
                if (vuMark==RelicRecoveryVuMark.CENTER){
                  // code for vuMark center column
                  visible=true;
                }
                // LEFT
                if (vuMark==RelicRecoveryVuMark.LEFT){
                  // code for vuMark left column
                  visible=true;
                }
            } else {
                if (counter>=99){
                  // code for vuMark not visible. For ex. put the Gliph in the right column
                  visible=true;
                }
            }
            counter++;
            telemetry.update();
        }
        relicTrackables.deactivate();
    }
}
