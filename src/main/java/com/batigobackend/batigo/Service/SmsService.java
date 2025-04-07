package com.batigobackend.batigo.Service;

import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {



    public void sendLowStockAlert(String message) {
      /*  Message.creator(
                new PhoneNumber("+21627693844"),
                new PhoneNumber("+19064214677"),
                message
        ).create();
*/        System.out.println(message);
    }
}
