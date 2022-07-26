package com.ITOSupportTracker.Entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "Sub_Category")
public class SubCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="sub_category_id")
    private Integer  subCategoryId;
    @Column(name="category_id")
    private Integer categoryId;
    @Column(name = "sub_category_desc")
    private  String subCategoryDesc;






}
