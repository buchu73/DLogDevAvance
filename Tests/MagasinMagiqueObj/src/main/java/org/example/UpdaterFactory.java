package org.example;

import org.example.updaters.*;

public class UpdaterFactory {

    public static Updater getUpdater(String itemName) {
        if (itemName.equals("Comté")) {
            return new ConteUpdater();
        }
        else if(itemName.equals("Pass VIP Concert")) {
            return new PassUpdater();
        }
        else if(itemName.equals("Kryptonite")) {
            return new KryptoniteUpdater();
        }
        else if (itemName.equals("Pouvoirs magiques")){
            return new PouvoirUpdater();
        }
        else {
            return new CommonUpdater();
        }
    }
}
