package com.axity.crud.to;

/**
 * The Class ErrorTO.
 */
public class ErrorTO extends CatalogTO
{

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 6821167174466991022L;

  /** The description. */
  private String description;

  /** The trace. */
  private String trace;

  /** The bad request. */
  private boolean badRequest;

  /**
   * Gets the description.
   *
   * @return the description
   */
  public String getDescription()
  {
    return description;
  }

  /**
   * Sets the description.
   *
   * @param description the new description
   */
  public void setDescription( String description )
  {
    this.description = description;
  }

  /**
   * Gets the trace.
   *
   * @return the trace
   */
  public String getTrace()
  {
    return trace;
  }

  /**
   * Sets the trace.
   *
   * @param trace the new trace
   */
  public void setTrace( String trace )
  {
    this.trace = trace;
  }

  /**
   * Checks if is bad request.
   *
   * @return true, if is bad request
   */
  public boolean isBadRequest()
  {
    return badRequest;
  }

  /**
   * Sets the bad request.
   *
   * @param badRequest the new bad request
   */
  public void setBadRequest( boolean badRequest )
  {
    this.badRequest = badRequest;
  }

}
