package com.medilabosolutions.model;

import java.util.List;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true, value = {"pageable"})
public class RestPage<T> extends PageImpl<T> {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RestPage(@JsonProperty("content") List<T> content,
            @JsonProperty("number") int pageNumber,
            @JsonProperty("size") int pageSize,
            @JsonProperty("totalElements") long total) {

        super(content, PageRequest.of(pageNumber, pageSize), total);
    }

    public RestPage(RestPage<T> page) {
        super(page.getContent(), page.getPageable(), page.getTotalElements());
    }

}
