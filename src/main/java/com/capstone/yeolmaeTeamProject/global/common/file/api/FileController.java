package com.capstone.yeolmaeTeamProject.global.common.file.api;

import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import com.capstone.yeolmaeTeamProject.global.common.file.application.FileService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/v1/files")
@RequiredArgsConstructor
@Tag(name = "File Upload", description = "파일 업로드")
@Slf4j
public class FileController {

    private final FileService fileService;

    @Operation(summary = "[U] 파일 업로드", description = "ROLE_USER 이상의 권한이 필요함")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<List<String>> boardUpload(
            @RequestParam(name = "multipartFile") List<MultipartFile> multipartFiles
    ) throws IOException {
        List<String> uploadedFileUrls = fileService.saveFiles(multipartFiles);
        return ApiResponse.success(uploadedFileUrls);
    }
}
