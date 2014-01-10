package api.workers;

import api.APITester;
import org.hexbot.api.methods.Walking;
import org.hexbot.api.methods.interactable.Npcs;
import org.hexbot.api.methods.interactable.Players;
import org.hexbot.api.methods.node.Inventory;
import org.hexbot.api.util.Time;
import org.hexbot.api.wrapper.interactable.Npc;
import org.hexbot.core.concurrent.script.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 18:31
 */
public class PickPocketMen extends Worker {
    @Override
    public boolean validate() {
        return !APITester.pickPocketMen;
    }

    @Override
    public void run() {
        Npc man = Npcs.getNearest("Man");
        if(Inventory.getItem("Coins") != null && Inventory.getItem("Coins").getStackSize() < 5 || Inventory.getItem("Coins") == null) {
        if (Players.getLocal().getAnimation() == -1) {
            if (man != null) {
                if (man.isVisible()) {
                    man.interact("Pickpocket");
                    Time.sleep(500,1500);
                } else {
                    Walking.walk(man);
                }
            }
        }
        }else{
            APITester.pickPocketMen = true;
        }
    }
}
