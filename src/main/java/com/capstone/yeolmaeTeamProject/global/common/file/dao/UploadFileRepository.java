package com.capstone.yeolmaeTeamProject.global.common.file.dao;

import com.capstone.yeolmaeTeamProject.global.common.file.domain.UploadedFile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UploadFileRepository extends JpaRepository<UploadedFile, Long> {

    Optional<UploadedFile> findByUrl(String url);

    List<UploadedFile> findAllByUrlIn(List<String> fileUrls);
}
