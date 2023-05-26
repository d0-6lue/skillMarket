package com.team4.skillmarket.utill.file;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import com.oreilly.servlet.MultipartRequest;

public class FileUploader {
    
    // 확장자 알아내기
    public static String getFileExtension(String fileName) {
        if (fileName != null && fileName.lastIndexOf(".") != -1) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return null;
    }
    
    // 첨부 파일 추출
    public static List<AttachmentVo> extractAttachments(MultipartRequest multi) {
        List<AttachmentVo> attachmentList = new ArrayList<>();
        Enumeration<String> fileNames = multi.getFileNames();

        while (fileNames.hasMoreElements()) {
            String fileName = fileNames.nextElement();
            File file = multi.getFile(fileName);

            if (file != null) {
                AttachmentVo attachment = new AttachmentVo();
                attachment.setAttachmentOriginName(multi.getOriginalFileName(fileName));
                attachment.setAttachmentServerName(file.getName());
                attachment.setAttachmentType(getFileExtension(file.getName()));
                attachmentList.add(attachment);
            }
        }

        return attachmentList;
    }
    
    // 프로필 파일 서버 저장
    public static String saveFile(String path , Part f) throws Exception {
        String randomName = UUID.randomUUID().toString();
        String originName = f.getSubmittedFileName();
        String ext = originName.substring( originName.lastIndexOf(".") ); 
        String changeName = randomName + ext;
        File target = new File(path + changeName);
        
        
        try (
                FileOutputStream fos = new FileOutputStream(target);
                InputStream is = f.getInputStream();
            ) 
        {
            byte[] buf = new byte[1024];
            int size = 0;
            while((size = is.read(buf)) != -1) {
                fos.write(buf, 0, size);
            }
        }
        
        return changeName;
    }
 
    
}
