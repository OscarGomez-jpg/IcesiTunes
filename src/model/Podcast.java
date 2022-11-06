package model;

public class Podcast extends Audio {

    private Category category;

    public Podcast(String name, String url, String author, String duration, int category) {
        super(name, url, author, duration);
        this.category = Category.values()[category];
    }

    public String getCategory() {
        String msg = "" + category;
        return msg;
    }
}
