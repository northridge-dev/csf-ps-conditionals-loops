import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.*;


public class RollLoadedDieTests {
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

    @Test
    @DisplayName("Tests that the die rolls follow the expected probability distribution")
    public void testDieDistribution() {
        int[] counts = new int[7]; // index 1-6 for faces
        int numRolls = 10000;

        for (int i = 0; i < numRolls; i++) {
            out.reset();
            RollLoadedDie.main(new String[0]);
            String output = out.toString().trim();
            // Parse output: just the face value as a string
            int face = Integer.parseInt(output);
            assertTrue(face >= 1 && face <= 6, "Face should be between 1 and 6");
            counts[face]++;
        }

        // Check probabilities: 1 and 6 should be ~10%, others ~20%
        // With 10000 rolls, expect 1000 for 1/6, 2000 for 2-5
        // Allowing some variance: approx 3 standard deviations
        assertTrue(counts[1] >= 910 && counts[1] <= 1090, "Count for 1 should be around 1000");
        assertTrue(counts[6] >= 910 && counts[6] <= 1090, "Count for 6 should be around 1000");
        for (int i = 2; i <= 5; i++) {
            assertTrue(counts[i] >= 1880 && counts[i] <= 2120, "Count for " + i + " should be around 2000");
        }
    }
}
