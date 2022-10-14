package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.common.exception.CustomException;
import com.gotogether.gotogethersbe.config.util.SecurityUtil;
import com.gotogether.gotogethersbe.domain.Member;
import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.enums.Continent;
import com.gotogether.gotogethersbe.dto.CurationDto;
import com.gotogether.gotogethersbe.dto.ProductDto;
import com.gotogether.gotogethersbe.dto.ProductOptionDto;
import com.gotogether.gotogethersbe.repository.MemberRepository;
import com.gotogether.gotogethersbe.repository.OptionRepository;
import com.gotogether.gotogethersbe.repository.ProductRepository;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;
    private final MemberRepository memberRepository;

    //메인 페이지 추천상품 관련 API

    /**
     * 메인페이지 오늘의 추천 상품
     *
     *
     * @return case 1-1. 큐레이션에 설문 응답값이 있는 경우 -> 큐레이션 맞춤 상품 리스트 리턴
     * case 1-2. 큐레이션에 설문 응답값이 없는 경우 -> 오늘의 추천리스트(전체) 상품 리스트 리턴
     * case 2-1. 세션에 설문 응답값이 있는 경우 -> 큐레이션 맞춤 상품 리스트 리턴
     * case 2-2. 세선에 설문 응답값이 없는 경우 -> 오늘의 추천리스트(전체) 상품 리스트 리턴
     */
    @Transactional(readOnly = true)
    public Page<ProductDto.ProductResponse> recommendByCustom(Pageable pageable, HttpServletRequest request) {
        if (isMemberLogin()) {
            //case 1-1
            Member findMember = memberRepository.findById(SecurityUtil.getCurrentMemberId()).get();
            if (findMember.getCuration() != null) {
                return productRepository.findCustomComplex(pageable,
                        findMember.getCuration().getAges(),
                        findMember.getCuration().getGenderGroup(),
                        findMember.getCuration().getCompanion(),
                        findMember.getCuration().getReligion(),
                        findMember.getCuration().getTheme()
                );
            }
            return productRepository.findAllCategoriesComplex(pageable, "상관 없음");
        }

        HttpSession session = request.getSession(false);
        //case 2-1
        if (session!=null) {
            CurationDto.CurationRequest cRequest = (CurationDto.CurationRequest) session.getAttribute("curation");
            return productRepository.findCustomComplex(pageable,
                    cRequest.getAges(),
                    cRequest.getGenderGroup(),
                    cRequest.getCompanion(),
                    cRequest.getReligion(),
                    cRequest.getTheme()
            );
        }
        //case 2-2
        return productRepository.findAllCategoriesComplex(pageable, "상관 없음");
    }

    private boolean isMemberLogin() {
        return SecurityUtil.getCurrentMemberId() != null;
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
    @Transactional(readOnly = true)
    public Page<ProductDto.ProductResponse> recommendByCategory(Pageable pageable, String category1, String category2, String category3, String category4) {
        return productRepository.findAllCategoriesComplex(pageable, category1, category2, category3, category4);
    }


    /**
     * 전체 상품 내 검색
     *
     * @param keyword
     * @return 상품이름에 입력 String값을 포함하는 상품리스트
     */
    @Transactional(readOnly = true)
    public Page<ProductDto.ProductResponse> searchProducts(Pageable pageable, String keyword) {
        return productRepository.findByProductNameContainsComplex(pageable, keyword);
    }

    /**
     * 상품 상세정보 보기
     *
     * @param productId
     * @retrun 상품 상세 정보
     */
    @Transactional(readOnly = true)
    public ProductDto.DetailResponse productDetail(Long productId) {
        Map<String, List<ProductOptionDto.OptionResponse>> productOptionList = optionRepository.findAllByProduct_Id(productId)
                .stream().map(ProductOptionDto.OptionResponse::new)
                .collect(Collectors.groupingBy(ProductOptionDto.OptionResponse::getName));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new CustomException(ResponseMessage.GET_PRODUCT_FAIL, StatusCode.BAD_REQUEST));

        return ProductDto.DetailResponse.of(product, productOptionList);
    }
}
