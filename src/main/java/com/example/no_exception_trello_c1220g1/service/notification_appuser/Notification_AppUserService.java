package com.example.no_exception_trello_c1220g1.service.notification_appuser;

import com.example.no_exception_trello_c1220g1.model.entity.Notification_appUser;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class Notification_AppUserService implements INotification_AppUserService{
    @Override
    public List<Notification_appUser> findAll() {
        return null;
    }

    @Override
    public Optional<Notification_appUser> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Notification_appUser save(Notification_appUser notification_appUser) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
