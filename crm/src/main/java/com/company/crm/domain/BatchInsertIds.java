package com.company.crm.domain;

/**
 * 该类专门用于批量插入
 * id_1和id_2分别代表两张表的id
 * @author yonghui
 *
 */
public class BatchInsertIds {
	private Long id_1;
	private Long id_2;

	public BatchInsertIds() {
		super();
	}

	public BatchInsertIds(Long id_1, Long id_2) {
		super();
		this.id_1 = id_1;
		this.id_2 = id_2;
	}

	public Long getId_1() {
		return id_1;
	}

	public void setId_1(Long id_1) {
		this.id_1 = id_1;
	}

	public Long getId_2() {
		return id_2;
	}

	public void setId_2(Long id_2) {
		this.id_2 = id_2;
	}

	@Override
	public String toString() {
		return "BatchInsertIds [id_1=" + id_1 + ", id_2=" + id_2 + "]";
	}

}
