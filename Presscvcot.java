package org.firstinspires.ftc.teamcode.teleops;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.Range;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.Blinker;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.Gyroscope;

@TeleOp

public class Presscvcot extends LinearOpMode{
    private DcMotor leftBack;
    private DcMotor leftFront;
    private DcMotor rightBack;
    private DcMotor rightFront;

    @Override
    public void runOpMode() {
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);
        
        

        // Put initialization blocks here.
        waitForStart();
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {
                double drive = gamepad1.left_stick_y;
                double rotate = gamepad1.right_stick_x;
                double strafe = gamepad1.left_stick_x;

                // Put loop blocks here.

                /*
                double leftFrontPower;
                double leftBackPower;
                double rightFrontPower;
                double rightBackPower;
                 */

                double leftFrontPower = Range.clip(drive + strafe + rotate, -1.0, 1.0);
                double leftBackPower = Range.clip(drive - strafe + rotate, -1.0, 1.0);
                double rightFrontPower = Range.clip(drive - strafe - rotate, -1.0, 1.0);
                double rightBackPower = Range.clip(drive + strafe - rotate, -1.0, 1.0);

                leftFront.setPower(leftFrontPower);
                leftBack.setPower(leftBackPower);
                rightFront.setPower(rightFrontPower);
                rightBack.setPower(rightBackPower);
                telemetry.update();
            }
        }
    }
}
