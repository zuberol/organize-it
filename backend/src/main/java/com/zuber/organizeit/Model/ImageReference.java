package com.zuber.organizeit.Model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

//@Entity
@Getter
@Setter
@NoArgsConstructor
public class ImageReference extends ReferenceResource {
    private String referenceUrl;
    private MultipartFile file;

//    void setImageUri(MultipartFile multipartFile) {
//
//    }
}
