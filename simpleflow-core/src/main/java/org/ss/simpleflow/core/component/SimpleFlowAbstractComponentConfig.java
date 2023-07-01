package org.ss.simpleflow.core.component;

import org.jetbrains.annotations.NotNull;

public abstract class SimpleFlowAbstractComponentConfig implements SimpleFlowComponentConfig, Comparable<SimpleFlowAbstractComponentConfig> {

    protected String id;

    protected String code;

    protected String name;

    protected String description;

    void setId(String id) {
        this.id = id;
    }

    void setCode(String code) {
        this.code = code;
    }

    void setName(String name) {
        this.name = name;
    }

    void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleFlowAbstractComponentConfig that = (SimpleFlowAbstractComponentConfig) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(@NotNull SimpleFlowAbstractComponentConfig o) {
        return this.id.compareTo(o.id);
    }
}
