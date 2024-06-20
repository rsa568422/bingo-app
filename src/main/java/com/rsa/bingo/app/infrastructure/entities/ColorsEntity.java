package com.rsa.bingo.app.infrastructure.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "colors")
public class ColorsEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "primary")
    private String primary;

    @Column(name = "secondary")
    private String secondary;

    public int[] getPrimaryRGB() {
        return toRGB(primary);
    }

    public int[] getSecondaryRGB() {
        return toRGB(secondary);
    }

    private static int[] toRGB(String color) {
        var values = color.replace("rgb(", "").replace(")", "").split(",");
        if (values.length != 3) throw new VerifyError("El formato del color es incorrecto");
        var rgb = new int[3];
        for (int i = 0; i < 3; i++) {
            try {
                rgb[i] = Integer.parseInt(values[i]);
            } catch (NumberFormatException exception) {
                throw new VerifyError("Los valores de RGB deben ser numéricos");
            }
        }
        return rgb;
    }
}
