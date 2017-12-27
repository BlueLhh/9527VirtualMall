package com.mao.vshop.model.pojo;
/**
 * ��Ϸ�˺�ʵ����
 * @author Administrator
 *
 */

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

@Entity
@Table(name = "TB_GAME_ACCOUNT")
public class TbGameAccount implements Serializable {
	private static final long serialVersionUID = -7897845563752441544L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_id")
	@SequenceGenerator(name = "seq_id", sequenceName = "SEQ_GAMEACCOUNT_ID")
	private Long id = -1L;
	private String game_name; // ��Ϸ��
	private String game_region; // ��Ϸ����
	private String game_server; // ��Ϸ������
	private String game_account; // ��Ϸ�˺�
	private String game_password; // ��Ϸ�˺�����
	private String second_password;// ��Ϸ�˺Ŷ�������
	private String role_name;// ��Ϸ��ɫ��

	// һ���˺ſ��Դ��ڶ����Ʒ��������Ʒ�ǲ�ͬ���˳��ۣ�
	// ��ʹ������ͬ����Ҳ����һ����ȫһ������Ʒ��������Ʒ����һ�Զ��ϵ
	// һ�Զ��ϵ
	@OneToMany(mappedBy = "account")
	@Cascade(value = CascadeType.SAVE_UPDATE)
	private Set<TbProduct> products = new HashSet<TbProduct>();

	public TbGameAccount() {
	}

	public TbGameAccount(String game_name, String game_region, String game_server, String game_account,
			String game_password, String second_password, String role_name) {
		this.game_name = game_name;
		this.game_region = game_region;
		this.game_server = game_server;
		this.game_account = game_account;
		this.game_password = game_password;
		this.second_password = second_password;
		this.role_name = role_name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getGame_name() {
		return game_name;
	}

	public void setGame_name(String game_name) {
		this.game_name = game_name;
	}

	public String getGame_region() {
		return game_region;
	}

	public void setGame_region(String game_region) {
		this.game_region = game_region;
	}

	public String getGame_server() {
		return game_server;
	}

	public void setGame_server(String game_server) {
		this.game_server = game_server;
	}

	public String getGame_account() {
		return game_account;
	}

	public void setGame_account(String game_account) {
		this.game_account = game_account;
	}

	public String getGame_password() {
		return game_password;
	}

	public void setGame_password(String game_password) {
		this.game_password = game_password;
	}

	public String getSecond_password() {
		return second_password;
	}

	public void setSecond_password(String second_password) {
		this.second_password = second_password;
	}

	public String getRole_name() {
		return role_name;
	}

	public void setRole_name(String role_name) {
		this.role_name = role_name;
	}

	public Set<TbProduct> getProducts() {
		return products;
	}

	public void setProducts(Set<TbProduct> products) {
		this.products = products;
	}

	@Override
	public String toString() {
		return "TbGameAccount [id=" + id + ", game_name=" + game_name + ", game_region=" + game_region
				+ ", game_server=" + game_server + ", game_account=" + game_account + ", game_password=" + game_password
				+ ", second_password=" + second_password + ", role_name=" + role_name + "]";
	}
}
