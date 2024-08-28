package org.example;

import org.example.updater.CommonUpdater;
import org.example.updater.KryptoniteUpdater;

public class UpdaterFactory {

    public static Updater createUpdater(String itemName){

        if (itemName.equals("Kryptonite")){
            return new KryptoniteUpdater();
        }
        /// else if
        else
        {
            return new CommonUpdater();
        }
    }
}
