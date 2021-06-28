package com.axity.crud.model;

import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang3.builder.CompareToBuilder;

@Entity
@Table(name = "W_LOG")
public class LogDO extends AbstractEntity<LogDO> {

	private static final long serialVersionUID = 2455979833464609892L;

	@Id
	@Column(name = "ID_LOG")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Lob
	@Column(name = "DS_LOG", nullable = false)
	private String log;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DT_LOG")
	private Date date;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLog() {
		return log;
	}

	public void setLog(String log) {
		this.log = log;
	}

	public Date getDate() {
		return date != null ? (Date) date.clone() : null;
	}

	public void setDate(Date date) {
		this.date = date != null ? (Date) date.clone() : null;
	}

	@Override
	public int compareTo(LogDO o) {
		return new CompareToBuilder().append(this.id, o.id).toComparison();
	}

	@Override
	public boolean equals(Object object) {
		boolean isEquals;
		if (this == object) {
			isEquals = true;
		} else if (object != null && object.getClass().equals(this.getClass())) {
			final LogDO that = (LogDO) object;
			isEquals = Objects.equals(this.id, that.id);
		} else {
			isEquals = false;
		}

		return isEquals;
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.id);
	}

}
