package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.domain.enums.Theme;
import com.gotogether.gotogethersbe.dto.ProductDto;
import com.gotogether.gotogethersbe.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@RequiredArgsConstructor
@Service
public class ProductService{

    private final ProductRepository productRepository;

//    /**
//     * 메인페이지 오늘의 추천 상품
//     * @return
//     * 세션에 큐레이션값 없음 : 오늘의 상품(랜덤) 리스트
//     * 로그인 or 세션에 큐레이션값 있음 : 큐레이션 맞춤 상품 리스트
//     */
//    @Transactional(readOnly = true)
//    public List<ProductDto.ProductResponse> findAll(HttpServletRequest request) {
//        HttpSession session = request.getSession(false);
//        if(session == null) {
//            return productRepository.findRandom()
//                    .stream()
//                    .map(ProductDto.ProductResponse::of)
//                    .collect(Collectors.toList());
//        }
//        else {
//            return productRepository.findCustom()
//                    .stream()
//                    .map(ProductDto.ProductResponse::of)
//                    .collect(Collectors.toList());
//        }
//    }

    /**
     * 협의 해야함
     * 기능 구현하려면 추가로 Entity 만들어야 함
     * 메인페이지 추천 여행지
     * @return 추천 여행지(랜덤) 리스트
     */

    /**
     * 메인페이지 연령대별 여행 추천
     * @return 연령대 키워드별 여행지 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendByAges(ProductDto.RecommendationRequest request) {
        return productRepository.findByAges(request.getKeyword())
                .stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

//    /**
//     * 메인페이지 유형별 여행 추천
//     * @return
//     */
//    @Transactional(readOnly = true)
//    public List<ProductDto.ProductResponse> recommendByCompanionOrGenderGroup(ProductDto.RecommendationRequest request) {
//        request.getKeyword();
//        return productRepository.findByCompanionOrGenderGroup()
//                .stream()
//                .map(ProductDto.ProductResponse::of)
//                .collect(Collectors.toList());
//    }
    /**
     * 메인페이지 골프 여행 추천
     * @return 골프테마 여행상품 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendGolf() {
        Theme golf = Theme.GOLF;

        return productRepository.findByTheme(golf)
                .stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }
    /**
     * 메인페이지 문화탐방 여행 추천
     * @return 문화탐방 여행상품 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendCulture() {
        Theme culture = Theme.CULTURE;

        return productRepository.findByTheme(culture)
                .stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }
    /**
     * 메인페이지 휴양지 추천
     * @return 휴양지 여행상품 리스트
     */
    @Transactional(readOnly = true)
    public List<ProductDto.ProductResponse> recommendHealing() {
        Theme healing = Theme.HEALING;

        return productRepository.findByTheme(healing)
                .stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }
}
