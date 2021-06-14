package com.example.no_exception_trello_c1220g1.repository;

import com.example.no_exception_trello_c1220g1.model.Entity.MediaFile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMediaFileRepository extends JpaRepository<MediaFile,Long> {
}
