package com.axity.libs.apache.common.builder;

import java.util.ArrayList;
import java.util.List;

import com.axity.libs.apache.common.Address;
import com.axity.libs.apache.common.Course;
import com.axity.libs.apache.common.Student;

/**
 * Esta clase se utilza para construir instancias de {@link com.axity.libs.apache.common.Student}
 * 
 * @author guillermo.segura@axity.com
 */
public final class StudentBuilder
{
  private Student student;
  private StudentBuilder()
  {
    student = new Student();
    student.setAddresses( new ArrayList<>() );
    student.setCourses( new ArrayList<>() );
  }

  /**
   * Regras una instacia del {@link com.axity.libs.apache.common.builder.StudentBuilder}
   * 
   * @return
   */
  public static StudentBuilder getInstance()
  {
    return new StudentBuilder();
  }

  /**
   * Setea el nombre
   * 
   * @param name
   * @return
   */
  public StudentBuilder name( String name )
  {
    this.student.setName( name );
    return this;
  }

  /**
   * Setea el apellido
   * 
   * @param lastname
   * @return
   */
  public StudentBuilder lastname( String lastname )
  {
    this.student.setLastname( lastname );
    return this;
  }

  public StudentBuilder age( Integer age )
  {
    this.student.setAge( age );
    return this;
  }

  public StudentBuilder addAddress( Address address )
  {
    this.student.getAddresses().add( address );
    return this;
  }

  public StudentBuilder addCourse( Course course )
  {
    this.student.getCourses().add( course );
    return this;
  }

  public StudentBuilder addAddresses( List<Address> addresses )
  {
    this.student.getAddresses().addAll( addresses );
    return this;
  }

  public StudentBuilder addCourses( List<Course> courses )
  {
    this.student.getCourses().addAll( courses );
    return this;
  }

  public StudentBuilder clearAddresses()
  {
    this.student.getAddresses().clear();
    return this;
  }

  public StudentBuilder clearCourses()
  {
    this.student.getCourses().clear();
    return this;
  }

  public StudentBuilder removeAddress( Address address )
  {
    this.student.getAddresses().remove( address );
    return this;
  }

  public StudentBuilder removeCourse( Course course )
  {
    this.student.getCourses().remove( course );
    return this;
  }

  public Student create()
  {
    return this.student;
  }
}
