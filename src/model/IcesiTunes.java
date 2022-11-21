package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

public class IcesiTunes {

    private ArrayList<User> users;
    private ArrayList<Audio> audios;
    private HashMap<String, Integer> acuGenre;
    private HashMap<String, Integer> acuCategory;
    private HashMap<String, Integer> boughtSongs;
    private Random random;

    public IcesiTunes() {
        this.users = new ArrayList<User>();
        this.audios = new ArrayList<Audio>();
        this.acuGenre = new HashMap<String, Integer>();
        this.acuCategory = new HashMap<String, Integer>();
        this.boughtSongs = new HashMap<String, Integer>();
        this.random = new Random();
    }

    /**
     * This function returns the position of a user in the app
     * 
     * @param userId The user's id
     * @return An int with position of the user in the array
     */
    public int searchUserById(String userId) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < users.size() && isFound == false; i++) {
            if (users.get(i).getId().equals(userId)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    /**
     * This function returns the position of a user in the app by his name
     * 
     * @param userName The user's name
     * @return An int with the position of the user in the array
     */
    public int searchUserByName(String userName) {
        int pos = -1;

        boolean isFound = false;

        for (int i = 0; i < users.size() && isFound == false; i++) {
            if (users.get(i).getNickname().equals(userName)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    /**
     * This function search an AudioFile by its name
     * 
     * @param audioName The audio's name
     * @return An int with the audio's position
     */
    public int searchAudioByName(String audioName) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < audios.size() && isFound == false; i++) {
            if (audios.get(i).getName().equals(audioName)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    /**
     * This function returns the position of a playlist from a user
     * 
     * @param userId       Consumer user's id
     * @param playlistName Playlist's name
     * @return An int with the position of the playlist
     */
    public int searchUserPlaylist(String userId, String playlistName) {
        int pos = -1;
        boolean isFound = false;
        int userPos = searchUserById(userId);
        ArrayList<Playlist> uPlaylists = ((ConsumerUser) (users.get(userPos))).getPlaylists();

        for (int i = 0; i < uPlaylists.size() && isFound == false; i++) {
            if (uPlaylists.get(i).getName().equals(playlistName)) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    /**
     * This function receives an User by parameter and adds it to the main
     * application
     * 
     * @param user The user to be added
     * @return A String with the result of the operation
     */
    public String addUser(User user) {
        String msg = "Usuario agregado con exito";

        if (searchUserById(user.getId()) != -1) {
            msg = "El Usuario ya existe dentro del sistema";

            return msg;
        }

        boolean result = users.add(user);

        if (result == false) {
            msg = "No se ha podido agregar el Usuario";

            return msg;
        }

        return msg;
    }

    /**
     * This function adds an audio file to the main application
     * 
     * @param audio The Audio to be added
     * @return A String with the result of the operation
     */
    public String addAudioFile(Audio audio) {
        String msg = "Archivo agregado con exito";

        if (searchAudioByName(audio.getName()) != -1) {
            msg = "La cancion ya existe dentro del sistema";

            return msg;
        }

        boolean result = audios.add(audio);

        if (result == false) {
            msg = "No se ha podido agregar la cancion";

            return msg;
        }

        return msg;
    }

    /**
     * This function adds a playlist of type Playlist to an existing user
     * 
     * @param userId   The user's id
     * @param playlist The playlist to be added
     * @return A String with the result of the operation
     */
    public String addPlaylistToUser(String userId, Playlist playlist) {
        String msg = "No se ha podido agregar la playlist";

        int userPos = searchUserById(userId);

        if (userPos == -1) {
            msg = "No se ha encontrado al usuario";
            return msg;
        }

        msg = ((ConsumerUser) (users.get(userPos))).addPlaylist(playlist);

        return msg;
    }

    /**
     * This function takes an existing audio file and puts it into a playlist
     * 
     * @param userPos     The position of the user that has the playlist
     * @param playlistPos The playlist position in the user
     * @param audioPos    Position of the audio that the user wants to add to the
     *                    playlist
     * @return A String with the result of the operation
     */
    public String addAudioToPlaylist(int userPos, int playlistPos, int audioPos) {
        String msg = "No se ha podido agregar el audio a la playlist";

        Audio reqAudio = audios.get(audioPos);

        boolean hasBoughtSong = ((ConsumerUser) (users.get(userPos))).getUserAudios().getSongs().contains(reqAudio);

        boolean isSong = audios.get(audioPos) instanceof Song;

        if (!hasBoughtSong && isSong) {
            return "El usuario no ha comprado esta cancion";
        }

        msg = ((ConsumerUser) (users.get(userPos))).getPlaylists().get(playlistPos).addAudio(audios.get(audioPos));

        return msg;
    }

    /**
     * This function removes an existing audio from a playlist
     * 
     * @param userPos     The position of the user
     * @param playlistPos The position of the playlist
     * @param audioName   The name of the audio to be removed
     * @return A String with the result of the operation
     */
    public String removeAudioFromPlaylist(int userPos, int playlistPos, String audioName) {
        String msg = "No se ha podido eliminar la cancion de la lista";

        msg = ((ConsumerUser) (users.get(userPos))).getPlaylists().get(playlistPos).deleteAudio(audioName);

        return msg;
    }

    /**
     * This function changes the name of an existing playlist
     * 
     * @param userPos     The owner of the playlist position
     * @param playlistPos The playlist's position
     * @param newName     The new name of the playlist
     * @return A String with the result of the operation
     */
    public String changePlaylistName(int userPos, int playlistPos, String newName) {
        String msg = "Nombre modificado con exito";

        ((ConsumerUser) (users.get(userPos))).getPlaylists().get(playlistPos).setName(newName);

        return msg;
    }

    /**
     * Returns the code of the playlist at the given position.
     * 
     * @param userPos     - the position of the user
     * @param playlistPos - The position of the playlist.
     */
    public String sharePlaylistCode(int userPos, int playlistPos) {
        return ((ConsumerUser) (users.get(userPos))).getPlaylists().get(playlistPos).getCode();
    }

    /**
     * This function Adds a song to the Consumer user playlist that has the same
     * user's name
     * 
     * @param userPos  The user position
     * @param audioPos The song position
     * 
     * @return A String with the result of the operation
     */
    public String buySong(int userPos, int audioPos) {
        String msg = "No se ha podido comprar la cancion";

        Audio songToBuy = audios.get(audioPos);

        if (((ConsumerUser) (users.get(userPos))).getUserAudios().getSongs().contains(songToBuy)) {
            return "El usuario ya ha comprado esta cancion";
        }

        if (users.get(userPos) instanceof StandardUser) {
            msg = ((StandardUser) (users.get(userPos))).addAudio(songToBuy);
        } else {
            msg = ((PremiumUser) (users.get(userPos))).addAudio(songToBuy);
        }

        if(boughtSongs.get(((Song) (songToBuy)).getGenre()) != null) {
            boughtSongs.put(((Song) (songToBuy)).getGenre(), boughtSongs.get(((Song) (songToBuy)).getGenre()) + 1);
        } else {
            boughtSongs.put(((Song) (songToBuy)).getGenre(), 1);
        }

        return msg;
    }

    /**
     * This function simulates the playing of all the songs either for a standard
     * user as for a premium user
     * for a standard user will return a Publicity value as an ad. The premium user
     * wont have to watch any ad
     * 
     * @param userPos The user that will simulate the playing
     * 
     * @return A String with the sequence of the songs and the ads showed according
     *         to the user type
     */
    public String simulatePlayingAudio(int userPos) {
        String msg = "";

        User reqUser = users.get(userPos);

        if (reqUser instanceof StandardUser) {
            msg = simulateStandardPlaying(reqUser);
        } else {
            msg = simulatePremiumPlaying(reqUser);
        }

        return msg;
    }

    public String simulateStandardPlaying(User reqUser) {
        String msg = "";

        StandardUser reqStandardUser = ((StandardUser) (reqUser));
        int totalAudios = reqStandardUser.getUserAudios().getSongs().size();
        int adsInterval = 2;

        // This variable controls the ad intervals
        int acuAdSongs = 0;

        for (int i = 0; i < totalAudios; i++) {
            Audio index = reqStandardUser.getUserAudios().getSongs().get(i);

            // I add 1 to the total reproductions for the authors
            User author = users.get(searchUserByName(index.getAuthor()));

            ((ProducerUser) (author)).setTotalReproductions(((ProducerUser) (author)).getTotalReproductions() + 1);

            if (index instanceof Song) {
                if (acuAdSongs % adsInterval == 0) {
                    msg += "Publicidad: " + Publicity.values()[random.nextInt(3)] + "\n";
                }

                msg += "Nombre de la cancion: " + index.getName() + "\n";

                if (acuGenre.get(((Song) (index)).getGenre()) != null) {
                    acuGenre.put(((Song) (index)).getGenre(), acuGenre.get(((Song) (index)).getGenre()) + 1);
                } else {
                    acuGenre.put(((Song) (index)).getGenre(), 1);
                }

                acuAdSongs++;
            } else {
                msg += "Publicidad :" + Publicity.values()[random.nextInt(3)] + "\n";
                msg += "Nombre del podcast: " + index.getName() + "\n";

                if (acuCategory.get(((Podcast) (index)).getCategory()) != null) {
                    acuCategory.put(((Podcast) (index)).getCategory(), acuCategory.get(((Podcast) (index)).getCategory()) + 1);
                } else {
                    acuCategory.put(((Podcast) (index)).getCategory(), 1);
                }
                        
            }

            audios.get(i).setTotalPlays(audios.get(i).getTotalPlays() + 1);
        }

        return msg;
    }

    public String simulatePremiumPlaying(User reqUser) {
        String msg = "";

        PremiumUser reqStandardUser = ((PremiumUser) (reqUser));
        int totalAudios = reqStandardUser.getUserAudios().getSongs().size();

        for (int i = 0; i < totalAudios; i++) {
            Audio index = reqStandardUser.getUserAudios().getSongs().get(i);

            // I add 1 to the total reproductions of the author
            User author = users.get(searchUserByName(index.getAuthor()));

            ((ProducerUser) (author)).setTotalReproductions(((ProducerUser) (author)).getTotalReproductions() + 1);

            String form = "";

            if (index instanceof Podcast) {
                form = "del podcast";
                if (acuGenre.get(((Song) (index)).getGenre()) != null) {
                    acuGenre.put(((Song) (index)).getGenre(), acuGenre.get(((Song) (index)).getGenre()) + 1);
                } else {
                    acuGenre.put(((Song) (index)).getGenre(), 1);
                }
            } else {
                form = "de la cancion";
                if (acuCategory.get(((Podcast) (index)).getCategory()) != null) {
                    acuCategory.put(((Podcast) (index)).getCategory(), acuCategory.get(((Podcast) (index)).getCategory()) + 1);
                } else {
                    acuCategory.put(((Podcast) (index)).getCategory(), 1);
                }
            }
            audios.get(i).setTotalPlays(audios.get(i).getTotalPlays() + 1);

            msg += "Nombre " + form + ": " + index.getName() + "\n";
        }

        return msg;
    }

    public String topFiveArtist() {
        String msg = "";

        int acu = 1;

        int limit = 5;

        User[] top = new User[limit];
        ArrayList<Integer> deneg = new ArrayList<Integer>();

        for (int i = 0; i < limit; i++) {
            int bigger = 0;
            for (int j = 0; j < users.size(); j++) {
                if (users.get(j) instanceof Artist) {
                    if (bigger < ((Artist) (users.get(j))).getTotalReproductions() && deneg.contains(j) == false) {
                        top[i] = users.get(j);
                        deneg.add(j);
                        bigger = ((Artist) (users.get(j))).getTotalReproductions();
                    }
                }
            }
        }

        for (User index : top) {
            if (index != null) {
                msg += acu + ". Nombre: " + index.getNickname() + " total: " + ((Artist) (index)).getTotalReproductions() + "\n";
                acu++;
            }
        }

        return msg;
    }

    public String topFiveContent() {
        String msg = "";

        int acu = 1;

        int limit = 5;

        User[] top = new User[limit];
        ArrayList<Integer> deneg = new ArrayList<Integer>();

        for (int i = 0; i < limit; i++) {
            int bigger = 0;
            for (int j = 0; j < users.size(); j++) {
                if (users.get(j) instanceof ContentCreator) {
                    if (bigger < ((ContentCreator) (users.get(j))).getTotalReproductions()
                            && deneg.contains(j) == false) {
                        top[i] = users.get(j);
                        deneg.add(j);
                        bigger = ((ContentCreator) (users.get(j))).getTotalReproductions();
                    }
                }
            }
        }

        for (User index : top) {
            if (index != null) {
                msg += acu + ". Nombre: " + index.getNickname() + " total: "
                        + ((ProducerUser) (index)).getTotalReproductions() + "\n";
                acu++;
            }
        }

        return msg;
    }

    public String topTenSongs() {
        String msg = "";

        int acu = 1;

        int limit = 10;

        Audio[] top = new Audio[limit];
        ArrayList<Integer> deneg = new ArrayList<Integer>();

        for (int i = 0; i < limit; i++) {
            int bigger = 0;
            for (int j = 0; j < audios.size(); j++) {
                if (audios.get(j) instanceof Song) {
                    if (bigger < ((Song) (audios.get(j))).getTotalPlays() && deneg.contains(j) == false) {
                        top[i] = audios.get(j);
                        deneg.add(j);
                        bigger = ((Song) (audios.get(j))).getTotalPlays();
                    }
                }
            }
        }

        for (Audio index : top) {
            if (index != null) {
                msg += acu + ". Nombre: " + index.getName() + " total: " + ((Song) (index)).getTotalPlays() + " genero: "
                        + ((Song) index).getGenre() + "\n";
                acu++;
            }
        }

        return msg;
    }

    public String topTenPodcasts() {
        String msg = "";

        int acu = 1;

        int limit = 10;

        Audio[] top = new Audio[limit];
        ArrayList<Integer> deneg = new ArrayList<Integer>();

        for (int i = 0; i < limit; i++) {
            int bigger = 0;
            for (int j = 0; j < audios.size(); j++) {
                if (audios.get(j) instanceof Podcast) {
                    if (bigger < ((Podcast) (audios.get(j))).getTotalPlays() && deneg.contains(j) == false) {
                        top[i] = audios.get(j);
                        deneg.add(j);
                        bigger = ((Podcast) (audios.get(j))).getTotalPlays();
                    }
                }
            }
        }

        for (Audio index : top) {
            if (index != null) {
                msg += acu + ". Nombre: " + index.getName() + " total: " + ((Podcast) (index)).getTotalPlays()
                        + " Categoria: " + ((Podcast) index).getCategory() + "\n";
                acu++;
            }
        }

        return msg;
    }

    public String genresSold() {
        String msg = "";

        for (HashMap.Entry<String, Integer> entry : boughtSongs.entrySet()) {
            msg += "-Genero: " + entry.getKey() + " veces vendido: " + entry.getValue() + "\n";
        }

        return msg;
    }

    public String mostSoldSong() {
        Map<String, Integer> total = new HashMap<String, Integer>();
        String msg = "";
        String bigger = "";
        int biggers = 0;

        for (Audio audio : audios) {
            if (total.get(audio.getName()) != null) {
                total.put(audio.getName(), total.get(audio.getName()) + 1);
            } else {
                total.put(audio.getName(), 1);
            }
        }

        Iterator<String> it = total.keySet().iterator();
        while (it.hasNext()) {
            String key = it.next();

            if (total.get(key) > biggers) {
                biggers = total.get(key);
                bigger = key;
            }
        }

        msg = "Nombre: " + bigger + " veces vendido: " + biggers + " Precio: " + users.get(searchAudioByName(bigger)) + "\n";

        return msg;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public String getUserPlaylists(int userPos) {
        String msg = "";

        for (Playlist index : ((ConsumerUser) (users.get(userPos))).getPlaylists()) {
            msg += "- " + index.getName() + " Codigo: " + index.getCode() + "\n";
        }

        return msg;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }

    public HashMap<String, Integer> getAcuGenre() {
        return acuGenre;
    }

    public HashMap<String, Integer> getAcuCategory() {
        return acuCategory;
    }
}
