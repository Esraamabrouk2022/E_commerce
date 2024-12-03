package com.example.E_commerce.model.Review;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewResponseDTO {
    private Long id;
    private Long productId;
    private Long userId;
    private int rate;
    private String text;
    private Date date;
}
