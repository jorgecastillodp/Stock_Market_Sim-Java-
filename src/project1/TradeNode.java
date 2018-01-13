/*
Author: Jorge Castillo
Program: StockMarket_Sim (Project1)
*/
package project1;

public class TradeNode 
{
    String stockSymbol;
    int shareCount;
    boolean buy;
    TradeNode nextTradeNode;
    
    TradeNode(){};
    
    TradeNode (String sStockSymbol, int iPurchase, boolean bBuy)
    {
        this.stockSymbol = sStockSymbol;
        this.shareCount = iPurchase;
        this.buy = bBuy;
        
    }
}