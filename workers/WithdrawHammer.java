package api.workers;

import api.APITester;
import org.hexbot.api.methods.helper.Bank;
import org.hexbot.api.methods.node.Inventory;
import org.hexbot.core.concurrent.script.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 22:20
 */
public class WithdrawHammer extends Worker{
    @Override
    public boolean validate() {
        return !APITester.withdrawHammer;
    }

    @Override
    public void run() {
        if(Bank.isOpen()){
            if(Inventory.getItem("Hammer") == null){
                Bank.withdraw("Hammer",10);
            }else{
                APITester.withdrawHammer = true;
            }

        }else{
            APITester.openBank = false;
        }
    }
}
