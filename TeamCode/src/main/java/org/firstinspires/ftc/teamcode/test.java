package org.firstinspires.ftc.teamcode;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.seattlesolvers.solverslib.hardware.AbsoluteAnalogEncoder;
import com.seattlesolvers.solverslib.hardware.motors.CRServoEx;
import com.seattlesolvers.solverslib.hardware.motors.Motor;
import com.seattlesolvers.solverslib.hardware.servos.ServoEx;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


@TeleOp
@Configurable
public class test extends LinearOpMode {
    ServoEx servoEX;
    CRServoEx crServoEx;
    AbsoluteAnalogEncoder encoder;

    Motor motor = new Motor(hardwareMap, "motor", Motor.GoBILDA.RPM_1150);

    public static double  p = 0.0, i = 0.0, d =0.0, f = 0.0;
    com.qualcomm.robotcore.hardware.PIDFCoefficients coeff  = new PIDFCoefficients(p, i, d, f);

    @Override
    public void runOpMode() throws InterruptedException {
        servoEX = new ServoEx(hardwareMap, "servo",0.0,90.0);

        encoder = new AbsoluteAnalogEncoder(hardwareMap, "encoder");
        crServoEx = new CRServoEx(hardwareMap, "crServo", "encoder", 3.3, AngleUnit.DEGREES, CRServoEx.RunMode.OptimizedPositionalControl);

        crServoEx.setPIDF(coeff);
        crServoEx.setCachingTolerance(0.005);
        motor.resetEncoder();

        while (!isStopRequested()){

            if (gamepad1.a){
                servoEX.set(45);
            }

            if (gamepad1.b){
                crServoEx.set(45);
            }

        }
    }
}
