package com.axity.services;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.axity.services.impl.PortfolioServiceImpl;
import com.axity.to.Stock;

@RunWith(MockitoJUnitRunner.class)
public class PortfolioServiceTest
{

  @InjectMocks
  private PortfolioService portfolioService = new PortfolioServiceImpl();

  @Mock
  private StockService stockService;

  @Before
  public void setUp()
  {
  }

  @Test
  public void test()
  {
    List<String> mockList = Mockito.mock( List.class );
    mockList.add( "one" );
    Mockito.verify( mockList ).add( "one" );
    when( mockList.size() ).thenReturn( 1 );
    assertEquals( 1, mockList.size() );
  }

  @Test
  public void testGetMarketValue()
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
    when( stockService.getPrice( googleStock ) ).thenReturn( 50.00 );
    when( stockService.getPrice( microsoftStock ) ).thenReturn( 1000.00 );

    double marketValue = portfolioService.getMarketValue();

    assertEquals( 100500.0, marketValue, 0.1 );
  }

}
