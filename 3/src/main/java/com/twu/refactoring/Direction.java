package com.twu.refactoring;

public class Direction {
    private final char direction;
    private static final String DIRECTIONS = "NWSE";

    public Direction(char direction) {
        this.direction = direction;
    }

    public Direction turn(String leftOrRight) {
        if (leftOrRight.equals("right"))
            return new Direction(DIRECTIONS.charAt((DIRECTIONS.indexOf(direction) + 3) % 4));
        return new Direction(DIRECTIONS.charAt((DIRECTIONS.indexOf(direction) + 1) % 4));
    }

    @Override
    public boolean equals(Object obj) {
        Direction directionObj = (Direction) obj;

        if (direction != directionObj.direction || obj == null || getClass() != obj.getClass()) return false;
        return true;
    }
}
