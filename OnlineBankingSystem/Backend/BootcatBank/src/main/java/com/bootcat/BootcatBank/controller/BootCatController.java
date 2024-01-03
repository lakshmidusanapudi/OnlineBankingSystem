package com.bootcat.BootcatBank.controller;

import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.bootcat.BootcatBank.model.Customers;
import com.bootcat.BootcatBank.model.TransactionHistory;
import com.bootcat.BootcatBank.model.TransactionModel;
import com.bootcat.BootcatBank.repo.CustomersRepo;
import com.bootcat.BootcatBank.repo.TransactionRepo;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BootCatController {

	public static int key = 0;

	@Autowired
	private Customers customer;

	@Autowired
	private CustomersRepo customerrepo;

	@Autowired
	private TransactionRepo tranRepo;

	@GetMapping("/getall")
	public ArrayList<Customers> getAllCustomers() {
		ArrayList<Customers> userlist = new ArrayList<>();
		customerrepo.findAll().forEach(userlist::add);
		return userlist;
	}

	@GetMapping("/get/{id}")
	public Customers getCustomer(@PathVariable Integer id) {
		return customerrepo.findById(id).get();
	}

	@PostMapping("/post")
	public String addCustomer(@RequestBody Customers customer) {
		customerrepo.save(customer);
		return "Added";
	}

	@PutMapping("/update")
	public String updateCustomer(@RequestBody Customers customer) {

		Customers newCustomer = new Customers(customer.getId(), customer.getName(), customer.getEmail(),
				customer.getMobile(), customer.getBalance());
		customerrepo.save(newCustomer);
		return "Update";
	}

	@Autowired
	private TransactionModel transaction;

	@Autowired
	private TransactionHistory newTrans;

	@PostMapping("/transaction")
	public String updateBalance(@RequestBody TransactionModel transaction) {
		int id = transaction.getId();
		int amount = transaction.getBalance();
		transaction.setTransactionType("Credit");
		Customers update = customerrepo.findById(id).get();
		amount = amount + customerrepo.findById(id).get().getBalance();
		update.setBalance(amount);
		customerrepo.save(update);
		newTrans.setId(++key);
		newTrans.setAccount_holder(customerrepo.findById(id).get().getName());
		newTrans.setAccount_number(customerrepo.findById(id).get().getId());
		newTrans.setAmount(transaction.getBalance());
		newTrans.setTransactionType("Credit");
		tranRepo.save(newTrans);

		return "Sent";
	}

	@PostMapping("/transaction/debit")

	public String debitBalance(@RequestBody TransactionModel transaction) {
		int id = transaction.getId();
		int amount = transaction.getBalance();
		transaction.setTransactionType("Debit");
		Customers customer = customerrepo.findById(id).orElse(null);

		if (customer != null) {
			int currentBalance = customer.getBalance();

			if (currentBalance >= amount) {
				// Sufficient balance, proceed with debit
				currentBalance -= amount;
				customer.setBalance(currentBalance);
				customerrepo.save(customer);
				newTrans.setId(++key);
				newTrans.setAccount_holder(customerrepo.findById(id).get().getName());
				newTrans.setAccount_number(customerrepo.findById(id).get().getId());
				newTrans.setAmount(transaction.getBalance());
				newTrans.setTransactionType("Debit");
				tranRepo.save(newTrans);
				return "Debited";
			} else {
				return "Insufficient balance";
			}
		} else {
			return "Customer not found";
		}
	}

	@GetMapping("/transaction")
	public ArrayList<TransactionHistory> showTransactions() {

		ArrayList<TransactionHistory> history = new ArrayList<>();
		tranRepo.findAll().forEach(history::add);
		return history;

	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable Integer id) {
		customerrepo.deleteById(id);
		return "Deleted";
	}
}