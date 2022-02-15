package model.services;

public class PaypalServices implements OnlinePaymentService{

    private static final Double PAYMENT_FEE = 0.02;
    private static final Double MONTH_INTEREST = 0.01;

    @Override
    public Double paymentFee(Double amount) {
        return amount * PAYMENT_FEE;
    }

    @Override
    public Double interest(Double amount, Integer months) {
        return amount * months * MONTH_INTEREST;
    }
}
