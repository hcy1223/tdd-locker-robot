package cn.xpbootcamp.gilded_rose;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import cn.xpbootcamp.gilded_rose.locker.Cupboard;
import cn.xpbootcamp.gilded_rose.locker.Locker;
import cn.xpbootcamp.gilded_rose.locker.Ticket;
import org.junit.jupiter.api.Test;

public class LockerPickTest {

    @Test
    void should_return_cupboard_when_pick_given_correct_password() {
//        given
        Locker locker = createLocker(19);
        Ticket ticket = locker.store();

//        when
        Cupboard board = locker.pick(ticket.getPassword());

//        then
        assertEquals(ticket.getNumber(), board.getNumber());
        assertNotNull(board);
        assertEquals(locker.getEmptyCount(), 19);
    }
}
