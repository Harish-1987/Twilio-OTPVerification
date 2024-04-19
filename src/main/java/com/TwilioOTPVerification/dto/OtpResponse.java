    package com.TwilioOTPVerification.dto;

    import lombok.Data;

    @Data
    public class OtpResponse {
        private String message;

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }