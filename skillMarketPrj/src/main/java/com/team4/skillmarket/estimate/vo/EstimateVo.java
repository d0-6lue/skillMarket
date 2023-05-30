package com.team4.skillmarket.estimate.vo;

import java.util.List;

import com.team4.skillmarket.utill.file.AttachmentVo;

import lombok.Data;

@Data
public class EstimateVo {
    private String estimateNo;
    private String freelancerNo;
    private String estimateCatNo;
    private String estimateTitle;
    private String estimateDuration;
    private String estimateLineIntroduction;
    private String estimatePrice;
    private String estimateDetail;
    private String mainImage;
    private String subImage;
    private String EstimateEnrollDate;
    private List<EstimateFaqVo> faqs; 
    private List<EstimateOptionVo> additionalOptions;
	private String memberNick;
	private String memberProfile;
	private String freelancerContactTime;
	private String reviewCount;
	
} 

 
    








