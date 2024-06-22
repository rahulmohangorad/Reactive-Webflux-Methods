package com.webflux.example.mehod.flux;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.context.Context;
import reactor.util.retry.Retry;

@Slf4j
public class SpringWebFluxReactiveStreamApiFluxExample {

    public static final String APPLE = "apple";
    public static final String BANANA = "banana";
    public static final String CHERRY = "cherry";
    public static final String INVALID = "invalid";

    /**
     * Creates a Flux that emits the given elements.
     */
    public static Flux<String> createFluxWithJust() {
        return Flux.just(APPLE, BANANA, CHERRY);
    }

    /**
     * Creates a Mono that emits the given element.
     */
    public static Mono<String> createMonoWithJust() {
        return Mono.just("hello");
    }

    /**
     * Creates a Flux from an Iterable.
     */
    public static Flux<String> createFluxFromIterable() {
        List<String> list = Arrays.asList("one", "two", "three");
        return Flux.fromIterable(list);
    }

    /**
     * Creates a Flux from an array of elements.
     */
    public static Flux<String> createFluxFromArray() {
        String[] array = {APPLE, BANANA, CHERRY};
        return Flux.fromArray(array);
    }

    /**
     * Creates a Flux that emits a range of sequential integers.
     */
    public static Flux<Integer> createFluxWithRange() {
        return Flux.range(1, 10);
    }

    /**
     * Creates a Flux that emits increasing long values at a fixed interval.
     */
    public static Flux<Long> createFluxWithInterval() {
        return Flux.interval(Duration.ofSeconds(1));
    }

    /**
     * Creates a Flux that allows you to push elements into it.
     */
    public static Flux<String> createFluxWithCreate() {
        return Flux.create(sink -> {
            sink.next(APPLE);
            sink.next(BANANA);
            sink.complete();
        });
    }

    /**
     * Creates a Mono that allows you to push a single element into it.
     */
    public static Mono<String> createMonoWithCreate() {
        return Mono.create(sink -> sink.success("hello"));
    }

    /**
     * Creates a Flux that defers the creation of the actual Flux until the time of subscription.
     */
    public static Flux<String> createFluxWithDefer() {
        return Flux.defer(() -> Flux.just(APPLE, BANANA, CHERRY));
    }


    /**
     * Concatenates the emissions of multiple Flux instances.
     */
    public static Flux<String> concatenateFluxes() {
        Flux<String> flux1 = Flux.just(APPLE, BANANA);
        Flux<String> flux2 = Flux.just(CHERRY, "date");
        return Flux.concat(flux1, flux2);
    }

    /**
     * Merges the emissions of multiple Flux instances.
     */
    public static Flux<String> mergeFluxes() {
        Flux<String> flux1 = Flux.just(APPLE, BANANA);
        Flux<String> flux2 = Flux.just(CHERRY, "date");
        return Flux.merge(flux1, flux2);
    }

    /**
     * Applies a function to pairs of elements from multiple Flux instances.
     */
    public static Flux<String> zipFluxes() {
        Flux<String> flux1 = Flux.just(APPLE, BANANA);
        Flux<String> flux2 = Flux.just(CHERRY, "date");
        return Flux.zip(flux1, flux2, (a, b) -> a + "-" + b);
    }

    /**
     * Switches to another Flux if the current Flux is empty.
     */
    public static Flux<String> switchIfEmptyFlux() {
        Flux<String> flux1 = Flux.empty();
        Flux<String> flux2 = Flux.just(APPLE, BANANA);
        return flux1.switchIfEmpty(flux2);
    }

    /**
     * Filters the elements emitted by a Flux.
     */
    public static Flux<Integer> filterFlux() {
        Flux<Integer> flux = Flux.range(1, 10);
        return flux.filter(i -> i % 2 == 0);
    }

    /**
     * Applies a function to each element emitted by a Flux.
     */
    public static Flux<String> mapFlux() {
        Flux<String> flux = Flux.just(APPLE, BANANA, CHERRY);
        return flux.map(String::toUpperCase);
    }

    /**
     * Transforms the elements emitted by a Flux into Monos or Fluxes.
     */
    public static Flux<String> flatMapFlux() {
        Flux<String> flux = Flux.just(APPLE, BANANA, CHERRY);
        return flux.flatMap(s -> Flux.just(s.toUpperCase(), s.toLowerCase()));
    }


    /**
     * Collects elements from a Flux into buffers.
     */
    public static Flux<List<String>> bufferFlux() {
        Flux<String> flux = Flux.just(APPLE, BANANA, CHERRY, "date", "elderberry");
        return flux.buffer(3);
    }

    /**
     * Emits Fluxes that represent slices of the source Flux.
     */
    public static Flux<Flux<String>> windowFlux() {
        Flux<String> flux = Flux.just(APPLE, BANANA, CHERRY, "date", "elderberry");
        return flux.window(3);
    }

