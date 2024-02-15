import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
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

}