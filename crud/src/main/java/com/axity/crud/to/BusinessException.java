package com.axity.crud.to;

/**
 * The Class InternalErrorException.
 */
public class BusinessException extends RuntimeException
{

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -5607313279563367458L;
  /** The error. */
  private final ErrorTO error;

  /**
   * Instantiates a new internal error exception.
   */
  public BusinessException()
  {
    super();
    error = new ErrorTO();
  }

  /**
   * Instantiates a new internal error exception.
   *
   * @param message the message
   * @param cause the cause
   */
  public BusinessException( String message, Throwable cause )
  {
    super( message, cause );
    error = new ErrorTO();
  }

  /**
   * Instantiates a new internal error exception.
   *
   * @param message the message
   */
  public BusinessException( String message )
  {
    super( message );
    error = new ErrorTO();
  }

  /**
   * Instantiates a new internal error exception.
   *
   * @param cause the cause
   */
  public BusinessException( Throwable cause )
  {
    super( cause );
    error = new ErrorTO();
  }

  /**
   * Gets the error.
   *
   * @return the error
   */
  public ErrorTO getError()
  {
    return error;
  }

}
