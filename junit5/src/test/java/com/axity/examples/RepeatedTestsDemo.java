package com.axity.examples;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class RepeatedTestsDemo
{

  private static final Logger LOG = LoggerFactory.getLogger( RepeatedTestsDemo.class );

  @BeforeEach
  void beforeEach( TestInfo testInfo, RepetitionInfo repetitionInfo )
  {
    int currentRepetition = repetitionInfo.getCurrentRepetition();
    int totalRepetitions = repetitionInfo.getTotalRepetitions();
    String methodName = testInfo.getTestMethod().get().getName();
    LOG.info( String.format( "About to execute repetition %d of %d for %s", //
      currentRepetition, totalRepetitions, methodName ) );
  }

  @RepeatedTest(10)
  void repeatedTest()
  {
    // ...
  }

  @RepeatedTest(5)
  void repeatedTestWithRepetitionInfo( RepetitionInfo repetitionInfo )
  {
    assertEquals( 5, repetitionInfo.getTotalRepetitions() );
  }

  @RepeatedTest(value = 1, name = "{displayName} {currentRepetition}/{totalRepetitions}")
  @DisplayName("Repeat!")
  void customDisplayName( TestInfo testInfo )
  {
    assertEquals( "Repeat! 1/1", testInfo.getDisplayName() );
  }

  @RepeatedTest(value = 1, name = RepeatedTest.LONG_DISPLAY_NAME)
  @DisplayName("Details...")
  void customDisplayNameWithLongPattern( TestInfo testInfo )
  {
    assertEquals( "Details... :: repetition 1 of 1", testInfo.getDisplayName() );
  }

  @RepeatedTest(value = 5, name = "Wiederholung {currentRepetition} von {totalRepetitions}")
  void repeatedTestInGerman()
  {
    // ...
  }
  
  @RepeatedTest(value = 5, name = "Repetici\u00F3n {currentRepetition} de {totalRepetitions}")
  void repeatedTestInSpanish()
  {
    // ...
  }

}
