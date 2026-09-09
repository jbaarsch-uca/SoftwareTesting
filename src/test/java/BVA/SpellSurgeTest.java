package BVA;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;




public class SpellSurgeTest {

    @ParameterizedTest
    @CsvFileSource(resources="/SpellSurge ExampleNonErrorTests.csv", numLinesToSkip = 1)
    public void testSpellSurge(String testCaseName, int heroLevel, int manaInvested, int damage ) {
        Wizard merlin = new Wizard();

        int result = merlin.spellSurge(heroLevel, manaInvested);

        assertEquals(damage, result, "" + testCaseName + " failed.");
    }

    @ParameterizedTest
    @CsvFileSource(resources="/SpellSurge ExampleErrorTests.csv", numLinesToSkip = 1)
    public void testSpellSurge(String testCaseName, int heroLevel, int manaInvested, int damage ) {
        Wizard harryPotter = new Wizard();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> { harryPotter.spellSurge(heroLevel, manaInvested)
                });


    }

    }


