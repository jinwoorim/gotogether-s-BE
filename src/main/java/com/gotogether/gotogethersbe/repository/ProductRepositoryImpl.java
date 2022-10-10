package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.enums.Companion;
import com.gotogether.gotogethersbe.domain.enums.GenderGroup;
import com.gotogether.gotogethersbe.domain.enums.Religion;
import com.gotogether.gotogethersbe.domain.enums.Theme;
import com.gotogether.gotogethersbe.dto.ProductDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.gotogether.gotogethersbe.domain.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<ProductDto.ProductResponse> findCustom(String ages, GenderGroup genderGroup, Companion companion, Religion religion, Theme theme) {
        GenderGroup g = NoMatterChecker(genderGroup);
        Companion c = NoMatterChecker(companion);
        Religion r = NoMatterChecker(religion);
        Theme t = NoMatterChecker(theme);

        return jpaQueryFactory.selectFrom(product)
                .where(safeNull(() -> product.ages.contains(ages))
                        .or(safeNull(() -> product.genderGroup.eq(g)))
                        .or(safeNull(() -> product.companion.eq(c)))
                        .or(safeNull(() -> product.religion.eq(r)))
                        .or(safeNull(() -> product.theme.eq(t))))
                .orderBy()
                .fetch().stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto.ProductResponse> findAgesContains(String ages) {
        return jpaQueryFactory.selectFrom(product)
                .where(safeNull(() -> product.ages.contains(ages)))
                .fetch().stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProductDto.ProductResponse> findCompanion(String companion) {
        Companion c = NoMatterChecker(Companion.valueOf(companion));
        return jpaQueryFactory.selectFrom(product)
                .where(safeNull(() -> product.companion.eq(c)))// 맞나?
                //.orderBy(productSort())
                .fetch().stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    public <T extends Enum> T NoMatterChecker(T t) {
        if (t.name().equals("NO_MATTER")) {
            return null;
        }
        return t;
    }

    private BooleanBuilder safeNull(final Supplier<BooleanExpression> s) {
        try {
            return new BooleanBuilder(s.get());
        } catch (IllegalArgumentException e) {
            return new BooleanBuilder();
        }
    }

}