    /**
     * Applies an accumulator function that emits a single value.
     */
    public static Mono<Integer> reduceFlux() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        return flux.reduce(0, Integer::sum);
    }

    /**
     * Applies an accumulator function and emits intermediate results.
     */
    public static Flux<Integer> scanFlux() {
        Flux<Integer> flux = Flux.just(1, 2, 3, 4, 5);
        return flux.scan(0, Integer::sum);
    }

    /**
     * Emits only the first n elements from a Flux.
     */
    public static Flux<String> takeFlux() {
        Flux<String> flux = Flux.just(APPLE, BANANA, CHERRY, "date");
        return flux.take(2);
    }

    /**
     * Skips the first n elements from a Flux.
     */
    public static Flux<String> skipFlux() {
        Flux<String> flux = Flux.just(APPLE, BANANA, CHERRY, "date");
        return flux.skip(2);
    }


    /**
     * Emits the last element from a Flux, or completes if the Flux is empty.
     */
    public static Mono<String> lastFlux() {
        Flux<String> flux = Flux.just(APPLE, BANANA, CHERRY);
        return flux.last();
    }

    /**
     * Emits the element at the specified index in the Flux.
     */
    public static Mono<String> elementAtFlux() {
        Flux<String> flux = Flux.just(APPLE, BANANA, CHERRY);
        return flux.elementAt(1);
    }

    /**
     * Creates a Flux that emits an error signal.
     */
    public static Flux<String> errorFlux() {
        return Flux.error(new IllegalArgumentException("Invalid argument"));
    }

    /**
     * Specifies a fallback value to be emitted if an error occurs.
     */
    public static Flux<String> onErrorReturnFlux() {
        return Flux.just(APPLE, BANANA)
                .concatWith(Flux.error(new IllegalArgumentException()))
                .onErrorReturn("fallback");
    }

    /**
     * Specifies a fallback Flux to be used if an error occurs.
     */
    public static Flux<String> onErrorResumeFlux() {
        return Flux.just(APPLE, BANANA)
                .concatWith(Flux.error(new IllegalArgumentException()))
                .onErrorResume(e -> Flux.just("fallback"));
    }

    /**
     * Handles errors by continuing the Flux with a fallback element.
     */
    public static Flux<String> onErrorContinueFlux() {
        return Flux.just(APPLE, BANANA, INVALID)
                .map(s -> {
                    if (s.equals(INVALID)) {
                        throw new IllegalArgumentException();
                    }
                    return s;
                })
                .onErrorContinue((e, v) -> log.debug("Error for value: {}" ,v));
    }

    /**
     * Retries a failed Flux a specified number of times.
     */
    public static Flux<String> retryFlux() {
        return Flux.just(APPLE, BANANA)
                .concatWith(Flux.error(new IllegalArgumentException()))
                .retry(2);
    }

    /**
     * Retries a failed Flux based on a custom retry strategy.
     */
    public static Flux<String> retryWhenFlux() {
        return Flux.just(APPLE, BANANA)
                .concatWith(Flux.error(new IllegalArgumentException()))
                .retryWhen(Retry.max(2).filter(e -> e instanceof IllegalArgumentException));
    }

    /**
     * Applies a timeout to each element emitted by a Flux.
     */
    public static Flux<String> timeoutFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .delayElements(Duration.ofSeconds(1))
                .timeout(Duration.ofSeconds(2));
    }

    /**
     * Executes a side-effect function for each element emitted by a Flux.
     */
    public static Flux<String> doOnNextFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .doOnNext(log::debug);
    }

    /**
     * Executes a side-effect function when an error occurs in a Flux.
     */
    public static Flux<String> doOnErrorFlux() {
        return Flux.just(APPLE, BANANA, INVALID)
                .map(s -> {
                    if (s.equals(INVALID)) {
                        throw new IllegalArgumentException();
                    }
                    return s;
                })
                .doOnError(e -> log.debug("Error: {}" , e.getMessage()));
    }

    /**
     * Executes a side-effect function when a Flux completes normally.
     */
    public static Flux<String> doOnCompleteFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .doOnComplete(() -> log.debug("Flux completed"));
    }

    /**
     * Executes a side-effect function when a subscriber subscribes to a Flux.
     */
    public static Flux<String> doOnSubscribeFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .doOnSubscribe(subscription -> log.debug("Subscribed"));
    }

    /**
     * Executes a side-effect function when a subscriber requests elements from a Flux.
     */
    public static Flux<String> doOnRequestFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .doOnRequest(n -> log.debug("Requested {} elements",n));
    }

    /**
     * Executes a side-effect function when a subscriber cancels a Flux.
     */
    public static Flux<String> doOnCancelFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .doOnCancel(() -> log.debug("Flux cancelled"));
    }

    /**
     * Executes a side-effect function when a Flux terminates, regardless of the outcome.
     */
    public static Flux<String> doFinallyFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .doFinally(signalType ->log.debug("Flux terminated: {}" , signalType));
    }

    /**
     * Logs all the signals emitted by a Flux.
     */
    public static Flux<String> logFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .log();
    }

    /**
     * Caches the elements emitted by a Flux and replays them to new subscribers.
     */
    public static Flux<String> cacheFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .cache();
    }

    /**
     * Adds a checkpoint to a Flux, which can be used for debugging purposes.
     */
    public static Flux<String> checkpointFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .checkpoint("Checkpoint 1");
    }

    /**
     * Writes a key-value pair to the reactive context.
     */
    public static Flux<String> contextWriteFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .contextWrite(Context.of("key", "value"));
    }

    /**
     * Reads a value from the reactive context.
     */
    public static Flux<String> contextReadFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .contextWrite(Context.of("key", "value")).contextCapture();
    }

    /**
     * Delays the emissions of a Flux by a specified duration.
     */
    public static Flux<String> delayFlux() {
        return Flux.just(APPLE, BANANA, CHERRY)
                .delayElements(Duration.ofSeconds(1));
    }

}