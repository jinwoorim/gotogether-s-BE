package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.domain.Curation;
import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.dto.CurationDto;
import com.gotogether.gotogethersbe.repository.CurationRepository;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CurationService {

    private final CurationRepository curationRepository;
    private final MemberRepository memberRepository;

    //로그인 후 추천 데이터 저장
    public void insertCuration(CurationDto.CurationRequest request, Long memberId){
            Member member = memberRepository.findById(memberId).get();
            Curation curation = curationRepository.save(request.toCuration());
            member.addCuration(curation);
            memberRepository.save(member);
        }
    }

