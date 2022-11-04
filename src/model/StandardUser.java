package model;

public class StandardUser extends ConsumerUser {

    public static final int TOTAL_SONGS = 100; 

    public StandardUser(String nickname, String id) {
        super(nickname, id);
    }

    @Override
    public String addSong(String idSong) {
        String msg = "El usuario ha alcanzado el limite de canciones";

        if (getIdSongs().size() <= TOTAL_SONGS) {
            getIdSongs().add(idSong);
            msg = "Cancion agregada con exito";
        }
        
        return msg;
    }
    
}
