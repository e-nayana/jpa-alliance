package com.auth.service;

import com.auth.model.AccountRegisterRequest;
import org.springframework.transaction.annotation.Transactional;

public interface AccountRegisterRequestService {

  void processCustomerRegistration(AccountRegisterRequest accountRegisterRequest) throws Exception;
  void verifyCustomerFromVerifyCode(String verifyCode) throws Exception;
}
