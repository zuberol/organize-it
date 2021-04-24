package com.zuber.organizeit.Model.tests;

import com.zuber.organizeit.Model.MultipartTest.InnerTest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class OuterTest implements Serializable {
    private MultipartFile file;
    private InnerTest innerTest;
}
