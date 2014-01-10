package api.workers;

import api.APITester;
import org.hexbot.api.methods.helper.Bank;
import org.hexbot.api.util.Time;
import org.hexbot.core.concurrent.script.Worker;

/**
 * Created with IntelliJ IDEA.
 * User: Fryslan
 * Date: 10-1-14
 * Time: 22:21
 */
public class CloseBank extends Worker {
    @Override
    public boolean validate() {
        return !APITester.closeBank;
    }

    @Override
    public void run() {
        if (!Bank.isOpen()) {
            Bank.open();
            Time.sleep(1000, 1500);
        }

        if (Bank.isOpen()) {
            Bank.close();
            Time.sleep(1000, 1500);
            if (!Bank.isOpen()) {
                APITester.closeBank = true;
            }
        }
    }
}
