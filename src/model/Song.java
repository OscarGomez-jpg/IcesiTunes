package model;

public class Song extends Audio {
    private double price;
    private Genre genre;

    public Song(String name, String url, String duration, double price, int genre) {
        super(name, url, duration);
        this.price = price;
        this.genre = Genre.values()[genre];
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGenre() {
        String msg = "" + genre;
        return msg;
    }
}
