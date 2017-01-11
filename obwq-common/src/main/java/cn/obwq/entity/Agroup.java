package cn.obwq.entity;



import cn.obwq.util.FeatureUtil;
import cn.obwq.util.StringUtils;

import java.util.Map;

/**
 * 圈子
 *
 * @author guoxing.zgx
 */
public class Agroup extends BaseDo {

/*
    url	                varchar(2000),
    logo	                varchar(2000),
    type	                numeric(18),
    acount	            numeric(18),
    */

    private static final long serialVersionUID = 8916129837966410798L;

    private Long id;

    private String name;

    private String descr;

    private String url ;

    private String logo ;

    private Long account ;

    /**
     * 1、私有的
     * 2、公开的
     */
    private Integer type;


    /**
     * 其它属性
     */
    private String feature;

    /**
     * 其它属性
     */
    private Map<String,String> attr;


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


    protected void initattrMap() {
        if (null == attr) {
            attr = FeatureUtil.initAttrMap(feature);
        }
    }

    public void addFeature(String name, String value){

        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
            initattrMap();
            attr.put(name, value);
            resetFeature();
        }
    }

    public String getFeature(String name){
        initattrMap();
        String value = attr.get(name);
        return value==null ? "" : value;
    }

    public boolean removeFeature(String name){
        initattrMap();
        boolean flag = false;
        if(attr.containsKey(name)){
            attr.remove(name);
            resetFeature();
            flag = true;
        }else{
            flag = false;
        }
        return flag;
    }

    private void resetFeature(){
        this.feature = FeatureUtil.resetAttrMap(attr);
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public Long getAccount() {
        return account;
    }

    public void setAccount(Long account) {
        this.account = account;
    }
}
