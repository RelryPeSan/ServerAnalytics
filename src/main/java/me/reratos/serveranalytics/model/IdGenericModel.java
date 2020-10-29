package me.reratos.serveranalytics.model;

import javax.persistence.*;

@MappedSuperclass
public class IdGenericModel<I> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private I id;

    /* Constructors */
    public IdGenericModel() {}

    public IdGenericModel(I id) {
        this.id = id;
    }

    /* Getters e Setters */
    public I getId() {
        return id;
    }

    public void setId(I id) {
        this.id = id;
    }

}
