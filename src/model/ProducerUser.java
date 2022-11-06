package model;

import java.time.Duration;

public abstract class ProducerUser extends User {
    private String name;
    private String url;
    private int totalReproductions;
    private Duration totalTimeReproduced;

    public ProducerUser(String nickname, String id, String name, String url) {
        super(nickname, id);
        this.name = name;
        this.url = url;
        this.totalReproductions = 0;
        this.totalTimeReproduced = Duration.ofSeconds(00);
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

    public int getTotalReproductions() {
        return totalReproductions;
    }

    public void setTotalReproductions(int totalReproductions) {
        this.totalReproductions = totalReproductions;
    }

    public Duration getTotalTimeReproduced() {
        return totalTimeReproduced;
    }

    public void setTotalTimeReproduced(Duration totalTimeReproduced) {
        this.totalTimeReproduced = totalTimeReproduced;
    }

}
