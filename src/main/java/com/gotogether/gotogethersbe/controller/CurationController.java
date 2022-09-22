package com.gotogether.gotogethersbe.controller;

import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.dto.CurationDto;
import com.gotogether.gotogethersbe.service.CurationService;
import com.gotogether.gotogethersbe.web.api.DefaultRes;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class CurationController {

    private final CurationService curationService;

    @PostMapping("/members/curation")
    public DefaultRes insertCuration(@RequestBody CurationDto.CurationRequest curationRequest, HttpServletRequest request){

        //로그인 상태면 회원 정보에 큐레이션 데이터 연결
        Long memberId = SecurityUtil.getCurrentMemberId();
        if(memberId!=null){
            curationService.insertCuration(curationRequest, memberId);
            return DefaultRes.res(StatusCode.OK, ResponseMessage.RECO_DATA_SUCCESS_FOR_MEMBER);
        }
        //비로그인 상태면 세션에 큐레이션 데이터 저장
        HttpSession session = request.getSession();
        session.setAttribute("curation", curationRequest);
        return DefaultRes.res(StatusCode.OK, ResponseMessage.RECO_DATA_SUCCESS_FOR_NONMEMBER);
    }
}
