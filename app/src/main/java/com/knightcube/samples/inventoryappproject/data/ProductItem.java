package com.knightcube.samples.inventoryappproject.data;

public class ProductItem {

    private String productName;
    private String productPrice;
    private int productQuantity;
    private String productSupplierName;
    private String productSupplierEmail;
    private String productSupplierPhone;
    private String productImage;

    public ProductItem(String productName, String productPrice, int productQuantity, String productSupplierName, String productSupplierEmail, String productSupplierPhone, String productImage) {
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productSupplierName = productSupplierName;
        this.productSupplierEmail = productSupplierEmail;
        this.productSupplierPhone = productSupplierPhone;
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public String getProductSupplierName() {
        return productSupplierName;
    }

    public void setProductSupplierName(String productSupplierName) {
        this.productSupplierName = productSupplierName;
    }

    public String getProductSupplierEmail() {
        return productSupplierEmail;
    }

    public void setProductSupplierEmail(String productSupplierEmail) {
        this.productSupplierEmail = productSupplierEmail;
    }

    public String getProductSupplierPhone() {
        return productSupplierPhone;
    }

    public void setProductSupplierPhone(String productSupplierPhone) {
        this.productSupplierPhone = productSupplierPhone;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }
}
