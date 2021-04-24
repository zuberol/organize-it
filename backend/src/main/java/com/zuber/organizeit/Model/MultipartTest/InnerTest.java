package com.zuber.organizeit.Model.MultipartTest;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
public class InnerTest {
    private String question;
    private String answer;
}
