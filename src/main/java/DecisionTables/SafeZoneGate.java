package DecisionTables;

public class SafeZoneGate {



    public enum AccessOutcome {
        INVALID_SENSOR_DATA,
        TERMINATE_THREAT,
        DENY_FULL_CAPACITY,
        QUARANTINE_AND_TREAT,
        ADMIT_DISARMED,
        ADMIT_FULL_ACCESS
    }

    /**
     * Evaluates survivor access based on raw numeric bio-sensor data and security counts.
     */
    public static AccessOutcome evaluateSurvivor(
            double bodyTempCelsius,
            int hoursSinceBitten,
            int weaponCount,
            int currentOccupancy,
            int maxCapacity) {

        // Step 1: Input Validation (System Boundaries)
        if (bodyTempCelsius < 30.0 || bodyTempCelsius > 45.0 ||
                hoursSinceBitten < 0 || weaponCount < 0 ||
                currentOccupancy < 0 || maxCapacity <= 0) {
            return AccessOutcome.INVALID_SENSOR_DATA;
        }

        // Step 2: Equivalence Partitioning (Mapping Numeric Ranges to Boolean Flags)
        boolean isInfected = (bodyTempCelsius >= 38.0) || (hoursSinceBitten > 0);
        boolean isTreatable = (hoursSinceBitten > 0) && (hoursSinceBitten <= 12);
        boolean isArmed = (weaponCount > 0);
        boolean isShelterFull = (currentOccupancy >= maxCapacity);

        // Step 3: Decision Logic
        if (isInfected && !isTreatable) {
            return AccessOutcome.TERMINATE_THREAT;
        }

        if (isShelterFull) {
            return AccessOutcome.DENY_FULL_CAPACITY;
        }

        if (isInfected && isTreatable) {
            return AccessOutcome.QUARANTINE_AND_TREAT;
        }

        return isArmed ? AccessOutcome.ADMIT_DISARMED : AccessOutcome.ADMIT_FULL_ACCESS;
    }





}
