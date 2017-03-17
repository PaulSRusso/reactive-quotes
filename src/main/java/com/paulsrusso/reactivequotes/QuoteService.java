package com.paulsrusso.reactivequotes;

import java.net.URI;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.paulsrusso.reactivequotes.model.IntradayQuote;
import com.paulsrusso.reactivequotes.model.IntradayQuoteIn;

/**
 * @author <a href="mailto:paul.russo@jchart.com>Paul Russo</a>
 * @since Mar 15, 2017
 */
@Service
public class QuoteService {
   
   private static final Logger log = LoggerFactory
         .getLogger(QuoteService.class);

   @Value("${quote.baseurl}")
   private String _quoteBaseUrl;

   private ObjectMapper _mapper = new ObjectMapper();
   
   public IntradayQuote collect(String ticker) {
      IntradayQuote retval = null; 
      UriComponentsBuilder builder = UriComponentsBuilder
            .fromHttpUrl(_quoteBaseUrl)
            .queryParam("q", ticker);
      try {
         RestTemplate restTemplate = new RestTemplate();
         RequestEntity<?> request = new RequestEntity<>(HttpMethod.GET, new URI(builder.toUriString()));
         ResponseEntity<String> responseEntity = restTemplate.exchange(request,String.class);
         String body = responseEntity.getBody();
         log.debug(body);
         if (body != null) {
            body = body.substring(3);
            List<IntradayQuoteIn> quotes = _mapper.readValue(body,new TypeReference<List<IntradayQuoteIn>>(){});
            if (!quotes.isEmpty()) {
               IntradayQuoteIn quoteIn = quotes.get(0);
               retval = quoteIn.getIntradayQuote();
               log.debug(retval.toString());
            } else {
               log.warn("ticker :" + ticker + " was not found");
            }
         }
      } catch (Exception e) {
         retval = new IntradayQuote();
         retval.setTicker("NOT FOUND");
         log.error(e.toString());
      }
      return retval;
   }

}
