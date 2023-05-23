package com.team4.skillmarket.estimate.vo;

import java.util.ArrayList;
import java.util.List;

import com.team4.skillmarket.utill.file.AttachmentVo;

import lombok.Data;

@Data
public class EstimateVo {
    private int estimateNo;
    private int freelancerNo;
    private String estimateCatNo;
    private String estimateTitle;
    private String estimateDuration;
    private String estimateLineIntroduction;
    private String estimatePrice;
    private String estimateDetail;
    private List<AttachmentVo> attachments;
    private List<String> attachmentPaths;

    public void setAttachmentPaths(List<String> attachmentPaths) {
        this.attachmentPaths = attachmentPaths;
    }

    public List<String> getAttachmentPaths() {
        return attachmentPaths;
    }

    // 생성자, getter, setter 생략

}
    








