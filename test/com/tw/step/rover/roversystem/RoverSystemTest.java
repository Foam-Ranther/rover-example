package com.tw.step.rover.roversystem;

import com.tw.step.rover.boundary.InfinitePlateau;
import com.tw.step.rover.boundary.Plateau;
import com.tw.step.rover.commands.MoveCommand;
import com.tw.step.rover.commands.RoverCommands;
import com.tw.step.rover.position.Coordinate;
import com.tw.step.rover.position.Direction;
import com.tw.step.rover.position.Navigator;
import com.tw.step.rover.rover.Rover;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RoverSystemTest {
    @Test
    void shouldExecuteCommandsForAddedRover() {
        RoverSystem roverSystem = new RoverSystem();
        Rover rover = new Rover(new Coordinate(0, 0), Direction.N);
        RoverCommands commands = new RoverCommands();
        commands.add(new MoveCommand(Navigator.create(), new InfinitePlateau()));

        roverSystem.addRover(rover);
        roverSystem.addCommands(commands);
        roverSystem.execute();

        assertEquals("0 1 N", roverSystem.toString());
    }

    @Test
    void shouldMoveWithinBoundedPlateau() {
        RoverSystem roverSystem = new RoverSystem();
        Rover rover = new Rover(new Coordinate(0, 0), Direction.N);
        RoverCommands commands = new RoverCommands();
        Coordinate leftBottom = new Coordinate(0, 0);
        Coordinate topRight = new Coordinate(5, 5);
        commands.add(new MoveCommand(Navigator.create(), new Plateau(leftBottom, topRight)));
        commands.add(new MoveCommand(Navigator.create(), new Plateau(leftBottom, topRight)));

        roverSystem.addRover(rover);
        roverSystem.addCommands(commands);
        roverSystem.execute();

        assertEquals("0 2 N", roverSystem.toString());
    }

//    @Test
//    void shouldMoveWithinBoundedPlateau() {
//        RoverSystem roverSystem = new RoverSystem();
//        Rover rover = new Rover(new Coordinate(0, 0), Direction.N);
//        RoverCommands commands = new RoverCommands();
//        Coordinate leftBottom = new Coordinate(0, 0);
//        Coordinate topRight = new Coordinate(5, 5);
//        commands.add(new MoveCommand(Navigator.create(), new Plateau(leftBottom, topRight)));
//        commands.add(new MoveCommand(Navigator.create(), new Plateau(leftBottom, topRight)));
//
//        roverSystem.addRover(rover);
//        roverSystem.addCommands(commands);
//        roverSystem.execute();
//
//        assertEquals("0 2 N", roverSystem.toString());
//    }
}
