package com.example.E_commerce.model.Order;



import com.example.E_commerce.entity.Enum.Order_stutus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {
    private Long id;
    private LocalDate date;
    private double totalPrice;
    private Order_stutus orderStutus;
    private String userName;
    private List<Long> orderItemIds;
    private Long paymentId;
}
