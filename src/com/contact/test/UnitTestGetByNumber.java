/**
 * 
 */
package com.contact.test;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

/**
 * @author WILL
 *
 */
class UnitTestGetByNumber {
	private static final String SUCCESS = "success"; 
	private static final String NOT_FOUND = "not found";
	private static final String PASS = "pass";
	private static final String FAIL = "fail";
	
	private Map<String, String> test_case1;
	private Map<String, String> test_case2;
	
	public UnitTestGetByNumber() {
		this.test_case1 = new HashMap<>();
		this.test_case2 = new HashMap<>();
		this.test_case1.put("+1 (805) 429-3985", SUCCESS);
		this.test_case1.put("+1 (974) 475-2023", SUCCESS);
		this.test_case1.put("+1 (829) 421-2194", SUCCESS);
		this.test_case1.put("+1 (853) 419-3922", SUCCESS);
		this.test_case1.put("+1 (948) 444-3823", SUCCESS);
		this.test_case1.put("+1 (937) 570-3134", SUCCESS);
		this.test_case1.put("+1 (910) 471-3361", SUCCESS);
		this.test_case1.put("+1 (991) 512-3804", SUCCESS);
		this.test_case1.put("+1 (992) 435-2245", SUCCESS);
		this.test_case1.put("+1 (996) 501-2299", SUCCESS);
		this.test_case1.put("+1 (897) 586-2072", SUCCESS);
		this.test_case1.put("+1 (893) 553-3234", SUCCESS);
		this.test_case2.put("null", NOT_FOUND);
		this.test_case2.put("+1 (893) 553-3235", NOT_FOUND);
		this.test_case2.put("(893) 553-3235", NOT_FOUND);
		this.test_case2.put("This is not a number.", NOT_FOUND);
		this.test_case2.put("893-553-3234", NOT_FOUND);
		this.test_case2.put("#!4@2*", NOT_FOUND);
		this.test_case2.put("+1 (992) 435-2241", NOT_FOUND);
	}

	/**
	 * @throws java.lang.Exception
	 */

	@Test
	void test() {
		ClientTest tester = new ClientTest();
		tester.init();
		for (String number: test_case1.keySet()) {
			if (tester.getContactByNumber(number).equals(test_case1.get(number))) {
				System.out.println(PASS);
			} else {
				System.out.println(FAIL);
			}
		}
		for (String number: test_case2.keySet()) {
			if (tester.getContactByNumber(number).equals(test_case2.get(number))) {
				System.out.println(PASS);
			} else {
				System.out.println(FAIL);
			}
		}
	}

}
