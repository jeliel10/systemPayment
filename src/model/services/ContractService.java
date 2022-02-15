package model.services;

import model.entities.Contract;
import model.entities.Installment;

import java.util.Calendar;
import java.util.Date;

public class ContractService {

    private OnlinePaymentService onlinePaymentService;

    public ContractService(OnlinePaymentService onlinePaymentService){
        this.onlinePaymentService = onlinePaymentService;
    }

    public void processContract(Contract contract, Integer months){
        for(int i=1; i<=months; i++){
            Double amount = contract.getTotalValue()/ months;
            amount = amount + onlinePaymentService.interest(amount, i);
            amount = amount + onlinePaymentService.paymentFee(amount);

            Date newDate = addDate(contract.getDate(), i);

            Installment installment = new Installment(newDate, amount);
            contract.getInstallment().add(installment);
        }
    }

    private Date addDate(Date date, int months){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.MONTH, months);
        return calendar.getTime();
    }
}
