/**
 * 
 */
package com.mtester.mobile.controller;

import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtester.common.MTesterUtil;
import com.mtester.common.controller.BaseController;
import com.mtester.mobile.dto.BookingDTO;
import com.mtester.mobile.dto.MobileDTO;
import com.mtester.mobile.service.BookingService;
import com.mtester.mobile.service.ExternalRestService;
import com.mtester.mobile.service.MobileService;

/**
 * 
 * @author samba
 *
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/v1/mobiles")
@Validated
public class MobileContoller extends BaseController<MobileDTO> {
	@Autowired
	MobileService mobileService;
	@Autowired
	BookingService bookingService;
	@Autowired
	ExternalRestService externalService;
	@Autowired
	ModelMapper modelMapper;

	/**
	 * Fetches list of mobile based on given filter, page and sorting. Default
	 * sorting will be based on name and in ascending order
	 * 
	 * @param name
	 * @param available - Yes or No
	 * @param page
	 * @param size
	 * @param sort
	 * @return
	 */
	@GetMapping
	public ResponseEntity<Map<String, Object>> get(@RequestParam(required = false) String name,
			@RequestParam(required = false) String available, @RequestParam(defaultValue = "0") int page,
			@RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = MobileConstants.MOBILE_SEARCH_DEFAULT_SORTING) String[] sort) {
		Page<MobileDTO> data = mobileService.fetchMobiles(name, available, page, size, MTesterUtil.getOrders(sort));
		data.forEach(mobile -> {
			// Fetching mobile info from external system
			MobileDTO externalData = externalService.populateDeviceInfoFromExternal(mobile.getName());
			if (externalData != null) {
				mobile.setTechnology(externalData.getTechnology());
				mobile.setBands2g(externalData.getBands2g());
				mobile.setBands3g(externalData.getBands3g());
				mobile.setBands4g(externalData.getBands4g());
			}

			// Loading last booked info for each mobile if it is not available
			if (MobileConstants.NO.equals(mobile.getAvailable())) {
				BookingDTO bookingInfo = bookingService
						.fetchBookings(mobile.getId(), 0, 1,
								MTesterUtil.getOrders(new String[] { MobileConstants.BOOKING_SEARCH_DEFAULT_SORTING }))
						.stream().findFirst().get();
				mobile.setBookingInfo(bookingInfo);
			}
		});
		return new ResponseEntity<>(getData(data), HttpStatus.OK);
	}

	/**
	 * Books mobile.
	 * 
	 * @param id
	 * @return
	 */
	@PostMapping
	public ResponseEntity<String> book(@RequestBody @Valid MobileDTO mobile) {
		Long bookingId = mobileService.book(mobile);
		return new ResponseEntity<>("Mobile is booked successfully and reference number is " + bookingId,
				HttpStatus.OK);
	}

	/**
	 * Updates mobile availability to Yes, before updating validates give id valid
	 * or with database.
	 * 
	 * @param id
	 * @return
	 */
	@PutMapping("/{id}")
	public ResponseEntity<String> returnBack(
			@PathVariable("id") @Min(value = 1, message = "{mt.mobile.id.min}") @Max(value = 2147483647, message = "{mt.mobile.id.max}") Long id) {

		mobileService.updateToAvailalbe(id);
		return new ResponseEntity<>("Mobile Returned successfully", HttpStatus.OK);
	}
}
