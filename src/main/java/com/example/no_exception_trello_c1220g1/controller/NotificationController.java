package com.example.no_exception_trello_c1220g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
public class NotificationController {
//    @Autowired
//    private INotificationService iNotificationService;
//    @Autowired
//    private IAppUserService userService;
//
//    @MessageMapping("/notice")
//    @SendTo("/topic/notice")
//    public ResponseEntity<Notification> create(@RequestBody Notification notification){
//        return new ResponseEntity<>(iNotificationService.save(notification), HttpStatus.OK);
//    }
//    @GetMapping("notification")
//    public ResponseEntity<List<Notification>> findNotificationByUser(){
//        AppUser appUser = userService.getUserCurrent();
//        return new ResponseEntity<>(iNotificationService.findNotificationByUser(appUser.getId()),HttpStatus.OK);
//    }
//    @GetMapping("notification/users/{id}")
//    public ResponseEntity<List<AppUser>> findUserByBoard(@PathVariable Long id){
//        return new ResponseEntity<>(userService.findUserAndTagUserByBoard(id),HttpStatus.OK);
//    }
//    @DeleteMapping("notification")
//    public ResponseEntity<?> deleteNotificationByUser(){
//        AppUser appUser = userService.getUserCurrent();
//        iNotificationService.deleteNotification_appUsersByAppUser_Id(appUser.getId());
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
}
