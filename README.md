# Reactive Quotes
This project uses the [Spring Boot Reactive WebFlux Framework](http://docs.spring.io/spring-framework/docs/5.0.0.BUILD-SNAPSHOT/spring-framework-reference/html/web-reactive.html) in Spring 5 to stream intraday stock quotes.  
* Twitter: [@PaulSRusso](https://twitter.com/@PaulSRusso)
* View [My Projects](https://paulsrusso.github.io)

### Commands
```ShellSession
git clone https://github.com/PaulSRusso/reactive-quotes.git  
cd ./reactive-quotes
./gradlew bootrun
```
In a separate terminal
```ShellSession
curl http://localhost:8080/quotes/QQQ
```

The number of seconds between quotes returned can be configured in application.yml. The default is 5 seconds.
```Java
@RestController
public class QuoteController {

   @Autowired
   private QuoteService _quoteService;

   @Value("${interval.seconds}")
   private Integer _seconds;

   
   @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/quotes/{ticker}" )
   Flux<IntradayQuote> quotes(
         @PathVariable String ticker) {
    
      // create two Fluxes; one for the quotes and one for the duration 
      Flux<IntradayQuote> retval = null;
      Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(_seconds));

      // generate a Stream from a List and turn it into a FLux
      Flux<IntradayQuote> quotes = Flux.fromStream(Stream.generate(() -> _quoteService.collect(ticker)));

      // merge the Fluxes into a single Flux and return it  
      retval = Flux.zip(quotes, durationFlux).map(Tuple2::getT1);
     
   }

```



