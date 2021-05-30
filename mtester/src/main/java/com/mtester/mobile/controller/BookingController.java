/**
 * 
 */
package com.mtester.mobile.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mtester.common.MTesterUtil;
import com.mtester.common.controller.BaseController;
import com.mtester.mobile.dto.BookingDTO;
import com.mtester.mobile.service.BookingService;

/**
 * 
 * @author samba
 *
 */
@CrossOrigin(origins = "http://localhost:8081")
@RestController
@RequestMapping("/v1/mobiles/{id}/bookings")
public class BookingController extends BaseController<BookingDTO> {
	@Autowired
	BookingService bookingService;

	/**
	 * Fetches all bookings done on give mobile id in descending order.
	 * 
	 * @param id can't be {@literal null}.
	 * @return all bookings with page info
	 */
	@GetMapping
	public ResponseEntity<Map<String, Object>> fetchBookings(@PathVariable("id") Long mobileId,
			@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size,
			@RequestParam(defaultValue = MobileConstants.BOOKING_SEARCH_DEFAULT_SORTING) String[] sort) {
		return new ResponseEntity<>(getData(bookingService.fetchBookings(mobileId, page, size, MTesterUtil.getOrders(sort))),
				HttpStatus.OK);
	}
}
