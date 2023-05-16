package com.team4.skillmarket.estimate.vo;

import java.util.List;

import com.team4.skillmarket.utill.file.AttachmentVo;

import lombok.Data;

@Data
public class EstimateVo {
	private Integer estimateNo;
    private Integer freelancerNo;
    private Integer estimateCatNo;
    private String estimateTitle;
    private String estimateDuration;
    private String estimateThumbnail;
    private String estimateLineIntroduction;
    private String estimatePrice;
    private String estimateDetail;
    private String estimateDetailFile;
    private String estimatePortfolio;
    private String businessRegistrationNumber;
    private String estimateEnrollDate;
    private String estimateModificationDate;
    private String estimateStatus;
    private String estimateViews;
    private List<AttachmentVo> attachments;

}
