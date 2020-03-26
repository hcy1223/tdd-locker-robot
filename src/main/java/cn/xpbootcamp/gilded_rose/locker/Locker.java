package cn.xpbootcamp.gilded_rose.locker;

import cn.xpbootcamp.gilded_rose.exception.NoEmptyCupboardException;
import cn.xpbootcamp.gilded_rose.exception.RandomPasswordException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class Locker {

    private static final int PASSWORD_LENGTH = 6;
    private static final int RANDOM_PASSWORD_TRY_TIMES = 20;
    private static final String CHARS = "0123456789";
    private Queue<Integer> emptyCupboards;
    private Map<Integer, Cupboard> usedCupboards;

    public Locker(Queue<Integer> emptyCupboards) {
        this.emptyCupboards = emptyCupboards;
        this.usedCupboards = new HashMap<>();
    }

    public static Locker createLocker(int totalCount) {
        Queue<Integer> emptyCupboards = IntStream.range(0, totalCount).boxed().collect(Collectors.toCollection(LinkedList::new));
        return new Locker(emptyCupboards);
    }

    public Ticket store() {
        if (emptyCupboards.size() < 1) {
            throw new NoEmptyCupboardException("no empty cupboard");
        }

        int number = emptyCupboards.remove();
        String password = null;
        try{
            password = generatePassword(RANDOM_PASSWORD_TRY_TIMES);
        } catch (RandomPasswordException e){
            throw e;
        }

        this.usedCupboards.put(number, new Cupboard(number, password));
        return new Ticket(number, password);
    }

    private String generatePassword(int maxTimes) throws RandomPasswordException {
        if(maxTimes <= 0) {
            throw new RandomPasswordException("Failed to generate password");
        }
        String password = getRandomStr(PASSWORD_LENGTH);
        if(!usedCupboards.isEmpty()) {
            if(usedCupboards.values().stream().filter(x->x.isMatch(password)).findAny().isPresent()) {
                return generatePassword(maxTimes--);
            }
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
        return this.usedCupboards.containsKey(number);
    }

    public int getEmptyCount() {
        return this.emptyCupboards.size();
    }

    public Cupboard pick(String password) {
        return null;
    }

}
