/**
*用户
*/
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `login_name` varchar(30) NOT NULL COMMENT '登录帐号,自动生成，可以用手机号登录',
  `login_pass` varchar(32) DEFAULT NULL COMMENT '登录密码加密',
  `pay_password` varchar(50) DEFAULT NULL COMMENT '支付密码',  
  `nick_name` varchar(50) DEFAULT NULL COMMENT '用户昵称',
  `user_type` tinyint(3)   NOT NULL COMMENT '用户类型（1-商户；2.普通用户）',
  `bind_mobile` varchar(20) DEFAULT NULL COMMENT '用户绑定的手机号，可以用手机号登录',
  `bind_wechat` varchar(200) DEFAULT NULL COMMENT '用户绑定的微信号OpenID',
  `bind_qq` varchar(20) DEFAULT NULL,
  `state` tinyint(3)   NOT NULL COMMENT '用户状态（0-未激活；1-启用；2-停用）',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `login_count` int(11) DEFAULT '0' COMMENT '登录次数',
  `last_login_time` datetime DEFAULT NULL COMMENT '上次登录时间',
  `last_login_ip` varchar(20) DEFAULT NULL COMMENT '上次登录IP',
  `user_regtime` datetime NOT NULL COMMENT '注册时间',
  `user_regip` varchar(50) DEFAULT NULL COMMENT '注册IP',
  `pic` varchar(255) DEFAULT NULL COMMENT '头像'
  `remark` varchar(500),
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `phone` (`bind_mobile`),
  KEY `id` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='用户表';
/**
*配送地址
*/
DROP TABLE IF EXISTS `user_addr`;
CREATE TABLE `user_addr` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `user_id` varchar(36) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `receiver` varchar(50) DEFAULT NULL COMMENT '收货人',
  `province_id` bigint(20) DEFAULT NULL COMMENT '省ID',
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(20) DEFAULT NULL COMMENT '城市',
  `city_id` bigint(20) DEFAULT NULL COMMENT '城市ID',
  `area` varchar(20) DEFAULT NULL COMMENT '区',
  `area_id` bigint(20) DEFAULT NULL COMMENT '区ID',
  `post_code` varchar(15) DEFAULT NULL COMMENT '邮编',
  `addr` varchar(1000) DEFAULT NULL COMMENT '地址',
  `mobile` varchar(20) DEFAULT NULL COMMENT '手机',
  `status` int(1) NOT NULL COMMENT '状态,1正常，0无效',
  `common_addr` int(1) NOT NULL DEFAULT '0' COMMENT '是否默认地址 1是',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='用户配送地址';

/**
*订单配送
*/
DROP TABLE IF EXISTS `user_order_addr`;

CREATE TABLE `user_order_addr` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `addr_id` bigint(20)  NOT NULL COMMENT '地址ID',
  `order_id` varchar(36) NOT NULL DEFAULT '0' COMMENT 'order_id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户订单配送地址';



/**
*商铺
*/
DROP TABLE IF EXISTS `shop`;

