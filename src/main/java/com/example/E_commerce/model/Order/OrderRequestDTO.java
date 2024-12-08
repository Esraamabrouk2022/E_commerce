package com.example.E_commerce.model.Order;


import com.example.E_commerce.entity.Enum.Order_stutus;
import com.mysql.cj.result.LocalDateTimeValueFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {
    private Order_stutus orderStutus;
    private Long userId;
    private List<Long> orderItemIds;
}
