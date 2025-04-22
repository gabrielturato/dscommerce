package com.gturato.dscommerce.dto;

import com.gturato.dscommerce.entities.User;

public class ClientDTO {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ClientDTO(User entity){
        id = entity.getId();
        name = entity.getName();
    }

    public ClientDTO(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
