package com.paulsrusso.reactivequotes.model;

/**
 * @author <a href="mailto:paul.russo@jchart.com>Paul Russo</a>
 * @since Mar 2, 2017
 */
public class IntradayQuote {
 
   private String ticker;
   private String last;
   private String time;
   private String change;
   private String pctChange;

   public String getTicker() {
      return ticker;
   }

   public void setTicker(String ticker) {
      this.ticker = ticker;
   }

   public String getLast() {
      return last;
   }

   public void setLast(String last) {
      this.last = last;
   }

   public String getTime() {
      return time;
   }

   public void setTime(String time) {
      this.time = time;
   }

   public String getChange() {
      return change;
   }

   public void setChange(String change) {
      this.change = change;
   }

   public String getPctChange() {
      return pctChange;
   }

   public void setPctChange(String pctChange) {
      this.pctChange = pctChange;
   }

   @Override
   public String toString() {
      return "IntradayQuote [ticker=" + ticker + ", last=" + last + ", time="
            + time + ", change=" + change + ", pctChange=" + pctChange + "]";
   }
     

}
