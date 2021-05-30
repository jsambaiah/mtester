/**
 * 
 */
package com.mtester.mobile.controller;

/**
 * @author samba
 *
 */
public interface MobileConstants {
	public static String YES = "Yes";
	public static String NO = "No";
	public static String MOBILE_SEARCH_DEFAULT_SORTING = "name,asc";
	public static String BOOKING_SEARCH_DEFAULT_SORTING = "bookedOn,desc";
	// TODO need to update the correct token. This can be configured in environment
	// specific yml file and same can be read in real implementation.
	public static String FONOAPI_DEVICEAPIURL = "https://fonoapi.freshpixl.com/v1/getdevice?token=NOTKNOWN&position=0";
}
