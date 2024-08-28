package org.example;

import static java.util.Arrays.asList;

public class Magasin {
    Item[] items;

    public Magasin(Item[] items) {
        this.items = items;
    }

    public void updateQuality(){
        //asList(items).forEach(item -> UpdaterFactory.createUpdater(item.name).update(item));

        for (Item item: items) {
            Updater itemUpdater = UpdaterFactory.createUpdater(item.name);
            itemUpdater.update(item);
        }
    }

}
