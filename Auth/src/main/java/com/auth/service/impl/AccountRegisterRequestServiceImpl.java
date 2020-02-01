package com.auth.service.impl;

import com.auth.model.AccountRegisterRequest;
import com.auth.model.Customer;
import com.auth.model.User;
import com.auth.repository.AccountRegisterRequestRepository;
import com.auth.repository.CustomerRepository;
import com.auth.repository.UserRepository;
import com.auth.service.AccountRegisterRequestService;
import com.java.service.CommonService;
import com.java.service.CustomEmail;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service
@Transactional
public class AccountRegisterRequestServiceImpl implements AccountRegisterRequestService {

  private CustomEmail customEmail;
  private AccountRegisterRequestRepository accountRegisterRequestRepository;
  private CommonService commonService;
  private UserRepository userRepository;
  private CustomerRepository customerRepository;

  @Value("${server.url}")
  private String serverUrl;

  public AccountRegisterRequestServiceImpl(
    CustomEmail customEmail,
    AccountRegisterRequestRepository accountRegisterRequestRepository,
    CommonService commonService,
    UserRepository userRepository,
    CustomerRepository customerRepository
  ) {
    this.customEmail = customEmail;
    this.accountRegisterRequestRepository = accountRegisterRequestRepository;
    this.commonService = commonService;
    this.userRepository = userRepository;
    this.customerRepository = customerRepository;
  }

  @Override
  public void processCustomerRegistration(AccountRegisterRequest accountRegisterRequest) throws Exception {

    //check if already registered from this email
    AccountRegisterRequest existAccountRegisterRequest = accountRegisterRequestRepository.findFirstByEmail(accountRegisterRequest.getEmail());
    if(existAccountRegisterRequest != null){
      if(existAccountRegisterRequest.getRegistered() == 1){
        throw new Exception("This email already registered.");
      }else{
        accountRegisterRequest.setId(existAccountRegisterRequest.getId());
      }
    }
    //

    //generate verify code
    Boolean isCreatedNewVerifyCode = false;
    String verifyCode = null;
    while(!isCreatedNewVerifyCode){
      verifyCode = UUID.randomUUID().toString();
      AccountRegisterRequest alreadyInVerifyCodeRecord = accountRegisterRequestRepository.findFirstByVerifyCode(verifyCode);
      if(alreadyInVerifyCodeRecord == null){
        isCreatedNewVerifyCode = true;
      }
    }
    //

    String encodePassword = commonService.passwordEncoder(accountRegisterRequest.getPassword());
    accountRegisterRequest.setVerifyCode(verifyCode);
    accountRegisterRequest.setPassword(encodePassword);
    accountRegisterRequestRepository.save(accountRegisterRequest);

    String redirectUrlAfterVerify = serverUrl+"/account-register-request/no-auth/verify/"+verifyCode;

    //send email

    String html = "<div style=\"width: 100%;padding:20px;text-align: center;background: #f9f6f6\">\n" +
      "  <div style=\"\n" +
      "        background: white;\n" +
      "        width: 500px;\n" +
      "        margin: auto;\n" +
      "        padding: 40px;\n" +
      "      \">\n" +
      "    <h2>Thank you for signing in</h2>\n" +
      "    <h1>Verify your email address</h1>\n" +
      "    <p style=\"margin-bottom: 40px;\">Please confirm that you want to use this as your email address.</p>\n" +
      "\n" +
      "    <a\n" +
      "      style=\"\n" +
      "        background: #0cae38;\n" +
      "        padding: 14px 80px;\n" +
      "        border: 0;\n" +
      "        border-radius: 3px;\n" +
      "        color: white;\n" +
      "        font-size: 24px;\n" +
      "        margin-top: 10px;\n" +
      "        text-decoration: none;\n" +
      "      \"\n" +
      "      href=\""+redirectUrlAfterVerify+"\">Verify my email</a>\n" +
      "  </div>\n" +
      "</div>";

    String[] sendTo = new String[]{accountRegisterRequest.getEmail()};

    customEmail.sendHtmlEmail(
      "Verification",
      sendTo,
      null,
      null,
      html
    );

  }

  @Override
  public void verifyCustomerFromVerifyCode(String verifyCode) throws Exception {

    AccountRegisterRequest existAccountRegisterRequest = accountRegisterRequestRepository.findFirstByVerifyCode(verifyCode);

    if(existAccountRegisterRequest == null){
      throw new Exception("Verification request was expired.");
    }

    if(existAccountRegisterRequest.getRegistered() == 1){
      throw new Exception("This account was already verified.");
    }

    //set registered status
    existAccountRegisterRequest.setRegistered(1);
    accountRegisterRequestRepository.save(existAccountRegisterRequest);

    //create user
    User user = new User();
    user.setEmail(existAccountRegisterRequest.getEmail());
    user.setPassword(existAccountRegisterRequest.getPassword());
    user.setStatus(true);
    userRepository.save(user);

    //create customer
    Customer customer = new Customer();
    customer.setIsActive(true);
    customer.setUserId(user.getId());
    customerRepository.save(customer);

  }




}
