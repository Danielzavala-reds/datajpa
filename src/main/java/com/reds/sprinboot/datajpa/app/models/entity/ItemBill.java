package com.reds.sprinboot.datajpa.app.models.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

/**
 * Al crear esta clase se establece la relación con Bill, es decir, Bill tendrá
 * muchos items bills
 * no tendía sentido que ItemBill tenga la relación directa ya que no
 * consultaremos
 * un item en específico de una factura
 */

@Entity
@Table(name = "bills_items")
public class ItemBill implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer amount;

    @ManyToOne(fetch = FetchType.LAZY) /*
                                        * Mapeamos ManyToOne es decir muchos ItemBill a un product, el ItemBill va a
                                        * tener un solo product, pero al relacion inversa no se necesitara ya que nunca
                                        * en un product vamos a listar los ItemBill, por ende es una relación
                                        * unidireccional
                                        */
    @JoinColumn(name = "product_id") /*
                                      * Automaticamente se creara el atributo de la llave foranaea en la tabla
                                      * ItemBill, pero podemos se explicitos y especificar el JoinColumn aunque en
                                      * este caso
                                      * es opcional
                                      */
    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    /* Calcular el importe */
    public Double calcImport() {
        return amount.doubleValue() * product.getPrice();
    }

    private static final long serialVersionUID = 1L;
}
