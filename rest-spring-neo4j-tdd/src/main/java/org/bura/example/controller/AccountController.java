package org.bura.example.controller;

import java.util.List;

import org.bura.example.dao.AccountDao;
import org.bura.example.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "account")
public class AccountController {

	@Autowired
	private AccountDao dao;

	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseBody
	public long createAccount(@RequestBody Account account) {
		dao.createAccount(account);

		return account.getId();
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Account> getAccounts() {
		return dao.getAccounts();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Account getAccount(@PathVariable("id") long id) {
		return dao.getAccount(id);
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
	public void removeAccount(@PathVariable("id") long id) {
		dao.remove(id);
	}

}
