package api.workers;

import api.APITester;
import org.hexbot.api.methods.Walking;
import org.hexbot.api.methods.helper.Shop;
import org.hexbot.api.methods.interactable.Npcs;
import org.hexbot.api.methods.node.Inventory;
import org.hexbot.api.util.Time;
import org.hexbot.api.wrapper.Tile;
import org.hexbot.api.wrapper.interactable.Npc;
import org.hexbot.core.concurrent.script.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 22:01
 */
public class ShopSellHandeler extends Worker{
    private final Tile SHOP_TILE = new Tile(3211,3246);

    @Override
    public boolean validate() {
        return !APITester.sellHammer;
    }

    @Override
    public void run() {
        Npc shopper = Npcs.getNearest("Shop keeper");

        if(shopper != null){
            if(shopper.isVisible()){
                if(Shop.isOpen()){
                    if(Inventory.getItem("Hammer") == null || Inventory.getCount("Hammer") < 3){
                        System.out.println("Buying Hammer");
                        Shop.sellItem("Hammer", 1);
                        Time.sleep(1000, 1500);
                    } else{
                        APITester.sellHammer = true;
                    }
                }else{
                    System.out.println("Opening Shop");
                    shopper.interact("Trade");
                    Time.sleep(1000,1500);
                }
            }else{
                System.out.println("Walking to Shop");
                Walking.walk(shopper);
            }

        }else{
            System.out.println("Walking to Shop using Tile");
            Walking.walk(SHOP_TILE);
        }
    }
}
