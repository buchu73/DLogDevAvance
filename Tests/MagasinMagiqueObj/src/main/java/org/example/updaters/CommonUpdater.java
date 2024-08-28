package org.example.updaters;

import org.example.Item;
import org.example.Updater;

public class CommonUpdater implements Updater {
    @Override
    public void update(Item item) {

        if(canLowerQuality(item)) {
            item.quality--;
            if(pastExpirationDate(item)) {
                item.quality--;
            }
        }

        item.sellIn--;
    }

    private boolean pastExpirationDate(Item item) {
        return item.sellIn <= 0;
    }

    private boolean canLowerQuality(Item item) {
        return item.quality > 0;
    }
}
