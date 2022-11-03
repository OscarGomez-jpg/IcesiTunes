package model;

public class IcesiTunesController {
    private IcesiTunes icesiTunes;

    public IcesiTunesController() {
        this.icesiTunes = new IcesiTunes();
    }

    /**
     * This function adds an artist to the main application
     * 
     * @param nickname Artist's nickname
     * @param id       Artist's id
     * @param name     Artist's name
     * @param url      Artist's profile photo url
     * 
     * @return String with the result of the operation
     */
    public String addArtist(String nickname, String id, String name, String url) {
        String msg = "No se ha podido agregar el artista";

        ProducerUser newArtist = new Artist(nickname, id, name, url);

        msg = icesiTunes.addProducerUser(newArtist);

        return msg;
    }

    /**
     * This function adds a content creator to the main application
     * 
     * @param nickname Content creator's nickname
     * @param id       Content creator's id
     * @param name     Content creator's name
     * @param url      Content creator's profile photo url
     * @return String with the result of the operation
     */
    public String addContentCreator(String nickname, String id, String name, String url) {
        String msg = "No se ha podido agregar el creador de contenido";

        ProducerUser newContentCreator = new ContentCreator(nickname, id, name, url);

        msg = icesiTunes.addProducerUser(newContentCreator);

        return msg;
    }
}
