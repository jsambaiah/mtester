package com.mtester.test;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.mtester.mobile.dto.BookingDTO;
import com.mtester.mobile.dto.MobileDTO;
import com.mtester.mobile.service.MobileService;

/**
 * This class is responsible to run tests during build if profile is given as
 * test.
 * 
 * @author samba
 *
 */
@SpringBootTest
@ActiveProfiles("test")
public class MTesterApplicationTests {
	@Autowired
	private MobileService mobileService;

	/**
	 * Tests booking service is working or not.
	 * 
	 * 1. Books Mobile 2. Validates fetching mobile info my id is working or not. 3.
	 * Validates mobile is marked is booked or not.
	 */
	@Test
	public void testBooking() {
		MobileDTO testData = getSamsungGalaxyS9();
		Long mobileId = mobileService.book(testData);

		// Assures book service call is worked without issues.
		Assert.assertNotNull("Mobile is not booked, something wrong in current build in book service", mobileId);
		Optional<MobileDTO> bookedData = mobileService.findById(mobileId);

		// Assures book is done or not
		Assert.assertFalse("Mobile is not exist, soemthing wrong is current build while fetching mobile by id",
				bookedData.isEmpty());

		// Assures
		Assert.assertEquals("Mobile is not markes as non avilable, something worong in current build in book service",
				"No", bookedData.get().getAvailable());
	}

	// TODO pending tests for return, fetch can be written similar to above

	private MobileDTO getSamsungGalaxyS9() {
		return new MobileDTO(null, "Samsung Galaxy S9", null, new BookingDTO(null, null, "Samba"), null, null, null,
				null);
	}
}
