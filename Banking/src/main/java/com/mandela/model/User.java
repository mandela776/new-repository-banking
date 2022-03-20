package com.mandela.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.Arrays;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The persistent class for the BANKING_TBL database table.
 * 
 */
@Entity
@Table(name = "BANKING_TBL")
@NamedQuery(name = "User.findAll", query = "SELECT u FROM User u")
public class User implements Serializable {
	@Override
	public String toString() {
		return "User [id=" + id + ", accountno=" + accountno + ", address=" + address + ", balance=" + balance
				+ ", city=" + city + ", dateofbirth=" + dateofbirth + ", fullname=" + fullname + ", gender=" + gender
				+ ", idcardno=" + idcardno + ", image=" + Arrays.toString(image) + ", martual=" + martual
				+ ", mobileno=" + mobileno + ", pincode=" + pincode + ", religion=" + religion + ", toaccountno="
				+ toaccountno + ", transferamount=" + transferamount + "]";
	}

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "BANKING_TBL_ID_GENERATOR", sequenceName = "BANKING_SEQUENCE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "BANKING_TBL_ID_GENERATOR")
	private long id;

	private Long accountno;

	private String address;

	private Long balance;

	private String city;

	private Date dateofbirth;

	private String fullname;

	private String gender;

	private Long idcardno;

	@Lob
	private Byte[] image;

	private String martual;

	private Long mobileno;

	private Long pincode;

	private String religion;

	private Long toaccountno;
	
	private Long transferamount;
	
	public Long getToaccountno() {
		return toaccountno;
	}

	public void setToaccountno(Long toaccountno) {
		this.toaccountno = toaccountno;
	}

	public Long getTransferamount() {
		return transferamount;
	}

	public void setTransferamount(Long transferamount) {
		this.transferamount = transferamount;
	}

	public User() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Long getAccountno() {
		return accountno;
	}

	public void setAccountno(Long accountno) {
		this.accountno = accountno;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getBalance() {
		return balance;
	}

	public void setBalance(Long balance) {
		this.balance = balance;
	}

	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Date getDateofbirth() {
		return dateofbirth;
	}

	public void setDateofbirth(Date dateofbirth) {
		this.dateofbirth = dateofbirth;
	}

	public String getFullname() {
		return this.fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getGender() {
		return this.gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Byte[] getImage() {
		return image;
	}

	public void setImage(Byte[] image) {
		this.image = image;
	}

	public String getMartual() {
		return this.martual;
	}

	public void setMartual(String martual) {
		this.martual = martual;
	}

	public Long getIdcardno() {
		return idcardno;
	}

	public void setIdcardno(Long idcardno) {
		this.idcardno = idcardno;
	}

	public Long getMobileno() {
		return mobileno;
	}

	public void setMobileno(Long mobileno) {
		this.mobileno = mobileno;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getReligion() {
		return this.religion;
	}

	public void setReligion(String religion) {
		this.religion = religion;
	}

}