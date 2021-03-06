package se.kth.dd2480.group25.assignment1.helpers;

public final class Constants {
    // Denotes the tolerance when comparing two floating point numbers to account for floating point precision
    public static final double FLOAT_TOLERANCE = 0.0000001d;
    // Custom definition of pi
    public static final double PI = 3.1415926535;

    // Don't allow instantiation of fully static class
    private Constants() {
    }
}
