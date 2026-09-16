package EquivalencePartitions;
import static EquivalencePartitions.TicketType.*;

public class MovieTicketPricing {


    public static TicketType getTicket(int age) {
        if (age < 0)
            return REJECT;
        if (age >= 0 && age < 12)
            return CHILD;
        if (age >=12 && age < 65)
            return STANDARD;
        if (age >=65 && age <=120)
            return SENIOR;
        return REJECT;


    }








}
