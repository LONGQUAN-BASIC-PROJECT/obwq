CREATE DATABASE `obwq`  
CHARACTER SET 'utf8'  
COLLATE 'utf8_general_ci';  


create table t_user
(
   u_id                 varchar(32) not null,
   nick_name            varchar(2000),
   email                varchar(2000),
   password             varchar(2000),
   user_type            numeric(18),
   out_user_id          varchar(2000),
   gmt_create           datetime,
   gmt_modify           datetime,
   primary key (u_id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;





create table t_agroup
(
   g_id int      auto_increment  primary key , 
   name	            varchar(2000),
   descr	            varchar(2000),
   type	                numeric(18),
   acount	            numeric(18),
   gmt_create           datetime,
   gmt_modify           datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;





create table t_article
(
   a_id                 int  auto_increment primary key  ,
   title	            varchar(2000),
   url	                varchar(2000),
     group_id   numeric(18),
   praise	            numeric(18),
   comment	            numeric(18),
   collection           numeric(18),
   gmt_create           datetime,
   gmt_modify           datetime
   
)ENGINE=InnoDB DEFAULT CHARSET=utf8;




create table t_user_agroup
(
   ug_id               int  auto_increment primary key ,
   user_id	            varchar(2000),
   group_id		        numeric(18),
   gmt_create           datetime,
   gmt_modify           datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;


create table t_user_article
(
   ua_id               int  auto_increment primary key ,
   user_id	            varchar(2000),
   article_id	        numeric(18),
   type	                numeric(18),
   gmt_create           datetime,
   gmt_modify           datetime
)ENGINE=InnoDB DEFAULT CHARSET=utf8;




