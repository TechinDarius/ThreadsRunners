import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    public static void main(String[] args) throws InterruptedException {

//        Thread safety task
//        Generate every runner’s starting number
//        Use ‘setStartingNumber’ method on runner to set generated value
//        Starting number should be unique, and between 1 and 50
//        Each starting number should be generated within same thread where
//        runner detailed information is fetched
//        New list should be created with all runners and their information
//        Runners should be printed in starting number order

//        long startTime = System.currentTimeMillis();
//
//        Set<String> runnersIds = Runner.getAllRunnersIDs();
//        List<Runner> runners = Collections.synchronizedList(new ArrayList<>());
//        AtomicInteger startingOrderCounter = new AtomicInteger();
//
//        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
//            runnersIds.forEach(runnerId -> executorService.submit(() -> {
//                try {
//                    Runner runner = Runner.getRunnerById(runnerId);
//                    runner.setStartingNumber(startingOrderCounter.incrementAndGet());
//                    runners.add(runner);
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//                executorService.shutdown();
//            }));
//        }
//
//        runners.sort(Comparator.comparing(Runner::getStartingNumber));
//        System.out.println(runners);
//        //System.out.println("Execution time: " + (System.currentTimeMillis() - startTime));
//        System.out.println(STR."Execution time: \{System.currentTimeMillis() - startTime}");


        //   }

        //with builder:

        long startTime = System.currentTimeMillis();

        Set<String> runnersIds = Runner.getAllRunnersIDs();
        List<Runner> runners = Collections.synchronizedList(new ArrayList<>());
        AtomicInteger startingOrderCounter = new AtomicInteger();

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
            runnersIds.forEach(runnerId -> executorService.submit(() -> {
                try {
                    Runner runner = Runner.Builder.builder()
                            .withName(Runner.getRunnerById(runnerId).getName())
                            .withPersonalId(runnerId)
                            .withStartingNumber(startingOrderCounter.incrementAndGet())
                            .build();

                    runners.add(runner);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                executorService.shutdown();
            }));
        }

        runners.sort(Comparator.comparing(Runner::getStartingNumber));
        for (Runner runner : runners) {
            System.out.println(runner);
        }

        System.out.println("Execution time: " + (System.currentTimeMillis() - startTime));
    }
}

////Thread task

//Print all runner's name
//Program should print its total execution time
//Program preferably should finish in less than 5s (maximum allowed
//execution time is 10s)

//    public static void main(String[] args) throws InterruptedException {
//        long startTime = System.currentTimeMillis();
//
//        Set<String> runnersIds = Runner.getAllRunnersIDs();
//
//        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {
//            runnersIds.forEach(runnerId -> executorService.submit(() -> {
//                try {
//                    System.out.println(Runner.getRunnerById(runnerId).getName());
//                } catch (InterruptedException e) {
//                    throw new RuntimeException(e);
//                }
//            }));
//            //System.out.println("Execution time: " + (System.currentTimeMillis() - startTime));
//            System.out.println(STR."Execution time: \{System.currentTimeMillis() - startTime}"); //same line jus optimised by intelliJ
//
//        }
//        System.out.println("---------------------------\n");
//        System.out.println("done!");
//    }


