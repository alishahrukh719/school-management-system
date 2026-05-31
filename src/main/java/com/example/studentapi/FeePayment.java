package com.example.studentapi;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "fee_payments")
public class FeePayment implements SchoolDataController.Identified {
    @Id
    @Column(length = 36)
    private String id;
    private String student;
    private String amount;
    private String date;
    private String status;
    private String feeType;
    private String paymentMode;
    private String receiptNo;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getStudent() { return student; }
    public void setStudent(String student) { this.student = student; }
    public String getAmount() { return amount; }
    public void setAmount(String amount) { this.amount = amount; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getFeeType() { return feeType; }
    public void setFeeType(String feeType) { this.feeType = feeType; }
    public String getPaymentMode() { return paymentMode; }
    public void setPaymentMode(String paymentMode) { this.paymentMode = paymentMode; }
    public String getReceiptNo() { return receiptNo; }
    public void setReceiptNo(String receiptNo) { this.receiptNo = receiptNo; }
}
