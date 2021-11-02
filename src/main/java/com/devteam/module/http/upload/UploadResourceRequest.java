package com.devteam.module.http.upload;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class UploadResourceRequest {
    @Getter @Setter
    private String storagePath;

    @JsonProperty("uploadResources")
    private List<UploadResource> uploadResources;

    public UploadResourceRequest withUploadResource(UploadResource res) {
        if(uploadResources == null) uploadResources = new ArrayList<>();
        uploadResources.add(res);
        return this;
    }
}
