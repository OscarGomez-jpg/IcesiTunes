package ui;

import java.util.Scanner;

import model.IcesiTunesController;

public class Main {

    private Scanner reader;
    private IcesiTunesController controller;

    public Main() {
        reader = new Scanner(System.in);
        controller = new IcesiTunesController();
    }

    public static void main(String[] args) {
        Main main = new Main();

        int option = -1;
        do {

            option = main.getOptionShowMenu();
            main.executeOption(option);

        } while (option != 0);

    }

    public Scanner getReader() {
        return this.reader;
    }

    public void setReader(Scanner reader) {
        this.reader = reader;
    }

    public int validateIntegerOption() {
        int option = 0;

        if (reader.hasNextInt()) {
            option = reader.nextInt();
        } else {
            // clear reader.
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    public double validateDoubleOption() {
        double option = 0;

        if (reader.hasNextDouble()) {
            option = reader.nextDouble();
        } else {
            reader.nextLine();
            option = -1;
        }

        return option;
    }

    public int getOptionShowMenu() {
        int option = 0;
        System.out.println(printMenu());

        option = validateIntegerOption();

        return option;
    }

    public String printMenu() {
        return "\n" +
                "<< --------------------------------------------------------------------- >>\n" +
                "<< -                                Welcome                            - >>\n" +
                "<< --------------------------------------------------------------------- >>\n" +
                "1. Registrar edificio \n" +
                "2. Agregar un apartamento a un edificio \n" +
                "3. Agregar propietario\n" +
                "4. Agregar apartamento a un propietario\n" +
                "5. Agregar arrendatario\n" +
                "0. Salir del programa.\n";
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1:
                System.out.println(msg);
                break;

            case 2:
                System.out.println(msg);
                break;

            case 3:
                System.out.println(msg);
                break;

            case 4:
                System.out.println(msg);
                break;

            case 5:
                System.out.println(msg);
                break;

            case 0:
                System.out.println("Exit program.");
                break;

            default:
                System.out.println("Invalid Option");
                break;
        }
    }
}
