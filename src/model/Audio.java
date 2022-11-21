package model;

import java.time.Duration;

public class Audio {
    private String name;
    private String url;
    private String author;
    private Duration duration;
    private int totalPlays;

    public Audio(String name, String url, String author, String duration) {
        this.name = name;
        this.url = url;
        this.author = author;
        this.duration = Duration.parse(duration);
        this.totalPlays = 0;
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

    public Duration getDuration() {
        return duration;
    }

    public void setDuration(Duration duration) {
        this.duration = duration;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getTotalPlays() {
        return totalPlays;
    }

    public void setTotalPlays(int totalPlays) {
        this.totalPlays = totalPlays;
    }
}
