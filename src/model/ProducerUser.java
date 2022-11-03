package model;

import java.time.LocalTime;

public abstract class ProducerUser extends User {
    private String name;
    private String url;
    private int totalReproductions;

    // Acumulative reproductions in seconds
    private LocalTime totalTimeReproduced;

    public ProducerUser(String nickname, String id, String name, String url) {
        super(nickname, id);
        this.name = name;
        this.url = url;
        this.totalReproductions = 0;
        this.totalTimeReproduced = LocalTime.of(00, 00, 00);
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

    public LocalTime getTotalTimeReproduced() {
        return totalTimeReproduced;
    }

    public void addTotalTimeReproduced(Long totalTimeReproduced) {
        this.totalTimeReproduced.plusSeconds(totalReproductions);
    }

}
