package model;

import java.util.ArrayList;

public class IcesiTunes {

    private ArrayList<User> users;
    private ArrayList<Audio> audios;

    public IcesiTunes() {
        this.users = new ArrayList<User>();
        this.audios = new ArrayList<Audio>();
    }

    /**
     * This function returns the position of a user in the app
     * 
     * @param userId The user's id
     * @return A int with position of the user in the array
     */
    public int searchUserById(String userId) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < users.size() && isFound == false; i++) {
            if (users.get(i).getId() == userId) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    /**
     * This function receives an User by parameter and adds it to the main application
     * 
     * @param user
     * @return
     */
    public String addUser(User user) {
        String msg = "Usuario agregado con exito";

        if (searchUserById(user.getId()) != -1) {
            msg = "El Usuario ya existe dentro del sistema";
        }

        boolean result = users.add(user);

        if (result == false) {
            msg = "No se ha podido agregar el Usuario";
        }

        return msg;
    }
}
