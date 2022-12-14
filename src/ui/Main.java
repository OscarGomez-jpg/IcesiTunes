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
                "6. Editar Playlist\n" +
                "7. Compartir Playlist\n" +
                "8. Comprar cancion\n" +
                "9. Simular reproduccion de Audio\n" +
                "10.Reportar informacion\n" +
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
                msg = uiEditPlaylist();
                System.out.println(msg);
                break;

            case 7:
                msg = uiShareCodePlaylist();
                System.out.println(msg);
                break;

            case 8:
                msg = uiBuySong();
                System.out.println(msg);
                break;

            case 9:
                msg = uiSimulatePlayingSong();
                System.out.println(msg);
                break;

            case 10:
                msg = uiReportData();
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

            System.out.println("Ingrese el nickname del artista: ");
            System.out.println("Artistas disponibles: ");
            String producers = controller.getArtists();

            System.out.println(producers);

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

            System.out.println("Ingrese el nickname del creador de contenido: ");
            String producers = controller.getContentCreators();

            System.out.println(producers);

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
     * This function is a sub menu to let the user change the playlist of a consumer
     * user
     * 
     * @return A String with the result of the operation
     */
    public String uiEditPlaylist() {
        String msg = "";

        try {
            System.out.println("Seleccione una opcion digitando un numero: \n" +
                    "1. Agregar archivo de audio a playlist\n" +
                    "2. Remover archivo de audio a playlist\n" +
                    "3. Cambiar el nombre de la playlist");

            int selection = reader.nextInt();

            switch (selection) {
                case 1:
                    msg = uiAddAudioToPlaylist();
                    break;
                case 2:
                    msg = uiRemoveAudioFromPlaylist();
                    break;
                case 3:
                    msg = uiChangePlaylistName();
                    break;
            }

        } catch (Exception error) {
            msg = "Ingrese un valor permitido";
        }

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
        System.out.println(controller.getUserPlaylists(userId));
        String playlistName = reader.next();

        System.out.println("Ingrese el nombre del audio: ");
        System.out.println(controller.getSongs());
        System.out.println(controller.getPodcasts());
        String audioName = reader.next();

        msg = controller.addSongToPlaylist(userId, playlistName, audioName);

        return msg;
    }

    /**
     * This function takes the parameters to delete an audio from a playlist
     * 
     * @return A String with the result of the operation
     */
    public String uiRemoveAudioFromPlaylist() {
        String msg = "";

        System.out.println("Ingrese el id del usuario: ");
        String userId = reader.next();

        System.out.println("Ingrese el nombre de la playlist: ");
        System.out.println(controller.getUserPlaylists(userId));
        String playlistName = reader.next();

        System.out.println("Ingrese el nombre del audio: ");
        System.out.println(controller.getSongs());
        String audioName = reader.next();

        msg = controller.removeAudioFromPlaylist(userId, playlistName, audioName);

        return msg;
    }

    /**
     * This function takes the parameter to change the name of an existing playlist
     * 
     * @return A String with the result of the operation
     */
    public String uiChangePlaylistName() {
        String msg = "";

        System.out.println("Ingrese el id del usuario: ");
        String userId = reader.next();

        System.out.println("Ingrese el antiguo nombre de la playlist: ");
        System.out.println(controller.getUserPlaylists(userId));
        String oldPlaylistName = reader.next();

        System.out.println("Ingrese el nuevo nombre de la playlist: ");
        String newPlaylistName = reader.next();

        msg = controller.changePlaylistName(userId, oldPlaylistName, newPlaylistName);

        return msg;
    }

    /**
     * This function provides the user the option to know which list he wants to
     * know its code
     * 
     * @return A String with the code of the playlist
     */
    public String uiShareCodePlaylist() {
        String msg = "";

        System.out.println("Ingrese el id del usuario: ");
        String userId = reader.next();

        System.out.println("Ingrese el nombre de la playlist: ");
        String playlistName = reader.next();

        msg = controller.sharePlaylistCode(userId, playlistName);

        return msg;
    }

    /**
     * This function provides the user the optio to buy a song for a consumer user
     * 
     * @return A String with the result of the operation
     */
    public String uiBuySong() {
        String msg = "";

        System.out.println("Ingrese el nombre de la cancion que desea comprar: ");
        System.out.println(controller.getSongs());

        String songName = reader.next();

        System.out.println("Ingrese el id del usuario: ");

        String userName = reader.next();

        msg = controller.buySong(userName, songName);

        return msg;
    }

    /**
     * This function lets the user select the consumer user that will emulate the
     * playing of a songF
     * 
     * @return A String with the result of the operation
     */
    public String uiSimulatePlayingSong() {
        String msg = "";

        System.out.println("Ingrese el nickname del usuario: ");
        System.out.println(controller.getUsers());

        String userId = reader.next();

        msg = controller.simulatePlayingAudio(userId);

        return msg;
    }

    public String uiReportData() {
        String msg = "";

        try {
            System.out.println("Seleccione una opcion digitando un numero: \n" +
                    "1. Total de reproducciones de un audio\n" +
                    "2. Genero mas escuchado\n" +
                    "3. Top 5 Artistas\n" +
                    "4. Top 5 Creadores de contenido\n" +
                    "5. Top 10 Canciones\n" +
                    "6. Top 10 Podcasts\n" +
                    "7. Generos mas vendidos\n" +
                    "8. Cancion mas vendida\n");

            int selection = reader.nextInt();

            switch (selection) {
                case 1:
                    msg = uiTotalPlaysAudio();
                    break;
                case 2:
                    msg = uiMostListenedGenre();
                    break;
                case 3:
                    msg = uiTopFiveArtist();
                    break;
                case 4:
                    msg = uiTopFiveContent();
                    break;
                case 5:
                    msg = uiTopTenSongs();
                    break;

                case 6:
                    msg = uiTopTenPodcasts();
                    break;

                case 7:
                    msg = uiSoldGenres();
                    break;

                case 8:
                    msg = uiMostSoldSong();
                    break;
            }

        } catch (Exception error) {
            msg = "Ingrese un valor permitido";
        }

        return msg;
    }

    public String uiTotalPlaysAudio() {
        String msg = "";

        System.out.println("Ingrese el nombre del audio del que desea saber sus reproducciones: ");
        String audioName = reader.next();

        msg = controller.getTotalPlaysAudio(audioName);

        return msg;
    }

    public String uiMostListenedGenre() {
        String msg = "";

        msg = controller.getMostListenedGenre();

        return msg;
    }

    public String uiTopFiveArtist() {
        return controller.getTopFiveArtist();
    }

    public String uiTopFiveContent() {
        return controller.getTopFiveContent();
    }

    public String uiTopTenSongs() {
        return controller.getTopTenSongs();
    }

    public String uiTopTenPodcasts() {
        return controller.getTopTenPodcasts();
    }

    public String uiSoldGenres() {
        return controller.getMostListenedGenre();
    }

    public String uiMostSoldSong() {
        return controller.getMostSoldSong();
    }
}
