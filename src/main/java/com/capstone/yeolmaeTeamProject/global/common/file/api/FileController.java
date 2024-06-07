package com.capstone.yeolmaeTeamProject.global.common.file.api;

import com.capstone.yeolmaeTeamProject.global.common.dto.ApiResponse;
import com.capstone.yeolmaeTeamProject.global.common.file.application.FileService;

import com.capstone.yeolmaeTeamProject.global.common.file.dto.request.DeleteFileRequestDto;
import com.capstone.yeolmaeTeamProject.global.common.file.dto.response.UploadedFileResponseDto;
import com.capstone.yeolmaeTeamProject.global.exception.PermissionDeniedException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.MediaType;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
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

    @Operation(summary = "[U] 첨부파일 업로드", description = "ROLE_USER 이상의 권한이 필요함")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @PostMapping(value = "", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ApiResponse<List<UploadedFileResponseDto>> boardUpload(
            @RequestParam(name = "multipartFile") List<MultipartFile> multipartFiles
    ) throws IOException {
        List<UploadedFileResponseDto> uploadedFileUrls = fileService.saveFiles(multipartFiles);
        return ApiResponse.success(uploadedFileUrls);
    }

    @Operation(summary = "[U] 첨부파일 삭제", description = "ROLE_USER 이상의 권한이 필요함<br>" +
            "/resources/files/~를 입력해서 파일 위치를 파악하고 url을 입력할 수 있게.")
    @Secured({"ROLE_USER", "ROLE_ADMIN"})
    @DeleteMapping("")
    public ApiResponse<String> deleteFile(
            @RequestBody DeleteFileRequestDto deleteFileRequestDto
    ) throws PermissionDeniedException {
        String deletedFileUrl = fileService.deleteFile(deleteFileRequestDto);
        return ApiResponse.success(deletedFileUrl);
    }
}
