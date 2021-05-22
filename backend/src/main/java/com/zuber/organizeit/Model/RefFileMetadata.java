package com.zuber.organizeit.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class RefFileMetadata {
    private List<Long> refResourceIndex = new LinkedList<>();
    private List<MultipartFile> refResourceAssociatedFiles = new LinkedList<>(); //only One
}
