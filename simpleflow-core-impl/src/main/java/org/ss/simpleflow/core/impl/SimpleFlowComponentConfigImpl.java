package org.ss.simpleflow.core.impl;

import org.jetbrains.annotations.NotNull;
import org.ss.simpleflow.core.SimpleFlowComponentConfig;

public abstract class SimpleFlowComponentConfigImpl implements SimpleFlowComponentConfig, Comparable<SimpleFlowComponentConfigImpl> {

    private String id;

    private String code;

    private String name;

    private String description;

    public void setId(String id) {
        this.id = id;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public String getCode() {
        return null;
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

        SimpleFlowComponentConfigImpl that = (SimpleFlowComponentConfigImpl) o;

        return id.equals(that.id);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public int compareTo(@NotNull SimpleFlowComponentConfigImpl o) {
        return this.id.compareTo(o.id);
    }
}
