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

        switch (type) {

            case 1: {
                User newProducer = new Artist(nickname, id, name, url);
                msg = icesiTunes.addUser(newProducer);
                break;
            }

            case 2: {
                User newProducer = new ContentCreator(nickname, id, name, url);
                msg = icesiTunes.addUser(newProducer);
                break;
            }

            default: {
                msg = "Ingrese un valor valido";
                break;
            }
        }

        return msg;
    }

    /**
     * This function adds a Consumer user to the main application. This function
     * builds the
     * Consumer user by parameters given by the user and adds a standard or premium
     * user depending on the type given given by the user.
     * 
     * @param type     int type that differentiates the standard from the premium
     * @param nickname User's nickname
     * @param id       User's id
     * @return A String validating the operation
     */
    public String addConsumerUser(int type, String nickname, String id) {
        String msg = "No se ha podido agregar el usuario";

        switch (type) {

            case 1: {
                User newConsumer = new PremiumUser(nickname, id);
                msg = icesiTunes.addUser(newConsumer);
                break;
            }

            case 2: {
                User newConsumer = new StandardUser(nickname, id);
                msg = icesiTunes.addUser(newConsumer);
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
