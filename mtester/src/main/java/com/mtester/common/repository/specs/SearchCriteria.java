/**
 * 
 */
package com.mtester.common.repository.specs;

/**
 * @author samba
 *
 */
public class SearchCriteria {
	private String key;
	private Object value;
	private SearchOperation operation;

	public SearchCriteria() {
	}

	public SearchCriteria(String key, Object value, SearchOperation operation) {
		this.key = key;
		this.value = value;
		this.operation = operation;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public Object getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(Object value) {
		this.value = value;
	}

	/**
	 * @return the operation
	 */
	public SearchOperation getOperation() {
		return operation;
	}

	/**
	 * @param operation the operation to set
	 */
	public void setOperation(SearchOperation operation) {
		this.operation = operation;
	}

	@Override
	public String toString() {
		return "SearchCriteria [key=" + key + ", value=" + value + ", operation=" + operation + "]";
	}

}
