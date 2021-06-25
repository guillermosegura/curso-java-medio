package com.axity.services;

import java.util.List;

import com.axity.to.Stock;

public interface PortfolioService
{
  double getMarketValue();

  List<Stock> getStocks();

  void setStocks( List<Stock> stocks );

  void setStockService( StockService stockService );
}
