package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.NoEmptyCupboardException;
import cn.xpbootcamp.gilded_rose.locker.Locker;
import cn.xpbootcamp.gilded_rose.locker.Ticket;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static cn.xpbootcamp.gilded_rose.locker.Locker.START_INDEX;
import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static org.junit.jupiter.api.Assertions.*;

public class LockerStoreTest {

    @Test
    void should_get_ticket_when_store_given_19_empty_locker() {
//        given
        Locker locker = createLocker(19);
//        when
        Ticket ticket = locker.store();

//        then
        assertTrue(locker.locked(ticket.getNumber()));
        assertNotNull(ticket.getPassword());
        assertEquals(locker.getEmptyCount(), 18);
    }

    @Test
    void should_get_error_when_store_given_0_empty_locker() {
//        given
        Locker locker = createLocker(0);
//        when
        assertThrows(NoEmptyCupboardException.class, (Executable) locker::store);
    }

    @Test
    void should_get_0_empty_locker_when_store_given_1_empty_locker() {
//        given
        Locker locker = createLocker(1);
//        when
        Ticket ticket = locker.store();
//        then
        assertEquals(ticket.getNumber(), START_INDEX);
        assertTrue(locker.locked(ticket.getNumber()));
        assertNotNull(ticket.getPassword());
        assertEquals(locker.getEmptyCount(), 0);
    }
}
