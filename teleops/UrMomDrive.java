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
public class UrMomDrive extends LinearOpMode{
    //init motors
    private DcMotor leftBack;
    private DcMotor leftFront;
    private DcMotor rightBack;
    private DcMotor rightFront;
    private DcMotor intake;
    private DcMotor conveyor;
    private DcMotor linearSlide;

    //init servos
    private Servo dumpy;
    private CRServo carousel;



    @Override
    public void runOpMode() {
        //Drive motors
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        rightBack.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.REVERSE);

        //Intake motors
        intake = hardwareMap.get(DcMotor.class, "intake");
        conveyor = hardwareMap.get(DcMotor.class, "conveyor");

        //linear slide motor
        linearSlide = hardwareMap.get(DcMotor.class, "lift");

        //map dumpy
        dumpy = hardwareMap.get(Servo.class, "dumpy");

        //map carousel servo
        carousel = hardwareMap.get(CRServo.class, "carousel");



        // Put initialization blocks here.
        waitForStart();
        dumpy.setPosition(0);
        if (opModeIsActive()) {
            // Put run blocks here.
            while (opModeIsActive()) {

                //Intake Inputs
                double intakePwr = gamepad1.left_trigger;
                double outtakePwr = gamepad1.right_trigger;

                //Drive Inputs
                double lpwr = gamepad1.left_stick_y;
                double rpwr = gamepad1.right_stick_y;
                double rstrf = gamepad1.right_stick_x;
                double lstrf = gamepad1.left_stick_x;

                //dumpy inputs
                boolean dumpyPower = gamepad1.y;

                // Set Motion variables

                // Set Drive Motion
                double leftFrontPower = Range.clip(lpwr-lstrf, -1.0, 1.0);
                double leftBackPower = Range.clip(lpwr+lstrf, -1.0, 1.0);
                double rightFrontPower = Range.clip(rpwr+rstrf, -1.0, 1.0);
                double rightBackPower = Range.clip(rpwr-rstrf, -1.0, 1.0);

                //set intake motion x


                //move drive motors
                leftFront.setPower(leftFrontPower);
                leftBack.setPower(leftBackPower);
                rightFront.setPower(rightFrontPower);
                rightBack.setPower(rightBackPower);

                //move intake motors
                intake.setPower(intakePwr);
                conveyor.setPower(intakePwr);

                //move linear slide
                if (gamepad1.dpad_up || gamepad1.a) {
                    linearSlide.setPower(1.0);
                } else if (gamepad1.dpad_down || gamepad1.b) {
                    linearSlide.setPower(-1.0);
                } else {
                    linearSlide.setPower(0);
                }

                //rotate dumpy
                if (dumpyPower){
                    dumpy.setPosition(0.7);
                    dumpy.setPosition(0);
                }

                //rotate carousel



                telemetry.update();
            }
        }
    }
}
