package com.zuber.organizeit.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@NoArgsConstructor
@Getter @Setter
public class RefFileMetadata {
    private String refResourceIndex;
    private List<MultipartFile> refResourceAssociatedFiles;
}
