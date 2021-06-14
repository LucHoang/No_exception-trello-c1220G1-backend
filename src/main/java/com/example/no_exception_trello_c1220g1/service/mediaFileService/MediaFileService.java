package com.example.no_exception_trello_c1220g1.service.mediaFileService;

import com.example.no_exception_trello_c1220g1.model.Entity.MediaFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MediaFileService implements IMediaFileService{
    @Override
    public List<MediaFile> findAll() {
        return null;
    }

    @Override
    public Optional<MediaFile> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public MediaFile save(MediaFile mediaFile) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
//    @Autowired
//    private MediaFile_repo mediaFile_repo;
//    @Override
//    public List<MediaFile> findAll() {
//        return (List<MediaFile>) mediaFile_repo.findAll();
//    }
//
//    @Override
//    public MediaFile findById(Long id) {
//        return mediaFile_repo.findById(id).get();
//    }
//
//    @Override
//    public MediaFile save(MediaFile mediaFile) {
//        return mediaFile_repo.save(mediaFile);
//    }
//
//    @Override
//    public void delete(Long id) {
//        mediaFile_repo.deleteById(id);
//    }
//
//    @Override
//    public List<MediaFile> findMediaFileByCard(Long card_id) {
//        return mediaFile_repo.findMediaFileByCard_Id(card_id);
//    }
}
