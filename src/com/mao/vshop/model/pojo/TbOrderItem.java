package com.mao.vshop.model.pojo;
/**
 * ���������ʵ����
 * @author Administrator
 *
 */

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Entity
@Table(name = "TB_ORDER_ITEM")
public class TbOrderItem implements Serializable {
	private static final long serialVersionUID = -3918698643487770371L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id")
	@SequenceGenerator(name = "seq_id", sequenceName = "SEQ_ORDERITEM_ID")
	private Long id;
	private String role_name; // �µ��û�����Ϸ�еĽ�ɫ��
	private int count; // ��������

	// ���ö��һ
	@ManyToOne()
	@JoinColumn(name = "ORDER_ID")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private TbOrder order = new TbOrder();// ����ʵ���࣬�������order_id(FK)��ʵ��һ��һ��ϵ

	@ManyToOne()
	@JoinColumn(name = "PRODUCT_ID")
	// @Cascade(value=CascadeType.SAVE_UPDATE)
	private TbProduct product = new TbProduct();// ����ʵ���࣬�������product_id(FK)��ʵ��һ��һ��ϵ

	public TbOrderItem() {
	}

	public TbOrderItem(String role_name, int count) {
		this.role_name = role_name;
		this.count = count;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public TbOrder getOrder() {
		return order;
	}

	public void setOrder(TbOrder order) {
		this.order = order;
	}

	public TbProduct getProduct() {
		return product;
	}

	public void setProduct(TbProduct product) {
		this.product = product;
	}

	@Override
	public String toString() {
		return "TbOrderItem [role_name=" + role_name + ", count=" + count + "]";
	}
}
