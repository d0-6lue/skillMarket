package com.team4.skillmarket.estimate.vo;

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
    private AttachmentVo mainImage;
    private String EstimateEnrollDate;
    private List<AttachmentVo> detailImages;
    private List<EstimateFaqVo> faqs; 
    private List<EstimateOptionVo> additionalOptions; 

    


}
    








