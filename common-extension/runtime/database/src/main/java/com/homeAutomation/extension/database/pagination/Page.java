package com.homeAutomation.extension.database.pagination;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
public class Page<T> {
    private int pageNumber;

    private int pageSize;

    private List<T> content;

    public <R> Page<R> map(Function<? super T, R> runnable) {
        return new Page<>(pageNumber, pageNumber,
                content.stream().map(runnable)
                        .collect(Collectors.toList()));
    }

    public static <T> Page<T> of(List<T> content, PageRequest pageRequest) {
        return new Page<>(pageRequest.getPageNumber(), content.size(), content);
    }

    public static <T> Page<T> of(PanacheQuery<T> query, PageRequest pageRequest) {
        return of(query.page(pageRequest.getPageNumber(), pageRequest.getPageSize()).list(), pageRequest);
    }
}
