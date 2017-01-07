package cn.obwq.entity;


public class UserArticle extends BaseDo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2332222887594013425L;

	
	private Long id ;

	private String userId ; 
	
	private Long articleId ;

	private Long type ;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Long getArticleId() {
		return articleId;
	}

	public void setArticleId(Long articleId) {
		this.articleId = articleId;
	}

	public Long getType() {
		return type;
	}

	public void setType(Long type) {
		this.type = type;
	}
	
	
}
