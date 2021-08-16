package com.zuber.organizeit.Model.MultipartTest;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Profile;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@Profile("development")
public class InnerTest {
    private String question;
    private String answer;
}
