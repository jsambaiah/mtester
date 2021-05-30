/**
 * 
 */
package com.mtester.mobile.dto;

import java.util.Date;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * @author samba
 *
 */
public class BookingDTO {
	private Long id;
	//TODO validations to be handled to validate date format
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date bookedOn;
	@NotEmpty(message = "{mt.mobile.booking.bookedby.not.empty}")
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
	 * @param mobileId
	 * @param bookedOn
	 * @param returnedOn
	 * @param bookedBy
	 * @param returnedBy
	 */
	public BookingDTO(Long id, Date bookedOn, String bookedBy) {
		super();
		this.id = id;
		this.bookedOn = bookedOn;
		this.bookedBy = bookedBy;
	}

	/**
	 * 
	 */
	public BookingDTO() {
		super();
	}
}
