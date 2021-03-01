package com.example.demojava.googlephoneutil;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

/**
 * @author HariomYadav
 * @since 28/10/20
 */
public class PhoneNumberValidate {
    // add maven dependency https://mvnrepository.com/artifact/com.googlecode.libphonenumber/libphonenumber/3.5

    public static void main(String[] args) throws NumberParseException {
        final PhoneNumberUtil phoneNumberUtil = PhoneNumberUtil.getInstance();
        final Phonenumber.PhoneNumber phoneNumber = phoneNumberUtil.parse("9887700499", "IN");
        if (phoneNumberUtil.isValidNumber(phoneNumber)) {
            System.out.println("phoneNumber = " + phoneNumber);

        } else {
            System.out.println("invalid phone number");
        }
    }

}
