/**
 * 
 */
package com.mtester.mobile.repository.specs;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.mtester.common.repository.specs.SearchCriteria;
import com.mtester.common.repository.specs.SearchOperation;
import com.mtester.mobile.model.Mobile;

/**
 * @author samba
 *
 */
public class MobileSearchSpecification implements Specification<Mobile> {
	private static final long serialVersionUID = -7976732582979184977L;
	private List<SearchCriteria> list;

	public MobileSearchSpecification() {
		this.list = new ArrayList<>();
	}

	public void add(SearchCriteria criteria) {
		list.add(criteria);
	}

	@Override
	public Predicate toPredicate(Root<Mobile> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		// create a new predicate list
		List<Predicate> predicates = new ArrayList<>();

		// add add criteria to predicates
		for (SearchCriteria criteria : list) {
			if (criteria.getOperation().equals(SearchOperation.EQUALITY) && criteria.getValue() != null) {
				predicates.add(builder.equal(root.get(criteria.getKey()), criteria.getValue()));
			}
			// TODO more conditions can be added in future as and when required.
		}

		return builder.and(predicates.toArray(new Predicate[0]));
	}
}
