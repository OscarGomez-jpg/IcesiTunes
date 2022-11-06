package model;

public class StandardUser extends ConsumerUser {

    public static final int TOTAL_AUDIOS = 100; 
    public static final int TOTAL_PLAYLIST = 20;

    public StandardUser(String nickname, String id) {
        super(nickname, id);
    }

    @Override
    public String addAudio(Audio audio) {
        String msg = "El usuario ha alcanzado el limite de Audios";

        if (getUserAudios().getSongs().size() <= TOTAL_AUDIOS) {
            getUserAudios().addAudio(audio);

            msg = "Audio agregado exitosamente";
        }
        
        return msg;
    }

    @Override
    public String addPlaylist(Playlist playlist) {
        String msg = "El usuario ha alcanzado el limite de playlist";

        if (getPlaylists().size() <= TOTAL_PLAYLIST) {
            getPlaylists().add(playlist);
            msg = "Playlist creada con exito";
        }
        return msg;
    }
    
}
