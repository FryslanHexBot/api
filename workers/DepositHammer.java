package api.workers;

import api.APITester;
import org.hexbot.api.methods.helper.Bank;
import org.hexbot.api.methods.node.Inventory;
import org.hexbot.core.concurrent.script.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 22:17
 */
public class DepositHammer extends Worker{
    @Override
    public boolean validate() {
        return APITester.depositHammer == false;
    }

    @Override
    public void run() {
        if(Bank.isOpen()){
            if(Inventory.getItem("Hammer") != null){
                Bank.deposit("Hammer",10);
            }else{
                APITester.depositHammer = true;
            }

        }else{
            APITester.openBank = false;
        }
    }
}
