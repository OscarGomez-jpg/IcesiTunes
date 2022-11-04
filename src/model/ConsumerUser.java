package model;

import java.util.ArrayList;

public abstract class ConsumerUser extends User {

    private ArrayList<String> idSongs;

    public ConsumerUser(String nickname, String id) {
        super(nickname, id);
        idSongs = new ArrayList<String>();
    }
    
    public abstract String addSong(String idSong);

    public ArrayList<String> getIdSongs() {
        return idSongs;
    }
}
