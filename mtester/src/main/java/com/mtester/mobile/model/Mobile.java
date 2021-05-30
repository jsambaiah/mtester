/**
 * 
 */
package com.mtester.mobile.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author samba
 *
 */
@Entity
@Table(name = "MT_MOB")
public class Mobile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@Column(name = "NAME")
	private String name;
	@Column(name = "AVL")
	private String available;
	@OneToMany(mappedBy = "mobile", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Booking> bookings;
	@Column(name = "TECH")
	private String technology;
	@Column(name = "BANDS_2G")
	private String bands2g;
	@Column(name = "BANDS_3G")
	private String bands3g;
	@Column(name = "BANDS_4G")
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
	 * @return the bookings
	 */
	public List<Booking> getBookings() {
		return bookings;
	}

	/**
	 * @param bookings the bookings to set
	 */
	public void setBookings(List<Booking> bookings) {
		this.bookings = bookings;
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
	public String getBands2g() {
		return bands2g;
	}

	/**
	 * @param bands2g the bands2g to set
	 */
	public void setBands2g(String bands2g) {
		this.bands2g = bands2g;
	}

	/**
	 * @return the bands3g
	 */
	public String getBands3g() {
		return bands3g;
	}

	/**
	 * @param bands3g the bands3g to set
	 */
	public void setBands3g(String bands3g) {
		this.bands3g = bands3g;
	}

	/**
	 * @return the bands4g
	 */
	public String getBands4g() {
		return bands4g;
	}

	/**
	 * @param bands4g the bands4g to set
	 */
	public void setBands4g(String bands4g) {
		this.bands4g = bands4g;
	}

	/**
	 * 
	 */
	public Mobile() {
		super();
	}

	/**
	 * @param id
	 * @param name
	 * @param available
	 * @param bookings
	 * @param technology
	 * @param bands2g
	 * @param bands3g
	 * @param bands4g
	 */
	public Mobile(Long id, String name, String available, List<Booking> bookings, String technology, String bands2g,
			String bands3g, String bands4g) {
		super();
		this.id = id;
		this.name = name;
		this.available = available;
		this.bookings = bookings;
		this.technology = technology;
		this.bands2g = bands2g;
		this.bands3g = bands3g;
		this.bands4g = bands4g;
	}

	@Override
	public String toString() {
		return "Mobile [id=" + id + ", name=" + name + ", available=" + available + ", bookings=" + bookings
				+ ", technology=" + technology + ", bands2g=" + bands2g + ", bands3g=" + bands3g + ", bands4g="
				+ bands4g + "]";
	}

}
