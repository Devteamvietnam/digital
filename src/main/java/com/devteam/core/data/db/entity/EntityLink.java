package com.devteam.core.data.db.entity;

import com.devteam.lib.util.ds.Objects;
import com.devteam.lib.util.text.StringUtil;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@MappedSuperclass
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Getter
@Setter
public class EntityLink extends Persistable<Long> {
    private static final long serialVersionUID = 1L;

    static public enum Type { EntityId, EntityCustomId, Http }

    private Type type = Type.EntityId;

    private String label;

    @NotNull
    @Column(name="link_name")
    private String linkName;

    @NotNull
    @Column(name="link_id")
    private String linkId;
    private String description;

    public EntityLink(Persistable<Long> entity) {
        this.withEntity(entity);
    }

    public EntityLink withEntity(Persistable<Long> entity) {
        Class<?> entityClass = entity.getClass();
        this.type = Type.EntityId;
        this.linkName  = entityClass.getDeclaredAnnotation(Table.class).name();
        this.linkId = Long.toString(entity.getId());
        if(Objects.isNull(label)) label = linkName + "[" + linkId + "]";
        return this;
    }

    public EntityLink withEntity(Persistable<Long> entity, String customId) {
        Class<?> entityClass = entity.getClass();
        this.type = Type.EntityCustomId;
        this.linkName  = entityClass.getDeclaredAnnotation(Table.class).name();
        this.linkId = customId;
        if(Objects.isNull(label)) label = linkId;
        return this;
    }

    public EntityLink withEntityCode(String name, String value) {
        this.linkName  = name;
        this.label = name;
        this.linkId = value;
        return this;
    }

    public EntityLink withLabel(String label) {
        this.label = label;
        return this;
    }

    public void merge(EntityLink other) {
        linkName  = other.linkName;
        linkId = other.linkId;
        if(other.description != null) description = other.description;
    }

    static public <T extends EntityLink> List<T> addTo(List<T> holder, T link, boolean unique) {
        if(holder == null) holder = new ArrayList<>();
        Iterator<T> i = holder.iterator();
        while(i.hasNext()) {
            T sel = i.next();
            if(sel.getLinkName().equals(link.getLinkName())) {
                if(unique) {
                    if(link.getLinkId() == null) {
                        i.remove();
                    } else {
                        sel.merge(link);
                    }
                } else {
                    if(StringUtil.compare(sel.getLinkId(),  link.getLinkId()) == 0) {
                        sel.merge(link);
                    } else {
                        holder.add(link);
                    }
                }
                return holder;
            }
        }
        if(link.getLinkId() != null) {
            holder.add(link);
        }
        return holder;
    }

    static public <T extends EntityLink> T findByName(List<T> holder, String name) {
        if(holder == null) return null;
        Iterator<T> i = holder.iterator();
        while(i.hasNext()) {
            T sel = i.next();
            if(sel.getLinkName().equals(name)) return sel;
        }
        return null;
    }
}
