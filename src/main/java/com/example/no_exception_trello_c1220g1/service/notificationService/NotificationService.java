package com.example.no_exception_trello_c1220g1.service.notificationService;

import com.example.no_exception_trello_c1220g1.model.Entity.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
//    @Autowired
//    private NotificationRepo notificationRepo;
//
//    @Autowired
//    private NotificationUserRepo notificationUserRepo;
//
//    @Override
//    public List<Notification> findAll() {
//        return null;
//    }
//
//    @Override
//    public Notification findById(Long id) {
//        return null;
//    }
//
//    @Override
//    public Notification save(Notification notification) {
//        return notificationRepo.save(notification);
//    }
//
//    @Override
//    public void delete(Long id) {
//
//    }
//    @Override
//    public List<Notification> findNotificationByUser(Long id) {
//        return notificationRepo.findNotificationByAppUserQ(id);
//    }
//
//    @Override
//    @Transactional
//    public void deleteNotification_appUsersByAppUser_Id(Long id) {
//        notificationUserRepo.deleteNotification_appUsersByAppUser_Id(id);
//    }
}
