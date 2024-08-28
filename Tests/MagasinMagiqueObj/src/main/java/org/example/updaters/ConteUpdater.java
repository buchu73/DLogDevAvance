package org.example.updaters;

import org.example.Updater;
import org.example.Item;

public class ConteUpdater implements Updater {

    @Override
    public void update(Item item) {
        if(item.quality < 50) {
            item.quality++;
        }
        item.sellIn--;
    }
}
