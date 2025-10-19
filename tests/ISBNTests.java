import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


public class ISBNTests {
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
    @CsvSource({
        "034553837, 0345538374",
        "195364945, 1953649459",
        "048628211, 0486282112",
        "091514486, 0915144867",
        "097677366, 097677366X",
        "075381675, 075381675X"
    })
    @DisplayName("Tests that ISBN.main correctly computes the check digit for given first 9 digits")
    public void testISBNCheckDigit(String firstNine, String expectedISBN) {
        ISBN.main(new String[]{firstNine});
        String output = out.toString().trim();
        assertEquals(expectedISBN, output, "ISBN output for " + firstNine + " should be " + expectedISBN);
    }
}