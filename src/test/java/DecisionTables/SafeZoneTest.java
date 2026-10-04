package DecisionTables;

import static DecisionTables.SafeZoneGate.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

public class SafeZoneTest {

    @ParameterizedTest
    @CsvFileSource(resources="/SafeZoneGateTestCases.csv", numLinesToSkip = 1)
    public void testEvaluateSurvivor(String testID,
                                     double bodyTempCelsius,
                                     int hoursSinceBitten,
                                     int weaponCount,
                                     int currentOccupancy,
                                     int maxCapacity, AccessOutcome expectedOutcome){
        AccessOutcome result = SafeZoneGate.evaluateSurvivor(
                bodyTempCelsius,
                hoursSinceBitten,
                weaponCount,
                currentOccupancy,
                maxCapacity);

        assertEquals(expectedOutcome, result, "Test Case " + testID + "Failed.");
    }

}
