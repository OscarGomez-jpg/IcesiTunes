package model;

import java.time.LocalTime;

public class Audio {
    private String name;
    private String url;
    private LocalTime duration;

    public Audio(String name, String url, String duration) {
        this.name = name;
        this.url = url;
        this.duration = LocalTime.parse(duration);
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

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }
}
