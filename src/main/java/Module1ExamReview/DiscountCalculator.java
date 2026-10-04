package Module1ExamReview;

public class DiscountCalculator {

    public static double getDiscountPercent(int age, boolean isMember) {



        if (age < 0 || age > 120) {
            throw new IllegalArgumentException("Invalid age");
        }
        if (age < 18) {
            // Fault #1: Equivalence Partitions.  Will fail because the whole partition logic for children is wrong.
            //return isMember? .5:.4;
            return isMember ? .25 : .15;
        }
        // Fault #2: Boundary Value Analysis.  Will fail only when the boundary is tested.
        //if (age > 65) {
        if (age >= 65) {
            // Fault #3: Decision Tables.  Will fail only when both Senior Discount is true and isMember is false.
            //return isMember ? .30 : .40;

            // Fault #4: Code Coverage.  Will fail only if age happens to be 75--problem is only identifiable using code
            // coverage tools.
            //if (age == 75)
            //    return isMember ? .5 : .4;
            return isMember ? .30 : .20;
        }

        // Fault #5: Branch Coverage.  Will not execute the age>= 18 is false branch--because it is always true from line 12
        //return (age >= 18 && isMember ) ? .1 : 0;
        return isMember ? .10 : 0;
    }
}