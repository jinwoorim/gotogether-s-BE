package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.domain.enums.Continent;
import com.gotogether.gotogethersbe.dto.CurationDto;
import com.gotogether.gotogethersbe.dto.ProductDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final MemberRepository memberRepository;

    //메인 페이지 추천상품 관련 API

    /**
     * 메인페이지 오늘의 추천 상품
     *
     * @param request
     * @return case 1-1. 큐레이션에 설문 응답값이 있는 경우 -> 큐레이션 맞춤 상품 리스트 리턴
     * case 1-2. 큐레이션에 설문 응답값이 없는 경우 -> 오늘의 추천리스트(전체) 상품 리스트 리턴
     * case 2-1. 세션에 설문 응답값이 있는 경우 -> 큐레이션 맞춤 상품 리스트 리턴
     * case 2-2. 세선에 설문 응답값이 없는 경우 -> 오늘의 추천리스트(전체) 상품 리스트 리턴
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendByCustom(HttpServletRequest request) {
        if (isMemberLogin()) {
            //case 1-1
            Member findMember = memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
            if (findMember.getCuration() != null) {
                return productRepository.findCustom(
                        findMember.getCuration().getAges(),
                        findMember.getCuration().getGenderGroup(),
                        findMember.getCuration().getCompanion(),
                        findMember.getCuration().getReligion(),
                        findMember.getCuration().getTheme()
                );
            }
            return productRepository.findAll()
                    .stream().map(ProductDto.ProductResponse::of)
                    .collect(Collectors.toList());
        }

        HttpSession session = request.getSession(false);
        //case 2-1
        if (hasCurationData(session)) {
            CurationDto.CurationRequest cRequest = (CurationDto.CurationRequest) session.getAttribute("curation");
            return productRepository.findCustom(
                    cRequest.getAges(),
                    cRequest.getGenderGroup(),
                    cRequest.getCompanion(),
                    cRequest.getReligion(),
                    cRequest.getTheme()
            );
        }
        //case 2-2
        return productRepository.findAll()
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    private boolean isMemberLogin() {
        return SecurityUtil.getCurrentMemberId() != null;
    }

    private boolean hasCurationData(HttpSession session) {
        return session.getAttribute("curation") != null;
    }

    /**
     * 메인페이지
     * 여행지 종류
     *
     * @return 메인페이지 여행지 종류 리스트
     */
    @Transactional(readOnly = true)
    public List<String> continentList() {
        return Continent.getContinentList();
    }

    @Transactional(readOnly = true)
    public Page<ProductDto.ProductResponse> recommendByCategory(Pageable pageable, String category) {
        return productRepository.findAllCategoriesComplex(pageable, category);
    }

    /**
     * 전체 상품 내 검색
     *
     * @param request
     * @return 상품이름에 입력 String값을 포함하는 상품리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> searchProducts(ProductDto.SearchRequest request) {
        return productRepository.findByProductNameContains(request.getKeyword())
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 상품 상세정보 보기
     *
     * @param productId
     * @retrun 상품 상세 정보
     */
    @Transactional(readOnly = true)
    public List<ProductDto.DetailResponse> productDetail(Long productId) {
        return productRepository.findById(productId)
                .stream().map(ProductDto.DetailResponse::of)
                .collect(Collectors.toList());
    }
}
