package com.zuber.organizeit.Model;

public record EntityIdentity(
        Long persistentId,
        String fileAbsolutePath
) {
}
