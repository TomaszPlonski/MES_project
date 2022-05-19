package com.github.tomaszplonski.mes_project.model;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter @Setter
@Entity @Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="order_handlers")
public class OrderHandler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String firstName;

    private String lastName;

    @OneToMany(mappedBy = "orderHandler")
    private Set<Order> orders = new HashSet<>();

    @Transient
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
