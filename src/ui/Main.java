package ui;

import java.util.InputMismatchException;
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
                "1. Agregar artista \n" +
                "2. Agregar creador de contenido \n" +
                "3. Agregar propietario\n" +
                "4. Agregar apartamento a un propietario\n" +
                "5. Agregar arrendatario\n" +
                "0. Salir del programa.\n";
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1:
                msg = uiAddUserProducer();
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

    /**
     * This function adds a producer user to the system
     * 
     * @return A String validating the operation
     */
    public String uiAddUserProducer() {
        String msg = "";

        try {    
            System.out.println("Ingrese el tipo de productor que quiere agregar:\n" +
                                "1. Artista\n" + 
                                "2. Creador de contenido");
            int type = reader.nextInt();
    
            System.out.println("Ingrese el nickname: ");
            String nickname = reader.next();
    
            System.out.println("Ingrese el identificador del artista: ");
            String id = reader.next();
    
            System.out.println("Ingrese el nombre del artista: ");
            String name = reader.next();
    
            System.out.println("Ingrese la url de la foto de perfil: ");
            String url = reader.next();
    
            msg = controller.addProducerUser(type, nickname, id, name, url);
        } catch (Exception error) {
            msg = "Por favor ingrese un numero";
            reader.next();
        }

        return msg;
    }
}
