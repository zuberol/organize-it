package com.zuber.organizeit.Model.tests;

import com.zuber.organizeit.Model.MultipartTest.InnerTest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@NoArgsConstructor
public class FormWrapper {
    List<InnerTest> refs;
}