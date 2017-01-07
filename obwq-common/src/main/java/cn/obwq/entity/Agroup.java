package cn.obwq.entity;



/**
 * 圈子
 * @author guoxing.zgx
 *
 */
public class Agroup extends  BaseDo {

	private static final long serialVersionUID = 8916129837966410798L;

	private Long id ;
	
	private String name ;
	
	private String descr ;
	
	/**
	 * 1、私有的
	 * 2、公开的
	 */
	private Integer type ;
	
	
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
	public String getDescr() {
		return descr;
	}
	public void setDescr(String descr) {
		this.descr = descr;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	
	
}
