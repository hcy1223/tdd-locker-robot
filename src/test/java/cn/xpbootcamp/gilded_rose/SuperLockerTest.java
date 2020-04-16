package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.locker.*;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertSame;

public class SuperLockerTest {

    @Test
    void should_get_two_ticket_when_store_two_bags_given_two_lockers_both_five() {
        Locker firstLocker = createLocker(5);
        Locker secondLocker = createLocker(5);
        SuperLockerRobot robot = new SuperLockerRobot(asList(firstLocker, secondLocker));
        Bag firstBag = new Bag();
        Bag secondBag = new Bag();

        Ticket firstTicket = robot.store(firstBag);
        Ticket secondTicket = robot.store(secondBag);

        assertNotNull(firstTicket);
        assertNotNull(secondTicket);
        assertSame(firstBag, firstLocker.pick(firstTicket));
        assertSame(secondBag, secondLocker.pick(secondTicket));
    }


}
