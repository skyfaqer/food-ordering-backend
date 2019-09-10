package com.cgy.sell.form;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class CategoryForm {

    private Integer categoryId;

    @NotEmpty(message = "category name cannot be empty")
    private String categoryName;

    @NotNull(message = "category type cannot be empty")
    @Min(value = 1, message = "category type cannot be less than 1")
    @Max(value = 999, message = "category type cannot be greater than 999")
    private Integer categoryType;
}
