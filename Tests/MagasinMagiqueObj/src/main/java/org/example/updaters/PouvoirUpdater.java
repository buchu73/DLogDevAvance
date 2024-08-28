package org.example.updaters;

import org.example.Item;
import org.example.Updater;

public class PouvoirUpdater implements Updater {
    @Override
    public void update(Item item)
    {
        if(canLowerQuality(item)) {
            item.quality = item.quality - 2;
        }

        item.sellIn--;
    }

    private boolean canLowerQuality(Item item) {
        return item.quality > 0;
    }
}
