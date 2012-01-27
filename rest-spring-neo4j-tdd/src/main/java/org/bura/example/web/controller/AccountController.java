package org.bura.example.web.controller;

import java.util.List;

import org.bura.example.app.domain.dto.Account;
import org.bura.example.app.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "account")
public class AccountController {

	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	public long createAccount(@RequestBody Account account) {
		accountService.create(account);

		return account.getId();
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Account> getAccounts() {
		return accountService.getAll();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Account getAccount(@PathVariable("id") long id) {
		return accountService.get(id);
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void removeAccount(@PathVariable("id") Long id) {
		accountService.remove(id);
	}

}
