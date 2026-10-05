package InjectionAndMocking;

public interface PaymentGateway {

    public boolean charge(String amount, double totalAmt);
}
