package com.axity.libs.apache.common;

import java.util.List;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Student implements Comparable<Student>
{
  private String name;
  private String lastname;
  private List<Address> addresses;
  private List<Course> courses;
  private Integer age;

  @Override
  public boolean equals( Object object )
  {
    boolean isEquals = false;
    if( this == object )
    {
      isEquals = true;
    }
    else if( object != null && object.getClass().equals( this.getClass() ) )
    {
      Student that = (Student) object;

      isEquals = new EqualsBuilder()
          .append( this.name, that.name ).append( this.lastname, that.lastname )
          .append( this.age, that.age ).isEquals();

    }
    return isEquals;
  }

  @Override
  public int hashCode()
  {
    return new HashCodeBuilder().append( this.name ).append( this.lastname ).append( this.age ).hashCode();
  }

  @Override
  public String toString()
  {
    return new ToStringBuilder( this ).append( "name", this.name ).append( "lastname", this.lastname )
        .append( "age", this.age ).build();
  }

  @Override
  public int compareTo( Student that )
  {
    return new CompareToBuilder().append( this.name, that.name ).append( this.lastname, that.lastname )
        .append( this.age, that.age ).toComparison();
  }
}
