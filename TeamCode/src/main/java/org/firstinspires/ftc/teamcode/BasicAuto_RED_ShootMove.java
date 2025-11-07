package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name="Auto RED: shoot & move", group="Basic Auto")
public class BasicAuto_RED_ShootMove extends LinearOpMode {

    DcMotor frontLeftDrive, backLeftDrive, frontRightDrive, backRightDrive;
    DcMotor shooter;
    CRServo intakeLeft, intakeRight;
    ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        frontLeftDrive = hardwareMap.get(DcMotor.class, "leftFront");
        backLeftDrive = hardwareMap.get(DcMotor.class, "leftBack");
        frontRightDrive = hardwareMap.get(DcMotor.class, "rightFront");
        backRightDrive = hardwareMap.get(DcMotor.class, "rightBack");

        frontLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        backLeftDrive.setDirection(DcMotor.Direction.FORWARD);
        frontRightDrive.setDirection(DcMotor.Direction.REVERSE);
        backRightDrive.setDirection(DcMotor.Direction.REVERSE);

        shooter = hardwareMap.get(DcMotor.class, "shooter");
        intakeLeft = hardwareMap.get(CRServo.class, "intakeLeft");
        intakeRight = hardwareMap.get(CRServo.class, "intakeRight");

        shooter.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        shooter.setDirection(DcMotor.Direction.FORWARD);
        intakeLeft.setDirection(DcMotor.Direction.REVERSE);
        intakeRight.setDirection(DcMotor.Direction.FORWARD);

        telemetry.addData("Status", "Initialized");
        telemetry.addLine("AUTO - Blue alliance");
        telemetry.update();

        waitForStart();
        runtime.reset();

        telemetry.addData("Status", "Running shooter");
        telemetry.update();

        shooter.setPower(0.5);
        sleep(1000);
        intakeLeft.setPower(0.15);
        intakeRight.setPower(0.15);
        sleep(3000);
        shooter.setPower(0.0);
        intakeLeft.setPower(0.0);
        intakeRight.setPower(0.0);

        telemetry.addData("Status", "Moving");
        telemetry.update();

        move(-0.5);
        sleep(600);
        strafe(-0.5); // Positive is right, negative is left
        sleep(600);
        stopRobot();

        telemetry.addData("Status", "Done/stopped");
        telemetry.update();
    }
    void sleep(int ms) {
        double startTime = runtime.milliseconds();
        while(opModeIsActive() && (runtime.milliseconds() - startTime) < ms);
        // Wait for `ms` milliseconds
    }

    void move(double speed) {
        if(!opModeIsActive()) {
            stopRobot();
            return;
        } // Stop the robot if it shouldn't me moving

        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(speed);
        backLeftDrive.setPower(speed);
        backRightDrive.setPower(speed);
    }

    void strafe(double speed) {
        if(!opModeIsActive()) {
            stopRobot();
            return;
        } // Stop the robot if it shouldn't me moving

        frontLeftDrive.setPower(speed);
        frontRightDrive.setPower(-speed);
        backLeftDrive.setPower(-speed);
        backRightDrive.setPower(speed);
    }

    void stopRobot() {
        frontLeftDrive.setPower(0);
        frontRightDrive.setPower(0);
        backLeftDrive.setPower(0);
        backRightDrive.setPower(0);
    }
}
