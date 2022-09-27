package com.gotogether.gotogethersbe.service;

import com.gotogether.gotogethersbe.domain.Option;
import com.gotogether.gotogethersbe.dto.AdminProductDto;
import com.gotogether.gotogethersbe.repository.OptionRepository;
import com.gotogether.gotogethersbe.repository.ProductRepository;
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

}
