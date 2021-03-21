package com.zuber.organizeit.examples;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Dir
{

    //This field is a table column
    //It uniquely identifies a row on the DIR table
    @Id
    private int dirId;

    //This field is a table column
    // It identifies the parent of the current row
    // It it will be written as the type of dirId
    // By default this relationship will be eagerly fetched
    // , which you may or may not want
    @ManyToOne(fetch= FetchType.LAZY, cascade={CascadeType.PERSIST, CascadeType.MERGE})
    private Dir parent;

    //This field is not a table column
    // It is a collection of those Dir rows that have this row as a parent.
    // This is the other side of the relationship defined by the parent field.
    @OneToMany(mappedBy="parent")
    private Set<Dir> children;
}