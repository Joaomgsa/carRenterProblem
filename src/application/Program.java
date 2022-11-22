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

        System.out.println("Enter rental data");
        System.out.print("Car Model");
        String carModel = sc.nextLine();
        System.out.print("Pickup (dd/MM/yyyy) :");
        LocalDateTime start = LocalDateTime.parse(sc.nextLine(), fmt);
        System.out.print("Retorn (dd/MM/yyyy) :");
        LocalDateTime finish = LocalDateTime.parse(sc.nextLine(), fmt);

        CarRental cr = new CarRental(start,finish,new Vehicle(carModel));
        System.out.print("Enter price per hour:");
        double pricePerHour = sc.nextDouble();
        System.out.print("Enter price per day:");
        double pricePerDay = sc.nextDouble();

        RentalService rentalService = new RentalService(pricePerHour,pricePerDay,new BrazilTaxServices());

        rentalService.processInvoice(cr);

        System.out.println("INVOICE");
        System.out.println("BASIC PAYMENT :$"+ String.format("%.2f", cr.getInvoice().getBasicPayment()));
        System.out.println("TAX :$"+ String.format("%.2f",cr.getInvoice().getTax()));
        System.out.println("TOTAL PAYMENT :$"+ String.format("%.2f",cr.getInvoice().getTotalPayment()));

        sc.close();
    }
}