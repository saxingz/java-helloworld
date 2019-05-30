-- 指定存储引擎
create table T(c int) engine=InnoDB;

-- 查看事务隔离级别
show variables like 'transaction_isolation';

-- 查找持续时间超过 60s 的事务
select * from information_schema.innodb_trx where TIME_TO_SEC(timediff(now(),trx_started))>60

-- 存储过程来插入数据
delimiter ;;
create procedure idata()
begin
  declare i int;
  set i=1;
  while(i<=100000)do
    insert into t values(i, i, i);
    set i=i+1;
  end while;
end;;
delimiter ;
call idata();

-- explain
explain select * from t where a between 10000 and 20000;

set long_query_time=0;
select * from t where a between 10000 and 20000; /*Q1*/
select * from t force index(a) where a between 10000 and 20000;/*Q2*/
-- 复制代码
-- 第一句，是将慢查询日志的阈值设置为 0，表示这个线程接下来的语句都会被记录入慢查询日志中；
-- 第二句，Q1 是 session B 原来的查询；
-- 第三句，Q2 是加了 force index(a) 来和 session B 原来的查询语句执行情况对比。

-- 查看索引
show index from t;

-- 重新统计索引信息
analyze table t;

-- 查不同的值
select count(distinct email) as L from SUser;

-- 倒序存储
mysql> select field_list from t where id_card = reverse('input_id_card_string');

-- 增加hash
mysql> select field_list from t where id_card_crc=crc32('input_id_card_string') and id_card='input_id_card_string'

-- 测试磁盘IO
 fio -filename=$filename -direct=1 -iodepth 1 -thread -rw=randrw -ioengine=psync -bs=16k -size=500M -numjobs=10 -runtime=10 -group_reporting -name=mytest


