package model;

import java.time.Duration;

public class Audio {
    private String name;
    private String url;
    private String author;
    private Duration duration;

    public Audio(String name, String url, String author, String duration) {
        this.name = name;
        this.url = url;
        this.author = author;
        this.duration = Duration.parse(duration);
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

}
