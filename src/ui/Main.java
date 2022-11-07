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

    public String formatDuration(int hours, int minutes, int seconds) {
        String msg = "";
        msg = "PT" + hours + "H" + minutes + "M" + seconds + "S";
        return msg;
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
                "1. Agregar Productor \n" +
                "2. Agregar Consumidor \n" +
                "3. Agregar cancion\n" +
                "4. Agregar Podcast\n" +
                "5. Agregar Playlist\n" +
                "6. Agregar cancion a Playlist\n" +
                "7. Remover cancion de Playlist\n" +
                "8. Cambiar nombre de Playlist\n" +
                "0. Salir del programa.\n";
    }

    public void executeOption(int option) {
        String msg = "";

        switch (option) {
            case 1:
                msg = uiAddProducerUser();
                System.out.println(msg);
                break;

            case 2:
                msg = uiAddConsumerUser();
                System.out.println(msg);
                break;

            case 3:
                msg = uiAddSongFile();
                System.out.println(msg);
                break;

            case 4:
                msg = uiAddPodcastFile();
                System.out.println(msg);
                break;

            case 5:
                msg = uiAddPlaylist();
                System.out.println(msg);
                break;

            case 6:
                msg = uiAddAudioToPlaylist();
                System.out.println(msg);
                break;
            
            case 7:
                msg = uiRemoveAudioFromSong();
                System.out.println(msg);
                break;

            case 8:
                msg = uiChangePlaylistName();
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
    public String uiAddProducerUser() {
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

    /**
     * This function adds a Consumer user to the system
     * 
     * @return A message validating the operation
     */
    public String uiAddConsumerUser() {
        String msg = "";

        try {
            System.out.println("Ingrese el tipo de usuario que quiere agregar:\n" +
                    "1. Standard\n" +
                    "2. Premium");
            int type = reader.nextInt();

            System.out.println("Ingrese el nickname: ");
            String nickname = reader.next();

            System.out.println("Ingrese el identificador del usuario: ");
            String id = reader.next();

            msg = controller.addConsumerUser(type, nickname, id);
        } catch (Exception error) {
            msg = "Por favor ingrese un numero";
            reader.next();
        }

        return msg;
    }

    /**
     * This function adds a song to the system
     * 
     * @return a String with the result of the operation
     */
    public String uiAddSongFile() {
        String msg = "";

        try {

            System.out.println("Ingrese el nombre de la cancion: ");
            String name = reader.next();

            System.out.println("Ingrese el link de la portada: ");
            String url = reader.next();

            System.out.println("Ingrese el id del autor: ");
            String authorsId = reader.next();

            System.out.println("Ingrese las horas de la cancion: ");
            int hours = reader.nextInt();

            System.out.println("Ingrese los minutos de la cancion: ");
            int minutes = reader.nextInt();

            System.out.println("Ingrese los segundos de la cancion: ");
            int seconds = reader.nextInt();

            String duration = formatDuration(hours, minutes, seconds);

            System.out.println("Ingrese el precio de la cancion: ");
            double price = reader.nextDouble();

            System.out.println("Ingrese el genero de la cancion: \n" +
                    "1. Rock\n" +
                    "2. Pop\n" +
                    "3. Trap\n" +
                    "4. House");

            int type = reader.nextInt();

            msg = controller.addAudioFile(name, authorsId, url, duration, price, type);

        } catch (Exception error) {
            msg = "Ingrese un valor valido";
            reader.next();
        }

        return msg;
    }

    /**
     * This function adds a podcast to the system
     * 
     * @return A String with the resul of the operation
     */
    public String uiAddPodcastFile() {
        String msg = "";

        try {

            System.out.println("Ingrese el nombre del podcast: ");
            String name = reader.next();

            System.out.println("Ingrese el link de la portada: ");
            String url = reader.next();

            System.out.println("Ingrese el id del autor: ");
            String authorsId = reader.next();

            System.out.println("Ingrese las horas del podcast: ");
            int hours = reader.nextInt();

            System.out.println("Ingrese los minutos del podcast: ");
            int minutes = reader.nextInt();

            System.out.println("Ingrese los segundos del podcast: ");
            int seconds = reader.nextInt();

            String duration = formatDuration(hours, minutes, seconds);

            System.out.println("Ingrese la categoria del podcast: \n" +
                    "1. Politica\n" +
                    "2. Entretenimiento\n" +
                    "3. Videojuegos\n" +
                    "4. Moda");

            int type = reader.nextInt();

            msg = controller.addAudioFile(name, url, authorsId, duration, type);

        } catch (Exception error) {
            msg = "Ingrese un valor valido";
            reader.next();
        }

        return msg;
    }

    /**
     * This function rececives the paramaters to add a playlist to the system
     * 
     * @return A String with the result of the operation
     */
    public String uiAddPlaylist() {
        String msg = "";

        System.out.println("Ingrese el id del usuario al que va a agregar la lista: ");
        String userId = reader.next();

        System.out.println("Ingrese el nombre que va a llevar la lista: ");
        String playlistName = reader.next();

        msg = controller.addPlaylistToUser(userId, playlistName);

        return msg;
    }

    /**
     * This function receives the parameters to add a song to a playlist
     * 
     * @return A String with the result of the operation
     */
    public String uiAddAudioToPlaylist() {
        String msg = "";

        System.out.println("Ingrese el id del usuario: ");
        String userId = reader.next();

        System.out.println("Ingrese el nombre de la playlist: ");
        String playlistName = reader.next();

        System.out.println("Ingrese el nombre del audio: ");
        String audioName = reader.next();

        msg = controller.addSongToPlaylist(userId, playlistName, audioName);

        return msg;
    }

    /**
     * This function takes the parameters to delete an audio from a playlist
     * 
     * @return A String with the result of the operation
     */
    public String uiRemoveAudioFromSong() {
        String msg = "";

        System.out.println("Ingrese el id del usuario: ");
        String userId = reader.next();

        System.out.println("Ingrese el nombre de la playlist: ");
        String playlistName = reader.next();

        System.out.println("Ingrese el nombre del audio: ");
        String audioName = reader.next();

        msg = controller.removeAudioFromPlaylist(userId, playlistName, audioName);

        return msg;
    }

    /**
     * This function takes the parameter to change the name of an existing playlist
     * 
     * @return A String with the result of the operation
     */
    private String uiChangePlaylistName() {
        String msg = "";

        System.out.println("Ingrese el id del usuario: ");
        String userId = reader.next();

        System.out.println("Ingrese el antiguo nombre de la playlist: ");
        String oldPlaylistName = reader.next();

        System.out.println("Ingrese el nuevo nombre de la playlist: ");
        String newPlaylistName = reader.next();

        msg = controller.changePlaylistName(userId, oldPlaylistName, newPlaylistName);

        return msg;
    }
}
