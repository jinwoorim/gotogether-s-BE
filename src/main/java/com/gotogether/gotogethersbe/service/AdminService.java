package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.config.common.exception.CustomException;
import com.gotogether.gotogethersbe.domain.Option;
import com.gotogether.gotogethersbe.dto.AdminProductDto;
import com.gotogether.gotogethersbe.dto.OptionDto;
import com.gotogether.gotogethersbe.repository.OptionRepository;
import com.gotogether.gotogethersbe.repository.ProductRepository;
import com.gotogether.gotogethersbe.web.api.ResponseMessage;
import com.gotogether.gotogethersbe.web.api.StatusCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AdminService {

    private final ProductRepository productRepository;
    private final OptionRepository optionRepository;

    //상품 등록
    @Transactional
    public void insertProduct(AdminProductDto.ProductRequest request){
        List<Option> optionList  = request.getOptionDtoList()
                                   .stream()
                                   .map(optionDto -> optionDto.toOption())
                                   .toList();
        optionRepository.saveAll(optionList
                                   .stream()
                                   .map(option -> option.addProduct(request.toProduct(optionList)))
                                   .toList());
        productRepository.save(request.toProduct(optionList));
    }

    //상품 수정
    @Transactional
    public void updateProduct(AdminProductDto.ProductRequest request){
        List<Option> optionList  = request.getOptionDtoList()
                                    .stream()
                                    .map(optionDto -> optionDto.toOption())
                                    .toList();
        optionRepository.saveAll(optionList
                                    .stream()
                                    .map(option -> option.addProduct(request.toProduct(optionList)))
                                    .toList());
        productRepository.save(request.toProduct(optionList));
    }

    //상품 삭제
    @Transactional
    public void deleteProduct(Long productId){
        productRepository.deleteById(productId);
    }

    //상품 상세 조회
    @Transactional(readOnly = true)
    public AdminProductDto.ProductResponse getProduct(Long productId){
        List<OptionDto.OptionResponse> optionDtoList  = optionRepository.findByProduct_Id(productId)
                                                   .stream()
                                                   .map(OptionDto.OptionResponse::new)
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
