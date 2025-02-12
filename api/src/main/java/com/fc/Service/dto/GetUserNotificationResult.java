package com.fc.Service.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GetUserNotificationResult {

    private List<ConvertedNotification> notifications;
    private boolean hasNext;

}
