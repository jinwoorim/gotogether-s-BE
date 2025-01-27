package com.gotogether.gotogethersbe.repository;

import com.gotogether.gotogethersbe.domain.enums.*;
import com.gotogether.gotogethersbe.dto.ProductDto;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.PageableExecutionUtils;

import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static com.gotogether.gotogethersbe.domain.QProduct.product;

@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryQueryDsl {
    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public Page<ProductDto.ProductResponse> findCustomComplex(Pageable pageable, String ages, GenderGroup genderGroup, Companion companion, Religion religion, Theme theme) {
        List<ProductDto.ProductResponse> content = findCustom(pageable, ages, genderGroup, companion, religion, theme);

        JPAQuery<Long> countQuery = getCount(ages, genderGroup, companion, religion, theme);

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchOne());

    }

    List<ProductDto.ProductResponse> findCustom(Pageable pageable, String ages, GenderGroup genderGroup, Companion companion, Religion religion, Theme theme) {
        Companion c = NoMatterChecker(companion);
        GenderGroup g = NoMatterChecker(genderGroup);
        Religion r = NoMatterChecker(religion);
        Theme t = NoMatterChecker(theme);

        return jpaQueryFactory.selectFrom(product)
                .where(safeNull(() -> product.ages.contains(ages))
                        .or(safeNull(() -> product.genderGroup.eq(g)))
                        .or(safeNull(() -> product.companion.eq(c)))
                        .or(safeNull(() -> product.religion.eq(r)))
                        .or(safeNull(() -> product.theme.eq(t))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(priceSort(pageable))
                .fetch().stream().map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto.ProductResponse> findAllCategoriesComplex(Pageable pageable, String category) {
        List<ProductDto.ProductResponse> content = findAllCategories(pageable, category);

        JPAQuery<Long> countQuery = getCount(category);

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchOne());
    }

    public List<ProductDto.ProductResponse> findAllCategories(Pageable pageable, String category) {
        Continent continent = Continent.typeChecker(category);
        Companion companion = Companion.typeChecker(category);
        GenderGroup genderGroup = GenderGroup.typeChecker(category);
        Theme theme = Theme.typeChecker(category);

        return jpaQueryFactory.selectFrom(product)
                .where(safeNull(() -> product.ages.contains(category))
                        .or(safeNull(() -> product.continent.eq(continent)))
                        .or(safeNull(() -> product.companion.eq(companion)))
                        .or(safeNull(() -> product.genderGroup.eq(genderGroup)))
                        .or(safeNull(() -> product.theme.eq(theme))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(priceSort(pageable))
                .fetch().stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto.ProductResponse> findAllCategoriesComplex(Pageable pageable, String category1, String category2, String category3, String category4) {
        List<ProductDto.ProductResponse> content = findAllCategories(pageable, category1, category2, category3, category4);

        JPAQuery<Long> countQuery = getCount(category1, category2, category3, category4);

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchOne());
    }

    public List<ProductDto.ProductResponse> findAllCategories(Pageable pageable, String category1, String category2, String category3, String category4) {
        Continent continent = Continent.typeChecker(category1);
        Companion companion = Companion.typeChecker(category3);
        GenderGroup genderGroup = GenderGroup.typeChecker(category3);
        Theme theme = Theme.typeChecker(category4);

        return jpaQueryFactory.selectFrom(product)
                .where((safeNull(() -> product.continent.eq(continent))
                        .or(safeNull(() -> product.ages.contains(category2))))
                        .or(safeNull(() -> product.companion.eq(companion)))
                        .or(safeNull(() -> product.genderGroup.eq(genderGroup)))
                        .or(safeNull(() -> product.theme.eq(theme))))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(priceSort(pageable))
                .fetch().stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    @Override
    public Page<ProductDto.ProductResponse> findByProductNameContainsComplex(Pageable pageable, String category) {
        List<ProductDto.ProductResponse> content = findByProductNameContains(pageable, category);

        JPAQuery<Long> countQuery = getCount(category);

        return PageableExecutionUtils.getPage(content, pageable, () -> countQuery.fetchOne());
    }

    public List<ProductDto.ProductResponse> findByProductNameContains(Pageable pageable, String keyword) {
        return jpaQueryFactory.selectFrom(product)
                .where(safeNull(() -> product.productName.contains(keyword)))
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .orderBy(priceSort(pageable))
                .fetch().stream()
                .map(ProductDto.ProductResponse::of)
                .collect(Collectors.toList());
    }

    private JPAQuery<Long> getCount(String category) {
        JPAQuery<Long> countQuery = jpaQueryFactory.select(product.count()).from(product)
                .where(safeNull(() -> product.ages.contains(category)),
                        safeNull(() -> product.continent.eq(Continent.valueOf(category))),
                        safeNull(() -> product.companion.eq(Companion.valueOf(category))),
                        safeNull(() -> product.genderGroup.eq(GenderGroup.valueOf(category))),
                        safeNull(() -> product.theme.eq(Theme.valueOf(category))));
        return countQuery;
    }

    private JPAQuery<Long> getCount(String ages, GenderGroup genderGroup, Companion companion, Religion religion, Theme theme) {
        JPAQuery<Long> countQuery = jpaQueryFactory.select(product.count()).from(product)
                .where(safeNull(() -> product.ages.contains(ages)),
                        safeNull(() -> product.genderGroup.eq(genderGroup)),
                        safeNull(() -> product.companion.eq(companion)),
                        safeNull(() -> product.religion.eq(religion)),
                        safeNull(() -> product.theme.eq(theme)));
        return countQuery;
    }

    private JPAQuery<Long> getCount(String category1, String category2, String category3, String category4) {
        JPAQuery<Long> countQuery = jpaQueryFactory.select(product.count()).from(product)
                .where(
                        safeNull(() -> product.continent.eq(Continent.valueOf(category1))),
                        safeNull(() -> product.ages.contains(category2)),
                        safeNull(() -> product.companion.eq(Companion.valueOf(category3))),
                        safeNull(() -> product.genderGroup.eq(GenderGroup.valueOf(category3))),
                        safeNull(() -> product.theme.eq(Theme.valueOf(category4))));
        return countQuery;
    }

    private OrderSpecifier<?> priceSort(Pageable pageable) {
        //서비스에서 보내준 Pageable 객체에 정렬조건 null 값 체크
        if (!pageable.getSort().isEmpty()) {
            for (Sort.Order order : pageable.getSort()) {
                Order direction = order.getDirection().isAscending() ? Order.ASC : Order.DESC;
                return new OrderSpecifier(direction, product.basicPrice);
            }
        }
        return new OrderSpecifier(Order.ASC, product.id);
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