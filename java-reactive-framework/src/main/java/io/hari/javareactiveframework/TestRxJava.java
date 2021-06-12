package io.hari.javareactiveframework;

import io.hari.javareactiveframework.entity.Game;
import org.junit.Test;
import rx.Observable;

import java.util.List;

/**
 * @author Hariom Yadav
 * @since 12/06/21
 */
public class TestRxJava {
    public static final List<Game> GAMES_DB = List.of(
            Game.builder().name("game1").inventory(10).build(),
            Game.builder().name("game2").inventory(2).build(),
            Game.builder().name("game3").inventory(0).build(),
            Game.builder().name("game4").inventory(6).build());

    public static final List<Game> GAMES_DB_2 = List.of(
            Game.builder().name("game1").inventory(10).build(),
            Game.builder().name("game2").inventory(2).build(),
            Game.builder().name("game4").inventory(6).build());

    @Test
    public void testDataChannelAndErrorChannel() {
        Observable<Game> gameObservable = getGames(GAMES_DB);//one data throw exception
        gameObservable.subscribe(
                dataChannel -> System.out.println("DATA_CHANNEL : coming data from observable data channel : " + dataChannel),
                errorChannel -> System.out.println("ERROR CHANNEL : Error from error channel : " + errorChannel),
                () -> System.out.println("DONE CHANNEL : complete signal from Done channel : ")
        );
    }

    @Test
    public void testDataChannelAndDoneChannel() {
        Observable<Game> gameObservable = getGames(GAMES_DB_2);//all data are
        gameObservable.subscribe( //subscribing observable to access all 3 channels
                dataChannel -> System.out.println("DATA_CHANNEL : coming data from observable data channel : " + dataChannel),
                errorChannel -> System.out.println("ERROR CHANNEL : Error from error channel : " + errorChannel),
                () -> System.out.println("DONE CHANNEL : complete signal from Done channel : ")
        );
    }


    public Observable<Game> getGames(List<Game> gamesDb) {
        Observable<Game> observable = Observable.create(emitter -> {
            System.out.println("starting observable...");
            int i = 0;
            while (!emitter.isUnsubscribed() && i < gamesDb.size()) {
                Game fetchedGame = gamesDb.get(i++);
                if (fetchedGame.getInventory().equals(0))
                    emitter.onError(new RuntimeException("Inventory count 0")); //after this DATA CHANNEL will not send any data

                emitter.onNext(fetchedGame);//send data to DATA CHANNEL
            }
            System.out.println("ending observable...");
            emitter.onCompleted();//send DONE signal through DONE CHANNEL, n after this DATA CHANNEL will not send any daya
        });
        return observable;//any one can subscribe this observable
    }
}
