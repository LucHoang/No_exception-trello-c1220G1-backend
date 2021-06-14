package com.example.no_exception_trello_c1220g1.service.media;

import com.example.no_exception_trello_c1220g1.model.Entity.MediaFile;
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
}
