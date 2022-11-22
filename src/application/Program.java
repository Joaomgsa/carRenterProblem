package application;

import model.entities.CarRental;
import model.entities.Vehicle;
import model.services.BrazilTaxServices;
import model.services.RentalService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);

        Scanner sc = new Scanner(System.in);
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        System.out.println("Entre com os dados de Aluguel");
        System.out.print("Modelo do Carro");
        String carModel = sc.nextLine();
        System.out.print("Retirada (dd/MM/yyyy) :");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorno (dd/MM/yyyy) :");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start,finish,new Vehicle(carModel));
        System.out.print("Entre com o preço por hora:");
        double pricePerHour = sc.nextDouble();
        System.out.print("Entre com o preço por dia:");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour,pricePerDay,new BrazilTaxServices());

        rentalService.processInvoice(cr);

        System.out.println("FATURA");
        System.out.println("Pagamento Basico :"+ cr.getInvoice().getBasicPayment());
        System.out.println("Imposto :"+ cr.getInvoice().getTax());
        System.out.println("Pagamento Total :"+ cr.getInvoice().getTotalPayment());

        sc.close();
    }
}