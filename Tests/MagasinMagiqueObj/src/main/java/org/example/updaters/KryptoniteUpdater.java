package org.example.updaters;

import org.example.Item;
import org.example.Updater;

public class KryptoniteUpdater implements Updater {
    @Override
    public void update(Item item) {
        item.quality = 80;
    }
}
