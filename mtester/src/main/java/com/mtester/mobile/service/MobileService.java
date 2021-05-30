/**
 * 
 */
package com.mtester.mobile.service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.mtester.common.MTesterUtil;
import com.mtester.common.exception.ResourceNotFoundException;
import com.mtester.common.repository.specs.SearchCriteria;
import com.mtester.common.repository.specs.SearchOperation;
import com.mtester.mobile.controller.MobileConstants;
import com.mtester.mobile.dto.MobileDTO;
import com.mtester.mobile.model.Booking;
import com.mtester.mobile.model.Mobile;
import com.mtester.mobile.repository.BookingRepository;
import com.mtester.mobile.repository.MobileRepository;
import com.mtester.mobile.repository.specs.MobileSearchSpecification;

/**
 * @author samba
 *
 */
@Service
public class MobileService {
	@Autowired
	MobileRepository mobileRepo;
	@Autowired
	BookingRepository bookingRepo;
	@Autowired
	ModelMapper modelMapper;

	/**
	 * Fetches mobile's based on name and availability
	 * 
	 * @param mobile    name can be {@literal null}.
	 * @param filtering mobile availability, can be {@literal null}.
	 * @param page      can't be {@literal null}.
	 * @param size      can't be {@literal null}.
	 * @param orders    can be {@literal null}.
	 * @return list of mobile's, if nothing found returns empty list.
	 */
	public Page<MobileDTO> fetchMobiles(String name, String available, int page, int size, List<Order> orders) {
		MobileSearchSpecification msGenre = new MobileSearchSpecification();
		msGenre.add(new SearchCriteria("name", name, SearchOperation.EQUALITY));
		msGenre.add(new SearchCriteria("available", available, SearchOperation.EQUALITY));

		Page<Mobile> mobiles = mobileRepo.findAll(msGenre, PageRequest.of(page, size, Sort.by(orders)));

		return mobiles.map(new Function<Mobile, MobileDTO>() {
			@Override
			public MobileDTO apply(Mobile entity) {
				return modelMapper.map(entity, MobileDTO.class);
			}
		});
	}

	/**
	 * Fetches the mobile information from DB.
	 * 
	 * @param id can't be {@literal null}.
	 * @return <code>Optional MobileDTO></code>
	 */
	public Optional<MobileDTO> findById(Long id) {
		return mobileRepo.findById(id).map(m -> modelMapper.map(m, MobileDTO.class));
	}

	/**
	 * Booking mobile.
	 * 
	 * @param <code>MobileDTO</code> can't be {@literal null}.
	 * @return <code>Long</code> booking id.
	 */
	public Long book(MobileDTO mobile) {
		// Validate mobile exist or not.
		Page<MobileDTO> results = fetchMobiles(mobile.getName(), MobileConstants.YES, 0, 1,
				MTesterUtil.getOrders(new String[] { MobileConstants.MOBILE_SEARCH_DEFAULT_SORTING }));
		if (results.isEmpty()) {
			throw new ResourceNotFoundException("Mobile is already booked or not a valid mobile");
		} else {
			MobileDTO dbMobile = results.stream().findFirst().get();
			mobile.setId(dbMobile.getId());

			// Updating mobile status to Available
			mobileRepo.updateStatus(mobile.getId(), MobileConstants.NO);

			// Inserting entry into booking table to know who all booked and date time
			Booking b = modelMapper.map(mobile.getBookingInfo(), Booking.class);
			Mobile m = new Mobile();
			m.setId(mobile.getId());
			b.setMobile(m);
			b.setBookedOn(Calendar.getInstance().getTime());

			return bookingRepo.save(b).getId();
		}
	}

	/**
	 * Updates the mobile availability to Yes.
	 * 
	 * @param mobile id <code>Long</code> {@literal null}.
	 */
	public void updateToAvailalbe(Long mobileId) {
		Optional<MobileDTO> mobile = findById(mobileId);
		if (mobile.isPresent()) {
			// Updating mobile status to Available
			mobileRepo.updateStatus(mobileId, MobileConstants.YES);
		} else {
			throw new ResourceNotFoundException(null);
		}

	}
}
