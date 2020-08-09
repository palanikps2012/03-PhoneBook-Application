package com.tcs.service;

import java.util.List;

import com.tcs.dto.Contact;

public interface  ContactService {

	boolean saveContact(Contact c);

	List<Contact> getAllContacts();

	Contact getContactById(Integer cid);

	boolean updateContact(Contact c);

	boolean deleteContact(Integer cid);
}
