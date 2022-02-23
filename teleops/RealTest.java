package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.util.Range;

@TeleOp
public class RealTest extends LinearOpMode {

    private DcMotor leftBack;
    private DcMotor rightFront;
    private DcMotor leftFront;
    private DcMotor rightBack;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {

        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        conveyor = hardwareMap.get(DcMotor.class, "conveyor");


        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                double leftPwr = gamepad1.left_stick_y;
                double rightPwr = gamepad1.right_stick_y;
                double strafeRight = gamepad1.right_stick_x;
                double strafeLeft = gamepad1.left_stick_x;
                // Put loop blocks here.

                /*
                double leftFrontPower;
                double leftBackPower;
                double rightFrontPower;
                double rightBackPower;
                 */

                double leftFrontPower = Range.clip(-(strafeLeft + strafeRight), -1.0, 1.0);
                double leftBackPower = Range.clip(strafeLeft + strafeRight, -1.0, 1.0);
                double rightFrontPower = Range.clip(-(strafeLeft + strafeRight), -1.0, 1.0);
                double rightBackPower = Range.clip(strafeLeft + strafeRight, -1.0, 1.0);


                /*
                if (strafeRight > rightPwr || strafeLeft > leftPwr) {
                    double leftFrontPower = Range.clip(-(strafeLeft + strafeRight), -1.0, 1.0);
                    double leftBackPower = Range.clip(strafeLeft + strafeRight, -1.0, 1.0);
                    double rightFrontPower = Range.clip(strafeLeft + strafeRight, -1.0, 1.0);
                    double rightBackPower = Range.clip(-(strafeLeft + strafeRight), -1.0, 1.0);
                } else {
                    double leftFrontPower = Range.clip(leftPwr, -1.0, 1.0);
                    double leftBackPower = Range.clip(leftPwr, -1.0, 1.0);
                    double rightFrontPower = Range.clip(rightPwr, -1.0, 1.0);
                    double rightBackPower = Range.clip(rightPwr, -1.0, 1.0);
                } */



                // not highly optimized but it is low latency enough lmao
                leftFront.setPower(leftFrontPower);
                leftBack.setPower(leftBackPower);
                rightFront.setPower(rightFrontPower);
                rightBack.setPower(rightBackPower);
                telemetry.update();
            }
        }
    }
}
