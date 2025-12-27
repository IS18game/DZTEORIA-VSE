package org.example;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<String> urls = Arrays.asList(
                "https://catfact.ninja/fact",
                "https://official-joke-api.appspot.com/random_joke",
                "https://randomuser.me/api/",
                "https://api.adviceslip.com/advice",
                "https://dog.ceo/api/breeds/image/random",
                "https://api.open-meteo.com/v1/forecast?latitude=35&longitude=139&hourly=temperature_2m",
                "https://www.boredapi.com/api/activity",
                "https://api.ipify.org?format=json",
                "https://api.agify.io/?name=lucas",
                "https://api.genderize.io/?name=lucas",
                "https://api.nationalize.io/?name=lucas",
                "https://api.chucknorris.io/jokes/random",
                "https://api.publicapis.org/entries",
                "https://api.spacexdata.com/v4/launches/latest",
                "https://api.coindesk.com/v1/bpi/currentprice.json",
                "https://api.quotable.io/random",
                "https://uselessfacts.jsph.pl/random.json?language=en",
                "https://randomfox.ca/floof/",
                "https://random-d.uk/api/v2/random",
                "https://some-random-api.ml/img/owl"
        );

        int poolSize = 5;
        ExecutorService executor = new ThreadPoolExecutor(
                poolSize,
                poolSize,
                0L,
                TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<>()
        );

        for (String url : urls) {
            executor.submit(new UrlChecker(url));
        }

        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }
}