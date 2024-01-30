package com.example.ibm;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.http.HttpHeaders;

//import javax.security.auth.login.AccountNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.ibm.exception.AccountNotFoundException;
import com.example.ibm.exception.MinimumBalanceException;
import com.example.ibm.exception.ui.ErrorResponse;
import com.example.ibm.model.Account;
import com.example.ibm.service.AccountService;

@RestController
public class AccountApi {

	
	
	private final AccountService accountService;

	@ExceptionHandler
	public ErrorResponse handleException(AccountNotFoundException e) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	
	@ExceptionHandler
	public ErrorResponse handleException(MinimumBalanceException e) {

		ErrorResponse response = new ErrorResponse();
		response.setMessage(e.getMessage());
		response.setStatusCode(HttpStatus.BAD_REQUEST.value());
		response.setToOfError(System.currentTimeMillis());
		return response;
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, List<String>>> handleValidationErrors(MethodArgumentNotValidException ex) {
		List<String> errors = ex.getBindingResult().getFieldErrors().stream().map(FieldError::getDefaultMessage)
				.collect(Collectors.toList());
		return new ResponseEntity<>(getErrorsMap(errors), new HttpHeaders(), HttpStatus.BAD_REQUEST);
	}

	private Map<String, List<String>> getErrorsMap(List<String> errors) {
		Map<String, List<String>> errorResponse = new HashMap<>();
		errorResponse.put("errors", errors);
		return errorResponse;
	}
	
	// or you can use @AllArgsCOnstrucotr
	public AccountApi(AccountService accountService) {
		super();
		this.accountService = accountService;
	}
	
	
	///------------or above class name write @RequestMapping("/accounts")-----
	@PostMapping("/accounts")
	public ResponseEntity<Account> createAccount(@RequestBody Account account)
	{
		return new ResponseEntity<Account> (accountService.createAccount(account), HttpStatus.OK);
	}
	
	
	@GetMapping("/accounts")
	public ResponseEntity<List<Account>> listAccounts()
	{
		return ResponseEntity.status(HttpStatus.OK).body(accountService.getAllAccounts());
	}
	
	
	@GetMapping("/accounts/{accountNumber}")
	public ResponseEntity<?> getAccountByNumber(@PathVariable("accountNumber") String accountNumber)
			throws AccountNotFoundException {
		return new ResponseEntity<Account>(accountService.getAccountByaccountNumber(accountNumber),HttpStatus.OK);
	}
	
	@PutMapping("/accounts/{accountNumber}")
	public ResponseEntity<?> updateAccount(@PathVariable("accountNumber") String accountNumber, @RequestBody Account account) throws AccountNotFoundException 
	{
		return ResponseEntity.status(HttpStatus.ACCEPTED)
				.body(accountService.updateAccountByAccountNumber(accountNumber, account));
	}
	
	@DeleteMapping("/accounts/{accountNumber}")
	public ResponseEntity<?> deleteAccount(@PathVariable("accountNumber") String accountNumber) throws AccountNotFoundException
	{
		accountService.deleteAccountByAccountNumber(accountNumber);
		return ResponseEntity.status(HttpStatus.OK).body("deletion succeed"); 	 
	}
	
	@PutMapping("/accounts/updateAmount/{accountNumber}")
	public ResponseEntity<?> depositAmount(@PathVariable("accountNumber") String accountNumber , @RequestBody Double amount ) throws AccountNotFoundException
	{
		System.out.println("amount"+amount);
		System.out.println();
		return  ResponseEntity.status(HttpStatus.OK).body(accountService.depositAmount(accountNumber, amount));
	}
	
	@PutMapping("/accounts/withdrawAmount/{accountNumber}")
	public ResponseEntity<?> withdrawAmount(@PathVariable("accountNumber") String accountNumber , @RequestBody Double amount ) throws AccountNotFoundException, MinimumBalanceException
	{
		System.out.println("amount"+amount);
		System.out.println();
		return  ResponseEntity.status(HttpStatus.OK).body(accountService.withdrawAmount(accountNumber, amount));
	//	return accountService.withdrawAmount(accountNumber, amount);
	}
	
	

	
}
