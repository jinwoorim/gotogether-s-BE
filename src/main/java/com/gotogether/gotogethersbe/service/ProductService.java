package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.domain.enums.Theme;
import com.gotogether.gotogethersbe.dto.CurationDto;
import com.gotogether.gotogethersbe.dto.ProductDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
     * 여행지별 사진은 url로 드릴테니까 직접 넣어 달라
     * @return 메인페이지 여행지 종류 리스트
     */
    @Transactional(readOnly = true)
    public List<String> continentCategory() {
        return productRepository.findDistinctContinent();
    }

    /**
     * 메인페이지 연령대별 여행 추천
     * @param ages
     * @return 연령대 키워드별 상품 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendByAges(String ages) {

        return productRepository.findAgesContains(ages);
    }

    /**
     * 메인페이지 유형별 여행 추천
     * case 1. 입력받은 값이 Companion인 경우
     *
     * @param companion
     * @return 동행자 유형별 상품 리스트 리턴
     * case 1-2. 입력 값이 GenderGroup인 경우 -> 여행자 성별별 상품 리스트 리턴
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendByCompanion(String companion) {
        return productRepository.findCompanion(companion);

    }

    /**
     * 메인페이지 유형별 여행 추천
     * case 2. 입력받은 값이 GenderGroup인 경우
     *
     * @param request
     * @return 여행자 성별별 상품 리스트 리턴
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendByGenderGroup(ProductDto.GenderGroupRequest request) {
        return productRepository.findByGenderGroup(request.getGenderGroup())
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 메인페이지 골프 여행 추천
     *
     * @return 골프테마 여행상품 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendGolf() {
        return productRepository.findByTheme(Theme.GOLF)
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 메인페이지 문화탐방 여행 추천
     *
     * @return 문화탐방 여행상품 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendCulture() {
        return productRepository.findByTheme(Theme.CULTURE)
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 메인페이지 휴양지 추천
     *
     * @return 휴양지 여행상품 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendHealing() {
        return productRepository.findByTheme(Theme.HEALING)
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 카테고리 전체 상품
     *
     * @return 전체 상품 중 이름에 입력 값을 포함하는 상품리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> allProducts() {
        return productRepository.findAll()
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 카테고리
     * 국가별 상품 추천
     *
     * @param request
     * @return 여행지 키워드별 상품 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendByContinent(ProductDto.ContinentRequest request) {
        return productRepository.findByContinent(request.getContinent())
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    /**
     * 카테고리 테마별 상품
     *
     * @return 카테고리 테마별 상품리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendedByTheme(ProductDto.ThemeRequest request) {
        return productRepository.findByTheme(request.getTheme())
                .stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
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
     * @param request 상품테이블 기본키인 productId
     * @retrun 상품 상세 정보
     */
    @Transactional(readOnly = true)
    public List<ProductDto.DetailResponse> productDetail(ProductDto.DetailRequest request) {
        return productRepository.findById(request.getProductId())
                .stream().map(ProductDto.DetailResponse::of)
                .collect(Collectors.toList());
    }
}
