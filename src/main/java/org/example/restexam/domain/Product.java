package org.example.restexam.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@NoArgsConstructor
@Table(name="lion_products")
public class Product {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Integer price;

    public Product(String name, int price) {
        this.name = name;
        this.price = price;
    }

    // Product 클래스를 기준으로, 아래 제시된 부분을 만들어서
    // 엔티티, repository, controller, service, .http 까지
    // UserController -> 응답을 좀 더 세심하게
}
