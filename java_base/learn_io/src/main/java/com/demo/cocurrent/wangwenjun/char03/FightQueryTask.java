package com.demo.cocurrent.wangwenjun.char03;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: zwy
 * @Date: 2020/10/27
 * @Description: com.demo.cocurrent.wangwenjun.char03
 * @version:
 */
public class FightQueryTask extends Thread implements FightQuery {
    private final String origin;
    private final String destiination;
    private final List<String> fightList = new ArrayList<>();

    public FightQueryTask(String airline, String origin, String destination) {
        super("[ " + airline + " ]");
        this.origin = origin;
        this.destiination = destination;
    }

    @Override
    public void run() {
        System.out.printf("%s - query from %s to %s \n", getName(), origin, destiination);
        int randomVal = ThreadLocalRandom.current().nextInt(10);
        try {
            TimeUnit.SECONDS.sleep(randomVal);
            this.fightList.add(getName()+"-"+randomVal);
            System.out.printf("the fight : %s list query successful \n",getName());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> get() {
        return this.fightList;
    }


}
