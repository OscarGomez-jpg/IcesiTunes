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
                msg = uiAddArtist();
                System.out.println(msg);
                break;

            case 2:
                msg = uiAddContentCreator();
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

    public String uiAddArtist() {
        String msg = "";

        System.out.println("Ingrese el nickname: ");
        String nickname = reader.next();

        System.out.println("Ingrese el identificador del artista: ");
        String id = reader.next();

        System.out.println("Ingrese el nombre del artista: ");
        String name = reader.next();

        System.out.println("Ingrese la url de la foto de perfil: ");
        String url = reader.next();

        msg = controller.addArtist(nickname, id, name, url);

        return msg;
    }

    public String uiAddContentCreator() {
        String msg = "";

        System.out.println("Ingrese el nickname: ");
        String nickname = reader.next();

        System.out.println("Ingrese el identificador del creador de contenido: ");
        String id = reader.next();

        System.out.println("Ingrese el nombre del creador de contenido: ");
        String name = reader.next();

        System.out.println("Ingrese la url de la foto de perfil: ");
        String url = reader.next();

        msg = controller.addContentCreator(nickname, id, name, url);

        return msg;
    }
}
