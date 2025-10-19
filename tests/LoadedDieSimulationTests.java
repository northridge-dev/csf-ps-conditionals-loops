import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.*;


public class LoadedDieSimulationTests {
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
    @DisplayName("Tests that the simulation reports correct frequencies and relative frequencies for 10000 rolls")
    public void testSimulationOutput() {
        int n = 10000;
        LoadedDieSimulation.main(new String[]{String.valueOf(n)});
        String output = out.toString().trim();
        String[] lines = output.split("\n");

        // Check header: should split into 4 parts due to space in "Relative Frequency"
        String[] headerParts = lines[0].split("\\s+");
        assertEquals(4, headerParts.length, "Header should have 4 parts");
        assertEquals("Face", headerParts[0]);
        assertEquals("Frequency", headerParts[1]);
        assertEquals("Relative", headerParts[2]);
        assertEquals("Frequency", headerParts[3]);

        // Check there are 7 lines: header + 6 faces
        assertEquals(7, lines.length, "Should have header and 6 face lines");

        int[] counts = new int[7]; // index 1-6
        double[] relFreqs = new double[7];

        for (int i = 1; i <= 6; i++) {
            String[] parts = lines[i].split("\\s+");
            assertEquals(3, parts.length, "Each face line should have 3 parts: face, frequency, relative frequency");
            int face = Integer.parseInt(parts[0]);
            int freq = Integer.parseInt(parts[1]);
            double rel = Double.parseDouble(parts[2]);
            assertEquals(i, face, "Face should be " + i);
            counts[face] = freq;
            relFreqs[face] = rel;
            assertEquals((double) freq / n, rel, 0.0001, "Relative frequency should match freq/n");
        }

        // Check total frequency
        int total = 0;
        for (int i = 1; i <= 6; i++) total += counts[i];
        assertEquals(n, total, "Total frequency should equal number of rolls");

        // Check distributions: 1 and 6 ~10%, others ~20%
        // Allowing variance: approx 3 standard deviations
        assertTrue(counts[1] >= 910 && counts[1] <= 1090, "Count for 1 should be around 1000");
        assertTrue(counts[6] >= 910 && counts[6] <= 1090, "Count for 6 should be around 1000");
        for (int i = 2; i <= 5; i++) {
            assertTrue(counts[i] >= 1880 && counts[i] <= 2120, "Count for " + i + " should be around 2000");
        }
    }
}