package de.andre.entity.core;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by andreika on 2/28/2015.
 */
@Entity
@Table(name = "DPS_CREDIT_CARD", schema = "HYBRIS")
public class DpsCreditCard {
    private Integer creditId;
    private String creditCardNumber;
    private Date expirationDate;
    private DpsUser dpsUser;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profileSeq")
    @SequenceGenerator(name = "profileSeq", sequenceName = "profile_seq")
    @Column(name = "CREDIT_ID")
    public Integer getCreditId() {
        return creditId;
    }

    public void setCreditId(Integer creditId) {
        this.creditId = creditId;
    }

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CREDIT_ID")
    public DpsUser getDpsUser() {
        return dpsUser;
    }

    public void setDpsUser(DpsUser dpsUser) {
        this.dpsUser = dpsUser;
    }

    @NotBlank
    @CreditCardNumber
    @Column(name = "CREDIT_CARD_NUMBER")
    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @NotBlank
    @Column(name = "EXPIRATION_DATE")
    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DpsCreditCard)) return false;

        DpsCreditCard that = (DpsCreditCard) o;

        if (!creditCardNumber.equals(that.creditCardNumber)) return false;
        if (!creditId.equals(that.creditId)) return false;
        if (!expirationDate.equals(that.expirationDate)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = creditId.hashCode();
        result = 31 * result + creditCardNumber.hashCode();
        result = 31 * result + expirationDate.hashCode();
        return result;
    }
}
