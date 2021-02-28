-- ----------------------------
--  Table structure for cms_user_primary 用户主表
-- ----------------------------
CREATE TABLE cms_user_primary (
	create_time timestamp  not null  default CURRENT_TIMESTAMP,
	update_time timestamp  not null default '0000-00-00 00:00:00',
    id int(11) NOT NULL AUTO_INCREMENT primary key,
    username varchar(50) not null comment '用户名',
    password varchar(64) not null comment '用户密码，MD5加密或sha256散列加密',
	salt varchar(64) not null comment '密码盐',
    email varchar(50) not null default '' comment '邮箱',
    login_count int(10) not null default 0 comment '登陆次数',
  UNIQUE KEY user_name_unique (username) USING BTREE,
  UNIQUE KEY user_email_unique (email) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cms_user_primary (id, create_time, update_time, username, password, salt, email, login_count) VALUES (1, '2019-06-14 11:30:58', null, 'admin', 'e298f9b29585da289080ffebb32e6f931b52a61195bcf6246d3e0f24654897eb', '6e4abc9695661ce11f442eaea3cb6540', 'abc@126.com', 0);
INSERT INTO cms_user_primary (id, create_time, update_time, username, password, salt, email, login_count) VALUES (2, '2019-06-14 11:30:58', null, 'administrator', 'e298f9b29585da289080ffebb32e6f931b52a61195bcf6246d3e0f24654897eb', '6e4abc9695661ce11f442eaea3cb6540', 'def@126.com', 0);


-- ----------------------------
--  Table structure for cms_user  用户副表
-- ----------------------------
CREATE TABLE cms_user (
    create_time timestamp  not null  default CURRENT_TIMESTAMP,
    update_time timestamp  not null default '0000-00-00 00:00:00',
    id int(11) NOT NULL AUTO_INCREMENT primary key,
    group_id int null comment '会员组ID',
    username varchar(50) not null comment '用户名',
    email varchar(50) not null default '' comment '邮箱',
    rank int default '0' not null comment '管理员级别',
    is_admin tinyint(1) not null default 0 comment '是否后台用户 0不是 1是',
    is_super tinyint(1) not null default 0 comment '是否超级管理员 0不是 1是',
    is_self_admin tinyint(1) default '0' not null comment '是否受限管理员 是否只管理自己的数据 0:否 1:是',
    register_time timestamp not null comment '注册时间',
    register_ip varchar(50) default '127.0.0.1' not null comment '注册IP',
    login_count int(10) not null default 0 comment '登陆次数',
    upload_total bigint default '0' not null comment '上传总大小',
    upload_size int default '0' not null comment '上传大小',
    is_viewonly_admin tinyint(1) default '0' not null comment '是否只读管理员 0:否 1:是',
    upload_date timestamp null comment '上传日期',
    session_id varchar(200) not null default '' comment '用户session id',
    status tinyint(1) default '1' not null comment '状态',
    deleted tinyint(1) default '1' not null comment '是否已删除 0:删除 1正常',
    last_login_ip varchar(30) not null default '' comment '最后登录ip',
    last_login_time timestamp  not null default '0000-00-00 00:00:00' comment '最后登录时间',
    UNIQUE KEY user_name_unique (username) USING BTREE,
    UNIQUE KEY user_email_unique (email) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO cms_user (id, create_time, update_time, group_id, username, email, rank, is_admin, is_super, is_self_admin, register_time, register_ip, login_count, upload_total, upload_size, is_viewonly_admin, upload_date, session_id, last_login_ip, last_login_time) VALUES (1, '2019-06-14 11:30:58', null, 1, 'admin', 'abc@126.com', 1, 1, 1, 0, '2019-06-14 11:30:58', '127.0.0.1', 0, 0, 0, 0, null, '', '', null);
INSERT INTO cms_user(id, create_time, update_time, group_id, username, email, rank, is_admin, is_self_admin, register_time, register_ip, login_count, upload_total, upload_size, is_viewonly_admin, upload_date, session_id, last_login_ip, last_login_time) VALUES (2, '2019-06-14 11:30:58', null, 1, 'administrator', 'def@126.com', 1, 1, 0, '2019-06-14 11:30:58', '127.0.0.1', 0, 0, 0, 0, null, '', '', null);


-- ----------------------------
--  Table structure for cms_log  日志表
-- ----------------------------
CREATE TABLE cms_log (
	create_time timestamp  not null  default CURRENT_TIMESTAMP,
	update_time timestamp  not null default '0000-00-00 00:00:00',
    id int(11) NOT NULL AUTO_INCREMENT primary key,
    user_id int(11) not null comment '用户id',
    username varchar(25) not null comment '用户名称',
    login_ip varchar(30) default '' comment 'ip地址',
    url varchar(100) default '' comment 'URL地址',
	content varchar(100) null comment '日志内容'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- ----------------------------
-- Table structure for cms_site  站点表
-- ----------------------------
CREATE TABLE cms_site
(
    create_time        timestamp                   not null default CURRENT_TIMESTAMP,
    update_time        timestamp                   not null default '0000-00-00 00:00:00',
    id                 int(11)                     NOT NULL AUTO_INCREMENT primary key,
    site_name          varchar(100)                not null comment '网站名称',
    keywords           varchar(255)                not null default '' comment '站点关键字',
    description        varchar(255)                not null default '' comment '站点描述'
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8 comment ='CMS站点表';

INSERT INTO cms_site (id, site_name, keywords, description)
VALUES (1, 'cms官网','cms,内容管理', 'cms内容管理系统');








