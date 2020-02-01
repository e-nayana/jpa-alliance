package com.auth.repository;

import com.auth.model.AccountRegisterRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRegisterRequestRepository extends JpaRepository<AccountRegisterRequest, Long> {

  AccountRegisterRequest findById(Integer id);
  AccountRegisterRequest findFirstByVerifyCode(String verifyCode);
  AccountRegisterRequest findFirstByEmail(String email);
}
