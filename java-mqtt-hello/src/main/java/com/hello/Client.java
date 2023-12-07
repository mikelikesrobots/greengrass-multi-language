package com.hello;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

import software.amazon.awssdk.aws.greengrass.GreengrassCoreIPCClientV2;
import software.amazon.awssdk.aws.greengrass.model.PublishToIoTCoreRequest;
import software.amazon.awssdk.aws.greengrass.model.PublishToIoTCoreResponse;
import software.amazon.awssdk.aws.greengrass.model.QOS;

public class Client {

    GreengrassCoreIPCClientV2 mIpcClient;
    String mMessage;

    public Client(String message) {
        try {
            mIpcClient = GreengrassCoreIPCClientV2.builder().build();
        } catch (Exception ex) {
            System.out.println("Failed to create Greengrass IPC client!");
            System.exit(-1);
        }
        if (mIpcClient == null) {
            System.out.println("Failed to create Greengrass IPC client!");
            System.exit(-1);
        }

        mMessage = String.format("{\"message\": \"%s\"}", message);
    }

    public void tick() {
        PublishToIoTCoreRequest publishRequest = new PublishToIoTCoreRequest();
        publishRequest.setQos(QOS.AT_LEAST_ONCE);
        publishRequest.setTopicName("hello");
        publishRequest.withPayload(mMessage.getBytes(StandardCharsets.UTF_8));
        CompletableFuture<PublishToIoTCoreResponse> publishFuture = mIpcClient.publishToIoTCoreAsync(publishRequest);

        try {
            publishFuture.get(5, TimeUnit.SECONDS);
            System.out.println("Successfully published IPC message to IoT Core");
        } catch (Exception ex) {
            System.out.println(String.format("Failed to publish IPC message to IoT Core: %s", ex));
        }
    }
}