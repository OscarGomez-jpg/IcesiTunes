package model;


public class PremiumUser extends ConsumerUser {

    public PremiumUser(String nickname, String id) {
        super(nickname, id);
        //TODO Auto-generated constructor stub
    }

    @Override
    public String addSong(String idSong) {
        String msg = "Cancion agregada con exito";

        getIdSongs().add(idSong);

        return msg;
    }
    
}
