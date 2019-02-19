package pl.devdmin.core.model;


import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

public class Product {

    @Id
    @GeneratedValue
    private Long id;

    @NotEmpty
    private String name;

}
