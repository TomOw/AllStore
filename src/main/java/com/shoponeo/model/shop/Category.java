package com.shoponeo.model.shop;

import javax.persistence.*;

/**
 * Created by owczatom on 2016-10-21.
 */

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;

    @Column(name = "NAME")
    private String name;
}
