package cn.xpbootcamp.gilded_rose.locker;

import cn.xpbootcamp.gilded_rose.exception.InvalidPasswordException;
import cn.xpbootcamp.gilded_rose.exception.NoEmptyCupboardException;
import cn.xpbootcamp.gilded_rose.exception.RandomPasswordException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

public class Locker {

    public static final int START_INDEX = 1;
    private static final int PASSWORD_LENGTH = 6;
    private static final int RANDOM_PASSWORD_TRY_TIMES = 20;
    private static final String CHARS = "0123456789";
    private List<Cupboard> cupboards;

    private Locker(List<Cupboard> boards) {
        this.cupboards = boards;
    }

    public static Locker createLocker(int totalCount) {
        List<Cupboard> cupboards = new ArrayList<>(totalCount);
        for (int i = 0; i < totalCount; i++) {
            cupboards.add(new Cupboard(i + START_INDEX));
        }
        return new Locker(cupboards);
    }

    public Ticket store() {
        if (getEmptyCount() < 1) {
            throw new NoEmptyCupboardException("no empty cupboard");
        }

        Cupboard board = cupboards.stream().filter(x -> x.getPassword() == null).findFirst().get();
        String password = null;
        try {
            password = generatePassword(RANDOM_PASSWORD_TRY_TIMES);
        } catch (RandomPasswordException e) {
            throw e;
        }
        board.setPassword(password);
        return new Ticket(board.getNumber(), password);
    }

    private String generatePassword(int maxTimes) throws RandomPasswordException {
        if (maxTimes <= 0) {
            throw new RandomPasswordException("Failed to generate password");
        }
        String password = getRandomStr(PASSWORD_LENGTH);
        if (cupboards.stream().filter(x -> x.isMatch(password)).findAny().isPresent()) {
            return generatePassword(maxTimes--);
        }
        return password;
    }

    private static String getRandomStr(int length) {
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(10);
            sb.append(CHARS.charAt(number));
        }
        return sb.toString();
    }

    public boolean locked(int number) {
        return cupboards.stream().filter(x -> x.getNumber() == number).findFirst().get().getPassword() != null;
    }

    public int getEmptyCount() {
        return (int) this.cupboards.stream().filter(x -> x.getPassword() == null).count();
    }

    public Cupboard pick(String password) {
        Optional<Cupboard> boardOpt = cupboards.stream().filter(x -> x.isMatch(password)).findAny();
        if (boardOpt.isPresent()) {
            Cupboard board = boardOpt.get();
            board.setPassword(null);
            return board;
        }
        throw new InvalidPasswordException("Invalid password");
    }
}
