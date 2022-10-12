package com.bobrov.eshop.model;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderDetailId implements Serializable {
    private static final long serialVersionUID = 3152124740656566802L;
    private Long orderId;
    private Long productId;
}
