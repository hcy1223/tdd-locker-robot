package cn.xpbootcamp.gilded_rose.locker;

import cn.xpbootcamp.gilded_rose.exception.NoEmptyLockersException;
import cn.xpbootcamp.gilded_rose.exception.NoEmptyRobotException;

import java.util.Comparator;
import java.util.List;

public class SuperLockerRobot {
    private List<Locker> lockers;

    public SuperLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        try {
            return lockers.stream()
                    .max(Comparator.comparing(Locker::getEmptyPercent))
                    .orElseThrow(NoEmptyLockersException::new).store(bag);
        } catch (NoEmptyLockersException e) {
            throw new NoEmptyRobotException();
        }
    }
}
