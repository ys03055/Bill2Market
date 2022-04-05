package com.example.demo.model.item;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
public class ItemPhotoSaveRequest {
    private List<MultipartFile> itemPhotos;
}
