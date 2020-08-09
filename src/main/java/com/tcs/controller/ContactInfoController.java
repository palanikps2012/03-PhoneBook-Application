package com.tcs.controller;

import java.util.List;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.tcs.dto.Contact;
import com.tcs.service.ContactService;

@Controller
public class ContactInfoController {
	private static final Logger logger = org.slf4j.LoggerFactory.getLogger(ContactInfoController.class);


	@Autowired
	private ContactService contactService;

	@GetMapping(value = { "/", "/addContact" })
	public String loadForm(Model model) {
		Contact c = new Contact();
		model.addAttribute("contact", c);
		return "contactinfo";
	}

	@PostMapping(value = "/saveContact")
	public String handleSubmitBtn(@ModelAttribute("contact") Contact c, RedirectAttributes attributes) {
		logger.info(" contact from submitted::" + c);

         boolean isSaved = contactService.saveContact(c);
		if (isSaved) {
			//model.addAttribute("succMsg", "Contact Saved");
			attributes.addFlashAttribute("msg", "Account Created Successfully");


		} else {
			//model.addAttribute("errMsg", "Failed to Save Contact");
			attributes.addFlashAttribute("errMsg", "Failed to Save Contact");

		}

	//	return "contactinfo";
		return "redirect:/userAccCreationSuccess";

	}
	
	@RequestMapping(value="/userAccCreationSuccess",method=RequestMethod.GET)
	public String userAccCreationSuccess(Model model) {
		logger.info("userAccCreationSuccess() method called");
		model.addAttribute("contact", new Contact());
		return "contactinfo";
		
	}
	
	@GetMapping("/viewContacts")
	public String handleViewContactsLink(Model model) {
		
		List<Contact> contactsList=contactService.getAllContacts();
		System.out.println(contactsList);

		model.addAttribute("contacts",contactsList);
		return "viewContacts";
		
	}
}
