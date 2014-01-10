package api;

import api.workers.*;
import org.hexbot.api.listeners.Paintable;
import org.hexbot.core.concurrent.script.Info;
import org.hexbot.core.concurrent.script.TaskScript;
import org.hexbot.core.concurrent.script.Type;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 18:02
 */
@Info(author = "Fryslan", name = "API Tester", description = "Tests methods in API", type = Type.MISC)
public class APITester extends TaskScript implements Paintable {

    public static boolean
            teleportLumby = false,
            pickPocketMen = false,
            buyhammer = false,
            sellHammer = false,
            walkBank = false,
            openBank = false,
            depositHammer = false,
            withdrawHammer = false,
            closeBank = false;

    public APITester(){
        submit(
                new TelePortToLumby(),
                new PickPocketMen(),
                new ShopBuyHandeler(),
                new ShopSellHandeler(),
                new Walkbank(),
                new OpenBank(),
                new DepositHammer(),
                new WithdrawHammer(),
                new CloseBank());
    }

    @Override
    public void paint(Graphics g) {



        g.drawString("Small Widget Interacting + Magic castSpell: "+teleportLumby,10,15);
        g.drawString("NPC Interacting : "+pickPocketMen,10,30);
        g.drawString("Shop Buying : "+buyhammer,10,45);
        g.drawString("Shop Sell : "+sellHammer,10,60);
        g.drawString("Walking : "+walkBank,10,75);
        g.drawString("Opening Bank : "+openBank,10,90);
        g.drawString("Deposit Item : "+depositHammer,10,105);
        g.drawString("Withdraw Item : "+withdrawHammer,10,120);
        g.drawString("Closing bank : "+closeBank,10,135);

    }
}
