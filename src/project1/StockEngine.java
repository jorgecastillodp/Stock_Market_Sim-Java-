/*
Author: Jorge Castillo
Program: StockMarket_Sim (Project1)
*/
package project1;
import java.security.SecureRandom;

public class StockEngine 
{
    SecureRandom oRand;
    Stock [] aStock;
    public StockEngine ()
    {
        aStock = new Stock [3];
        // random object
        oRand = new SecureRandom();
        // The 3 stocks with symbol and prices.
        aStock[0] = new Stock();
        aStock[0].stockSymbol = "AAPL";
        aStock[0].pricePerShare = oRand.nextInt(30);
        
        aStock[1] = new Stock();
        aStock[1].stockSymbol = "Yhoo";
        aStock[1].pricePerShare = oRand.nextInt(30);
        
        aStock[2] = new Stock();
        aStock[2].stockSymbol = "googl";
        aStock[2].pricePerShare = oRand.nextInt(30); 
    }   
    
    public void cycleTurn()
            {
                int iStockMoveLimit = 3;
                int iStockMove;
                int iTrendChance = 50;
                
                SecureRandom oRandSecure = new SecureRandom();
    for (Stock stock : aStock) 
        {
        
            iStockMove = oRandSecure.nextInt ( iStockMoveLimit + 1);
            
            if (oRandSecure.nextInt(100) < iTrendChance) 
            {
                if (!stock.lastMoveUp) 
                {
                    stock.pricePerShare -= oRandSecure.nextInt(3);
                }
                if (stock.lastMoveUp) 
                {
                    stock.pricePerShare += oRandSecure.nextInt(3);
                }
            }
            else 
            {
                if (stock.lastMoveUp) 
                {
                    stock.pricePerShare -= oRandSecure.nextInt(3);
                    stock.lastMoveUp = !stock.lastMoveUp;
                }
                 if (!stock.lastMoveUp) 
                 {
                    stock.pricePerShare += oRandSecure.nextInt(3);
                    stock.lastMoveUp = !stock.lastMoveUp;
                }
            }
        }
    }
}

