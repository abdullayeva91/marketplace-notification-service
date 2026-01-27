package com.marketplace.marketplacenotificationservice.service;

import com.marketplace.auth.UserRequest;
import com.marketplace.auth.UserResponse;
import com.marketplace.auth.UserServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.springframework.stereotype.Service;

@Service
public class TestService {

    @GrpcClient("auth-service")
    private UserServiceGrpc.UserServiceBlockingStub userServiceStub;

    public void getUserDataAndSendEmail(long userId) {
        try {
            UserRequest request = UserRequest.newBuilder()
                    .setUserId(userId)
                    .build();

            UserResponse response = userServiceStub.getUserById(request);

            String email = response.getEmail();
            String username = response.getUsername();

            System.out.println("Auth Service-dən məlumat alındı: " + email);


        } catch (Exception e) {
            System.err.println("gRPC zəngi zamanı xəta: " + e.getMessage());
        }
    }
}