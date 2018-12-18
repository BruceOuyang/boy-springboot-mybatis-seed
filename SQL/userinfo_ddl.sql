create table user_info
(
  id                   int not null auto_increment comment '主键',
  name                 varchar(50) comment '用户姓名',
  ename                 varchar(50) comment '英文名',
  remark               varchar(100) comment '备注',
  version              int NOT NULL DEFAULT 0 comment '乐观锁',
  create_dt            datetime NOT NULL DEFAULT CURRENT_TIMESTAMP comment '创建时间',
  create_by            varchar(50) comment '创建人标识-用户表id + 可选值( 姓名、工号、手机号 )',
  update_dt            datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP comment '修改时间',
  update_by            varchar(50) comment '修改人标识-用户表id + 可选值( 姓名、工号、手机号 )',
  is_delete            int NOT NULL DEFAULT 0 comment '删除标识',
  primary key (id)
) engine=innodb charset utf8 collate utf8_bin comment '用户信息表';