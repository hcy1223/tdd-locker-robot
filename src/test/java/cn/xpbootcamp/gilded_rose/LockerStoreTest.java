package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.NoEmptyLockersException;
import cn.xpbootcamp.gilded_rose.locker.Bag;
import cn.xpbootcamp.gilded_rose.locker.Locker;
import cn.xpbootcamp.gilded_rose.locker.Ticket;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static org.junit.jupiter.api.Assertions.*;

public class LockerStoreTest {

    @Test
    void should_get_ticket_when_store_given_19_empty_locker() {
//        given
        Locker locker = createLocker(19);
        Bag bag = new Bag();
//        when
        Ticket ticket = locker.store(bag);

//        then
        assertNotNull(ticket);
        assertEquals(locker.getEmptyCount(), 18);
    }

    @Test
    void should_get_error_when_store_given_0_empty_locker() {
//        given
        Locker locker = createLocker(0);
        Bag bag = new Bag();
//        when
        assertThrows(NoEmptyLockersException.class, () -> {
            locker.store(bag);
        });
    }

    @Test
    void should_get_0_empty_locker_when_store_given_1_empty_locker() {
//        given
        Locker locker = createLocker(1);
        Bag bag = new Bag();
//        when
        Ticket ticket = locker.store(bag);
//        then
        assertNotNull(ticket);
        assertEquals(locker.getEmptyCount(), 0);
    }

}
