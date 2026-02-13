package org.holo.service;

import lombok.RequiredArgsConstructor;
import org.holo.content.UserContent;
import org.holo.entity.AppSetting;
import org.holo.repo.AppSettingRepository;
import org.springframework.stereotype.Service;
@Service
@RequiredArgsConstructor
public class AppSettingHistoryService {
    private final AppSettingRepository appSettingRepository;
    private final UserContent userContent;

    public void saveAppSetting(AppSetting appSetting) {
        appSetting.setUserId(userContent.getUserId());
        appSettingRepository.save(appSetting);
    }

    public AppSetting queryAppSetting() {
        return appSettingRepository.queryFirstByUserId(userContent.getUserId());
    }
}
