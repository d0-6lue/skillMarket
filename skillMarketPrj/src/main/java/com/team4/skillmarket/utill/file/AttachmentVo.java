package com.team4.skillmarket.utill.file;


import lombok.Data;

@Data
public class AttachmentVo {
    private int attachmentNo;
    private int estimateNo;
    private String attachmentOriginName;
    private String attachmentServerName;
    private String attachmentType;
    private boolean mainImage;

    // 생성자, getter, setter 생략

    // 다른 필드 및 메소드 구현
}