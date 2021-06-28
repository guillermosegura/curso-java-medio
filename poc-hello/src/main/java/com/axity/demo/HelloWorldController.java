package com.axity.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.axity.other.Something;

@RestController()
public class HelloWorldController
{
  @Autowired
  private Something something;

  @Value("${hello.app.name}")
  private String appName;

  @Value("${hello.app.undefined:123}")
  private String appUndefined;

  @GetMapping(path = "/json", produces = MediaType.APPLICATION_JSON_VALUE)
  public Hello helloJson()
  {
    return new Hello( "Hello World!!!" );
  }

  @GetMapping(path = "/something", produces = MediaType.APPLICATION_JSON_VALUE)
  public Hello something()
  {
    return new Hello( something.doSomething() );
  }

  @GetMapping(path = "/app", produces = MediaType.APPLICATION_JSON_VALUE)
  public Hello helloAppName()
  {
    return new Hello( this.appName + "_" + this.appUndefined );
  }

}
