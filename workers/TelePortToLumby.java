package api.workers;

import api.APITester;
import org.hexbot.api.methods.helper.Magic;
import org.hexbot.api.methods.helper.Tab;
import org.hexbot.api.methods.interactable.Players;
import org.hexbot.api.util.Area;
import org.hexbot.api.util.Timer;
import org.hexbot.api.wrapper.Tile;
import org.hexbot.core.concurrent.script.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 18:16
 */
public class TelePortToLumby extends Worker {

    Area LumbyArea = new Area(new Tile[] { new Tile(3197, 3238, 0), new Tile(3189, 3212, 0), new Tile(3212, 3200, 0),
            new Tile(3233, 3209, 0), new Tile(3232, 3224, 0), new Tile(3226, 3241, 0) });

    @Override
    public boolean validate() {
        return !APITester.teleportLumby;
    }

    @Override
    public void run() {
        if(!LumbyArea.contains(Players.getLocal()) && APITester.teleportLumby == false) {

            if(Tab.MAGIC.isOpen()){
                System.out.println("Teleporting To Lumbridge");
                Magic.Spell.HOME_TELEPORT.clickSpell();
                Timer t = new Timer(2000);
                while(t.isRunning() && !LumbyArea.contains(Players.getLocal())){
                    if(Players.getLocal().getAnimation() != -1){
                        t = new Timer(2000);
                    }
                }
            }else{
                System.out.println("Opening Magic Tab");
                Tab.MAGIC.open();
            }
        } else{
            APITester.teleportLumby = true;
        }

    }
}
