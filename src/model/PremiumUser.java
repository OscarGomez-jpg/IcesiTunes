package model;


public class PremiumUser extends ConsumerUser {

    public PremiumUser(String nickname, String id) {
        super(nickname, id);
    }

    @Override
    public String addAudio(Audio audio) {
        String msg = "Cancion agregada con exito";

        getUserAudios().addAudio(audio);

        return msg;
    }

    @Override
    public String addPlaylist(Playlist playlist) {
        String msg = "Playlsit agregada con exito";

        getPlaylists().add(playlist);

        return msg;
    }
    
}
