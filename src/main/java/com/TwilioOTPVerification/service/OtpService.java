package com.TwilioOTPVerification.service;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class OtpService {

    private final Map<String, String> otpMap = new HashMap<>();
    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    @Value("${otp.length}")
    private int otpLength;

    public void sendOtp(String phoneNumber) {
        String otp = generateOtp();
        otpMap.put(phoneNumber, otp); // Store OTP in the map with phone number as key
        Twilio.init(accountSid, authToken);
        String messageBody = "Your OTP for verification: " + otp;
        Message message = Message.creator(
                        new PhoneNumber(phoneNumber),
                        new PhoneNumber(twilioPhoneNumber),
                        messageBody)
                .create();
        System.out.println("Message SID: " + message.getSid());
    }

    public String generateOtp() {
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < otpLength; i++) {
            otp.append((int) (Math.random() * 10));
        }
        return otp.toString();
    }

    public boolean verifyOtp(String otpEntered, String phoneNumber) {
        String storedOtp = otpMap.get(phoneNumber);                   // Retrieve OTP from the map using phone number
        if (storedOtp != null) {
            return otpEntered.equals(storedOtp);
        }
        return false;
    }
}
