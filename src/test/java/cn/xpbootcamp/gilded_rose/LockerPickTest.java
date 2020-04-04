package cn.xpbootcamp.gilded_rose;

import cn.xpbootcamp.gilded_rose.exception.InvalidTicketException;
import cn.xpbootcamp.gilded_rose.locker.Bag;
import cn.xpbootcamp.gilded_rose.locker.Locker;
import cn.xpbootcamp.gilded_rose.locker.Ticket;
import org.junit.jupiter.api.Test;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LockerPickTest {

    @Test
    void should_return_cupboard_when_pick_given_correct_password() {
//        given
        Locker locker = createLocker(19);
        Bag bag = new Bag();
        Ticket ticket = locker.store(bag);

//        when
        Bag pickBag = locker.pick(ticket);

//        then
        assertEquals(bag, pickBag);
    }

    @Test
    void should_throw_exception_when_pick_given_invalid_ticket() {
//        given
        Locker locker = createLocker(19);
        Bag bag = new Bag();
        Ticket ticket = locker.store(bag);

        Ticket invalidTicket = new Ticket();
        // then
        assertThrows(InvalidTicketException.class, () -> {
            //        when
            Bag pickBag = locker.pick(invalidTicket);
        });
    }

    @Test
    void should_throw_exception_when_pick_given_used_ticket() {
//        given
        Locker locker = createLocker(1);
        Bag bag = new Bag();
        Ticket ticket = locker.store(bag);

        locker.pick(ticket);

        assertThrows(InvalidTicketException.class, () -> {
            //        when
            locker.pick(ticket);
        });
    }
}
