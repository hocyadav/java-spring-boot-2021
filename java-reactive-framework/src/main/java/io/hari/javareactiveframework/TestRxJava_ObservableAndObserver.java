package io.hari.javareactiveframework;

import io.hari.javareactiveframework.entity.Game;
import org.junit.Test;
import rx.Observable;
import rx.Observer;

import java.util.List;

/**
 * @author Hariom Yadav
 * @since 12/06/21
 */
public class TestRxJava_ObservableAndObserver {
    public static final List<Game> GAMES_DB = List.of(
            Game.builder().name("game1").inventory(10).build(),
            Game.builder().name("game2").inventory(2).build(),
            Game.builder().name("game3").inventory(0).build(),
            Game.builder().name("game4").inventory(6).build());

    public static final List<Game> GAMES_DB_2 = List.of(
            Game.builder().name("game1").inventory(10).build(),
            Game.builder().name("game2").inventory(2).build(),
            Game.builder().name("game4").inventory(6).build());

    public Observable<Game> getGamesDataStreamOrSimpleDataFromGameService(List<Game> gamesDb) {
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


    //todo : subscribe observable + access all 3 channel
    @Test
    public void testDataChannelAndErrorChannel() {
        Observable<Game> gameObservable = getGamesDataStreamOrSimpleDataFromGameService(GAMES_DB);//one data will throw exception
        gameObservable.subscribe(
                dataChannel -> System.out.println("DATA_CHANNEL : coming data from observable data channel : " + dataChannel),
                errorChannel -> System.out.println("ERROR CHANNEL : Error from error channel : " + errorChannel),
                () -> System.out.println("DONE CHANNEL : complete signal from Done channel : ")
        );
    }
    /** output
     starting observable...
     DATA_CHANNEL : coming data from observable data channel : Game(name=game1, inventory=10)
     DATA_CHANNEL : coming data from observable data channel : Game(name=game2, inventory=2)
     ERROR CHANNEL : Error from error channel : java.lang.RuntimeException: Inventory count 0
     ending observable...
     */


    @Test
    public void testDataChannelAndDoneChannel() {
        Observable<Game> gameObservable = getGamesDataStreamOrSimpleDataFromGameService(GAMES_DB_2);//all data are correct, no exception
        gameObservable.subscribe( //subscribing observable to access all 3 channels
                dataChannel -> System.out.println("DATA_CHANNEL : coming data from observable data channel : " + dataChannel),
                errorChannel -> System.out.println("ERROR CHANNEL : Error from error channel : " + errorChannel),
                () -> System.out.println("DONE CHANNEL : complete signal from Done channel : ")
        );
    }
    /**
     starting observable...
     DATA_CHANNEL : coming data from observable data channel : Game(name=game1, inventory=10)
     DATA_CHANNEL : coming data from observable data channel : Game(name=game2, inventory=2)
     DATA_CHANNEL : coming data from observable data channel : Game(name=game4, inventory=6)
     ending observable...
     DONE CHANNEL : complete signal from Done channel :
     */


    //todo : subscribe observable using Observer instance + access all 3 channel
    //this Observer instance contain all 3 channel impl
    @Test
    public void testObserverInterface1() {
        Observable<Game> gameObservable = getGamesDataStreamOrSimpleDataFromGameService(GAMES_DB);
        Observer<Game> observer = new Observer<>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(Game game) {
            }
        };
        gameObservable.subscribe(observer);
    }
    /**
     starting observable...
     ending observable...
     */

    @Test
    public void testObserverInterface2() {
        Observable<Game> gameObservable = getGamesDataStreamOrSimpleDataFromGameService(GAMES_DB);//output same as above 1st test
//        Observable<Game> gameObservable = getGames(GAMES_DB_2);//output same as above 2nd test
        Observer<Game> observer = new Observer<>() {
            @Override
            public void onCompleted() {
                System.out.println("DONE CHANNEL : complete signal from Done channel : ");
            }
            @Override
            public void onError(Throwable throwable) {
                System.out.println("ERROR CHANNEL : Error from error channel : " + throwable);
            }
            @Override
            public void onNext(Game game) {
                System.out.println("DATA_CHANNEL : coming data from observable data channel : " + game);
            }
        };
        gameObservable.subscribe(observer);
    }

    /**
     starting observable...
     DATA_CHANNEL : coming data from observable data channel : Game(name=game1, inventory=10)
     DATA_CHANNEL : coming data from observable data channel : Game(name=game2, inventory=2)
     ERROR CHANNEL : Error from error channel : java.lang.RuntimeException: Inventory count 0
     ending observable...
     */


    @Test //impl pending
    public void testSendUnSubscribeSignalToObservable() {
        Observable<Game> gameObservable = getGamesDataStreamOrSimpleDataFromGameService(GAMES_DB);
        Observer<Game> observer = new Observer<>() {
            @Override
            public void onCompleted() {
            }
            @Override
            public void onError(Throwable throwable) {
            }
            @Override
            public void onNext(Game game) {
                if (game.getName().equals("game2")) {
                    //todo impl : send unsubscribe signal to observable
                }
                System.out.println("DATA_CHANNEL : coming data from observable data channel : " + game);
            }
        };
        gameObservable.subscribe(observer);
    }
}
