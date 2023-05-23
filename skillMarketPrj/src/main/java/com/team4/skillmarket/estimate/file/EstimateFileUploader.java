package com.team4.skillmarket.estimate.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;


import com.oreilly.servlet.MultipartRequest;
import com.team4.skillmarket.utill.file.AttachmentVo;

public class EstimateFileUploader {
    private String uploadPath;
    private int maxFileSize;

    public EstimateFileUploader(String uploadPath, int maxFileSize) {
        this.uploadPath = uploadPath;
        this.maxFileSize = maxFileSize;
    }

    public List<AttachmentVo> uploadAttachments(MultipartRequest multi) throws IOException {
        List<AttachmentVo> attachmentList = new ArrayList<>();

        Enumeration<String> fileNames = multi.getFileNames();
        while (fileNames.hasMoreElements()) {
            String fileName = fileNames.nextElement();
            File file = multi.getFile(fileName);

            if (file != null && file.length() > 0) {
                AttachmentVo attachment = new AttachmentVo();

                attachment.setAttachmentOriginName(multi.getOriginalFileName(fileName));
                attachment.setAttachmentServerName(multi.getFilesystemName(fileName));
                attachment.setAttachmentType(multi.getContentType(fileName));

                attachmentList.add(attachment);
            }
        }
        
        
        return attachmentList;
    }
}