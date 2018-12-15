package com.system.common.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.data.domain.Page;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 分页DTO
 */
@ApiModel
public final class PageDTO<V> {

    @ApiModelProperty("总记录数")
    private final long total;

    @ApiModelProperty("数据集")
    private final List<V> list;


    public PageDTO(long total, List<V> list) {
        this.total = total;
        this.list = list;
    }

    /**
     * Builder构造器
     *
     * @param datas
     * @param <V>
     * @return
     */
    public static <V> Builder<V> builder(List<V> datas) {
        return new Builder<>(datas);
    }

    /**
     * 将Page转换为PageDTO
     *
     * @param page
     * @param <V>
     * @return
     */
    public static <V> PageDTO<V> of(Page<V> page) {
        Objects.requireNonNull(page, "page is null");
        return new PageDTO<>(page.getTotalElements(),
                Optional.ofNullable(page.getContent()).orElse(Collections.emptyList()));
    }

    /**
     * 将Page转换为PageDTO，需提供转换器对列表数据进行转换
     *
     * @param page
     * @param converter
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> PageDTO<V> of(Page<T> page, Function<T, V> converter) {

        Objects.requireNonNull(page, "page is null");
        Objects.requireNonNull(converter, "converter is null");

        return new PageDTO<>(page.getTotalElements(), Optional.ofNullable(page.getContent())
                .map(content -> content.stream().map(converter)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList()));
    }

    /**
     * 根据转换器构造PageDTO
     *
     * @param page
     * @param converter
     * @param <T>
     * @param <V>
     * @return
     */
    public static <T, V> PageDTO<V> of(PageDTO<T> page, Function<T, V> converter) {
        Objects.requireNonNull(page, "page is null");
        Objects.requireNonNull(converter, "converter is null");
        return new PageDTO<>(page.getTotal(), Optional.ofNullable(page.getList())
                .map(content -> content.stream().map(converter)
                        .collect(Collectors.toList()))
                .orElse(Collections.emptyList()));
    }

    public final long getTotal() {
        return total;
    }

    public final List<V> getList() {
        return list;
    }


    /**
     * Builder构造器
     *
     * @param <V>
     */
    public static class Builder<V> {
        private long totalRecords;
        private final List<V> datas;

        private Builder(List<V> datas) {
            Objects.requireNonNull(datas, "list is null");
            this.datas = datas;
            totalRecords = datas.size();
        }

        public final Builder<V> totalRecords(long totalRecords) {
            this.totalRecords = totalRecords;
            return this;
        }

        public final PageDTO<V> build() {
            return new PageDTO<V>(totalRecords, datas);
        }
    }
}

