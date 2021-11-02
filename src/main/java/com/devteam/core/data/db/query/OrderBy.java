package com.devteam.core.data.db.query;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderBy {
    @Getter @Setter
    String[] fields;

    @Getter @Setter
    String[] selectFields;

    @Getter @Setter
    String   sort = "ASC";

    public OrderBy() {}

    public OrderBy(String[] field, String[] selectedField) {
        this.fields       = field;
        this.selectFields = selectedField;
    }

    public OrderBy(String[] field, String selectedField, String sort) {
        this.fields       = field;
        if(selectedField != null) {
            this.selectFields = new String[] { selectedField };
        }
        this.sort = sort;
    }

    public void mergeValue(OrderBy other) {
        selectFields = other.getSelectFields();
        sort = other.getSort();
    }
}
