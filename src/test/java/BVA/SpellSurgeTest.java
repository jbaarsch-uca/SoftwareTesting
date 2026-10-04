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
    public void testSpellSurgeErrors(String testCaseName, int heroLevel,
                                     int manaInvested, String exceptionMessage, String exceptionCode ) {
        Wizard harryPotter = new Wizard();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> { harryPotter.spellSurge(heroLevel, manaInvested);
                });
        assertEquals(exceptionMessage, exception.getMessage(), "" + testCaseName + " failed.");

    }

    @ParameterizedTest
    @CsvFileSource(resources="/SpellSurge ExampleErrorTests.csv", numLinesToSkip = 1)
    public void testSpellSurgeExceptions(String testCaseName, int heroLevel,
                                         int manaInvested, String exceptionMessage, String exceptionCode ) {
        WizardException expectedException = ExceptionTranslator(exceptionCode);
        Wizard harryPotter = new Wizard();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> { harryPotter.spellSurge(heroLevel, manaInvested);
                });
        assertInstanceOf(exception.getClass(), expectedException,  "" + testCaseName + " failed.");
    }





    private WizardException ExceptionTranslator(String exceptionCode) {
        if (exceptionCode.equalsIgnoreCase("HeroLevelException"))
            return new HeroLevelException("Hero Level Error");
        else if (exceptionCode.equalsIgnoreCase("ManaInvestedException"))
            return new ManaInvestedException("Mana Invested Error");
        else
            return new WizardException("Unknown Exception");

    }




    }


