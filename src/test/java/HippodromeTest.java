import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.jupiter.api.Assertions.*;

class HippodromeTest {

    @Test
    void hippodrome_giveNullToConstructor_throwIllegalArgumentException() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    void hippodrome_giveNullToConstructor_throwIllegalArgumentExceptionWithMessage() {
        String expectedMessage = "Horses cannot be null.";
        String actualMessage = "";
        try {
            new Hippodrome(null);
        } catch (IllegalArgumentException e) {
            actualMessage = e.getMessage();
        }

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void hippodrome_giveEmptyListToConstructor_throwIllegalArgumentException() {
        List<Horse> emptyList = Collections.emptyList();

        Assertions.assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
    }

    @Test
    void hippodrome_giveEmptyListToConstructor_throwIllegalArgumentExceptionWithMessage() {
        List<Horse> emptyList = Collections.emptyList();
        String expectedMessage = "Horses cannot be empty.";
        String actualMessage = "";
        try {
            new Hippodrome(emptyList);
        } catch (IllegalArgumentException e) {
            actualMessage = e.getMessage();
        }
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void getHorses() {
        List<Horse> expectedListHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            expectedListHorses.add(new Horse("Horse", ThreadLocalRandom.current().nextDouble()));
        }
        Hippodrome hippodrome = new Hippodrome(expectedListHorses);
        List<Horse> actualListHorses = hippodrome.getHorses();

        Assertions.assertEquals(expectedListHorses, actualListHorses);
    }

    @Test
    void move() {
        List<Horse> horses = new ArrayList<>();
        Horse horseMock = Mockito.mock(Horse.class);
        int times = 50;
        for (int i = 0; i < times; i++) {
            horses.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        Mockito.verify(horseMock, Mockito.times(times)).move();
    }

    @Test
    void getWinner() {
        Horse horse1 = new Horse("Horse", 15, 1);
        Horse horse2 = new Horse("Horse", 15, 1.5);
        Horse horse3 = new Horse("Horse", 16, 2);
        double expectedDistance = 2;
        List<Horse> horses = List.of(horse1, horse2, horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();
        double actualDistance = winner.getDistance();

        Assertions.assertEquals(expectedDistance, actualDistance);
    }
}