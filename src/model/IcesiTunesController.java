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

    /**
     * This function adds an audio file of type Song to the main application
     * 
     * @param name      Song's name
     * @param authorsId Song's id
     * @param url       Song's url album cover
     * @param duration  Song's duration as a String formatted as hh:mm:ss
     * @param price     Song's price
     * @param genre     Song's genre
     * 
     * @return A String with the result of the operation
     */
    public String addAudioFile(String name, String authorsId, String url, String duration, double price, int genre) {
        String msg = "No se ha podido agregar el archivo";

        int authorsPos = icesiTunes.searchUserById(authorsId);

        if (authorsPos == -1) {
            msg = "No se ha podido encontrar el autor";
            return msg;
        }

        String author = ((Artist) (icesiTunes.getUsers().get(authorsPos))).getName();

        Audio newAudio = new Song(name, author, url, duration, price, genre);

        msg = icesiTunes.addAudioFile(newAudio);

        return msg;
    }

    /**
     * This function adds an audio of instance Podcast to the main application
     * 
     * @param name      The podcast's name
     * @param url       The podcast image url
     * @param authorsId The podcast author's id
     * @param duration  The podcast duration as a String
     * @param category  The podcast category
     * @return A String with the result of the operation
     */
    public String addAudioFile(String name, String url, String authorsId, String duration, int category) {
        String msg = "No se ha podido agregar el archivo";

        int authorsPos = icesiTunes.searchUserById(authorsId);

        if (authorsPos == -1) {
            msg = "No se ha podido encontrar el autor";
            return msg;
        }

        String author = ((ContentCreator)(icesiTunes.getUsers().get(authorsPos))).getName();

        Audio newAudio = new Podcast(name, url, author, duration, category);

        msg = icesiTunes.addAudioFile(newAudio);

        return msg;
    }
}
