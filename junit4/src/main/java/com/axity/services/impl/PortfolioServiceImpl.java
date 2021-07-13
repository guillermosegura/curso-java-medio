package com.axity.services.impl;

import java.util.List;

import com.axity.services.PortfolioService;
import com.axity.services.StockService;
import com.axity.to.Stock;

public class PortfolioServiceImpl implements PortfolioService
{
  private StockService stockService;
  private List<Stock> stocks;

  @Override
  public double getMarketValue()
  {
    double marketValue = 0.0;

    for( Stock stock : stocks )
    {
      double price = stockService.getPrice( stock );
      marketValue += price * stock.getQuantity();
    }
    return marketValue;
  }

  public List<Stock> getStocks()
  {
    return stocks;
  }

  public void setStocks( List<Stock> stocks )
  {
    this.stocks = stocks;
  }

  public void setStockService( StockService stockService )
  {
    this.stockService = stockService;
  }

}
