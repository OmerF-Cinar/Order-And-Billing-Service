package com.webtech.ordernbilling.DTO;

import java.util.List;

public class CreateOrderRequestDTO {

    private List<OrderItemDTO> items;

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

}
