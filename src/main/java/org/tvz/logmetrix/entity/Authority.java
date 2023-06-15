package org.tvz.logmetrix.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "AUTHORITY")
public class Authority implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private Long id;

    @NotNull
    @Column(length = 50, nullable = false)
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
