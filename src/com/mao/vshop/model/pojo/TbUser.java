package com.mao.vshop.model.pojo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

/**
 * 用户实体类
 * 
 * @author Administrator
 *
 */
@Entity
@Table(name = "TB_USER")
public class TbUser implements Serializable {
	private static final long serialVersionUID = 1774239868858330678L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id")
	@SequenceGenerator(name = "seq_id", sequenceName = "SEQ_USER_ID")
	private Long id = -1L;
	private String name; // 用户名
	private String password; // 密码
	private String gender;
	private int age;
	private String idCard;// 身份证
	private String phone;
	private String qq;
	private int tradeCount;// 交易次数
	private int creditLevel; // 信誉等级
	private int balance; // 账户余额

	@OneToMany(mappedBy = "user")
	@Fetch(FetchMode.JOIN)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private Set<TbProduct> plist = new HashSet<TbProduct>(); // 与商品表建立一对多关系

	@OneToMany(mappedBy = "user")
	@Fetch(FetchMode.JOIN)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private Set<TbOrder> olist = new HashSet<TbOrder>();// 与订单表建立一对多关系

	public TbUser() {
	}

	public TbUser(String name, String password, String gender, int age, String idCard, String phone, String qq,
			int tradeCount, int creditLevel, int balance) {
		this.name = name;
		this.password = password;
		this.gender = gender;
		this.age = age;
		this.idCard = idCard;
		this.phone = phone;
		this.qq = qq;
		this.tradeCount = tradeCount;
		this.creditLevel = creditLevel;
		this.balance = balance;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public int getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}

	public int getCreditLevel() {
		return creditLevel;
	}

	public void setCreditLevel(int creditLevel) {
		this.creditLevel = creditLevel;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	public Set<TbProduct> getPlist() {
		return plist;
	}

	public void setPlist(Set<TbProduct> plist) {
		this.plist = plist;
	}

	public Set<TbOrder> getOlist() {
		return olist;
	}

	public void setOlist(Set<TbOrder> olist) {
		this.olist = olist;
	}

	@Override
	public String toString() {
		return "TbUser [id=" + id + ", name=" + name + ", password=" + password + ", gender=" + gender + ", age=" + age
				+ ", idCard=" + idCard + ", phone=" + phone + ", qq=" + qq + ", tradeCount=" + tradeCount
				+ ", creditLevel=" + creditLevel + ", balance=" + balance + "]";
	}
}
