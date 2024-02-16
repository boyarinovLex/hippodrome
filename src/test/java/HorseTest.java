import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class HorseTest {

    private final String name = "Horse";
    private final double speed = 25.2;
    private final double distance = 15;

    @Test
    void horse_firstParameterNull_throwIllegalArgumentException() {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, speed, distance));
    }

    @Test
    void horse_firstParameterNull_throwIllegalArgumentExceptionWithMessage() {
        String messageExpected = "Name cannot be null.";
        String messageActual = "";
        try {
            new Horse(null, speed, distance);
        } catch (IllegalArgumentException e) {
            messageActual = e.getMessage();
        }

        Assertions.assertEquals(messageExpected, messageActual);
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void horse_firstParameterSpacesOrEmptyString_throwIllegalArgumentException(String name) {

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, speed, distance));
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r", "\f"})
    void horse_firstParameterSpacesOrEmptyString_throwIllegalArgumentExceptionWithMessage(String name) {
        String messageExpected = "Name cannot be blank.";
        String messageActual = "";
        try {
            new Horse(name, speed, distance);
        } catch (IllegalArgumentException e) {
            messageActual = e.getMessage();
        }

        Assertions.assertEquals(messageExpected, messageActual);
    }

    @Test
    void horse_secondParameterNegativeNumber_throwIllegalArgumentException() {
        double speed = -1;

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, speed, distance));
    }

    @Test
    void horse_secondParameterNegativeNumber_throwIllegalArgumentExceptionWithMessage() {
        double speed = -1;
        String messageExpected = "Speed cannot be negative.";
        String messageActual = "";
        try {
            new Horse(name, speed, distance);
        } catch (IllegalArgumentException e) {
            messageActual = e.getMessage();
        }

        Assertions.assertEquals(messageExpected, messageActual);
    }

    @Test
    void horse_thirdParameterNegativeNumber_throwIllegalArgumentException() {
        double distance = -1;

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> new Horse(name, speed, distance));
    }

    @Test
    void horse_thirdParameterNegativeNumber_throwIllegalArgumentExceptionWithMessage() {
        double distance = -1;
        String messageExpected = "Distance cannot be negative.";
        String messageActual = "";
        try {
            new Horse(name, speed, distance);
        } catch (IllegalArgumentException e) {
            messageActual = e.getMessage();
        }

        Assertions.assertEquals(messageExpected, messageActual);
    }

    @Test
    void getNameTest() {
        Horse horse = new Horse(name, speed, distance);
        String actual = horse.getName();

        Assertions.assertEquals(name, actual);
    }

    @Test
    void getSpeedTest() {
        Horse horse = new Horse(name, speed, distance);
        double actual = horse.getSpeed();

        Assertions.assertEquals(speed, actual);
    }

    @Test
    void getDistanceTest_getThirdParameterFromConstructor() {
        Horse horse = new Horse(name, speed, distance);
        double actual = horse.getDistance();

        Assertions.assertEquals(distance, actual);
    }

    @Test
    void getDistanceTest_getZeroIfNewInstanceHaveTwoParameter() {
        Horse horse = new Horse(name, speed);
        double expected = 0;
        double actual = horse.getDistance();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void moveTest_VerifyGetRandomDouble() {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            Horse horse = new Horse(name, speed, distance);
            horse.move();

            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"0.2, 0.9, 0.55"})
    void moveTest(double min, double max, double randomDouble) {
        try (MockedStatic<Horse> mockedStatic = Mockito.mockStatic(Horse.class)) {
            mockedStatic.when(() -> Horse.getRandomDouble(min, max)).thenReturn(randomDouble);
            Horse horse = new Horse(name, speed, distance);
            double expectedDistance = distance + speed * randomDouble;
            horse.move();
            double actualDistance = horse.getDistance();

            Assertions.assertEquals(expectedDistance, actualDistance);
        }
    }


}