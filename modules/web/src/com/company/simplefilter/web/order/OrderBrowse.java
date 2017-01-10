package com.company.simplefilter.web.order;

import com.company.simplefilter.entity.Order;
import com.google.common.base.Strings;
import com.haulmont.bali.util.ParamsMap;
import com.haulmont.cuba.gui.components.AbstractLookup;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.data.GroupDatasource;

import javax.inject.Inject;
import java.util.Map;
import java.util.UUID;

public class OrderBrowse extends AbstractLookup {

    @Inject
    private GroupDatasource<Order, UUID> ordersDs;
    @Inject
    private TextField filterField;

    @Override
    public void init(Map<String, Object> params) {
        filterField.addValueChangeListener(e -> applyFilter());
    }

    public void applyFilter() {
        String filterValue = filterField.getValue();
        if (Strings.isNullOrEmpty(filterValue)) {
            ordersDs.setQuery("select e from sf$Order e");
            ordersDs.refresh();
        } else {
            ordersDs.setQuery("select e from sf$Order e where " +
                    "lower(e.customer) like :custom$filterValue or " +
                    "cast(e.amount varchar(100))  like :custom$filterValue or " +
                    "cast(e.date varchar(100))  like :custom$filterValue");
            ordersDs.refresh(ParamsMap.of("filterValue", "%" + filterValue.toLowerCase() + "%"));
        }
    }
}