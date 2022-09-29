package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.common.exception.CustomException;
import com.gotogether.gotogethersbe.domain.Product;
import com.gotogether.gotogethersbe.domain.ProductOption;
import com.gotogether.gotogethersbe.dto.AdminProductDto;
import com.gotogether.gotogethersbe.dto.ProductOptionDto;
import com.gotogether.gotogethersbe.repository.OptionRepository;
import com.gotogether.gotogethersbe.repository.ProductRepository;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;

    //상품 등록
    @Transactional
    public void insertProduct(AdminProductDto.ProductRequest request){
        List<ProductOption> productOptionList = request.getOptionDtoList()
                                               .stream()
                                               .map(optionDto -> optionDto.toProductOption())
                                               .collect(Collectors.toList());
        Product product = request.toProduct(productOptionList);
        List<ProductOption> mappedProductOptionList = productOptionList
                                               .stream()
                                               .map(option -> option.addProduct(product))
                                               .toList();
        productRepository.save(product);
        optionRepository.saveAll(mappedProductOptionList);
    }

    //아래 로직은 추후 프론트 구현 예정

    //상품 수정
    @Transactional
    public void updateProduct(AdminProductDto.ProductRequest request){
        List<ProductOption> updatedproductOptionList = request.getOptionDtoList()
                                                .stream()
                                                .map(optionDto -> optionDto.toProductOption())
                                                .collect(Collectors.toList());
        Product updatedProduct = request.toProduct(updatedproductOptionList);
        List<ProductOption> mappedProductOptionList = updatedproductOptionList
                                                .stream()
                                                .map(option -> option.addProduct(updatedProduct))
                                                .toList();
        productRepository.save(updatedProduct);
        optionRepository.saveAll(mappedProductOptionList);
    }

    //상품 삭제
    @Transactional
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    //상품 상세 조회
    @Transactional(readOnly = true)
    public AdminProductDto.ProductResponse getProduct(Long productId){
        List<ProductOptionDto.OptionResponse> optionDtoList  = optionRepository.findByProduct_Id(productId)
                                                   .stream()
                                                   .map(ProductOptionDto.OptionResponse::new)
                                                   .toList();

        return productRepository.findById(productId)
                .map(product -> new AdminProductDto.ProductResponse(product, optionDtoList))
                .orElseThrow(() -> new CustomException(ResponseMessage.GET_PRODUCT_FAIL, StatusCode.BAD_REQUEST));
    }

    //상품 리스트 조회
    @Transactional(readOnly = true)
    public List<AdminProductDto.ProductListResponse> getProductList(){
        return productRepository.findAll()
                                .stream()
                                .map(AdminProductDto.ProductListResponse::new)
                                .toList();
    }
}
