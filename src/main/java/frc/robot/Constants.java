// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class LEDConstants {
        public static final int kLEDPWMPort = 0;
        public static final int kBufferSize = 2;
    }

    public static final class SHOOTER {
        public static final int TOP_MOTOR = 13;
        public static final int BOTTOM_MOTOR = 14;
        public static final int BOTTOM_MOTOR2 = 15;
        public static final int ELEVATION_MOTOR = 0;
        public static final int ELEVATION_LOWER_LIMIT = 0;
        public static final int ELEVATION_ENCODER = 0;
        public static final int GATE_SOLENOID_CHANNEL = 0;
        public static final double MIN_TURN = 0.36;
        //22ft 8000,9000
        //27fr 10000
        //public static final double HIGH_VELOCITY = 9000.0;
        //public static final double HIGH_VELOCITY = 11000.0;
        public static final double HIGH_VELOCITY = 10000.0;//dropped to 10 to shoot lower
        public static final double LOW_VELOCITY = 4500.0;
        public static final double TOP_PERCENT_OF_BOTTOM = 1.0; //50%;
        public static final double kP = .45;
        //public static final double kF = .049;
        public static final double kF = .055;
    }
}
