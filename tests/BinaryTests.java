import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class BinaryTests {
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();
    private PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
      originalOut = System.out;
      System.setOut(new PrintStream(out));
    }

    @AfterEach
    void tearDown() {
      System.setOut(originalOut);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 15, 16, 31, 32})
    @DisplayName("Tests that Binary.main correctly converts decimal to binary")
    public void testBinaryConversion(int n) {
        Binary.main(new String[]{String.valueOf(n)});
        String output = out.toString().trim();
        String expected = Integer.toBinaryString(n);
        assertEquals(expected, output, "Binary output for " + n + " should be " + expected);
    }
}