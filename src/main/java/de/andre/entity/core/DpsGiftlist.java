package de.andre.entity.core;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Set;

/**
 * Created by Andrei on 3/30/2015.
 */
@Entity
@Table(name = "DPS_GIFTLIST", schema = "HYBRIS")
public class DpsGiftlist {
	private Integer giftListId;
	private Boolean isPublished;
	private Timestamp creationDate;
	private DpsAddress shippingAddr;
	private DpsUser dpsUser;
	private Set<DpsGiftitem> dpsGiftitems;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profileSeq")
	@SequenceGenerator(name = "profileSeq", sequenceName = "profile_seq")
	@Column(name = "GIFT_LIST_ID")
	public Integer getGiftListId() {
		return giftListId;
	}

	public void setGiftListId(Integer giftListId) {
		this.giftListId = giftListId;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "dpsGiftlist")
	public Set<DpsGiftitem> getDpsGiftitems() {
		return dpsGiftitems;
	}

	public void setDpsGiftitems(Set<DpsGiftitem> dpsGiftitems) {
		this.dpsGiftitems = dpsGiftitems;
	}

	@Type(type = "boolean")
	@Column(name = "IS_PUBLISHED")
	public Boolean getIsPublished() {
		return isPublished;
	}

	public void setIsPublished(Boolean isPublished) {
		this.isPublished = isPublished;
	}

	@OneToOne
	@JoinColumn(name = "GIFT_LIST_ID")
	public DpsUser getDpsUser() {
		return dpsUser;
	}

	public void setDpsUser(DpsUser dpsUser) {
		this.dpsUser = dpsUser;
	}


	@Column(name = "CREATION_DATE")
	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	@OneToOne
	@JoinColumn(name = "SHIPPING_ADDR_ID", referencedColumnName = "ADDRESS_ID")
	public DpsAddress getShippingAddr() {
		return shippingAddr;
	}

	public void setShippingAddr(DpsAddress shippingAddr) {
		this.shippingAddr = shippingAddr;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		DpsGiftlist that = (DpsGiftlist) o;

		if (giftListId != null ? !giftListId.equals(that.giftListId) : that.giftListId != null) return false;
		if (isPublished != null ? !isPublished.equals(that.isPublished) : that.isPublished != null) return false;
		if (creationDate != null ? !creationDate.equals(that.creationDate) : that.creationDate != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = giftListId != null ? giftListId.hashCode() : 0;
		result = 31 * result + (isPublished != null ? isPublished.hashCode() : 0);
		result = 31 * result + (creationDate != null ? creationDate.hashCode() : 0);
		return result;
	}
}