CREATE TABLE `shop` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '店铺id',
  `shop_name` varchar(50) DEFAULT NULL COMMENT '店铺名称(数字、中文，英文(可混合，不可有特殊字符)，可修改)、不唯一',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `shop_type` tinyint(2) DEFAULT NULL COMMENT '店铺类型',
  `intro` varchar(200) DEFAULT NULL COMMENT '店铺简介',
  `shop_notice` varchar(50) DEFAULT NULL COMMENT '店铺公告',
  `shop_industry` tinyint(2) DEFAULT NULL COMMENT '店铺行业',
  `shop_owner` varchar(20) DEFAULT NULL COMMENT '店长',
  `mobile` varchar(20) DEFAULT NULL COMMENT '店铺绑定的手机',
  `tel` varchar(20) DEFAULT NULL COMMENT '店铺联系电话',
  `shop_lat` varchar(20) DEFAULT NULL COMMENT '店铺所在纬度(可修改)',
  `shop_lng` varchar(20) DEFAULT NULL COMMENT '店铺所在经度(可修改)',
  `shop_address` varchar(100) DEFAULT NULL COMMENT '店铺详细地址',
  `shop_logo` varchar(200) DEFAULT NULL COMMENT '店铺logo(可修改)',
  `shop_photos` varchar(1000) DEFAULT NULL COMMENT '店铺相册',
  `open_time` varchar(100) DEFAULT NULL COMMENT '每天营业时间段(可修改)',
  `shop_status` tinyint(2) DEFAULT NULL COMMENT '店铺状态(-1:未开通 0: 停业中 1:营业中)',
  `transport_type` tinyint(2) DEFAULT NULL COMMENT '0:商家承担运费; 1:买家承担运费',
  `fixed_freight` decimal(15,2) DEFAULT NULL COMMENT '运费',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `mobile` (`mobile`),
  UNIQUE KEY `shop_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8  COMMENT='商铺';

/**
*商品
*/
DROP TABLE IF EXISTS `prod`;

CREATE TABLE `prod` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `prod_name` varchar(300) NOT NULL DEFAULT '' COMMENT '商品名称',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `ori_price` decimal(15,2) DEFAULT '0.00' COMMENT '原价',
  `price` decimal(15,2) DEFAULT NULL COMMENT '现价',
  `brief` varchar(500) DEFAULT '' COMMENT '简要描述,卖点等',
  `attribute_list` json DEFAULT '' COMMENT '商品属性，可以改为单独的表id,value,name',
  `content` text COMMENT '详细描述',
  `pic` varchar(255) DEFAULT NULL COMMENT '商品主图',
  `imgs` varchar(1000) DEFAULT NULL COMMENT '商品图片，以,分割',
  `status` int(1) DEFAULT '0' COMMENT '默认是1，表示正常状态, -1表示删除, 0下架',
  `category_id` bigint(20)   DEFAULT NULL COMMENT '商品分类',
  `sold_num` int(11) DEFAULT NULL COMMENT '销量',
  `total_stocks` int(11) DEFAULT '0' COMMENT '总库存',
  `delivery_mode` int(3) DEFAULT '0' COMMENT '配送方式',
  `delivery_template_id` bigint(20) DEFAULT NULL COMMENT '运费',
  PRIMARY KEY (`id`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='商品';
DROP TABLE IF EXISTS `sku`;
/**
*商品规格表
*/
CREATE TABLE `sku` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `prod_id` bigint(20)   NOT NULL COMMENT '商品ID',
  `properties` json DEFAULT '' COMMENT '销售属性 可以是单独的表id,value,name',
  `ori_price` decimal(15,2) DEFAULT NULL COMMENT '原价',
  `price` decimal(15,2) DEFAULT NULL COMMENT '价格',
  `stocks` int(11) NOT NULL COMMENT '商品在付款减库存的状态下，该sku上未付款的订单数量',
  `actual_stocks` int(11) DEFAULT NULL COMMENT '实际库存',
  `update_time` datetime NOT NULL COMMENT '修改时间',
  `rec_time` datetime NOT NULL COMMENT '记录时间',
  `party_code` varchar(100) DEFAULT NULL COMMENT '商家编码',
  `model_id` varchar(100) DEFAULT NULL COMMENT '商品条形码',
  `pic` varchar(500) DEFAULT NULL COMMENT 'sku图片',
  `sku_name` varchar(120) DEFAULT NULL COMMENT 'sku名称',
  `prod_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `prod_id` (`prod_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='单品SKU表';



/**
*订单
*/
DROP TABLE IF EXISTS `order`;

CREATE TABLE `order` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `prod_name` varchar(1000) NOT NULL DEFAULT '' COMMENT '产品名称,多个产品将会以逗号隔开',
  `user_id` varchar(36) NOT NULL COMMENT '用户ID',
  `order_number` varchar(50) NOT NULL COMMENT '订单号',
  `total` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '原总金额',
  `actual_total` decimal(15,2) DEFAULT NULL COMMENT '实际总金额',
  `reduce_amount` decimal(15,2) DEFAULT NULL COMMENT '优惠总额',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式 1 微信支付 2 支付宝 3.银行卡 4其他',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:取消',
  `dvy_type` varchar(10) DEFAULT NULL COMMENT '配送类型',
  `dvy_id` bigint(20) DEFAULT NULL COMMENT '配送方式，物流配送',
  `dvy_flow_id` varchar(100) DEFAULT '' COMMENT '物流单号',
  `freight_amount` decimal(15,2) DEFAULT '0.00' COMMENT '订单运费',
  `order_addr_id` bigint(20) DEFAULT NULL COMMENT '用户订单地址Id',
  `product_nums` int(10) DEFAULT NULL COMMENT '订单商品总数',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `dvy_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finally_time` datetime DEFAULT NULL COMMENT '完成时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `is_payed` tinyint(1) DEFAULT NULL COMMENT '是否已经支付，1：已支付，0：未支付',
  `delete_status` int(1) DEFAULT '0' COMMENT '用户订单删除状态，0：未删除， 1：回收站， 2：永久删除',
  `refund_sts` int(1) DEFAULT '0' COMMENT '0:默认,1:在处理,2:处理完成',
  `close_type` tinyint(2) DEFAULT NULL COMMENT '订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_number_unique_ind` (`order_number`),
  UNIQUE KEY `order_number_userid_unique_ind` (`user_id`,`order_number`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 COMMENT='订单表';
/**
*订单详情
*/
DROP TABLE IF EXISTS `order_item`;

CREATE TABLE `order_item` (
  `id` bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺id',
  `order_number` varchar(50) NOT NULL COMMENT '订单order_number',
  `prod_id` bigint(20)   NOT NULL COMMENT '产品ID',
  `sku_id` bigint(20)   NOT NULL COMMENT '产品SkuID',
  `prod_count` int(11) NOT NULL DEFAULT '0' COMMENT '购物车产品个数',
  `prod_name` varchar(120) NOT NULL DEFAULT '' COMMENT '产品名称',
  `sku_name` varchar(120) DEFAULT NULL COMMENT 'sku名称',
  `pic` varchar(255) NOT NULL DEFAULT '' COMMENT '产品主图片路径',
  `price` decimal(15,2) NOT NULL COMMENT '产品价格',
  `user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '用户Id',
  `product_total_amount` decimal(15,2) NOT NULL COMMENT '商品总金额',
  `reduce_amount` decimal(15,2) DEFAULT NULL COMMENT '优惠金额',
  `actual_total` decimal(15,2) DEFAULT NULL COMMENT '实际金额',
  `rec_time` datetime NOT NULL COMMENT '购物时间',
  `comm_sts` int(1) NOT NULL DEFAULT '0' COMMENT '评论状态： 0 未评价  1 已评价',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order_number` (`order_number`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='订单详情';

/**
*订单快照
*/
DROP TABLE IF EXISTS `order_snapshot`;

CREATE TABLE `order_snapshot` (
  `id` bigint(20)  NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `shop_id` bigint(20) DEFAULT NULL COMMENT '店铺id',
  `prod_name` varchar(1000) NOT NULL DEFAULT '' COMMENT '产品名称,多个产品将会以逗号隔开',
  `user_id` varchar(36) NOT NULL COMMENT '用户ID',
  `order_number` varchar(50) NOT NULL COMMENT '订单号',
  `total` decimal(15,2) NOT NULL DEFAULT '0.00' COMMENT '原总金额',
  `actual_total` decimal(15,2) DEFAULT NULL COMMENT '实际总金额',
  `reduce_amount` decimal(15,2) DEFAULT NULL COMMENT '优惠总额',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式 1 微信支付 2 支付宝 3.银行卡 4其他',
  `remarks` varchar(1024) DEFAULT NULL COMMENT '备注',
  `status` int(2) NOT NULL DEFAULT '0' COMMENT '订单状态 1:待付款 2:待发货 3:待收货 4:待评价 5:取消',
  `dvy_type` varchar(10) DEFAULT NULL COMMENT '配送类型',
  `dvy_id` bigint(20) DEFAULT NULL COMMENT '配送方式',
  `dvy_flow_id` varchar(100) DEFAULT '' COMMENT '物流单号',
  `freight_amount` decimal(15,2) DEFAULT '0.00' COMMENT '订单运费',
  `order_addr_id` bigint(20) DEFAULT NULL COMMENT '用户订单地址Id',
  `product_nums` int(10) DEFAULT NULL COMMENT '订单商品总数',
  `pay_time` datetime DEFAULT NULL COMMENT '付款时间',
  `dvy_time` datetime DEFAULT NULL COMMENT '发货时间',
  `finally_time` datetime DEFAULT NULL COMMENT '完成时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `is_payed` tinyint(1) DEFAULT NULL COMMENT '是否已经支付，1：已支付，0：未支付',
  `delete_status` int(1) DEFAULT '0' COMMENT '用户订单删除状态，0：未删除， 1：回收站， 2：永久删除',
  `refund_sts` int(1) DEFAULT '0' COMMENT '0:默认,1:在处理,2:处理完成',
  `close_type` tinyint(2) DEFAULT NULL COMMENT '订单关闭原因 1-超时未支付 2-退款关闭 4-买家取消 15-已通过货到付款交易',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `order_number_unique_ind` (`order_number`),
  UNIQUE KEY `order_number_userid_unique_ind` (`user_id`,`order_number`),
  KEY `shop_id` (`shop_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='订单快照';
/**
*订单详情快照
*/
DROP TABLE IF EXISTS `order_item_snapshot`;

CREATE TABLE `order_item_snapshot` (
  `id` bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺id',
  `order_number` varchar(50) NOT NULL COMMENT '订单order_number',
  `prod_id` bigint(20)   NOT NULL COMMENT '产品ID',
  `sku_id` bigint(20)   NOT NULL COMMENT '产品SkuID',
  `prod_count` int(11) NOT NULL DEFAULT '0' COMMENT '购物车产品个数',
  `prod_name` varchar(120) NOT NULL DEFAULT '' COMMENT '产品名称',
  `sku_name` varchar(120) DEFAULT NULL COMMENT 'sku名称',
  `pic` varchar(255) NOT NULL DEFAULT '' COMMENT '产品主图片路径',
  `price` decimal(15,2) NOT NULL COMMENT '产品价格',
  `user_id` varchar(36) NOT NULL DEFAULT '' COMMENT '用户Id',
  `product_total_amount` decimal(15,2) NOT NULL COMMENT '商品总金额',
  `reduce_amount` decimal(15,2) DEFAULT NULL COMMENT '优惠金额',
  `actual_total` decimal(15,2) DEFAULT NULL COMMENT '实际金额',
  `rec_time` datetime NOT NULL COMMENT '购物时间',
  `comm_sts` int(1) NOT NULL DEFAULT '0' COMMENT '评论状态： 0 未评价  1 已评价',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `order_number` (`order_number`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8 COMMENT='订单详情快照';
/**
*支付信息
*/
DROP TABLE IF EXISTS `order_payment`;

CREATE TABLE `order_payment` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '支付结算单据ID',
  `pay_no` varchar(36) DEFAULT NULL COMMENT '支付单号',
  `biz_pay_no` varchar(255) DEFAULT NULL COMMENT '外部订单流水号',
  `order_number` varchar(36) DEFAULT NULL COMMENT 'order表中的订单号',
  `pay_type` int(1) DEFAULT NULL COMMENT '支付方式 1 微信支付 2 支付宝 ...',
  `pay_status` int(1) DEFAULT NULL COMMENT '支付状态',
  `pay_type_name` varchar(50) DEFAULT NULL COMMENT '支付方式名称',
  `pay_amount` decimal(15,2) DEFAULT NULL COMMENT '支付金额',
  `is_clearing` int(1) DEFAULT NULL COMMENT '是否清算 0:否 1:是',
  `user_id` varchar(36) DEFAULT NULL COMMENT '用户ID',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `primary_order_no` (`order_number`),
  KEY `user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='支付信息';

/**
*退款
*/
DROP TABLE IF EXISTS `order_refund`;

CREATE TABLE `order_refund` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '退款记录ID',
  `shop_id` bigint(20) NOT NULL COMMENT '店铺ID',
  `order_id` bigint(20) NOT NULL COMMENT '订单ID',
  `order_number` varchar(50) NOT NULL COMMENT '订单流水号',
  `order_amount` double(12,2) NOT NULL COMMENT '订单总金额',
  `order_item_id` bigint(20) NOT NULL DEFAULT '0' COMMENT '订单项ID 全部退款是0',
  `refund_sn` varchar(50) NOT NULL COMMENT '退款编号',
  `flow_trade_no` varchar(100) NOT NULL COMMENT '订单支付流水号',
  `out_refund_no` varchar(200) DEFAULT NULL COMMENT '第三方退款单号',
  `pay_type` int(1) DEFAULT NULL COMMENT '订单支付方式 1 微信支付 2 支付宝',
  `pay_type_name` varchar(50) DEFAULT NULL COMMENT '订单支付名称',
  `user_id` varchar(50) NOT NULL COMMENT '买家ID',
  `goods_num` int(11) DEFAULT NULL COMMENT '退货数量',
  `refund_amount` decimal(10,2) NOT NULL COMMENT '退款金额',
  `apply_type` int(1) NOT NULL DEFAULT '0' COMMENT '申请类型:1,仅退款,2退款退货',
  `refund_sts` int(1) NOT NULL DEFAULT '0' COMMENT '处理状态:1为待审核,2同意,3不同意',
  `return_money_sts` int(1) NOT NULL DEFAULT '0' COMMENT '处理退款状态: 0:退款处理中 1:退款成功 -1:退款失败',
  `apply_time` datetime NOT NULL COMMENT '申请时间',
  `handel_time` datetime DEFAULT NULL COMMENT '卖家处理时间',
  `refund_time` datetime DEFAULT NULL COMMENT '退款时间',
  `photo_files` varchar(150) DEFAULT NULL COMMENT '文件凭证',
  `buyer_msg` varchar(300) DEFAULT NULL COMMENT '申请原因',
  `seller_msg` varchar(300) DEFAULT NULL COMMENT '卖家备注',
  `express_name` varchar(50) DEFAULT NULL COMMENT '物流公司名称',
  `express_no` varchar(50) DEFAULT NULL COMMENT '物流单号',
  `ship_time` datetime DEFAULT NULL COMMENT '发货时间',
  `receive_time` datetime DEFAULT NULL COMMENT '收货时间',
  `receive_message` varchar(300) DEFAULT NULL COMMENT '收货备注',
  `create_at` datetime DEFAULT NULL,
  `create_by` varchar(50) DEFAULT NULL,
  `last_update_at` datetime DEFAULT NULL,
  `last_update_by` varchar(50) DEFAULT NULL,
  `is_deleted` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `refund_sn_unique` (`refund_sn`),
  KEY `order_number` (`order_number`)
) ENGINE=InnoDB AUTO_INCREMENT=20  DEFAULT CHARSET=utf8  COMMENT='退款信息';

/**
* 物流
*/
DROP TABLE IF EXISTS `delivery`;

CREATE TABLE `delivery` (
  `dvy_id` bigint(20)   NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `dvy_name` varchar(50) NOT NULL DEFAULT '' COMMENT '物流公司名称',
  `company_home_url` varchar(255) DEFAULT NULL COMMENT '公司主页',
  `query_url` varchar(520) NOT NULL COMMENT '查询接口，快递100接口。',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8 COMMENT='物流公司';
