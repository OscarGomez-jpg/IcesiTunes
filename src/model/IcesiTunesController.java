package model;

public class IcesiTunesController {
    private IcesiTunes icesiTunes;

    public IcesiTunesController() {
        this.icesiTunes = new IcesiTunes();
    }

    /**
     * This function adds a Producer user that can be of type artist or content
     * creator to the
     * main application
     * 
     * @param type     Producer user's name
     * @param nickname Producer user's
     * @param id       Producer user's
     * @param name     Producer user's
     * @param url      Producer user's
     * @return String validating the operation
     */
    public String addProducerUser(int type, String nickname, String id, String name, String url) {
        String msg = "No se ha podido agregar el productor";

        switch (type) {

            case 1: {
                User newProducer = new Artist(nickname, id, name, url);
                msg = icesiTunes.addUser(newProducer);
                break;
            }

            case 2: {
                User newProducer = new ContentCreator(nickname, id, name, url);
                msg = icesiTunes.addUser(newProducer);
                break;
            }

            default: {
                msg = "Ingrese un valor valido";
                break;
            }
        }

        return msg;
    }

    /**
     * This function adds a Consumer user to the main application. This function
     * builds the
     * Consumer user by parameters given by the user and adds a standard or premium
     * user depending on the type given given by the user.
     * 
     * @param type     int type that differentiates the standard from the premium
     * @param nickname User's nickname
     * @param id       User's id
     * @return A String validating the operation
     */
    public String addConsumerUser(int type, String nickname, String id) {
        String msg = "No se ha podido agregar el usuario";

        switch (type) {

            case 1: {
                User newConsumer = new PremiumUser(nickname, id);
                msg = icesiTunes.addUser(newConsumer);
                break;
            }

            case 2: {
                User newConsumer = new StandardUser(nickname, id);
                msg = icesiTunes.addUser(newConsumer);
                break;
            }

            default: {
                msg = "Ingrese un valor valido";
                break;
            }
        }

        return msg;
    }

    /**
     * This function adds an audio file of type Song to the main application
     * 
     * @param name      Song's name
     * @param authorsId Song's id
     * @param url       Song's url album cover
     * @param duration  Song's duration as a String formatted as hh:mm:ss
     * @param price     Song's price
     * @param genre     Song's genre
     * 
     * @return A String with the result of the operation
     */
    public String addAudioFile(String name, String authorsId, String url, String duration, double price, int genre) {
        String msg = "No se ha podido agregar el archivo";

        int authorsPos = icesiTunes.searchUserByName(authorsId);

        if (authorsPos == -1) {
            msg = "No se ha podido encontrar el autor";
            return msg;
        }

        if (icesiTunes.getUsers().get(authorsPos) instanceof Artist == false) {
            msg = "No se le puede agregar una cancion a un autor que no es un artista";
            return msg;
        }

        String author = ((Artist) (icesiTunes.getUsers().get(authorsPos))).getName();

        Audio newAudio = new Song(name, author, url, duration, price, genre);

        msg = icesiTunes.addAudioFile(newAudio);

        return msg;
    }

    /**
     * This function adds an audio of instance Podcast to the main application
     * 
     * @param name      The podcast's name
     * @param url       The podcast image url
     * @param authorsId The podcast author's id
     * @param duration  The podcast duration as a String
     * @param category  The podcast category
     * @return A String with the result of the operation
     */
    public String addAudioFile(String name, String url, String authorsId, String duration, int category) {
        String msg = "No se ha podido agregar el archivo";

        int authorsPos = icesiTunes.searchUserByName(authorsId);

        if (authorsPos == -1) {
            msg = "No se ha podido encontrar el autor";
            return msg;
        }

        if (icesiTunes.getUsers().get(authorsPos) instanceof ContentCreator == false) {
            msg = "No se le puede agregar un podcast a un autor que no es un creador de contenido";
            return msg;
        }

        String author = ((ContentCreator) (icesiTunes.getUsers().get(authorsPos))).getName();

        Audio newAudio = new Podcast(name, url, author, duration, category);

        msg = icesiTunes.addAudioFile(newAudio);

        return msg;
    }

    /**
     * This function build a playlist and send it to the main application
     * 
     * @param userId       The user who is going to receive the playlist
     * @param playlistName The new playlist name
     * @return A String with the result of the operation
     */
    public String addPlaylistToUser(String userId, String playlistName) {
        String msg = "No se ha podido agregar la playlist";

        Playlist newPlaylist = new Playlist(playlistName);

        msg = icesiTunes.addPlaylistToUser(userId, newPlaylist);

        return msg;
    }

