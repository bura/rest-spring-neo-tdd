package org.bura.example.controller;

import java.util.List;

import org.bura.example.dao.AccountDao;
import org.bura.example.dto.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
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
	private AccountDao accountDao;

	@RequestMapping(value = "create", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	@ResponseBody
	@Transactional
	public long createAccount(@RequestBody Account account) {
		accountDao.createAccount(account);

		return account.getId();
	}

	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public List<Account> getAccounts() {
		return accountDao.getAccounts();
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public Account getAccount(@PathVariable("id") long id) {
		return accountDao.getAccount(id);
	}

	@RequestMapping(value = "remove/{id}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	@Transactional
	public void removeAccount(@PathVariable("id") long id) {
		accountDao.remove(id);
	}

}
