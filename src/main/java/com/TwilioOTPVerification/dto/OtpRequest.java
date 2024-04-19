package com.TwilioOTPVerification.dto;

import lombok.Data;

@Data
public class OtpRequest {
    private String phoneNumber;
    private String otp;


    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}