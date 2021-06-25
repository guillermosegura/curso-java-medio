package com.axity.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import com.axity.services.impl.PortfolioServiceImpl;
import com.axity.to.Stock;

@ExtendWith(MockitoExtension.class)
// @MockitoSettings(strictness = Strictness.LENIENT)
class PortfolioServiceTest
{

  @InjectMocks
  private PortfolioService portfolioService = new PortfolioServiceImpl();

  @Mock
  private StockService stockService;

  @BeforeEach
  void setUp() throws Exception
  {
  }

  @Test
  void testGetMarketValue()
  {
    // Creates a list of stocks to be added to the portfolio
    List<Stock> stocks = new ArrayList<Stock>();
    Stock googleStock = new Stock( "1", "Google", 10 );
    Stock microsoftStock = new Stock( "2", "Microsoft", 100 );

    stocks.add( googleStock );
    stocks.add( microsoftStock );

    // add stocks to the portfolio
    portfolioService.setStocks( stocks );

    // mock the behavior of stock service to return the value of various stocks
    lenient().when( stockService.getPrice( ArgumentMatchers.eq( googleStock ) ) ).thenReturn( 50.00 );
    lenient().when( stockService.getPrice( ArgumentMatchers.eq( microsoftStock ) ) ).thenReturn( 1000.00 );

    double marketValue = portfolioService.getMarketValue();

    assertEquals( 100500.0, marketValue, 0.1 );
  }

}
