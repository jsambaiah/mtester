/**
 * 
 */
package com.mtester.mobile.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author samba
 *
 */
@Entity
@Table(name = "MT_MOB_BOOK_HSTRY")
public class Booking {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(name = "MOB_ID")
	private Mobile mobile;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "BKD_ON")
	private Date bookedOn;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@Column(name = "BKD_BY")
	private String bookedBy;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the mobile
	 */
	public Mobile getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(Mobile mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the bookedOn
	 */
	public Date getBookedOn() {
		return bookedOn;
	}

	/**
	 * @param bookedOn the bookedOn to set
	 */
	public void setBookedOn(Date bookedOn) {
		this.bookedOn = bookedOn;
	}

	/**
	 * @return the bookedBy
	 */
	public String getBookedBy() {
		return bookedBy;
	}

	/**
	 * @param bookedBy the bookedBy to set
	 */
	public void setBookedBy(String bookedBy) {
		this.bookedBy = bookedBy;
	}

	/**
	 * @param id
	 * @param mobile
	 * @param bookedOn
	 * @param returnedOn
	 * @param bookedBy
	 * @param returnedBy
	 */
	public Booking(Long id, Mobile mobile, Date bookedOn, String bookedBy) {
		super();
		this.id = id;
		this.mobile = mobile;
		this.bookedOn = bookedOn;
		this.bookedBy = bookedBy;
	}

	/**
	 * 
	 */
	public Booking() {
		super();
	}

	@Override
	public String toString() {
		return "Booking [id=" + id + ", mobile=" + mobile + ", bookedOn=" + bookedOn + ", bookedBy=" + bookedBy + "]";
	}
}
