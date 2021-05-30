/**
 * 
 */
package com.mtester.mobile.service;

import java.util.List;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.mtester.mobile.dto.BookingDTO;
import com.mtester.mobile.model.Booking;
import com.mtester.mobile.model.Mobile;
import com.mtester.mobile.repository.BookingRepository;

/**
 * @author samba
 *
 */
@Service
public class BookingService {
	@Autowired
	BookingRepository bookingRepo;
	@Autowired
	ModelMapper modelMapper;

	/**
	 * Fetches bookings associated with given mobile id order by booked date desc.
	 * 
	 * @param mobileId can't be {@literal null}.
	 * @param page     can't be {@literal null}.
	 * @param size     can't be {@literal null}.
	 * @param orders   can be {@literal null}.
	 * @return
	 */
	public Page<BookingDTO> fetchBookings(Long mobileId, int page, int size, List<Order> orders) {
		Mobile m = new Mobile();
		m.setId(mobileId);

		Page<Booking> bookings = bookingRepo.findByMobile(m, PageRequest.of(page, size, Sort.by(orders)));
		return bookings.map(new Function<Booking, BookingDTO>() {
			@Override
			public BookingDTO apply(Booking entity) {
				return modelMapper.map(entity, BookingDTO.class);
			}
		});
	}
}
