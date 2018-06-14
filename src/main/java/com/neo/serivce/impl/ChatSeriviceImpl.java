package com.neo.serivce.impl;

import com.neo.dao.UserDao;
import com.neo.entity.BaseEntity;
import com.neo.entity.MessageEntity;
import com.neo.entity.UserEntity;
import com.neo.serivce.ChatSerivice;
import com.neo.serivce.UserSerivice;
import com.turo.pushy.apns.ApnsClient;
import com.turo.pushy.apns.ApnsClientBuilder;
import com.turo.pushy.apns.PushNotificationResponse;
import com.turo.pushy.apns.auth.ApnsSigningKey;
import com.turo.pushy.apns.util.ApnsPayloadBuilder;
import com.turo.pushy.apns.util.SimpleApnsPushNotification;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * Created by liudong on 2018/6/8.
 */
@Component
public class ChatSeriviceImpl<T extends BaseEntity> extends BaseSeriviceImpl<MessageEntity> implements ChatSerivice<MessageEntity> {

    @Override
    public void saveMessageData(MessageEntity entity) {
        long time = new Date().getTime();
        entity.setTimestamp(time);
        saveEntity(entity);
    }

    @Override
    public void sendApnData() {
        final ApnsClient apnsClient;

        try {
            apnsClient = new ApnsClientBuilder()
                    .setApnsServer(ApnsClientBuilder.PRODUCTION_APNS_HOST)
                    .setSigningKey(ApnsSigningKey.loadFromPkcs8File(new ClassPathResource("AuthKey.p8").getFile(),
                            "5HBP8N48W6", "SXZZL6BZ83"))
                    .build();
            final ApnsPayloadBuilder payloadBuilder = new ApnsPayloadBuilder();
            payloadBuilder.setAlertBody("Example!");
            payloadBuilder.setBadgeNumber(1);
            payloadBuilder.setAlertBody("sdfsdfsfsddf0");
            final String payload = payloadBuilder.buildWithDefaultMaximumLength();
            final String token = "431b9699945a0fa11e692f9a281e3be5deec70ea61c8530aef7efbcc098b7e71";

            SimpleApnsPushNotification pushNotification = new SimpleApnsPushNotification(token, "com.example.myApp", payload);
            final Future sendNotificationFuture = apnsClient.sendNotification(pushNotification);

            sendNotificationFuture.addListener(new GenericFutureListener<Future<PushNotificationResponse>>() {

                @Override
                public void operationComplete(final Future<PushNotificationResponse> future) throws Exception {
                    // This will get called when the sever has replied and returns immediately
                    final PushNotificationResponse response = future.getNow();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        }

    }
}
