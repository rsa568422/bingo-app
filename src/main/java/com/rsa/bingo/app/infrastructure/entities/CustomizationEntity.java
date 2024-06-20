package com.rsa.bingo.app.infrastructure.entities;


import com.rsa.bingo.app.infrastructure.entities.keys.CustomizationKey;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "customizations")
public class CustomizationEntity {

    @EmbeddedId
    private CustomizationKey key;
}
