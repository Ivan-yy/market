C:
    查看一个不存在的库不报错，库里有数据时隐式创建
    show databases
    use test
    show collections
    db.createCollection('col1')
    db.col1.insert({name:"dog",age:1})       #键不加"",mongo隐式加
    #批量插入
    db.col1.insert([
        {name:"dog",age:1},
        {name:"pig",age:3},
        {name:"john",age:18,sex:1}
    ])
    
   for(var i = 0;i < 10;i++){
        db.col1.insert({name:"chicken"+i,age:3}) 
   }
    mongo的_id 组成： 
    0 1 2 3| 4 5 6| 7 8| 9 10 11 
        时间|  机器| PID|  计数器
R:
    db.${collection}.find(${condition}[,${columns}]).sort({${column}:1} (1升序 -1降序)).skip(${skipNum}).limit(${pageSize})
    
    #查询age=1的_id列
    db.col1.find({age:1},{_id:1})
    #查询age>5的文档
    #  $gt  >  
    #  $gte >=
    #  $lt  <
    #  $lte <=
    #  $ne  <>
    #  $in  in
    #  $nin not in
    #age>5的文档
    db.col1.find({age:{$gt:5}})
    #age=3,18的文档
    db.col1.find({age:{$in:[3,18]}})
U:
    db.${col}.update(${condition},${newData},[,${ifInsert}(匹配不到的话是否新增，默认false),${ifBatchUpdate}(匹配多条的话是否批量更新，默认false)])
    # 修改器
    #  $inc  增加  
    #  $rename 重命名列
    #  $set 修改列值
    #  $unset 删除列

D:
    db.${col}.remove(${condition}[,${ifRemoveOne}(是否删除一条，默认false,即默认删除全部匹配文档)])
    
聚合查询:
    管道
    #  group  分组
    #  match  过滤数据
    #  sort  排序
    #  skip  跳过
    #  limit 分页
    表达式
    #  sum
    #  avg
    #  max
    #  min
    db.${col}.aggregate([
        {管道:{表达式}}
    ])
    #根据name分组，统计每个相同name共有多少文档
    db.col1.aggregate([
        {$group:{_id:"$name",result:{$sum:1}}}
    ])
    #根据name分组，统计每个相同name的age总和,降序排序
    db.col1.aggregate([
            {$group:{_id:"$name",result:{$sum:"$age"}}},
            {$sort:{result:-1}}
        ])
 
INDEX:
    db.${col}.createIndex({${column}:1}[,{name:""}])
    #查看索引
    db.${col}.getIndexes()
    #执行计划
    db.batch.find({name:"chicken999999"}).explain('executionStats')
    #创建唯一索引，名字为idx_name
    db.batch.createIndex({name:1},{unique:"name",name:"idx_name"})
    