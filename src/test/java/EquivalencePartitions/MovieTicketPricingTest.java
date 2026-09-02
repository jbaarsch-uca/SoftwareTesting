package EquivalencePartitions;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;


public class MovieTicketPricingTest {

    @ParameterizedTest
    @CsvFileSource(resources="/movie_ticket_test_cases.csv", numLinesToSkip = 1)
    public void testTicketing(String testName, int inputAge, TicketType expectedResult, String EPTested  ) {

        TicketType result = MovieTicketPricing.getTicket(inputAge);
        assertEquals(expectedResult, result, testName + ": See TCI " + EPTested);


    }





        }