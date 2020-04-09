package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.locker.Bag;
import cn.xpbootcamp.gilded_rose.locker.Locker;
import cn.xpbootcamp.gilded_rose.locker.Robot;
import cn.xpbootcamp.gilded_rose.locker.Ticket;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static java.util.Arrays.asList;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class RobotTest {

    @Test
    void should_get_ticket_when_store_given_2_lockers_first_1_capacity_second_2_capacity() {
        Locker firstLocker = createLocker(1);
        Locker secondLocker = createLocker(2);
        Robot robot = new Robot(asList(firstLocker, secondLocker));
        Bag bag = new Bag();

        Ticket ticket = robot.store(bag);

        assertNotNull(ticket);
        assertEquals(firstLocker.getEmptyCount(), 1);
        assertEquals(secondLocker.getEmptyCount(), 1);
    }
}
