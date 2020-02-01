package com.auth.controller;

import com.auth.model.AccountRegisterRequest;
import com.auth.service.AccountRegisterRequestService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("account-register-request")
@CrossOrigin
public class AccountRegisterRequestController {

  private AccountRegisterRequestService accountRegisterRequestService;

  public AccountRegisterRequestController(AccountRegisterRequestService accountRegisterRequestService) {
    this.accountRegisterRequestService = accountRegisterRequestService;
  }

  @PostMapping("no-auth/customer-registration-request")
  public ResponseEntity saveCustomerRegistrationRequest(@Valid @RequestBody AccountRegisterRequest accountRegisterRequest) {
    try {
      accountRegisterRequestService.processCustomerRegistration(accountRegisterRequest);
      Map<String, String> responseData = new HashMap<>();
      responseData.put("title","Check your email inbox");
      responseData.put("message","We sent an email to verify your registration.");
      return ResponseEntity
        .status(HttpStatus.OK)
        .body(responseData);
    } catch (Exception exp) {
      return ResponseEntity
        .status(HttpStatus.INTERNAL_SERVER_ERROR)
        .body(exp.getMessage());
    }

  }

  @GetMapping("no-auth/verify/{verifyCode}")
  public ModelAndView verifyAccountFromVerifyCode(@PathVariable String verifyCode , HttpServletRequest request) {

    request.setAttribute(
      View.RESPONSE_STATUS_ATTRIBUTE,
      HttpStatus.TEMPORARY_REDIRECT
    );

    try {
      accountRegisterRequestService.verifyCustomerFromVerifyCode(verifyCode);
      return new ModelAndView("redirect:http://localhost:4905/#/classic/account/login");
    } catch (Exception exp) {
      return new ModelAndView("redirect:http://localhost:4905/#/classic/account/login");
    }
  }

  @GetMapping("no-auth/create-cus")
  public int testUserCreate(){
    return 1;
  }

}
