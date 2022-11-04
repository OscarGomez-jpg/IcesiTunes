package model;

import java.util.ArrayList;
import java.util.Random;

public class Playlist {
    public static final int TOTAL_SIZE_MATRIX = 6;

    private String name;
    private String code;
    private Random random;
    private boolean hasSongs;
    private boolean hasPodcast;
    private ArrayList<Audio> songs;
    private int[][] matrix;

    public Playlist(String name) {
        this.random = new Random();
        this.name = name;
        this.code = "";
        this.hasSongs = false;
        this.hasPodcast = false;
        this.songs = new ArrayList<Audio>();
        this.matrix = new int[TOTAL_SIZE_MATRIX][TOTAL_SIZE_MATRIX];
        fillMatrix();
    }

    /**
     * This function receives a song and adds it to the playlist
     * 
     * @param audio The song to be added
     * @return A String validating the operation
     */
    public String addAudio(Audio audio) {
        String msg = "Audio agregado con exito";

        songs.add(audio);

        checkContent();

        return msg;
    }

    public String deleteAudio(Audio audio) {
        String msg = "Audio eliminado con exito";
        return msg;
    }

    /**
     * This function fills the matrix of the playlist to generate its code
     */
    public void fillMatrix() {
        for (int i = 0; i < TOTAL_SIZE_MATRIX; i++) {
            for (int j = 0; j < TOTAL_SIZE_MATRIX; j++) {
                matrix[i][j] = random.nextInt(10);
            }
        }
    }

    /**
     * This function checks the content of the playlist and change hasSong or
     * hasPodcast
     * according to the content of the playlist
     */
    public void checkContent() {
        hasSongs = false;
        hasPodcast = false;

        for (int i = 0; i < songs.size(); i++) {
            if (songs.get(i) instanceof Song) {
                hasSongs = true;
            } else if (songs.get(i) instanceof Podcast) {
                hasPodcast = true;
            }
        }

        generateCode();
    }

    /**
     * This function generates the code according to the content of the playlist
     */
    public void generateCode() {
        if (hasSongs && hasPodcast) {
            code = generateSongsAndPodcastCode();
        } else if (hasSongs) {
            code = generateOnlySongsCode();
        } else if (hasPodcast) {
            code = generateOnlyPodcastCode();
        }
    }

    /**
     * This function returns a String with the code formatted as an N
     * 
     * @return String code with the sequence of a N
     */
    public String generateOnlySongsCode() {
        String formattedCode = "";
        int posX = 1;
        int posY = 1;

        for (int i = matrix.length - 1; i >= 0; i--) {
            formattedCode += matrix[i][0];
        }

        for (int i = 1; i < matrix.length - 1; i++) {
            formattedCode += matrix[posY][posX];
            posX += 1;
            posY += 1;
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            formattedCode += matrix[i][matrix.length - 1];
        }

        return formattedCode;
    }

    /**
     * This function generates a code when the playlist contains only
     * Podcast. The code will be generated as a T
     * 
     * @return A String with the code generated as a t
     */
    public String generateOnlyPodcastCode() {
        String formattedMessage = "";

        for (int i = 0; i < (matrix.length / 2) - 1; i++) {
            formattedMessage += matrix[0][i];
        }

        for (int i = 0; i < matrix.length; i++) {
            formattedMessage += matrix[i][(matrix.length / 2) - 1];
        }

        for (int i = matrix.length - 1; i >= 0; i--) {
            formattedMessage += matrix[i][(matrix.length / 2)];
        }

        for (int i = (matrix.length / 2); i < matrix.length; i++) {
            formattedMessage += matrix[0][i];
        }

        return formattedMessage;
    }

    /**
     * This function generates the code if the playlist contains a song and
     * a podcast.
     * 
     * It will generate if the position j + i is equals to an odd number and i + j
     * is different from 1
     * 
     * @return A String with the code generated
     */
    public String generateSongsAndPodcastCode() {
        String formattedCode = "";

        for (int i = matrix.length - 1; i >= 0; i--) {
            for (int j = matrix.length - 1; j >= 0; j--) {
                if ((j + i) % 2 != 0 && j + i != 1) {
                    formattedCode += matrix[i][j];
                }
            }
        }

        return formattedCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public ArrayList<Audio> getSongs() {
        return songs;
    }

    public int[][] getMatrix() {
        return matrix;
    }
}
