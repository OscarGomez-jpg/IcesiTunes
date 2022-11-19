package model;

import java.util.ArrayList;

public class IcesiTunes {

    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    public IcesiTunes() {
        this.users = new ArrayList<User>();
        this.audios = new ArrayList<Audio>();
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
     * @param audioPos The audio position
     */
    public String buySong(int userPos, int audioPos) {
        String msg = "No se ha podido comprar la cancion";

        Audio songToBuy = audios.get(audioPos);

        if (users.get(userPos) instanceof StandardUser) {
            msg = ((StandardUser) (users.get(userPos))).addAudio(songToBuy);
        } else {
            msg = ((PremiumUser) (users.get(userPos))).addAudio(songToBuy);
        }

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
}
