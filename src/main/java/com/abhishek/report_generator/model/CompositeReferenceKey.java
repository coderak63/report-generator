package com.abhishek.report_generator.model;


import java.util.Objects;

public class CompositeReferenceKey {
    private String refkey1;
    private String refkey2;

    public CompositeReferenceKey(ReferenceFileRecord referenceFileRecord) {
        this.refkey1 = referenceFileRecord.getRefkey1();
        this.refkey2 = referenceFileRecord.getRefkey2();
    }
    
    public CompositeReferenceKey(InputFileRecord inputFileRecord) {
        this.refkey1 = inputFileRecord.getRefkey1();
        this.refkey2 = inputFileRecord.getRefkey2();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeReferenceKey that = (CompositeReferenceKey) o;
        return Objects.equals(refkey1, that.refkey1) &&
               Objects.equals(refkey2, that.refkey2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(refkey1, refkey2);
    }

    // Getters (optional)
    public String getRefkey1() {
        return refkey1;
    }

    public String getRefkey2() {
        return refkey2;
    }
}

