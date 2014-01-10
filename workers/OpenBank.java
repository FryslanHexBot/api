package api.workers;

import api.APITester;
import org.hexbot.api.methods.helper.Bank;
import org.hexbot.core.concurrent.script.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 22:15
 */
public class OpenBank extends Worker{
    @Override
    public boolean validate() {
        return !APITester.openBank;
    }

    @Override
    public void run() {
        if(Bank.isOpen()){
            APITester.openBank = true;
        }else{
            Bank.open();
        }
    }
}
