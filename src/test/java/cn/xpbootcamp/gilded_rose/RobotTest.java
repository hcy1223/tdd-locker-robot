package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.exception.NoEmptyLockersException;
import cn.xpbootcamp.gilded_rose.locker.Bag;
import cn.xpbootcamp.gilded_rose.locker.Locker;
import cn.xpbootcamp.gilded_rose.locker.Robot;
import cn.xpbootcamp.gilded_rose.locker.Ticket;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.*;

public class RobotTest {

    @Test
    void should_get_ticket_when_store_given_2_lockers_first_1_capacity_second_1_capacity() {
        Locker firstLocker = createLocker(1);
        Locker secondLocker = createLocker(2);
        Robot robot = new Robot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        assertNotNull(ticket);
        assertEquals(firstLocker.getEmptyCount(), 0);
        assertEquals(secondLocker.getEmptyCount(), 2);
    }

    @Test
    void should_throw_exception_when_store_given_2_lockers_first_0_capacity_second_0_capacity() {
        Locker firstLocker = createLocker(0);
        Locker secondLocker = createLocker(0);
        Robot robot = new Robot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        assertThrows(NoEmptyLockersException.class, () -> {
            robot.store(bag);
        });
    }

    @Test
    void should_get_ticket_when_store_given_2_lockers_first_0_capacity_second_1_capacity() {
        Locker firstLocker = createLocker(0);
        Locker secondLocker = createLocker(1);
        Robot robot = new Robot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        assertNotNull(ticket);
        assertEquals(firstLocker.getEmptyCount(), 0);
        assertEquals(secondLocker.getEmptyCount(), 0);
    }

    @Test
    void should_get_bag_when_pick_given_valid_ticket() {
        Locker firstLocker = createLocker(1);
        Locker secondLocker = createLocker(2);
        Robot robot = new Robot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        Bag pickBag = robot.pick(ticket);

        assertEquals(bag, pickBag);
        assertEquals(firstLocker.getEmptyCount(), 1);
        assertEquals(secondLocker.getEmptyCount(), 2);
    }


    @Test
    void should_throw_exception_when_pick_given_invalid_ticket() {
        Locker firstLocker = createLocker(1);
        Locker secondLocker = createLocker(2);
        Robot robot = new Robot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        robot.store(bag);

        Ticket invalidTicket = new Ticket();
        assertThrows(InvalidTicketException.class, () -> {
            robot.pick(invalidTicket);
        });
    }
}