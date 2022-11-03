package model;

public class IcesiTunesController {
    private IcesiTunes icesiTunes;

    public IcesiTunesController() {
        this.icesiTunes = new IcesiTunes();
    }

    /**
     * This function adds a Producer user that can be of type artist or content
     * creator to the
     * main application
     * 
     * @param type     Producer user's name
     * @param nickname Producer user's
     * @param id       Producer user's
     * @param name     Producer user's
     * @param url      Producer user's
     * @return String validating the operation
     */
    public String addProducerUser(int type, String nickname, String id, String name, String url) {
        String msg = "No se ha podido agregar el productor";

        System.out.println(type);

        switch (type) {

            case 1: {
                ProducerUser newProducer = new Artist(nickname, id, name, url);
                msg = icesiTunes.addProducerUser(newProducer);
                break;
            }

            case 2: {
                ProducerUser newProducer = new ContentCreator(nickname, id, name, url);
                msg = icesiTunes.addProducerUser(newProducer);
                break;
            }

            default: {
                msg = "Ingrese un valor valido";
                break;
            }
        }

        return msg;
    }

}
