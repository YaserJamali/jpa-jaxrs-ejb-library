package tamin.library.model.entity;

import com.google.gson.Gson;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
//@Entity(name = "itemtEntity")
@Table(name = "ITEM_TABLE")
@DiscriminatorColumn(name = "DISK", discriminatorType = DiscriminatorType.STRING) //its work with @Entity Annotations
@DiscriminatorValue("BASE") //its work with @Entity Annotations
public abstract class Item {


    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @SequenceGenerator(name = "itemSeq", sequenceName = "item_seq", initialValue = 1, allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")
    protected Long id;

    @Column(columnDefinition = "nvarchar2(100)")
    protected String title;

    @Column(length = 3000)
    protected String description;

    @Column(name = "unit_cost")
    protected double unitCost;


    // ======================================
    // =            Constructors            =
    // ======================================

//    public Item() {
//    }
//
//    public Item(String title, String description, double unitCost) {
//        this.title = title;
//        this.description = description;
//        this.unitCost = unitCost;
//    }
//
//    public Item(Long id, String title, String description, double unitCost) {
//        this.id = id;
//        this.title = title;
//        this.description = description;
//        this.unitCost = unitCost;
//    }

    // ======================================
    // =          Getters & Setters         =
    // ======================================

    public Long getId() {
        return id;
    }

    public Item setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public Item setTitle(String title) {
        this.title = title;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Item setDescription(String description) {
        this.description = description;
        return this;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public Item setUnitCost(double unitCost) {
        this.unitCost = unitCost;
        return this;
    }


    // ======================================
    // =    hashcode, equals & toString     =
    // ======================================


    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
