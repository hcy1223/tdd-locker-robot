package cn.xpbootcamp.gilded_rose.locker;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.NoEmptyLockersException;
import cn.xpbootcamp.gilded_rose.exception.NoEmptyRobotException;

import java.util.Comparator;
import java.util.List;

public class SmartLockerRobot {
    private List<Locker> lockers;

    public SmartLockerRobot(List<Locker> lockers) {
        this.lockers = lockers;
    }

    public Ticket store(Bag bag) {
        try {
            return lockers.stream()
                    .max(Comparator.comparing(Locker::getEmptyCount))
                    .orElseThrow(NoEmptyLockersException::new).store(bag);
        } catch (NoEmptyLockersException e) {
            throw new NoEmptyRobotException();
        }
    }

    public Bag pick(Ticket ticket) {
        for (Locker locker : lockers) {
            try {
                return locker.pick(ticket);
            } catch (InvalidTicketException ignored) {
            }
        }
        throw new InvalidTicketException();
    }
}
