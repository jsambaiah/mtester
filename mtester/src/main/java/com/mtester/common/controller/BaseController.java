/**
 * 
 */
package com.mtester.common.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.domain.Page;

/**
 * @author samba
 *
 */
public class BaseController<T> {
	private static final String CURRENT_PAGE = "currentPage";
	private static final String TOTAL_ITEMS = "totalItems";
	private static final String TOTAL_PAGES = "totalPages";
	private static final String DATA = "data";

	public Map<String, Object> getData(Page<T> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		if (data.hasContent()) {
			result.put(CURRENT_PAGE, data.getNumber());
			result.put(TOTAL_ITEMS, data.getNumberOfElements());
			result.put(TOTAL_PAGES, data.getTotalPages());
			result.put(DATA, data.getContent());
		}
		return result;
	}
}
