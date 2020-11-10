package com.noirix.controller.request;

import lombok.Data;
import org.springframework.web.bind.annotation.RequestParam;

@Data
public class SearchCriteria {
    private String query;

    private Long limit;
}
