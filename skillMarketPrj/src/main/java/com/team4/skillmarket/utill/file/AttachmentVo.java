package com.team4.skillmarket.utill.file;

import lombok.Data;

@Data
public class AttachmentVo {
	private int attachmentNo;
    private int estimateNo;
    private String attachmentInsertTime;
    private String attachmentDeleteTime;
    private String attachmentOriginName;
    private String attachmentServerName;
    private String attachmentType;

}
