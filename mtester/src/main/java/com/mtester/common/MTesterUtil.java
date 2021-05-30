/**
 * 
 */
package com.mtester.common;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;

/**
 * @author samba
 *
 */
public class MTesterUtil {
	private static Sort.Direction getSortDirection(String direction) {
		if (direction.equals("asc")) {
			return Sort.Direction.ASC;
		} else if (direction.equals("desc")) {
			return Sort.Direction.DESC;
		}

		return Sort.Direction.ASC;
	}

	public static List<Order> getOrders(String[] sort) {
		List<Order> orders = new ArrayList<Order>();

		if (sort[0].contains(",")) {
			// will sort more than 2 columns
			for (String sortOrder : sort) {
				// sortOrder="column, direction"
				String[] _sort = sortOrder.split(",");
				orders.add(new Order(getSortDirection(_sort[1]), _sort[0]));
			}
		} else {
			// sort=[column, direction]
			orders.add(new Order(getSortDirection(sort[1]), sort[0]));
		}

		return orders;
	}
}
