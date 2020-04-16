package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.NoEmptyRobotException;
import cn.xpbootcamp.gilded_rose.locker.*;
import com.google.common.collect.ImmutableList;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    void should_get_three_ticket_when_store_given_first_locker_is_three_and_second_is_six() {
        Locker firstLocker = createLocker(3);
        Locker secondLocker = createLocker(6);
        SuperLockerRobot robot = new SuperLockerRobot(asList(firstLocker, secondLocker));
        Bag firstBag = new Bag();
        Bag secondBag = new Bag();
        Bag thirdBag = new Bag();

        Ticket firstTicket = robot.store(firstBag);
        Ticket secondTicket = robot.store(secondBag);
        Ticket thirdTicket = robot.store(thirdBag);

        assertNotNull(firstTicket);
        assertNotNull(secondTicket);
        assertNotNull(thirdTicket);
        assertSame(firstBag, firstLocker.pick(firstTicket));
        assertSame(secondBag, secondLocker.pick(secondTicket));
        assertSame(thirdBag, secondLocker.pick(thirdTicket));
    }

    @Test
    void should_get_three_ticket_when_store_given_first_locker_is_six_and_second_is_three() {
        Locker firstLocker = createLocker(6);
        Locker secondLocker = createLocker(3);
        SuperLockerRobot robot = new SuperLockerRobot(asList(firstLocker, secondLocker));
        Bag firstBag = new Bag();
        Bag secondBag = new Bag();
        Bag thirdBag = new Bag();

        Ticket firstTicket = robot.store(firstBag);
        Ticket secondTicket = robot.store(secondBag);
        Ticket thirdTicket = robot.store(thirdBag);

        assertNotNull(firstTicket);
        assertNotNull(secondTicket);
        assertNotNull(thirdTicket);
        assertSame(firstBag, firstLocker.pick(firstTicket));
        assertSame(secondBag, secondLocker.pick(secondTicket));
        assertSame(thirdBag, firstLocker.pick(thirdTicket));
    }

    @Test
    void should_store_failure_when_store_given_empty_locker() {
        Locker firstLocker = createLocker(1);
        SuperLockerRobot robot = new SuperLockerRobot(ImmutableList.of(firstLocker));
        Bag firstBag = new Bag();
        Bag secondBag = new Bag();

        Ticket firstTicket = robot.store(firstBag);

        assertNotNull(firstTicket);
        assertThrows(NoEmptyRobotException.class, () -> {
            robot.store(secondBag);
        });
    }

    @Test
    void should_get_bag_when_pick_given_valid_ticket() {
        Locker firstLocker = createLocker(1);
        Locker secondLocker = createLocker(2);
        SuperLockerRobot robot = new SuperLockerRobot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        Bag pickBag = robot.pick(ticket);

        assertSame(bag, pickBag);
    }

}
