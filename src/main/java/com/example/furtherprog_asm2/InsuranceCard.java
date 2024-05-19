package com.example.furtherprog_asm2;

/**
 * @author <Dong Dang Khoa- s3986281>
 */
public class InsuranceCard {
    private String cardNumber;
    private String CardHolder;
    private String policyOwner;
    private String expirationDate;
    private double rate; // The rate of the insurance card

    public InsuranceCard() {
        this.cardNumber = "default";
        CardHolder = "default";
        this.policyOwner = "default";
        this.expirationDate = "default";
        this.rate = 0.3; // Set the default rate to 0.05
    }

    public InsuranceCard(String cardNumber, String cardHolder, String policyOwner, String expirationDate) {
        this.cardNumber = cardNumber;
        CardHolder = cardHolder;
        this.policyOwner = policyOwner;
        this.expirationDate = expirationDate;
        this.rate = 0.3; // Set the default rate to 0.05
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardHolder() {
        return CardHolder;
    }

    public void setCardHolder(String cardHolder) {
        CardHolder = cardHolder;
    }

    public String getPolicyOwner() {
        return policyOwner;
    }

    public void setPolicyOwner(String policyOwner) {
        this.policyOwner = policyOwner;
    }

    public String getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(String expirationDate) {
        this.expirationDate = expirationDate;
    }
    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "InsuranceCard{" +
                "cardNumber='" + cardNumber + '\'' +
                ", CardHolder='" + CardHolder + '\'' +
                ", policyOwner='" + policyOwner + '\'' +
                ", expirationDate='" + expirationDate + '\'' +
                '}';
    }
}
