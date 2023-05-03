package tamin.library.model.entity;

import com.google.gson.Gson;
import tamin.library.service.utiles.LifecycleListener;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

//@MappedSuperclass
@Entity(name = "itemtEntity")
@Table(name = "ITEM_TABLE")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@EntityListeners({ LifecycleListener.class})
@DiscriminatorColumn(name = "DISK", discriminatorType = DiscriminatorType.STRING) //its work with @Entity Annotations
@DiscriminatorValue("BASE") //its work with @Entity Annotations
public abstract class Item {


    // ======================================
    // =             Attributes             =
    // ======================================

    @Id
    @SequenceGenerator(name = "itemSeq", sequenceName = "item_seq",  allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itemSeq")
    private Long id;

    @Version

//    @Column(nullable = false)
    private Long version;
    @NotNull
    @Size(min=4,max = 30)
    @Column(columnDefinition = "nvarchar2(100)",nullable = false)
    private String title;

    @Column(length = 3000)
    private String description;

    @Column(name = "unit_cost",columnDefinition = "double precision")
    private Double unitCost;




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

    public Long getVersion() {
        return version;
    }

    public Item setVersion(Long version) {
        this.version = version;
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

    public Double getUnitCost() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return Double.compare(item.getUnitCost(), getUnitCost()) == 0 && Objects.equals(getId(), item.getId()) && Objects.equals(getTitle(), item.getTitle()) && Objects.equals(getDescription(), item.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTitle(), getDescription(), getUnitCost());
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }

}
