package application;

import model.entities.Contract;
import model.entities.Installment;
import model.services.ContractService;
import model.services.PaypalServices;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {
        Scanner sc = new Scanner(System.in);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        System.out.println("Enter contract data");

        System.out.print("Number: ");
        Integer number = sc.nextInt();

        System.out.print("Date (dd/MM/yyyy): ");
        Date date = sdf.parse(sc.next());

        System.out.print("Contract value: ");
        Double totalValue = sc.nextDouble();

        Contract contract = new Contract(number, date, totalValue);

        System.out.print("Enter number of installments: ");
        Integer installments = sc.nextInt();

        ContractService contractService = new ContractService(new PaypalServices());
        contractService.processContract(contract, installments);

        System.out.println("Installments: ");
        for(Installment installment : contract.getInstallment()){
            System.out.println(installment.getDueDate() + " - " + installment.getAmount());

        }
    }

}
