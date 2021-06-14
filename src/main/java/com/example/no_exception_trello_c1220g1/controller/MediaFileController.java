package com.example.no_exception_trello_c1220g1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/mediafile")
public class MediaFileController {
//    @Autowired
//    private MediaFileService mediaFileService;
//    @GetMapping("findByCard/{id}")
//    public ResponseEntity<List<MediaFile>> findMediaFileCard(@PathVariable Long id){
//        return new ResponseEntity<>(mediaFileService.findMediaFileByCard(id), HttpStatus.OK);
//    }
//    @PostMapping("create")
//    public ResponseEntity<MediaFile> createMediaFile(@RequestBody MediaFile mediaFile){
//        return new ResponseEntity<>(mediaFileService.save(mediaFile),HttpStatus.OK);
//    }
}
