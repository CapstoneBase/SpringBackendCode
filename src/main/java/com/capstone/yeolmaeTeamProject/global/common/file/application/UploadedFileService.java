package com.capstone.yeolmaeTeamProject.global.common.file.application;

import com.capstone.yeolmaeTeamProject.global.common.file.dao.UploadFileRepository;
import com.capstone.yeolmaeTeamProject.global.common.file.domain.UploadedFile;
import com.capstone.yeolmaeTeamProject.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class UploadedFileService {

    private final UploadFileRepository uploadFileRepository;

    public UploadedFile saveUploadedFile(UploadedFile uploadedFile) {
        return uploadFileRepository.save(uploadedFile);
    }

    public UploadedFile getUploadedFileByUrl(String url) {
        return uploadFileRepository.findByUrl(url)
                .orElseThrow(() -> new NotFoundException("파일을 찾을 수 없습니다."));
    }

    public List<UploadedFile> getUploadedFilesByUrls(List<String> fileUrls) {
        if (fileUrls == null || fileUrls.isEmpty()) {
            return new ArrayList<>();
        }
        List<UploadedFile> uploadedFiles = uploadFileRepository.findAllByUrlIn(fileUrls);
        if (uploadedFiles.size() != fileUrls.size()) {
            throw new NotFoundException("서버에 업로드되지 않은 파일이 포함되어 있습니다.");
        }
        return uploadedFiles;
    }
}
