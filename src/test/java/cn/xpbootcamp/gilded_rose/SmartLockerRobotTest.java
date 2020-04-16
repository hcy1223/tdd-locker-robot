package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.NoEmptyRobotException;
import cn.xpbootcamp.gilded_rose.locker.Bag;
import cn.xpbootcamp.gilded_rose.locker.Locker;
import cn.xpbootcamp.gilded_rose.locker.SmartLockerRobot;
import cn.xpbootcamp.gilded_rose.locker.Ticket;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class SmartLockerRobotTest {

    @Test
    void should_get_ticket_when_store_given_2_lockers_first_1_capacity_second_2_capacity() {
        Locker firstLocker = createLocker(1);
        Locker secondLocker = createLocker(2);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        assertNotNull(ticket);
        assertSame(bag, secondLocker.pick(ticket));
    }
    @Test
    void should_store_failure_when_store_given_2_lockers_first_0_capacity_second_0_capacity() {
        Locker firstLocker = createLocker(0);
        Locker secondLocker = createLocker(0);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        assertThrows(NoEmptyRobotException.class, () -> {
            robot.store(bag);
        });
    }

    @Test
    void should_get_ticket_when_store_given_2_lockers_first_2_capacity_second_1_capacity() {
        Locker firstLocker = createLocker(2);
        Locker secondLocker = createLocker(1);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        assertNotNull(ticket);
        assertSame(bag, firstLocker.pick(ticket));
    }

    @Test
    void should_get_ticket_when_store_given_2_lockers_first_2_capacity_second_2_capacity() {
        Locker firstLocker = createLocker(2);
        Locker secondLocker = createLocker(2);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        assertNotNull(ticket);
        assertSame(bag, firstLocker.pick(ticket));
    }

    @Test
    void should_get_bag_when_pick_given_valid_ticket() {
        Locker firstLocker = createLocker(1);
        Locker secondLocker = createLocker(2);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        Bag pickBag = robot.pick(ticket);

        assertSame(bag, pickBag);
    }

    @Test
    void should_pick_failure_when_pick_given_invalid_ticket() {
        Locker firstLocker = createLocker(1);
        Locker secondLocker = createLocker(2);
        SmartLockerRobot robot = new SmartLockerRobot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        robot.store(bag);

        Ticket invalidTicket = new Ticket();
        assertThrows(InvalidTicketException.class, () -> {
            robot.pick(invalidTicket);
        });
    }
}
