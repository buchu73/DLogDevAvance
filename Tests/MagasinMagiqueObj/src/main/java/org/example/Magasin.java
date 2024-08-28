package org.example;

import static java.util.Arrays.*;
import static org.example.UpdaterFactory.getUpdater;

public class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    public void updateQualities(){
        asList(this.items).forEach(item -> getUpdater(item.name).update(item));
    }
}
