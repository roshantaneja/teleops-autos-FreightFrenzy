package org.firstinspires.ftc.teamcode;
/*
 */

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.AnalogInput;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Team 9960 Revision 170119.0
 * This program is the scaffold for autonomous operation.
 * Designed to push the correct button on both beacons
 *
 * This robot uses four VEX Mecanum wheels, each direct driven by Neverest 40 motors.
 * It is designed as a linear op mode, and uses RUN_TO_POSITION motor operation.
 *
 */

@Autonomous(name="AutoRedBoth")  // @TeleOp(...) is the other common choice
// @Disabled
public class SussyWussy extends LinearOpMode {

    // Declare Devices
    DcMotor  leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;


    // drive motor position variables
    private int lfPos; private int rfPos; private int lrPos; private int rrPos;

    // operational constants
    private double fast = 0.5; // Limit motor power to this value for Andymark RUN_USING_ENCODER mode
    private double medium = 0.3; // medium speed
    private double slow = 0.1; // slow speed
    private double clicksPerInch = 87.5; // empirically measured
    private double clicksPerDeg = 21.94; // empirically measured

    @Override
    public void runOpMode() {
        telemetry.setAutoClear(true);


        // Initialize the hardware variables.
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");

        // The right motors need reversing
        leftFront.setDirection(DcMotor.Direction.REVERSE);
        rightFront.setDirection(DcMotor.Direction.FORWARD);
        leftBack.setDirection(DcMotor.Direction.REVERSE);
        rightBack.setDirection(DcMotor.Direction.FORWARD);

        // Set the drive motor run modes:
        leftFront.setTargetPosition(0);
        rightFront.setTargetPosition(0);
        leftBack.setTargetPosition(0);
        rightBack.setTargetPosition(0);
        leftFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFront.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBack.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightFront.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightBack.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        // *****************Dead reckoning list*************
        // Distances in inches, angles in deg, speed 0.0 to 0.6
        moveForward(16, fast);
        /*
        turnClockwise(-45, fast);
        moveForward(33, fast);
        turnClockwise(-45, fast);
        moveForward(24, fast);
        moveToLine(24, medium);

        moveForward(-6, fast);
        turnClockwise(-3, medium); // aiming tweak
        moveRight(36, fast);
        moveToLine(24, medium);

        moveForward(-12, fast);
        turnClockwise(-135, fast);
        moveForward(66, fast);
        */
    }

    private void moveForward(int howMuch, double speed) {
        // howMuch is in inches. A negative howMuch moves backward.

        // fetch motor positions
        lfPos =  leftFront.getCurrentPosition();
        rfPos = rightFront.getCurrentPosition();
        lrPos = leftBack.getCurrentPosition();
        rrPos = rightBack.getCurrentPosition();

        // calculate new targets
        lfPos += howMuch * clicksPerInch;
        rfPos += howMuch * clicksPerInch;
        lrPos += howMuch * clicksPerInch;
        rrPos += howMuch * clicksPerInch;

        // move robot to new position
        leftFront.setTargetPosition(lfPos);
        rightFront.setTargetPosition(rfPos);
        leftBack.setTargetPosition(lrPos);
        rightBack.setTargetPosition(rrPos);
        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);

