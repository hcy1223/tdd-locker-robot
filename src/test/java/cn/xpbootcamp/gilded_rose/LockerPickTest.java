package cn.xpbootcamp.gilded_rose;

import static cn.xpbootcamp.gilded_rose.locker.Locker.createLocker;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cn.xpbootcamp.gilded_rose.exception.InvalidPasswordException;
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

    @Test
    void should_throw_exception_when_pick_given_wrong_password() {
//        given
        Locker locker = createLocker(19);
        locker.store();

        Cupboard board = null;
        try {
            //        when
            board = locker.pick("wrong");

        } catch(InvalidPasswordException e) {
            //        then
            assertNull(board);
        } finally{
            //        then
            assertEquals(locker.getEmptyCount(), 18);
        }
    }


    @Test
    void should_throw_exception_when_pick_given_used_password() {
//        given
        Locker locker = createLocker(1);
        Ticket ticket = locker.store();
        locker.pick(ticket.getPassword());

        Cupboard board = null;
        try {
            //        when
            board = locker.pick(ticket.getPassword());

        } catch(InvalidPasswordException e) {
            //        then
            assertNull(board);
        } finally{
            //        then
            assertEquals(locker.getEmptyCount(), 1);
        }
    }

}