    /**
     * This function validates and returns the position of the user, playlist and
     * audio
     * to add an audio to a playlist
     * 
     * @param userId       String user's id
     * @param playlistName String playlist's name
     * @param audioName    String song's name
     * @return A String with the result of the operation
     */
    public String addSongToPlaylist(String userId, String playlistName, String audioName) {
        String msg = "No se ha podido agregar la cancion";

        int userPos = icesiTunes.searchUserById(userId);

        if (userPos == -1) {
            msg = "No se ha encontrado el usuario";
            return msg;
        }

        if (icesiTunes.getUsers().get(userPos) instanceof ConsumerUser == false) {
            msg = "Solo los usuarios consumidores tienen playlists";
            return msg;
        }

        int playlistPos = icesiTunes.searchUserPlaylist(userId, playlistName);

        if (playlistPos == -1) {
            msg = "No se ha encontrado la playlist";
            return msg;
        }

        int songPos = icesiTunes.searchAudioByName(audioName);

        if (songPos == -1) {
            msg = "No se ha encontrado el archivo de audio";
            return msg;
        }

        msg = icesiTunes.addAudioToPlaylist(userPos, playlistPos, songPos);

        return msg;
    }

    /**
     * This function verifies and takes the String of a consumer user, a playlist
     * and an audio
     * to return its position
     * 
     * @param userId       The user's id
     * @param playlistName The user's playlist name
     * @param audioName    The user's audio to be removed
     * @return A String with the result of the operation
     */
    public String removeAudioFromPlaylist(String userId, String playlistName, String audioName) {
        String msg = "No se ha podido remover el audio de la playlist";

        int userPos = icesiTunes.searchUserById(userId);

        if (userPos == -1) {
            msg = "No se ha encontrado el usuario";
            return msg;
        }

        if (icesiTunes.getUsers().get(userPos) instanceof ConsumerUser == false) {
            msg = "Solo los usuarios consumidores tienen playlists";
            return msg;
        }

        int playlistPos = icesiTunes.searchUserPlaylist(userId, playlistName);

        if (playlistPos == -1) {
            msg = "No se ha encontrado la playlist";
            return msg;
        }

        msg = icesiTunes.removeAudioFromPlaylist(userPos, playlistPos, audioName);

        return msg;
    }

    /**
     * This function verifies and returns the position of a consumer user with its playlist
     * 
     * @param userId The user's id
     * @param playlistName The playlist's name
     * @param newName The new name of the playlist
     * @return A String with the result of the operation
     */
    public String changePlaylistName(String userId, String playlistName, String newName) {
        String msg = "No se ha podido modificar el nombre de la playlist";

        int userPos = icesiTunes.searchUserById(userId);

        if (userPos == -1) {
            msg = "No se ha encontrado el usuario";
            return msg;
        }

        if (icesiTunes.getUsers().get(userPos) instanceof ConsumerUser == false) {
            msg = "Solo los usuarios consumidores tienen playlists";
            return msg;
        }

        int playlistPos = icesiTunes.searchUserPlaylist(userId, playlistName);

        if (playlistPos == -1) {
            msg = "No se ha encontrado la playlist";
            return msg;
        }

        msg = icesiTunes.changePlaylistName(userPos, playlistPos, newName);

        return msg;
    }

    /**
     * This function connects the share playlist code with the ui
     * 
     * @param userId The user's id that needs the code
     * @param playlistName The playlist that correspond's to the user's id
     * @return A String with the result of the operation
     */
    public String sharePlaylistCode(String userId, String playlistName) {
        String msg = "No se ha podido obtener el codigo de la playlist";

        int userPos = icesiTunes.searchUserById(userId);

        if (userPos == -1) {
            msg = "No se ha encontrado el usuario";
            return msg;
        }

        if (icesiTunes.getUsers().get(userPos) instanceof ConsumerUser == false) {
            msg = "Solo los usuarios consumidores tienen playlists";
            return msg;
        }

        int playlistPos = icesiTunes.searchUserPlaylist(userId, playlistName);

        if (playlistPos == -1) {
            msg = "No se ha encontrado la playlist";
            return msg;
        }

        msg = icesiTunes.sharePlaylistCode(userPos, playlistPos);

        return msg;
    }

    public String getArtists() {
        String msg = "";

        for (User index : icesiTunes.getUsers()) {
            if (index instanceof Artist) {
                msg += "- " + index.getNickname() + "\n";
            }
        }

        return msg;
    }

}
