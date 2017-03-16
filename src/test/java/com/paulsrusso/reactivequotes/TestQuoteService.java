package com.paulsrusso.reactivequotes;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.paulsrusso.reactivequotes.model.IntradayQuote;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestQuoteService {

   @Autowired
   private QuoteService _quoteService;
   
   @Test
   public void testQuoteRetrieval() {
      Assert.assertNotNull(_quoteService);
      IntradayQuote quote = _quoteService.collect("QQQ");
      Assert.assertNotNull(quote);
      Assert.assertEquals("QQQ", quote.getTicker());
   }

}
