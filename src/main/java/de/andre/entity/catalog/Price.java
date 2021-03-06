package de.andre.entity.catalog;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.math.BigDecimal;

@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Immutable
@Entity
@Table(name = "hc_price", schema = "hybris")
public class Price {
    private String id;
    private BigDecimal listPrice;

    private PriceList priceList;
    private Product product;

    public Price() {
    }

    public Price(String id, BigDecimal listPrice) {
        this.id = id;
        this.listPrice = listPrice;
    }

    @Id
    @Column(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ManyToOne
    @JoinColumn(name = "price_list_id")
    public PriceList getPriceList() {
        return priceList;
    }

    public void setPriceList(PriceList priceList) {
        this.priceList = priceList;
    }

    @OneToOne
    @JoinColumn(name = "product_id")
    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Column(name = "list_price")
    public BigDecimal getListPrice() {
        return listPrice;
    }

    public void setListPrice(BigDecimal listPrice) {
        this.listPrice = listPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Price hcPrice = (Price) o;

        if (id != null ? !id.equals(hcPrice.id) : hcPrice.id != null) return false;
        if (listPrice != null ? !listPrice.equals(hcPrice.listPrice) : hcPrice.listPrice != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (listPrice != null ? listPrice.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Price{" +
                "id='" + id + '\'' +
                ", listPrice=" + listPrice +
                '}';
    }
}
