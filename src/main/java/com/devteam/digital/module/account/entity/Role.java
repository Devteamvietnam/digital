package com.devteam.digital.module.account.entity;

import com.devteam.digital.core.base.BaseEntity;
import com.devteam.digital.core.util.DataScopeEnum;
import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;


@Getter
@Setter
@Entity
@Table(name = "digital_role")
public class Role extends BaseEntity implements Serializable {

    @Id
    @Column(name = "role_id")
    @NotNull(groups = {Update.class})
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @NotBlank
    private String name;

    private String dataScope = DataScopeEnum.THIS_LEVEL.getValue();

    @Column(name = "level")
    private Integer level = 3;

    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}

