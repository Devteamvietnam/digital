package com.devteam.module.account.entity;

import com.devteam.module.data.db.entity.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
  name = AccountGroup.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = AccountGroup.TABLE_NAME + "_path",
      columnNames = {"path"}),
  },
  indexes = {
    @Index(columnList="id, storage_state, modified_time"),
    @Index(columnList="name, path, parent_path")
  }
)
@NoArgsConstructor @Getter @Setter
public class AccountGroup extends BaseEntity<Long> {
  public static final String TABLE_NAME = "devteam_account_group";

  @NotNull
  private String            name;

  @NotNull
  private String            path;

  @Column(name = "parent_path")
  private String            parentPath;

  @Column(name = "parent_id")
  private Long              parentId;

  private String            label;

  @Column(length = 65536)
  private String            description;

  public AccountGroup(String name, String label) {
    this.name = name;
    this.path = name;
    this.label = label;
  }

  public AccountGroup(AccountGroup parent, String name, String label, String desc) {
    this.name = name;
    this.label = label;
    setParent(parent);
  }

  public void setParent(AccountGroup parent) {
    if (parent == null) {
      path = name;
      parentPath = null;
    } else {
      path = parent.getPath() + "/" + name;
      parentPath = parent.getPath();
      parentId   = parent.getId();
    }
  }

  public String calculateParentPath() {
    if(path == null) return null;
    int idx = path.lastIndexOf('/');
    if(idx >= 0) return path.substring(0, idx);
    return null;
  }
}
