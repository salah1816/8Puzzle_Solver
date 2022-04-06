package Model;

import java.time.Duration;
import java.time.Instant;

public class TimeTest {

    public static long testTime(Runnable exp) {
        Instant debut = Instant.now();
        exp.run();
        Instant fin = Instant.now();
        Duration timeElapsed = Duration.between(debut, fin);
        //System.out.println("we are here in testTime : "+timeElapsed.toMillis()+" milliseconds");
        return timeElapsed.toMillis();
    }

    public static long testTimeAverage(Runnable exp, int nbTry) {
        long average = 0;
        for (int i = 0; i < nbTry; i++) {
            average += testTime(exp);
            exp.reset();
        }

        return average / nbTry;
    }
}
