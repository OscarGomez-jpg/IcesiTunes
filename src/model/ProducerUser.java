package model;

public abstract class ProducerUser extends User {
    private String name;
    private String url;
    private int total;

    
    //Tiempo de reproducción
    //Total de reproducciones
    
    public ProducerUser(String nickname, String id, String name, String url) {
        super(nickname, id);
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}