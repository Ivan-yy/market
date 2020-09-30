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
    db.${collection}.find(${condition}[,${columns}])
    
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