package com.java.order.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.huston.springboot.crudgeneric.Alliance;
import com.huston.springboot.crudgeneric.GenericCrudEntity;
import com.java.address.model.Address;
import com.java.address.repository.AddressRepository;
import com.java.area.model.Area;
import com.java.area.repository.AreaRepository;
import com.java.contact.model.Contact;
import com.java.contact.repository.ContactRepository;
import com.java.customer.model.EcomCustomer;
import com.java.customer.repository.EcomCustomerRepository;
import com.java.order.repository.OrderItemRepository;
import com.java.order.state.engine.OrderState;
import com.java.order.state.engine.OrderStateFactory;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

@Entity
@Table(name = "customer_order")
public class CustomerOrder extends GenericCrudEntity<Integer> {

    @Column(name = "customer_id")
    private Integer customerId;

    @Column(name = "shipping_address_id")
    private Integer shippingAddressId;

    @Column(name = "shipping_contact_id")
    private Integer shippingContactId;

    @Column(name = "shipping_area_id")
    private Integer shippingAreaId;

    @Column(name = "payment_method")
    private String paymentMethod;

    private Double amount;

    @Column(name = "discount_percentage")
    private Float discountPercentage;

    @Column(name = "shipping_cost")
    private Double shippingCost;

    private Double total;
    private String status;

    @Column(name = "deliver_complete_period")
    private Integer deliverCompletePeriod;

    @Transient
    @Alliance(repositoryType = EcomCustomerRepository.class,localKey = "customerId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private EcomCustomer customer;

    @Transient
    @Alliance(repositoryType = OrderItemRepository.class, foreignKey = "customerOrderId", alliances = {"product","productVariation","childCustomerOrderItems"})
    @NotFound(action = NotFoundAction.IGNORE)
    private List<CustomerOrderItem> customerOrderItems;

    @Transient
    @Alliance(repositoryType = AddressRepository.class, localKey = "shippingAddressId", foreignKey = "id")
    private Address shippingAddress;

    @Transient
    @Alliance(repositoryType = ContactRepository.class, localKey = "shippingContactId", foreignKey = "id")
    private Contact shippingContact;

    @Transient
    @Alliance(repositoryType = AreaRepository.class, localKey = "shippingAreaId", foreignKey = "id", alliances = {"serviceOfArea"})
    private Area shippingArea;

    @Transient
    @JsonIgnore
    private OrderState orderState;

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public Double getShippingCost() {
        return shippingCost;
    }

    public void setShippingCost(Double shippingCost) {
        this.shippingCost = shippingCost;
    }

    public Integer getShippingAddressId() {
        return shippingAddressId;
    }

    public void setShippingAddressId(Integer shippingAddressId) {
        this.shippingAddressId = shippingAddressId;
    }

    public Integer getShippingContactId() {
        return shippingContactId;
    }

    public void setShippingContactId(Integer shippingContactId) {
        this.shippingContactId = shippingContactId;
    }

    public Integer getShippingAreaId() {
        return shippingAreaId;
    }

    public void setShippingAreaId(Integer shippingAreaId) {
        this.shippingAreaId = shippingAreaId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrderState getOrderState() {
        if(this.orderState == null){
            return OrderStateFactory.factory(this,this.status);
        }
        return this.orderState;
    }

    public void setOrderState(OrderState orderState) {
        if(this.orderState == null){
            OrderStateFactory.factory(this,this.status);
        }
    }

    public EcomCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(EcomCustomer customer) {
        this.customer = customer;
    }

    public List<CustomerOrderItem> getCustomerOrderItems() {
        return customerOrderItems;
    }

    public void setCustomerOrderItems(List<CustomerOrderItem> customerOrderItems) {
        this.customerOrderItems = customerOrderItems;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public Contact getShippingContact() {
        return shippingContact;
    }

    public void setShippingContact(Contact shippingContact) {
        this.shippingContact = shippingContact;
    }

    public Area getShippingArea() {
        return shippingArea;
    }

    public void setShippingArea(Area shippingArea) {
        this.shippingArea = shippingArea;
    }

    public Integer getDeliverCompletePeriod() {
        return deliverCompletePeriod;
    }

    public void setDeliverCompletePeriod(Integer deliverCompletePeriod) {
        this.deliverCompletePeriod = deliverCompletePeriod;
    }

    @Override
    public String toString() {
        return "CustomerOrder{" +
                "customerId=" + customerId +
                ", shippingAddressId=" + shippingAddressId +
                ", shippingContactId=" + shippingContactId +
                ", shippingAreaId=" + shippingAreaId +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", amount=" + amount +
                ", discountPercentage=" + discountPercentage +
                ", shippingCost=" + shippingCost +
                ", total=" + total +
                ", status='" + status + '\'' +
                ", deliverCompletePeriod=" + deliverCompletePeriod +
                ", customer=" + customer +
                ", customerOrderItems=" + customerOrderItems +
                ", shippingAddress=" + shippingAddress +
                ", shippingContact=" + shippingContact +
                ", shippingArea=" + shippingArea +
                ", orderState=" + orderState +
                '}';
    }
}
