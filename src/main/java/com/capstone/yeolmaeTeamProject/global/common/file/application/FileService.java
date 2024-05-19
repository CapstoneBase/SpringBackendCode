package com.capstone.yeolmaeTeamProject.global.common.file.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileService {

    @Value("${resource.file.url}")
    private String fileURL;

    private final FileHandler fileHandler;

    public List<String> saveFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<String> uploadedFileUrls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            String fileUrls = saveFile(multipartFile);
            uploadedFileUrls.add(fileUrls);
        }
        return uploadedFileUrls;
    }

    public String saveFile(MultipartFile multipartFile) throws IOException {
        String savedFilePath = fileHandler.saveFile(multipartFile);
        String fileName = new File(savedFilePath).getName();
        return fileURL + "/" + fileName;
    }
}
