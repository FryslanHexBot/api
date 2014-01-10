package api.workers;

import api.APITester;
import org.hexbot.api.methods.Walking;
import org.hexbot.api.methods.interactable.GameObjects;
import org.hexbot.api.wrapper.Tile;
import org.hexbot.api.wrapper.interactable.GameObject;
import org.hexbot.core.concurrent.script.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 22:07
 */
public class Walkbank extends Worker {

    private final Tile BANK_TILE = new Tile(3092,3245);

    @Override
    public boolean validate() {
        return APITester.walkBank == false;
    }

    @Override
    public void run() {
        GameObject booth = GameObjects.getNearestByAction("Bank");
        if(booth != null){
            if(booth.getLocation().getDistance() > 5){
                Walking.walk(BANK_TILE);
            }else{
                APITester.walkBank = true;
            }
        }else{
            Walking.walk(BANK_TILE);
        }
    }
}
