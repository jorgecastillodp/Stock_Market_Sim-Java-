/*
Author: Jorge Castillo
Program: StockMarket_Sim (Project1)
*/
package project1;
import java.util.Scanner;
public class StockMarketSim 
{
    TradingAccount Account; 
    TradeQueue Queue;
    StockEngine Engine;
    
    public void Start()
    {
        // Person's account
        Account = new TradingAccount();
        Account.balance = 2000;
        Account.username = "John";
        Engine = new StockEngine();
        Queue = new TradeQueue();
        Account.aStockPosition = new StockPosition[3];
        
        //Apple stock
        Account.aStockPosition[0] = new StockPosition();
        Account.aStockPosition[0].shareCount = 0;
        Account.aStockPosition[0].stockSymbol =  "AAPL";
        
        // Yahoo Stock
        Account.aStockPosition[1] = new StockPosition();
        Account.aStockPosition[1].shareCount = 0;
        Account.aStockPosition[1].stockSymbol =  "Yhoo";
        
        //Google Stock
        Account.aStockPosition[2] = new StockPosition();
        Account.aStockPosition[2].shareCount = 0;
        Account.aStockPosition[2].stockSymbol =  "Googl";
        
        RunUI();
    }
    
    public void RunUI()
    {
        Scanner Userinput = new Scanner(System.in);
        String input = "";
        int Answer;
        final String EXIT = "Exit";
        do
        {
            System.out.println("Enter option or \"" + EXIT + "\" to quit." );
            System.out.println("1. Account Status \n" +
                               "2. Trade Stock \n" +
                               "3. Cycle Stock Market");
            input =  Userinput.nextLine();
            
            switch (input)
            {
                case "1":
                    System.out.println(Account.username);
                    System.out.print("$"+ Account.balance + " dollars\n");
                    
                    for(int x = 0; x < Account.aStockPosition.length; x++)
                    {
                        System.out.println(Account.aStockPosition[x].stockSymbol +
                                          "\n" + Account.aStockPosition[x].shareCount);
                    }
             break;       
             
                case "2":
                    System.out.println("Which stock would you like to buy or sell? (" 
                        + Account.aStockPosition[0].stockSymbol + ", "
                        + Account.aStockPosition[1].stockSymbol + ", "
                        + Account.aStockPosition[2].stockSymbol + ")");
                    input = Userinput.next();
                    
                if (input.equals(Account.aStockPosition[0].stockSymbol))
                {
                    System.out.println("How many shares? ");
                    Answer = Userinput.nextInt();
                    System.out.println("Buy or sell? ");
                    input = Userinput.next();
                    
                    if (input.toLowerCase().equals("buy"))
                    {
                        Queue.Enqueue(Account.aStockPosition[0].stockSymbol, Answer, true);
                    }
                    else if (input.equalsIgnoreCase("sell"))
                    {
                        Queue.Enqueue(Account.aStockPosition[0].stockSymbol, Answer, false);
                    }
                    else
                    {
                        System.out.println("Error: Wrong selection! Returning to menu. ");
                    }
                }
                else if (input.equalsIgnoreCase(Account.aStockPosition[1].stockSymbol))
                {
                    System.out.println("How many shares? ");
                    Answer = Userinput.nextInt();
                    System.out.println("Buy or sell?");
                    input = Userinput.next();
                    if (input.equalsIgnoreCase("buy"))
                    {
                        Queue.Enqueue(Account.aStockPosition[1].stockSymbol, Answer, true);
                    }
                    else if (input.equalsIgnoreCase("sell"))
                    {
                        Queue.Enqueue(Account.aStockPosition[1].stockSymbol, Answer, false);
                    }
                    else
                    {
                        System.out.println("Error: Wrong selection! Returning to menu.");
                    }
                }
                else if (input.equalsIgnoreCase(Account.aStockPosition[2].stockSymbol))
                {
                    System.out.println("How many shares?");
                    Answer = Userinput.nextInt();
                    System.out.println("Buy or sell ?");
                    input = Userinput.next();
                    if (input.equalsIgnoreCase("buy"))
                    {
                        Queue.Enqueue(Account.aStockPosition[2].stockSymbol, Answer, true);
                    }
                    else if (input.equalsIgnoreCase("sell"))
                    {
                        Queue.Enqueue(Account.aStockPosition[2].stockSymbol, Answer, false);
                    }
                    else
                    {
                        System.out.println("Error: Wrong selection! Returning to menu.");
                    }
                }
                else
                {
                    System.out.println("Error: Wrong selection! Returning to menu.");
                }
                break;
                
                case "3":
                    Engine.cycleTurn();
                    ProcessTrades();
                    break;
                    
                case EXIT:
                break;
                
                default :
                System.out.println("Error: not a valit input sorry.");
            }
        }
        while((!(input.equals(EXIT))) && Account.balance > 0);
        System.out.printf("Balance: ", Account.balance); 
    }
    
    public void ProcessTrades()
    {
         while(!(Queue.headTradeNode == null))
        {
            int temp = 0;
            int temp1 = 0;
            for(int i = 0; i < Account.aStockPosition.length; i++)
            {
                // compare stock simbols
                if(Queue.headTradeNode.stockSymbol.equals(Account.aStockPosition[i].stockSymbol)) 
                {
                    temp = i;
                }
            }
            for(int i = 0; i < Engine.aStock.length; i++) 
            {
                if(Queue.headTradeNode.stockSymbol.equals(Engine.aStock[i].stockSymbol))
                {
                    temp1 = i;
                }
            }
            if (Queue.headTradeNode.buy == true)
            {
                Account.balance -= Queue.headTradeNode.shareCount * Engine.aStock[temp1].pricePerShare;
                Account.aStockPosition[temp].shareCount += Queue.headTradeNode.shareCount;
            }
            
            
            else // do opposive because user is selling
            {
                 Account.balance += Queue.headTradeNode.shareCount * Engine.aStock[temp1].pricePerShare;
                Account.aStockPosition[temp].shareCount -= Queue.headTradeNode.shareCount;            
            }
        Queue.Dequeue();           
        }   
    }
}