        // wait for move to complete
        while ( leftFront.isBusy() && rightFront.isBusy() &&
                leftBack.isBusy() && rightBack.isBusy()) {

            // Display it for the driver.
            telemetry.addLine("Move Foward");
            telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            telemetry.addData("Actual", "%7d :%7d",  leftFront.getCurrentPosition(),
                    rightFront.getCurrentPosition(), leftBack.getCurrentPosition(),
                    rightBack.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }

    private void moveRight(int howMuch, double speed) {
        // howMuch is in inches. A negative howMuch moves backward.

        // fetch motor positions
        lfPos =  leftFront.getCurrentPosition();
        rfPos = rightFront.getCurrentPosition();
        lrPos = leftBack.getCurrentPosition();
        rrPos = rightBack.getCurrentPosition();

        // calculate new targets
        lfPos += howMuch * clicksPerInch;
        rfPos -= howMuch * clicksPerInch;
        lrPos -= howMuch * clicksPerInch;
        rrPos += howMuch * clicksPerInch;

        // move robot to new position
        leftFront.setTargetPosition(lfPos);
        rightFront.setTargetPosition(rfPos);
        leftBack.setTargetPosition(lrPos);
        rightBack.setTargetPosition(rrPos);
        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);

        // wait for move to complete
        while ( leftFront.isBusy() && rightFront.isBusy() &&
                leftBack.isBusy() && rightBack.isBusy()) {

            // Display it for the driver.
            telemetry.addLine("Strafe Right");
            telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            telemetry.addData("Actual", "%7d :%7d",  leftFront.getCurrentPosition(),
                    rightFront.getCurrentPosition(), leftBack.getCurrentPosition(),
                    rightBack.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);

    }

    private void turnClockwise(int whatAngle, double speed) {
        // whatAngle is in degrees. A negative whatAngle turns counterclockwise.

        // fetch motor positions
        lfPos =  leftFront.getCurrentPosition();
        rfPos = rightFront.getCurrentPosition();
        lrPos = leftBack.getCurrentPosition();
        rrPos = rightBack.getCurrentPosition();

        // calculate new targets
        lfPos += whatAngle * clicksPerDeg;
        rfPos -= whatAngle * clicksPerDeg;
        lrPos += whatAngle * clicksPerDeg;
        rrPos -= whatAngle * clicksPerDeg;

        // move robot to new position
        leftFront.setTargetPosition(lfPos);
        rightFront.setTargetPosition(rfPos);
        leftBack.setTargetPosition(lrPos);
        rightBack.setTargetPosition(rrPos);
        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);

        // wait for move to complete
        while ( leftFront.isBusy() && rightFront.isBusy() &&
                leftBack.isBusy() && rightBack.isBusy()) {

            // Display it for the driver.
            telemetry.addLine("Turn Clockwise");
            telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            telemetry.addData("Actual", "%7d :%7d",  leftFront.getCurrentPosition(),
                    rightFront.getCurrentPosition(), leftBack.getCurrentPosition(),
                    rightBack.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }
    private void moveToLine(int howMuch, double speed) {
        // howMuch is in inches. The robot will stop if the line is found before
        // this distance is reached. A negative howMuch moves left, positive moves right.

        // fetch motor positions
        lfPos =  leftFront.getCurrentPosition();
        rfPos = rightFront.getCurrentPosition();
        lrPos = leftBack.getCurrentPosition();
        rrPos = rightBack.getCurrentPosition();

        // calculate new targets
        lfPos += howMuch * clicksPerInch;
        rfPos -= howMuch * clicksPerInch;
        lrPos -= howMuch * clicksPerInch;
        rrPos += howMuch * clicksPerInch;

        // move robot to new position
        leftFront.setTargetPosition(lfPos);
        rightFront.setTargetPosition(rfPos);
        leftBack.setTargetPosition(lrPos);
        rightBack.setTargetPosition(rrPos);
        leftFront.setPower(speed);
        rightFront.setPower(speed);
        leftBack.setPower(speed);
        rightBack.setPower(speed);

        // wait for move to complete
        while ( leftFront.isBusy() && rightFront.isBusy() &&
                leftBack.isBusy() && rightBack.isBusy()) {

            // Display it for the driver.
            telemetry.addLine("Move To Line");
            telemetry.addData("Target", "%7d :%7d", lfPos, rfPos, lrPos, rrPos);
            telemetry.addData("Actual", "%7d :%7d",  leftFront.getCurrentPosition(),
                    rightFront.getCurrentPosition(), leftBack.getCurrentPosition(),
                    rightBack.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightBack.setPower(0);
    }
}