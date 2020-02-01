package com.java.order.model;

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
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.List;

/**
 * CREATE VIEW customer_order_view AS
 * SELECT customer_order.*, user.email AS customer_email, customer.name AS customer_name
 * FROM customer_order
 * INNER JOIN customer ON customer.id = customer_order.customer_id
 * INNER JOIN user ON user.id = customer.user_id
 * INNER JOIN address ON address.id = customer_order.shipping_address_id
 * GROUP BY customer_order.id
 */

@Entity
@Table(name = "customer_order_view")
public class CustomerOrderView extends GenericCrudEntity<Integer> {

    @Column(name = "customer_id")
    private Integer customerId;

    private Double amount;

    @Column(name = "payment_method")
    private String paymentMethod;

    private Double total;

    @Column(name = "discount_percentage")
    private Float discountPercentage;

    @Column(name = "shipping_address_id")
    private Integer shippingAddressId;

    @Column(name = "shipping_contact_id")
    private Integer shippingContactId;

    @Column(name = "shipping_area_id")
    private Integer shippingAreaId;

    private String status;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_name")
    private String customerName;

    @Column(name = "deliver_complete_period")
    private Integer deliverCompletePeriod;

    @Transient
    @Alliance(repositoryType = EcomCustomerRepository.class,localKey = "customerId", foreignKey = "id")
    @NotFound(action = NotFoundAction.IGNORE)
    private EcomCustomer customer;

    @Transient
    @Alliance(repositoryType = OrderItemRepository.class, foreignKey = "customerOrderId", alliances = {"product","productVariation"})
    @NotFound(action = NotFoundAction.IGNORE)
    private List<CustomerOrderItem> customerOrderItems;

    @Transient
    @Alliance(repositoryType = AddressRepository.class, localKey = "shippingAddressId", foreignKey = "id")
    private Address shippingAddress;

    @Transient
    @Alliance(repositoryType = ContactRepository.class, localKey = "shippingContactId", foreignKey = "id")
    private Contact shippingContact;

    @Transient
    @Alliance(repositoryType = AreaRepository.class, localKey = "shippingAreaId", foreignKey = "id" , alliances = {"serviceOfArea"})
    private Area shippingArea;

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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public Integer getDeliverCompletePeriod() {
        return deliverCompletePeriod;
    }

    public void setDeliverCompletePeriod(Integer deliverCompletePeriod) {
        this.deliverCompletePeriod = deliverCompletePeriod;
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

    @Override
    public String toString() {
        return "CustomerOrderView{" +
                "customerId=" + customerId +
                ", amount=" + amount +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", total=" + total +
                ", discountPercentage=" + discountPercentage +
                ", shippingAddressId=" + shippingAddressId +
                ", shippingContactId=" + shippingContactId +
                ", shippingAreaId=" + shippingAreaId +
                ", status='" + status + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customer=" + customer +
                ", customerOrderItems=" + customerOrderItems +
                ", shippingAddress=" + shippingAddress +
                ", shippingContact=" + shippingContact +
                ", shippingArea=" + shippingArea +
                '}';
    }
}
