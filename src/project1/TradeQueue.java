/*
Author: Jorge Castillo
Program: StockMarket_Sim (Project1)
*/
package project1;


public class TradeQueue 
{
    TradeNode headTradeNode;
    TradeNode tailTradeNode;
    
    public void Enqueue(String sStockSymbol, int iPurchaseShareCount,boolean bBuy) 
    {
      TradeNode temp;
      temp = new TradeNode(sStockSymbol, iPurchaseShareCount, bBuy);
      
      if (headTradeNode == null)
      {
          headTradeNode = temp;
          tailTradeNode = temp;
      }
        else
      {
          tailTradeNode.nextTradeNode = temp;
          tailTradeNode = tailTradeNode.nextTradeNode; 
      }
    }
    public TradeNode Dequeue ()
    {
       TradeNode temp = headTradeNode;
       
       if (headTradeNode != null)
       {
           headTradeNode = headTradeNode.nextTradeNode;
       }
       
       return temp;
    }
}