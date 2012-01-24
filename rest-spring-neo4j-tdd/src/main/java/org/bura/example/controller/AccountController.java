package org.bura.example.controller;

import java.util.List;

import org.bura.example.dto.Account;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "account")
public class AccountController {

	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public long createAccount(@RequestBody Account account) {
		// TODO
		return 0L;
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Account> getAccounts() {
		// TODO
		return null;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Account getAccount(@PathVariable("id") long id) {
		// TODO
		return null;
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.POST, consumes = "application/json")
	public void removeAccount(@PathVariable("id") long id) {
		// TODO
	}

}
