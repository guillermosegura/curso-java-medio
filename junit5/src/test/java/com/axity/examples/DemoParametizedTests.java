package com.axity.examples;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.axity.util.PalindromeUtils;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
class DemoParametizedTests
{

  @ParameterizedTest
  @ValueSource(strings = { "racecar", "radar", "able was I ere I saw elba" })
  void palindromes( String candidate )
  {
    assertTrue( PalindromeUtils.isPalindrome( candidate ) );
  }

  @ParameterizedTest
  @NullSource
  void isNull_ShouldReturnTrueForNullInputs( String input )
  {
    assertNull( input );
  }

  @ParameterizedTest
  @EmptySource
  void isBlank_ShouldReturnTrueForNullInputs( String input )
  {
    assertTrue( StringUtils.isBlank( input ) );
  }

}
