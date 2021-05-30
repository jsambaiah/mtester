/**
 * 
 */
package com.mtester.mobile.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.mtester.mobile.model.Mobile;

/**
 * @author samba
 *
 */
@Transactional
public interface MobileRepository extends PagingAndSortingRepository<Mobile, Long>, JpaSpecificationExecutor<Mobile> {
	@Modifying
	@Query("UPDATE Mobile M set M.available = :available where M.id = :id")
	public void updateStatus(@Param(value = "id") Long id, @Param(value = "available") String avl);
}
