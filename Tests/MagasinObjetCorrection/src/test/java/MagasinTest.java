import org.example.Item;
import org.example.Magasin;
import org.junit.jupiter.api.Test;

public class MagasinTest {

    @Test
    public void Test(){

        /*
          Comté
          Pass VIP Concert
          Kryptonite
          Pouvoirs magiques
         */

        Item[] items = new Item[2];
        items[0] = new Item("Toto", 2, 25);
        items[1] = new Item("Kryptonite", 2, 80);

        Magasin magasin = new Magasin(items);
        magasin.updateQuality();
        magasin.updateQuality();
    }
}
