/**
 * 
 */
package com.mtester.mobile.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author samba
 *
 */
public class MobileDTO {
	private Long id;
	@NotEmpty(message = "{mt.mobile.name.not.empty}")
	private String name;
	private String available;
	@NotNull(message = "{mt.mobile.booking.notnull}")
	@Valid
	private BookingDTO bookingInfo;
	private String technology;
	private String bands2g;
	private String bands3g;
	private String bands4g;

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
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the available
	 */
	public String getAvailable() {
		return available;
	}

	/**
	 * @param available the available to set
	 */
	public void setAvailable(String available) {
		this.available = available;
	}

	/**
	 * @return the bookingInfo
	 */
	public BookingDTO getBookingInfo() {
		return bookingInfo;
	}

	/**
	 * @param bookingInfo the bookingInfo to set
	 */
	public void setBookingInfo(BookingDTO bookingInfo) {
		this.bookingInfo = bookingInfo;
	}

	/**
	 * @return the technology
	 */
	public String getTechnology() {
		return technology;
	}

	/**
	 * @param technology the technology to set
	 */
	public void setTechnology(String technology) {
		this.technology = technology;
	}

	/**
	 * @return the bands2g
	 */
	@JsonProperty("_2g_bands")
	public String getBands2g() {
		return bands2g;
	}

	/**
	 * @param bands2g the bands2g to set
	 */
	@JsonProperty("_2g_bands")
	public void setBands2g(String bands2g) {
		this.bands2g = bands2g;
	}

	/**
	 * @return the bands3g
	 */
	@JsonProperty("_3g_bands")
	public String getBands3g() {
		return bands3g;
	}

	/**
	 * @param bands3g the bands3g to set
	 */
	@JsonProperty("_3g_bands")
	public void setBands3g(String bands3g) {
		this.bands3g = bands3g;
	}

	/**
	 * @return the bands4g
	 */
	@JsonProperty("_4g_bands")
	public String getBands4g() {
		return bands4g;
	}

	/**
	 * @param bands4g the bands4g to set
	 */
	@JsonProperty("_4g_bands")
	public void setBands4g(String bands4g) {
		this.bands4g = bands4g;
	}

	/**
	 * @param id
	 * @param name
	 * @param available
	 * @param bookingInfo
	 * @param technology
	 * @param bands2g
	 * @param bands3g
	 * @param bands4g
	 */
	public MobileDTO(Long id, String name, String available, BookingDTO bookingInfo, String technology, String bands2g,
			String bands3g, String bands4g) {
		super();
		this.id = id;
		this.name = name;
		this.available = available;
		this.bookingInfo = bookingInfo;
		this.technology = technology;
		this.bands2g = bands2g;
		this.bands3g = bands3g;
		this.bands4g = bands4g;
	}

	@Override
	public String toString() {
		return "MobileDTO [id=" + id + ", name=" + name + ", available=" + available + ", bookingInfo=" + bookingInfo
				+ ", technology=" + technology + ", bands2g=" + bands2g + ", bands3g=" + bands3g + ", bands4g="
				+ bands4g + "]";
	}

	/**
	 * 
	 */
	public MobileDTO() {
		super();
	}

}
