package com.axity.util;

public final class PalindromeUtils
{
  private PalindromeUtils()
  {
  }

  public static boolean isPalindrome( String candidate )
  {
    int length = candidate.length();
    for( int i = 0; i < length / 2; i++ )
    {
      if( candidate.charAt( i ) != candidate.charAt( length - (i + 1) ) )
      {
        return false;
      }
    }
    return true;
  }
}
