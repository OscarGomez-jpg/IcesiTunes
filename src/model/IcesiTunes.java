package model;

import java.util.ArrayList;

public class IcesiTunes {
    
    private ArrayList<ProducerUser> producers;
    private ArrayList<Audio> audios;

    public IcesiTunes() {
        this.producers = new ArrayList<ProducerUser>();
        this.audios = new ArrayList<Audio>();
    }

    public int searchProducerById(String producerId) {
        int pos = -1;
        boolean isFound = false;

        for (int i = 0; i < producers.size() && isFound == false; i++) {
            if (producers.get(i).getId() == producerId) {
                pos = i;
                isFound = true;
            }
        }

        return pos;
    }

    public String addProducerUser(ProducerUser producer) {
        String msg = "Productor agregado con exito";

        if (searchProducerById(producer.getId()) != -1) {
            msg = "El productor ya existe dentro del sistema";
        }

        boolean result = producers.add(producer);

        if (result == false) {
            msg = "No se ha podido agregar el productor";
        }

        return msg;
    }

    
}
