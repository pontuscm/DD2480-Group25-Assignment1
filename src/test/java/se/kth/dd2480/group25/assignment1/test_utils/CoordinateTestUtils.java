package se.kth.dd2480.group25.assignment1.test_utils;

import se.kth.dd2480.group25.assignment1.Coordinate;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoordinateTestUtils {

    public static Coordinate point(double x, double y) {
        return Coordinate.of(x, y);
    }

    public static List<Coordinate> repeating(double x, double y, double xInc, int repetitions) {
        ArrayList<Coordinate> coordinates = new ArrayList<>();

        for (int i = 0; i < repetitions; ++i) {
            coordinates.add(Coordinate.of(x + xInc * i, y));
        }
        return coordinates;
    }

    public static class ListBuilder {
        List<Coordinate> list = new ArrayList<>();

        public ListBuilder add(Coordinate... points) {
            list.addAll(List.of(points));
            return this;
        }

        public ListBuilder add(Collection<Coordinate> points) {
            list.addAll(points);
            return this;
        }

        public List<Coordinate> get() {
            return list;
        }

        public static ListBuilder create() {
            return new ListBuilder();
        }

    }

}
