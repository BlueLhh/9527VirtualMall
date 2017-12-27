package com.mao.vshop.model.pojo;
/**
 * ��Ʒʵ����
 * @author Administrator
 *
 */

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TB_PRODUCT")
public class TbProduct implements Serializable {
	private static final long serialVersionUID = 3373361796341934376L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id")
	@SequenceGenerator(name = "seq_id", sequenceName = "SEQ_PRODUCT_ID")
	private Long id = -1L;
	@Column(name = "product_name")
	private String product_name; // ��Ʒ��
	@Column(name = "stock")
	private int stock; // ���
	@Column(name = "price")
	private int price; // ����
	@Column(name = "up_date")
	private Date up_date; // ��Ʒ�ϼ�����
	@Column(name = "status")
	private int status; // ��Ʒ����״̬ ��-1.ȡ���ϼ� 0.������ 1.������ 2.���׳ɹ���
	@Column(name = "describe")
	private String describe;// ��Ʒ����
	@Column(name = "fileName")
	private String fileName; // ��ƷͼƬ·��

	// ע��ʵ�ֶ��һ��ϵ
	@ManyToOne()
	// ע�����
	@JoinColumn(name = "USER_ID")
	// @Cascade(value = CascadeType.SAVE_UPDATE)
	private TbUser user = new TbUser(); // ����ʵ���࣬�������user_id(FK)��������ID��ʵ�ֶ��һ��ϵ

	@ManyToOne()
	@JoinColumn(name = "GAME_ACCOUNT_ID")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private TbGameAccount account = new TbGameAccount();// ����ʵ���࣬�������game_account_id(FK)��������ID��ʵ�ֶ��һ��ϵ

	@OneToMany(mappedBy = "product")
	@Fetch(FetchMode.JOIN)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private Set<TbOrderItem> items = new HashSet<TbOrderItem>();// �붩���������һ�Զ��ϵ

	// ע�⣬��ʾ���ֶβ�д�����ݿ�
	@Transient
	private int sellCount = 0;// �ɽ����������ݿ��в���Ҫд����ֶ�

	public TbProduct() {
	}

	public TbProduct(String product_name, int stock, int price, Date up_date, int status, String describe,
			String fileName) {
		this.product_name = product_name;
		this.stock = stock;
		this.price = price;
		this.up_date = up_date;
		this.status = status;
		this.describe = describe;
		this.fileName = fileName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getUp_date() {
		return up_date;
	}

	public void setUp_date(Date up_date) {
		this.up_date = up_date;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

	public TbGameAccount getAccount() {
		return account;
	}

	public void setAccount(TbGameAccount account) {
		this.account = account;
	}

	public Set<TbOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<TbOrderItem> items) {
		this.items = items;
	}

	public int getSellCount() {
		return sellCount;
	}

	public void setSellCount(int sellCount) {
		this.sellCount = sellCount;
	}

	@Override
	public String toString() {
		return "TbProduct [id=" + id + ", product_name=" + product_name + ", stock=" + stock + ", price=" + price
				+ ", up_date=" + up_date + ", status=" + status + ", describe=" + describe + ", fileName=" + fileName
				+ "]";
	}
}
