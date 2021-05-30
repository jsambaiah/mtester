/**
 * 
 */
package com.mtester.mobile.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.mtester.mobile.model.Booking;
import com.mtester.mobile.model.Mobile;

/**
 * @author samba
 *
 */
@Transactional
public interface BookingRepository extends PagingAndSortingRepository<Booking, Long> {
	public Page<Booking> findByMobile(Mobile mobile, Pageable page);
}
