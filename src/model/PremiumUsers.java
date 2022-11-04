package model;


public class PremiumUsers extends ConsumerUser {

    public PremiumUsers(String nickname, String id) {
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
