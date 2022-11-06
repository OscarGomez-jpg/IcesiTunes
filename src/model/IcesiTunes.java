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
     * @return A int with position of the user in the array
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
     * This function receives an User by parameter and adds it to the main
     * application
     * 
     * @param user
     * @return
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
     * @param userId The user's id
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

        msg = ((ConsumerUser)(users.get(userPos))).addPlaylist(playlist);

        return msg;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public ArrayList<Audio> getAudios() {
        return audios;
    }
}
