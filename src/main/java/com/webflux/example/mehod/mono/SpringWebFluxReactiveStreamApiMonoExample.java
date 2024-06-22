package com.webflux.example.mehod.mono;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.function.Tuple2;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

@Slf4j
public class SpringWebFluxReactiveStreamApiMonoExample {

    public static final String HELLO = "hello";
    public static final String APPLE = "apple";

    /**
     * Creates a Mono that emits the given element.
     */
    public static Mono<String> createMonoWithJust() {
        return Mono.just(HELLO);
    }

    /**
     * Creates a Mono from a Callable.
     */
    public static Mono<String> createMonoWithFromCallable() {
        Callable<String> callable = () -> HELLO;
        return Mono.fromCallable(callable);
    }

    /**
     * Creates a Mono from a Supplier.
     */
    public static Mono<String> createMonoWithFromSupplier() {
        Supplier<String> supplier = () -> HELLO;
        return Mono.fromSupplier(supplier);
    }


    /**
     * Creates a Mono that emits an error signal.
     */
    public static Mono<String> errorMono() {
        return Mono.error(new IllegalArgumentException("Invalid argument"));
    }

    /**
     * Specifies a fallback value to be emitted if an error occurs.
     */
    public static Mono<Object> onErrorReturnMono() {
        return Mono.just(APPLE)
                .flatMap(s -> Mono.error(new IllegalArgumentException()))
                .onErrorReturn("fallback");
    }

    /**
     * Specifies a fallback Mono to be used if an error occurs.
     */
    public static Mono<Object> onErrorResumeMono() {
        return Mono.just(APPLE)
                .flatMap(s -> Mono.error(new IllegalArgumentException()))
                .onErrorResume(e -> Mono.just("fallback"));
    }

    /**
     * Retries a failed Mono a specified number of times.
     */
    public static Mono<Object> retryMono() {
        return Mono.just(APPLE)
                .flatMap(s -> Mono.error(new IllegalArgumentException()))
                .retry(2);
    }

    /**
     * Retries a failed Mono based on a custom retry strategy.
     */
    public static Mono<Object> retryWhenMono() {
        return Mono.just(APPLE)
                .flatMap(s -> Mono.error(new IllegalArgumentException()))
                .retryWhen(Retry.max(2).filter(e -> e instanceof IllegalArgumentException));
    }

    /**
     * Applies a timeout to the Mono.
     */
    public static Mono<String> timeoutMono() {
        return Mono.just(APPLE)
                .delayElement(Duration.ofSeconds(1))
                .timeout(Duration.ofSeconds(2));
    }

    /**
     * Executes a side-effect function when an error occurs in a Mono.
     */
    public static Mono<Object> doOnErrorMono() {
        return Mono.just(APPLE)
                .flatMap(s -> Mono.error(new IllegalArgumentException()))
                .doOnError(e -> log.debug("Error: {}",e.getMessage()));
    }

    /**
     * Executes a side-effect function when a Mono completes normally.
     */
    public static Mono<String> doOnCompleteMono() {
        return Mono.just(APPLE).doOnSuccess(log::debug);
    }

    /**
     * Executes a side-effect function when a subscriber subscribes to a Mono.
     */
    public static Mono<String> doOnSubscribeMono() {
        return Mono.just(APPLE)
                .doOnSubscribe(subscription -> log.debug("Subscribed"));
    }

    /**
     * Executes a side-effect function when a subscriber requests elements from a Mono.
     */
    public static Mono<String> doOnRequestMono() {
        return Mono.just(APPLE)
                .doOnRequest(n -> log.debug("Requested  {} elements",n));
    }

    /**
     * Executes a side-effect function when a subscriber cancels a Mono.
     */
    public static Mono<String> doOnCancelMono() {
        return Mono.just(APPLE)
                .doOnCancel(() -> log.debug("Mono cancelled"));
    }

    /**
     * Executes a side-effect function when a Mono terminates, regardless of the outcome.
     */
    public static Mono<String> doFinallyMono() {
        return Mono.just(APPLE)
                .doFinally(signalType -> log.debug("Mono terminated: {}", signalType));
    }

    /**
     * Logs all the signals emitted by a Mono.
     */
    public static Mono<String> logMono() {
        return Mono.just(APPLE)
                .log();
    }

    /**
     * Caches the element emitted by a Mono and replays it to new subscribers.
     */
    public static Mono<String> cacheMono() {
        return Mono.just(APPLE)
                .cache();
    }

    /**
     * Adds a checkpoint to a Mono, which can be used for debugging purposes.
     */
    public static Mono<String> checkpointMono() {
        return Mono.just(APPLE)
                .checkpoint("Checkpoint 1");
    }

    /**
     * Writes a key-value pair to the reactive context.
     */
    public static Mono<String> contextWriteMono() {
        return Mono.just(APPLE)
                .contextWrite(Context.of("key", "value"));
    }

    /**
     * Delays the emission of the Mono by a specified duration.
     */
    public static Mono<String> delayMono() {
        return Mono.just(APPLE)
                .delayElement(Duration.ofSeconds(1));
    }

    /**
     * Concatenates the emission of two Mono instances.
     */
    public static Mono<Tuple2<String, String>> concatMono() {
        Mono<String> mono1 = Mono.just(HELLO);
        Mono<String> mono2 = Mono.just("world");
        return Mono.zip(mono1,mono2);
    }


    /**
     * Applies a function to the element emitted by a Mono.
     */
    public static Mono<String> mapMono() {
        Mono<String> mono = Mono.just(HELLO);
        return mono.map(String::toUpperCase);
    }

    /**
     * Transforms the element emitted by a Mono into another Mono.
     */
    public static Mono<String> flatMapMono() {
        Mono<String> mono = Mono.just(HELLO);
        return mono.flatMap(s -> Mono.just(s.toUpperCase()));
    }

    /**
     * Filters the element emitted by a Mono.
     */
    public static Mono<String> filterMono() {
        Mono<String> mono = Mono.just(HELLO);
        return mono.filter(s -> s.length() > 3);
    }

    /**
     * Switches to another Mono if the current Mono is empty.
     */
    public static Mono<String> switchIfEmptyMono() {
        Mono<String> mono1 = Mono.empty();
        Mono<String> mono2 = Mono.just(HELLO);
        return mono1.switchIfEmpty(mono2);
    }
}