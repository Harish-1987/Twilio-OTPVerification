package com.TwilioOTPVerification.controller;

import com.TwilioOTPVerification.dto.OtpRequest;
import com.TwilioOTPVerification.dto.OtpResponse;
import com.TwilioOTPVerification.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OtpController {

    private final OtpService otpService;

    @Autowired
    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }



    //     http://localhost:8080/generate-otp
    @PostMapping("/generate-otp")
    public ResponseEntity<OtpResponse> generateOtp(@RequestBody OtpRequest otpRequest) {
        otpService.sendOtp(otpRequest.getPhoneNumber());
        OtpResponse response = new OtpResponse();
        response.setMessage("OTP sent successfully");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //    http://localhost:8080/verify-otp
    @PostMapping("/verify-otp")
    public ResponseEntity<OtpResponse> verifyOtp(@RequestBody OtpRequest otpRequest) {
        boolean isValid = otpService.verifyOtp(otpRequest.getOtp(), otpRequest.getPhoneNumber());
        OtpResponse response = new OtpResponse();
        if (isValid) {
            response.setMessage("OTP verification successful");
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            response.setMessage("Invalid OTP");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
    }
}
