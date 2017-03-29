package com.paulsrusso.reactivequotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author <a href="mailto:paul.russo@jchart.com>Paul Russo</a>
 * @since Mar 1, 2017
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IntradayQuoteIn {
   
   private IntradayQuote intradayQuote = new IntradayQuote();
 
   @JsonProperty("t")
   private String ticker;

   @JsonProperty("l")
   private String last;
   
   @JsonProperty("lt_dts")
   private String time;
   
   @JsonProperty("c")
   private String change;

   @JsonProperty("cp")
   private String pctChange;

   public String getTicker() {
      return ticker;
   }

   public void setTicker(String ticker) {
      this.intradayQuote.setTicker(ticker);
   }

   public void setLast(String last) {
      this.intradayQuote.setLast(last);
   }

   public void setTime(String time) {
      this.intradayQuote.setTime(time);
   }

   public void setChange(String change) {
      this.intradayQuote.setChange(change);
   }

   public void setPctChange(String pctChange) {
      this.intradayQuote.setPctChange(pctChange);
   }

   public IntradayQuote getIntradayQuote() {
      return intradayQuote;
   }

   @Override
   public String toString() {
      return "IntradayQuoteIn [intradayQuote=" + intradayQuote + ", ticker="
            + ticker + ", last=" + last + ", time=" + time + ", change="
            + change + ", pctChange=" + pctChange + "]";
   }

}
