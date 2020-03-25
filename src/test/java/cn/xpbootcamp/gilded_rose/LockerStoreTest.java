package cn.xpbootcamp.gilded_rose;

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
        assertEquals(locker.getStatus(ticket.getNumber()), 1);
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
