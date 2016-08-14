CREATE DATABASE `obwq`  
CHARACTER SET 'utf8'  
COLLATE 'utf8_general_ci';  



create table d_category
(
   id                	varchar(32) not null,
   phone	            varchar(32),
   email                varchar(2000),
   descr                varchar(2000),
   gmt_create           datetime,
   gmt_modify           datetime,
   primary key (id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE UNIQUE INDEX  d_category_phone  ON d_category(phone);



create table d_category_detail(
id varchar(32) not null,
name varchar(32),
doTypeKey varchar(32),
doTypeValue varchar(2000),
button_type varchar(32), 
xseqt varchar(10),
cg_id varchar(32),
gmt_create datetime,
gmt_modify datetime,
primary key (id))ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE UNIQUE INDEX  d_category_phone  ON d_category(button_type,xseqt,cg_id);



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

CREATE UNIQUE INDEX  d_category_phone  ON d_category_detail(button_type,xseqt,cg_id);


create table t_user_plan
(
   up_id                varchar(32) not null,
   u_id                 varchar(32),
   p_id                 varchar(32),
   gmt_create           datetime,
   gmt_modify           datetime,
   primary key (up_id)
);



create table t_plan
(
   p_id                 varchar(32) not null,
   u_id                 varchar(2000),
   name                 varchar(2000),
   descr                varchar(2000),
   gmt_create           datetime,
   gmt_modify           datetime,
   primary key (p_id)
);



create table t_plan_detail
(
   pd_id                varchar(32) not null,
   p_id                 varchar(32),
   content				varchar(2000),
   xx                   double(10,4),
   yy                   double(10,4),
   picture_url          varchar(2000) comment '是相对路径',
   praise_count			numeric(18),
   evaluate_count		numeric(18),
   gmt_create           datetime,
   gmt_modify           datetime,
   primary key (pd_id)
);




create table t_setting
(
   s_id                 varchar(32) not null,
   u_id                 varchar(32),
   vkey                 varchar(2000),
   vvalue               varchar(2000),
   gmt_create           datetime,
   gmt_modify           datetime,
   primary key (s_id)
);


create table t_user_commit
(
   c_id                 varchar(32) not null,
   u_id                 varchar(32),
   c_desc               varchar(2000),
   gmt_create           datetime,
   gmt_modify           datetime,
   primary key (c_id)
);




create table t_evalute
(
   e_id                 varchar(32) not null,
   u_id                 varchar(32),
   pd_id                 varchar(32),
   primary key (e_id)
);



create table t_praise
(
   r_id                 varchar(32) not null,
   u_id                 varchar(32),
   pd_id                 varchar(32),
   primary key (r_id)
);




create table t_evaluate
(
   e_id                 varchar(32) not null,
   pd_id                varchar(32),
   u_id					varchar(32),
   nick_name			varchar(32),
   figter				varchar(800)
   content              varchar(200),
   primary key (r_id)
);



