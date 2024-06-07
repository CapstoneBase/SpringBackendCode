package com.capstone.yeolmaeTeamProject.global.common.file.application;

import com.capstone.yeolmaeTeamProject.domain.user.application.UserService;
import com.capstone.yeolmaeTeamProject.domain.user.domain.User;
import com.capstone.yeolmaeTeamProject.global.common.file.domain.UploadedFile;
import com.capstone.yeolmaeTeamProject.global.common.file.dto.request.DeleteFileRequestDto;
import com.capstone.yeolmaeTeamProject.global.common.file.dto.response.UploadedFileResponseDto;
import com.capstone.yeolmaeTeamProject.global.exception.NotFoundException;
import com.capstone.yeolmaeTeamProject.global.exception.PermissionDeniedException;
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

    private final UserService userService;

    private final UploadedFileService uploadedFileService;

    public List<UploadedFileResponseDto> saveFiles(List<MultipartFile> multipartFiles) throws IOException {
        List<UploadedFileResponseDto> uploadedFileUrls = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            UploadedFileResponseDto fileUrls = saveFile(multipartFile);
            uploadedFileUrls.add(fileUrls);
        }
        return uploadedFileUrls;
    }

    public UploadedFileResponseDto saveFile(MultipartFile multipartFile) throws IOException {
        User curruentUser = userService.getCurruentUser();
        String savedFilePath = fileHandler.saveFile(multipartFile);
        String fileName = new File(savedFilePath).getName();
        String url = fileURL + "/" + fileName;

        UploadedFile uploadedFile = UploadedFile.create(curruentUser, multipartFile.getOriginalFilename(), fileName, savedFilePath, url, multipartFile.getSize(), multipartFile.getContentType());
        uploadedFileService.saveUploadedFile(uploadedFile);
        return UploadedFileResponseDto.toDto(uploadedFile);
    }

    public String deleteFile(DeleteFileRequestDto deleteFileRequestDto) throws PermissionDeniedException {
        User currentMember = userService.getCurruentUser();
        UploadedFile uploadedFile = uploadedFileService.getUploadedFileByUrl(deleteFileRequestDto.getUrl());
        String filePath = uploadedFile.getSavedPath();
        File storedFile = new File(filePath);
        if (!storedFile.exists()) {
            throw new NotFoundException("존재하지 않는 파일입니다.");
        }
        uploadedFile.validateAccessPermission(currentMember);
        fileHandler.deleteFile(uploadedFile.getSavedPath());
        return uploadedFile.getUrl();
    }
}
