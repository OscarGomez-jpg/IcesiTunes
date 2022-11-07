package model;

import java.util.ArrayList;

public abstract class ConsumerUser extends User {

    private ArrayList<Playlist> playlists;
    private Playlist userAudios;

    public ConsumerUser(String nickname, String id) {
        super(nickname, id);
        this.playlists = new ArrayList<Playlist>();
        this.userAudios = new Playlist(nickname);
    }
    
    /**
     * This function will add an audio that can be either a song or a podcast to 
     * a consumer user
     * 
     * @param audio The audio to be added
     * @return A String with the result of the operation
     */
    public abstract String addAudio(Audio audio);

    /**
     * This function will add a Playlist to a consumer user
     * 
     * @param playlist The playlist to be added
     * @return A String with the result of the operation
     */
    public abstract String addPlaylist(Playlist playlist);

    public ArrayList<Playlist> getPlaylists() {
        return playlists;
    }

    public Playlist getUserAudios() {
        return userAudios;
    }
}
