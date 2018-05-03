package com.contact.test;

import java.util.Scanner;

public class TestEntry {
	public static void main(String[] args) {
		Scanner reader = new Scanner(System.in);
		while (true) {
			dispalyTestMenu();
			ClientTest test = new ClientTest();
			test.init();
			System.out.println("Select the option: ");
			String option = reader.nextLine();
			switch (option) {
			case "1":
				test.createContact();
				break;
			case "2":
				test.updateContact();
				break;
			case "3":
				test.deleteContact();
				break;
			case "4":
				System.out.println("Contact id: ");
				String id = reader.nextLine();
				test.getContactById(id);
				break;
			case "5":
				System.out.println("Phone number: ");
				String number = reader.nextLine();
				test.getContactByNumber(number);
				break;
			case "6":
				System.out.println("Email: ");
				String email = reader.nextLine();
				test.getContactByEmail(email);
				break;
			case "7":
				System.out.println("State name: ");
				String state = reader.nextLine();
				test.getContactsByCity(state);
				break;
			case "8":
				System.out.println("City name: ");
				String city = reader.nextLine();
				test.getContactsByState(city);
				break;
			case "9":
				UnitTestGetByNumber unitTest = new UnitTestGetByNumber();
				unitTest.test();
				break;
			case "0":
				return;
			}
		}
	}

	private static void dispalyTestMenu() {
		// TODO Auto-generated method stub
		System.out.println("---------------- Menu ------------------");
		System.out.println("1. Create a new contact");
		System.out.println("2. Update an existing contact");
		System.out.println("3. Delete a contact");
		System.out.println("4. Retrieve the contact by id");
		System.out.println("5. Retrieve the contact by phone number");
		System.out.println("6. Retrieve the contact by email");
		System.out.println("7. Search the contacts by city");
		System.out.println("8. Search the contacts by state");
		System.out.println("9. Unit test for getContactByNumber");
		System.out.println("0. Exit");
		
	}
}
