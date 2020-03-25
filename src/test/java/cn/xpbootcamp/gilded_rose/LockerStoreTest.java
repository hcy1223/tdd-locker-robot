package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.NoEmptyCupboardException;
import cn.xpbootcamp.gilded_rose.locker.Locker;
import cn.xpbootcamp.gilded_rose.locker.Ticket;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LockerStoreTest {

    @Test
    void should_get_ticket_when_store_given_19_empty_locker() {
//        given
        Locker locker = new Locker(19);
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
        Locker locker = new Locker(0);

        assertThrows(NoEmptyCupboardException.class, () -> {
//        when
            locker.store();
        });


    }
}
