package org.saxing.decorator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * SimpleTroll implements {@link Troll} interface directly.
 *
 * @author saxing 2018/12/8 11:36
 */
public class SimpleTroll implements Troll {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimpleTroll.class);

    @Override
    public void attack() {
        LOGGER.info("The troll tries to grab you!");
    }

    @Override
    public int getAttackPower() {
        return 10;
    }

    @Override
    public void fleeBattle() {
        LOGGER.info("The troll shrieks in horror and runs away!");
    }
}
