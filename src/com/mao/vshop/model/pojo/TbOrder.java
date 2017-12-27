package com.mao.vshop.model.pojo;
/**
 * ������ʵ����
 * @author Administrator
 *
 */

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name = "TB_ORDER")
public class TbOrder implements Serializable {
	private static final long serialVersionUID = 9111597465588178067L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id")
	@SequenceGenerator(name = "seq_id", sequenceName = "SEQ_ORDER_ID")
	private Long id = -1L;
	private String phone; // �û�����ϵ�绰
	private String qq;// �û�����ϵQQ
	private Date shopDate;// �û��µ�����
	private int status; // ����״̬ ��-1.�������� 0.ȷ���ջ� 1.������ɣ�

	// ���ö��һע��
	@ManyToOne()
	// ���
	@JoinColumn(name = "USER_ID")
	// @Cascade(value=CascadeType.SAVE_UPDATE)
	private TbUser user = new TbUser();// ����ʵ���࣬�������user_id(FK)��ʵ�ֶ��һ��ϵ

	// ����һ�Զ��ϵ
	@OneToMany(mappedBy = "order")
	@Fetch(FetchMode.JOIN)
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private Set<TbOrderItem> items = new HashSet<TbOrderItem>();// �붩���������һ�Զ��ϵ

	public TbOrder() {
	}

	public TbOrder(String phone, String qq, Date shopDate, int status) {
		this.phone = phone;
		this.qq = qq;
		this.shopDate = shopDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public Date getShopDate() {
		return shopDate;
	}

	public void setShopDate(Date shopDate) {
		this.shopDate = shopDate;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public TbUser getUser() {
		return user;
	}

	public void setUser(TbUser user) {
		this.user = user;
	}

	public Set<TbOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<TbOrderItem> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "TbOrder [id=" + id + ", phone=" + phone + ", qq=" + qq + ", shopDate=" + shopDate + ", status=" + status
				+ "]";
	}
}
