package com.cyx.pojo;

public class CustomerServiceInfo {
    private String customerserviceId;

    private String customerservicePhoneno;

    public CustomerServiceInfo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomerServiceInfo(String customerserviceId,
			String customerservicePhoneno) {
		super();
		this.customerserviceId = customerserviceId;
		this.customerservicePhoneno = customerservicePhoneno;
	}

	public String getCustomerserviceId() {
        return customerserviceId;
    }

    public void setCustomerserviceId(String customerserviceId) {
        this.customerserviceId = customerserviceId == null ? null : customerserviceId.trim();
    }

    public String getCustomerservicePhoneno() {
        return customerservicePhoneno;
    }

    public void setCustomerservicePhoneno(String customerservicePhoneno) {
        this.customerservicePhoneno = customerservicePhoneno == null ? null : customerservicePhoneno.trim();
    }
}