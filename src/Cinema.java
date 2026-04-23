
import org.w3c.dom.ls.LSOutput;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Cinema {

    public static void statistics (String[][] cinema) {
        int purchasedTickets = 0;
        for (int i = 1; i <= cinema.length - 1; i++) {
            for (int j = 0; j <= cinema[0].length - 1; j++) {
                if (cinema[i][j] == "B") {
                    purchasedTickets++;
                }
            }
        }

        int totalTicketsPrice = 0;
        int totalSeats = (cinema.length - 1) * (cinema[0].length - 1);
        if (totalSeats <= 60) {
            totalTicketsPrice = totalSeats * 10;
        }
        else if ((cinema.length - 1) % 2 == 0) {
            totalTicketsPrice = ((cinema.length - 1) / 2) * (cinema[0].length - 1) * 8 + ((cinema.length - 1) / 2) * (cinema[0].length - 1) * 10;
        }
        else {
            totalTicketsPrice = ((cinema.length - 1) / 2) * (cinema[0].length - 1) * 10 + ((cinema.length - 1) - (cinema.length - 1) / 2) * (cinema[0].length - 1) * 8;
        }


        int currentIncome = 0;
        if (totalSeats <= 60) {
            for (int i = 1; i <= cinema.length - 1; i++) {
                for (int j = 0; j <= cinema[0].length - 1; j++) {
                    if (cinema[i][j] == "B") {
                        currentIncome = currentIncome + 10;
                    }
                }
            }
        }
        else if ((cinema.length - 1) % 2 == 0) {
            for (int i = 1; i <= cinema.length - 1; i++) {
                for (int j = 0; j <= cinema[0].length - 1; j++) {
                    if (cinema[i][j] == "B" && i <= (cinema.length - 1) / 2) {
                        currentIncome = currentIncome + 10;
                    }
                    else if (cinema[i][j] == "B" && i >= (cinema.length - 1) / 2) {
                        currentIncome = currentIncome + 8;
                    }
                }
            }
        }
        else {
            for (int i = 1; i <= cinema.length - 1; i++) {
                for (int j = 0; j <= cinema[0].length - 1; j++) {
                    if (cinema[i][j] == "B" && i <= (cinema.length - 1) / 2) {
                        currentIncome = currentIncome + 10;
                    }
                    else if (cinema[i][j] == "B" && i >= (cinema.length - 1) / 2 + 1) {
                        currentIncome = currentIncome + 8;
                    }
                }
            }
        }

        double percentage = ((double) purchasedTickets / totalSeats)  * 100;
        DecimalFormat df = new DecimalFormat("0.00");

        System.out.println("Number of purchased tickets: " + purchasedTickets);
        if (percentage == 0) {
            System.out.println("Percentage: 0.00%");
        }
        else {
            System.out.println("Percentage: " + df.format(percentage) + "%");
        }
        System.out.println("Current income: $" + currentIncome);
        System.out.println("Total income: $" + totalTicketsPrice);
    }

    public static void printCinema (String[][] cinema) {
        System.out.println("Cinema: ");
        System.out.print(" ");
        for (int i = 1; i <= cinema[0].length - 1; i++) {
            System.out.print(" " + i);
        }
        System.out.println();
        for (int i = 1; i <= cinema.length - 1; i++) {
            System.out.print(i + " ");
            for (int j = 1; j <= cinema[0].length - 1; j++) {
                System.out.print(cinema[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static String[][] buyTicket(String[][] cinema, int selectedSeat, int selectedRow, boolean printMessage) {
        if (selectedSeat > cinema.length - 1 || selectedRow > cinema[0].length - 1) {
            System.out.println("Wrong input!");
            buyTicket(cinema, readTicketRow(), readTicketSeat(), false);
        }
        for (int i = 1; i <= cinema.length - 1; i++) {
            for (int j = 1; j <= cinema[0].length - 1; j++) {
                if (selectedRow == j && selectedSeat == i) {
                    if (cinema[i][j].equals("B")) {
                        System.out.println("That ticket has already been purchased");
                        buyTicket(cinema, readTicketRow(), readTicketSeat(), false);
                    }
                    else {
                        cinema[i][j] = "B";
                    }
                }
            }
        }

        if (printMessage) {
            int totalSeats = (cinema.length - 1) * (cinema[0].length - 1);
            if (totalSeats <= 60) {
                System.out.println("Ticket price: $10");
            } else if ((cinema[0].length - 1) % 2 == 0) {
                if (selectedSeat <= (cinema[0].length - 1) / 2) {
                    System.out.println("Ticket price: $10");
                } else {
                    System.out.println("Ticket price: $8");
                }
            } else {
                if (selectedSeat <= ((cinema[0].length - 1) - 1) / 2) {
                    System.out.println("Ticket price: $10");
                } else {
                    System.out.println("Ticket price: $8");
                }
            }

            System.out.println();
        }
        return cinema;
    }

    public static int readCinemaRows() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of rows: ");
        int rows = sc.nextInt();
        return rows;
    }

    public static int readCinemaSeats() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the number of seats in each row: ");
        int seats = sc.nextInt();
        return seats;
    }

    public static int readTicketRow() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a row number: ");
        int row = sc.nextInt();
        return row;
    }

    public static int readTicketSeat() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a seat number in that row: ");
        int seat = sc.nextInt();
        return seat;
    }

    public static void main(String[] args) {
        int cinemaRows = readCinemaRows();
        int cinemaSeats = readCinemaSeats();
        String[][] cinema = new String[cinemaRows + 1][cinemaSeats + 1];
        for (int i = 1; i <= cinemaRows; i++) {
            for (int j = 1; j <= cinemaSeats; j++) {
                cinema[i][j] = "S";
            }
        }

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");

            int option = sc.nextInt();

            switch (option) {
                case 2:
                    buyTicket(cinema, readTicketRow(), readTicketSeat(), true);
                    break;
                case 1:
                    printCinema(cinema);
                    break;
                case 3:
                    statistics(cinema);
                    break;
                case 0:
                    return;
            }
        }
    }
}