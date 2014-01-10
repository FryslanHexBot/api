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
 * Time: 21:33
 */
public class shopBuyHandeler extends Worker{
    @Override
    public boolean validate() {
        return APITester.buyhammer == false;
    }

    @Override
    public void run() {
        Npc shopper = Npcs.getNearest("Shop keeper");

        if(shopper != null){
            if(shopper.isVisible()){
                if(Shop.isOpen()){
                    if(Inventory.getItem("Hammer") == null || Inventory.getCount("Hammer") < 3){
                        System.out.println("Buying Hammer");
                        Shop.buyItem("Hammer",5);
                        Time.sleep(1000,1500);
                    } else{
                        APITester.buyhammer = true;
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
            Walking.walk(new Tile(3211,3246));
        }
    }
}
