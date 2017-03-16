package com.paulsrusso.reactivequotes;

import java.time.Duration;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.paulsrusso.reactivequotes.model.IntradayQuote;

import reactor.core.publisher.Flux;
import reactor.util.function.Tuple2;

/**
 * @author <a href="mailto:paul.russo@jchart.com>Paul Russo</a>
 * @since Mar 15, 2017
 */
@RestController
public class QuoteController {

   @Autowired
   private QuoteService _quoteService;

   @Value("${interval.seconds}")
   private Integer _seconds;

   
   @GetMapping(produces = MediaType.TEXT_EVENT_STREAM_VALUE, value = "/quotes/{ticker}" )
   Flux<IntradayQuote> quotes(
         @PathVariable String ticker) {
    
      Flux<IntradayQuote> retval = null;
      Flux<Long> durationFlux = Flux.interval(Duration.ofSeconds(_seconds));
      Flux<IntradayQuote> quotes = Flux.fromStream(Stream.generate(() -> _quoteService.collect(ticker)));
      retval = Flux.zip(quotes, durationFlux).map(Tuple2::getT1);
     
      return retval;
      
   }

}
