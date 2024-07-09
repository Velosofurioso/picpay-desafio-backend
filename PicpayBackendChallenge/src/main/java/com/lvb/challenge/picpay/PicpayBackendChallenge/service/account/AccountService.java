package com.lvb.challenge.picpay.PicpayBackendChallenge.service.account;

import com.lvb.challenge.picpay.PicpayBackendChallenge.configuration.cache.RedisTemplateConfig;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.AccountBase;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.Seller;
import com.lvb.challenge.picpay.PicpayBackendChallenge.entity.User;
import com.lvb.challenge.picpay.PicpayBackendChallenge.enums.ValidationType;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.SellerRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.repository.UserRepository;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.email.EmailService;
import com.lvb.challenge.picpay.PicpayBackendChallenge.service.sms.SmsService;
import com.lvb.challenge.picpay.PicpayBackendChallenge.util.Utils;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.Duration;
import java.util.Objects;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private SellerRepository sellerRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;

    @Autowired
    private RedisTemplateConfig redisTemplate;


    public void sendValidationCode(final long accountId, final ValidationType validationType) throws MessagingException, IOException {

        //Retrieve data
        var account = findAccountById(accountId);

        if (account == null) {
            throw new RuntimeException("User not found");
        }

        // Business Validation
        //accountValidation.newAccountValidation(createSellerDto, sellerRepository);

        // Generate code
        long code = Utils.generateRandomNumber();

        // Send email
        if (validationType == ValidationType.EMAIL)
            emailService.sendEmailCode(account, code);

        // Send Phone Message
        else if (validationType == ValidationType.PHONE)
            smsService.sendCode(account, code);

        // Save code on cache for user
        redisTemplate.saveWithExpire(accountId, code, Duration.ofMinutes(5L));
    }

    public void validateCode(final long accountId, final String code, final ValidationType validationType) {

        if (!redisTemplate.contain(accountId)) {
            throw new RuntimeException("Code expired, generate new one and try again");
        }
        else if (!Utils.isNumeric(code)) {
            throw new RuntimeException("Code no equals to expected");
        }

        // Get data from redis cache
        final Long expectedCode = Utils.parseLong((String) redisTemplate.find(accountId));

        if (!Objects.equals(Utils.parseLong(code), expectedCode)) {
           throw new RuntimeException("Code no equals to expected");
        }

        // Retrieve data
        var account = findAccountById(accountId);
        if (validationType == ValidationType.EMAIL)
            account.setEmailValidated(true);
        else if (validationType == ValidationType.PHONE)
            account.setPhoneNumberValidated(true);

        // Update account
        if (account instanceof Seller)
            sellerRepository.save((Seller) account);
        else
            userRepository.save((User) account);

        // Remove code from cache
        redisTemplate.remove(accountId);
    }

    private AccountBase findAccountById(final long accountId) {
        Optional<AccountBase> account;

        account = sellerRepository.findById(accountId).map(seller -> seller);
        if (account.isPresent()) {
            return account.get();
        }

        account = userRepository.findById(accountId).map(user -> user);
        return account.orElse(null);
    }

}
