package com.homeAutomation.extension.database.thread;

import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.infrastructure.Infrastructure;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Worker {
    private static final Duration MAX_OPERATION_TIME = Duration.ofSeconds(5);

    public static void runOperations(Runnable... runnableOperations) {
        List<Uni<Void>> operations = Arrays.stream(runnableOperations).map(runnable -> Uni.createFrom().voidItem()
                        .invoke(runnable).runSubscriptionOn(Infrastructure.getDefaultExecutor()))
                .collect(Collectors.toList());
        Uni.combine().all().unis(operations).discardItems().await().atMost(MAX_OPERATION_TIME);
    }
}
