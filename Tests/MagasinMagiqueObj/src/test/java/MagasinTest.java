import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MagasinTest {

    @Test
    void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        assertEquals("foo", items[0].name);
    }

    @Test
    void quality2timesFasterWhenSellinPassed(){
        Item[] items = new Item[] { new Item("Kinder", 0, 10) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(-1, items[0].sellIn);
        Assertions.assertEquals(8, items[0].quality);
    }

    @Test
    void qualityShouldNeverBeNegative(){
        Item[] items = new Item[] { new Item("Kinder", 1, 0) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(0, items[0].sellIn);
        Assertions.assertEquals(0, items[0].quality);
    }

    @Test
    void comteQualityShouldIncrementWhenSellInPassed(){
        Item[] items = new Item[] { new Item("Comté", -1, 12) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(-2, items[0].sellIn);
        Assertions.assertEquals(13, items[0].quality); // with previous function this test fail ! need to pass 13 -> 14
    }

    @Test
    void comteQualityShouldNotIncrementWhenQualityOver50EvenIfSellinDecrement(){
        Item[] items = new Item[] { new Item("Comté", -1, 50) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(-2, items[0].sellIn);
        Assertions.assertEquals(50, items[0].quality);
    }

    @Test
    void qualityShouldNeverBeHigherThan50(){
        Item[] items = new Item[] { new Item("Comté", 10, 50) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(9, items[0].sellIn);
        Assertions.assertEquals(50, items[0].quality);
    }

    @Test
    void kryptonite(){
        Item[] items = new Item[] { new Item("Kryptonite", -1, 80) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(-1, items[0].sellIn);
        Assertions.assertEquals(80, items[0].quality);
    }

    @Test
    void vipTicketQualityShouldIncrementBy2WhenSellinUnder10(){
        Item[] items = new Item[] { new Item("Pass VIP Concert", 10, 10) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(9, items[0].sellIn);
        Assertions.assertEquals(12, items[0].quality);
    }

    @Test
    void vipTicketQualityShouldIncrementBy1WhenQualityOver50(){
        Item[] items = new Item[] { new Item("Pass VIP Concert", 5, 49) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(4, items[0].sellIn);
        Assertions.assertEquals(50, items[0].quality);
    }

    @Test
    void vipTicketQualityShouldIncrementBy1WhenSellinOver10(){
        Item[] items = new Item[] { new Item("Pass VIP Concert", 12, 10) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(11, items[0].sellIn);
        Assertions.assertEquals(11, items[0].quality);
    }

    @Test
    void vipTicketQualityShouldIncrementBy3WhenSellinUnder5(){
        Item[] items = new Item[] { new Item("Pass VIP Concert", 5, 10) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(4, items[0].sellIn);
        Assertions.assertEquals(13, items[0].quality);
    }

    @Test
    void vipTicketQualityShouldBe0WhenSellinPassed(){
        Item[] items = new Item[] { new Item("Pass VIP Concert", 0, 10) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(-1, items[0].sellIn);
        Assertions.assertEquals(0, items[0].quality);
    }

    @Test
    void pouvoirMagiquesQualityDecrement2TimesFasterWhenSellinOver0(){
        Item[] items = new Item[] { new Item("Pouvoirs magiques", 15, 20) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(14, items[0].sellIn);
        Assertions.assertEquals(18, items[0].quality);
    }
    @Test
    void pouvoirMagiquesQualityDecrement2TimesFasterWhenSellinUnder0(){
        Item[] items = new Item[] { new Item("Pouvoirs magiques", -1, 20) };
        Magasin app = new Magasin(items);
        app.updateQualities();
        Assertions.assertEquals(-2, items[0].sellIn);
        Assertions.assertEquals(16, items[0].quality);
    }
}