package com.mandela.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.mandela.model.User;
import com.mandela.service.RegistrationService;

@RestController
public class RegistrationController {

	@Autowired
	private RegistrationService service;

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// to check it in postman = http://localhost:9090/registeruser (with request
	// body in json format)
	@PostMapping(path = "/registeruser")
	@CrossOrigin(origins = "*")
	public User registerUser(@RequestBody User user) throws Exception {
		Long tempAccountNo = user.getAccountno();

		if (tempAccountNo != null && !"".equals(tempAccountNo)) {
			User userobj = service.fetchUserByAccountNo(tempAccountNo);

			if (userobj != null) {
				throw new Exception(" user with Account no: " + tempAccountNo
						+ " is already existing please try again with another Account no ");
			}
		}
		User userObj = null;

		// to save it into DB
		userObj = service.saveUser(user);
		return userObj;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	// the url in post man is = http://localhost:9090/login (with request body in
	// json format)
	@PostMapping(path = "/login")
	@CrossOrigin(origins = "*")
	public User loginUser(@RequestBody User user) throws Exception {
		Long tempAccountNo = user.getAccountno();
		Long tempPinCode = user.getPincode();

		User userObj = null;
		// to check weather the emailId and password is exsisting in DB or not
		if (tempAccountNo != null && tempPinCode != null) {
			userObj = service.fetchUserByAccountNoAndPinCode(tempAccountNo, tempPinCode);
		}
		if (userObj == null) {
			throw new Exception("The email or the password is not correct");
		}
		return userObj;
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PutMapping(path = "/transfer")
	@CrossOrigin(origins = "*")
	public User transferMoney(@RequestBody User user) throws Exception {
		Long senderAccountNo = user.getAccountno();
		Long reciverAccountNo = user.getToaccountno();
		Long amount = user.getTransferamount();

		User userObj = null;
		User userObj1 = null;

		// to check weather the emailId and password is exsisting in DB or not
		if (senderAccountNo != null) {
			userObj = service.fetchUserByAccountNo(senderAccountNo);
			Long senderBalance = userObj.getBalance();
			if (senderBalance < amount) {
				throw new Exception("Sorry!!! you dont have sufficient balance.");
			}
			System.out.println(senderBalance);

			if (reciverAccountNo != null) {
				userObj1 = service.fetchUserByAccountNo(reciverAccountNo);
				Long reciverBalance = userObj1.getBalance();

				senderBalance = senderBalance - amount;
				userObj.setBalance(senderBalance);
				System.out.println(senderBalance);
				reciverBalance = reciverBalance + amount;
				userObj1.setBalance(reciverBalance);
				System.out.println(reciverBalance);
			}
		}
		userObj = service.saveUser(userObj);
		userObj1 = service.saveUser(userObj1);
		return userObj;

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PutMapping(path = "/deposit")
	@CrossOrigin(origins = "*")
	public User depositAmount(@RequestBody User user) throws Exception {

		Long depositAmount = user.getTransferamount();
		Long tempAccountNo = user.getAccountno();
		User userObj = null;

		// to check weather the emailId and password is exsisting in DB or not
		if (depositAmount != null && tempAccountNo != null) {
			userObj = service.fetchUserByAccountNo(tempAccountNo);
			if (userObj == null) {
				throw new Exception("Sorry the Account no is not correct");
			}
			Long mainBalance = userObj.getBalance();

			mainBalance = depositAmount + mainBalance;
			System.out.println(mainBalance);

			System.out.println("done 2");

			System.out.println(userObj.getBalance());
			userObj.setBalance(mainBalance);
			System.out.println(userObj.getBalance());
			System.out.println("done 2");
			System.out.println("done 2");
		} else {
			throw new Exception("deposit amount is not valid or account no is not correct");
		}

		return service.saveUser(userObj);

	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@PutMapping(path = "/withdraw")
	@CrossOrigin(origins = "*")
	public User withdrawAmount(@RequestBody User user) throws Exception {

		Long withdrawAmount = user.getTransferamount();
		Long tempAccountNo = user.getAccountno();
		User userObj = null;

		// to check weather the account no in DB or not and money correct
		if (withdrawAmount != null && tempAccountNo != null) {
			userObj = service.fetchUserByAccountNo(tempAccountNo);
			if (userObj == null) {
				throw new Exception("Sorry the Account no is not correct");
			}
			Long mainBalance = userObj.getBalance();
			mainBalance = mainBalance - withdrawAmount;
			if (mainBalance < 0) {
				throw new Exception("Sorry you don't have sufficient balance");
			}
			System.out.println(mainBalance);
			userObj.setBalance(mainBalance);
		}

		return service.saveUser(userObj);
	}

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	// @PostMapping(path = "/statement")
	@GetMapping(path = "/getUsers")
	@CrossOrigin(origins = "*")
//	public User getUser(@RequestBody User user) throws Exception {
	public User getUser(@RequestParam Long accNo) throws Exception {
		// requestparam annotation is used to get value from url .so as we gave name
		// accNo their i have declared here as accNo thats it
		// understood?sure any doubts? ask thats it cool i will disconnect now.remove
		// all comments wait yeah its ok now ok

		// leave the comments ok
		// Long tempAccountNo = user.getAccountno();

		Long tempAccountNo = accNo;
		User userObj = null;

		// to check weather the account no in DB or not and money correct
		if (tempAccountNo != null) {
			userObj = service.fetchUserByAccountNo(tempAccountNo);
			if (userObj == null) {
				throw new Exception("select account no");
			}
		}

		System.out.println("User :" + userObj.toString());
		return userObj;
	}
}
