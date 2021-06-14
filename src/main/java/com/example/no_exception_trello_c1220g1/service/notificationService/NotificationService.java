package com.example.no_exception_trello_c1220g1.service.notificationService;

import com.example.no_exception_trello_c1220g1.model.Entity.Notification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class NotificationService implements INotificationService{
    @Override
    public List<Notification> findAll() {
        return null;
    }

    @Override
    public Optional<Notification> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Notification save(Notification notification) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
