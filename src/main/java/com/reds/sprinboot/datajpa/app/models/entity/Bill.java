package com.reds.sprinboot.datajpa.app.models.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "bills")
public class Bill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;
    private String observation;

    @Temporal(TemporalType.DATE)
    @Column(name = "create_at")
    private Date createAt;

    /* Establecemos relaciòn con Client */
    @ManyToOne(fetch = FetchType.LAZY) /*
                                        * El Many hace referencia a esta clase (Bill), muchas facturas para un cliente
                                        */
    private Client client;

    /* Establecemos relación con los itemFactura que son hijos de factura */
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL) /*
                                                                   * Aqui se utilizó JoinColumn
                                                                   * para indicar la relación ya que esta no es una
                                                                   * relación bidireccional es unidireccional
                                                                   */
    @JoinColumn(name = "bill_id")
    private List<ItemBill> items;

    public Bill(){
        this.items = new ArrayList<ItemBill>();
    }

    @PrePersist
    public void prePersist() { /* Mètodo que se encarga de generar la fecha */
        createAt = new Date();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getObservation() {
        return observation;
    }

    public void setObservation(String observation) {
        this.observation = observation;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public List<ItemBill> getItems() {
        return items;
    }

    public void setItems(List<ItemBill> items) {
        this.items = items;
    }

    private void addItemBill(ItemBill itemBill){
        this.items.add(itemBill);
    }

    public Double getTotal(){
        Double total = 0.0;

        int size = items.size();

        for(int i = 0; i < size; i ++){
            total += items.get(i).calcImport();
        }

        return total;
    }

    private static final Long serialVersionUID = 1L;
}
